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
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BookSkill;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IDoom;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class nine_sword_book extends BookSkill implements IDoom {
    public static final String size = "swordSize";
    public static final String lvl = "nineSwordBookSwordLvl";
    public static final String small = "nineSwordBookSwordLvlSmall";
    public static final int maxLvl = 10;
    public static final int addLvl = 100;

    public static void att(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nine_sword_book.get())&& book.isHasMaxInt(player,book.nineSword,300)){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is(Items.nine_sword_book.get())){
                                if (stack.get(DataReg.tag)!=null){
                                    if (stack.get(DataReg.tag).getInt(small)<=(addLvl*maxLvl)){
                                        stack.get(DataReg.tag).putInt(small,stack.get(DataReg.tag).getInt(small)+1);
                                    }
                                    if (stack.get(DataReg.tag).getInt(small)%100==0){
                                        stack.get(DataReg.tag).putInt(lvl,stack.get(DataReg.tag).getInt(lvl)+1);
                                    }


                                    int s = 5* stack.get(DataReg.tag).getInt(size);
                                    if (Mth.nextInt(RandomSource.create(),0,100)<=s){
                                        for (int j = 0; j < stack.get(DataReg.tag).getInt(size); j++) {
                                            float lvl = Mth.nextFloat(RandomSource.create(), -0.3f, 0.3f);

                                            as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                                            as_sword.setPos(player.position().x, player.position().y + 1, player.position().z);
                                            Vec3 forward = player.getLookAngle();
                                            double speed = 0.25f;

                                            as_sword.setDeltaMovement(-forward.add(lvl, lvl, lvl).x * speed, -forward.add(lvl, lvl, lvl).y * speed, -forward.add(lvl, lvl, lvl).z * speed);
                                            as_sword.setOwner(player);

                                            player.level().addFreshEntity(as_sword);
                                            as_sword.setTarget(event.getEntity());

                                            stack.get(DataReg.tag).putInt(size,stack.get(DataReg.tag).getInt(size)-1);
                                        }
                                    }
                                }
                                List<Float> floats = new ArrayList<>();
                                for (int j = 0; j < 9; j++) {
                                    ItemStack sword = player.getInventory().items.get(j);
                                    if (sword.getItem() instanceof SwordItem swordItem) {
                                        floats.add(swordItem.getTier().getAttackDamageBonus()+4);
                                    }
                                }
                                float damage = 0;
                                for (float all: floats){
                                    damage+=all;
                                }
                                if (!player.getCooldowns().isOnCooldown(Items.nine_sword_book.get())) {
                                    event.setAmount(damage+event.getAmount());
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.MACE_SMASH_GROUND_HEAVY, SoundSource.NEUTRAL, 2, 2);
                                    player.getCooldowns().addCooldown(Items.nine_sword_book.get(),100);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (stack.get(DataReg.tag)!=null&& book.isHasMaxInt(player,book.nineSword,300)) {
                if (stack.get(DataReg.tag).getInt(lvl)>10){
                    stack.get(DataReg.tag).putInt(lvl,10);
                }
                player.getAttributes().addTransientAttributeModifiers(Head(player,stack));
                List<Integer> integers = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    ItemStack sword = player.getInventory().items.get(i);
                    if (sword.getItem() instanceof SwordItem|| BuiltInRegistries.ITEM.getKey(sword.getItem()).getPath().contains("sword")) {
                        integers.add(1);
                    }
                }
                int swSize = integers.size();
                if (player.tickCount % 40 == 0) {
                    if (stack.get(DataReg.tag).getInt(size)<swSize){
                        stack.get(DataReg.tag).putInt(size,stack.get(DataReg.tag).getInt(size)+1);
                    }
                }

            }else {
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putInt(lvl,1);
                stack.set(DataReg.tag,compoundTag);
            }
        }
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(Head(player,stack));
        }
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(Player player,ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {
            float level =1 +  (stack.get(DataReg.tag).getInt(lvl)/10f);
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                ItemStack sword = player.getInventory().items.get(i);
                if (sword.getItem() instanceof SwordItem|| BuiltInRegistries.ITEM.getKey(sword.getItem()).getPath().contains("sword")) {
                    integers.add(1);
                }
            }
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    (integers.size() / 10f)*level,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    (integers.size() / 20F)*level,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    (integers.size() / 35F)*level,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.heal, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    (integers.size() / 100F)*level,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.alL_attack, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    -0.25,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    -0.5,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.get(DataReg.tag)!=null) {
            pTooltipComponents.add(Component.literal(""));
            if (pStack.get(DataReg.tag).getInt(small)<=300){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.1");
            }else if (pStack.get(DataReg.tag).getInt(small)>300&&pStack.get(DataReg.tag).getInt(small)<=600){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.2");
            }else if (pStack.get(DataReg.tag).getInt(small)>900){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.3");
            }

            pTooltipComponents.add(Component.translatable("item.nine_sword_book_lvl.tool.string.2").append(" " + pStack.get(DataReg.tag).getInt(small)).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
        }
    }
    private void addNme(ItemStack pStack, List<Component> pTooltipComponents, String translatable) {
        int l = pStack.get(DataReg.tag).getInt(small);
        String ss = "small";
        int displayValue = pStack.get(DataReg.tag).getInt(ss); // 用于存放要显示的值

        if (l > 0 && l < 300) {
            pStack.get(DataReg.tag).putInt(ss,l / 30);// 计算0到299之间的值
        } else if (l >= 300 && l < 600) {
            pStack.get(DataReg.tag).putInt(ss,(l - 300) / 30); // 计算300到599之间的值，显示从1开始
        } else if (l >= 600 && l <= 10000) {
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
