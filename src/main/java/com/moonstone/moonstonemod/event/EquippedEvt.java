package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import com.moonstone.moonstonemod.item.plague.TheNecora.god.GodAmbush;
import com.moonstone.moonstonemod.item.plague.TheNecora.god.GodPutrefactive;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioCanEquipEvent;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.text.DecimalFormat;
import java.util.Map;

public class EquippedEvt {
    public static final String isGod = "isGod";

    @SubscribeEvent
    public void ItemTooltipEvent(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.get(DataReg.tag) !=null) {
            if (stack.get(DataReg.tag).getBoolean(EquippedEvt.isGod)) {
                event.getToolTip().add(1, Component.translatable("moonstone.difficulty.name.true_god").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF4040)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
        }
    }
    @SubscribeEvent
    public  void CurioCanEquipEvent(CurioCanEquipEvent event) {
        Item item = event.getStack().getItem();
        if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(MoonStoneMod.MODID)) {
            if (Handler.hascurio(event.getEntity(), item)) {
                event.setEquipResult(TriState.FALSE);
            }
        }
    }
    @SubscribeEvent
    public void NotEquipped(CurioCanEquipEvent event) {
        Item item = event.getStack().getItem();
        LivingEntity living = event.getEntity();
        if (item instanceof GodDNA godDNA) {
            Item not = godDNA.getNotEquippedItem();
            if (Handler.hascurio(living,not)){
                event.setEquipResult(TriState.FALSE);
            }
        }else {
            if (CuriosApi.getCuriosInventory(living).isPresent()){
                for (int i = 0; i < CuriosApi.getCuriosInventory(living).get().getEquippedCurios().getSlots(); i++) {
                    ItemStack stack = CuriosApi.getCuriosInventory(living).get().getEquippedCurios().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        if (stack.getItem() instanceof GodDNA godDNA) {
                            if (godDNA.getNotEquippedItem() == item) {
                                event.setEquipResult(TriState.FALSE);
                            }
                        }
                    }
                }
            }
        }
    }


}
