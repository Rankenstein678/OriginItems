package com.rankenstein.origin_items.mixin;

import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.items.custom.WingPiercerItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    private static boolean isChargedCrossbow(ItemStack stack) {
        return stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) ||
                (stack.isOf(ModItems.WING_PIERCER) && WingPiercerItem.isCharged(stack));
    }

    @Inject(method = "getHandRenderType", at = @At("HEAD"), cancellable = true)
    private static void injected(ClientPlayerEntity player, CallbackInfoReturnable<HeldItemRenderer.HandRenderType> cir) {
        ItemStack stack = player.getMainHandStack();
        if (isChargedCrossbow(stack)) {
            cir.setReturnValue(HeldItemRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY);
        }
    }

    @Inject(method = "getUsingItemHandRenderType", at = @At("HEAD"), cancellable = true)
    private static void injected1(ClientPlayerEntity player, CallbackInfoReturnable<HeldItemRenderer.HandRenderType> cir) {
        ItemStack itemStack = player.getActiveItem();
        Hand hand = player.getActiveHand();
        if (itemStack.isOf(Items.BOW) || itemStack.isOf(Items.CROSSBOW) || itemStack.isOf(ModItems.WING_PIERCER)) {
            cir.setReturnValue(HeldItemRenderer.HandRenderType.shouldOnlyRender(hand));
        }
        cir.setReturnValue(hand == Hand.MAIN_HAND && isChargedCrossbow(player.getOffHandStack()) ? HeldItemRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY : HeldItemRenderer.HandRenderType.RENDER_BOTH_HANDS);
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER))
    private void injected4(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!item.isEmpty() && item.isOf(ModItems.WING_PIERCER)) {
            Arm arm = hand == Hand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
            int i;
            WingPiercerItem.isCharged(item);
            boolean bl3 = arm == Arm.RIGHT;
            i = bl3 ? 1 : -1;
            if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
                applyEquipOffset(matrices, arm, equipProgress);
                matrices.translate((float) i * -0.4785682f, -0.094387f, 0.05731530860066414);
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-11.935f));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((float) i * 65.3f));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion((float) i * -9.785f));
                float f = (float) item.getMaxUseTime() - ((float) player.getItemUseTimeLeft() - tickDelta + 1.0f);
                float g = f / (float) WingPiercerItem.getPullTime(item);
                if (g > 1.0f) {
                    g = 1.0f;
                }
                if (g > 0.1f) {
                    float h = MathHelper.sin((f - 0.1f) * 1.3f);
                    float j = g - 0.1f;
                    float k = h * j;
                    matrices.translate(k * 0.0f, k * 0.004f, k * 0.0f);
                }
                matrices.translate(g * 0.0f, g * 0.0f, g * 0.04f);
                matrices.scale(1.0f, 1.0f, 1.0f + g * 0.2f);
                matrices.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion((float) i * 45.0f));
            }


        }
    }
    private void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((float)i * 0.56f, -0.52f + equipProgress * -0.6f, -0.72f);
    }
}
