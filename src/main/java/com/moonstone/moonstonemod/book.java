package com.moonstone.moonstonemod;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.CuriosHelper;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;
import java.util.Map;

public class book extends Item implements ICurioItem {
    public book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !Handler.hascurio(slotContext.entity(),this);
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(Head(player,stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().removeAttributeModifiers(Head(player,stack));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null) {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    public static final String nineSword = "nineSword";

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (ModList.get().isLoaded("patchouli")){

            if (p_41433_ instanceof ServerPlayer player){
                PatchouliAPI.get().openBookGUI(player,ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"soul_book"));
            }
        }else {
            p_41433_.displayClientMessage(Component.translatable("moonstone.book.error").withStyle(ChatFormatting.RED), true);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.book.tool.string").withStyle(ChatFormatting.GOLD));
            if (pStack.get(DataReg.tag)!=null){
                if (pStack.get(DataReg.tag).getInt(nineSword)>=300){
                    pTooltipComponents.add(Component.translatable("item.book.tool.string.nine_sword").withStyle(ChatFormatting.AQUA));

                }
            }

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD));
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> Head(Player player, ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        if (player.getMainHandItem().getItem() instanceof SwordItem) {
            if (stack.get(DataReg.tag)!=null&&stack.get(DataReg.tag).getInt(nineSword)>=300) {
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                        0.1,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                        0.1,
                        AttributeModifier.Operation.ADD_VALUE));
            }
        }

        return multimap;
    }

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.book.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is( Items.book.get())){
                                if (player.getMainHandItem().getItem() instanceof SwordItem){
                                    if (stack.get(DataReg.tag)!=null){
                                        if (stack.get(DataReg.tag).getInt(nineSword)<=300){
                                            stack.get(DataReg.tag).putInt(nineSword,stack.get(DataReg.tag).getInt(nineSword)+1);
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
    public static boolean isHasMaxInt(Player player,String string,int max){
        if ( CuriosApi.getCuriosInventory(player).isPresent()) {
            Map<String, ICurioStacksHandler> curios = CuriosApi.getCuriosInventory(player).get().getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (stack.is(Items.book) && stack.get(DataReg.tag) != null) {
                        if (stack.get(DataReg.tag).getInt(string) >= max) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
