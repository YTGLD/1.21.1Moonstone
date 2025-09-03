package com.ytgld.seeking_immortals.item.nightmare.fall;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.item.nightmare.extend.INightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.Godtip;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Map;

public class FallItem  extends Item implements ICurioItem, INightmare, Godtip, Terror {
    public FallItem() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/fire.png");

    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF00FF)));
        return co;
    }
    @Nullable
    @Override
    public Map<Integer, Component> describe(ItemStack stack) {
        return null;
    }

    @Override
    public int maxLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public int nowLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean showFire(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRot(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isTrail(ItemStack stack) {
        return false;
    }

    @Override
    public RenderType getTrailRenderType(ItemStack stack) {
        return MRender.light;
    }

    @Override
    public int color(ItemStack stack) {
        int red  = 255;
        int purple  = 255;
        int g = (int) (255 * Math.sin(NewEvent.time/33f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        return Light.ARGB.color(255,red,purple,g);
    }
}
