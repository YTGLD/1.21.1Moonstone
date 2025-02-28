package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class coffin_entity extends Arrow {
    public coffin_entity(EntityType<? extends coffin_entity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

    }

    @Override
    public void playerTouch(Player entity) {

    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount%3==0) {
            float s = this.tickCount / 20f / 11f;
            if (s >= 4) {
                s=4;
            }else {
                BlockState blockstate = this.level().getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ()));
                this.level().levelEvent(2001, new BlockPos((int) this.getX(), (int) (this.getY()), (int) this.getZ()), Block.getId(blockstate));
            }
        }
        if (tickCount>=250) {

            if (!this.getTags().contains("canUseY")) {
                ytgld atSword = new ytgld(EntityTs.ytgld.get(), this.level());
                atSword.setPos(this.position());
                if (this.getOwner() != null) {
                    atSword.setOwnerUUID(this.getOwner().getUUID());
                }
                this.level().addFreshEntity(atSword);
                this.getTags().add("canUseY");
            }
            if (tickCount>=300){
                this.discard();
            }


        }
    }
}

