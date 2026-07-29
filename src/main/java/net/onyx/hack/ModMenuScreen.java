package net.onyx.hack;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModMenuScreen extends Screen {
    private static final int WIDTH = 320;
    private static final int HEIGHT = 350;
    private int left, top;

    public ModMenuScreen() {
        super(Text.literal("Onyx Hack"));
    }

    @Override
    protected void init() {
        super.init();
        this.left = (this.width - WIDTH) / 2;
        this.top = (this.height - HEIGHT) / 2;

        ButtonWidget espToggle = ButtonWidget.builder(
            Text.literal("ESP: " + (FeatureManager.espEnabled ? "ON" : "OFF")),
            b -> {
                FeatureManager.espEnabled = !FeatureManager.espEnabled;
                b.setMessage(Text.literal("ESP: " + (FeatureManager.espEnabled ? "ON" : "OFF")));
                ConfigManager.save();
            }
        ).dimensions(left + 20, top + 40, 120, 20).build();
        this.addDrawableChild(espToggle);

        ButtonWidget hitboxToggle = ButtonWidget.builder(
            Text.literal("Hitbox: " + (FeatureManager.hitboxEnabled ? "ON" : "OFF")),
            b -> {
                FeatureManager.hitboxEnabled = !FeatureManager.hitboxEnabled;
                b.setMessage(Text.literal("Hitbox: " + (FeatureManager.hitboxEnabled ? "ON" : "OFF")));
                ConfigManager.save();
            }
        ).dimensions(left + 20, top + 70, 120, 20).build();
        this.addDrawableChild(hitboxToggle);

        ButtonWidget killAuraToggle = ButtonWidget.builder(
            Text.literal("KillAura: " + (FeatureManager.killAuraEnabled ? "ON" : "OFF")),
            b -> {
                FeatureManager.killAuraEnabled = !FeatureManager.killAuraEnabled;
                b.setMessage(Text.literal("KillAura: " + (FeatureManager.killAuraEnabled ? "ON" : "OFF")));
                ConfigManager.save();
            }
        ).dimensions(left + 20, top + 100, 120, 20).build();
        this.addDrawableChild(killAuraToggle);

        ButtonWidget closeBtn = ButtonWidget.builder(
            Text.literal("Close"),
            b -> this.close()
        ).dimensions(left + WIDTH - 60, top + HEIGHT - 30, 40, 20).build();
        this.addDrawableChild(closeBtn);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawText(this.textRenderer, "Onyx Hack", left + WIDTH/2 - 40, top + 12, 0xFFFFFF, true);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
