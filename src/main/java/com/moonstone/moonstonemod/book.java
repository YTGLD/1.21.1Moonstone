package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_owner;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import vazkii.patchouli.api.PatchouliAPI;

public class book extends Item {
    public book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        if (ModList.get().isLoaded("patchouli")){

            if (p_41433_ instanceof ServerPlayer player){
                PatchouliAPI.get().openBookGUI(player,ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"soul_book"));
            }
        }else {
            p_41433_.displayClientMessage(Component.translatable("moonstone.book.error").withStyle(ChatFormatting.RED), true);
        }
        blood_orb_owner blood_orb_owner = new blood_orb_owner(EntityTs.blood_orb_owner.get(),p_41432_);
        blood_orb_owner.setPos(p_41433_.position());
        blood_orb_owner.setOwnerUUID(p_41433_.getUUID());
        p_41432_.addFreshEntity(blood_orb_owner);
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
