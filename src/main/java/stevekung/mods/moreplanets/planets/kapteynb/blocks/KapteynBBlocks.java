/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.planets.kapteynb.fluids.BlockFluidFrozenWater;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockKapteynBIce;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockUraniumWaste;
import stevekung.mods.stevecore.RegisterHelper;

public class KapteynBBlocks
{
	public static Block kapteyn_b_block;
	public static Block kapteyn_b_redstone_ore;
	public static Block kapteyn_b_redstone_ore_active;
	public static Block kapteyn_b_ice;
	//public static Block kapteyn_b_treasure_chest;
	public static Block kapteyn_b_cracked_ice_stairs;
	public static Block kapteyn_b_dungeon_brick_stairs;
	public static Block frozen_water;
	public static Block rocky_solid_water;
	public static Block uranium_waste;
	public static Block kapteyn_b_ancient_chest;
	public static Block uranium_bomb;
	public static Block fallen_ice_crystal_meteor;
	public static Block frozen_water_geyser;
	public static Block icy_poison_crystal;

	// Fluid
	public static Fluid frozen_water_fluid;

	public static void init()
	{
		KapteynBBlocks.initBlocks();
		KapteynBBlocks.setHarvestLevels();
		KapteynBBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		KapteynBBlocks.kapteyn_b_block = new BlockKapteynB("kapteyn_b_block");
		KapteynBBlocks.kapteyn_b_redstone_ore = new BlockKapteynBRedstoneOre("kapteyn_b_redstone_ore", false);
		KapteynBBlocks.kapteyn_b_redstone_ore_active = new BlockKapteynBRedstoneOre("kapteyn_b_redstone_ore_active", true);
		KapteynBBlocks.kapteyn_b_ice = new BlockKapteynBIce("kapteyn_b_ice");
		//KapteynBBlocks.kapteyn_b_treasure_chest = new BlockKapteynBTreasureChest("kapteyn-b_treasure_chest");
		KapteynBBlocks.kapteyn_b_cracked_ice_stairs = new BlockStairsMP("kapteyn_b_cracked_ice_stairs", 3.25F, StairsCategory.kapteyn_b_cracked_ice, Blocks.stone.getDefaultState());
		KapteynBBlocks.kapteyn_b_dungeon_brick_stairs = new BlockStairsMP("kapteyn_b_dungeon_brick_stairs", 4.0F, StairsCategory.kapteyn_b_dungeon_brick, Blocks.stone.getDefaultState());
		KapteynBBlocks.rocky_solid_water = new BlockRockySolidWater("rocky_solid_water");
		KapteynBBlocks.kapteyn_b_ancient_chest = new BlockKapteynBAncientChest("kapteyn_b_ancient_chest");
		KapteynBBlocks.uranium_waste = new BlockUraniumWaste("uranium_waste");
		KapteynBBlocks.uranium_bomb = new BlockUraniumBomb("uranium_bomb");
		KapteynBBlocks.fallen_ice_crystal_meteor = new BlockFallenIceCrystalMeteor("fallen_ice_crystal_meteor");
		KapteynBBlocks.frozen_water_geyser = new BlockFrozenWaterGeyser("frozen_water_geyser");
		KapteynBBlocks.icy_poison_crystal = new BlockIcyPoisonCrystal("icy_poison_crystal");

		KapteynBBlocks.frozen_water_fluid = new Fluid("frozen_water_fluid").setBlock(KapteynBBlocks.frozen_water);
		FluidRegistry.registerFluid(KapteynBBlocks.frozen_water_fluid);
		KapteynBBlocks.frozen_water = new BlockFluidFrozenWater("frozen_water_fluid");
	}

	private static void setHarvestLevels()
	{
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("shovel", 0, 0);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("shovel", 0, 1);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 2);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 3);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 4);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 5);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 6);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 7);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 8);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 9);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 10);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 11);
		((BlockKapteynB)KapteynBBlocks.kapteyn_b_block).setHarvestLevel("pickaxe", 1, 12);
		KapteynBBlocks.frozen_water_geyser.setHarvestLevel("pickaxe", 1);
		KapteynBBlocks.kapteyn_b_redstone_ore.setHarvestLevel("pickaxe", 2);
		KapteynBBlocks.kapteyn_b_redstone_ore_active.setHarvestLevel("pickaxe", 2);
		KapteynBBlocks.kapteyn_b_cracked_ice_stairs.setHarvestLevel("pickaxe", 1);
		KapteynBBlocks.kapteyn_b_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
		KapteynBBlocks.rocky_solid_water.setHarvestLevel("shovel", 0);
		KapteynBBlocks.kapteyn_b_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_block, ItemBlockKapteynB.class);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_redstone_ore);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_redstone_ore_active);
		RegisterHelper.registerBlock(KapteynBBlocks.rocky_solid_water);
		RegisterHelper.registerBlock(KapteynBBlocks.frozen_water_geyser);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_ice, ItemBlockKapteynBIce.class);
		RegisterHelper.registerBlock(KapteynBBlocks.uranium_bomb);
		//RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_treasure_chest);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_ancient_chest);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_cracked_ice_stairs);
		RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_dungeon_brick_stairs);
		RegisterHelper.registerBlock(KapteynBBlocks.fallen_ice_crystal_meteor);
		RegisterHelper.registerBlock(KapteynBBlocks.uranium_waste, ItemBlockUraniumWaste.class);
		RegisterHelper.registerBlock(KapteynBBlocks.icy_poison_crystal, ItemBlockIcyPoisonCrystal.class);
		RegisterHelper.registerBlock(KapteynBBlocks.frozen_water);
	}
}