package com.moonstone.moonstonemod.item.maxitem.book;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class the_blood_book  extends UnCommonItem implements Blood {

    public static final int lvl1= 300;
    public static final int lvl2= 600;
    public static final int lvl3= 900;

    public static final String exp = "smallSkillNameAndNowLvl1the_blood_book";

    public static final String heals = " Heal";
    public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.the_blood_book.get())&& book.isHasMaxInt(player,book.MoDiBlood,100)){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is(Items.the_blood_book.get())){
                                if (stack.get(DataReg.tag)!=null) {
                                    if (!player.level().isClientSide) {
                                        stack.get(DataReg.tag).putInt(exp, stack.get(DataReg.tag).getInt(exp) + 1);
                                        stack.get(DataReg.tag).putInt(heals, stack.get(DataReg.tag).getInt(heals) + 1);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void att(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.the_blood_book.get())&& book.isHasMaxInt(player,book.MoDiBlood,100)){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is(Items.the_blood_book.get())){
                                if (stack.get(DataReg.tag)!=null) {
                                    player.hurt(player.damageSources().dryOut(),player.getMaxHealth()*(0.01f*getLvl(stack)));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
     public static float getLvl(ItemStack stack){
        return 1 +  stack.get(DataReg.tag).getInt(exp) / 300f;
     }
    private Multimap<Holder<Attribute>, AttributeModifier> hhh(Player player, ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {
            if (!player.level().isClientSide) {
                float level = getLvl(stack);
                float doH = stack.get(DataReg.tag).getInt(heals) / 100f;//0.25%
                if (player.getHealth() >= player.getMaxHealth()) {
                    doH = 0;
                }

                multimap.put(AttReg.heal, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                        ((doH) * level),
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


                multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                        getLvl(stack),
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            }
        }
        return multimap;
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (stack.get(DataReg.tag) != null&&slotContext.entity() instanceof  Player player) {
            player.getAttributes().removeAttributeModifiers(hhh(player, stack));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) != null) {
            if (slotContext.entity() instanceof  Player player) {
                if (!player.level().isClientSide) {
                    player.getAttributes().addTransientAttributeModifiers(hhh(player, stack));
                    int lvl = stack.get(DataReg.tag).getInt(heals);
                    float l = 50f;
                    float level = getLvl(stack);//1~4
                    level *= 3f;
                    l -= level;

                    l /= 100f;
                    if (lvl > player.getMaxHealth() * l) {
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 1F, 1F);

                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        float range = 8;
                        List<LivingEntity> entities =
                                player.level().getEntitiesOfClass(LivingEntity.class,
                                        new AABB(playerPos.x - range,
                                                playerPos.y - range,
                                                playerPos.z - range,
                                                playerPos.x + range,
                                                playerPos.y + range,
                                                playerPos.z + range));

                        for (LivingEntity living : entities) {
                            if (!living.is(player)) {
                                living.hurt(living.damageSources().dryOut(), lvl * (level / 6));
                            }
                            stack.get(DataReg.tag).putInt(heals, 0);
                        }
                    }
                }
            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.get(DataReg.tag)!=null) {
           if (pStack.get(DataReg.tag).getInt(exp)<=lvl1){
               addNme(pStack,pTooltipComponents,"item.the_blood_book_lvl.tool.string.1");
           }else if (pStack.get(DataReg.tag).getInt(exp)>lvl1&&pStack.get(DataReg.tag).getInt(exp)<=lvl2){
               addNme(pStack,pTooltipComponents,"item.the_blood_book_lvl.tool.string.2");
           }else if (pStack.get(DataReg.tag).getInt(exp)>lvl2){
               addNme(pStack,pTooltipComponents,"item.the_blood_book_lvl.tool.string.3");
           }
            pTooltipComponents.add(Component.translatable("item.nine_sword_book_lvl.tool.string.2")
                    .append(String.valueOf(pStack.get(DataReg.tag).getInt(exp)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
        }
    }
    private void addNme(ItemStack pStack, List<Component> pTooltipComponents, String translatable) {
        int l = pStack.get(DataReg.tag).getInt(exp);
        String ss = "modsNS";
        int displayValue = pStack.get(DataReg.tag).getInt(ss); // 用于存放要显示的值

        if (l > 0 && l < 300) {
            pStack.get(DataReg.tag).putInt(ss,l / 30);// 计算0到299之间的值
        } else if (l >= 300 && l < 600) {
            pStack.get(DataReg.tag).putInt(ss,(l - 300) / 30); // 计算300到599之间的值，显示从1开始
        } else if (l >= 600 && l < 900) {
            pStack.get(DataReg.tag).putInt(ss,(l - 600) / 30); // 计算600到899之间的值，显示从1开始
        }

        // 限制 displayValue 在 1 到 10 之间
        if (pStack.get(DataReg.tag).getInt(ss) < 1) {
            pStack.get(DataReg.tag).putInt(ss,1);// 计算0到299之间的值
        } else if (pStack.get(DataReg.tag).getInt(ss) > 10) {
            pStack.get(DataReg.tag).putInt(ss,10);
        }

        // 添加到tooltip中
        pTooltipComponents.add(Component.translatable(translatable)
                .append(String.valueOf(displayValue))
                .append(Component.translatable("sword.moonstone.lvl"))
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
    }


}
