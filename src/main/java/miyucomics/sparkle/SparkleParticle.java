package miyucomics.sparkle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SparkleParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;

	protected SparkleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider provider) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.maxAge = 20;
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;
		this.spriteProvider = provider;
		this.setSprite(provider);
		this.setSpriteForAge(provider);
		this.scale(2.5F);
	}

	public void tick() {
		super.tick();
		this.setSpriteForAge(this.spriteProvider);
	}

	@Override
	public int getBrightness(float tint) {
		int i = super.getBrightness(tint);
		int k = i >> 16 & 0xFF;
		return 240 | k << 16;
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double d, double e, double f, double g, double h, double i) {
			SparkleParticle sparkleParticle = new SparkleParticle(world, d, e, f, g, h, i, this.spriteProvider);
			sparkleParticle.setAlpha(1.0f);
			return sparkleParticle;
		}
	}
}