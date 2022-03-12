package me.hotpocket.testingmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class TestingModClient implements ClientModInitializer{
    public static TestingModClient instance;
    private static KeyBinding keyBinding;
    private static KeyBinding keyBinding2;
    public boolean alwaysGround = false;
    public boolean fastSpeed = false;
    @Override
    public void onInitializeClient() {
        instance = this;
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Toggle Always Ground", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "Honeycomb Client" // The translation key of the keybinding's category.
        ));
        keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Toggle Fast Speed", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_K, // The keycode of the key
                "Honeycomb Client" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                alwaysGround = !alwaysGround;
                client.player.sendMessage(new LiteralText("Toggled Always Ground - " + alwaysGround), true);
            }
            while (keyBinding2.wasPressed()) {
                fastSpeed = !fastSpeed;
                client.player.sendMessage(new LiteralText("Toggled Fast Speed - " + fastSpeed), true);
            }
        });
    }
}