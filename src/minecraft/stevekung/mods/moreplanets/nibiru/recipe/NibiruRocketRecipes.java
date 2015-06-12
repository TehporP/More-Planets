/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.GCCoreNasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.nibiru.inventory.NibiruInventoryRocketBenchT5;
import stevekung.mods.moreplanets.nibiru.inventory.NibiruInventoryRocketBenchT5NoFlag;

public class NibiruRocketRecipes
{
	private static List<INasaWorkbenchRecipe> rocketBenchT5Recipes = new ArrayList<INasaWorkbenchRecipe>();
	private static List<INasaWorkbenchRecipe> rocketBenchT5NoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

	public static ItemStack findMatchingSpaceshipT5Recipe(NibiruInventoryRocketBenchT5 inventoryRocketBench)
	{
		for (INasaWorkbenchRecipe recipe : getRocketT5Recipes())
		{
			if (recipe.matches(inventoryRocketBench))
			{
				return recipe.getRecipeOutput();
			}
		}
		return null;
	}

	public static void addRocketBenchT5Recipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		addT5RocketRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT5RocketRecipe(INasaWorkbenchRecipe recipe)
	{
		rocketBenchT5Recipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT5Recipes()
	{
		return rocketBenchT5Recipes;
	}

	public static ItemStack findMatchingSpaceshipT5NoFlagRecipe(NibiruInventoryRocketBenchT5NoFlag inventoryRocketBench)
	{
		for (INasaWorkbenchRecipe recipe : getRocketT5NoFlagRecipes())
		{
			if (recipe.matches(inventoryRocketBench))
			{
				return recipe.getRecipeOutput();
			}
		}
		return null;
	}

	public static void addRocketBenchT5NoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		addT5RocketNoFlagRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT5RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
	{
		rocketBenchT5NoFlagRecipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT5NoFlagRecipes()
	{
		return rocketBenchT5NoFlagRecipes;
	}
}