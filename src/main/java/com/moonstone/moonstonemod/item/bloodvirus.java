package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BloodViru;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class bloodvirus extends BloodViru {
    public bloodvirus (){
    }
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof batskill) {
                    CompoundTag tag = me.get(DataReg.tag);
                    if (tag != null){
                        if (!tag.getBoolean(batskill.batskill)) {
                            String size = "SizeBlood";
                            if (tag.getInt(size) < 2) {
                                tag.putBoolean(batskill.batskill, true);
                                Other.shrink(1);
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player,this)) {
                CompoundTag tag = stack.get(DataReg.tag);
                if (tag != null) {
                    if (tag.getBoolean(batskill.batskill)) {
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        int range = 12;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                        for (LivingEntity living : entities) {
                            if (!living.is(player)) {
                                if (living instanceof Bat bat) {
                                    if (bat.tickCount % 20 == 0) {
                                        bat.setHealth(bat.getHealth() - (bat.getMaxHealth() / 10));
                                    }
                                }
                            }
                        }
                    }

                    if (!Handler.hascurio(player, Items.sleepgene.get())) {
                        if (!player.level().isClientSide && player.tickCount % 10 == 0) {
                            if (player.level().isDay()) {
                                if (player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))) {
                                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 1));
                                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 1));
                                    player.setRemainingFireTicks(3);
                                }
                            } else {
                                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0));

                            }
                        }
                    }

                } else {
                    stack.set(DataReg.tag, new CompoundTag());
                }
            }
        }
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
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>  multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "ncrdna", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 2, AttributeModifier.Operation.ADD_VALUE);

        return multimap;
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(  slotContext.entity()));
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.getAttributeModifiers(  slotContext.entity()));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(LivingEntity player) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("ac41f76c-b1dd-32f9-a5d3-3eb94da3e653");
        modifierMultimap.put(Attributes.MAX_HEALTH,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                        -0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)

        );


       return modifierMultimap;
    }

    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.4").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.5").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.6").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.7").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.8").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.9").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.11").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        CompoundTag tag = pStack.get(DataReg.tag);
        if (tag != null){
            if (tag.getBoolean(batskill.batskill)) {
                pTooltipComponents.add(Component.translatable("item.bloodvirus.tool.string.12").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        }
    }
}
