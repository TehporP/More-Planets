/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.GCCoreNasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.diona.inventory.DionaInventoryRocketBenchT3;
import stevekung.mods.moreplanets.diona.inventory.DionaInventoryRocketBenchT3NoFlag;

public class DionaRocketRecipes
{
	private static List<INasaWorkbenchRecipe> rocketBenchT3Recipes = new ArrayList<INasaWorkbenchRecipe>();
	private static List<INasaWorkbenchRecipe> rocketBenchT3NoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

	public static ItemStack findMatchingSpaceshipT3Recipe(DionaInventoryRocketBenchT3 inventoryRocketBench)
	{
		for (INasaWorkbenchRecipe recipe : getRocketT3Recipes())
		{
			if (recipe.matches(inventoryRocketBench))
			{
				return recipe.getRecipeOutput();
			}
		}
		return null;
	}

	public static void addRocketBenchT3Recipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		DionaRocketRecipes.addT3RocketRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT3RocketRecipe(INasaWorkbenchRecipe recipe)
	{
		DionaRocketRecipes.rocketBenchT3Recipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT3Recipes()
	{
		return DionaRocketRecipes.rocketBenchT3Recipes;
	}

	public static ItemStack findMatchingSpaceshipT3NoFlagRecipe(DionaInventoryRocketBenchT3NoFlag inventoryRocketBench)
	{
		for (INasaWorkbenchRecipe recipe : getRocketT3NoFlagRecipes())
		{
			if (recipe.matches(inventoryRocketBench))
			{
				return recipe.getRecipeOutput();
			}
		}
		return null;
	}

	public static void addRocketBenchT3NoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		addT3RocketNoFlagRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT3RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
	{
		rocketBenchT3NoFlagRecipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT3NoFlagRecipes()
	{
		return rocketBenchT3NoFlagRecipes;
	}
}