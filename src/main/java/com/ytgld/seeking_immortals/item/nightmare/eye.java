package com.ytgld.seeking_immortals.item.nightmare;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.EntityTs;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.test_entity.orb_entity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Optional;

/**
 * 无名邪眼
 * <p>
 * <p>
 * 撕咬你目视的生物，并削弱它们和恢复你的生命值
 * <p>
 * 假设撕咬时你是满血状态，那么多余恢复的生命值将储存在物品当中
 * <p>
 * <p>
 * 当你受到致命伤害，且储存的血液量大于你1000%最大生命值时
 * <p>
 * 消耗所有的血液并免除这次死亡附带恢复你至满血
 */
public class eye extends nightmare {
    public static String health = "HealthEye";

    public static void CurioDamageEvent(CurioHurtEvent event){
        Player player = event.getPlayer();
        ItemStack stack = event.getStack();
        LivingIncomingDamageEvent damageEvent = event.getEvent();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (Handler.hascurio(player,Items.eye.get())){
            if (stack.is(Items.eye.get())) {
                if (compoundTag != null) {
                    if (compoundTag.getInt(health) >= player.getMaxHealth() * 3) {
                        player.heal(compoundTag.getInt(health));
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 1F, 1F);
                        damageEvent.setAmount(0);
                        compoundTag.putInt(health, (int) (compoundTag.getInt(health) - player.getMaxHealth()));
                    }
                }
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (look(slotContext.entity().level(), slotContext.entity()) instanceof LivingEntity entity) {
            if (entity.isAlive()){
                if (slotContext.entity().tickCount%10==0) {
                    if (!slotContext.entity().level().isClientSide) {
                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1));

                    }
                    entity.invulnerableTime = 0;


                    entity.hurt(entity.damageSources().magic(), slotContext.entity().getMaxHealth() / 20f + 3);
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PHANTOM_BITE, SoundSource.AMBIENT, 1, 1);

                    CompoundTag compoundTag = stack.get(DataReg.tag);
                    if (compoundTag!=null) {
                        if (slotContext.entity().getHealth() >= slotContext.entity().getMaxHealth()) {
                            compoundTag.putInt(health, (int) (compoundTag.getInt(health)+slotContext.entity().getMaxHealth() / 100 + 2));
                        } else {
                            slotContext.entity().heal(slotContext.entity().getMaxHealth() / 100f + 2);
                        }
                    }else {
                        stack.set(DataReg.tag,new CompoundTag());
                    }
                    orb_entity orb_entity = new orb_entity(EntityTs.orb_entity.get(), slotContext.entity().level());
                    orb_entity.r = Mth.nextInt(RandomSource.create(),100,255);
                    orb_entity.g = Mth.nextInt(RandomSource.create(),0,25);
                    orb_entity.b = Mth.nextInt(RandomSource.create(),175,255);

                    orb_entity.setOwner(slotContext.entity());
                    orb_entity.setPos(entity.position().add(0,1.5,0));
                    orb_entity.setDeltaMovement(new Vec3(Mth.nextFloat(RandomSource.create(),-0.21F,0.25F),0.25f,Mth.nextFloat(RandomSource.create(),-0.311F,0.25F)));

                    slotContext.entity().level().addFreshEntity(orb_entity);
                }
            }
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        ResourceLocation.parse("eye" + "add_slot"
                        ), 1, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
    public Entity look(Level level, LivingEntity living) {
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.eye.tool.string.8").withStyle(ChatFormatting.DARK_RED));
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null){
            pTooltipComponents.add(Component.translatable("item.eye.tool.string.5").append(": ").append(String.valueOf(compoundTag.getInt(health))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }else {
            pTooltipComponents.add(Component.translatable("item.eye.tool.string.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }
    }
}


