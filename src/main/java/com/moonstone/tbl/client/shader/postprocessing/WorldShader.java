package com.moonstone.tbl.client.shader.postprocessing;

import com.all.IPostChain;
import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import com.moonstone.tbl.client.renderer.GLTextureObjectWrapper;
import com.moonstone.tbl.client.shader.LightSource;
import com.moonstone.tbl.common.MoonstoneTBL;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TODO: Finish and add Starfield, OcclusionExtractor, Godrays, and Swirl shaders.
 */
public class WorldShader extends PostChain implements AutoCloseable {;

	public static final ResourceLocation GAS_PARTICLE_TEXTURE = MoonstoneTBL.prefix("gas_particle");

	private RenderTarget depthBuffer;
	private RenderTarget repellerShieldBuffer;
	private RenderTarget gasParticlesBuffer;

	private Matrix4f invertedModelviewProjectionMatrix;
	private Matrix4f modelviewProjectionMatrix;
	private Matrix4f modelviewMatrix;
	private Matrix4f projectionMatrix;

	public static final int MAX_LIGHT_SOURCES_PER_PASS = 32;
	private final List<LightSource> lightSources = new ArrayList<>();
	public Vector3f cameraPos = new Vector3f();

	private static final int WORLD_INDEX = 0;	// The world shader pass index
	//Uniforms
	@Nullable
	public Uniform MVPUniform;
	@Nullable
	public Uniform invMVPUniform;
	@Nullable
	public Uniform projectionUniform;
	@Nullable
	public  Uniform viewPosUniform;
	@Nullable
	public  Uniform renderPosUniform;
	@Nullable
	private  Uniform lightSourcePositionUniforms;
	@Nullable
	private  Uniform lightSourceColorUniforms;
	@Nullable
	private  Uniform lightSourceRadiusUniforms;
	@Nullable
	private  Uniform lightSourceAmountUniform;
	@Nullable
	public Uniform worldTimeUniform;

	//Effects
	@Nullable
	private Warp gasWarpEffect = null;


	public WorldShader(TextureManager textureManager, ResourceProvider resourceProvider, RenderTarget screenTarget) throws IOException, JsonSyntaxException {
		super(textureManager, resourceProvider, screenTarget, ResourceLocation.fromNamespaceAndPath(MoonstoneTBL.ID, "shaders/post/world.json"));

		/* 	PostChain structure:
		 * 		0 - 	World shader 		= WORLD_INDEX
		 * 		1 -	 	Blit to screen		= BLIT_INDEX
		 */

		// Get Uniforms
		this.invMVPUniform = this.getUniform(WORLD_INDEX, "u_INVMVP");
		this.viewPosUniform = this.getUniform(WORLD_INDEX, "u_viewPos");
		this.renderPosUniform = this.getUniform(WORLD_INDEX, "u_renderPos");
		Uniform fogModeUniform = this.getUniform(WORLD_INDEX, "u_fogMode");	// temp
		this.MVPUniform = this.getUniform(WORLD_INDEX, "u_MVP");
		this.worldTimeUniform = this.getUniform(WORLD_INDEX, "u_worldTime");

		this.lightSourcePositionUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_position");
		this.lightSourceColorUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_color");
		this.lightSourceRadiusUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_radius");
		this.lightSourceAmountUniform = this.getUniform(WORLD_INDEX, "u_lightSourcesAmount");

		// Set samplers
		this.depthBuffer = this.getTempTarget("s_diffuse_depth");
		this.repellerShieldBuffer = this.getTempTarget("s_repellerShield");
		this.gasParticlesBuffer = this.getTempTarget("s_gasParticles");

		// Setup additional effects
		// TODO: consider separating from WorldShader into separate PostChain instances.
		this.gasWarpEffect = new Warp(textureManager, resourceProvider, screenTarget);
		//this.occlusionExtractor;
		//this.godRayEffect;
		//this.swirlEffect;
		//this.groundFogEffect;

		Minecraft.getInstance().getTextureManager().register(GAS_PARTICLE_TEXTURE, new GLTextureObjectWrapper(this.gasWarpEffect.gasTextureTarget.getColorTextureId()));
	}

	public void cleanUp() {
		clearLights();
	}


