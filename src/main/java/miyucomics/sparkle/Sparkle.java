package miyucomics.sparkle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class Sparkle implements ClientModInitializer {
	public static final String MOD_ID = "sparkle";
	public static DefaultParticleType SPARKLE_PARTICLE;
	public static SparkleConfig CONFIG = SparkleConfig.of(MOD_ID);

	public static List<Block> SPARKLY_BLOCKS;
	public static List<Item> SPARKLY_ITEMS;
	public static List<? extends EntityType<?>> SPARKLY_ENTITIES;

	@Override
	public void onInitializeClient() {
		SPARKLY_BLOCKS = CONFIG.blocks.stream().filter(string -> string != null && !string.isEmpty()).map(thing -> Registries.BLOCK.getOrEmpty(new Identifier(thing))).filter(Optional::isPresent).map(Optional::get).toList();
		SPARKLY_ITEMS = CONFIG.items.stream().filter(string -> string != null && !string.isEmpty()).map(thing -> Registries.ITEM.getOrEmpty(new Identifier(thing))).filter(Optional::isPresent).map(Optional::get).toList();
		SPARKLY_ENTITIES = CONFIG.entities.stream().filter(string -> string != null && !string.isEmpty()).map(thing -> Registries.ENTITY_TYPE.getOrEmpty(new Identifier(thing))).filter(Optional::isPresent).map(Optional::get).toList();

		SPARKLE_PARTICLE = Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparkle"), FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(SPARKLE_PARTICLE, SparkleParticle.Factory::new);
	}
}