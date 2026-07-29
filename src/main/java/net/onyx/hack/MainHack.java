package net.onyx.hack;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class MainHack implements ClientModInitializer {
    public static MinecraftClient client;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();

        KeyBinding menuKey = new KeyBinding(
            "key.onyxhack.menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.onyxhack.general"
        );
        KeyBindingHelper.registerKeyBinding(menuKey);

        KeyBindings.register();
        ConfigManager.load();

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            if (menuKey.wasPressed() && MinecraftClient.getInstance().currentScreen == null) {
                MinecraftClient.getInstance().setScreen(new ModMenuScreen());
            }
            if (c.player != null && c.world != null) {
                FeatureManager.tick();
            }
        });
    }
}
