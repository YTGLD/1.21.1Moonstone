package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.item.man.run_dna;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract Vec3 position();

    @Inject(at = @At("RETURN"), method = "isInvulnerableTo", cancellable = true)
    public void mhead(DamageSource p_20122_, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof Player player){
            if (p_20122_.getEntity() instanceof MoonTamableAnimal moonTamableAnimal){
                if (moonTamableAnimal.getOwner()!=null&&moonTamableAnimal.getOwner().is(player)) {
                    cir.setReturnValue(true);
                }
            }
        }


        if (p_20122_.getEntity() instanceof Player player){
            Entity entity = (Entity) (Object) this;
            if (entity instanceof TamableAnimal tamableAnimal){
                ResourceLocation resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(tamableAnimal.getType());
                if (resourceLocation.getNamespace().equals(MoonStoneMod.MODID)){
                    if (tamableAnimal.getOwner()!=null &&tamableAnimal.getOwner().is(player)){
                        if (Config.SERVER.immortalZombie.get()){
                            cir.setReturnValue(true);
                        }
                    }
                }
            }
        }
        if ((Entity) (Object) this instanceof Player player){
            if (Handler.hascurio(player,Items.twelve_sword_.get())){
                if (p_20122_.getEntity()!=null) {
                    if (p_20122_.getEntity().getType() == EntityTs.at_sword_entity.get()) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }


        if ((Entity) (Object) this instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_redemption_degenerate.get())) {
                if (p_20122_.is(DamageTypes.MAGIC) ||
                        p_20122_.is(DamageTypes.FALL) ||
                        p_20122_.is(DamageTypes.ON_FIRE) ||
                        p_20122_.is(DamageTypes.LAVA) ||
                        p_20122_.is(DamageTypes.IN_FIRE)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
