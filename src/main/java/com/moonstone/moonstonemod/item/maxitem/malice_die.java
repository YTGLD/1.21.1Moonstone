package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class malice_die extends CommonItem implements Die {
    /*
      	如果附近生物的攻击目标是你，那么每增加一个生物：
      	+10%伤害
      	+9%生命恢复
      	+8%速度
      	+7%攻击速度
      	+6%护甲
      	+5%生命值

        如果目标数量少于3，那么你造成的伤害减35%

      	攻击会使目标8格半径内的生物主动攻击你
     */

    public static final String MALICE_DIE = "MaliceDie";
    public static void att(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.malice_die.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities){
                    if (living instanceof Mob mob) {
                        mob.setTarget(player);
                    }
                }
            }
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                player.getAttributes().addTransientAttributeModifiers(this.Head(stack));
                int size = 0;
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (living instanceof Mob mob) {
                        if (mob.getTarget() != null && mob.getTarget().is(player)) {
                            size = entities.size();
                        }
                    }
                }
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putInt(MALICE_DIE, size);
                } else {
                    stack.set(DataReg.tag, new CompoundTag());
                }
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head(stack));
    }
    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {

            float s = stack.get(DataReg.tag).getInt(MALICE_DIE);//1 == 100%
            if (s > 18) {
                s=18;
            }
            s /= 100f;//0.01 = 1%
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*10,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.heal, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*9,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*8,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*7,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(Attributes.ARMOR, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*6,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            multimap.put(AttReg.cit, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    s*5,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            float ss = 0;
            if (stack.get(DataReg.tag).getInt(MALICE_DIE)<3){
                ss = 0.35f;
            }
            multimap.put(AttReg.alL_attack, new AttributeModifier(
                    ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    -(0.15f+ss),
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.10").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.8").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.malice_die.tool.string.9").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));


    }
}
