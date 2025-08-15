package com.forestotzka.yurufu.mixin;

import com.forestotzka.yurufu.ForceConfirmContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.dialog.DialogScreen;
import net.minecraft.text.ClickEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(DialogScreen.class)
public class DialogScreenMixin {
    @Inject(method = "handleClickEvent", at = @At("HEAD"))
    private void handleClickEventHead(ClickEvent clickEvent, @Nullable Screen afterActionScreen, CallbackInfo ci) {
        if (clickEvent instanceof ClickEvent.RunCommand) {
            ForceConfirmContext.markDialogRunCommand(true);
        }
    }

    @Inject(method = "handleClickEvent", at = @At("RETURN"))
    private void handleClickEventReturn(ClickEvent clickEvent, @Nullable Screen afterActionScreen, CallbackInfo ci) {
        if (clickEvent instanceof ClickEvent.RunCommand) {
            ForceConfirmContext.markDialogRunCommand(false);
        }
    }
}
