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
@Mixin(
    targets = {
        "dev.emi.emi.search.RegexNameQuery",
        "dev.emi.emi.search.RegexTooltipQuery"
    },
    remap = false
)
public class EmiSearchRegex {

    @Redirect(method = "matches", at = @At(value = "INVOKE", target = "Ljava/util/regex/Pattern;matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;"))
    private Matcher redirectMatcher(Pattern pattern, CharSequence sequence) {
        return MatchHelper.matcher(pattern, sequence);
    }

}

