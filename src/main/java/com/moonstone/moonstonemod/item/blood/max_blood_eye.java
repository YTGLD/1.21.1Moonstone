package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.zombie.blood_zombie;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;

public class max_blood_eye extends Item implements ICurioItem , Blood {
    public max_blood_eye() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }
    public static void Att (LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_blood_eye.get())){
                LivingEntity entity = event.getEntity();
                if (!player.getCooldowns().isOnCooldown(Items.max_blood_eye.get())) {

                    if (player.getAttackStrengthScale(1)==1) {
                        if (!entity.hasEffect(Effects.blood)) {
                            entity.addEffect(new MobEffectInstance(Effects.blood, 200, 0));
                        }
                        if (entity.getEffect(Effects.blood) != null && entity.hasEffect(Effects.blood)) {
                            entity.addEffect(new MobEffectInstance(Effects.blood, 200, entity.getEffect(Effects.blood).getAmplifier() + 1));
                            if (entity.getEffect(Effects.blood).getAmplifier() > 3) {
                                blood_zombie zombie = new blood_zombie(EntityTs.blood_zombie.get(), entity.level());
                                zombie.setPos(entity.position());
                                entity.level().addFreshEntity(zombie);
                                entity.removeEffect(Effects.blood);
                                Vec3 playerPos = player.position().add(0, 0.75, 0);
                                int range = 6;
                                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                                for (LivingEntity living : entities) {
                                    if (!living.is(player)) {
                                        if (living.hasEffect(Effects.blood)) {
                                            living.addEffect(new MobEffectInstance(Effects.rage, 200, 1));
                                            living.removeEffect(Effects.blood);
                                        } else {
                                            living.addEffect(new MobEffectInstance(Effects.blood, 200, 1));

                                        }
                                    }
                                }
                            }
                        }

                        player.getCooldowns().addCooldown(Items.max_blood_eye.get(), 20);
                    }
                }
            }
        }
    }
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.max_blood_eye.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_blood_eye.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_blood_eye.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_blood_eye.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_blood_eye.tool.string.4").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
    /*
    校准之眼：{
	    每次斩击都会施加流血（叠加）
	    当流血层数超过3时
    	产生一次血爆造成伤害,并清除流血状态
    	被波及者获得流血，如果状态已存在，则转换成重伤
    	冷却时间5秒
    }
     */
}
