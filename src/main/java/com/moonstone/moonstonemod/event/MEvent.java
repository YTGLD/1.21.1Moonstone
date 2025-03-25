package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.event.mevent.AttackBloodEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;

public class MEvent {
    @SubscribeEvent
    public  void AttackBloodEvent(AttackBloodEvent event){
        Player player = event.getPlayer();
        attack_blood attackBlood = event.getAttackBlood();

        event.setDamage((float) (event.getDamage()*player.getAttributeValue(AttReg.owner_blood_damage)));

        event.setMaxTime((float) (event.getMaxTime()*player.getAttributeValue(AttReg.owner_blood_time)));

        event.setSpeed((float) (event.getSpeed()*player.getAttributeValue(AttReg.owner_blood_speed)));
    }
    @SubscribeEvent
    public  void Finish(AttackBloodEvent.OwnerBloodEvent event){
        Player player = event.getPlayer();
        event.setAttackSpeed((float) (event.getAttackSpeed()*player.getAttributeValue(AttReg.owner_blood_attack_speed)));

    }

    @SubscribeEvent
    public void Attr(CurioAttributeModifierEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.is(Items.owner_blood_eye)){
            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+
                            Items.owner_blood_eye.get().getDescriptionId()),
                            -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        if (stack.is(Items.owner_blood_attack_eye)){
            event.getModifiers().put(AttReg.owner_blood_damage,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_attack_eye.get().getDescriptionId()),
                            0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_attack_eye.get().getDescriptionId()),
                            0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }

        if (stack.is(Items.owner_blood_attack_eye)){
            event.getModifiers().put(AttReg.owner_blood_damage,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_attack_eye.get().getDescriptionId()),
                            0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_attack_eye.get().getDescriptionId()),
                            0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        if (stack.is(Items.owner_blood_speed_eye)){
            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_speed_eye.get().getDescriptionId()),
                            -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            event.getModifiers().put(AttReg.owner_blood_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_speed_eye.get().getDescriptionId()),
                            4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        if (stack.is(Items.owner_blood_vex)){
            event.getModifiers().put(AttReg.owner_blood_damage,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_vex.get().getDescriptionId()),
                            -0.75f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
        if (stack.is(Items.owner_blood_boom_eye)){
            event.getModifiers().put(AttReg.owner_blood_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_boom_eye.get().getDescriptionId()),
                            -0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.owner_blood_boom_eye.get().getDescriptionId()),
                            1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
        if (stack.is(Items.the_blood_book)){
            event.getModifiers().put(AttReg.owner_blood_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.the_blood_book.get().getDescriptionId()),
                            6, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            event.getModifiers().put(AttReg.owner_blood_time,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.the_blood_book.get().getDescriptionId()),
                            -0.425, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            event.getModifiers().put(AttReg.owner_blood_damage,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.the_blood_book.get().getDescriptionId()),
                            2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            event.getModifiers().put(AttReg.owner_blood_attack_speed,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID +
                            Items.the_blood_book.get().getDescriptionId()),
                            -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
    }
}


















