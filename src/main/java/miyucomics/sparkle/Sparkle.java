package miyucomics.sparkle;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Sparkle implements ClientModInitializer {
	public static final String MOD_ID = "sparkle";
	public static DefaultParticleType SPARKLE_PARTICLE;
	public static SparkleConfig config = ConfigApiJava.registerAndLoadConfig(SparkleConfig::new, RegisterType.CLIENT);

	@Override
	public void onInitializeClient() {
		config.init();
		SPARKLE_PARTICLE = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "sparkle"), FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(SPARKLE_PARTICLE, SparkleParticle.Factory::new);
	}
}