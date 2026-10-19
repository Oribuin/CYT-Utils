package dev.oribuin.cytutils.config;

import com.mojang.serialization.Codec;
import dev.oribuin.cytutils.CYTUtilsMod;
import me.lilac.camellia.config.Setting;
import me.lilac.camellia.config.SettingBuilder;
import me.lilac.camellia.config.SettingHolder;
import me.lilac.camellia.config.Util;
import me.lilac.camellia.input.CheckBoxInput;
import me.lilac.camellia.input.ColorInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SettingsConfig implements SettingHolder {

    public static final SettingHolder INSTANCE = new SettingsConfig();
    private static final List<Setting<?>> SETTINGS = new ArrayList<>();

    public static final Setting<Boolean> HIGHLIGHT_ITEMS = create(SettingBuilder.of(
                    "highlight-items", Codec.BOOL, true
            )
            .tab("client")
            .comment("Should CYT Items be highlighted in the inventory?")
            .input(new CheckBoxInput<>()));

    public static final Setting<Boolean> PREVENT_TOOL_DROP = create(SettingBuilder.of(
                    "prevent-tool-drop", Codec.BOOL, true
            )
            .tab("client")
            .comment("Should we prevent you from dropping your tools?")
            .input(new CheckBoxInput<>()));

    public static final Setting<Boolean> NORMAL_HEADS = create(SettingBuilder.of(
                    "3d-heads", Codec.BOOL, true
            )
            .tab("client")
            .comment("You can always turn this off manually, This option will just add it to your resource packs automatically")
            .input(new CheckBoxInput<>()));

    // region Fish Colours

    public static final Setting<Boolean> LEGACY_FISH = create(SettingBuilder.of(
                    "legacy-fish", Codec.BOOL, false
            )
            .tab("client")
            .comment("You can always turn this off manually, This option will just add it to your resource packs automatically")
            .section("pyrofishing")
            .input(new CheckBoxInput<>()));

    public static final Setting<Color> FISH_BRONZE = create(SettingBuilder.of(
                    "fish_bronze", Util.HEX_COLOR_CODEC, decode("#d9941d", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_SILVER = create(SettingBuilder.of(
                    "fish_silver", Util.HEX_COLOR_CODEC, decode("#555555", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_GOLD = create(SettingBuilder.of(
                    "fish_gold", Util.HEX_COLOR_CODEC, decode("#fcdb2b", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_DIAMOND = create(SettingBuilder.of(
                    "fish_diamond", Util.HEX_COLOR_CODEC, decode("#18cde4", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_PLATINUM = create(SettingBuilder.of(
                    "fish_platinum", Util.HEX_COLOR_CODEC, decode("#9b85b0", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_MYTHIC = create(SettingBuilder.of(
                    "fish_mythic", Util.HEX_COLOR_CODEC, decode("#c949ff", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_AUGMENT = create(SettingBuilder.of(
                    "fish_augment", Util.HEX_COLOR_CODEC, decode("#83c9d4", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_CRAB = create(SettingBuilder.of(
                    "fish_crab", Util.HEX_COLOR_CODEC, decode("#cf2d2d", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_SQUID = create(SettingBuilder.of(
                    "fish_squid", Util.HEX_COLOR_CODEC, decode("#5460d1", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_DOLPHIN = create(SettingBuilder.of(
                    "fish_dolphin", Util.HEX_COLOR_CODEC, decode("#5460d1", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));


    // endregion

    /**
     * Decode a color with an assigned opacity
     *
     * @param hex   The hex code
     * @param alpha The alpha number
     * @return The color with assigned alpha
     */
    private static Color decode(String hex, int alpha) {
        Color decode = Color.decode(hex);
        return new Color(decode.getRed(), decode.getGreen(), decode.getBlue(), alpha);
    }

    @Override
    public List<Setting<?>> get() {
        return Collections.unmodifiableList(SETTINGS);
    }

    private static <T> Setting<T> create(Setting.Builder<T> builder) {
        Setting<T> setting = builder.build(CYTUtilsMod.getInstance()); // Replace with your mod instance.
        SETTINGS.add(setting);
        return setting;
    }

    @Override
    public void save() {
        CYTUtilsMod.getInstance().getOrCreateConfig().save();
    }

    @Override
    public void reload() {
        CYTUtilsMod.getInstance().getOrCreateConfig().reload();
    }
}