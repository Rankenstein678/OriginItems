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
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

import java.util.Arrays;
import java.util.List;

public class BlazingMolotovItem extends Item {
    public BlazingMolotovItem(Settings settings) {
        super(settings);
    }

    private static final int RADIUS = OriginItems.CONFIG.blazingMolotovRadius();
    private static final float EXPLOSION_STRENGTH = OriginItems.CONFIG.blazingMolotovExplosionRadius();

    private static final List<Material> DESTROYS = Arrays.asList(Material.AIR, Material.REPLACEABLE_PLANT);

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
                    if(!world.isClient) {
                        HitResult.Type type = hitResult.getType();
                        if (type == HitResult.Type.ENTITY) {
                            Entity entity = ((EntityHitResult) hitResult).getEntity();
                            entity.setOnFireFor(10);
                            this.world.createExplosion(null, getPos().getX(), getPos().getY(), getPos().getZ(), EXPLOSION_STRENGTH, Explosion.DestructionType.NONE);
                            lightArea(entity.getSteppingPos());
                            this.world.emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));
                        } else if (type == HitResult.Type.BLOCK) {
                            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                            BlockPos blockPos = blockHitResult.getBlockPos();
                            this.world.createExplosion(null, blockPos.getX(), blockPos.getY()+1, blockPos.getZ(), EXPLOSION_STRENGTH, Explosion.DestructionType.NONE);

                            lightArea(blockPos);
                            this.world.emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Emitter.of(this, this.world.getBlockState(blockPos)));
                        }

                    }

                    this.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 0.5f, random.nextFloat() * 0.1f + 0.9f);
                    this.discard();
                }


                private void lightArea(BlockPos center) {
                    for (int y = 0; y < 3; y++) {
                        for (int x = -RADIUS; x < RADIUS; x++) {
                            for (int z = -RADIUS; z < RADIUS; z++) {
                                BlockPos pos = center.add(x, y, z);
                                if (Math.sqrt(center.getSquaredDistance(pos)) > RADIUS) continue;
                                BlockState blockState = world.getBlockState(pos);
                                if (DESTROYS.contains(blockState.getMaterial())) {
                                    world.setBlockState(pos, Blocks.FIRE.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                                }
                            }
                        }

                    }
                }
            };
            potionEntity.setItem(itemStack);
            potionEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0f, 0.75f, 1.0f);
            world.spawnEntity(potionEntity);
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
