package com.moonstone.moonstonemod.entity.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.items.BookItems;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class MoonTamableAnimal extends TamableAnimal {
    protected MoonTamableAnimal(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        if (this.getOwner()!=null && this.getOwner() instanceof Player player){
            if (this.getTags().contains(BookItems.blood_stasisTAG)){
                player.heal(player.getMaxHealth()/10);
            }
        }
    }

    public int time;
    @Override
    public void tick() {
        super.tick();

        if (this.getTags().contains(BookItems.tumourTAG)){
            this.getAttributes().addTransientAttributeModifiers(tumourTAG());
        }
        if (this.getTags().contains(BookItems.bone_structureTAG)){
            this.getAttributes().addTransientAttributeModifiers(bone_structureTAG(this.getOwner()));
        }

        if (this.getTags().contains(BookItems.mummificationTAG)){
            this.getAttributes().addTransientAttributeModifiers(mummificationTAG());
        }
        if (this.getTags().contains(BookItems.mummificationTAG)) {
            if (this.tickCount % 2 == 1) {
                time--;
            }
        }

        if (this.getTags().contains(BookItems.organizational_regenerationTAG)) {
            if (this.tickCount % 5 == 1) {
                this.heal(1);
                time++;
            }
        }
    }

    private Multimap<Holder<Attribute>, AttributeModifier> tumourTAG(){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+"tumour"),
                -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+"tumour"),
                1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }

    private Multimap<Holder<Attribute>, AttributeModifier> bone_structureTAG(LivingEntity owner){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (owner != null) {

            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + "bone_structure"),
                    owner.getAttributeValue(Attributes.ARMOR) * 0.7f, AttributeModifier.Operation.ADD_VALUE));

            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + "bone_structure"),
                    owner.getAttributeValue(Attributes.MAX_HEALTH)*0.3F, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    private Multimap<Holder<Attribute>, AttributeModifier> mummificationTAG(){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + "mummification"),
                    -0.2, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }
}