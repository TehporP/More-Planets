/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.planets.polongnius.fluids.BlockFluidCheeseOfMilk;
import stevekung.mods.moreplanets.planets.polongnius.itemblocks.ItemBlockPolongnius;
import stevekung.mods.stevecore.RegisterHelper;

public class PolongniusBlocks
{
	public static Block polongnius_block;
	public static Block polongnius_treasure_chest;
	public static Block fallen_polongnius_meteor;
	public static Block flonium_torch;
	public static Block cheese_of_milk_cake;
	public static Block ultra_violet_solar_panel;
	public static Block polongnius_cobblestone_stairs;
	public static Block polongnius_dungeon_brick_stairs;
	public static Block cheese_of_milk;
	public static Block polongnius_ancient_chest;
	public static Block cheese_slime_block;
	public static Block cheese_slime_egg;
	public static Block ultra_violet_solar_fake;
	public static Block cheese_gas;

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
		PolongniusBlocks.polongnius_block = new BlockBasicPolongnius("polongnius_block");
		PolongniusBlocks.polongnius_treasure_chest = new BlockT5PolongniusTreasureChest("polongnius_treasure_chest");
		PolongniusBlocks.fallen_polongnius_meteor = new BlockFallenPolongniusMeteor("fallen_polongnius_meteor");
		PolongniusBlocks.cheese_of_milk_cake = new BlockCheeseOfMilkCake("cheese_of_milk_cake");
		PolongniusBlocks.flonium_torch = new BlockFloniumTorch("flonium_torch");
		PolongniusBlocks.ultra_violet_solar_panel = new BlockUltraVioletSolarPanel("ultra_violet_solar_panel");
		PolongniusBlocks.polongnius_cobblestone_stairs = new BlockStairsMP("polongnius_cobblestone_stairs", 3.0F, StairsCategory.POLONGNIUS_COBBLESTONE, Blocks.stone);
		PolongniusBlocks.polongnius_dungeon_brick_stairs = new BlockStairsMP("polongnius_dungeon_brick_stairs", 4.0F, StairsCategory.POLONGNIUS_BRICK, Blocks.stone);
		PolongniusBlocks.polongnius_ancient_chest = new BlockPolongniusAncientChest("polongnius_ancient_chest");
		PolongniusBlocks.cheese_slime_block = new BlockCheeseSlime("cheese_slime_block");
		PolongniusBlocks.cheese_slime_egg = new BlockCheeseSlimeEgg("cheese_slime_egg");
		PolongniusBlocks.ultra_violet_solar_fake = new BlockUltraVioletSolarFake("ultra_violet_solar_fake");
		PolongniusBlocks.cheese_gas = new BlockCheeseGas("cheese_gas");

		PolongniusBlocks.cheese_of_milk_fluid = new Fluid("cheese_of_milk_fluid").setBlock(PolongniusBlocks.cheese_of_milk).setViscosity(2000);
		FluidRegistry.registerFluid(PolongniusBlocks.cheese_of_milk_fluid);
		PolongniusBlocks.cheese_of_milk = new BlockFluidCheeseOfMilk("cheese_of_milk_fluid");
	}

	private static void setHarvestLevels()
	{
		PolongniusBlocks.polongnius_ancient_chest.setHarvestLevel("axe", 0);
		PolongniusBlocks.polongnius_block.setHarvestLevel("shovel", 0, 0);
		PolongniusBlocks.polongnius_block.setHarvestLevel("shovel", 0, 1);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 2);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 3);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 4);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 5);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 6);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 7);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 8);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 9);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 10);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 11);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 12);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 13);
		PolongniusBlocks.polongnius_block.setHarvestLevel("pickaxe", 1, 14);
		PolongniusBlocks.polongnius_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.polongnius_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.fallen_polongnius_meteor.setHarvestLevel("pickaxe", 1);
		PolongniusBlocks.ultra_violet_solar_panel.setHarvestLevel("pickaxe", 1);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_block, ItemBlockPolongnius.class);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_gas);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_slime_block);
		RegisterHelper.registerBlock(PolongniusBlocks.ultra_violet_solar_panel, ItemBlockDesc.class);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_treasure_chest);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_ancient_chest);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_cobblestone_stairs);
		RegisterHelper.registerBlock(PolongniusBlocks.polongnius_dungeon_brick_stairs);
		RegisterHelper.registerBlock(PolongniusBlocks.fallen_polongnius_meteor);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_slime_egg);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_of_milk_cake, ItemBlockDesc.class);
		RegisterHelper.registerBlock(PolongniusBlocks.flonium_torch);
		RegisterHelper.registerBlock(PolongniusBlocks.cheese_of_milk);
		RegisterHelper.registerBlock(PolongniusBlocks.ultra_violet_solar_fake);
	}
}