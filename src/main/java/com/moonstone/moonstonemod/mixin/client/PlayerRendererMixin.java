package com.moonstone.moonstonemod.mixin.client;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin  extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }
//
//    @Inject(at = @At("RETURN"), method = "<init>")
//    public void init(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
//        MoonPost.renderEffectForNextTick(MoonStoneMod.POST_Blood);
//        ModelPart modelPart = context.bakeLayer(MoonStoneMod.Client.SWORD);
//        this.addLayer(new SwordRenderLayer<>(this,modelPart));
//        ModelPart modelPart1 = context.bakeLayer(MoonStoneMod.Client.SWORDOut);
//        this.addLayer(new SwordRenderLayer<>(this,modelPart1));
//    }

}
