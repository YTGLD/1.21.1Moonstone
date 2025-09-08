package com.moonstone.moonstonemod.client.model;

import com.moonstone.moonstonemod.client.renderer.MRender;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.player.Player;

public class SwordSoul2Use <T extends Player>extends HumanoidModel<T> {
    public SwordSoul2Use(ModelPart root) {
        super(root, MRender::entityCutoutNoCull1outline);
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 65).addBox(-31.0F, -32.0F, -1.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(41.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.0436F));
        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 33).addBox(-31.0F, -32.0F, -1.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(37.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.48F));
        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 1).addBox(-31.0F, -32.0F, -1.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(30.0F, -35.0F, 0.0F, 0.0F, 0.0F, -0.3054F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
