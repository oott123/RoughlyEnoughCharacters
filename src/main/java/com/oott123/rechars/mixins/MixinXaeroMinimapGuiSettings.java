package com.oott123.rechars.mixins;

import com.oott123.rechars.helpers.MatchHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = {"xaero.common.gui.GuiSettings"})
public class MixinXaeroMinimapGuiSettings {
    @Redirect(method = {"init"}, at = @At(value = "INVOKE", target = "Ljava/lang/String;indexOf(Ljava/lang/String;)I"), remap = false)
    private int proxyStringIndexOf(String instance, String str) {
        return MatchHelper.contains(instance, str) ? 0 : instance.indexOf(str);
    }
}