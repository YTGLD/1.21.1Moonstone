package com.moonstone.moonstonemod.item.plague.ALL;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.BundleContents;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.DNAS;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.apache.commons.lang3.math.Fraction;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class dna extends Item implements Iplague, ICurioItem , TextEvt.Twelve{
    public dna() {
        super(new Item.Properties().stacksTo(1).component(DataReg.BUNDLE_CONTENTS, BundleContents.EMPTY).rarity(Rarity.UNCOMMON));
    }
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (!(other.getItem() instanceof DNAS)) {
            return false;
        }
        if (stack.getCount() != 1) {
            return false;
        } else if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents == null) {
                return false;
            } else {
                BundleContents.Mutable bundlecontents$mutable = new BundleContents.Mutable(bundlecontents);
                if (other.isEmpty()) {
                    ItemStack itemstack = bundlecontents$mutable.removeOne();
                    if (itemstack != null) {
                        this.playRemoveOneSound(player);
                        access.set(itemstack);
                    }
                } else {
                    int i = bundlecontents$mutable.tryInsert(other);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataReg.BUNDLE_CONTENTS, bundlecontents$mutable.toImmutable());
                return true;
            }
        } else {
            return false;
        }
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (dropContents(itemstack, player)) {
            this.playDropContentsSound(player);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public boolean isBarVisible(ItemStack stack) {
        BundleContents bundlecontents = stack.getOrDefault(DataReg.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return bundlecontents.weight().compareTo(Fraction.ZERO) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        net.minecraft.world.item.component.BundleContents bundlecontents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, net.minecraft.world.item.component.BundleContents.EMPTY);
        return Math.min(1 + Mth.mulAndTruncate(bundlecontents.weight(), 12), 13);
    }

    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    private static boolean dropContents(ItemStack stack, Player player) {
        BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
        if (bundlecontents != null && !bundlecontents.isEmpty()) {
            stack.set(DataReg.BUNDLE_CONTENTS, BundleContents.EMPTY);
            if (player instanceof ServerPlayer) {
                bundlecontents.itemsCopy().forEach((p_330078_) -> {
                    player.drop(p_330078_, true);
                });
            }

            return true;
        } else {
            return false;
        }
    }
    public void onDestroyed(ItemEntity itemEntity) {
        BundleContents bundlecontents = itemEntity.getItem().get(DataReg.BUNDLE_CONTENTS);
        if (bundlecontents != null) {
            itemEntity.getItem().set(DataReg.BUNDLE_CONTENTS, BundleContents.EMPTY);
            ItemUtils.onContainerDestroyed(itemEntity, bundlecontents.itemsCopy());
        }

    }


    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents != null) {
                bundlecontents.items().forEach((itemStack -> {
                    if (itemStack.is(DNAItems.speed_metabolism)) {
                        int count = itemStack.getCount();
                        int a = count / 32;
                        if (player.getFoodData().getSaturationLevel() < a&&player.getFoodData().getFoodLevel()>18) {
                            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                        }
                    }
                    if (itemStack.is(DNAItems.cell_disorder)) {
                        int count = itemStack.getCount();
                        int a = count * 10;
                        if (!player.level().isClientSide&&player.tickCount % a == 0){
                            player.removeAllEffects();
                        }
                    }
                    if (itemStack.is(DNAItems.cell_eyes)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count));

                        for (LivingEntity living : entities){
                            living.addEffect(new MobEffectInstance(MobEffects.GLOWING,100,0,false,false));
                        }

                    }

                    if (itemStack.is(DNAItems.cell_flu)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count));

                        for (LivingEntity living : entities){
                            if (!living.is(player)) {
                                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false));
                                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, false, false));
                                living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1, false, false));
                            }
                        }
                    }
                }));

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

    public  static void doBreak(LivingEntityUseItemEvent.Start event){
        LivingEntity player = event.getEntity();
        if (Handler.hascurio(player, Items.dna.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is( Items.dna.get())) {
                            BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.items().forEach((itemStack -> {

                                    if (itemStack.is(DNAItems.cell_big_boom)) {
                                        int count = itemStack.getCount();
                                        if (event.getItem().getUseAnimation() ==UseAnim.EAT){
                                            event.setDuration((int) (event.getDuration() * (1 - (count/100f))));
                                        }
                                    }
                                }));
                            }
                        }
                    }
                }
            });
        }
    }
    public  static void eat(LivingEntityUseItemEvent.Finish event){
        LivingEntity kl = event.getEntity();
        if (kl instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.dna.get())) {
                                BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_digestion)) {

                                            int count = itemStack.getCount();
                                            if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+count/10);
                                                player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel()+count/10f);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public  static void hur(LivingIncomingDamageEvent event){
        Entity p = event.getEntity();
        if (p instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.dna.get())) {
                                BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_inheritance)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            s/=3.2f;//0.2
                                            event.setAmount(event.getAmount()*(1-s));
                                        }
                                        if (itemStack.is(DNAItems.cell_cranial)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            if (event.getSource().is(DamageTypes.FALLING_ANVIL)
                                                    && event.getSource().is(DamageTypes.FALLING_STALACTITE)
                                                    && event.getSource().is(DamageTypes.FALLING_BLOCK)
                                                    && event.getSource().is(DamageTypes.MOB_PROJECTILE))
                                            {
                                                event.setAmount(event.getAmount()*(1-s));
                                            }
                                        }

                                        if (itemStack.is(DNAItems.cell_compress)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            if (event.getSource().getEntity() instanceof LivingEntity living){
                                                float dam = event.getAmount() * s;
                                                living.hurt(living.damageSources().dryOut(),dam);
                                            }
                                        }
                                        if (itemStack.is(DNAItems.cell_constant)) {
                                            if (!player.getCooldowns().isOnCooldown(DNAItems.cell_constant.get())) {
                                                float s = itemStack.getCount();//64
                                                s /= 100f;//0.64
                                                player.invulnerableTime = player.invulnerableTime + ((int) (player.invulnerableTime * s));
                                                player.getCooldowns().addCooldown(DNAItems.cell_constant.get(), player.invulnerableTime);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.dna.get())) {
                                BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_acid)) {
                                            int count = itemStack.getCount();//64
                                            ItemStack head = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
                                            if (!head.isEmpty()&&head.getMaxDamage()!=0){
                                                head.hurtAndBreak(count, event.getEntity(),EquipmentSlot.HEAD);
                                            }
                                            ItemStack CHEST = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
                                            if (!CHEST.isEmpty()&&CHEST.getMaxDamage()!=0){
                                                CHEST.hurtAndBreak(count, event.getEntity(),EquipmentSlot.CHEST);
                                            }
                                            ItemStack LEGS = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
                                            if (!LEGS.isEmpty()&&LEGS.getMaxDamage()!=0){
                                                LEGS.hurtAndBreak(count, event.getEntity(),EquipmentSlot.LEGS);
                                            }
                                            ItemStack FEET = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
                                            if (!FEET.isEmpty()&&FEET.getMaxDamage()!=0){
                                                FEET.hurtAndBreak(count, event.getEntity(),EquipmentSlot.HEAD);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public  static void dieD(LivingDeathEvent event){
        Entity p = event.getSource().getEntity();
        if (p instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.dna.get())) {
                                BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_darwin)) {
                                            float count = itemStack.getCount();
                                            if (Mth.nextInt(RandomSource.create(),1,2)==1){
                                                player.heal(count/8);
                                            }else {
                                                player.hurt(player.damageSources().magic(),count/32);
                                            }
                                        }
                                        if (itemStack.is(DNAItems.cell_god)) {
                                            float count = itemStack.getCount();
                                            player.heal(count/32);
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();

        BundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
        if (bundlecontents != null) {
            bundlecontents.items().forEach((itemStack -> {
                if (itemStack.is(DNAItems.atp_height)) {
                    int count = itemStack.getCount();
                    int a = count / 4;
                    multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            a,
                            AttributeModifier.Operation.ADD_VALUE));
                }

                if (itemStack.is(DNAItems.cell_off_on)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.is(DNAItems.cell_oxygen)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    count *= 0.5F;
                    multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }


                if (itemStack.is(DNAItems.cell_in_water)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.WATER_MOVEMENT_EFFICIENCY, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_VALUE));
                }

                if (itemStack.is(DNAItems.cell_break_down_water)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    count *= 1.5F;
                    multimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.is(DNAItems.cell_in_air)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.JUMP_STRENGTH, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_ground)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    count *= 2F;
                    multimap.put(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_necrosis)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(AttReg.heal, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_bone_add)) {
                    float count = itemStack.getCount();
                    count /= 4f;
                    multimap.put(Attributes.ARMOR, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_VALUE));
                }
                if (itemStack.is(DNAItems.cell_sense)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.OXYGEN_BONUS, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_VALUE));

                    multimap.put(Attributes.SUBMERGED_MINING_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count*10,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_synthesis)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_putrefactive)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(Attributes.BURNING_TIME, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            -count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.is(DNAItems.cell_dna_suppression)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(AttReg.cit, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.is(DNAItems.cell_preferential)) {
                    {
                        float count = itemStack.getCount();
                        count /= 100;
                        multimap.put(AttReg.heal, new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("base_attack_damage_heal_cell_preferential" + this.getDescriptionId()),
                                count,
                                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    }
                    {
                        float count = itemStack.getCount();
                        count /= 4;
                        multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("base_attack_damage_max_health_cell_preferential" + this.getDescriptionId()),
                                count,
                                AttributeModifier.Operation.ADD_VALUE));
                    }
                }
                if (itemStack.is(DNAItems.cell_chromosome)) {
                    float count = itemStack.getCount();
                    count /= 10;
                    multimap.put(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                            count,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

            }));
        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (Screen.hasShiftDown()) {
            BundleContents bundlecontents = pStack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents != null) {
                bundlecontents.items().forEach((itemStack -> {
                    pTooltipComponents.add(Component.translatable(itemStack.getDescriptionId()).append("："+ itemStack.getCount()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEE6363))));
                }));
            }
        }else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB22222))));
            pTooltipComponents.add(Component.literal(""));

        }
    }
}