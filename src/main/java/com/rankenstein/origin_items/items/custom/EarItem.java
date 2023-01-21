package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class EarItem extends ArmorItem implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public EarItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!OriginUtils.checkOrigin(Constants.FELINE, world, user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("description.origin_items.ears").formatted(Formatting.GOLD));

        } else if (Screen.hasControlDown()) {
            tooltip.add(Text.translatable("flavor.origin_items.ears").formatted(Formatting.GOLD));
        } else {
            tooltip.add(Text.translatable("misc.origin_items.description").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("misc.origin_items.flavor").formatted(Formatting.AQUA));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && stack == player.getInventory().getArmorStack(3)) {
            if (OriginUtils.isOfOrigin(player, Constants.FELINE)) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.QUESTIONABLE_FASHION_CHOICE, 200, 0, false, false, true));
            } else {
                int emptySlot = player.getInventory().getEmptySlot();
                if (emptySlot != -1) {
                    player.getInventory().insertStack(slot, stack);
                } else {
                    player.dropItem(stack, true);
                    player.getInventory().removeStack(slot);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.STOP;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}