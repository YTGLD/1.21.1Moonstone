package com.ytgld.seeking_immortals.renderer.light;

public class Light {

//    public static void renderShadow(
//            PoseStack poseStack, MultiBufferSource bufferSource, Entity renderState, LevelReader level, float size,
//            int r,
//            int g,
//            int b
//    ) {
//        int i = Mth.floor(renderState.getX() - size);
//        int j = Mth.floor(renderState.getX() + size);
//
//        int k = Mth.floor(renderState.getY() - size); // 原先的 f * 10 改为 f * 1
//        int l = Mth.floor(renderState.getY() + size); // 新增的下限，f * 10 * 0.1 改为 f * 9
//
//        int i1 = Mth.floor(renderState.getZ() - size);
//        int j1 = Mth.floor(renderState.getZ() + size);
//        PoseStack.Pose posestack$pose = poseStack.last();
//        VertexConsumer vertexconsumer = bufferSource.getBuffer(MRender.entityShadowsEEKING(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/shadow_writer.png")));
//        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
//
//        for (int k1 = i1; k1 <= j1; k1++) {
//            for (int l1 = i; l1 <= j; l1++) {
//                for (int m1 = k; m1 <= l; m1++) {
//                    blockpos$mutableblockpos.set(l1, m1, k1);
//                    ChunkAccess chunkaccess = level.getChunk(blockpos$mutableblockpos);
//                    for (Direction face : Direction.values()) {
//                        renderBlockShadow(
//                                renderState,
//                                posestack$pose, vertexconsumer, chunkaccess, blockpos$mutableblockpos, renderState.getX(), renderState.getY(), renderState.getZ(), size, size, face,
//                                r,g,b
//                        );
//                    }
//                }
//            }
//        }
//    }
//
//    public static void renderBlockShadow(
//            Entity renderState,
//            PoseStack.Pose pose,
//            VertexConsumer consumer,
//            ChunkAccess chunk,
//            BlockPos pos,
//            double x,
//            double y,
//            double z,
//            float size,
//            float weight,
//            Direction face,
//            int r,
//            int g,
//            int b
//    ) {
//        BlockState blockstate = chunk.getBlockState(pos);
//        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
//            VoxelShape voxelshape = blockstate.getShape(chunk, pos);
//            if (blockstate.isCollisionShapeFullBlock(chunk, pos)) {
//                if (!voxelshape.isEmpty()) {
//                    float f1 = weight * 0.5F;
//                    if (f1 >= 0.0F) {
//
//                        float maxDist = 5;
//                        float dist = Handler.getDistanceToGround(renderState);
//                        int alpha = (int) (255 * (1 - (dist / maxDist)));
//                        alpha /= 2;
//                        if (alpha < 0) alpha = 0; // 确保 alpha 不会小于 0
//                        if (dist > maxDist) {
//                            return;
//                        }
//                        int color = ARGB.color(alpha, r, g, b);
//
//
//                        if (alpha > 0) {
//                            BlockPos neighborPos = pos.relative(face);
//                            BlockState neighborState = chunk.getBlockState(neighborPos);
//                            if (!neighborState.isCollisionShapeFullBlock(chunk,neighborPos)) {
//                                AABB aabb = voxelshape.bounds();
//                                double d0, d1, d2, d3, d4, d5;
//                                Vector3f normal = new Vector3f(face.getStepX(), face.getStepY(), face.getStepZ());
//                                switch (face) {
//                                    case DOWN:
//                                        d0 = pos.getX() + aabb.minX;
//                                        d1 = pos.getX() + aabb.maxX;
//                                        d2 = pos.getY() + aabb.minY;
//                                        d3 = pos.getZ() + aabb.minZ;
//                                        d4 = pos.getZ() + aabb.maxZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d3 - z, u(d0 - x, size), v(d3 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d0 - x, size), v(d4 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d4 - z, u(d1 - x, size), v(d4 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d3 - z, u(d1 - x, size), v(d3 - z, size), normal);
//                                        break;
//                                    case UP:
//                                        d0 = pos.getX() + aabb.minX;
//                                        d1 = pos.getX() + aabb.maxX;
//                                        d2 = pos.getY() + aabb.maxY;
//                                        d3 = pos.getZ() + aabb.minZ;
//                                        d4 = pos.getZ() + aabb.maxZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d3 - z, u(d0 - x, size), v(d3 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d0 - x, size), v(d4 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d4 - z, u(d1 - x, size), v(d4 - z, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d3 - z, u(d1 - x, size), v(d3 - z, size), normal);
//                                        break;
//                                    case EAST:
//                                        d0 = pos.getX() + aabb.maxX;
//                                        d2 = pos.getY() + aabb.minY;
//                                        d3 = pos.getY() + aabb.maxY;
//                                        d4 = pos.getZ() + aabb.minZ;
//                                        d5 = pos.getZ() + aabb.maxZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d4 - z, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d5 - z, u(d5 - z, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d5 - z, u(d5 - z, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d4 - z, u(d4 - z, size), v(d3 - y, size), normal);
//                                        break;
//                                    case WEST:
//                                        d0 = pos.getX() + aabb.minX;
//                                        d2 = pos.getY() + aabb.minY;
//                                        d3 = pos.getY() + aabb.maxY;
//                                        d4 = pos.getZ() + aabb.minZ;
//                                        d5 = pos.getZ() + aabb.maxZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d4 - z, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d5 - z, u(d5 - z, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d5 - z, u(d5 - z, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d4 - z, u(d4 - z, size), v(d3 - y, size), normal);
//                                        break;
//                                    case NORTH:
//                                        d0 = pos.getX() + aabb.minX;
//                                        d1 = pos.getX() + aabb.maxX;
//                                        d2 = pos.getY() + aabb.minY;
//                                        d3 = pos.getY() + aabb.maxY;
//                                        d4 = pos.getZ() + aabb.minZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d0 - x, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d4 - z, u(d0 - x, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d3 - y, d4 - z, u(d1 - x, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d4 - z, u(d1 - x, size), v(d2 - y, size), normal);
//                                        break;
//                                    case SOUTH:
//                                        d0 = pos.getX() + aabb.minX;
//                                        d1 = pos.getX() + aabb.maxX;
//                                        d2 = pos.getY() + aabb.minY;
//                                        d3 = pos.getY() + aabb.maxY;
//                                        d4 = pos.getZ() + aabb.maxZ;
//                                        shadowVertex(pose, consumer, color, d0 - x, d2 - y, d4 - z, u(d0 - x, size), v(d2 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d0 - x, d3 - y, d4 - z, u(d0 - x, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d3 - y, d4 - z, u(d1 - x, size), v(d3 - y, size), normal);
//                                        shadowVertex(pose, consumer, color, d1 - x, d2 - y, d4 - z, u(d1 - x, size), v(d2 - y, size), normal);
//                                        break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    public static void shadowVertex(
//            PoseStack.Pose pose,
//            VertexConsumer consumer,
//            int color,
//            double offsetX,
//            double offsetY,
//            double offsetZ,
//            double u,
//            double v,
//            Vector3f normal
//    ) {
//        consumer.addVertex(pose.pose(), (float) offsetX, (float) offsetY, (float) offsetZ)
//                .setColor((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF)
//                .setUv((float) u, (float) v)
//                .setOverlay(OverlayTexture.NO_OVERLAY)
//                .setUv2(0xFF, 0xFF)
//                .setNormal(normal.x(), normal.y(), normal.z());
//
//    }

    public static class ARGB {
        public static int color(int alpha, int red, int green, int blue) {
            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }
    }

    public static float u(double offset, float size) {
        return (float) (-offset / 2.0 / size + 0.5);
    }

    public static float v(double offset, float size) {
        return (float) (-offset / 2.0 / size + 0.5);
    }

}
