package com.oott123.rechars.mixins;

import com.oott123.rechars.helpers.MatchHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = {"mezz.jei.common.search.ElementSearchLowMem"})
public abstract class MixinJeiSearchLowMemory {

    @Redirect(method = "matches", at = @At(value = "INVOKE", target = "Ljava/lang/String;contains(Ljava/lang/CharSequence;)Z"),remap = false)
    private static boolean redirectContains(String instance, CharSequence s) {
        return MatchHelper.contains(instance, s);
    }

}
