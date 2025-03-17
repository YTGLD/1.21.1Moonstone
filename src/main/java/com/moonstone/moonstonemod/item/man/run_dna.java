package com.moonstone.moonstonemod.item.man;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class run_dna extends ManDNA{

    public run_dna() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).component(DataReg.man,ManBundleContents.EMPTY));
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.protein.get(),//蛋白质
                Drugs.hydrolysis.get(),//水解
                Drugs.cp_energy.get(),//cp能力
                Drugs.phosphorylation.get()//异常磷酸化
        );
    }
    public  static void phosphorylationDamage(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.run_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.run_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.phosphorylation)) {
                                            event.setAmount(event.getAmount()*1.2f);
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
    public  static void phosphorylation(CallbackInfoReturnable<Float> cir,Player player){
        if (Handler.hascurio(player, Items.run_dna.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.run_dna.get())) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is( Drugs.phosphorylation)){
                                        cir.setReturnValue(cir.getReturnValue()/2);
                                    }
                                }));
                            }
                        }
                    }
                }
            });
        }
    }


    public  static void cp_energy(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.run_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.run_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is( Drugs.cp_energy)){
                                            if (player.isSprinting()){
                                                event.setAmount(event.getAmount()*1.1f);
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
    public  static void run(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.run_dna.get())) {
                if (player.isSprinting()){
                    event.setAmount(event.getAmount()*0.9f);
                }
            }
        }
    }
    public  static void protein(CallbackInfoReturnable<Float> cir,Player player){
        if (Handler.hascurio(player, Items.run_dna.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.run_dna.get())) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is( Drugs.protein)){
                                        if (player.isSprinting()){
                                            cir.setReturnValue(cir.getReturnValue()*1.2f);
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

    public  static void hydrolysis(CallbackInfoReturnable<Float> cir,Player player){
        if (Handler.hascurio(player, Items.run_dna.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.run_dna.get())) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is(Drugs.hydrolysis)) {
                                        cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                    }
                                }));
                            }
                        }
                    }
                }
            });
        }
    }
    public  static void hydrolysisDamage(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.run_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.run_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is( Drugs.hydrolysis)) {
                                            event.setAmount(event.getAmount() * 0.9f);
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
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {



        pTooltipComponents.add(Component.translatable("item.run_dna.tool.string").withStyle(ChatFormatting.GOLD));
        ManBundleContents manBundleContents = pStack.get(DataReg.man);
        if (manBundleContents != null) {
            manBundleContents.items().forEach((itemStack -> {
                if (!itemStack.isEmpty()) {
                    ResourceLocation resourceLocation= BuiltInRegistries.ITEM.getKey(itemStack.getItem());
                    String s = resourceLocation.toString().replace(":",".");
                    pTooltipComponents.add(Component.translatable("item."+s).withStyle(ChatFormatting.GOLD));
                }
            }));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
