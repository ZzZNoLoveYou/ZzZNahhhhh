package net.onyx.hack;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import java.util.Comparator;

public class KillAuraFeature {
    public static void tick() {
        if (!FeatureManager.killAuraEnabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || client.interactionManager == null) return;

        client.world.getPlayers().stream()
            .filter(p -> p != client.player && p.isAlive() && client.player.distanceTo(p) < 6.0)
            .min(Comparator.comparingDouble(p -> client.player.distanceTo(p)))
            .ifPresent(target -> {
                client.interactionManager.attackEntity(client.player, target);
                client.player.swingHand(Hand.MAIN_HAND);
            });
    }
}
