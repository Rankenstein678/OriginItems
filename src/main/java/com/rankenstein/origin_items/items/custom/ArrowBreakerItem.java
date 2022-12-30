package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ArrowBreakerItem extends Item {
    private static final int DURATION = OriginItems.CONFIG.enderReflexesDurationSeconds()*20;
    public ArrowBreakerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!OriginUtils.checkOrigin(Constants.ENDERIAN, world, user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        user.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_REFLEXES, DURATION));
        user.getStackInHand(hand).damage(1, user, e -> e.sendToolBreakStatus(hand));
        user.getItemCooldownManager().set(this,60);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
