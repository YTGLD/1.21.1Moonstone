package com.moonstone.moonstonemod.client.renderer;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.resources.ResourceLocation;

public enum WidgetTypes {
    OBTAINED(
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/box_obtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/task_frame_obtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/challenge_frame_obtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/goal_frame_obtained")),
    UNOBTAINED(
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/box_unobtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/task_frame_unobtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/challenge_frame_unobtained"),
            ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"advancements/goal_frame_unobtained"));


    private final ResourceLocation boxSprite;
    private final ResourceLocation taskFrameSprite;
    private final ResourceLocation challengeFrameSprite;
    private final ResourceLocation goalFrameSprite;

    WidgetTypes(ResourceLocation boxSprite, ResourceLocation taskFrameSprite, ResourceLocation challengeFrameSprite, ResourceLocation goalFrameSprite) {
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
