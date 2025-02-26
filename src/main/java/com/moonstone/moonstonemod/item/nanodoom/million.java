package com.moonstone.moonstonemod.item.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Doom;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class million extends Doom implements TextEvt.Twelve{
    public static final String sizeLvl = "swordSize";
    public static final String attackLvl = "attackLvlSize";
    public static final String allAttackTime = "allAttackTime";

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) != null) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(Head(stack));
            if (stack.get(DataReg.tag).getInt(allAttackTime)>0){
                stack.get(DataReg.tag).putInt(allAttackTime,stack.get(DataReg.tag).getInt(allAttackTime)-1);
            }

            if (stack.get(DataReg.tag).getInt(allAttackTime)<=0){
                stack.get(DataReg.tag).putInt(attackLvl,0);
            }
        }else {
            CompoundTag tag = new CompoundTag();
            tag.putInt(sizeLvl,30);
            stack.set(DataReg.tag, tag);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(Head(stack));
    }

    public static void hurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.million.get())&&Handler.hascurio(player,Items.as_amout.get())){
                return;
            }


            if (Handler.hascurio(player, Items.million.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack .is(Items.million.get())&&!player.getCooldowns().isOnCooldown(Items.million.get())) {
                                if (player.getAttackStrengthScale(1) >= 1) {
                                    if (stack.get(DataReg.tag) != null) {

                                        if (stack.get(DataReg.tag).getInt(sizeLvl) >= 3) {

                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 2, 2);

                                            LivingEntity target = event.getEntity();


                                            for (int j = 0; j < 3; j++) {

                                                float lvl = Mth.nextFloat(RandomSource.create(), -0.6f, 0.6f);

                                                as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                                                as_sword.setPos(target.position().x, target.position().y + 1, target.position().z);
                                                Vec3 forward = player.getLookAngle();
                                                double speed = 0.25;

                                                as_sword.setDeltaMovement(forward.add(lvl, lvl, lvl).x * speed, forward.add(lvl, lvl, lvl).y * speed, forward.add(lvl, lvl, lvl).z * speed);

                                                as_sword.setOwner(player);

                                                player.level().addFreshEntity(as_sword);
                                                as_sword.setTarget(target);

                                                if (stack.get(DataReg.tag).getInt(sizeLvl) > 0) {
                                                    stack.get(DataReg.tag).putInt(sizeLvl, stack.get(DataReg.tag).getInt(sizeLvl) - 1);
                                                }
                                                player.getCooldowns().addCooldown(Items.million.get(),10);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            float dam = stack.get(DataReg.tag).getInt(attackLvl) / 100f;
            multimap.put(AttReg.alL_attack, new AttributeModifier( ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                    dam,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }

        return multimap;
    }

    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.million.tool.string.1").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.2").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.3").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.4").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.million.tool.string.5").withStyle(ChatFormatting.GOLD));
        } else {
            pTooltipComponents.add(Component.translatable("item.million.tool.string.6").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            if (pStack.get(DataReg.tag)!=null) {
                pTooltipComponents.add(Component.translatable("item.million.tool.string.7").append(pStack.get(DataReg.tag).getInt(attackLvl) + "%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("item.million.tool.string.8").append(pStack.get(DataReg.tag).getInt(sizeLvl) + "").withStyle(ChatFormatting.YELLOW));
            }
        }
    }
}
