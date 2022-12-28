package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Arrays;
import java.util.List;

public class BlazingMolotovItem extends Item {
    public BlazingMolotovItem(Settings settings) {
        super(settings);
    }

    public static final int RANGE = 5;
    public static final List<Material> DESTROYS = Arrays.asList(Material.AIR, Material.REPLACEABLE_PLANT);

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!OriginUtils.checkOrigin(Constants.BLAZEBORN, world, user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            PotionEntity potionEntity = new PotionEntity(world, user) {

                @Override
                protected void onCollision(HitResult hitResult) {
                    HitResult.Type type = hitResult.getType();
                    if (type == HitResult.Type.ENTITY) {
                        Entity entity = ((EntityHitResult) hitResult).getEntity();
                        entity.setOnFireFor(10);
                        lightArea(entity.getSteppingPos(), 1);

                        this.onEntityHit((EntityHitResult) hitResult);
                        this.world.emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));
                    } else if (type == HitResult.Type.BLOCK) {
                        BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                        this.onBlockHit(blockHitResult);
                        BlockPos blockPos = blockHitResult.getBlockPos();
                        this.world.emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Emitter.of(this, this.world.getBlockState(blockPos)));
                    }

                    this.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 0.5f, random.nextFloat() * 0.1f + 0.9f);
                    this.discard();
                }

                @Override
                protected void onBlockHit(BlockHitResult blockHitResult) {
                    BlockPos pos = blockHitResult.getBlockPos();
                    Direction side = blockHitResult.getSide();
                    if (side == Direction.UP) {
                        lightArea(pos, 1);
                    } else if (side == Direction.DOWN) {
                        lightArea(pos, -1);
                    } else {
                        lightArea(pos, 0);
                    }
                }


                private void lightArea(BlockPos center, int yOffset) {
                    int TEMPORARYRANGE = OriginItems.CONFIG.blazingMolotovRadius();
                    for (int x = -TEMPORARYRANGE; x < TEMPORARYRANGE; x++) {
                        for (int z = -TEMPORARYRANGE; z < TEMPORARYRANGE; z++) {
                            BlockPos pos = center.add(x, yOffset, z);
                            if (center.getSquaredDistance(pos) > TEMPORARYRANGE) continue;
                            BlockState blockState = world.getBlockState(pos);
                            if (DESTROYS.contains(blockState.getMaterial())) {
                                world.setBlockState(pos, Blocks.FIRE.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                            }
                        }
                    }
                }
            };
            potionEntity.setItem(itemStack);
            //Todo: change Velocity?
            potionEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0f, 0.5f, 1.0f);
            world.spawnEntity(potionEntity);
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
