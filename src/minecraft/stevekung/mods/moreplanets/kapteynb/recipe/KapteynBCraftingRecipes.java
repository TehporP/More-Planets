/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.recipe;

import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;

public class KapteynBCraftingRecipes
{
	public static void loadRecipes()
	{
		addBlockRecipes();
		addItemRecipes();
		addBlockSmelting();
		addItemSmelting();
	}

	private static void addBlockRecipes()
	{
		/**@BlockRecipes**/
		//TODO MPRecipeUtil.addRecipe(new ItemStack(KapteynBBlocks.kapteynBOreBlock, 1, 0), new Object[] { "III", "III", "III", 'I', new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBBlocks.kapteynBOreBlock, 1, 2), new Object[] { "FFF", "FFF", "FFF", 'F', new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBBlocks.kapteynBOreBlock, 1, 3), new Object[] { "UUU", "UUU", "UUU", 'U', new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 8), new Object[] { "CCC", "CCC", 'C', new ItemStack(KapteynBBlocks.kapteynBBasicBlock, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf2, 6, 3), new Object[] { "CCC", 'C', new ItemStack(KapteynBBlocks.kapteynBBasicBlock, 1, 3) });
		/*MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamVanilla, 1, 0), new Object[] { "VV", "VV", 'V', new ItemStack(FronosItems.creamBall, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamChocolate, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.creamBall, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamVanillaLayer, 6, 0), new Object[] { "VVV", 'V', new ItemStack(FronosBlocks.creamVanilla, 1, 0) });
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
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.fronosGlassPane, 16, 0), new Object[] { "GGG", "GGG", 'G', new ItemStack(FronosBlocks.fronosGlass, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.strawberryCreamLayer, 6, 0), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.strawberryCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.strawberryCream, 1, 0), new Object[] { "SS", "SS", 'S', new ItemStack(FronosItems.creamBall, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.orangeCream, 1, 0), new Object[] { "OO", "OO", 'O', new ItemStack(FronosItems.creamBall, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf2, 6, 2), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 7), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronosStone, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamChocolateLayer, 6, 0), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.creamChocolate, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 0), new Object[] { "C", 'C', new ItemStack(FronosBlocks.creamVanilla, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 1), new Object[] { "C", 'C', new ItemStack(FronosBlocks.creamChocolate, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 2), new Object[] { "C", 'C', new ItemStack(FronosBlocks.strawberryCream, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosBlocks.creamHead, 2, 3), new Object[] { "C", 'C', new ItemStack(FronosBlocks.orangeCream, 1) });*/

		MPRecipeUtil.addRecipe(new ItemStack(KapteynBBlocks.kapteynBCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteynBBasicBlock, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBBlocks.kapteynBCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteynBBasicBlock, 1, 3) });
	}

	private static void addItemRecipes()
	{
		// Normal Item Stuff
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBItems.uraniumBattery, 1, 99), new Object[] { " I ", "IUI", "IGI", 'I', new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 2), 'U', new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 1), 'G', new ItemStack(NibiruItems.powerCrystal) });
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBItems.kapteynBBasicItem, 9, 0), new Object[] { "F", 'F', new ItemStack(KapteynBBlocks.kapteynBOreBlock, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(KapteynBItems.kapteynBBasicItem, 9, 1), new Object[] { "U", 'U', new ItemStack(KapteynBBlocks.kapteynBOreBlock, 1, 3) });
		/*MPRecipeUtil.addRecipe(new ItemStack(FronosItems.strawberrySeed, 1, 0), new Object[] { "S", 'S', new ItemStack(FronosItems.fronosFood, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.emptyJar, 4, 0), new Object[] { "G G", "G G", "GGG", 'G', new ItemStack(Block.glass) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.greenTopazBucket, 1, 0), new Object[] { "G G", " G ", 'G', new ItemStack(FronosItems.fronosGem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(Item.cookie, 4, 0), new Object[] { "C", 'C', new ItemStack(FronosBlocks.cookieBlock, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 6), new Object[] { "S", "B", 'S', new ItemStack(FronosItems.creamBall, 1, 2), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 7), new Object[] { "S", "B", 'S', new ItemStack(FronosBlocks.strawberryCloud), 'B', new ItemStack(Item.bowlEmpty, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.fronosFood, 1, 8), new Object[] { "WWW", 'W', new ItemStack(FronosItems.fronosGem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.candyBow, 1), new Object[] { " CS", "C S", " CS", 'C', new ItemStack(FronosItems.candyCane, 1), 'S', new ItemStack(Item.silk, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosItems.poisonArrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candyCane, 1), 'S', new ItemStack(FronosBlocks.strawberryCloud, 1), 'P', new ItemStack(FronosBlocks.fronosFlower, 1, 5) });

		// Armor Stuff
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });*/

		// Tools Stuff
		/*MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyHoe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candyPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candySword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candySpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosBlocks.candyCane1), 'Y', new ItemStack(FronosItems.candyCane, 1, 0) });*/
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 3, new ItemStack(KapteynBBlocks.kapteynBBasicBlock, 1, 2), 0.5F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 8, new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 9, new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 1), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 10, new ItemStack(GCCoreItems.basicItem, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 11, new ItemStack(GCCoreItems.basicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 12, new ItemStack(Item.diamond, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 13, new ItemStack(Item.ingotGold, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 14, new ItemStack(Item.redstone), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KapteynBBlocks.kapteynBBasicBlock.blockID, 15, new ItemStack(Item.redstone), 0.5F);
	}
}