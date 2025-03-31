package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.entity.flysword;
import com.moonstone.moonstonemod.event.old.TextEvt;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class as_amout  extends Doom implements TextEvt.Twelve{
    public static String canFlySword = "canFlySword";
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.isEmpty()) {
                if (me.get(DataReg.tag) == null) {
                    me.set(DataReg.tag, new CompoundTag());
                }
                CompoundTag tag = me.get(DataReg.tag);
                boolean c = tag.getBoolean(canFlySword);
                tag.putBoolean(canFlySword, !c);
                return true;
            }
        }
        return false;
    }


    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player ){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.as_amout) ){
                            if (stack.get(DataReg.tag) != null) {
                                if (!stack.get(DataReg.tag).getBoolean(canFlySword)){
                                    if (Config.SERVER.canFlySword.get() == false) {
                                        if (Handler.hascurio(player, Items.million.get()) && Handler.hascurio(player, Items.as_amout.get())) {
                                            return;
                                        }
                                    }
                                    if (Handler.hascurio(player,Items.as_amout.get())){
                                        if (!player.getCooldowns().isOnCooldown(Items.as_amout.get())) {
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 2, 2);

                                            LivingEntity target = event.getEntity();
                                            for (int p = 0; p < 7; p++) {

                                                float lvl = Mth.nextFloat(RandomSource.create(), -0.3f, 0.3f);

                                                as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                                                as_sword.setPos(target.position().x, target.position().y + 1, target.position().z);
                                                Vec3 forward = player.getLookAngle();
                                                double speed = 0.25f;

                                                as_sword.setDeltaMovement(forward.add(lvl, lvl, lvl).x * speed, forward.add(lvl, lvl, lvl).y * speed, forward.add(lvl, lvl, lvl).z * speed);
                                                as_sword.setOwner(player);

                                                player.level().addFreshEntity(as_sword);
                                                as_sword.setTarget(target);

                                                player.getCooldowns().addCooldown(Items.as_amout.get(),100);
                                            }

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
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, tooltip, pTooltipFlag);
        tooltip.add(Component.translatable("item.moonstone.sword").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.as_amout.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.as_amout.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.as_amout.tool.string.3").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.as_amout.tool.string.4").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.as_amout.tool.string.5").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.as_amout.tool.string.6").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.as_amout.tool.string.7").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.moonstone.tool.string.sword").withStyle(ChatFormatting.GOLD));
        if (pStack.get(DataReg.tag)!=null){
            if (!pStack.get(DataReg.tag).getBoolean(canFlySword)){
                tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
            }else {
                tooltip.add(Component.translatable("item.moonstone.tooltips.on").withStyle(ChatFormatting.GOLD));
            }
        }else {
            tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
        }
    }
}

