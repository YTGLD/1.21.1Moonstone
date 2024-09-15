package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_giant;
import com.moonstone.moonstonemod.entity.zombie.cell_giant;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class Handler {

    public static <T extends TamableAnimal> void trySpawnMob(
            Player player,
            EntityType<T> pEntityType,
            MobSpawnType pSpawnType,
            ServerLevel pLevel,
            BlockPos pPos,
            int pAttempts,
            int p_216409_,
            int pYOffset,
            SpawnUtil.Strategy pStrategy
    ) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for (int i = 0; i < pAttempts; i++) {
            int j = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            int k = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            blockpos$mutableblockpos.setWithOffset(pPos, j, pYOffset, k);
            if (pLevel.getWorldBorder().isWithinBounds(blockpos$mutableblockpos)
                    && moveToPossibleSpawnPosition(pLevel, pYOffset, blockpos$mutableblockpos, pStrategy)) {
                T t = (T)pEntityType.create(pLevel, null, blockpos$mutableblockpos, pSpawnType, false, false);
                if (t != null) {

                    if (t instanceof nightmare_giant nightmareGiant){
                        nightmareGiant.setPose(Pose.EMERGING);
                    }

                    if (t instanceof cell_giant nightmareGiant){
                        nightmareGiant.setPose(Pose.EMERGING);
                    }

                    t.setOwnerUUID(player.getUUID());
                    if (net.neoforged.neoforge.event.EventHooks.checkSpawnPosition(t, pLevel, pSpawnType)) {
                        pLevel.addFreshEntityWithPassengers(t);
                        return;
                    }

                    t.discard();
                }
            }
        }

    }

    private static boolean moveToPossibleSpawnPosition(ServerLevel pLevel, int pYOffset, BlockPos.MutableBlockPos pPos, SpawnUtil.Strategy pStrategy) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos().set(pPos);
        BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);

        for (int i = pYOffset; i >= -pYOffset; i--) {
            pPos.move(Direction.DOWN);
            blockpos$mutableblockpos.setWithOffset(pPos, Direction.UP);
            BlockState blockstate1 = pLevel.getBlockState(pPos);
            if (pStrategy.canSpawnOn(pLevel, pPos, blockstate1, blockpos$mutableblockpos, blockstate)) {
                pPos.move(Direction.UP);
                return true;
            }

            blockstate = blockstate1;
        }

        return false;
    }


    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity instanceof Player player) {
            if (player.getCapability(CuriosCapability.INVENTORY)!= null) {
                if (CuriosApi.getCuriosInventory(entity).isPresent()) {
                    List<SlotResult> a = CuriosApi.getCuriosInventory(entity).get().findCurios(curio);
                    for (SlotResult slotResult : a) {
                        if (slotResult.stack().is(curio))
                            return true;
                    }
                }
            }
        }
        return false;

    }

    public static void renderLine(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        // 设置线条的起点
        vertexConsumer.addVertex(poseStack.last().pose(), (float) start.x, (float) start.y, (float) start.z)
                .setColor(1,1,1,a)
                .setNormal(1,0,0);

        // 设置线条的终点
        vertexConsumer.addVertex(poseStack.last().pose(), (float) end.x, (float) end.y, (float) end.z)
                .setColor(1,1,1,a)
                .setNormal(1,0,0);
    }
}
