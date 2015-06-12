/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.recipe;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import micdoodle8.mods.galacticraft.mars.blocks.GCMarsBlocks;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;

public class MPCraftingRecipes
{
	public static void loadRecipes()
	{
		MPCraftingRecipes.addBlockRecipes();
	}

	private static void addBlockRecipes()
	{
		/**@SlabRecipe**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 0), new Object[] { "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 1), new Object[] { "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 2), new Object[] { "XXX", 'X', new ItemStack(GCCoreBlocks.blockMoon, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 3), new Object[] { "XXX", 'X', new ItemStack(GCMarsBlocks.marsBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 4), new Object[] { "XXX", 'X', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 10) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 5), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBasicBlock, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 6), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf, 6, 7), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 11) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf2, 6, 0), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 12) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockStoneSlabHalf2, 6, 1), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWoodSlabHalf, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWoodSlabHalf, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 1) });

		/**@WallRecipe**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(GCMarsBlocks.marsBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 3), new Object[] { "XXX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 10) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 4), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBasicBlock, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 5), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 12) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.blockWall, 6, 6), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 9) });

		/**@StairsSlab**/
		/**@Tin**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.tinStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.tinStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 4) });

		/**@Tin2**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.tin2Stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.tin2Stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(GCCoreBlocks.basicBlock, 1, 3) });

		/**@MoonStone**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.moonStoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(GCCoreBlocks.blockMoon, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.moonStoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(GCCoreBlocks.blockMoon, 1, 4) });

		/**@MarsCobblestone**/
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.marsCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(GCMarsBlocks.marsBlock, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(MPBlocks.marsCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(GCMarsBlocks.marsBlock, 1, 4) });

		/**@DionaStone**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaStoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.dionaStoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 8) });

		/**@SmoothQuontonium**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.smoothQuontoniumStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 11) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.smoothQuontoniumStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 11) });

		/**@QuontoniumBricks**/
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.quontoniumBricksStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 12) });
		MPRecipeUtil.addRecipe(new ItemStack(DionaBlocks.quontoniumBricksStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 12) });

		/**@PolongniusCobblestone**/
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 10) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 10) });

		/**@NibiruCobblestone**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBasicBlock, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBasicBlock, 1, 8) });

		/**@KoentusCobblestone**/
		MPRecipeUtil.addRecipe(new ItemStack(KoentusBlocks.koentusCobblestoneStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(KoentusBlocks.koentusCobblestoneStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentusBasicBlock, 1, 9) });

		/**@WhiteWood**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.whiteWoodStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.whiteWoodStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 0) });

		/**@OrangeWood**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruOrangeWoodStairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruOrangeWoodStairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, 1) });
	}
}