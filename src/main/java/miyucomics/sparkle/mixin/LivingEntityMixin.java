package miyucomics.sparkle.mixin;

import miyucomics.sparkle.Sparkle;
import miyucomics.sparkle.SparkleConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void addParticles(CallbackInfo ci) {
		if (!getWorld().isClient())
			return;
		var client = MinecraftClient.getInstance();
		if (this.isAlive() && client.player != null) {
			if (client.player.getUuid().equals(this.getUuid()) && client.options.getPerspective().isFirstPerson())
				return;
			spawnSparkles(getShineValue((LivingEntity) (Object) this));
		}
	}

	@Unique
	private void spawnSparkles(int shineValue) {
		if (shineValue > 0) {
			if (this.random.nextInt(20 - shineValue) == 0) {
				double x = random.nextFloat() * 2 - 1;
				double y = random.nextFloat();
				double z = random.nextFloat() * 2 - 1;
				getWorld().addParticle(Sparkle.SPARKLE_PARTICLE, this.getX() + x, this.getY() + y + 1, this.getZ() + z, 0, 0, 0);
			}
		}
	}

	@Unique
	private static int getShineValue(LivingEntity entity) {
		int shineValue = 0;
		for (ItemStack stack : entity.getArmorItems())
			if (SparkleConfig.SPARKLY_ITEMS.contains(stack.getItem()))
				shineValue++;
		return shineValue;
	}
}