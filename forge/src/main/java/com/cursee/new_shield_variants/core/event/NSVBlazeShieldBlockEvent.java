package com.cursee.new_shield_variants.core.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVBlazeShieldBlockEvent {
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.ServerTickEvent event) {
        // iterate over players
        // check if blocking with blaze shield
        // set nearby entities on fire
        if ((event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END)) {
            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {


                if (
                    player.isBlocking() &&
                        (
                            (player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().getString().contains("Blaze")) ||
                                (player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().getString().contains("Blaze")
                                )
                        )
                ) {
                    List<LivingEntity> nearby = event.getServer().getLevel(player.level().dimension())
                        .getNearbyEntities(
                            LivingEntity.class,
                            TargetingConditions.forNonCombat(),
                            player,
                            player.getBoundingBox().inflate(3));
                    for (LivingEntity entity : nearby) {
                        entity.setRemainingFireTicks(4*20);
                    }
                }


            }
        }
    }
}
