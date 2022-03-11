package me.hotpocket.testingmod.mixin;

import me.hotpocket.testingmod.TestingMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;

@Mixin(MinecraftClient.class)
public abstract class TestingModMixin {

    @Shadow @Final public Window window;

    @Inject(at = @At("RETURN"), method = "updateWindowTitle")
    private void updateWindowTitle(CallbackInfo ci) throws IOException {
        this.window.setTitle("Honeycomb Client");
        InputStream is = TestingMod.class.getResourceAsStream("/assets/honeycomb-16x16.png");
        InputStream is1 = TestingMod.class.getResourceAsStream("/assets/honeycomb-32x32.png");

        this.window.setIcon(is, is1);
    }
}
