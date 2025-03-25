package com.moonstone.moonstonemod.item.plague.dna;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.old.NewEvent;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import com.moonstone.moonstonemod.item.necora;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class dna_box extends TheNecoraIC {
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.getItem() instanceof Iplague) {
                if (me.getItem() instanceof dna_box) {
                    CompoundTag tag = me.get(DataReg.tag);
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(Other.getItem());
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);

                            if (Other.get(DataReg.tag)!=null) {
                                if (Other.get(DataReg.tag).getBoolean(Difficulty.PEACEFUL.getKey())) {
                                    tag.putBoolean(Difficulty.PEACEFUL.getKey() + dnas, true);
                                }
                                if (Other.get(DataReg.tag).getBoolean(Difficulty.EASY.getKey())) {
                                    tag.putBoolean(Difficulty.EASY.getKey() + dnas, true);
                                }
                                if (Other.get(DataReg.tag).getBoolean(Difficulty.NORMAL.getKey())) {
                                    tag.putBoolean(Difficulty.NORMAL.getKey() + dnas, true);
                                }
                                if (Other.get(DataReg.tag).getBoolean(Difficulty.HARD.getKey())) {
                                    tag.putBoolean(Difficulty.HARD.getKey() + dnas, true);
                                }
                                if (Other.get(DataReg.tag).getBoolean(NewEvent.lootTable)) {
                                    tag.putBoolean(NewEvent.lootTable + dnas, true);
                                }

                            }
                            Other.shrink(1);
                            return true;
                        }

                    }else {
                        me.set(DataReg.tag,new CompoundTag());
                    }
                }
            }
            if (Other.getItem() instanceof medIC necoraIC) {
                if (me.getItem() instanceof dna_box) {
                    CompoundTag tag = me.get(DataReg.tag);
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(necoraIC);
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);
                            Other.shrink(1);
                            return true;
                        }
                    }else {
                        me.set(DataReg.tag,new CompoundTag());
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return super.use(level, player, usedHand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        int count = Mth.nextInt(RandomSource.create(),3,9);
        int count1 = Mth.nextInt(RandomSource.create(),2,6);
        int count2 = Mth.nextInt(RandomSource.create(),3,7);
        int count3 = Mth.nextInt(RandomSource.create(),2,8);
        int count4 = Mth.nextInt(RandomSource.create(),1,2);
        int cn = Mth.nextInt(RandomSource.create(),2,4);
        int cn1 = Mth.nextInt(RandomSource.create(),3,5);
        int cn2 = Mth.nextInt(RandomSource.create(),1,5);
        int cn3 = Mth.nextInt(RandomSource.create(),2,6);
        int cn4 = Mth.nextInt(RandomSource.create(),1,6);

        int count5 = Mth.nextInt(RandomSource.create(),1,2);
        if (stack.get(DataReg.tag)!=null){

            while (stack.get(DataReg.tag).getAllKeys().iterator().hasNext()) {

                String itemId = stack.get(DataReg.tag).getAllKeys().iterator().next();
                ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, itemId);


                if (BuiltInRegistries.ITEM.containsKey(resourceLocation)) {
                    Item Dna = BuiltInRegistries.ITEM.get(resourceLocation);
                    if (livingEntity instanceof Player player) {
                        if (Dna instanceof necora) {
                            player.addItem(new ItemStack(DNAItems.atp_height, count * 2));
                            player.addItem(new ItemStack(DNAItems.cell_inheritance, count1 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_big_boom, count2 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_disorder, count3 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_in_air, count4 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_off_on, count5 * 2));

                            stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        }
                        if (Dna instanceof Iplague) {
                            switch (Mth.nextInt(RandomSource.create(), 1, 13)) {
                                case 1:
                                    player.addItem(new ItemStack(DNAItems.atp_height, count));
                                    break;
                                case 2:
                                    player.addItem(new ItemStack(DNAItems.cell_big_boom, count1));
                                    break;
                                case 3:
                                    player.addItem(new ItemStack(DNAItems.cell_disorder, count2));
                                    break;
                                case 4:
                                    player.addItem(new ItemStack(DNAItems.cell_off_on, count));
                                    break;
                                case 5:
                                    player.addItem(new ItemStack(DNAItems.cell_darwin, count3));
                                    break;
                                case 6:
                                    player.addItem(new ItemStack(DNAItems.cell_god, count5));
                                    break;
                                case 7:
                                    player.addItem(new ItemStack(DNAItems.speed_metabolism, count4));
                                    break;
                                case 8:
                                    player.addItem(new ItemStack(DNAItems.cell_oxygen, count));
                                    break;
                                case 9:
                                    player.addItem(new ItemStack(DNAItems.cell_break_down_water, count4));
                                    break;
                                case 10:
                                    player.addItem(new ItemStack(DNAItems.cell_ground, count3));
                                    break;
                                case 11:
                                    player.addItem(new ItemStack(DNAItems.cell_in_water, count2));
                                    break;
                                case 12:
                                    player.addItem(new ItemStack(DNAItems.cell_inheritance, count1));
                                    break;
                                case 13:
                                    player.addItem(new ItemStack(DNAItems.cell_in_air, count));
                                    break;
                            }
                            stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        }
                    }

                }else if (livingEntity instanceof Player player) {
                    if (itemId.contains(Difficulty.PEACEFUL.getKey())) {
                        int b = 1;
                        switch (Mth.nextInt(RandomSource.create(), 1, 13)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height, count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom, count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder, count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on, count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin, count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god, count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism, count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen, count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water, count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground, count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water, count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance, count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air, count * b));
                                break;
                        }
                        stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.EASY.getKey())) {
                        int b = 2;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height, count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom, count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder, count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on, count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin, count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god, count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism, count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen, count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water, count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground, count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water, count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance, count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air, count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis, cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial, count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression, count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive, count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis, count5 * b));
                                break;
                        }
                        stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.NORMAL.getKey())) {
                        int b = 3;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height, count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom, count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder, count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on, count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin, count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god, count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism, count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen, count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water, count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground, count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water, count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance, count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air, count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis, cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;
                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial, count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression, count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive, count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis, count5 * b));
                                break;
                        }
                        stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.HARD.getKey())) {
                        int b = 4;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height, count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom, count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder, count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on, count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin, count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god, count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism, count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen, count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water, count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground, count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water, count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance, count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air, count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis, cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial, count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression, count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive, count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis, count5 * b));
                                break;
                        }

                        stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(NewEvent.lootTable)) {
                        int b = 3;
                        switch (Mth.nextInt(RandomSource.create(), 1, 5)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.cell_compress, count1 * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_preferential, count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_flu, count1 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_constant, count1 * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_chromosome, count1 * b));
                                break;
                        }
                        switch (Mth.nextInt(RandomSource.create(), 14, 28)) {
                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis, cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion, cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid, cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes, cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add, cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense, count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial, count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression, count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive, count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis, count5 * b));
                                break;
                        }
                        stack.get(DataReg.tag).getAllKeys().remove(itemId);
                        break;
                    }
                    break;
                }
            }
        }
        return stack;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.moonstone.medicinebox.tool").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.moonstone.medicinebox.tool.2").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        pTooltipComponents.add(Component.literal(""));
        CompoundTag tag = pStack.get(DataReg.tag);
        if (tag != null){
            for (String s : tag.getAllKeys()) {
                pTooltipComponents.add(Component.translatable(s).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
            }
        }
    }
}
