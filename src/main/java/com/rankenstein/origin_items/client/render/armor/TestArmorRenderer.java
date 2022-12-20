package com.rankenstein.origin_items.client.render.armor;

import com.rankenstein.origin_items.items.custom.EarItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class TestArmorRenderer extends GeoArmorRenderer<EarItem> {
    public TestArmorRenderer() {
        super(new EarArmorModel());

        this.headBone = "armorHead";
    }
}