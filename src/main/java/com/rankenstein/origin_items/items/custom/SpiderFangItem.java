package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.items.EmptyToolMaterial;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("DataFlowIssue")
public class SpiderFangItem extends SwordItem {

    private static final String NBT_KEY_JUMP = "origin_items.jump";
    private static final float ADDITIONAL_DAMAGE = OriginItems.CONFIG.spiderFangsDashDamage();
    private static final int DURATION = (int) OriginItems.CONFIG.spiderFangsPoisonDuration() * 20;

    public SpiderFangItem(int attackDamage, float attackSpeed, Settings settings) {
        super(new EmptyToolMaterial(), attackDamage, attackSpeed, settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!stack.hasNbt()) stack.setNbt(new NbtCompound());
        if (!stack.getNbt().getBoolean(NBT_KEY_JUMP)) {
            float f = user.getYaw();
            float g = user.getPitch();
            float h = -MathHelper.sin(f * (float) (Math.PI / 180.0)) * MathHelper.cos(g * (float) (Math.PI / 180.0));
            float k = -MathHelper.sin(g * (float) (Math.PI / 180.0));
            float l = MathHelper.cos(f * (float) (Math.PI / 180.0)) * MathHelper.cos(g * (float) (Math.PI / 180.0));
            float m = MathHelper.sqrt(h * h + k * k + l * l);
            float n = 3.0F * ((1.0F + 1) / 4.0F);
            h *= n / m;
            k *= n / m;
            l *= n / m;
            user.addVelocity(h, k + 0.5F, l);
            user.getStackInHand(hand).getNbt().putBoolean(NBT_KEY_JUMP, true);
            user.getItemCooldownManager().set(this, 40);
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.fail(stack);

    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && entity instanceof PlayerEntity player && !OriginUtils.isOfOrigin(player, Constants.ARACHNID)) {
            player.dropItem(stack, false, false);
            player.getInventory().removeStack(slot);
        }
        if (stack.hasNbt() && stack.getNbt().getBoolean(NBT_KEY_JUMP) && entity.isOnGround()) {
            stack.getNbt().putBoolean(NBT_KEY_JUMP, false);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.hasNbt() && stack.getNbt().getBoolean(NBT_KEY_JUMP)) {
            target.damage(DamageSource.mob(attacker), ADDITIONAL_DAMAGE);
        }
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, DURATION));

        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("description.origin_items.spider_fangs").formatted(Formatting.GOLD));

        } else if (Screen.hasControlDown()) {
            tooltip.add(Text.translatable("flavor.origin_items.spider_fangs").formatted(Formatting.GOLD));
        } else {
            tooltip.add(Text.translatable("misc.origin_items.description").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("misc.origin_items.flavor").formatted(Formatting.AQUA));
        }
    }

}
