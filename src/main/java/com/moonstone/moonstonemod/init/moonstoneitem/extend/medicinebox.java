package com.moonstone.moonstonemod.init.moonstoneitem.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.catalyzer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class medicinebox extends TheNecoraIC {
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(Items.botton.get()));
                    Other.shrink(1);

                    return true;
                }
            }
        }
        return false;
    }


    public static int do_hurt;
    public static int do_apple;
    public static int do_jump;


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return super.use(level, player, usedHand);
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "medicine",ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 3, AttributeModifier.Operation.ADD_VALUE);

        return linkedHashMultimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
           CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null){
                do_hurt = tag.getInt(AllEvent.hurt_size);
                do_apple = tag.getInt(AllEvent.apple);
                do_jump = tag.getInt(AllEvent.jump_size);
            }
        }
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            pTooltipComponents.add(Component.translatable(""));
            CompoundTag tag = pStack.get(DataReg.tag);
            if (tag != null) {
                if (tag.getBoolean(AllEvent.blood_eat) &&
                        tag.getBoolean(AllEvent.blood_hurt) &&
                        tag.getBoolean(AllEvent.blood_jump) &&
                        tag.getBoolean(AllEvent.blood_spawn) &&
                        tag.getBoolean(AllEvent.blood_enchant)) {
                    pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
                    pTooltipComponents.add(Component.translatable("item.medicinebox.tool.string.6").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));


                }
            }
        }
    }
}
