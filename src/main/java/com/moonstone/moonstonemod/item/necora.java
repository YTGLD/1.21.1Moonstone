package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.catalyzer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class necora extends TheNecoraIC {
    public necora() {
    }

    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(Items.bloodvirus.get()));
                    Other.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(Player player, ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        double acc = 0.8;
        if (Handler.hascurio(player, Items.autolytic.get())){
            acc = 0;
        }
        multimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                -acc,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier( ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                3,
                AttributeModifier.Operation.ADD_VALUE));


        return multimap;
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(Head(player, stack));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (Handler.hascurio(player,this)) {
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                float range = 12;
                List<LivingEntity> entities =
                        player.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));

                for (LivingEntity living : entities){
                    if (living instanceof Villager villager){
                        villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, Player.class, 6.0F, 1.0, 1.2));
                    }
                    if (living instanceof IronGolem golem){
                        if (golem.getTarget()==null) {
                            golem.setTarget(player);
                        }
                    }
                }
                player.getAttributes().addTransientAttributeModifiers(Head(player, stack));

                if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                        (player.level().isDay() &&
                                player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ())))) {
                    player.setRemainingFireTicks(40);
                }
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>  multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "ncrdna", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 2, AttributeModifier.Operation.ADD_VALUE);

        return multimap;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.4").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.5").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.7").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.8").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.9").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.10").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.11").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
        }else {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.12").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.13").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.14").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.necora.tool.string.15").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
        }
    }
}
