package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.zombie.cell_giant;
import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

public class deceased_contract extends TheNecoraIC {
    public static void attack(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.deceased_contract.get())){
                event.setAmount(event.getAmount()*1.1f);
            }
        }
    }
    public static void Did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.deceased_contract.get())){
                if (!player.getCooldowns().isOnCooldown(Items.deceased_contract.get())) {
                    if (Mth.nextInt(RandomSource.create(), 1, 100) <= 40) {
                        cell_zombie z = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                        z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                        z.setOwnerUUID(player.getUUID());
                        z.getAttributes().addTransientAttributeModifiers(zombieAtt(player));
                        z.heal(1000);

                        for (MobEffectInstance effect : player.getActiveEffects()) {
                            if (effect != null
                                    && effect.getEffect().value().isBeneficial()) {
                                z.addEffect(effect);
                            }
                        }


                        player.level().addFreshEntity(z);
                    }
                    if (Mth.nextInt(RandomSource.create(), 1, 100) <= 20) {
                        cell_giant g = new cell_giant(EntityTs.cell_giant.get(), player.level());
                        g.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                        g.setOwnerUUID(player.getUUID());
                        g.getAttributes().addTransientAttributeModifiers(zombieAtt(player));
                        g.heal(1000);
                        g.setPose(Pose.EMERGING);
                        for (MobEffectInstance effect : player.getActiveEffects()) {
                            if (effect != null
                                    && effect.getEffect().value().isBeneficial()) {
                                g.addEffect(effect);
                            }
                        }
                        player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                        player.level().addFreshEntity(g);
                    }

                    if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                        blood blood = new blood(EntityTs.blood.get(), player.level());
                        blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(), 0.1, 0.11), Mth.nextDouble(RandomSource.create(), 0.095, 0.1), Mth.nextDouble(RandomSource.create(), 0.099, 0.1));
                        blood.setOwner(player);
                        blood.setPos(event.getEntity().getX(), event.getEntity().getY() + 1.5f, event.getEntity().getZ());

                        player.level().addFreshEntity(blood);
                    }
                    player.getCooldowns().addCooldown(Items.deceased_contract.get(),1);
                }
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(AttReg.alL_attack, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),  -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.cit, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),  -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),  -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    private static  Multimap<Holder<Attribute>, AttributeModifier> zombieAtt(Player owner) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();

        if (owner.getAttribute(Attributes.ATTACK_DAMAGE)!=null) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue()/2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.ARMOR)!=null) {
            modifierMultimap.put(Attributes.ARMOR,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ARMOR).getValue()/2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.MAX_HEALTH)!=null) {
            modifierMultimap.put(Attributes.MAX_HEALTH,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.MAX_HEALTH).getValue()/2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.ATTACK_DAMAGE)!=null) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue()/2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.MOVEMENT_SPEED)!=null) {
            modifierMultimap.put(Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.MOVEMENT_SPEED).getValue()/2, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
}