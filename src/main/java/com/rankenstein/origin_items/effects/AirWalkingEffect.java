package com.rankenstein.origin_items.effects;

import com.rankenstein.origin_items.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AirWalkingEffect extends StatusEffect {
    public AirWalkingEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16251135);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        BlockPos blockPos = entity.getBlockPos();
        BlockState blockState = ModBlocks.HARD_AIR.getDefaultState();
        World world = entity.getWorld();
        float f = Math.min(16, 3);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-f, -1.0, -f), blockPos.add(f, -1.0, f))) {
            if (!blockPos2.isWithinDistance(entity.getPos(), f)) continue;
            mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
            BlockState blockState2 = world.getBlockState(mutable);
            if (!blockState2.isAir() || world.getBlockState(blockPos2).getMaterial() != Material.AIR || !blockState.canPlaceAt(world, blockPos2) || !world.canPlace(blockState, blockPos2, ShapeContext.absent()))
                continue;
            world.setBlockState(blockPos2, blockState);
            world.createAndScheduleBlockTick(blockPos2, ModBlocks.HARD_AIR, MathHelper.nextInt(entity.getRandom(), 60, 120));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }
}
