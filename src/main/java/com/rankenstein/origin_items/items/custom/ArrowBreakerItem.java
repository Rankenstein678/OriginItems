package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("description.origin_items.wing_piercer").formatted(Formatting.GOLD));

        } else if (Screen.hasControlDown()) {
            tooltip.add(Text.translatable("flavor.origin_items.wing_piercer").formatted(Formatting.GOLD));
        } else {
            tooltip.add(Text.translatable("status.origin_items.arrow_breaker",getMaxDamage()-stack.getDamage()).formatted(Formatting.WHITE));
            tooltip.add(Text.translatable("misc.origin_items.description").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("misc.origin_items.flavor").formatted(Formatting.AQUA));
        }
    }
}
