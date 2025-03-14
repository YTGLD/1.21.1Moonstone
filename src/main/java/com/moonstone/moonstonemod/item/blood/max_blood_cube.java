package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.Optional;

public class max_blood_cube extends Item implements ICurioItem, Blood {
    public max_blood_cube() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));



    }

    public static final String slowing = "Slowing";
    public static final int slowingMax = 100;

    public static void RightClickItem(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        if (getPlayerLookTarget(player.level(),player) instanceof LivingEntity living){
            if (player.isShiftKeyDown()) {
                player.getCooldowns().addCooldown(Items.max_blood_cube.get(), 4);
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag == null){
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player){
            if (!player.getCooldowns().isOnCooldown(this)) {
                if (stack.get(DataReg.tag) != null) {
                    if (stack.get(DataReg.tag).getInt(max_blood_cube.slowing) > 0) {
                        stack.get(DataReg.tag).putInt(max_blood_cube.slowing, stack.get(DataReg.tag).getInt(max_blood_cube.slowing) - 1);
                    }
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (slotContext.entity() instanceof Player player){
            if (player.getCooldowns().isOnCooldown(this)){
                Entity entity = getPlayerLookTarget(player.level(),player);
                if (entity instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 2, 1,false,false));
                    if (stack.get(DataReg.tag) != null) {
                        if (stack.get(DataReg.tag).getInt(max_blood_cube.slowing) < max_blood_cube.slowingMax) {
                            stack.get(DataReg.tag).putInt(max_blood_cube.slowing, stack.get(DataReg.tag).getInt(max_blood_cube.slowing) + 2);
                        }
                        if (stack.get(DataReg.tag).getInt(max_blood_cube.slowing) >= (max_blood_cube.slowingMax - 20)) {
                            if (player.tickCount%3==0) {
                                attack_blood attack_blood = new attack_blood(EntityTs.attack_blood.get(), player.level());
                                attack_blood.setPos(new Vec3(living.getX()+Mth.nextFloat(RandomSource.create(),-2,2),living.getY()+Mth.nextFloat(RandomSource.create(),-2.2F,2.2F),living.getZ()+Mth.nextFloat(RandomSource.create(),-2.1F,2.1F)));
                                living.level().addFreshEntity(attack_blood);
                            }
                        }
                    }
                }
            }
        }
    }
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.max_blood_cube.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_blood_cube.tool.string.1").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), -stack.get(DataReg.tag).getInt(slowing) / 100F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return modifierMultimap;
    }


    public static Entity getPlayerLookTarget(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
}
