package com.moonstone.moonstonemod.client.renderer;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.resources.ResourceLocation;

public enum NecoraWidgetTypes {
    OBTAINED(
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/box_obtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/task_frame_obtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/challenge_frame_obtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/goal_frame_obtained_necora")),
    UNOBTAINED(
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/box_unobtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/task_frame_unobtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/challenge_frame_unobtained_necora"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/goal_frame_unobtained_necora"));


    private final ResourceLocation boxSprite;
    private final ResourceLocation taskFrameSprite;
    private final ResourceLocation challengeFrameSprite;
    private final ResourceLocation goalFrameSprite;

    NecoraWidgetTypes(ResourceLocation boxSprite, ResourceLocation taskFrameSprite, ResourceLocation challengeFrameSprite, ResourceLocation goalFrameSprite) {
        this.boxSprite = boxSprite;
        this.taskFrameSprite = taskFrameSprite;
        this.challengeFrameSprite = challengeFrameSprite;
        this.goalFrameSprite = goalFrameSprite;
    }

    public ResourceLocation boxSprite() {
        return this.boxSprite;
    }

    public ResourceLocation frameSprite(AdvancementType type) {
        return switch (type) {
            case TASK -> this.taskFrameSprite;
            case CHALLENGE -> this.challengeFrameSprite;
            case GOAL -> this.goalFrameSprite;
        };
    }
}
