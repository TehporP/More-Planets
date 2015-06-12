/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.recipe;

import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;

public class KoentusCraftingRecipes
{
	public static void loadRecipes()
	{
		KoentusCraftingRecipes.addBlockRecipes();
		KoentusCraftingRecipes.addItemRecipes();
		KoentusCraftingRecipes.addBlockSmelting();
		KoentusCraftingRecipes.addItemSmelting();
	}

	private static void addBlockRecipes()
	{
		// Normal Block Stuff
		MPRecipeUtil.addRecipe(new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 10), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(KoentusItems.koentusBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 11), new Object[] { "MMM", "MMM", "MMM", 'M', new ItemStack(KoentusItems.koentusBasicItem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(KoentusBlocks.whiteCrystalTorch, 4), new Object[] { "W", "S", 'W', new ItemStack(KoentusItems.koentusBasicItem, 1, 0), 'S', new ItemStack(Item.stick, 1) });
	}

	private static void addItemRecipes()
	{
		// Normal Item Stuff
		MPRecipeUtil.addRecipe(new ItemStack(KoentusItems.koentusBasicItem, 9, 0), new Object[] { "C", 'C', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 10) });
		MPRecipeUtil.addRecipe(new ItemStack(KoentusItems.koentusBasicItem, 9, 1), new Object[] { "M", 'M', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 11) });

		/*// Armor Stuff
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });

		// Tools Stuff
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });*/
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 9, new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 8), 0.5F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 0, new ItemStack(GCCoreItems.basicItem, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 1, new ItemStack(GCCoreItems.meteoricIronIngot, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 2, new ItemStack(KoentusItems.koentusBasicItem, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 3, new ItemStack(KoentusItems.koentusBasicItem, 1, 1), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 4, new ItemStack(KoentusItems.koentusBasicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.koentusBasicBlock.blockID, 5, new ItemStack(KoentusItems.koentusBasicItem, 1, 2), 0.5F);
		FurnaceRecipes.smelting().addSmelting(KoentusBlocks.fallenKoentusMeteor.blockID, 0, new ItemStack(DionaItems.dionaBasicItem, 1, 9), 0.5F);
	}
}