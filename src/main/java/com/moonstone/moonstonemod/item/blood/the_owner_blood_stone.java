package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class the_owner_blood_stone  extends Item implements ICurioItem, Blood {
    public the_owner_blood_stone() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static void did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_owner_blood_stone.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.the_owner_blood_stone.get())) {
                    attack_blood attB = new attack_blood(EntityTs.attack_blood.get(), player.level());
                    Vec3 playerPos = player.position();
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.AMBIENT, 1, 1);
                    float range = 16;
                    List<LivingEntity> entities =
                            player.level().getEntitiesOfClass(LivingEntity.class,
                                    new AABB(playerPos.x - range,
                                            playerPos.y - range,
                                            playerPos.z - range,
                                            playerPos.x + range,
                                            playerPos.y + range,
                                            playerPos.z + range));

                    for (LivingEntity living : entities) {
                        if (!living.is(player) && living.deathTime <= 0
                                && !living.is(event.getEntity())
                                && living.isAlive()) {
                            ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(living.getType());
                            if (!entitys.getNamespace().equals(MoonStoneMod.MODID)) {
                                attB.setTarget(living);
                                attB.setAddDamgae(event.getEntity().getMaxHealth() * 1.25f);
                                attB.setOwner(player);
                                attB.speeds = 0.55f;
                                attB.setCannotFollow(false);
                                attB.setPos(event.getEntity().position());
                                attB.isPlayer = true;

                                player.level().addFreshEntity(attB);
                                player.getCooldowns().addCooldown(Items.the_owner_blood_stone.get(), 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.the_owner_blood_stone.tool.string").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.the_owner_blood_stone.tool.string.1").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.the_owner_blood_stone.tool.string.2").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.the_owner_blood_stone.tool.string.3").withStyle(ChatFormatting.RED));


    }
}
