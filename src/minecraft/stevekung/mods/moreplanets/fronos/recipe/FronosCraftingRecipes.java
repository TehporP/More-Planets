/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.fronos.items.tools.FronosToolsItems;

public class FronosCraftingRecipes
{
	public static void loadRecipes()
	{
		addBlockRecipes();
		addItemRecipes();
		addBlockSmelting();
		addItemSmelting();
		addItemExtracting();
		addOreDictRecipe();
	}

	private static void addBlockRecipes()
	{
		/**@BlockRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.cookieBlock, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(Item.cookie, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.vanillaCream, 1, 0), new Object[] { "VV", "VV", 'V', new ItemStack(FronosItems.creamBall, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.chocolateCream, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.creamBall, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.vanillaCreamLayer, 6, 0), new Object[] { "VVV", 'V', new ItemStack(FronosBlocks.vanillaCream, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWoodSlabHalf, 6, 2), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.fronosBlockPlanks, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.fronosBlockPlanks, 4, 0), new Object[] { "C", 'C', new ItemStack(FronosBlocks.fronosBlockLog, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.ovantineBlock, 1, 0), new Object[] { "OO", "OO", 'O', new ItemStack(FronosItems.fronosFood2, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.chocolateBlock, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.fronosFood2, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.pinkCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.orangeCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.greenCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.yellowCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.lightBlueCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.blueCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.lightPurpleCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.purpleCandyTorch, 4, 0), new Object[] { "C", "S", 'C', new ItemStack(Item.coal, 1), 'S', new ItemStack(FronosItems.candyCane, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.strawberryCreamLayer, 6, 0), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.strawberryCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.strawberryCream, 1, 0), new Object[] { "SS", "SS", 'S', new ItemStack(FronosItems.creamBall, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.orangeCream, 1, 0), new Object[] { "OO", "OO", 'O', new ItemStack(FronosItems.creamBall, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf2, 6, 2), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 7), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.chocolateCreamLayer, 6, 0), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.chocolateCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 0), new Object[] { "C", 'C', new ItemStack(FronosBlocks.vanillaCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 1), new Object[] { "C", 'C', new ItemStack(FronosBlocks.chocolateCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 2), new Object[] { "C", 'C', new ItemStack(FronosBlocks.strawberryCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 3), new Object[] { "C", 'C', new ItemStack(FronosBlocks.orangeCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.orangeCreamLayer, 6, 0), new Object[] { "OOO", 'O', new ItemStack(FronosBlocks.orangeCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 1), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 2), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 3), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 4), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.jellyBlock, 1, 5), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.coconutWoodStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronosBlockPlanks, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.coconutWoodStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronosBlockPlanks, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.fronosCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.fronosCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
	}

	private static void addItemRecipes()
	{
		/**@ItemRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.bearryEgg, 1, 0), new Object[] { "S", "E", 'S', new ItemStack(FronosItems.fronosFood, 1, 0), 'E', new ItemStack(Item.egg, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 4), new Object[] { "V", "B", 'V', new ItemStack(FronosItems.creamBall, 1, 0), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 5), new Object[] { "C", "B", 'C', new ItemStack(FronosItems.creamBall, 1, 1), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.strawberrySeed, 1, 0), new Object[] { "S", 'S', new ItemStack(FronosItems.fronosFood, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.greenTopazBucket, 1, 0), new Object[] { "G G", " G ", 'G', new ItemStack(FronosItems.fronosGem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(Item.cookie, 4, 0), new Object[] { "C", 'C', new ItemStack(FronosBlocks.cookieBlock, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 6), new Object[] { "S", "B", 'S', new ItemStack(FronosItems.creamBall, 1, 2), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 7), new Object[] { "S", "B", 'S', new ItemStack(FronosBlocks.strawberryCloud), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 8), new Object[] { "O", "B", 'O', new ItemStack(FronosItems.creamBall, 1, 3), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 9), new Object[] { "WWW", 'W', new ItemStack(FronosItems.fronosGem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.candyBow, 1), new Object[] { " CS", "C S", " CS", 'C', new ItemStack(FronosItems.candyCane, 1), 'S', new ItemStack(Item.silk, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.poisonArrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candyCane, 1), 'S', new ItemStack(FronosBlocks.strawberryCloud, 1), 'P', new ItemStack(FronosBlocks.fronosFlower, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.poisonArrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candyCane, 1), 'S', new ItemStack(FronosBlocks.strawberryCloud, 1), 'P', new ItemStack(FronosBlocks.fronosFlower, 1, 7) });

		/**@ArmorRecipes**/
		//MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		//MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		//MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		//MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });

		/**@ToolsRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candySword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candySpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
	}

	private static void addItemExtracting()
	{
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane1.blockID, 0, new ItemStack(FronosItems.candyCane.itemID, 4, 0), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane1.blockID, 1, new ItemStack(FronosItems.candyCane.itemID, 4, 1), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane1.blockID, 2, new ItemStack(FronosItems.candyCane.itemID, 4, 2), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane1.blockID, 3, new ItemStack(FronosItems.candyCane.itemID, 4, 3), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane2.blockID, 0, new ItemStack(FronosItems.candyCane.itemID, 4, 4), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane2.blockID, 1, new ItemStack(FronosItems.candyCane.itemID, 4, 5), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane2.blockID, 2, new ItemStack(FronosItems.candyCane.itemID, 4, 6), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.candyCane2.blockID, 3, new ItemStack(FronosItems.candyCane.itemID, 4, 7), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.frostedCakeBlock.blockID, 0, new ItemStack(FronosItems.cakeBlock.itemID, 1, 0), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.frostedCakeBlock.blockID, 1, new ItemStack(Item.cake.itemID, 1, 0), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.frostedCakeBlock.blockID, 2, new ItemStack(FronosItems.pinkCakeBlock.itemID, 1, 0), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 0, new ItemStack(FronosItems.jelly.itemID, 4, 0), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 1, new ItemStack(FronosItems.jelly.itemID, 4, 1), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 2, new ItemStack(FronosItems.jelly.itemID, 4, 2), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 3, new ItemStack(FronosItems.jelly.itemID, 4, 3), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 4, new ItemStack(FronosItems.jelly.itemID, 4, 4), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.jellyBlock.blockID, 5, new ItemStack(FronosItems.jelly.itemID, 4, 5), 0.5F);
		FronosCandyExtractorRecipes.extracting().addExtracting(FronosBlocks.chocolateBlock.blockID, 0, new ItemStack(FronosItems.fronosFood2.itemID, 4, 1), 0.5F);
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosStone.blockID, 1, new ItemStack(FronosBlocks.fronosStone, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosSand.blockID, 0, new ItemStack(Block.glass, 1, 0), 0.5F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(FronosItems.fronosFood.itemID, 2, new ItemStack(FronosItems.fronosFood, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 0, new ItemStack(FronosItems.fronosGem, 1, 0), 0.8F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 1, new ItemStack(FronosItems.jelly, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 2, new ItemStack(FronosItems.jelly, 1, 1), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 3, new ItemStack(FronosItems.jelly, 1, 2), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 4, new ItemStack(FronosItems.jelly, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 5, new ItemStack(FronosItems.jelly, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(FronosBlocks.fronosOreBlock.blockID, 6, new ItemStack(FronosItems.jelly, 1, 5), 0.5F);
	}

	private static void addOreDictRecipe()
	{
		OreDictionary.registerOre("plankWood", new ItemStack(FronosBlocks.fronosBlockPlanks, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("stairWood", new ItemStack(FronosBlocks.coconutWoodStairs));
		OreDictionary.registerOre("treeSapling", new ItemStack(FronosBlocks.fronosBlockSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(FronosBlocks.fronosColorizedLeaves, 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 1; i++)
		{
			OreDictionary.registerOre("logWood", new ItemStack(FronosBlocks.fronosBlockLog, 1, i));
		}
	}
}