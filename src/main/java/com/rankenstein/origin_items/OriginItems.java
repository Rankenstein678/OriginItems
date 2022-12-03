package com.rankenstein.origin_items;

import com.rankenstein.origin_items.items.ModItems;
import net.fabricmc.api.ModInitializer;

public class OriginItems implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItems.registerItems();
    }
}
