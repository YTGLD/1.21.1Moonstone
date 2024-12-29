package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.BookItems;
import com.moonstone.moonstonemod.init.items.Items;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JeiText implements IModPlugin {
    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, "jei_plugin");
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.ectoplasmstone"));
        registration.addIngredientInfo(new ItemStack(Items.twistedstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.twistedstone"));

        registration.addIngredientInfo(new ItemStack(Items.cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell").append(Component.translatable("moonstone.jei.cell")));
        registration.addIngredientInfo(new ItemStack(Items.giant.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant").append(Component.translatable("moonstone.jei.giant")));
        registration.addIngredientInfo(new ItemStack(Items.giant_nightmare.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant_nightmare").append(Component.translatable("moonstone.jei.giant_nightmare")));

        registration.addIngredientInfo(new ItemStack(Items.ambush.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ambush").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.atpoverdose.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.atpoverdose").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.autolytic.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.autolytic").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.fermentation.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.fermentation").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.putrefactive.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.putrefactive").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.regenerative.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.regenerative").append(Component.translatable("moonstone.jei.necora.all")));

        registration.addIngredientInfo(new ItemStack(Items.air.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.air").append(Component.translatable("moonstone.jei.necora.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.motor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.motor").append(Component.translatable("moonstone.jei.necora.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.watergen.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.watergen").append(Component.translatable("moonstone.jei.necora.treasure.all")));


        registration.addIngredientInfo(new ItemStack(Items.anaerobic_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.anaerobic_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));
        registration.addIngredientInfo(new ItemStack(Items.giant_boom_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant_boom_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));
        registration.addIngredientInfo(new ItemStack(Items.subspace_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.subspace_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));

        registration.addIngredientInfo(new ItemStack(Items.cell_mummy.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.adrenaline").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_boom.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_mummy").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_calcification.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_boom").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_blood.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_blood").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.adrenaline.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_calcification").append(Component.translatable("moonstone.jei.cell.all")));

        registration.addIngredientInfo(new ItemStack(Items.nightmare_cube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_cube").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_head.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_head").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_heart.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_heart").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_orb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_orb").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmareanchor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmareanchor").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarecharm.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarecharm").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmareeye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmareeye").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmaremoai.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmaremoai").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarerotten.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarerotten").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarestone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarestone").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmaretreasure.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmaretreasure").append(Component.translatable("moonstone.jei.nightmare")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarewater.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarewater").append(Component.translatable("moonstone.jei.nightmare")));


        registration.addIngredientInfo(new ItemStack(Items.brain.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.brain").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbattery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbattery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mblock.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mblock").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbottle.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbottle").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbox.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbox").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.meye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.meye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mhead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mhead").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mkidney.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mkidney").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.morb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.morb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mshell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mshell").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));

        registration.addIngredientInfo(new ItemStack(Items.blood.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blood").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood")));

        registration.addIngredientInfo(new ItemStack(Items.bloodvirus.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bloodvirus").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus")));


        registration.addIngredientInfo(new ItemStack(Items.batgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.batgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.batskill.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.batskill").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.bloodgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bloodgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.botton.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.botton").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.catalyzer.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.catalyzer").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.flygene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.flygene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.heathgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.heathgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.ragegene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ragegene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.sleepgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.sleepgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));

        registration.addIngredientInfo(new ItemStack(Items.bat_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bat_cell").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_blood_attack.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_blood_attack").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_desecrate.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_desecrate").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_doctor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_doctor").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_fear.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_fear").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_harvest.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_harvest").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_immortal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_immortal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_not_do.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_not_do").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_rage.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_rage").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_scientist.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_scientist").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));


        registration.addIngredientInfo(new ItemStack(Items.ectoplasmball.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmball").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.ectoplasmball")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmcloub.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmcloub").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.ectoplasmcloub")));

        registration.addIngredientInfo(new ItemStack(Items.beacon.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.beacon").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmtree.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmtree").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmshild.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmshild").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmapple.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmapple").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmprism.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmprism").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmcube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmcube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmstar.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmstar").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmsoul.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmsoul").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));

        registration.addIngredientInfo(new ItemStack(Items.ectoplasmsoul.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmsoul").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));

        registration.addIngredientInfo(new ItemStack(Items.soulcube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.soulcube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.soulbattery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.soulbattery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmhorseshoe.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmhorseshoe").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));


        registration.addIngredientInfo(new ItemStack(Items.greedcrystal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.greedcrystal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.obsidianring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.obsidianring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.redamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.redamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.blackeorb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blackeorb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.badgeofthedead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.badgeofthedead").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.whiteorb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.whiteorb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.greedamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.greedamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.evilcandle.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evilcandle").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.evilmug.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evilmug").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.blueamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blueamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.battery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.battery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.warcrystal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.warcrystal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));

        registration.addIngredientInfo(new ItemStack(Items.maxamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.maxamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maxamout")));

        registration.addIngredientInfo(new ItemStack(Items.doomeye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.doomeye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.doomswoud.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.doomswoud").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.magiceye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.magiceye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.magicstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.magicstone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.nanocube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nanocube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.nanorobot.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nanorobot").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.thedoomstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.thedoomstone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.thefruit.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.thefruit").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.wind.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.wind").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));

        registration.addIngredientInfo(new ItemStack(Items.wind_and_rain.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.wind_and_rain").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.wind_and_rain")));


        registration.addIngredientInfo(new ItemStack(Items.calcification.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.calcification").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.calcification")));
        registration.addIngredientInfo(new ItemStack(Items.masticatory.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.masticatory").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.masticatory")));
        registration.addIngredientInfo(new ItemStack(Items.polyphagia.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.polyphagia").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.polyphagia")));
        registration.addIngredientInfo(new ItemStack(Items.quadriceps.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.quadriceps").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.quadriceps")));
        registration.addIngredientInfo(new ItemStack(Items.reanimation.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.reanimation").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.reanimation")));


        registration.addIngredientInfo(new ItemStack(Items.evil_mob.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evil_mob").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.god_lead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.god_lead").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.malice_die.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.malice_die").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.max_eye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.max_eye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.blood_amout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blood_amout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.killer.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.killer").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.rage_blood_head.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.rage_blood_head").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));

        registration.addIngredientInfo(new ItemStack(Items.body_stone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.body_stone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.the_heart.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.the_heart").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.probability.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.probability").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));
        registration.addIngredientInfo(new ItemStack(Items.moon_stone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.moon_stone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood.all")));

        registration.addIngredientInfo(new ItemStack(Items.luck_stone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.luck_stone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.luck_ring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.luck_ring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));


        registration.addIngredientInfo(Items.owner_blood_attack_eye.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_attack_eye"));
        registration.addIngredientInfo(Items.owner_blood_speed_eye.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_speed_eye"));
        registration.addIngredientInfo(Items.owner_blood_eye.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_eye"));
        registration.addIngredientInfo(Items.owner_blood_effect_eye.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_effect_eye"));
        registration.addIngredientInfo(Items.owner_blood_boom_eye.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_boom_eye"));
        registration.addIngredientInfo(Items.owner_blood_vex.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_vex"));
        registration.addIngredientInfo(Items.owner_blood_earth.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.owner_blood_earth"));



        registration.addIngredientInfo(Items.fortunecrystal.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.fortunecrystal"));
        registration.addIngredientInfo(Items.mayhemcrystal.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.mayhemcrystal"));

        registration.addIngredientInfo(Items.deceased_contract.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.deceased_contract"));

        registration.addIngredientInfo(new ItemStack(Items.evil_mob.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evil_mob").append(Component.translatable("moonstone.jei.treasure")));
        registration.addIngredientInfo(new ItemStack(Items.god_lead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.god_lead").append(Component.translatable("moonstone.jei.treasure")));
        registration.addIngredientInfo(new ItemStack(Items.malice_die.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.malice_die").append(Component.translatable("moonstone.jei.treasure")));

        registration.addIngredientInfo(new ItemStack(Items.rage_blood_head.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.rage_blood_head").append(Component.translatable("moonstone.jei.city")));
        registration.addIngredientInfo(new ItemStack(Items.killer.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.killer").append(Component.translatable("moonstone.jei.city")));


        registration.addIngredientInfo(new ItemStack(BookItems.weak.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.weak").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.spore_outbreak.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.spore_outbreak").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.plague_book.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.plague_book").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.exercise_reinforcement.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.exercise_reinforcement").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.detect.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.detect").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.bloodstain.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bloodstain").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.blood_stasis.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blood_stasis").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.bone_structure.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bone_structure").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.mummification.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mummification").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.tumour.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.tumour").append(Component.translatable("moonstone.jei.deceased_contract.all")));
        registration.addIngredientInfo(new ItemStack(BookItems.organizational_regeneration.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.organizational_regeneration").append(Component.translatable("moonstone.jei.deceased_contract.all")));

        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_heart.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_heart").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_eye.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_eye").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_black_eye_red.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_black_eye_red").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_black_eye"))));

        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_brain.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_stone_brain").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_meet.get()), VanillaTypes.ITEM_STACK, Component.translatable( "moonstone.jei.item.moonstone.nightmare_base_stone_meet").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_stone_virus.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_stone_virus").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_stone"))));

        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_card.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_card").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_mysterious.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_mysterious").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_base_reversal_orb.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.item.moonstone.nightmare_base_reversal_orb").append(Component.translatable("moonstone.jei.item.moonstone.nightmare_base.all").append(Component.translatable("item.moonstone.nightmare_base_reversal"))));

    }

}
