package com.moonstone.moonstonemod.client.entitys.blood;

import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import net.minecraft.client.animation.definitions.BatAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class BloodBatModel  extends HierarchicalModel<blood_bat> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;
    private final ModelPart feet;

    public BloodBatModel(ModelPart pRoot) {
        super(RenderType::entityCutout);
        this.root = pRoot;
        this.body = pRoot.getChild("body");
        this.head = pRoot.getChild("head");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingTip = this.rightWing.getChild("right_wing_tip");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingTip = this.leftWing.getChild("left_wing_tip");
        this.feet = this.body.getChild("feet");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(blood_bat pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(pEntity.flyAnimationState, BatAnimation.BAT_FLYING, pAgeInTicks, 1.0F);
        this.animate(pEntity.restAnimationState, BatAnimation.BAT_RESTING, pAgeInTicks, 1.0F);
    }

    private void applyHeadRotation(float pHeadRotation) {
        this.head.yRot = pHeadRotation * (float) (Math.PI / 180.0);
    }
}
