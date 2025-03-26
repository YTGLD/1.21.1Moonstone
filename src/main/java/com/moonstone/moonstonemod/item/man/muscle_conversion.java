package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

/**
 * 玩家本体伤害降低20%
 * <p>
 * 召唤的僵尸继承玩家50%的攻击力
 * <p>
 * 召唤的僵尸伤害提高30%
 */
public class muscle_conversion extends ManDNA implements Iplague {
    public muscle_conversion() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).component(DataReg.man,
                ManBundleContents.EMPTY));
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of();
    }
    public  static void zombieAttack(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof MoonTamableAnimal moonTamableAnimal){
            if (moonTamableAnimal.getOwner() instanceof Player player){
                if (Handler.hascurio(player, Items.muscle_conversion.get())) {
                    float zombieDamage = (float) player.getAttributeValue(AttReg.zombie_attack_damage);
                    moonTamableAnimal.getAttributes().addTransientAttributeModifiers(muscle_conversion.modifierMultimap(player));
                    event.setAmount(event.getAmount()*zombieDamage);

                }
            }
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap= HashMultimap.create();
        modifierMultimap.put(AttReg.alL_attack,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("muscle_conversion"+ MoonStoneMod.MODID),
                        -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    public static Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap (Player owner){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap= HashMultimap.create();
        float damage = (float) (owner.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5f);
        modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("muscle_conversion"+ MoonStoneMod.MODID),
                        damage, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string.2").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }
}
