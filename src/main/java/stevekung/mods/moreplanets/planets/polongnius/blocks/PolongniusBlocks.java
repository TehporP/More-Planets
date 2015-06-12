/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.planets.polongnius.fluids.BlockFluidCheeseOfMilk;
import stevekung.mods.moreplanets.planets.polongnius.itemblocks.ItemBlockPolongnius;
import stevekung.mods.stevecore.RegisterHelper;

public class PolongniusBlocks
{
	public static Block polongnius_block;
	//public static Block polongnius_treasure_chest;
	public static Block fallen_polongnius_meteor;
	public static Block flonium_torch;
	public static Block cheese_of_milk_cake;
	//public static Block ultra_violet_solar_panel;
	public static Block polongnius_cobblestone_stairs;
	public static Block polongnius_dungeon_brick_stairs;
	public static Block cheese_of_milk;
	public static Block polongnius_ancient_chest;
	public static Block cheese_slime_block;
	public static Block cheese_slime_egg;
	//	public static Block ultra_violet_solar_fake;
	public static Block cheese_gas_block;

	// Fluid
	public static Fluid cheese_of_milk_fluid;

	public static void init()
	{
		PolongniusBlocks.initBlocks();
		PolongniusBlocks.setHarvestLevels();
		PolongniusBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		PolongniusBlocks.polongnius_block = new BlockPolongnius("polongnius_block");
		//PolongniusBlocks.polongnius_treasure_chest = new BlockPolongniusTreasureChest("polongnius_treasure_chest");
		PolongniusBlocks.fallen_polongnius_meteor = new BlockFallenPolongniusMeteor("fallen_polongnius_meteor");
		PolongniusBlocks.cheese_of_milk_cake = new BlockCheeseOfMilkCake("cheese_of_milk_cake");
		PolongniusBlocks.flonium_torch = new BlockFloniumTorch("flonium_torch");
		//PolongniusBlocks.ultra_violet_solar_panel = new BlockUltraVioletSolarPanel("ultra_violet_solar_panel");
		PolongniusBlocks.polongnius_cobblestone_stairs = new BlockStairsMP("polongnius_cobblestone_stairs", 3.0F, StairsCategory.polongnius_cobblestone, Blocks.stone.getDefaultState());
		PolongniusBlocks.polongnius_dungeon_brick_stairs = new BlockStairsMP("polongnius_dungeon_brick_stairs", 4.0F, StairsCategory.polongnius_dungeon_brick, Blocks.stone.getDefaultState());
		PolongniusBlocks.polongnius_ancient_chest = new BlockPolongniusAncientChest("polongnius_ancient_chest");
		PolongniusBlocks.cheese_slime_block = new BlockCheeseSlime("cheese_slime_block");
		PolongniusBlocks.cheese_slime_egg = new BlockCheeseSlimeEgg("cheese_slime_egg");
		//		PolongniusBlocks.ultra_violet_solar_fake = new BlockUltraVioletSolarFake("ultra_violet_solar_fake");
		PolongniusBlocks.cheese_gas_block = new BlockCheeseGas("cheese_gas_block");

		PolongniusBlocks.cheese_of_milk_fluid = new Fluid("cheese_of_milk_fluid").setBlock(PolongniusBlocks.cheese_of_milk).setViscosity(2000);
		FluidRegistry.registerFluid(PolongniusBlocks.cheese_of_milk_fluid);
		PolongniusBlocks.cheese_of_milk = new BlockFluidCheeseOfMilk("cheese_of_milk_fluid");
	}

	private static void setHarvestLevels()
	{
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("shovel", 0, 0);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("shovel", 0, 1);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 2);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 3);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 4);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 5);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 6);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 7);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 8);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 9);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 10);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 11);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 12);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 13);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 14);
		((BlockPolongnius)PolongniusBlocks.polongnius_block).setHarvestLevel("pickaxe", 1, 15);
		PolongniusBlocks.polongnius_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.polongnius_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.fallen_polongnius_meteor.setHarvestLevel("pickaxe", 2);
		//PolongniusBlocks.ultra_violet_solar_panel.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.polongnius_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_block, ItemBlockPolongnius.class);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_gas_block);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_slime_block);
		//RegisterHelper.registerBlock(PolongniusBlocks.ultra_violet_solar_panel, ItemBlockDesc.class);
		//RegisterHelper.registerBlock(PolongniusBlocks.polongnius_treasure_chest);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_ancient_chest);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_cobblestone_stairs);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_dungeon_brick_stairs);
		RegisterHelper.registerBlock(PolongniusBlocks.fallen_polongnius_meteor);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_slime_egg);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_of_milk_cake);
		RegisterHelper.registerBlock(PolongniusBlocks.flonium_torch);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_of_milk);
		//		RegisterHelper.registerBlock(PolongniusBlocks.ultra_violet_solar_fake);
	}
}