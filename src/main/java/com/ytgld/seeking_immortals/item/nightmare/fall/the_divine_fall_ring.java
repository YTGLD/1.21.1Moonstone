package com.ytgld.seeking_immortals.item.nightmare.fall;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.INightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.Godtip;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.common.CuriosRegistry;

import java.util.List;
import java.util.Map;

public class the_divine_fall_ring extends Item implements ICurioItem, INightmare, Godtip, Terror {
    public static final String uDead = "undead";

    public the_divine_fall_ring() {
        super(new Properties().stacksTo(1).component(CuriosRegistry.CURIO_ATTRIBUTE_MODIFIERS, CurioAttributeModifiers.EMPTY)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void exp(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_divine_fall_ring.get())) {
                event.setDroppedExperience(event.getDroppedExperience() * 2);
            }
        }
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return linkedHashMultimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            if (slotContext.entity().tickCount >= 20) {
            } else {
                slotContext.entity().addEffect(new MobEffectInstance(Effects.invulnerable, 200, 0, false, false, false));
            }
        }
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player) {

            if (!player.level().isClientSide) {
                float lv = player.getHealth() / player.getMaxHealth();

                lv *= 100;
                int now = (int) (100 - (lv));
                if (stack.get(DataReg.tag) == null) {
                    stack.set(DataReg.tag, new CompoundTag());
                }
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putInt(uDead, now);
                }

                player.getAttributes().addTransientAttributeModifiers(ad(stack));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            int lvl = stack.get(DataReg.tag).getInt(uDead);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;


            modifiers.put(AttReg.heal, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


        }
        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String tring1 = "item.the_divine_fall_ring.tool.string.1";
        String tring2 = "item.the_divine_fall_ring.tool.string.2";
        String tring3 = "item.the_divine_fall_ring.tool.string.3";
        String tring4 = "item.the_divine_fall_ring.tool.string.4";
        String tring5 = "item.the_divine_fall_ring.tool.string.5";
        String tring6 = "item.the_divine_fall_ring.tool.string.6";
        String tring7 = "item.the_divine_fall_ring.tool.string.7";

        addTip(tooltipComponents, tring1);
        addTip(tooltipComponents, tring2);
        addTip(tooltipComponents, tring3);
        addTip(tooltipComponents, tring4);
        addTip(tooltipComponents, tring5);
        addTip(tooltipComponents, tring6);
        addTip(tooltipComponents, tring7);

        tooltipComponents.add(Component.translatable("item.immortal.tool.string.5").withStyle(ChatFormatting.RED));
    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF0000)));
        return co;
    }
    public void addTip(List<Component> tooltipComponents, String Z) {
        int red  = 255;
        int purple  = 255;
        int g = (int) (255 * Math.sin(NewEvent.time/200f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        tooltipComponents.add(Component.translatable(Z).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, red, g, purple)))));
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/fire.png");

    }

    @Nullable
    @Override
    public Map<Integer, Component> describe(ItemStack stack) {
        return null;
    }

    @Override
    public int maxLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public int nowLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean showFire(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRot(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isTrail(ItemStack stack) {
        return false;
    }

    @Override
    public RenderType getTrailRenderType(ItemStack stack) {
        return MRender.light;
    }

    @Override
    public int color(ItemStack stack) {
        int red  = 255;
        int purple  = 255;
        int g = (int) (255 * Math.sin(NewEvent.time/33f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        return Light.ARGB.color(255,red,purple,g);
    }
}