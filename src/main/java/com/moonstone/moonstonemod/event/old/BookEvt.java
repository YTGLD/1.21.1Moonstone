package com.moonstone.moonstonemod.event.old;


import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.*;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class BookEvt {
    public static final String lvl = "LVLBookEvt";
    public static final String exp = "expBookEvt";
    public static final int maxLvl = 6000;
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        List<Component> pTooltipComponents =event.getToolTip();
        if (stack.get(DataReg.tag)!=null) {
            int expValue = stack.get(DataReg.tag).getInt(exp);
            int lvlValue = stack.get(DataReg.tag).getInt(lvl);
            if (expValue!=0&& lvlValue!=0) {

                String baseKey = "item.at_a_book.tool.string.lvl.";
                int l = 1;
                if (expValue > 300) {
                    for (int i = 0; i < expValue; i += 300) {
                        l++;
                    }
                }
                Style style = Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF));
                if (l>11){
                    style = Style.EMPTY.withColor(TextColor.fromRgb(0XFFEECFA1));
                    if (l>=21){
                        style = Style.EMPTY.withColor(TextColor.fromRgb(0XFFff5774));
                    }
                }

                // 添加到tooltip中
                pTooltipComponents.add(1,Component.literal(""));
                pTooltipComponents.add(1,Component.literal("")
                        .append(Component.translatable(baseKey + l)
                                .append(String.valueOf(lvlValue))
                                .append(Component.translatable("sword.moonstone.lvl"))
                                .withStyle(style)));

            }
        }
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingIncomingDamageEvent event){
        if ((event.getSource().getEntity() instanceof Player player)){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            int expValue = stack.get(DataReg.tag).getInt(exp);
                            int lvlValue = stack.get(DataReg.tag).getInt(lvl);

                            if (expValue!=0&& lvlValue!=0) {
                                float l = 1f;
                                if (expValue > 300) {
                                    for (int x = 0; x < expValue; x += 300) {
                                        l++;
                                    }
                                }
                                l/=100f;//l是1%到10%
                                l/=10f;//l是0.1%~1%
                                if (l>1){
                                    l=1;
                                }
                                event.setAmount(event.getAmount()*(l+1));


                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event){
        if ((event.getEntity() instanceof Player player)){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            int expValue = stack.get(DataReg.tag).getInt(exp);
                            int lvlValue = stack.get(DataReg.tag).getInt(lvl);

                            if (expValue!=0&& lvlValue!=0) {
                                float l = 1f;
                                if (expValue > 300) {
                                    for (int x = 0; x < expValue; x += 300) {
                                        l++;
                                    }
                                }
                                l/=100f;//l是1%到10%
                                l/=5f;//l是0.5%到5%
                                if (l>1){
                                    l=1;
                                }
                                 event.setAmount(event.getAmount()*(l+1));
                            }
                        }
                    }
                }
            });
        }
    }
    /**
     *
     * @param stack 是饰品
     * @param expLv 值在 0 ~ maxLvl 之间
     * @param lvls 值在 0 ~ maxLvl 之间
     */
    public static void addLvl(ItemStack stack,int expLv,int lvls){
        if (Config.SERVER.itemQuality.get()) {
            if (stack.getItem() instanceof UnCommonItem
                    || stack.getItem() instanceof nightmare
                    || stack.getItem() instanceof Die
                    || stack.getItem() instanceof Doom
                    || stack.getItem() instanceof TheNecoraIC
                    || stack.getItem() instanceof CommonItem) {

                if (stack.get(DataReg.tag) == null) {
                    stack.set(DataReg.tag, new CompoundTag());
                }
                stack.get(DataReg.tag).putInt(exp, expLv);
                if (stack.get(DataReg.tag) != null) {
                    String ss = lvl;
                    int l = lvls;
                    if (l > 300) {
                        for (int i = 0; i < maxLvl; i += 300) {
                            int to = i / 10;
                            if (to != 0) {
                                l = (l / to);
                                break;
                            }
                        }

                        if (l > 10) {
                            l -= (l / 10) * 10;
                        }
                        stack.get(DataReg.tag).putInt(ss, l);
                    } else {
                        stack.get(DataReg.tag).putInt(ss, l / 30);
                    }
                }
            }
        }
    }
}
