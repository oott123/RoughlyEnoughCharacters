package com.oott123.rechars.mixins;

import com.oott123.rechars.helpers.MatchHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = {"fi.dy.masa.malilib.gui.widgets.WidgetListBase"})
public class MixinMaLiLibWidgetListConfigOptions {
    @Redirect(method = {"matchesFilter(Ljava/lang/String;Ljava/lang/String;)Z"}, at = @At(value = "INVOKE", target = "Ljava/lang/String;contains(Ljava/lang/CharSequence;)Z"), remap = false)
    private boolean proxyStringContains(String haystack, CharSequence needle) {
        return MatchHelper.contains(haystack, needle);
    }
}
