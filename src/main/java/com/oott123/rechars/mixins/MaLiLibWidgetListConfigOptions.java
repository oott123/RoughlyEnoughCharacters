package com.oott123.rechars.mixins;

import com.oott123.rechars.helpers.MatchHelper;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.widgets.WidgetConfigOption;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptionsBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(value = WidgetListConfigOptions.class)
public abstract class MaLiLibWidgetListConfigOptions extends WidgetListConfigOptionsBase<GuiConfigsBase.ConfigOptionWrapper, WidgetConfigOption> {
    public MaLiLibWidgetListConfigOptions(int x, int y, int width, int height, int configWidth) {
        super(x, y, width, height, configWidth);
    }

    @Override
    protected boolean matchesFilter(String entryString, String filterText) {
        if (filterText.isEmpty()) {
            return true;
        }

        for (String filter : filterText.split("\\|")) {
            if (MatchHelper.contains(entryString, filter)) {
                return true;
            }
        }

        return false;
    }
}
