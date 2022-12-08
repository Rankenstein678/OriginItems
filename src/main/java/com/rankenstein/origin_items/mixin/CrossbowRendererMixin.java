package com.rankenstein.origin_items.mixin;

import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.items.custom.WingPiercerItem;
import com.rankenstein.origin_items.util.Constants;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class CrossbowRendererMixin {
    @Inject(method= "getArmPose" ,at=@At("HEAD"), cancellable = true)
    private static void injected(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (!player.handSwinging && player.getStackInHand(hand).isOf(ModItems.WING_PIERCER) && WingPiercerItem.isCharged(player.getStackInHand(hand))) {
            Constants.LOGGER.debug("Entered Mixin");
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
