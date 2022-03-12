package me.hotpocket.testingmod.mixin;

import me.hotpocket.testingmod.client.TestingModClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class TestingModMixin2 {

    @Inject(at = @At("RETURN"), method = "move")
    private void onMove(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        if(TestingModClient.instance.alwaysGround) {
            self.setOnGround(true);
        }
        if(TestingModClient.instance.fastSpeed) {
            if (self.isSneaking()
                    || self.forwardSpeed == 0 && self.sidewaysSpeed == 0)
                return;

            if (self.forwardSpeed > 0 && !self.horizontalCollision)
                self.setSprinting(true);

            if (!self.isOnGround())
                return;

            Vec3d v = self.getVelocity();
            self.setVelocity(v.x * 1.8, v.y + 0.1, v.z * 1.8);

            v = self.getVelocity();
            double currentSpeed = Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.z, 2));

            double maxSpeed = 0.66F;

            if (currentSpeed > maxSpeed)
                self.setVelocity(v.x / currentSpeed * maxSpeed, v.y,
                        v.z / currentSpeed * maxSpeed);
        }
    }
}
