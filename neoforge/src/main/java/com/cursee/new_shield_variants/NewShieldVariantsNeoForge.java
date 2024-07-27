package com.cursee.new_shield_variants;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.new_shield_variants.item.ModItemProperties;
import com.cursee.new_shield_variants.registry.GlobalRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(Constants.MOD_ID)
public class NewShieldVariantsNeoForge {

    public NewShieldVariantsNeoForge(IEventBus eventBus) {

        NewShieldVariants.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        GlobalRegistry.register(eventBus);
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            event.enqueueWork(() -> {
                ModItemProperties.addCustomItemProperties();
            });
        }
    }
}
