package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.items.EmptyToolMaterial;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@SuppressWarnings("DataFlowIssue")
public class SpiderFangItem extends SwordItem {

    private static final String NBT_KEY_JUMP = "origin_items.jump";

    public SpiderFangItem(int attackDamage, float attackSpeed, Settings settings) {
        super(new EmptyToolMaterial(), attackDamage, attackSpeed, settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
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
        user.addVelocity(h, k+0.5F, l);
        if(!user.getStackInHand(hand).hasNbt()) user.getStackInHand(hand).setNbt(new NbtCompound());
        user.getStackInHand(hand).getNbt().putBoolean(NBT_KEY_JUMP,true);
        user.getItemCooldownManager().set(this, 40);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(selected && entity instanceof PlayerEntity player&&!OriginUtils.isOfOrigin(player, Constants.ARACHNID)) {
            player.dropItem(stack,false,false);
            player.getInventory().removeStack(slot);
        }
        if(stack.hasNbt()&&stack.getNbt().getBoolean(NBT_KEY_JUMP)&&entity.isOnGround()) {
            stack.getNbt().putBoolean(NBT_KEY_JUMP,false);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(stack.hasNbt()&&stack.getNbt().getBoolean(NBT_KEY_JUMP)) {
            target.damage(DamageSource.mob(attacker), OriginItems.CONFIG.spiderFangsDashDamage());
        }
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,(int)OriginItems.CONFIG.spiderFangsPoisonDuration()*20));

        return super.postHit(stack, target, attacker);
    }
}
