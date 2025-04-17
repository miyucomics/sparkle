package miyucomics.sparkle.mixin;

import miyucomics.sparkle.Sparkle;
import miyucomics.sparkle.SparkleConfig;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow @Final protected Random random;
	@Shadow public abstract World getWorld();
	@Shadow public abstract double getX();
	@Shadow public abstract double getY();
	@Shadow public abstract double getZ();

	@Inject(method = "tick", at = @At("HEAD"))
	private void addParticles(CallbackInfo info) {
		if (!getWorld().isClient())
			return;
		if (SparkleConfig.SPARKLY_ENTITIES.contains(((Entity) (Object) this).getType()) && random.nextFloat() < 0.1F) {
			double positionX = (getX() - 0.5D) + random.nextDouble();
			double positionY = (getY() - 0.5D) + random.nextDouble();
			double positionZ = (getZ() - 0.5D) + random.nextDouble();
			getWorld().addParticleClient(Sparkle.SPARKLE_PARTICLE, positionX, positionY, positionZ, 0, 0, 0);
		}
	}
}