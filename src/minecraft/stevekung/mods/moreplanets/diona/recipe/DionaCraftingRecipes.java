/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import micdoodle8.mods.galacticraft.mars.items.GCMarsItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.diona.items.armor.DionaArmorItems;
import stevekung.mods.moreplanets.diona.items.tools.DionaToolsItems;

public class DionaCraftingRecipes
{
	public static void loadRecipes()
	{
		DionaCraftingRecipes.addRocketT3Recipes();
		DionaCraftingRecipes.addRocketT3NoFlagRecipes();
		DionaCraftingRecipes.addBlockRecipes();
		DionaCraftingRecipes.addItemRecipes();
		DionaCraftingRecipes.addItemSmelting();
	}

	private static void addBlockRecipes()
	{
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.fronisiumTnt, 1), new Object[] { "GFG", "FGF", "GFG", 'G', new ItemStack(Item.gunpowder, 1, 11), 'F', new ItemStack(DionaItems.dionaBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.quontoniumBricksFence, 4), new Object[] { "SSS", "SSS", 'S', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaBasicBlock, 1, 9), new Object[] { "QQQ", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaBasicBlock, 1, 10), new Object[] { "BBB", "BBB", "BBB", 'B', new ItemStack(DionaItems.dionaBasicItem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaBasicBlock, 4, 11), new Object[] { "   ", " S ", " Q ", 'S', new ItemStack(Block.stone, 1, 0), 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaBasicBlock, 4, 12), new Object[] { "QQ", "QQ", 'Q', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 11) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaBasicBlock, 1, 13), new Object[] { "Q", "Q", 'Q', new ItemStack(MPBlocks.blockStoneSlabHalf2, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.titaniumFence, 12, 0), new Object[] { "TTT", "TTT", 'T', new ItemStack(DionaItems.dionaBasicItem, 12, 3) });
	}

