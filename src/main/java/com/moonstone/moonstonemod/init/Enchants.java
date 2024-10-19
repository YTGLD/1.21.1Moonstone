package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Enchants {
    public static final String Terror = "terror";
    public static final String threat = "threat";

    public static final String malice  = "malice";
    public static int getEnchantmentLevel(String enchantmentName, ItemStack stack, LivingEntity entity) {
        Registry<Enchantment> enchantmentRegistry = entity.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Holder.Reference<Enchantment> reference =
                enchantmentRegistry.getHolderOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, enchantmentName)));


        return stack.getEnchantmentLevel(reference);
    }
    public static boolean isMoonEnchant(Holder<Enchantment> enchantment, String enchantmentName) {
       ResourceKey<Enchantment> a =  ResourceKey.create(Registries.ENCHANTMENT,ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, enchantmentName));
        return enchantment.is(a);
    }


    public static Holder.Reference<Enchantment> getEnchantHolder(Player player, String enchantmentName) {
        Registry<Enchantment> enchantmentRegistry = player.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        return enchantmentRegistry.getHolderOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, enchantmentName)));
    }









    public static void maliceAttack(LivingIncomingDamageEvent event){
        List<Integer> integers = new ArrayList<>();
        if (event.getSource().getEntity() instanceof LivingEntity living){
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (Enchants.getEnchantmentLevel(Enchants.malice, stack, living) > 0) {
                                int lvl = Enchants.getEnchantmentLevel(Enchants.malice, stack, living);
                                integers.add(lvl);
                                float ss = 0;
                                for (int is : integers) {
                                    ss += is;
                                }
                                ss /= 300;
                                if (ss > 0.8f) {
                                    ss = 0.8f;
                                }
                                ss = -ss;
                                event.setAmount(event.getAmount() * (1 + ss));

                            }

                        }
                    }
                }
            });
        }
    }
    public static void LivingHurtEvent(LivingIncomingDamageEvent event){
        List<Integer> integers = new ArrayList<>();
        if (event.getEntity() instanceof LivingEntity living){
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (Enchants.getEnchantmentLevel(Enchants.Terror, stack, living) > 0) {
                                int lvl = Enchants.getEnchantmentLevel(Enchants.Terror, stack, living);
                                integers.add(lvl);
                                float ss = 0;
                                for (int is : integers) {
                                    ss += is;
                                }
                                ss /= 99;
                                event.setAmount(event.getAmount() * (1 + ss));
                            }

                        }
                    }
                }
            });

        }
    }
    public static void threatHurtEvent(LivingIncomingDamageEvent event){
        List<Integer> integers = new ArrayList<>();
        if (event.getEntity() instanceof LivingEntity living){
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (Enchants.getEnchantmentLevel(Enchants.threat, stack, living) > 0) {
                                int lvl = Enchants.getEnchantmentLevel(Enchants.threat, stack, living);
                                integers.add(lvl);

                                int ss = 0;
                                for (int is : integers) {
                                    ss += is;
                                }

                                if (ss > 100) {
                                    ss=100;
                                }

                                if (Mth.nextInt(RandomSource.create(), ss,100)==100){
                                    event.setAmount(event.getAmount()*2);
                                }
                            }

                        }
                    }
                }
            });

        }
    }
}
