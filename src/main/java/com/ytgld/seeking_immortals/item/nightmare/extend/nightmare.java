package com.ytgld.seeking_immortals.item.nightmare.extend;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.item.nightmare.tip.ILevel;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.common.CuriosRegistry;

import java.util.List;
import java.util.Map;

public class nightmare extends Item implements ICurioItem, INightmare , Terror, ILevel {
    public static final String terrorName ="SITheTerrorName";
    public nightmare() {
        super(new Properties().stacksTo(1).component(CuriosRegistry.CURIO_ATTRIBUTE_MODIFIERS, CurioAttributeModifiers.EMPTY)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, TooltipContext context, ItemStack stack) {
        tooltips.clear();
        return tooltips;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
    }


    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {

        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/fire.png");

    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF0000)));
        return co;
    }


    public int getOneLevel() {
        return 0;
    }

    @Override
    @Nullable
    public Map<Integer ,Component> describe(ItemStack stack) {
        return null;
    }

    @Override
    public int maxLevel(ItemStack stack) {
        return 0;
    }
    @Override
    public int nowLevel(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean showFire(ItemStack stack) {
        return true;
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
        return 0;
    }

    @Override
    public float maxLevel_float(ItemStack stack) {
        return 0;
    }

    @Override
    public float nowLevel_float(ItemStack stack) {
        return 0;
    }
}

