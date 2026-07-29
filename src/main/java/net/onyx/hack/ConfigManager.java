package net.onyx.hack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {
    private static final Path CONFIG_PATH = Paths.get("config/onyxhack.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        ConfigData data = new ConfigData();
        data.espEnabled = FeatureManager.espEnabled;
        data.hitboxEnabled = FeatureManager.hitboxEnabled;
        data.killAuraEnabled = FeatureManager.killAuraEnabled;
        data.hitboxScale = FeatureManager.hitboxScale;
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) { GSON.toJson(data, writer); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) { save(); return; }
        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            if (data == null) return;
            FeatureManager.espEnabled = data.espEnabled;
            FeatureManager.hitboxEnabled = data.hitboxEnabled;
            FeatureManager.killAuraEnabled = data.killAuraEnabled;
            FeatureManager.hitboxScale = data.hitboxScale;
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static class ConfigData {
        boolean espEnabled = false;
        boolean hitboxEnabled = false;
        boolean killAuraEnabled = false;
        float hitboxScale = 1.0f;
    }
}
