/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.planets.venus.itemblocks.ItemBlockVenus;
import stevekung.mods.stevecore.RegisterHelper;

public class VenusBlocks
{
	public static Block venus_block;
	public static Block venus_redstone_ore;
	public static Block venus_redstone_ore_active;
	public static Block venus_smoke_geyser;
	public static Block venus_magma_rock;
	public static Block venus_sand;
	public static Block venusian_blaze_egg;
	public static Block sulfur_torch;
	public static Block venus_ancient_chest;
	public static Block venus_treasure_chest;

	public static void init()
	{
		VenusBlocks.initBlocks();
		VenusBlocks.setHarvestLevels();
		VenusBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		VenusBlocks.venus_block = new BlockBasicVenus("venus_block");
		VenusBlocks.venus_redstone_ore = new BlockVenusRedstoneOre("venus_redstone_ore", false).setBlockTextureName("venus:venus_redstone_ore");
		VenusBlocks.venus_redstone_ore_active = new BlockVenusRedstoneOre("venus_redstone_ore_active", true).setBlockTextureName("venus:venus_redstone_ore");
		VenusBlocks.venus_smoke_geyser = new BlockVenusSmokeGeyser("venus_smoke_geyser");
		VenusBlocks.venus_sand = new BlockVenusSand("venus_sand");
		VenusBlocks.venus_magma_rock = new BlockVenusMagmaRock("venus_magma_rock");
		VenusBlocks.venusian_blaze_egg = new BlockVenusianBlazeEgg("venusian_blaze_egg");
		VenusBlocks.sulfur_torch = new BlockSulfurTorch("sulfur_torch");
		VenusBlocks.venus_ancient_chest = new BlockVenusAncientChest("venus_ancient_chest");
		VenusBlocks.venus_treasure_chest = new BlockVenusTreasureChest("venus_treasure_chest");
	}

	private static void setHarvestLevels()
	{
		VenusBlocks.venus_block.setHarvestLevel("pickaxe", 1);
		VenusBlocks.venus_magma_rock.setHarvestLevel("pickaxe", 1);
		VenusBlocks.venus_smoke_geyser.setHarvestLevel("pickaxe", 1);
		VenusBlocks.venus_redstone_ore.setHarvestLevel("pickaxe", 2);
		VenusBlocks.venus_redstone_ore_active.setHarvestLevel("pickaxe", 2);
		VenusBlocks.venus_sand.setHarvestLevel("shovel", 0);
		VenusBlocks.venus_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(VenusBlocks.venus_block, ItemBlockVenus.class);
		RegisterHelper.registerBlock(VenusBlocks.venus_redstone_ore);
		RegisterHelper.registerBlock(VenusBlocks.venus_redstone_ore_active);
		RegisterHelper.registerBlock(VenusBlocks.venus_smoke_geyser);
		RegisterHelper.registerBlock(VenusBlocks.venus_magma_rock);
		RegisterHelper.registerBlock(VenusBlocks.venus_sand);
		RegisterHelper.registerBlock(VenusBlocks.sulfur_torch);
		RegisterHelper.registerBlock(VenusBlocks.venusian_blaze_egg);
		RegisterHelper.registerBlock(VenusBlocks.venus_ancient_chest);
		RegisterHelper.registerBlock(VenusBlocks.venus_treasure_chest);
	}
}