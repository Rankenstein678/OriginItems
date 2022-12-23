package com.rankenstein.origin_items.client;

import com.rankenstein.origin_items.blocks.ModBlocks;
import com.rankenstein.origin_items.client.render.armor.TestArmorRenderer;
import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.items.custom.WingPiercerItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Environment(EnvType.CLIENT)
public class OriginItemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARD_AIR, RenderLayer.getTranslucent());
        createModelPredicates();
        GeoArmorRenderer.registerArmorRenderer(new TestArmorRenderer(),ModItems.EARS);
    }

    private static void createModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.WING_PIERCER, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (WingPiercerItem.isCharged(stack)) {
                return 0.0f;
            }
            return (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float) WingPiercerItem.getPullTime(stack);
        });
        ModelPredicateProviderRegistry.register(ModItems.WING_PIERCER, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !WingPiercerItem.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(ModItems.WING_PIERCER, new Identifier("charged"), (stack, world, entity, seed) -> entity != null && WingPiercerItem.isCharged(stack) ? 1.0f : 0.0f);
    }
}
