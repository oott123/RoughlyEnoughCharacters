package com.oott123.rechars.mixins;

import com.oott123.rechars.stubs.FakeTree;
import mezz.jei.core.search.suffixtree.GeneralizedSuffixTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Supplier;

@Pseudo
@Mixin(targets = {"mezz.jei.common.search.ElementPrefixParser"})
public abstract class MixinJeiSearch {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lmezz/jei/core/search/PrefixInfo;<init>(CLmezz/jei/core/search/PrefixInfo$IModeGetter;Lmezz/jei/core/search/PrefixInfo$IStringsGetter;Ljava/util/function/Supplier;)V"), remap = false)
    private static Supplier<?> modifyConstructorClInit(Supplier<?> supplier) {
        return FakeTree::new;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lmezz/jei/core/search/PrefixInfo;<init>(CLmezz/jei/core/search/PrefixInfo$IModeGetter;Lmezz/jei/core/search/PrefixInfo$IStringsGetter;Ljava/util/function/Supplier;)V"), remap = false)
    private Supplier<?> modifyConstructorInit(Supplier<?> supplier) {
        if (supplier.get() instanceof GeneralizedSuffixTree) {
            return FakeTree::new;
        }
        return supplier;
    }

}
