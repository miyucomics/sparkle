package miyucomics.sparkle;

import me.fzzyhmstrs.fzzy_config.annotations.ClientModifiable;
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
	public SparkleConfig() {
		super(new Identifier("sparkle", "config"));
	}

	@ClientModifiable
	public ValidatedList<Identifier> sparklyBlocks = new ValidatedList<>(List.of(
		new Identifier("amethyst_block"),
		new Identifier("amethyst_cluster"),
		new Identifier("beacon"),
		new Identifier("budding_amethyst"),
		new Identifier("calibrated_sculk_sensor"),
		new Identifier("diamond_block"),
		new Identifier("emerald_block"),
		new Identifier("gilded_blackstone"),
		new Identifier("glowstone"),
		new Identifier("gold_block"),
		new Identifier("lapis_block"),
		new Identifier("large_amethyst_bud"),
		new Identifier("light_weighted_pressure_plate"),
		new Identifier("medium_amethyst_bud"),
		new Identifier("raw_gold_block"),
		new Identifier("small_amethyst_bud")
	), ValidatedIdentifier.ofRegistry(new Identifier("diamond_block"), Registries.BLOCK));

	@ClientModifiable
	public ValidatedList<Identifier> sparklyItems = new ValidatedList<>(List.of(
		new Identifier("amethyst_block"),
		new Identifier("amethyst_cluster"),
		new Identifier("amethyst_shard"),
		new Identifier("beacon"),
		new Identifier("budding_amethyst"),
		new Identifier("calibrated_sculk_sensor"),
		new Identifier("diamond"),
		new Identifier("diamond_axe"),
		new Identifier("diamond_block"),
		new Identifier("diamond_boots"),
		new Identifier("diamond_chestplate"),
		new Identifier("diamond_helmet"),
		new Identifier("diamond_hoe"),
		new Identifier("diamond_horse_armor"),
		new Identifier("diamond_leggings"),
		new Identifier("diamond_pickaxe"),
		new Identifier("diamond_shovel"),
		new Identifier("diamond_sword"),
		new Identifier("emerald"),
		new Identifier("emerald_block"),
		new Identifier("enchanted_golden_apple"),
		new Identifier("end_crystal"),
		new Identifier("experience_bottle"),
		new Identifier("gilded_blackstone"),
		new Identifier("glistering_melon_slice"),
		new Identifier("glow_ink_sac"),
		new Identifier("glowstone_dust"),
		new Identifier("gold_block"),
		new Identifier("gold_ingot"),
		new Identifier("gold_nugget"),
		new Identifier("golden_apple"),
		new Identifier("golden_axe"),
		new Identifier("golden_boots"),
		new Identifier("golden_carrot"),
		new Identifier("golden_chestplate"),
		new Identifier("golden_helmet"),
		new Identifier("golden_hoe"),
		new Identifier("golden_horse_armor"),
		new Identifier("golden_leggings"),
		new Identifier("golden_pickaxe"),
		new Identifier("golden_shovel"),
		new Identifier("golden_sword"),
		new Identifier("lapis_lazuli"),
		new Identifier("large_amethyst_bud"),
		new Identifier("light_weighted_pressure_plate"),
		new Identifier("medium_amethyst_bud"),
		new Identifier("nether_star"),
		new Identifier("raw_gold"),
		new Identifier("raw_gold_block"),
		new Identifier("small_amethyst_bud"),
		new Identifier("spectral_arrow")
	), ValidatedIdentifier.ofRegistry(new Identifier("diamond"), Registries.ITEM));

	@ClientModifiable
	public ValidatedList<Identifier> sparklyEntities = new ValidatedList<>(List.of(
		new Identifier("allay"),
		new Identifier("end_crystal"),
		new Identifier("experience_orb"),
		new Identifier("glow_item_frame"),
		new Identifier("glow_squid"),
		new Identifier("spectral_arrow")
	), ValidatedIdentifier.ofRegistry(new Identifier("allay"), Registries.ENTITY_TYPE));

	public static List<Block> SPARKLY_BLOCKS = List.of();
	public static List<Item> SPARKLY_ITEMS = List.of();
	public static List<? extends EntityType<?>> SPARKLY_ENTITIES = List.of();

	public void init() {
		SPARKLY_BLOCKS = Sparkle.config.sparklyBlocks.stream().map(Registries.BLOCK::get).toList();
		SPARKLY_ITEMS = Sparkle.config.sparklyItems.stream().map(Registries.ITEM::get).toList();
		SPARKLY_ENTITIES = Sparkle.config.sparklyEntities.stream().map(Registries.ENTITY_TYPE::get).toList();
	}

	@Override
	public void onUpdateClient() {
		SPARKLY_BLOCKS = Sparkle.config.sparklyBlocks.stream().map(Registries.BLOCK::get).toList();
		SPARKLY_ITEMS = Sparkle.config.sparklyItems.stream().map(Registries.ITEM::get).toList();
		SPARKLY_ENTITIES = Sparkle.config.sparklyEntities.stream().map(Registries.ENTITY_TYPE::get).toList();
	}
}