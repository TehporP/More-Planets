/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.planets.diona.itemblocks.ItemBlockDiona;
import stevekung.mods.stevecore.RegisterHelper;

public class DionaBlocks
{
	public static Block diona_block;
	//public static Block diona_treasure_chest;
	public static Block fronisium_tnt;
	public static Block minion_creeper_egg;
	public static Block diona_cobblestone_stairs;
	public static Block chiseled_quontonium_stairs;
	public static Block quontonium_brick_stairs;
	public static Block diona_dungeon_brick_stairs;
	public static Block diona_ancient_chest;

	public static void init()
	{
		DionaBlocks.initBlocks();
		DionaBlocks.setHarvestLevels();
		DionaBlocks.registerBlocks();
		DionaBlocks.setFireBurn();
	}

	private static void initBlocks()
	{

		DionaBlocks.diona_block = new BlockDiona("diona_block");
		//		DionaBlocks.diona_treasure_chest = new BlockDionaTreasureChest("diona_treasure_chest");
		DionaBlocks.fronisium_tnt = new BlockFronisiumTNT("fronisium_tnt");
		DionaBlocks.minion_creeper_egg = new BlockMinionCreeperEgg("minion_creeper_egg");
		DionaBlocks.diona_cobblestone_stairs = new BlockStairsMP("diona_cobblestone_stairs", 2.5F, StairsCategory.diona_cobblestone, Blocks.stone.getDefaultState());
		DionaBlocks.chiseled_quontonium_stairs = new BlockStairsMP("chiseled_quontonium_stairs", 3.25F, StairsCategory.chiseled_quontonium, Blocks.stone.getDefaultState());
		DionaBlocks.quontonium_brick_stairs = new BlockStairsMP("quontonium_brick_stairs", 3.25F, StairsCategory.quontonium_brick, Blocks.stone.getDefaultState());
		DionaBlocks.diona_dungeon_brick_stairs = new BlockStairsMP("diona_dungeon_brick_stairs", 4.0F, StairsCategory.diona_dungeon_brick, Blocks.stone.getDefaultState());
		DionaBlocks.diona_ancient_chest = new BlockDionaAncientChest("diona_ancient_chest");

	}

	private static void setHarvestLevels()
	{
		DionaBlocks.diona_block.setHarvestLevel("pickaxe", 1);
		DionaBlocks.diona_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
		DionaBlocks.chiseled_quontonium_stairs.setHarvestLevel("pickaxe", 1);
		DionaBlocks.quontonium_brick_stairs.setHarvestLevel("pickaxe", 1);
		DionaBlocks.diona_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
		DionaBlocks.diona_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void setFireBurn()
	{
		RegisterHelper.setFireBurn(DionaBlocks.fronisium_tnt, 15, 100);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(DionaBlocks.diona_block, ItemBlockDiona.class);
		RegisterHelper.registerBlock(DionaBlocks.diona_cobblestone_stairs);
		RegisterHelper.registerBlock(DionaBlocks.quontonium_brick_stairs);
		RegisterHelper.registerBlock(DionaBlocks.chiseled_quontonium_stairs);
		RegisterHelper.registerBlock(DionaBlocks.diona_dungeon_brick_stairs);
		RegisterHelper.registerBlock(DionaBlocks.fronisium_tnt);
		RegisterHelper.registerBlock(DionaBlocks.minion_creeper_egg);
		//		RegisterHelper.registerBlock(DionaBlocks.diona_treasure_chest);
		RegisterHelper.registerBlock(DionaBlocks.diona_ancient_chest);
	}
}