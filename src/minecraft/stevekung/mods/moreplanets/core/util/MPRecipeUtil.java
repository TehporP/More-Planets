/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MPRecipeUtil
{
	@SuppressWarnings("unchecked")
	public static void addRecipe(ItemStack result, Object[] obj)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, obj));

		Object[] newIngredients = Arrays.copyOf(obj, obj.length);
		boolean changed = false;

		if (changed)
		{
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, newIngredients));
		}

		newIngredients = Arrays.copyOf(obj, obj.length);
		changed = false;

		if (changed)
		{
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, newIngredients));
		}
	}
}