package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.entity.nightmare_giant_to;
import com.moonstone.moonstonemod.entity.zombie.cell_giant;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class Handler {
    public static final String Giant_Time = "Giant_Time";
    public static final String Giant_Boom = "Giant_Boom";
    public static final String Subspace_Giant = "Subspace_Giant";

    public static final String Bone_Giant = "Bone_Giant";
    public static final String Parasitic_cell_Giant = "Parasitic_cell_Giant";
    public static final String Disgusting__cell_Giant = "Disgusting__cell_Giant";


    public static <T extends TamableAnimal> void trySpawnMob(
            LivingEntity player,
            EntityType<T> pEntityType,
            MobSpawnType pSpawnType,
            ServerLevel pLevel,
            BlockPos pPos,
            int pAttempts,
            int p_216409_,
            int pYOffset,
            SpawnUtil.Strategy pStrategy,
            Item I,
            int time
    ) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        if (player instanceof Player eee){
            if (eee.getCooldowns().isOnCooldown(I)){
                return;
            }
        }
        for (int i = 0; i < pAttempts; i++) {
            int j = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            int k = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            blockpos$mutableblockpos.setWithOffset(pPos, j, pYOffset, k);
            if (pLevel.getWorldBorder().isWithinBounds(blockpos$mutableblockpos)
                    && moveToPossibleSpawnPosition(pLevel, pYOffset, blockpos$mutableblockpos, pStrategy)) {
                T t = (T) pEntityType.create(pLevel, null, blockpos$mutableblockpos, pSpawnType, false, false);
                if (t != null) {
                    t.setOwnerUUID(player.getUUID());

                    if (Handler.hascurio(player, Items.anaerobic_cell.get())) {
                        t.addTag(Giant_Time);
                    }
                    if (Handler.hascurio(player, Items.giant_boom_cell.get())) {
                        t.addTag(Giant_Boom);
                    }
                    if (Handler.hascurio(player, Items.subspace_cell.get())) {
                        t.addTag(Subspace_Giant);
                    }
                    if (Handler.hascurio(player, Items.bone_cell.get())) {
                        t.addTag(Bone_Giant);
                    }
                    if (Handler.hascurio(player, Items.parasitic_cell.get())) {
                        t.addTag(Parasitic_cell_Giant);
                    }
                    if (Handler.hascurio(player, Items.disgusting_cells.get())) {
                        t.addTag(Disgusting__cell_Giant);
                    }


                    if (t instanceof nightmare_giant_to nightmareGiant) {
                        nightmareGiant.setPose(Pose.EMERGING);
                    }

                    if (t instanceof cell_giant nightmareGiant) {
                        nightmareGiant.setPose(Pose.EMERGING);
                    }

                    t.setOwnerUUID(player.getUUID());

                    if (player instanceof Player eee){
                       eee.getCooldowns().addCooldown(I,time);
                    }

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

    //            if (hascurio(player,Items.deceased_contract.get())) {
//                if (curio == Items.necora.get() || curio == Items.bloodvirus.get()) {
//                    return false;
//                }
//            }
    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity instanceof LivingEntity player) {
            if (player.getCapability(CuriosCapability.INVENTORY) != null) {

                if (CuriosApi.getCuriosInventory(player).isPresent()
                        && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.nightmare_base.get())) {
                    if (curio == Items.evil_mob.get() || curio == Items.god_lead.get() || curio == Items.malice_die.get()) {
                        return false;
                    }
                }
                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.the_blood_book.get())) {
                    if (curio == Items.owner_blood_speed_eye.get() || curio == Items.owner_blood_eye.get()) {
                        return true;
                    }
                }




                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.god_ambush.get())) {
                    if (curio == Items.ambush.get()) {
                        return true;
                    }
                }
                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.god_putrefactive.get())) {
                    if (curio == Items.putrefactive.get()) {
                        return true;
                    }
                }
                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.god_fermentation.get())) {
                    if (curio == Items.fermentation.get()) {
                        return true;
                    }
                }
                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.god_autolytic.get())) {
                    if (curio == Items.autolytic.get()) {
                        return true;
                    }
                }
                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.god_regenerative.get())) {
                    if (curio == Items.regenerative.get()) {
                        return true;
                    }
                }




                if (CuriosApi.getCuriosInventory(entity).isPresent()
                        && CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.maxamout.get())) {
                    if (curio == Items.blueamout.get() || curio == Items.redamout.get() || curio == Items.greedamout.get()) {
                        return true;
                    }
                }
                if (CuriosApi.getCuriosInventory(player).isPresent()
                        && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.evil_mob.get())) {
                    if (curio == Items.necora.get() || curio == Items.nightmareeye.get() || curio == Items.bloodvirus.get()) {
                        return true;
                    }
                }


                if (CuriosApi.getCuriosInventory(entity).isPresent()) {
                    List<SlotResult> a = CuriosApi.getCuriosInventory(entity).get().findCurios(curio);
                    for (SlotResult slotResult : a) {
                        if (slotResult.stack().is(curio)
                                && hasDC(player, curio)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean hasDC(LivingEntity entity, Item item) {
        if (entity instanceof Player player) {
            if (player.getCapability(CuriosCapability.INVENTORY) != null) {
                if (CuriosApi.getCuriosInventory(player).isPresent()
                        && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.deceased_contract.get())) {
                    if (item == Items.necora.get() || item == Items.bloodvirus.get()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void renderColor(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float r, int red, int g, int b) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquareColor(vertexConsumer, poseStack, up1, up2, down1, down2, a, red, g, b);
        }
    }

    private static void addSquareColor(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha, int r, int g, int b) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(r, g, b, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(r, g, b, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(r, g, b, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(r, g, b, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
    }

    public static void renderBlood(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float r) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    public static void renderBlack(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float r) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquareBlack(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    private static void addSquareBlack(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(106 ,90 ,205, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(106 ,90 ,205, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(106 ,90 ,205, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(106 ,90 ,205, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
    }
    private static void addSquare(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(220, 20, 60, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(220, 20, 60, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(220, 20, 60, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(220, 20, 60, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
    }

    public static void renderSword(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float r) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSword(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }

    private static void addSword(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(151, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(151, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(151, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(151, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
    }

    public static void renderLine(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float radius) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    public static void renderSnake(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = 0.075f; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
}
