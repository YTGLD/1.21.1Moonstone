package com.ytgld.seeking_immortals.item.nightmare.base;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

/**
 * 任何尝试对你施加负面BUFF的生物都会自食恶果
 * <p>
 *     免疫负面药水状态
 * <p>
 *
 * <p>
 *宠物攻击会附加10%主人的面板伤害
 * <p>
 *
 * 宠物伤害提高200%
 * <p>
 * 宠物抗性提高50%
 * <p>
 * 若宠物没有攻击目标，则缓慢恢复生命值
 * <p>
 * <p>
 * <p>
 *     杀死100只宠物获取
 */
public class bone_or_god  extends nightmare implements SuperNightmare {

    public static final String give = "TheBonesAreStillGod";
    public static final String giveEnd = "giveEndTheBonesAreStillGod";
    public static void effect(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.bone_or_god.get())) {
                if (event.getEffectSource() instanceof LivingEntity living) {
                    MobEffectInstance instance = event.getEffectInstance();
                    if (instance != null) {
                        living.addEffect(instance);
                    }
                }
            }
        }
    }

    public static void hurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof OwnableEntity ownableEntity) {
            if (ownableEntity.getOwner() instanceof Player player) {
                if (Handler.hascurio(player, Items.bone_or_god.get())) {
                    event.setAmount((float) (event.getAmount() * 2 + (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.1F)));
                }
            }
        }
        if (event.getEntity() instanceof OwnableEntity ownableEntity) {
            if (ownableEntity.getOwner() instanceof Player player) {
                if (Handler.hascurio(player, Items.bone_or_god.get())) {
                    event.setAmount(event.getAmount() * 0.5F);
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            float range = 8;
            List<LivingEntity> entities =
                    player.level().getEntitiesOfClass(LivingEntity.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));
            for (LivingEntity living : entities) {
                if (living instanceof OwnableEntity entity) {
                    if (living instanceof Targeting targeting) {
                        if (entity.getOwner() instanceof Player player1) {
                            if (player.is(player1)) {
                                if (targeting.getTarget() == null) {
                                    if (living.tickCount % 20==1) {
                                        living.heal(1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string.3").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string.4").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.bone_or_god.tool.string.5").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));

    }
}
