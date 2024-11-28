package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.Enchants;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class body_stone  extends CommonItem implements Die {
    /*
    这个物品会使箱子里的所有饰品被施加诅咒

	作为补偿，每个具有负面附魔的物品，都会获得强大的增益
	每级附魔：
	+0.6%伤害，暴击，最大生命
	+1%治疗
	+2%挖掘速度
	+1%抗击退
	+0.4%速度，近战伤害，攻速

	添加了3个诅咒附魔：{
		恐怖：在夜晚，每级诅咒增加2.5%受到伤害
		恶意：附近每有一个生物以你为目标，每级诅咒减少1%伤害（不超过80%）
		威胁：受到伤害有概率使失去的血量翻倍，每级增加1%的概率

	这些附魔只能在饰品上生效

     */

    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable("item.body_stone.tool.string.8").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
            pTooltipComponents.add(Component.translatable(""));

            pTooltipComponents.add(Component.literal("Curse").append(":"+size).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));


        } else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        }
    }


    int size = 0;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack s) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(Head(slotContext.entity()));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack s) {

        slotContext.entity().getAttributes().removeAttributeModifiers(Head(slotContext.entity()));

    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(LivingEntity entity){
        List<Integer> integers = new ArrayList<>();
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {

                        Holder.Reference<Enchantment> terror = entity.level().registryAccess()
                                .registryOrThrow(Registries.ENCHANTMENT)
                                        .getHolderOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
                                                ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
                                                        Enchants.Terror)));
                        Holder.Reference<Enchantment> malice = entity.level().registryAccess()
                                .registryOrThrow(Registries.ENCHANTMENT)
                                .getHolderOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
                                        ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
                                                Enchants.malice)));
                        Holder.Reference<Enchantment> threat = entity.level().registryAccess()
                                .registryOrThrow(Registries.ENCHANTMENT)
                                .getHolderOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
                                        ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
                                                Enchants.threat)));


                        integers.add(stack.getEnchantmentLevel(terror)
                                + stack.getEnchantmentLevel(malice)
                                + stack.getEnchantmentLevel(threat));
                    }
                }
            }
        });
        float ss = 0;
        for (int lvl : integers) {
            ss+=lvl;
        }
        size= (int) ss;
        ss*=3f;
        multimap.put(AttReg.alL_attack, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*1/750f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(AttReg.cit, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*1/750f/4.5f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.MAX_HEALTH,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*1/750f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.ARMOR,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss/3/100f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(AttReg.dig,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss/3/100f*2,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        multimap.put(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss/3/100,
                AttributeModifier.Operation.ADD_VALUE));

        multimap.put(Attributes.ATTACK_SPEED,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*3/2000f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        multimap.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*3/2000f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        multimap.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage" + Items.body_stone.get().getDescriptionId()),
                ss*3/2000f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>  multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "charm", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 2, AttributeModifier.Operation.ADD_VALUE);

        return multimap;
    }

}