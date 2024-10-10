package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class MedicineboxUi {
    public MedicineboxUi(@NotNull PoseStack matrices,
                         @NotNull MultiBufferSource vertexConsumers,
                         int light,
                         @NotNull Entity entity,
                         EntityModel<?> model) {
        if (entity instanceof Player player) {
            if (Handler.hascurio(player, Items.medicinebox.get())) {
                EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
                if (render instanceof LivingEntityRenderer<?,?> renderer) {
                    if (model instanceof HumanoidModel) {
                        matrices.pushPose();

                        matrices.mulPose(Axis.ZP.rotationDegrees(180));
                        matrices.mulPose(Axis.YP.rotationDegrees(270));

                        matrices.mulPose(Axis.ZP.rotation(((HumanoidModel<?>) model).body.xRot));

                        matrices.translate(0.4, 0.05, 0);

                        Minecraft.getInstance().getItemRenderer().renderStatic(
                                Items.medicinebox_ui.get().getDefaultInstance(),
                                ItemDisplayContext.GROUND,
                                light,
                                OverlayTexture.NO_OVERLAY,
                                matrices,
                                vertexConsumers,
                                entity.level(),
                                0);

                        if (entity.isShiftKeyDown()) {
                            matrices.translate(0.0D, 0.1875D, 0.2D);
                        }

                        matrices.popPose();
                    }

                }
            }
        }
    }
}
