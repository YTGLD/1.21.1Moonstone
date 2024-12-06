package com.moonstone.moonstonemod.item.maxitem.book;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Doom;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class nine_sword_book extends Doom {
    public static String size = "swordSize";

    public static void att(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nine_sword_book.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is(Items.nine_sword_book.get())){
                                if (stack.get(DataReg.tag)!=null){
                                    int s = stack.get(DataReg.tag).getInt(size);
                                    if (Mth.nextInt(RandomSource.create(),0,100)<=s){
                                        for (int j = 0; j < s; j++) {
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
                                        floats.add(swordItem.getTier().getAttackDamageBonus());
                                    }
                                }
                                float damage = 0;
                                for (float all: floats){
                                    damage+=all;
                                }
                                if (!player.getCooldowns().isOnCooldown(Items.nine_sword_book.get())) {
                                    event.setAmount(damage);
                                    player.getCooldowns().addCooldown(Items.nine_sword_book.get(),200);
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
            if (stack.get(DataReg.tag)!=null) {
                player.getAttributes().addTransientAttributeModifiers(Head(player));
                List<Integer> integers = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    ItemStack sword = player.getInventory().items.get(i);
                    if (sword.getItem() instanceof SwordItem) {
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
                stack.set(DataReg.tag,new CompoundTag());
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(Head(player));
        }
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(Player player){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        List<Integer> integers =new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ItemStack sword = player.getInventory().items.get(i);
            if (sword.getItem() instanceof SwordItem){
                integers.add(1);
            }
        }
        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                integers.size()/10f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                integers.size()/20F,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                integers.size()/35F,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(AttReg.heal, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                integers.size()/100F,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(AttReg.alL_attack, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }

}
