package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class the_prison_of_sin extends Item implements ICurioItem, Blood {
    public the_prison_of_sin() {
        super(new Item.Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_prison_of_sin.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (stack.is(Items.the_prison_of_sin.get())) {
                                if (stack.get(DataReg.tag)!=null
                                        && event.getEntity().getEncodeId()!=null)
                                {
                                    String name = event.getEntity().getEncodeId();

                                    if (stack.get(DataReg.tag).getString(name).isEmpty()) {
                                        stack.get(DataReg.tag).putString(name, name);
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
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {

        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
        slotContext.entity().getAttributes().addTransientAttributeModifiers(Health());
    }


    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null) {
            stack.set(DataReg.tag, new CompoundTag());
        }

        stack.setDamageValue(stack.getDamageValue()+1);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(Health());
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s = 110;
        if (stack.get(DataReg.tag)!=null){
            s += stack.get(DataReg.tag).getAllKeys().size();
        }
        s -= 100;
        s /= 100;
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            modifierMultimap.put(attribute, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return modifierMultimap;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> Health() {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("health" + this.getDescriptionId()), -0.80, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("health" + this.getDescriptionId()), -0.80, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.get(DataReg.tag)!=null) {
            if (Screen.hasShiftDown()) {
                if (pStack.get(DataReg.tag) != null) {
                    pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));

                    pTooltipComponents.add(Component.translatable(""));
                    if (pStack.get(DataReg.tag) != null) {
                        for (String s : pStack.get(DataReg.tag).getAllKeys()) {
                            pTooltipComponents.add(Component.translatable(s).withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
                        }
                        pTooltipComponents.add(Component.translatable(""));
                    }
                } else {
                    pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
                }
            } else {
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.1").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.2").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.3").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.5").append(String.valueOf(pStack.get(DataReg.tag).getAllKeys().size())).append("%").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            }
        }else {
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.7").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.6").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
        }
    }
}
