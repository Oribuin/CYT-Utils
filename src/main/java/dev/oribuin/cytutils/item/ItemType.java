package dev.oribuin.cytutils.item;

import dev.oribuin.cytutils.config.SettingsConfig;
import jdk.jfr.SettingControl;
import me.lilac.camellia.config.Setting;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public enum ItemType {

    // region PyroFishing
    FISH_BRONZE(() -> SettingsConfig.FISH_BRONZE, "pyrofishingpro:tier", "Bronze"),
    FISH_SILVER(() -> SettingsConfig.FISH_SILVER, "pyrofishingpro:tier", "Silver"),
    FISH_GOLD(() -> SettingsConfig.FISH_GOLD, "pyrofishingpro:tier", "Gold"),
    FISH_DIAMOND(() -> SettingsConfig.FISH_DIAMOND, "pyrofishingpro:tier", "Diamond"),
    FISH_PLAT(() -> SettingsConfig.FISH_PLATINUM, "pyrofishingpro:tier", "Platinum"),
    FISH_MYTHIC(() -> SettingsConfig.FISH_MYTHIC, "pyrofishingpro:tier", "Mythical"),
    FISH_AUGMENT(() -> SettingsConfig.FISH_AUGMENT, "pyrofishingpro:augment"),
    FISH_CRAB(() -> SettingsConfig.FISH_CRAB, List.of("pyrofishingpro:crabclaw", "pyrofishingpro:crabscales")),
    FISH_DOLPHIN(() -> SettingsConfig.FISH_DOLPHIN, "pyrofishingpro:dolphintail"),
    FISH_SQUID(() -> SettingsConfig.FISH_SQUID, "pyrofishingpro:squidtentacle"),
    // endregion
    // region Wizard
    WIZARD_COIN_FRAGMENT(() -> SettingsConfig.WIZARD_COIN_FRAGMENT, "itemhandler:id", "wizard_ancient_coin_fragment"),
    WIZARD_COIN(() -> SettingsConfig.WIZARD_COIN, "itemhandler:id", "wizard_ancient_coin"),
    WIZARD_ENCHANTED(() -> SettingsConfig.WIZARD_ENCHANTED_COIN, "itemhandler:id", "wizard_enchanted_ancient_coin"),
    WIZARD_BROKEN_BOOK(() -> SettingsConfig.WIZARD_BROKEN_BOOK, "itemhandler:id", "wizard_broken_enchant_book"),
    
    // endregion
    RELIC_EGYPTIAN(() -> SettingsConfig.RELICS_EGYPTIAN, "mango-relics:group", "Egyptian Relics"),
    RELIC_FOSSILS(() -> SettingsConfig.RELICS_FOSSIL, "mango-relics:group", "Fossils Relics"),
    RELIC_GEMS(() -> SettingsConfig.RELICS_GEMS, "mango-relics:group", "Gems Relics"),
    RELIC_INSECT(() -> SettingsConfig.RELICS_INSECT, "mango-relics:group", "Insect Relics"),
    RELIC_MANGO(() -> SettingsConfig.RELICS_MANGO, "mango-relics:group", "Mango Relics"),
    RELIC_UFO(() -> SettingsConfig.RELICS_UFO, "mango-relics:group", "UFO Relics"),
    RELIC_LOST_RUNE(() -> SettingsConfig.RELICS_LOST_RUNE, "mango-relics:group", "Lost Rune Relics"),
    RELIC_ARCANE(() -> SettingsConfig.RELICS_ARCANE, "mango-relics:group", "Arcane Relics"),
    RELIC_OCCULT(() -> SettingsConfig.RELICS_OCCULT, "mango-relics:group", "Occult Relics"),
    RELIC_SCULK(() -> SettingsConfig.RELICS_SCULK, "mango-relics:group", "Sculk Relics"),
    
//    RELIC_DIRTY(, , ),
//    RELIC_CLEAN(, , );
    ;
    
    private final Supplier<Setting<Color>> colourGetter;
    private final List<String> namespace;
    private final String name;

    ItemType(Supplier<Setting<Color>> colourGetter, List<String> namespace, String name) {
        this.colourGetter = colourGetter;
        this.namespace = namespace;
        this.name = name;
    }

    ItemType(Supplier<Setting<Color>> colourGetter, String namespace, String name) {
        this.colourGetter = colourGetter;
        this.namespace = List.of(namespace);
        this.name = name;
    }

    ItemType(Supplier<Setting<Color>> colourGetter, String... namespace) {
        this.colourGetter = colourGetter;
        this.namespace = List.of(namespace);
        this.name = null;
    }

    ItemType(Supplier<Setting<Color>> colourGetter, List<String> namespace) {
        this.colourGetter = colourGetter;
        this.namespace = namespace;
        this.name = null;
    }

    /**
     * Get an item type colour from a CYT Item
     * @param stack The stack to get the colour from
     * @return The resulting item colour
     */
    @Nullable
    public static ItemType from(@NotNull ItemStack stack) {
        DataComponentMap componentMap = stack.getComponents();
        if (componentMap.isEmpty()) return null;

        CustomData nbtComponent = componentMap.get(DataComponents.CUSTOM_DATA);
        if (nbtComponent == null || nbtComponent.isEmpty()) return null;
        CompoundTag nbtCompound = nbtComponent.copyTag();

        Tag bukkitValues = nbtCompound.get("PublicBukkitValues");
        if (bukkitValues == null) return null;
        CompoundTag compound = bukkitValues.asCompound().orElse(null);
        if (compound == null) return null;
        
        // Check each cyt item type
        for (ItemType colour : ItemType.values()) {
            for (String namespace : colour.getNamespace()) {
                Tag tag = compound.get(namespace);
                if (tag == null) continue;
                
                String result = tag.asString().orElse(null);
                boolean isVague = colour.getName() == null;
                boolean doesMatch = result != null && result.equalsIgnoreCase(colour.getName());
                if (isVague || doesMatch) return colour;
            }
        }
        
        return null;
    }

    public Supplier<Setting<Color>> getColourGetter() {
        return colourGetter;
    }

    public List<String> getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }
}
