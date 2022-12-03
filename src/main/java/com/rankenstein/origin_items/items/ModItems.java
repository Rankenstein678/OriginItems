package com.rankenstein.origin_items.items;

import com.rankenstein.origin_items.items.custom.BlazingMolotovItem;
import com.rankenstein.origin_items.items.custom.CraftingItem;
import com.rankenstein.origin_items.items.custom.DebugItem;
import com.rankenstein.origin_items.items.custom.ShulkerCannonItem;
import com.rankenstein.origin_items.util.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {

    public static final Item DEBUG_ITEM = registerItem("debug_item",
            new DebugItem(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item SHULKER_CANNON = registerItem("shulker_cannon",
            new ShulkerCannonItem(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item CRAFTING_ITEM = registerItem("crafting_item",
            new CraftingItem(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_ENDERIAN = registerItem("crafting_item_enderian",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_MERLING = registerItem("crafting_item_merling",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_PHANTOM = registerItem("crafting_item_phantom",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_ELYTRIAN = registerItem("crafting_item_elytrian",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_BLAZEBORN = registerItem("crafting_item_blazeborn",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_AVIAN = registerItem("crafting_item_avian",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_ARACHNID = registerItem("crafting_item_arachnid",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_SHULK = registerItem("crafting_item_shulk",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item CRAFTING_ITEM_FELINE = registerItem("crafting_item_feline",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item BLAZING_MOLOTOV_ITEM = registerItem("blazing_molotov_item",
            new BlazingMolotovItem(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }

    public static void registerItems() {
        Constants.LOGGER.debug("Registering Items");
    }
}
