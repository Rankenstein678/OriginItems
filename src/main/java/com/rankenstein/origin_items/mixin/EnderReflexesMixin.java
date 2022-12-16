package com.rankenstein.origin_items.mixin;

import com.rankenstein.origin_items.effects.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class EnderReflexesMixin extends Entity {

    private static final double RANGE = 5.0;

    public EnderReflexesMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (((PlayerEntity) (Object) this).getActiveStatusEffects().containsKey(ModEffects.ENDER_REFLEXES) && source instanceof ProjectileDamageSource) {
            Entity entity = source.getSource();
            if (!(entity instanceof PotionEntity)) {
                for (int i = 0; i < Math.min(RANGE * RANGE + 20, 64); ++i) {
                    if (this.teleportRandomly()) {
                        cir.setReturnValue(true);
                        break;
                    }
                }
            }
        }
    }

    protected boolean teleportRandomly() {
        PlayerEntity thisObj = ((PlayerEntity) (Object) this);
        if (thisObj.world.isClient() || !thisObj.isAlive()) {
            return false;
        }
        double d = thisObj.getX() + (this.random.nextDouble() - 0.5) * RANGE;
        double e = thisObj.getY() + (double) (this.random.nextInt(3) * this.random.nextInt(1) > 0 ? -1 : 1);
        double f = thisObj.getZ() + (this.random.nextDouble() - 0.5) * RANGE;
        return this.teleportTo(d, e, f);
    }

    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);
        while (mutable.getY() > this.world.getBottomY() && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }
        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (!bl || bl2) {
            return false;
        }
        Vec3d vec3d = this.getPos();
        boolean bl3 = (((LivingEntity) (Object) this)).teleport(x, y, z, true);
        if (bl3) {
            this.world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
            if (!this.isSilent()) {
                this.world.playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0f, 1.0f);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
        return bl3;
    }
}
