package com.oott123.rechars.mixins;

import com.oott123.rechars.stubs.RecSuffixArray;
import net.minecraft.client.search.IdentifierSearchProvider;
import net.minecraft.client.search.IdentifierSearcher;
import net.minecraft.client.search.SuffixArray;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *I can't mixin a static method which in interface.
 */
@Mixin(IdentifierSearchProvider.class)
public abstract class VanillaIdentifierSearch {

    @Shadow
    @Final
    @Mutable
    protected IdentifierSearcher<?> idSearcher;

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void redirectSuffixArrayConstructor(Function identifiersGetter, List values, CallbackInfo ci) {
        idSearcher = of(values,identifiersGetter);
    }

    private  <T> IdentifierSearcher<T> of(List<T> values, Function<T, Stream<Identifier>> identifiersGetter) {
        if (values.isEmpty()) {
            return IdentifierSearcher.of();
        } else {
            final SuffixArray<T> suffixArray = new RecSuffixArray<>();
            final SuffixArray<T> suffixArray2 = new RecSuffixArray<>();

            for (T object : values) {
                identifiersGetter.apply(object).forEach((id) -> {
                    suffixArray.add(object, id.getNamespace().toLowerCase(Locale.ROOT));
                    suffixArray2.add(object, id.getPath().toLowerCase(Locale.ROOT));
                });
            }

            suffixArray.build();
            suffixArray2.build();
            return new IdentifierSearcher<T>() {
                public List<T> searchNamespace(String namespace) {
                    return suffixArray.findAll(namespace);
                }

                public List<T> searchPath(String path) {
                    return suffixArray2.findAll(path);
                }
            };
        }
    }

}
