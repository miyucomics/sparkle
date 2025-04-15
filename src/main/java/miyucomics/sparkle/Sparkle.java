package miyucomics.sparkle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Sparkle implements ClientModInitializer {
	public static final String MOD_ID = "sparkle";
	public static SimpleParticleType SPARKLE_PARTICLE;

	@Override
	public void onInitializeClient() {
		SparkleConfig.init();
		SPARKLE_PARTICLE = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "sparkle"), FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(SPARKLE_PARTICLE, SparkleParticle.Factory::new);
	}
}