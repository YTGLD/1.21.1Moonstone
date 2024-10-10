package com.moonstone.moonstonemod.init.moonstoneitem.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.DNAItems;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.item.BloodVirus.ex.catalyzer;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.item.necora;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class medicinebox extends TheNecoraIC {
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.getItem() instanceof TheNecoraIC necoraIC) {
                if (me.getItem() instanceof medicinebox) {
                    CompoundTag tag = me.get(DataReg.tag);
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(necoraIC);
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);
                            Other.shrink(1);
                        }
                    }else {
                        me.set(DataReg.tag,new CompoundTag());
                    }
                }
            }
            if (Other.getItem() instanceof medIC necoraIC) {
                if (me.getItem() instanceof medicinebox) {
                    CompoundTag tag = me.get(DataReg.tag);
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(necoraIC);
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);
                            Other.shrink(1);
                        }
                    }else {
                        me.set(DataReg.tag,new CompoundTag());
                    }
                }
            }
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(com.moonstone.moonstonemod.init.Items.botton.get()));
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
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        int count = Mth.nextInt(RandomSource.create(),1,5);
        int count1 = Mth.nextInt(RandomSource.create(),2,6);
        int count2 = Mth.nextInt(RandomSource.create(),3,7);
        int count3 = Mth.nextInt(RandomSource.create(),2,8);
        int count4 = Mth.nextInt(RandomSource.create(),1,2);

        int count5 = Mth.nextInt(RandomSource.create(),1,2);

        if (stack.get(DataReg.tag)!=null){
            while (stack.get(DataReg.tag).getAllKeys().iterator().hasNext()){
                Item Dna = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,stack.get(DataReg.tag).getAllKeys().iterator().next()));
                ResourceLocation name = BuiltInRegistries.ITEM.getKey(Dna);
                if (livingEntity instanceof Player player) {
                    if (Dna instanceof necora) {
                        player.addItem(new ItemStack(DNAItems.atp_height, count*2));
                        player.addItem(new ItemStack(DNAItems.cell_inheritance, count1*2));
                        player.addItem(new ItemStack(DNAItems.cell_big_boom, count2*2));
                        player.addItem(new ItemStack(DNAItems.cell_disorder, count3*2));
                        player.addItem(new ItemStack(DNAItems.cell_in_air, count4*2));
                        player.addItem(new ItemStack(DNAItems.cell_off_on, count5*2));
                        stack.get(DataReg.tag).getAllKeys().remove(name.getPath());
                    }
                    if (Dna instanceof TheNecoraIC ||Dna instanceof medIC) {
                        switch (Mth.nextInt(RandomSource.create(),1,13)){
                            case 1: player.addItem(new ItemStack(DNAItems.atp_height, count));
                                break;
                            case 2: player.addItem(new ItemStack(DNAItems.cell_big_boom, count));
                                break;
                            case 3: player.addItem(new ItemStack(DNAItems.cell_disorder, count));
                                break;
                            case 4: player.addItem(new ItemStack(DNAItems.cell_off_on, count));
                                break;
                            case 5: player.addItem(new ItemStack(DNAItems.cell_darwin, count));
                                break;
                            case 6: player.addItem(new ItemStack(DNAItems.cell_god, count));
                                break;
                            case 7: player.addItem(new ItemStack(DNAItems.speed_metabolism, count));
                                break;
                            case 8: player.addItem(new ItemStack(DNAItems.cell_oxygen, count));
                                break;
                            case 9: player.addItem(new ItemStack(DNAItems.cell_break_down_water, count));
                                break;
                            case 10: player.addItem(new ItemStack(DNAItems.cell_ground, count));
                                break;
                            case 11: player.addItem(new ItemStack(DNAItems.cell_in_water, count));
                                break;
                            case 12: player.addItem(new ItemStack(DNAItems.cell_inheritance, count));
                                break;
                            case 13: player.addItem(new ItemStack(DNAItems.cell_in_air, count));
                                break;
                        }
                        stack.get(DataReg.tag).getAllKeys().remove(name.getPath());
                    }
                }
            }
        }
        return stack;
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
        }else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            pTooltipComponents.add(Component.literal(""));
            CompoundTag tag = pStack.get(DataReg.tag);
            if (tag != null){
                for (String s : tag.getAllKeys()) {
                    pTooltipComponents.add(Component.translatable(s).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
                }
            }
        }
    }
}
