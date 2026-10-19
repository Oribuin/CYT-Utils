package dev.oribuin.cytutils.mixin;

import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(Pack.Position.class)
public class PackPositionMixin {

    /**
     * Adds the ability to modify the resource pack position in the client, letting users overwrite existing textures
     * 
     * @param items The resource pack 
     * @param item The item being inserted
     * @param profileGetter The profile select config 
     * @param listInverted Whether the list is inverted
     * @param cir Callback info
     * @param <T> The resource pack type
     */
    @Inject(method = "insert", at = @At("HEAD"), cancellable = true)
    public <T> void insert(List<T> items, T item, Function<T, PackSelectionConfig> profileGetter, boolean listInverted, CallbackInfoReturnable<Integer> cir) {
        int i;
        
        if (((Pack) item).getPackSource() == PackSource.SERVER) {
            for (i = 0; i < items.size(); i++) {
                Pack pack = (Pack) items.get(i);
                if (!pack.isFixedPosition() || pack.getDefaultPosition() != Pack.Position.TOP)
                    break;
            }

            i += 1;
            items.add(i, item);
            cir.setReturnValue(i);
        }
    }

}
