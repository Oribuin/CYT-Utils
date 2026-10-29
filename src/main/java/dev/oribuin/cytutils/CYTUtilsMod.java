package dev.oribuin.cytutils;

import dev.oribuin.cytutils.config.SettingsConfig;
import me.lilac.camellia.config.Config;
import me.lilac.camellia.config.ConfigHolder;
import me.lilac.camellia.sync.ConfigSync;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.io.File;
import java.io.IOException;

import static net.fabricmc.fabric.api.resource.v1.pack.PackActivationType.ALWAYS_ENABLED;
import static net.fabricmc.fabric.api.resource.v1.pack.PackActivationType.DEFAULT_ENABLED;
import static net.fabricmc.fabric.api.resource.v1.pack.PackActivationType.NORMAL;

public class CYTUtilsMod implements ClientModInitializer, ConfigHolder {

    public static final String MOD_ID = "cyt-utils";
    private static CYTUtilsMod instance;
    private Config config;

    /**
     * Runs the mod initialiser on the client environment.
     */
    @Override
    public void onInitializeClient() {
        instance = this;
        this.config = this.getOrCreateConfig();
        ConfigSync.init(SettingsConfig.INSTANCE); // Required to sync the config.

        // Disable Chat "welcome" message
//        ServerMessageEvents.GAME_MESSAGE.register((server, message, overlay) -> {
//            if (message.)
//        });
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            // Register Resource Packs
            if (SettingsConfig.LEGACY_FISH.get()) {
                ResourceLoader.registerBuiltinPack(
                        asId("cyt-fish"),
                        container,
                        Component.literal("Legacy Fish Textures"),
                        ALWAYS_ENABLED
                );
            }
            
            if (SettingsConfig.NORMAL_HEADS.get()) {
                ResourceLoader.registerBuiltinPack(
                        asId("3d-heads"),
                        container,
                        Component.literal("3D Player Heads"),
                        ALWAYS_ENABLED
                );
            }
        });
    }

    public static CYTUtilsMod getInstance() {
        return instance;
    }

    @Override
    public Config getOrCreateConfig() {
        if (this.config != null)
            return this.config;

        File folder = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID).toFile();
        folder.mkdirs();

        File file = new File(folder, "cyt-utils.json"); // The name of the config file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return Config.builder(file).settings(SettingsConfig.INSTANCE).build();
    }

    public static Identifier asId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public PackActivationType getType(boolean enabled) {
        return enabled ? DEFAULT_ENABLED : NORMAL;
    }
}
