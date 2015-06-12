/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.recipe;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.mars.items.GCMarsItems;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class MPCompressorRecipes
{
	public static void registerCompressorRecipes()
	{
		/**@Diona**/
		CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 4), new ItemStack(DionaItems.dionaBasicItem, 1, 0), new ItemStack(DionaItems.dionaBasicItem, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 5), new ItemStack(DionaItems.dionaBasicItem, 1, 2), new ItemStack(DionaItems.dionaBasicItem, 1, 2));
		CompressorRecipes.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 6), "DDD", "DMD", "DDD", 'D', new ItemStack(GCMarsItems.marsItemBasic, 1, 5), 'M', new ItemStack(GCMarsItems.marsItemBasic, 1, 3));
		CompressorRecipes.addRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 7), "TKQ", "TKQ", "TKQ", 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'K', new ItemStack(DionaItems.dionaBasicItem, 1, 10), 'Q', new ItemStack(DionaItems.dionaBasicItem, 1, 4));
		CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.dionaBasicItem, 1, 10), new ItemStack(DionaItems.dionaBasicItem, 1, 9), new ItemStack(DionaItems.dionaBasicItem, 1, 9));

		/**@Polongnius**/
		CompressorRecipes.addShapelessRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3), new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3));
		CompressorRecipes.addShapelessRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4), new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4));
		CompressorRecipes.addRecipe(new ItemStack(PolongniusItems.rocketPartT4, 1, 2), "PMH", "PMH", "PMH", 'P', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'M', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'H', new ItemStack(DionaItems.dionaBasicItem, 1, 7));

		/**@Nibiru**/
		CompressorRecipes.addShapelessRecipe(new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), new ItemStack(NibiruItems.nibiruBasicItem, 1, 0), new ItemStack(NibiruItems.nibiruBasicItem, 1, 0));
		CompressorRecipes.addShapelessRecipe(new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), new ItemStack(NibiruItems.nibiruBasicItem, 1, 1), new ItemStack(NibiruItems.nibiruBasicItem, 1, 1));
		CompressorRecipes.addRecipe(new ItemStack(NibiruItems.rocketPartT5, 1, 2), "HNR", "HNR", "HNR", 'H', new ItemStack(PolongniusItems.rocketPartT4, 1, 2), 'N', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'R', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2));

		/**@KapteynB**/
		CompressorRecipes.addShapelessRecipe(new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 2), new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 0), new ItemStack(KapteynBItems.kapteynBBasicItem, 1, 0));
	}
}