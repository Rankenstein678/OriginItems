package com.rankenstein.origin_items.client.render.armor;


import com.rankenstein.origin_items.items.custom.EarItem;
import com.rankenstein.origin_items.util.Constants;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EarArmorModel extends AnimatedGeoModel<EarItem> {

    @Override
    public Identifier getAnimationResource(EarItem animatable) {
        return new Identifier(Constants.MOD_ID, "animations/empty.json");
    }

    @Override
    public Identifier getModelResource(EarItem object) {
        return new Identifier(Constants.MOD_ID,"geo/ears.geo.json");
    }

    @Override
    public Identifier getTextureResource(EarItem object) {
        return new Identifier(Constants.MOD_ID, "textures/models/armor/ears_texture.png");    }
}
