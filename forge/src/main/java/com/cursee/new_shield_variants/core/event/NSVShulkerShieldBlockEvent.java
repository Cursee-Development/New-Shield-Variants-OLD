package com.cursee.new_shield_variants.core.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVShulkerShieldBlockEvent {

    @SubscribeEvent
    public static void shieldBlockEvent(TickEvent.PlayerTickEvent event) {
//        NewShieldVariants.LOGGER.info("\n event.getEntity getDisplayName: \n" + event.getEntity().getDisplayName().toString()); // contains Dev
//        NewShieldVariants.LOGGER.info("\n event.getDamageSource getEntity getDisplayName: \n" + event.getDamageSource().getEntity().getDisplayName().toString());

        if (
            ( event.player.isBlocking() &&
                event.player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Shulker Shield")))
        ) {
            event.player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
            event.player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
//            event.player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(event.player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() - 20);
        }
        if (
            ( event.player.isBlocking() &&
                event.player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Shulker Shield")) )
        ) {
            event.player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
            event.player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
//            event.player.getItemInHand(InteractionHand.OFF_HAND).setDamageValue(event.player.getItemInHand(InteractionHand.OFF_HAND).getDamageValue() - 20);


        }

    }
}
