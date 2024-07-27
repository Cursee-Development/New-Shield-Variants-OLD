package com.cursee.new_shield_variants.core.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVRedstoneShieldBlockEvent {

    @SubscribeEvent
    public static void shieldBlockEvent(TickEvent.ServerTickEvent event) {
//        NewShieldVariants.LOGGER.info("\n event.getEntity getDisplayName: \n" + event.getEntity().getDisplayName().toString()); // contains Dev
//        NewShieldVariants.LOGGER.info("\n event.getDamageSource getEntity getDisplayName: \n" + event.getDamageSource().getEntity().getDisplayName().toString());

        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (
                (player.isBlocking() &&
                    player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Redstone Shield")))
            ) {

                if (player.level().getBlockState(player.blockPosition()).hasProperty(BlockStateProperties.POWER)) {
                    player.level().setBlock(player.blockPosition(), player.getBlockStateOn().cycle(BlockStateProperties.POWER), 3);
                }


//                Stream<BlockState> blockstates =  player.level().getBlockStates(player.getBoundingBox().inflate(2));
//                for (BlockState blockstate : blockstates.toList()) {
//                    if (blockstate.hasProperty(BlockStateProperties.POWERED)) {
//                        blockstate.setValue(BlockStateProperties.POWERED, true);
//                    }
//                    if (blockstate.hasProperty(BlockStateProperties.POWER)) {
//                        blockstate.setValue(BlockStateProperties.POWER, 15);
//                    }
//                }

                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() - 20);
            }

            if (
                (player.isBlocking() &&
                    player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Redstone Shield")))
            ) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
                player.getItemInHand(InteractionHand.OFF_HAND).setDamageValue(player.getItemInHand(InteractionHand.OFF_HAND).getDamageValue() - 20);


            }
        }

    }
}
