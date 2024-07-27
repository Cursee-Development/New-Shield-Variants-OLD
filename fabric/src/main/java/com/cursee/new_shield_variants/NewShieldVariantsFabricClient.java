package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.NewShieldVariantsRegistryFabric;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class NewShieldVariantsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerProperties(NewShieldVariantsRegistryFabric.STONE_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.IRON_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.GOLD_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.DIAMOND_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.NETHERITE_SHIELD);

        registerProperties(NewShieldVariantsRegistryFabric.ENDER_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.BLAZE_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.FIRE_CHARGE_SHIELD);

        registerProperties(NewShieldVariantsRegistryFabric.SHULKER_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.DRAGON_HEAD_SHIELD);
        registerProperties(NewShieldVariantsRegistryFabric.TNT_SHIELD);

        registerProperties(NewShieldVariantsRegistryFabric.REDSTONE_SHIELD);
    }


    public static void registerProperties(Item item){
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, p_174578_) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}
