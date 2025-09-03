package com.moonstone.moonstonemod.item.decorated;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.entity.zombie.cell_giant;
import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.init.items.BookItems;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class deceased_contract extends TheNecoraIC {
    private final String time = "CurseTime";
    private final int maxTime = 3600;
    public static void attack(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.deceased_contract.get())) {
                if (event.getAmount()<Integer.MAX_VALUE) {
                    event.setAmount(event.getAmount() * 1.25f);
                }
            }
        }
    }
    public static void Did(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.deceased_contract.get())) {

                if (Mth.nextInt(RandomSource.create(), 1, 100) <= 30) {
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

                    addTag(z,player);
                    z.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,60, 6,false,false));
                    player.level().addFreshEntity(z);
                }
                if (Mth.nextInt(RandomSource.create(), 1, 100) <= 15) {
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

                    addTag(g,player);
                    g.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,60, 6,false,false));
                    player.level().addFreshEntity(g);
                }

                if (Mth.nextInt(RandomSource.create(), 1, 100) <=  15) {
                    blood blood = new blood(EntityTs.blood.get(), player.level());
                    blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(), 0.1, 0.11), Mth.nextDouble(RandomSource.create(), 0.095, 0.1), Mth.nextDouble(RandomSource.create(), 0.099, 0.1));
                    blood.setOwner(player);
                    blood.setPos(event.getEntity().getX(), event.getEntity().getY() + 1.5f, event.getEntity().getZ());

                    player.level().addFreshEntity(blood);
                }

            }
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());



        if (stack.get(DataReg.tag)==null){
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt(this.time,maxTime);
            stack.set(DataReg.tag,compoundTag);
        }

        if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount%20==0) {
            CompoundTag compoundTag = stack.get(DataReg.tag);
            if (compoundTag != null) {
                int time = compoundTag.getInt(this.time);
                if (time > 0) {
                    compoundTag.putInt(this.time, compoundTag.getInt(this.time) - 1);
                }
            }
        }
        String tag = "curioTickDeceasedContract";
        if (stack.get(DataReg.tag)!=null
                &&!stack.get(DataReg.tag).getBoolean(tag)
        ) {
            Random random = new Random();
            ArrayList<Item> items= new ArrayList<>(List.of(
                    Items.muscle_conversion.get(),
                    Items.phosphate_bond.get(),
                    Items.chemical_compound.get(),
                    Items.skin_glucose_fermentation.get(),
                    Items.white_blood_cells_are_abruptly_reduced.get()
            ));
            if (!items.isEmpty()) {
                int index = random.nextInt(items.size());
                Item selectedItem = items.remove(index);
                addLoot(slotContext.entity(), selectedItem);
            }

            stack.get(DataReg.tag).putBoolean(tag,true);
        }
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            if (compoundTag.getInt(this.time) <= 0) {
                return true;
            }
        }
        return com.ytgld.seeking_immortals.Config.SERVER.canUnequipMoonstoneItem.get();
    }

    private void addLoot(Entity entity ,
                         Item itemList){
        if (entity instanceof Player player) {
            player.addItem(itemList.getDefaultInstance());
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(AttReg.alL_attack, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.cit, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), -0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    private static Multimap<Holder<Attribute>, AttributeModifier> zombieAtt(Player owner) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();

        if (owner.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.ARMOR) != null) {
            modifierMultimap.put(Attributes.ARMOR,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ARMOR).getValue() / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.MAX_HEALTH) != null) {
            modifierMultimap.put(Attributes.MAX_HEALTH,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.MAX_HEALTH).getValue() / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        if (owner.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            modifierMultimap.put(Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.deceased_contract.get().getDescriptionId()),
                            owner.getAttribute(Attributes.MOVEMENT_SPEED).getValue() / 2, AttributeModifier.Operation.ADD_VALUE));
        }

        modifierMultimap.put(Attributes.MAX_HEALTH,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage_a" + Items.deceased_contract.get().getDescriptionId()),
                        -0.8f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>  multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "necora", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE);

        return multimap;
    }
    private static void addTag(MoonTamableAnimal tamableAnimal ,Player owner){
        if (Handler.hascurio(owner, BookItems.blood_stasis.get())){
            tamableAnimal.addTag(BookItems.blood_stasisTAG);
        }
        if (Handler.hascurio(owner, BookItems.mummification.get())){
            tamableAnimal.addTag(BookItems.mummificationTAG);
        }
        if (Handler.hascurio(owner, BookItems.tumour.get())){
            tamableAnimal.addTag(BookItems.tumourTAG);
        }
        if (Handler.hascurio(owner, BookItems.organizational_regeneration.get())){
            tamableAnimal.addTag(BookItems.organizational_regenerationTAG);
        }
        if (Handler.hascurio(owner, BookItems.bone_structure.get())){
            tamableAnimal.addTag(BookItems.bone_structureTAG);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.0").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.1").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.2").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.3").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.4").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.5").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        }else {
            tooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.RED));
            CompoundTag compoundTag = stack.get(DataReg.tag);
            if (compoundTag!=null&&compoundTag.getInt(this.time)>0) {
                tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.7")
                        .append(String.valueOf(compoundTag.getInt(this.time)))
                        .append(Component.translatable("item.deceased_contract.tool.string.8"))
                        .withStyle(ChatFormatting.DARK_RED));
            }else {
                tooltipComponents.add(Component.translatable("item.deceased_contract.tool.string.9").withStyle(ChatFormatting.GOLD));
            }
        }
    }
}