	private static void addItemRecipes()
	{
		/**@ItemRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 3, 11), new Object[] { "RRR", "CCC", "RRR", 'C', new ItemStack(GCCoreItems.canvas, 1, 0), 'R', new ItemStack(Item.dyePowder, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 3, 12), new Object[] { "WWW", "CCC", "WWW", 'C', new ItemStack(GCCoreItems.canvas, 1, 0), 'W', new ItemStack(Item.dyePowder, 1, 15) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 3, 13), new Object[] { "BBB", "CCC", "BBB", 'C', new ItemStack(GCCoreItems.canvas, 1, 0), 'B', new ItemStack(Item.dyePowder, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 14), new Object[] { "RRR", "WWW", "BBB", 'R', new ItemStack(DionaItems.dionaBasicItem, 1, 11), 'W', new ItemStack(DionaItems.dionaBasicItem, 1, 12), 'B', new ItemStack(DionaItems.dionaBasicItem, 1, 13) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 15), new Object[] { "BBB", "WWW", "RRR", 'R', new ItemStack(DionaItems.dionaBasicItem, 1, 11), 'W', new ItemStack(DionaItems.dionaBasicItem, 1, 12), 'B', new ItemStack(DionaItems.dionaBasicItem, 1, 13) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 4, 16), new Object[] { "Q", "Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 4, 17), new Object[] { "F", "F", 'F', new ItemStack(DionaItems.dionaBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.thailandFlag, 1), new Object[] { "PTT", "PBB", "P  ", 'P', new ItemStack(GCCoreItems.flagPole, 1, 0), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 14), 'B', new ItemStack(DionaItems.dionaBasicItem, 1, 15) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 0), new Object[] { " T ", " D ", "D D", 'T', new ItemStack(DionaItems.thailandFlag, 1, 0), 'D', new ItemStack(DionaItems.dionaBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 3), new Object[] { "DMD", "DCD", "TAT", 'M', new ItemStack(Block.blockDiamond, 1, 0), 'D', new ItemStack(GCMarsItems.marsItemBasic, 1, 5), 'C', new ItemStack(GCCoreItems.canister, 1, 0), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'A', new ItemStack(GCCoreItems.oxygenVent, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 1), new Object[] { " D ", "TDT", "T T", 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'D', new ItemStack(GCMarsItems.marsItemBasic, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 2), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Block.stoneButton, 1), 'S', new ItemStack(Item.flintAndSteel, 1), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'A', new ItemStack(GCCoreItems.oxygenVent, 1, 0), 'C', new ItemStack(GCCoreItems.canister, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 2), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Block.stoneButton, 1), 'S', new ItemStack(Item.flintAndSteel, 1), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'A', new ItemStack(GCCoreItems.oxygenVent, 1, 0), 'C', new ItemStack(GCCoreItems.canister, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.baronsiumBucket, 1, 0), new Object[] { "B B", " B ", 'B', new ItemStack(DionaItems.dionaBasicItem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.rocketPartT3, 1, 4), new Object[] { " T ", " D ", "D D", 'T', new ItemStack(Block.torchRedstoneActive), 'D', new ItemStack(DionaItems.dionaBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 9, 0), new Object[] { "Q", 'Q', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 9, 1), new Object[] { "B", 'B', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 10) });

		/**@ArmorRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.quontoniumHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.quontoniumChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.quontoniumLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.quontoniumBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.fronisiumHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.fronisiumChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.fronisiumLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.fronisiumBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.breathableQuontoniumHelmet, 1, 0), new Object[] { "O", "Q", 'O', new ItemStack(GCCoreItems.oxMask), 'Q', new ItemStack(DionaArmorItems.quontoniumHelmet) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaArmorItems.breathableFronisiumHelmet, 1, 0), new Object[] { "O", "F", 'O', new ItemStack(GCCoreItems.oxMask), 'F', new ItemStack(DionaArmorItems.fronisiumHelmet) });

		/**@ToolsRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.quontoniumSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 4), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 16) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaToolsItems.fronisiumSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(DionaItems.dionaBasicItem, 1, 5), 'Y', new ItemStack(DionaItems.dionaBasicItem, 1, 17) });
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 0, new ItemStack(DionaItems.dionaBasicItem, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 1, new ItemStack(GCCoreItems.basicItem, 1, 2), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 2, new ItemStack(GCCoreItems.basicItem, 1, 5), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 3, new ItemStack(DionaItems.dionaBasicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 4, new ItemStack(DionaItems.dionaBasicItem, 1, 1), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaBlocks.dionaBasicBlock.blockID, 5, new ItemStack(DionaItems.dionaBasicItem, 1, 2), 0.5F);
		FurnaceRecipes.smelting().addSmelting(DionaItems.dionaBasicItem.itemID, 8, new ItemStack(DionaItems.dionaBasicItem, 1, 9), 0.5F);
	}

	private static void addRocketT3Recipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 0));
		input.put(2, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(3, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(4, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(5, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(6, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(7, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(8, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(9, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(10, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(11, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(12, new ItemStack(DionaItems.rocketPartT3, 1, 3));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(DionaItems.rocketPartT3, 1, 2));
		input.put(16, new ItemStack(DionaItems.rocketPartT3, 1, 3));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 0), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3Recipe(new ItemStack(DionaItems.rocketT3, 1, 3), input2);
	}

	private static void addRocketT3NoFlagRecipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 4));
		input.put(2, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(3, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(4, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(5, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(6, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(7, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(8, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(9, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(10, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(11, new ItemStack(DionaItems.dionaBasicItem, 1, 6));
		input.put(12, new ItemStack(DionaItems.rocketPartT3, 1, 3));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(DionaItems.rocketPartT3, 1, 2));
		input.put(16, new ItemStack(DionaItems.rocketPartT3, 1, 3));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 10), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		DionaRocketRecipes.addRocketBenchT3NoFlagRecipe(new ItemStack(DionaItems.rocketT3, 1, 13), input2);
	}
}