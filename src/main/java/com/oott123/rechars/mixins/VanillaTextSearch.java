package com.oott123.rechars.mixins;

import com.oott123.rechars.stubs.RecSuffixArray;
import net.minecraft.client.search.TextSearchProvider;
import net.minecraft.client.search.TextSearcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * I can't mixin a static method which in interface.
 */
@Mixin(TextSearchProvider.class)
public abstract class VanillaTextSearch {

    @Redirect(method = "reload", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/search/TextSearcher;of(Ljava/util/List;Ljava/util/function/Function;)Lnet/minecraft/client/search/TextSearcher;"))
    private <T> TextSearcher<T> redirectSuffixArrayConstructor(List<T> values, Function<T, Stream<String>> textsGetter) {
        return of(values, textsGetter);
    }

    private <T> TextSearcher<T> of(List<T> values, Function<T, Stream<String>> textsGetter) {
        if (values.isEmpty()) {
            return TextSearcher.of();
        } else {
            RecSuffixArray<T> suffixArray = new RecSuffixArray<>();

            for (T object : values) {
                textsGetter.apply(object).forEach((text) -> suffixArray.add(object, text.toLowerCase(Locale.ROOT)));
            }

            suffixArray.build();
            Objects.requireNonNull(suffixArray);
            return suffixArray::findAll;
        }
    }

}
