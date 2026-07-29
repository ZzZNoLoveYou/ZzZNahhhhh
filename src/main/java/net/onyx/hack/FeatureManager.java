package net.onyx.hack;

public class FeatureManager {
    public static boolean espEnabled = false;
    public static boolean hitboxEnabled = false;
    public static boolean killAuraEnabled = false;
    public static float hitboxScale = 1.0f;

    public static void tick() {
        KillAuraFeature.tick();
    }
}
