package com.oott123.rechars.mixins;

import com.oott123.rechars.stubs.RecSuffixArray;
import net.minecraft.client.search.IdentifierSearchableContainer;
import net.minecraft.client.search.SuffixArray;
import net.minecraft.client.search.TextSearchableContainer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;
import java.util.stream.Stream;

@Mixin({TextSearchableContainer.class})
public abstract class VanillaTextSearch<T> extends IdentifierSearchableContainer<T> {
    @Shadow
    private SuffixArray<T> byText;

    public VanillaTextSearch(Function<T, Stream<Identifier>> identifierFinder) {
        super(identifierFinder);
    }

    @Inject(method = {"<init>"}, at = {@At("TAIL")})
    private final void onInit(Function textFinder, Function identifierFinder, CallbackInfo info) {
        this.reloadByTextWithRec();
    }

    @Inject(method = {"reload"}, at = {@At(target = "Lnet/minecraft/client/search/IdentifierSearchableContainer;reload()V", value = "INVOKE")})
    private final void onReload(CallbackInfo info) {
        this.reloadByTextWithRec();
    }

    private final void reloadByTextWithRec() {
        this.byText = new RecSuffixArray();
    }
}
