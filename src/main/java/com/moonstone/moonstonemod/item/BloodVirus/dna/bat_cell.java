package com.moonstone.moonstonemod.item.BloodVirus.dna;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class bat_cell extends BloodViru {
    public static final String cell_doctor="CellDoctor";
    public static final String cell_desecrate="CellDesecrate";
    public static final String cell_immortal="CellImmortal";
    public static final String cell_rage="CellRage";
    public static final String cell_blood_attack="CellBloodAttack";
    public static final String cell_fear="CellFear";

    public static void Bat(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player living) {
            if (Handler.hascurio(living,Items.bloodvirus.get())) {
                if (Handler.hascurio(living, Items.bat_cell.get())) {
                    if (Handler.hascurio(living, Items.cell_desecrate.get())) {
                        if (event.getEntity() instanceof Mob mob) {
                            if (!mob.isInvertedHealAndHarm()) {
                                event.setAmount(event.getAmount() * 1.4f);
                            }
                        }
                    }
                    if (Handler.hascurio(living, Items.cell_doctor.get())) {
                        living.heal(event.getAmount() / 5);
                    }
                }
            }
        }
        if (event.getEntity() instanceof Player living) {
            if (Handler.hascurio(living,Items.bloodvirus.get())) {
                if (!living.getCooldowns().isOnCooldown(Items.bat_cell.get())) {
                    if (Handler.hascurio(living, Items.bat_cell.get())) {
                        int j = Mth.nextInt(RandomSource.create(), 1, 7);
                        if (Handler.hascurio(living, Items.cell_harvest.get())) {
                            j = Mth.nextInt(RandomSource.create(), 1, 4);
                        }
                        if (j == 1) {
                            blood_bat blood_bat = new blood_bat(EntityTs.blood_bat.get(), living.level());
                            blood_bat.setOwnerUUID(living.getUUID());
                            blood_bat.teleportTo(living.getX(), living.getY(), living.getZ());
                            if (!Handler.hascurio(living, Items.cell_scientist.get())) {
                                blood_bat.getAttributes().addTransientAttributeModifiers(modifierMultimap(blood_bat, living.getHealth() * 0.15f));
                            } else {
                                blood_bat.getAttributes().addTransientAttributeModifiers(modifierMultimapA(blood_bat, living.getHealth() * 0.25f));
                                living.hurt(living.damageSources().dryOut(), living.getHealth() * 0.3f);
                                living.invulnerableTime = 0;
                            }
                            if (Handler.hascurio(living, Items.cell_doctor.get())) {
                                blood_bat.addTag(cell_doctor);
                            }
                            if (Handler.hascurio(living, Items.cell_desecrate.get())) {
                                blood_bat.addTag(cell_desecrate);
                            }
                            if (Handler.hascurio(living, Items.cell_immortal.get())) {
                                blood_bat.addTag(cell_immortal);
                            }
                            if (Handler.hascurio(living, Items.cell_rage.get())) {
                                blood_bat.addTag(cell_rage);
                            }
                            if (Handler.hascurio(living, Items.cell_blood_attack.get())) {
                                blood_bat.addTag(cell_blood_attack);

                            }
                            if (Handler.hascurio(living, Items.cell_fear.get())) {
                                blood_bat.addTag(cell_fear);
                            }

                            living.level().addFreshEntity(blood_bat);
                            living.getCooldowns().addCooldown(Items.bat_cell.get(), 20);
                            if (Handler.hascurio(living, Items.cell_not_do.get())) {
                                living.hurt(living.damageSources().dryOut(), living.getHealth() * 0.15f);
                            }
                        }
                    }
                }
            }
        }
    }

    public static Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap (LivingEntity bat, float owner){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap =HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier((ResourceLocation.withDefaultNamespace("base_attack_damage"+Items.bat_cell.get().getDescriptionId())),owner, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }
    public static Multimap<Holder<Attribute>, AttributeModifier> modifierMultimapA(LivingEntity bat, float owner){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap =HashMultimap.create();
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier((ResourceLocation.withDefaultNamespace("base_attack_damage"+Items.bat_cell.get().getDescriptionId())),owner, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+Items.bat_cell.get().getDescriptionId()),owner, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "dna", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 2, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext,  List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, pContext, tooltip, flags);
        tooltip.add(Component.translatable("item.bat_cell.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.bat_cell.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.bat_cell.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
