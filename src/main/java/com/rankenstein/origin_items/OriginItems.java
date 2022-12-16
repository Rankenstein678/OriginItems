package com.rankenstein.origin_items;

import com.rankenstein.origin_items.blocks.ModBlocks;
import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.items.ModItems;
import net.fabricmc.api.ModInitializer;

public class OriginItems implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();

        ModEffects.registerEffects();
    }
}
