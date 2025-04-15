package miyucomics.sparkle;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class SparkleConfig extends Config {
	public static SparkleConfig config = ConfigApiJava.registerAndLoadConfig(SparkleConfig::new, RegisterType.CLIENT);
	public ValidatedList<Identifier> sparklyBlocks = new ValidatedList<>(List.of(), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("diamond_block"), Registries.BLOCK));
	public ValidatedList<Identifier> sparklyItems = new ValidatedList<>(List.of(), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("diamond"), Registries.ITEM));
	public ValidatedList<Identifier> sparklyEntities = new ValidatedList<>(List.of(), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("allay"), Registries.ENTITY_TYPE));

	public static List<Block> SPARKLY_BLOCKS = List.of();
	public static List<Item> SPARKLY_ITEMS = List.of();
	public static List<? extends EntityType<?>> SPARKLY_ENTITIES = List.of();

	public SparkleConfig() {
		super(Identifier.of("sparkle", "config"));
	}

	public static void init() {
		SPARKLY_BLOCKS = SparkleConfig.config.sparklyBlocks.stream().map(Registries.BLOCK::get).toList();
		SPARKLY_ITEMS = SparkleConfig.config.sparklyItems.stream().map(Registries.ITEM::get).toList();
		SPARKLY_ENTITIES = SparkleConfig.config.sparklyEntities.stream().map(Registries.ENTITY_TYPE::get).toList();
	}

	@Override
	public void onUpdateClient() {
		SPARKLY_BLOCKS = SparkleConfig.config.sparklyBlocks.stream().map(Registries.BLOCK::get).toList();
		SPARKLY_ITEMS = SparkleConfig.config.sparklyItems.stream().map(Registries.ITEM::get).toList();
		SPARKLY_ENTITIES = SparkleConfig.config.sparklyEntities.stream().map(Registries.ENTITY_TYPE::get).toList();
	}
}