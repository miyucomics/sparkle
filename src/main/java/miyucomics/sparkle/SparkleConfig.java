package miyucomics.sparkle;

import me.fzzyhmstrs.fzzy_config.annotations.ClientModifiable;
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

	@ClientModifiable
	public ValidatedList<Identifier> sparklyBlocks = new ValidatedList<>(List.of(
		Identifier.ofVanilla("amethyst_block"),
		Identifier.ofVanilla("amethyst_cluster"),
		Identifier.ofVanilla("beacon"),
		Identifier.ofVanilla("budding_amethyst"),
		Identifier.ofVanilla("calibrated_sculk_sensor"),
		Identifier.ofVanilla("diamond_block"),
		Identifier.ofVanilla("emerald_block"),
		Identifier.ofVanilla("gilded_blackstone"),
		Identifier.ofVanilla("glowstone"),
		Identifier.ofVanilla("gold_block"),
		Identifier.ofVanilla("lapis_block"),
		Identifier.ofVanilla("large_amethyst_bud"),
		Identifier.ofVanilla("light_weighted_pressure_plate"),
		Identifier.ofVanilla("medium_amethyst_bud"),
		Identifier.ofVanilla("raw_gold_block"),
		Identifier.ofVanilla("small_amethyst_bud"),
		Identifier.ofVanilla("firefly_bush")
	), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("diamond_block"), Registries.BLOCK));

	@ClientModifiable
	public ValidatedList<Identifier> sparklyItems = new ValidatedList<>(List.of(
		Identifier.ofVanilla("amethyst_block"),
		Identifier.ofVanilla("amethyst_cluster"),
		Identifier.ofVanilla("amethyst_shard"),
		Identifier.ofVanilla("beacon"),
		Identifier.ofVanilla("budding_amethyst"),
		Identifier.ofVanilla("calibrated_sculk_sensor"),
		Identifier.ofVanilla("diamond"),
		Identifier.ofVanilla("diamond_axe"),
		Identifier.ofVanilla("diamond_block"),
		Identifier.ofVanilla("diamond_boots"),
		Identifier.ofVanilla("diamond_chestplate"),
		Identifier.ofVanilla("diamond_helmet"),
		Identifier.ofVanilla("diamond_hoe"),
		Identifier.ofVanilla("diamond_horse_armor"),
		Identifier.ofVanilla("diamond_leggings"),
		Identifier.ofVanilla("diamond_pickaxe"),
		Identifier.ofVanilla("diamond_shovel"),
		Identifier.ofVanilla("diamond_sword"),
		Identifier.ofVanilla("emerald"),
		Identifier.ofVanilla("emerald_block"),
		Identifier.ofVanilla("enchanted_golden_apple"),
		Identifier.ofVanilla("end_crystal"),
		Identifier.ofVanilla("experience_bottle"),
		Identifier.ofVanilla("gilded_blackstone"),
		Identifier.ofVanilla("glistering_melon_slice"),
		Identifier.ofVanilla("glow_ink_sac"),
		Identifier.ofVanilla("glowstone_dust"),
		Identifier.ofVanilla("gold_block"),
		Identifier.ofVanilla("gold_ingot"),
		Identifier.ofVanilla("gold_nugget"),
		Identifier.ofVanilla("golden_apple"),
		Identifier.ofVanilla("golden_axe"),
		Identifier.ofVanilla("golden_boots"),
		Identifier.ofVanilla("golden_carrot"),
		Identifier.ofVanilla("golden_chestplate"),
		Identifier.ofVanilla("golden_helmet"),
		Identifier.ofVanilla("golden_hoe"),
		Identifier.ofVanilla("golden_horse_armor"),
		Identifier.ofVanilla("golden_leggings"),
		Identifier.ofVanilla("golden_pickaxe"),
		Identifier.ofVanilla("golden_shovel"),
		Identifier.ofVanilla("golden_sword"),
		Identifier.ofVanilla("lapis_lazuli"),
		Identifier.ofVanilla("large_amethyst_bud"),
		Identifier.ofVanilla("light_weighted_pressure_plate"),
		Identifier.ofVanilla("medium_amethyst_bud"),
		Identifier.ofVanilla("nether_star"),
		Identifier.ofVanilla("raw_gold"),
		Identifier.ofVanilla("raw_gold_block"),
		Identifier.ofVanilla("small_amethyst_bud"),
		Identifier.ofVanilla("spectral_arrow"),
		Identifier.ofVanilla("firefly_bush")
	), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("diamond"), Registries.ITEM));

	@ClientModifiable
	public ValidatedList<Identifier> sparklyEntities = new ValidatedList<>(List.of(
		Identifier.ofVanilla("allay"),
		Identifier.ofVanilla("end_crystal"),
		Identifier.ofVanilla("experience_orb"),
		Identifier.ofVanilla("glow_item_frame"),
		Identifier.ofVanilla("glow_squid"),
		Identifier.ofVanilla("spectral_arrow")
	), ValidatedIdentifier.ofRegistry(Identifier.ofVanilla("allay"), Registries.ENTITY_TYPE));

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