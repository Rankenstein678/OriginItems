package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShulkerCannonItem extends Item {
    private static final int COOLDOWN_HIT = (int) OriginItems.CONFIG.shulkerCannonCooldownHitSeconds() * 20;
    private static final int COOLDOWN_MISS = (int) OriginItems.CONFIG.shulkerCannonCooldownMissSeconds() * 20;
    private static final double RANGE = OriginItems.CONFIG.shulkerCannonRange();

    public ShulkerCannonItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!OriginUtils.checkOrigin(Constants.SHULK, world, user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        if (hand == Hand.MAIN_HAND) {
            EntityHitResult hit = raycast(user);
            if (hit != null && hit.getEntity() instanceof LivingEntity) {
                Entity target = hit.getEntity();
                if (!world.isClient) {
                    world.spawnEntity(new ShulkerBulletEntity(world, user, target, null));
                    user.playSound(SoundEvents.ENTITY_SHULKER_SHOOT, SoundCategory.PLAYERS, 2.0f, world.getRandom().nextFloat() - world.getRandom().nextFloat() * 0.2f + 1.0f);
                    user.getItemCooldownManager().set(this, COOLDOWN_HIT);
                }
            } else {
                if (world.isClient) {
                    user.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
                user.getItemCooldownManager().set(this, COOLDOWN_MISS);
            }
        }
        return super.use(world, user, hand);
    }

    public static EntityHitResult raycast(Entity source) {
        Vec3d origin = new Vec3d(source.getX(), source.getEyeY(), source.getZ());
        Vec3d direction = source.getRotationVec(1);
        Vec3d target = origin.add(direction.multiply(RANGE));

        Vec3d ray = target.subtract(origin);
        Box box = source.getBoundingBox().stretch(ray).expand(1.0D, 1.0D, 1.0D);
        return ProjectileUtil.raycast(source, origin, target, box, (entityx) -> !entityx.isSpectator(), ray.lengthSquared());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("description.origin_items.shulker_cannon").formatted(Formatting.GOLD));

        } else if (Screen.hasControlDown()) {
            tooltip.add(Text.translatable("flavor.origin_items.shulker_cannon").formatted(Formatting.GOLD));
        } else {
            tooltip.add(Text.translatable("misc.origin_items.description").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("misc.origin_items.flavor").formatted(Formatting.AQUA));
        }
    }

}

