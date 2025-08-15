package com.forestotzka.yurufu.mixin;

import com.forestotzka.yurufu.ForceConfirmContext;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfirmScreen.class)
public class ConfirmScreenMixin {
    @Shadow
    @Final
    protected BooleanConsumer callback;

    @Inject(method = "init", at = @At("TAIL"))
    private void autoYesOnlyForDialogRunCommand(CallbackInfo ci) {
        if (!ForceConfirmContext.isDialogRunCommand()) return;

        try {
            this.callback.accept(true);
        } catch (Throwable t) {
            MinecraftClient client = MinecraftClient.getInstance();
            client.execute(() -> {
                if (client.currentScreen == (Object) this) {
                    this.callback.accept(true);
                }
            });
        }
    }
}
