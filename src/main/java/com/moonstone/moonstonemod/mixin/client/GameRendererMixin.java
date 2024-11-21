package com.moonstone.moonstonemod.mixin.client;

//import com.moonstone.moonstonemod.MoonStoneMod;
//import com.moonstone.moonstonemod.client.StrengtheningLayer;
//import com.moonstone.moonstonemod.entity.flysword;
//import com.moonstone.moonstonemod.entity.zombie.sword_soul;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.LivingEntityRenderer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.Entity;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(GameRenderer.class)
//public abstract class GameRendererMixin {
//
//    @Shadow public abstract void loadEffect(ResourceLocation resourceLocation);
//
//    @Inject(at = @At("RETURN"), method = "checkEntityPostEffect")
//    public void init(Entity entity, CallbackInfo ci) {
//        if (entity instanceof flysword){
//            this.loadEffect(ResourceLocation.withDefaultNamespace("shaders/post/entity_outline.json"));
//        }
//    }
//}
