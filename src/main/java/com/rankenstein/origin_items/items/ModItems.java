package com.rankenstein.origin_items.items;

import com.rankenstein.origin_items.items.custom.*;
import com.rankenstein.origin_items.util.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {

    public static final Item DEBUG_ITEM = registerItem("debug_item",
            new DebugItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM = registerItem("crafting_item",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ENDERIAN = registerItem("crafting_item_enderian",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_MERLING = registerItem("crafting_item_merling",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_PHANTOM = registerItem("crafting_item_phantom",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ELYTRIAN = registerItem("crafting_item_elytrian",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_BLAZEBORN = registerItem("crafting_item_blazeborn",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_AVIAN = registerItem("crafting_item_avian",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ARACHNID = registerItem("crafting_item_arachnid",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_SHULK = registerItem("crafting_item_shulk",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_FELINE = registerItem("crafting_item_feline",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));

    public static final Item SHULKER_CANNON = registerItem("shulker_cannon",
            new ShulkerCannonItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1)));
    public static final Item BLAZING_MOLOTOV_ITEM = registerItem("blazing_molotov_item",
            new BlazingMolotovItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item WING_PIERCER = registerItem("wing_piercer",
            new WingPiercerItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(465)));

    public static final Item ARROW_BREAKER = registerItem("arrow_breaker",
            new ArrowBreakerItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(4)));
    public static final Item AIR_SIGILL = registerItem("air_sigill",
            new AirSigillItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(6000)));
    public static final Item EARS = registerItem("ears",
            new EarItem(new EarArmorMaterial(), EquipmentSlot.HEAD, new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(25)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }

    public static void registerItems() {
        Constants.LOGGER.debug("Registering Items");
    }
}
