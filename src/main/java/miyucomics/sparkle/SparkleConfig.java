package miyucomics.sparkle;

import net.fabricmc.loader.api.FabricLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SparkleConfig {
	private final File file;
	List<String> blocks;
	List<String> items;
	List<String> entities;

	private SparkleConfig(File file) {
		this.file = file;
		if (!file.exists())
			try {createConfig();} catch (IOException ignored) {}
		try {loadConfig();} catch (IOException ignored) {}
	}

	public static SparkleConfig of(String filename) {
		return new SparkleConfig(FabricLoader.getInstance().getConfigDir().resolve(filename + ".yaml").toFile());
	}

	private void createConfig() throws IOException {
		file.getParentFile().mkdirs();
		Files.createFile(file.toPath());
		try (PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8)) {
			writer.write("""
blocks:
- minecraft:amethyst_block
- minecraft:amethyst_cluster
- minecraft:beacon
- minecraft:budding_amethyst
- minecraft:calibrated_sculk_sensor
- minecraft:diamond_block
- minecraft:emerald_block
- minecraft:gilded_blackstone
- minecraft:glowstone
- minecraft:gold_block
- minecraft:lapis_block
- minecraft:large_amethyst_bud
- minecraft:light_weighted_pressure_plate
- minecraft:medium_amethyst_bud
- minecraft:raw_gold_block
- minecraft:small_amethyst_bud

items:
- minecraft:amethyst_block
- minecraft:amethyst_cluster
- minecraft:amethyst_shard
- minecraft:beacon
- minecraft:budding_amethyst
- minecraft:calibrated_sculk_sensor
- minecraft:diamond
- minecraft:diamond_axe
- minecraft:diamond_block
- minecraft:diamond_boots
- minecraft:diamond_chestplate
- minecraft:diamond_helmet
- minecraft:diamond_hoe
- minecraft:diamond_horse_armor
- minecraft:diamond_leggings
- minecraft:diamond_pickaxe
- minecraft:diamond_shovel
- minecraft:diamond_sword
- minecraft:emerald
- minecraft:emerald_block
- minecraft:enchanted_golden_apple
- minecraft:end_crystal
- minecraft:experience_bottle
- minecraft:gilded_blackstone
- minecraft:glistering_melon_slice
- minecraft:glow_ink_sac
- minecraft:glowstone_dust
- minecraft:gold_block
- minecraft:gold_ingot
- minecraft:gold_nugget
- minecraft:golden_apple
- minecraft:golden_axe
- minecraft:golden_boots
- minecraft:golden_carrot
- minecraft:golden_chestplate
- minecraft:golden_helmet
- minecraft:golden_hoe
- minecraft:golden_horse_armor
- minecraft:golden_leggings
- minecraft:golden_pickaxe
- minecraft:golden_shovel
- minecraft:golden_sword
- minecraft:lapis_lazuli
- minecraft:large_amethyst_bud
- minecraft:light_weighted_pressure_plate
- minecraft:medium_amethyst_bud
- minecraft:nether_star
- minecraft:raw_gold
- minecraft:raw_gold_block
- minecraft:small_amethyst_bud
- minecraft:spectral_arrow

entities:
- minecraft:end_crystal
- minecraft:experience_orb
- minecraft:glow_item_frame
- minecraft:glow_squid
- minecraft:spectral_arrow
			""");
		}
	}

	@SuppressWarnings("unchecked")
	private void loadConfig() throws IOException {
		StringBuilder yamlContent = new StringBuilder();
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				yamlContent.append(scanner.nextLine()).append("\n");
			}
		}
		Yaml yaml = new Yaml();
		Map<String, Object> data = yaml.load(yamlContent.toString());
		blocks = (List<String>) data.get("blocks");
		items = (List<String>) data.get("items");
		entities = (List<String>) data.get("entities");
	}
}