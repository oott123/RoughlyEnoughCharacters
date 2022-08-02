package com.oott123.rechars.mixins;

import com.oott123.rechars.helpers.MatchHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = {
    "appeng.client.gui.me.fluids.FluidRepo",
    "appeng.client.gui.me.items.ItemRepo"
})
public class AppliedEnergeticsRepo {
    @Redirect(
        method = {
            "matchesSearch"
        },
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/regex/Pattern;matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;"
        ),
        remap = false
    )
    private Matcher proxyPatternMatcher(Pattern pattern, CharSequence sequence) {
        return MatchHelper.matcher(pattern, sequence);
    }
}
