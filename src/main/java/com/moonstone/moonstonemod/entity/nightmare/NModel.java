package com.moonstone.moonstonemod.entity.nightmare;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.animation.definitions.WardenAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

import java.util.List;

public class NModel<T extends nightmare_giant> extends HierarchicalModel<T> {
    private final ModelPart root;
    public final ModelPart bone;
    public final ModelPart body;
    public final ModelPart head;
    public final ModelPart rightTendril;
    public final ModelPart leftTendril;
    public final ModelPart leftLeg;
    public final ModelPart leftArm;
    public final ModelPart leftRibcage;
    public final ModelPart rightArm;
    public final ModelPart rightLeg;
    public final ModelPart rightRibcage;
    private final List<ModelPart> tendrilsLayerModelParts;
    private final List<ModelPart> heartLayerModelParts;
    private final List<ModelPart> bioluminescentLayerModelParts;
    private final List<ModelPart> pulsatingSpotsLayerModelParts;

    public NModel(ModelPart pRoot) {
        super(RenderType::entityCutoutNoCull);
        this.root = pRoot;
        this.bone = pRoot.getChild("bone");
        this.body = this.bone.getChild("body");
        this.head = this.body.getChild("head");
        this.rightLeg = this.bone.getChild("right_leg");
        this.leftLeg = this.bone.getChild("left_leg");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.rightTendril = this.head.getChild("right_tendril");
        this.leftTendril = this.head.getChild("left_tendril");
        this.rightRibcage = this.body.getChild("right_ribcage");
        this.leftRibcage = this.body.getChild("left_ribcage");
        this.tendrilsLayerModelParts = ImmutableList.of(this.leftTendril, this.rightTendril);
        this.heartLayerModelParts = ImmutableList.of(this.body);
        this.bioluminescentLayerModelParts = ImmutableList.of(this.head, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
        this.pulsatingSpotsLayerModelParts = ImmutableList.of(this.body, this.head, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float f = pAgeInTicks - (float)pEntity.tickCount;
        this.animateHeadLookTarget(pNetHeadYaw, pHeadPitch);
        this.animateWalk(pLimbSwing, pLimbSwingAmount);
        this.animateIdlePose(pAgeInTicks);
        this.animateTendrils(pEntity, pAgeInTicks, f);
        this.animate(pEntity.attackAnimationState, WardenAnimation.WARDEN_ATTACK, pAgeInTicks);
        this.animate(pEntity.diggingAnimationState, WardenAnimation.WARDEN_DIG, pAgeInTicks);
        this.animate(pEntity.emergeAnimationState, WardenAnimation.WARDEN_EMERGE, pAgeInTicks);
        this.animate(pEntity.roarAnimationState, WardenAnimation.WARDEN_ROAR, pAgeInTicks);
        this.animate(pEntity.sniffAnimationState, WardenAnimation.WARDEN_SNIFF, pAgeInTicks);
    }

    private void animateHeadLookTarget(float pYaw, float pPitch) {
        this.head.xRot = pPitch * (float) (Math.PI / 180.0);
        this.head.yRot = pYaw * (float) (Math.PI / 180.0);

        this.head.xRot  -= 35 * 0.017453292F;

    }

    private void animateIdlePose(float pAgeInTicks) {
        float f = pAgeInTicks * 0.1F;
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);
        this.head.zRot += 0.06F * f1;
        this.head.xRot += 0.06F * f2;
        this.body.zRot += 0.025F * f2;
        this.body.xRot += 0.025F * f1;
    }

    private void animateWalk(float pLimbSwing, float pLimbSwingAmount) {
        float f = Math.min(0.5F, 3.0F * pLimbSwingAmount);
        float f1 = pLimbSwing * 0.8662F;
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Math.min(0.35F, f);
        this.head.zRot += 0.3F * f3 * f;
        this.head.xRot = this.head.xRot + 1.2F * Mth.cos(f1 + (float) (Math.PI / 2)) * f4;
        this.body.zRot = 0.1F * f3 * f;
        this.body.xRot = 1.0F * f2 * f4;
        this.body.y += 1.5f;
        this.leftLeg.xRot = 1.0F * f2 * f;
        this.rightLeg.xRot = 1.0F * Mth.cos(f1 + (float) Math.PI) * f;
        this.leftArm.xRot = -(0.8F * f2 * f);
        this.leftArm.zRot = 0.0F;
        this.rightArm.xRot = -(0.8F * f3 * f);
        this.rightArm.zRot = 0.0F;

        this.body.xRot  += 35 * 0.017453292F;

        this.leftLeg.z +=5;
        this.rightLeg.z +=5;

        this.resetArmPoses();
    }

    private void resetArmPoses() {
        this.leftArm.yRot = 0.0F;
        this.leftArm.z = 1.0F;
        this.leftArm.x = 13.0F;
        this.leftArm.y = -13.0F;
        this.rightArm.yRot = 0.0F;
        this.rightArm.z = 1.0F;
        this.rightArm.x = -13.0F;
        this.rightArm.y = -13.0F;

        this.leftArm.xRot  -= 35 * 0.017453292F;
        this.rightArm.xRot  -= 35 * 0.017453292F;

    }

    private void animateTendrils(T pEntity, float pAgeInTicks, float pPartialTick) {
        float f = pEntity.getTendrilAnimation(pPartialTick) * (float)(Math.cos((double)pAgeInTicks * 2.25) * Math.PI * 0.1F);
        this.leftTendril.xRot = f;
        this.rightTendril.xRot = -f;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public List<ModelPart> getTendrilsLayerModelParts() {
        return this.tendrilsLayerModelParts;
    }

    public List<ModelPart> getHeartLayerModelParts() {
        return this.heartLayerModelParts;
    }

    public List<ModelPart> getBioluminescentLayerModelParts() {
        return this.bioluminescentLayerModelParts;
    }

    public List<ModelPart> getPulsatingSpotsLayerModelParts() {
        return this.pulsatingSpotsLayerModelParts;
    }
}
