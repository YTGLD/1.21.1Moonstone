package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.base.biochemistry;
import com.ytgld.seeking_immortals.item.nightmare.base.blood_god;
import com.ytgld.seeking_immortals.item.nightmare.base.blood_ring;
import com.ytgld.seeking_immortals.item.nightmare.base.bone_or_god;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class nightmare_base extends nightmare {

    public int tick = 0;
    public nightmare_base(){}


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
        tick = 100;
        if (slotContext.entity() instanceof Player player) {
            int kill = Handler.getTagNumber(stack, blood_god.giveName_kill);
            int heal = Handler.getTagNumber(stack,blood_god.giveName_heal);
            int damage = Handler.getTagNumber(stack,blood_god.giveName_damage);

            int killOwner = Handler.getTagNumber(stack,bone_or_god.give);


            int hurtDamage = Handler.getTagNumber(stack,blood_ring.giveName_damage);
            int biochemistry_give = Handler.getTagNumber(stack,biochemistry.giveName);

            if (stack.get(DataReg.tag)!=null) {

                if (!stack.get(DataReg.tag).getBoolean(biochemistry.giveNameEnd)) {
                    if (biochemistry_give >= Config.SERVER.biochemistry.get()) {
                        player.addItem(new ItemStack(Items.biochemistry.get()));
                        stack.get(DataReg.tag).putBoolean(biochemistry.giveNameEnd,true);
                    }
                }

                if (!stack.get(DataReg.tag).getBoolean(blood_ring.give_End)) {
                    if (hurtDamage >= Config.SERVER.blood_ring.get()) {
                        player.addItem(new ItemStack(Items.blood_ring.get()));
                        stack.get(DataReg.tag).putBoolean(blood_ring.give_End,true);
                    }
                }

                if (!stack.get(DataReg.tag).getBoolean(bone_or_god.giveEnd)) {
                    if (killOwner >= Config.SERVER.bone_or_god_give.get()) {
                        player.addItem(new ItemStack(Items.bone_or_god.get()));
                        stack.get(DataReg.tag).putBoolean(bone_or_god.giveEnd,true);
                    }
                }


                if (!stack.get(DataReg.tag).getBoolean(blood_god.give_End)) {
                    if (kill >= Config.SERVER.blood_god_kill.get()
                            && heal >= Config.SERVER.blood_god_heal.get()
                            && damage >= Config.SERVER.blood_god_damage.get()) {
                        player.addItem(new ItemStack(Items.blood_god.get()));
                        stack.get(DataReg.tag).putBoolean(blood_god.give_End,true);
                    }
                }
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
            stack.set(DataReg.tag, new CompoundTag());
        }




        if (!stack.get(DataReg.tag).getBoolean("canDo")) {
            Random random = new Random();
            ArrayList<Item> items = new ArrayList<>(List.of(
                    Items.nightmare_base_stone.get(),
                    Items.nightmare_base_reversal.get(),
                    Items.nightmare_base_black_eye.get(),

                    Items.nightmare_base_redemption.get(),
                    Items.nightmare_base_fool.get(),
                    Items.nightmare_base_insight.get(),

                    Items.nightmare_base_start.get()
            ));
            for (int i = 0; i < Config.SERVER.nightmareBaseMaxItem.get(); i++) {
                if (!items.isEmpty()) {
                    int index = random.nextInt(items.size());
                    Item selectedItem = items.remove(index);
                    addLoot(slotContext.entity(), selectedItem, stack);
                }
            }
            stack.get(DataReg.tag).putBoolean("canDo", true);
        }
    }
    public static void biochemistry(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_base.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base.get())) {

                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(biochemistry.giveNameEnd)) {
                                        int s = (int) event.getAmount();
                                        if (s < 1) {
                                            s = 1;
                                        }
                                        Handler.addTagNumber(stack, biochemistry.giveName, player, s);
                                    }
                                }

                            }
                        }
                    }
                });
            }
        }
    }
    public static void healGive(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_base.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base.get())) {
                                if (stack.get(DataReg.tag)!=null) {
                                    if (!stack.get(DataReg.tag).getBoolean(blood_god.give_End)) {
                                        int s = (int) event.getAmount();
                                        if (s < 1) {
                                            s = 1;
                                        }
                                        Handler.addTagNumber(stack, blood_god.giveName_heal, player, s);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void damageGive(LivingDamageEvent .Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_base.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(blood_god.give_End)) {

                                        int s = (int) event.getNewDamage();
                                        if (s < 1) {
                                            s = 1;
                                        }
                                        Handler.addTagNumber(stack, blood_god.giveName_damage, player, s);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void blood_ringDamage(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_base.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(blood_ring.give_End)) {
                                        int s = (int) event.getAmount();
                                        if (s < 1) {
                                            s = 1;
                                        }
                                        Handler.addTagNumber(stack, blood_ring.giveName_damage, player, s);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void killGive(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_base.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof OwnableEntity ownableEntity) {
                                        if (ownableEntity.getOwner() instanceof Player o) {
                                            if (player.is(o)){
                                                if (!stack.get(DataReg.tag).getBoolean(bone_or_god.giveEnd)) {
                                                    Handler.addTagNumber(stack, bone_or_god.give, player, 1);
                                                }
                                            }
                                        }
                                    }


                                    if (!stack.get(DataReg.tag).getBoolean(blood_god.give_End)) {
                                        Handler.addTagNumber(stack, blood_god.giveName_kill, player, 1);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        float s = -0.3f;
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_reversal_mysterious.get())) {
            s = 0;
        }
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_redemption_down_and_out.get())) {
            s += 0.35f;
        }
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_redemption.get())) {
            s -= Config.SERVER.nightmare_base_redemption.get()/100f;
        }
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).isPresent()
                    && CuriosApi.getCuriosInventory(player).get().isEquipped(com.moonstone.moonstonemod.init.items.Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    private void addLoot(Entity entity,
                         Item itemList,
                         ItemStack stack) {
        if (entity instanceof Player player) {
            if (stack.get(DataReg.tag) != null) {
                player.addItem(itemList.getDefaultInstance());
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        ResourceLocation.parse("nightmare_base" + "add_slot"
                        ), 3, AttributeModifier.Operation.ADD_VALUE);

        return linkedHashMultimap;
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
    public boolean isTrail(ItemStack stack) {
        return true;
    }

    @Override
    public RenderType getTrailRenderType(ItemStack stack) {
        return MRender.gingAlpha;
    }

    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,155,50,255);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}

