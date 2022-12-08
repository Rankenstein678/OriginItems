package com.rankenstein.origin_items.entities;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class PlayerSpeedBasedArrow extends ArrowEntity {
    private static final int VELOCITY_MULTIPLIER = 12;
    private static final double MINIMAL_DAMAGE_THRESHOLD = 1.5;

    public PlayerSpeedBasedArrow(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        DamageSource damageSource;
        Entity entity2;
        super.onEntityHit(entityHitResult);
        Entity target = entityHitResult.getEntity();
        int damage;
        if (getOwner() instanceof PlayerEntity player) {
            damage = MathHelper.ceil(player.getVelocity().length() * VELOCITY_MULTIPLIER);
            if(player.getVelocity().length()< MINIMAL_DAMAGE_THRESHOLD) {
                damage = 1;
            }
        } else {
            float f = (float) this.getVelocity().length();
            damage = MathHelper.ceil(MathHelper.clamp((double) f * 2.0, 0.0, 2.147483647E9));
        }
        if (this.isCritical()) {
            long l = this.random.nextInt(damage / 2 + 2);
            damage = (int) Math.min(l + (long) damage, Integer.MAX_VALUE);
        }
        if ((entity2 = this.getOwner()) == null) {
            damageSource = DamageSource.arrow(this, this);
        } else {
            damageSource = DamageSource.arrow(this, entity2);
            if (entity2 instanceof LivingEntity) {
                ((LivingEntity) entity2).onAttacking(target);
            }
        }
        boolean bl = target.getType() == EntityType.ENDERMAN;
        int j = target.getFireTicks();
        if (this.isOnFire() && !bl) {
            target.setOnFireFor(5);
        }
        if (target.damage(damageSource, damage)) {
            if (bl) {
                return;
            }
            if (target instanceof LivingEntity livingEntity) {
                if (!this.world.isClient && this.getPierceLevel() <= 0) {
                    livingEntity.setStuckArrowCount(livingEntity.getStuckArrowCount() + 1);
                }
                if (!this.world.isClient && entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity) entity2, livingEntity);
                }
                this.onHit(livingEntity);
                if (livingEntity != entity2 && livingEntity instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity) entity2).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, GameStateChangeS2CPacket.DEMO_OPEN_SCREEN));
                }
                if (!this.world.isClient && entity2 instanceof ServerPlayerEntity serverPlayerEntity) {
                    if (!target.isAlive() && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, List.of(target));
                    }
                }
            }
            this.playSound(this.getSound(), 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            target.setFireTicks(j);
            this.setVelocity(this.getVelocity().multiply(-0.1));
            this.setYaw(this.getYaw() + 180.0f);
            this.prevYaw += 180.0f;
            if (!this.world.isClient && this.getVelocity().lengthSquared() < 1.0E-7) {
                if (this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1f);
                }
                this.discard();
            }
        }
    }


}
