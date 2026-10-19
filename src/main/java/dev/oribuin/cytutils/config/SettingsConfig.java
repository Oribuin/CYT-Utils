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
            .hidden(true)
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
                    "fish-bronze", Util.HEX_COLOR_CODEC, decode("#d9941d", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_SILVER = create(SettingBuilder.of(
                    "fish-silver", Util.HEX_COLOR_CODEC, decode("#555555", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_GOLD = create(SettingBuilder.of(
                    "fish-gold", Util.HEX_COLOR_CODEC, decode("#fcdb2b", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_DIAMOND = create(SettingBuilder.of(
                    "fish-diamond", Util.HEX_COLOR_CODEC, decode("#18cde4", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_PLATINUM = create(SettingBuilder.of(
                    "fish-platinum", Util.HEX_COLOR_CODEC, decode("#9b85b0", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_MYTHIC = create(SettingBuilder.of(
                    "fish-mythic", Util.HEX_COLOR_CODEC, decode("#c949ff", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_AUGMENT = create(SettingBuilder.of(
                    "fish-augment", Util.HEX_COLOR_CODEC, decode("#83c9d4", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_CRAB = create(SettingBuilder.of(
                    "fish-crab", Util.HEX_COLOR_CODEC, decode("#cf2d2d", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_SQUID = create(SettingBuilder.of(
                    "fish-squid", Util.HEX_COLOR_CODEC, decode("#5460d1", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> FISH_DOLPHIN = create(SettingBuilder.of(
                    "fish-dolphin", Util.HEX_COLOR_CODEC, decode("#5460d1", 178)
            )
            .tab("client")
            .section("pyrofishing")
            .input(new ColorInput<>(true)));
    // endregion

    // region Wizard Colours
    public static final Setting<Color> WIZARD_COIN_FRAGMENT = create(SettingBuilder.of(
                    "wizard-coin-fragment", Util.HEX_COLOR_CODEC, decode("#BF69FF", 178)
            )
            .tab("client")
            .section("wizard")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> WIZARD_COIN = create(SettingBuilder.of(
                    "wizard-coin", Util.HEX_COLOR_CODEC, decode("#BF69FF", 178)
            )
            .tab("client")
            .section("wizard")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> WIZARD_ENCHANTED_COIN = create(SettingBuilder.of(
                    "wizard-enchanted-coin", Util.HEX_COLOR_CODEC, decode("#BF69FF", 178)
            )
            .tab("client")
            .section("wizard")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> WIZARD_BROKEN_BOOK = create(SettingBuilder.of(
                    "wizard-broken-book", Util.HEX_COLOR_CODEC, decode("#BF69FF", 178)
            )
            .tab("client")
            .section("wizard")
            .input(new ColorInput<>(true)));
    // endregion

    // region Relics Section
    public static final Setting<Color> RELICS_EGYPTIAN = create(SettingBuilder.of(
                    "relics-egyptian", Util.HEX_COLOR_CODEC, decode("#ded664", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));
    
    public static final Setting<Color> RELICS_FOSSIL = create(SettingBuilder.of(
                    "relics-fossil", Util.HEX_COLOR_CODEC, decode("#79663F", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));
    
    public static final Setting<Color> RELICS_GEMS = create(SettingBuilder.of(
                    "relics-gems", Util.HEX_COLOR_CODEC, decode("#6ab1c4", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));
    
    public static final Setting<Color> RELICS_INSECT = create(SettingBuilder.of(
                    "relics-mango", Util.HEX_COLOR_CODEC, decode("#bee8b0", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));

    public static final Setting<Color> RELICS_MANGO = create(SettingBuilder.of(
                    "relics-mango", Util.HEX_COLOR_CODEC, decode("#e69757", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));
    
    public static final Setting<Color> RELICS_UFO = create(SettingBuilder.of(
                    "relics-ufo", Util.HEX_COLOR_CODEC, decode("#5a8540", 178)
            )
            .tab("client")
            .section("relics")
            .input(new ColorInput<>(true)));

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