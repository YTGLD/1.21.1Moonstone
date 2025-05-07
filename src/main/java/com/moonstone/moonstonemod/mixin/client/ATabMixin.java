package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.IAdvancementWidget;
import com.moonstone.moonstonemod.client.renderer.MRender;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AdvancementTab.class)
public abstract class ATabMixin {

    @Shadow @Final private AdvancementWidget root;

    @Shadow @Final private ItemStack icon;

    @Shadow private boolean centered;

    @Shadow private double scrollX;

    @Shadow private double scrollY;

    @Shadow private int maxX;

    @Shadow private int minX;

    @Shadow private int minY;

    @Shadow private int maxY;

    @Shadow @Final private DisplayInfo display;

    @Shadow @Final private AdvancementNode rootNode;


    @Shadow @Final private Map<AdvancementHolder, AdvancementWidget> widgets;

    @Shadow private float fade;

    @Shadow @Final private int index;

    @Inject(at = @At("RETURN"), method = "drawContents(Lnet/minecraft/client/gui/GuiGraphics;II)V")
    public void moonstone1rawContents(GuiGraphics guiGraphics, int x, int y, CallbackInfo ci){
        if (this.rootNode.holder().id().equals(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"moonstone/root_nightmare"))){

            guiGraphics.enableScissor(x, y, x + 234, y + 113);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate((float)x, (float)y, 0.0F);
            ResourceLocation resourcelocation = this.display.getBackground().orElse(TextureManager.INTENTIONAL_MISSING_TEXTURE);
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            int k = i % 16;
            int l = j % 16;

            for(int i1 = -1; i1 <= 15; ++i1) {
                for(int j1 = -1; j1 <= 8; ++j1) {
                    moonstone1seekingImmortals$blit(MRender.gui(),guiGraphics,resourcelocation, k + 16 * i1, l + 16 * j1, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            if (root instanceof IAdvancementWidget iAdvancementWidget) {
                iAdvancementWidget.moonstone1$draw(guiGraphics, i, j);
            }

            guiGraphics.pose().popPose();
            guiGraphics.disableScissor();
        }
        if (this.rootNode.holder().id().equals(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"moonstone/root_necora"))){

            guiGraphics.enableScissor(x, y, x + 234, y + 113);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate((float)x, (float)y, 0.0F);
            ResourceLocation resourcelocation = this.display.getBackground().orElse(TextureManager.INTENTIONAL_MISSING_TEXTURE);
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            int k = i % 16;
            int l = j % 16;

            for(int i1 = -1; i1 <= 15; ++i1) {
                for(int j1 = -1; j1 <= 8; ++j1) {
                    moonstone1seekingImmortals$blit(MRender.gui(),guiGraphics,resourcelocation, k + 16 * i1, l + 16 * j1, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            if (root instanceof IAdvancementWidget iAdvancementWidget) {
                iAdvancementWidget.moonstone1$drawNecora(guiGraphics, i, j);
            }

            guiGraphics.pose().popPose();
            guiGraphics.disableScissor();
        }
    }

    @Inject(at = @At("RETURN"), method = "drawTooltips")
    public void  moonstone1drawTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int width, int height, CallbackInfo ci) {
        if (this.rootNode.holder().id().equals(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"moonstone/root_nightmare"))) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0F, 0.0F, -200.0F);
            guiGraphics.fill(0, 0, 234, 113, Mth.floor(this.fade * 255.0F) << 24);
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            if (mouseX > 0 && mouseX < 234 && mouseY > 0 && mouseY < 113) {
                for (AdvancementWidget advancementwidget : this.widgets.values()) {
                    if (advancementwidget.isMouseOver(i, j, mouseX, mouseY)) {
                        if (advancementwidget instanceof IAdvancementWidget iAdvancementWidget) {
                            iAdvancementWidget.moonstone1$drawHover(guiGraphics, i, j, this.fade, width, height);
                            break;
                        }
                    }
                }
            }
            guiGraphics.pose().popPose();
        }
        if (this.rootNode.holder().id().equals(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"moonstone/root_necora"))) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0F, 0.0F, -200.0F);
            guiGraphics.fill(0, 0, 234, 113, Mth.floor(this.fade * 255.0F) << 24);
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            if (mouseX > 0 && mouseX < 234 && mouseY > 0 && mouseY < 113) {
                for (AdvancementWidget advancementwidget : this.widgets.values()) {
                    if (advancementwidget.isMouseOver(i, j, mouseX, mouseY)) {
                        if (advancementwidget instanceof IAdvancementWidget iAdvancementWidget) {
                            iAdvancementWidget.moonstone1$drawHoverNecora(guiGraphics, i, j, this.fade, width, height);
                            break;
                        }
                    }
                }
            }
            guiGraphics.pose().popPose();
        }
    }
    @Unique
    void  moonstone1seekingImmortals$innerBlit(RenderType renderType, GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        VertexConsumer vertexConsumer = guiGraphics.bufferSource().getBuffer(renderType);
        guiGraphics.pose().pushPose();
        vertexConsumer.addVertex(matrix4f, (float)x1, (float)y1, (float)blitOffset).setUv(minU, minV).setColor(1,1,1,1);
        vertexConsumer.addVertex(matrix4f, (float)x1, (float)y2, (float)blitOffset).setUv(minU, maxV).setColor(1,1,1,1);
        vertexConsumer.addVertex(matrix4f, (float)x2, (float)y2, (float)blitOffset).setUv(maxU, maxV).setColor(1,1,1,1);
        vertexConsumer.addVertex(matrix4f, (float)x2, (float)y1, (float)blitOffset).setUv(maxU, minV).setColor(1,1,1,1);
        guiGraphics.pose().popPose();
    }
    @Unique
    void  moonstone1seekingImmortals$blit(RenderType renderType, GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        this. moonstone1seekingImmortals$innerBlit(renderType,guiGraphics,atlasLocation, x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float)textureWidth, (uOffset + (float)uWidth) / (float)textureWidth, (vOffset + 0.0F) / (float)textureHeight, (vOffset + (float)vHeight) / (float)textureHeight);
    }
    @Unique
    public void  moonstone1seekingImmortals$blit(RenderType renderType, GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        this. moonstone1seekingImmortals$blit(renderType,guiGraphics,atlasLocation, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    @Unique
    public void  moonstone1seekingImmortals$blit(RenderType renderType, GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        this. moonstone1seekingImmortals$blit(renderType,guiGraphics,atlasLocation, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

}
