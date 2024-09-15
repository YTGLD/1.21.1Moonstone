package com.moonstone.moonstonemod.entity.client.zombie;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.zombie.cell_giant;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CellZombieG  extends MobRenderer<cell_giant, GModel<cell_giant>> {
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/cell_giant.png");
    private static final ResourceLocation HEART_TEXTURE = ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/hearth.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = ResourceLocation.fromNamespaceAndPath("minecraft","textures/entity/warden/warden_pulsating_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = ResourceLocation.fromNamespaceAndPath("minecraft","textures/entity/warden/warden_pulsating_spots_2.png");

    public CellZombieG(EntityRendererProvider.Context p_234787_) {
        super(p_234787_, new GModel<>(p_234787_.bakeLayer(ModelLayers.WARDEN)), 0.9F);

        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_1, (p_234805_, p_234806_, p_234807_) -> {
            return Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_2, (p_234801_, p_234802_, p_234803_) -> {
            return Math.max(0.0F, Mth.cos(p_234803_ * 0.045F + (float)Math.PI) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts));

    }

    public ResourceLocation getTextureLocation(cell_giant p_234791_) {
        return TEXTURE;
    }
}
