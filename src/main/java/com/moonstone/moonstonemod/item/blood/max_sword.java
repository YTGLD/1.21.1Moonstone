package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class max_sword  extends Item implements ICurioItem , Blood {
    public static final String MaxSword = "MaxSword";


    public max_sword() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_sword.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.max_sword.get())){
                                    if (stack.get(DataReg.tag)!=null){
                                        float dam = (float) stack.get(DataReg.tag).getInt(MaxSword) /20;
                                        event.setAmount(event.getAmount()*(1+dam));
                                        if (Mth.nextInt(RandomSource.create(),1,2)==1) {
                                            if (stack.get(DataReg.tag).getInt(MaxSword)<9) {
                                                stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) + 1);
                                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.75F, 0.75F);
                                            }
                                        }
                                        if (stack.get(DataReg.tag).getInt(MaxSword)>=8){
                                            event.setAmount(event.getAmount()*4);
                                            stack.get(DataReg.tag).remove(MaxSword);
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_ATTACK_IMPACT, SoundSource.NEUTRAL, 2, 2);
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 0), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            player.level().levelEvent(2001, new BlockPos((int)player.getX(), (int) (player.getY() + 1), (int) player.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

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
    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_sword.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.max_sword.get())){
                                    if (stack.get(DataReg.tag)!=null){
                                        if (stack.get(DataReg.tag).getInt(MaxSword)<9) {
                                            stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) + Mth.nextInt(RandomSource.create(),1,3));
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.75F, 0.75F);
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

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.get(DataReg.tag)!=null) {
            if (!slotContext.entity().level().isClientSide){
                if (slotContext.entity().tickCount%120==0){
                    if (stack.get(DataReg.tag).getInt(MaxSword) > 0) {
                        stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) - 1);
                        slotContext.entity().heal(4+slotContext.entity().getMaxHealth()/50);
                        slotContext.entity().level().levelEvent(2001, new BlockPos((int) slotContext.entity().getX(), (int) (slotContext.entity().getY() + 1), (int) slotContext.entity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                        Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
                        int range = 3;
                        List<LivingEntity> entities = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                        for (LivingEntity living : entities){
                            if (!living.is(slotContext.entity())){
                                living.addEffect(new MobEffectInstance(Effects.blood,200,2));
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
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.4").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.5").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.6").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.7").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
