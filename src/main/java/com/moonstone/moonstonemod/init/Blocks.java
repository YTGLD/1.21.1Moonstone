package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, MoonStoneMod.MODID);
    public static final DeferredHolder<Block, ?> mit_block  =REGISTRY.register("mit_block",()-> new TransparentBlock(
            BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .noOcclusion()
                    .isValidSpawn(net.minecraft.world.level.block.Blocks::never)
    ));

}
