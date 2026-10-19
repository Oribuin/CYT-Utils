package dev.oribuin.cytutils.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Pack.class)
public abstract class PackFixedMixin {

    @Shadow public abstract PackSource getPackSource();

    @ModifyReturnValue(
            method = {"isFixedPosition"},
            at = {@At("RETURN")}
    )
    private boolean isFixedPosition(boolean original) {
        return this.getPackSource() == PackSource.DEFAULT; // Default minecraft pack is always fixed
    }
}