	private static final Comparator<LightSource> LIGHT_SOURCE_SORTER = (o1, o2) -> {
		double dx1 = o1.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
		double dy1 = o1.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
		double dz1 = o1.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
		double dx2 = o2.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
		double dy2 = o2.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
		double dz2 = o2.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
		double d1 = Math.sqrt(dx1 * dx1 + dy1 * dy1 + dz1 * dz1);
		double d2 = Math.sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2);
		if (d1 > d2) {
			return 1;
		} else if (d1 < d2) {
			return -1;
		}
		return 0;
	};

	public void uploadUniforms(float partialTicks) {
		this.invMVPUniform.set(invertedModelviewProjectionMatrix);
		//this.MVPUniform.set(modelviewProjectionMatrix);
		this.viewPosUniform.set(this.cameraPos);
		//this.screenSize.set((float)Minecraft.getInstance().getWindow().getWidth(), (float)Minecraft.getInstance().getWindow().getHeight());

		float[] positionBuff = new float[96];
		float[] colorBuff = new float[96];

		//Sort lights by distance
		Collections.sort(this.lightSources, LIGHT_SOURCE_SORTER);

		if (!lightSources.isEmpty()) {
			for (int i = 0; i < MAX_LIGHT_SOURCES_PER_PASS && i < lightSources.size(); i++) {
				positionBuff[(i*3)] = ((float) this.lightSources.get(i).x - this.cameraPos.x);
				positionBuff[(i*3)+1] = ((float) this.lightSources.get(i).y - this.cameraPos.y);
				positionBuff[(i*3)+2] = ((float) this.lightSources.get(i).z - this.cameraPos.z);
				colorBuff[(i*3)] = (this.lightSources.get(i).r);
				colorBuff[(i*3)+1] = (this.lightSources.get(i).g);
				colorBuff[(i*3)+2] = (this.lightSources.get(i).b);

				lightSourceRadiusUniforms.set(i, this.lightSources.get(i).radius);
			}

			lightSourcePositionUniforms.set(positionBuff);
			lightSourceColorUniforms.set(colorBuff);
		}

		lightSourceAmountUniform.set(Math.min(this.lightSources.size(), 32));
	}

		/**
	 * Returns the depth buffer
	 *
	 * @return
	 */
	public RenderTarget getDepthBuffer() {
		return this.depthBuffer;
	}

	/**
	 * Updates following matrices: MV (Modelview), PM (Projection), MVP (Modelview x Projection), INVMVP (Inverted MVP)
	 */
	public void updateMatrices(final RenderLevelStageEvent event) {
		this.cameraPos = event.getCamera().getPosition().toVector3f();
		this.modelviewMatrix = event.getModelViewMatrix().transpose(new Matrix4f());
		this.projectionMatrix = event.getProjectionMatrix().transpose(new Matrix4f());
		this.invertedModelviewProjectionMatrix = new Matrix4f();

		Matrix4f MVP = this.modelviewMatrix.mul(this.projectionMatrix);
		MVP.invert(this.invertedModelviewProjectionMatrix);

		this.invertedModelviewProjectionMatrix = invertedModelviewProjectionMatrix.assume(0);
	}


	public void addLight(LightSource light) {
		this.lightSources.add(light);
	}

	/**
	 * Clears all dynamic light sources
	 */
	public void clearLights() {
		this.lightSources.clear();
	}

	/**
	 * Updates the shader textures
	 *
	 * @param partialTicks
	 */
	public void updateTextures(float partialTicks) {
		ClientLevel level = Minecraft.getInstance().level;
		if (level != null && !Minecraft.getInstance().isPaused()) {
			//Update gas particles
			this.updateGasParticlesTexture(level, partialTicks);

			//Update starfield texture
//			this.updateStarfieldTexture(partialTicks);

			// Reset main render target
			Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
		}
	}

	/**
	 * Renders additional post processing effects such as god's rays, swirl etc.
	 *
	 * @param partialTicks
	 */

	private void updateGasParticlesTexture(ClientLevel level, float partialTicks) {
		boolean hasCloud = true;//DefaultParticleBatches.GAS_CLOUDS_TEXTURED.getParticles().size() > 0 || DefaultParticleBatches.GAS_CLOUDS_HEAT_HAZE.getParticles().size() > 0;
		if (hasCloud) {
			//Update gas texture
			this.gasWarpEffect.uploadUniforms(partialTicks);
			this.gasWarpEffect.process(partialTicks);

			// Retarget Minecraft MainRenderTarget
			Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
		}
	}
	/**
	 * Used to target a specific PostPass uniform value.
	 * @param index
	 * @param name
	 * @return uniform in PostPass index (index) with key of (name)
	 */
	public Uniform getUniform(int index, String name) {
		if (this instanceof IPostChain iPostChain){
			return iPostChain.moonstone1_21_1$passes().get(index).getEffect().getUniform(name);
		}
		return null;
	}
}
