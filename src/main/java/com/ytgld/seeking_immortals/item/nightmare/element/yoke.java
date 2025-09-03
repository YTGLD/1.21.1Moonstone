package com.ytgld.seeking_immortals.item.nightmare.element;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.an_element.elements.Destiny;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.nightmare_base_black_eye;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class yoke  extends nightmare implements AllElement {
    public static final String destinySting = "destinySting";
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        @Nullable CompoundTag compoundTag = me.get(DataReg.tag);
        if (compoundTag !=null) {
            if (other.getItem() instanceof nightmare_base_black_eye){
                compoundTag.putInt(destinySting,compoundTag.getInt(destinySting)+10);
                other.shrink(1);
                return true;
            }
        }else {
            me.set(DataReg.tag,new CompoundTag());
        }
        return false;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null) {
            if (slotContext.entity() instanceof Player player) {
                if (!player.level().isClientSide&&player.tickCount%10 == 1) {
                    if (Handler.hascurio(player, this)) {
                        int element = Handler.getElement(stack, this, destiny);
                        if (element >= 10) {
                            int lv = element / 10;
                            if (lv > 9) {
                                lv = 9;
                            }
                            player.addEffect(new MobEffectInstance(Effects.the_yoke_of_war, 200, lv, false, false));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("item.yoke.tool.string").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.yoke.tool.string.1").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.translatable("item.yoke.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        }else {
            tooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.nightmare_base.element.string").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_black_eye").withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public Map<Element, ResourceLocation> name() {
        Map<Element, ResourceLocation> map = new HashMap<>();
        map.put(destiny, Destiny.destiny);
        return map;
    }

    @Override
    public  Map<Element, String> tooltip() {
        Map<Element, String> map = new HashMap<>();
        map.put(this.destiny,"命运之眼");
        return map;
    }

    @Override
    public Map<Element, Integer> element(ItemStack stack) {
        Map<Element, Integer> map = new HashMap<>();
        @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
        int s = 0;
        if (compoundTag !=null){
            s = compoundTag.getInt(destinySting);
        }
        map.put(this.destiny,s);
        return map;
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new NightmareTooltip(this,stack));
    }
}
