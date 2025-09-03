package com.moonstone.moonstonemod.mixin.other;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Shadow public abstract FluidState getFluidState();

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;",
            at = @At("RETURN"), cancellable = true)
    private void addFluidCollision(BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext, CallbackInfoReturnable<VoxelShape> cir) {
        FluidState fluidState = this.getFluidState();

        if (collisionContext instanceof EntityCollisionContext context) {
            if (context.getEntity() instanceof LivingEntity entity ) {
                if (Handler.hascurio(entity, Items.ambush.get())) {
                    double stepHeight = 1.0d;
                    VoxelShape fluidShape = Shapes.box(0.0, 0.0, 0.0, 1.0, fluidState.getHeight(blockGetter, blockPos), 1.0); // fluidState.getShape() is b u g g e d
                    if (collisionContext.isAbove(fluidShape.move(0, -stepHeight, 0), blockPos, false)) {
                        cir.setReturnValue(Shapes.or(fluidShape, cir.getReturnValue()));
                    }
                }
                if (Handler.hascurio(entity, Items.evilcandle.get())) {
                    if (fluidState.is(Fluids.LAVA)) {
                        double stepHeight = 1.0d;
                        VoxelShape fluidShape = Shapes.box(0.0, 0.0, 0.0, 1.0, fluidState.getHeight(blockGetter, blockPos), 1.0); // fluidState.getShape() is b u g g e d
                        if (collisionContext.isAbove(fluidShape.move(0, -stepHeight, 0), blockPos, false)) {
                            cir.setReturnValue(Shapes.or(fluidShape, cir.getReturnValue()));
                        }
                    }
                }
            }
        }
    }
}