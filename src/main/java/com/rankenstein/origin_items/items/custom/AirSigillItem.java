package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("DataFlowIssue")
public class AirSigillItem extends Item {
    private static final String NBT_KEY_ACTIVE = "origin_items.air_sigill.activated";

    public AirSigillItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!OriginUtils.checkOrigin(Constants.AVIAN, world, user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        ItemStack stack = user.getStackInHand(hand);
        if (!stack.hasNbt()) {
            stack.setNbt(new NbtCompound());
        }
        if (stack.getNbt().getBoolean(NBT_KEY_ACTIVE)) {
            user.removeStatusEffect(ModEffects.AIR_WALKING);
            stack.getNbt().putBoolean(NBT_KEY_ACTIVE, false);
        } else {
            user.setStatusEffect(new StatusEffectInstance(ModEffects.AIR_WALKING, stack.getMaxDamage() - stack.getDamage()), user);
            stack.getNbt().putBoolean(NBT_KEY_ACTIVE, true);
        }
        user.getItemCooldownManager().set(this, 100);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (!stack.hasNbt()) return false;
        return stack.getNbt().getBoolean(NBT_KEY_ACTIVE);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (entity instanceof PlayerEntity player && stack.getNbt().getBoolean(NBT_KEY_ACTIVE)) {
                if (selected || player.getStackInHand(Hand.OFF_HAND).equals(stack)) {
                    stack.damage(1, player, p -> p.sendToolBreakStatus(selected ? Hand.MAIN_HAND : Hand.OFF_HAND));//selected ? Hand.MAIN_HAND : Hand.OFF_HAND));
                } else {
                    player.removeStatusEffect(ModEffects.AIR_WALKING);
                    stack.getNbt().putBoolean(NBT_KEY_ACTIVE, false);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("description.origin_items.wing_piercer").formatted(Formatting.GOLD));

            } else if (Screen.hasControlDown()) {
                tooltip.add(Text.translatable("flavor.origin_items.wing_piercer").formatted(Formatting.GOLD));
            } else {
                tooltip.add(Text.translatable("status.origin_items.air_sigill", (stack.getMaxDamage() - stack.getDamage()) / 20));
                tooltip.add(Text.translatable("misc.origin_items.description").formatted(Formatting.YELLOW));
                tooltip.add(Text.translatable("misc.origin_items.flavor").formatted(Formatting.AQUA));

        }
    }


}
