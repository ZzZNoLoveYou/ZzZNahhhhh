package net.onyx.hack;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding espKey;
    public static KeyBinding hitboxKey;
    public static KeyBinding killAuraKey;

    public static void register() {
        espKey = new KeyBinding("key.onyxhack.esp", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.onyxhack.general");
        KeyBindingHelper.registerKeyBinding(espKey);
        hitboxKey = new KeyBinding("key.onyxhack.hitbox", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.onyxhack.general");
        KeyBindingHelper.registerKeyBinding(hitboxKey);
        killAuraKey = new KeyBinding("key.onyxhack.killaura", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.onyxhack.general");
        KeyBindingHelper.registerKeyBinding(killAuraKey);

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            while (espKey.wasPressed()) { FeatureManager.espEnabled = !FeatureManager.espEnabled; ConfigManager.save(); }
            while (hitboxKey.wasPressed()) { FeatureManager.hitboxEnabled = !FeatureManager.hitboxEnabled; ConfigManager.save(); }
            while (killAuraKey.wasPressed()) { FeatureManager.killAuraEnabled = !FeatureManager.killAuraEnabled; ConfigManager.save(); }
        });
    }
}
