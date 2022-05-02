package com.oott123.rechars.mixins;

import com.oott123.rechars.ReChars;
import com.oott123.rechars.helpers.MatchHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = {"badasintended.slotlink.screen.RequestScreenHandler$Filter"})
public class SlotlinkRequestScreenFilter {
    @Redirect(method = {"match"}, at = @At(value = "INVOKE", target = "Lkotlin/text/StringsKt;contains(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Z", remap = false))
    private boolean proxyStringKtContains(CharSequence haystack, CharSequence needle, boolean ignoreCase) {
        return MatchHelper.contains(haystack, needle, ignoreCase);
    }
}
