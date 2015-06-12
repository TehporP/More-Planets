/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.GCCoreNasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.polongnius.inventory.PolongniusInventoryRocketBenchT4;
import stevekung.mods.moreplanets.polongnius.inventory.PolongniusInventoryRocketBenchT4NoFlag;

public class PolongniusRocketRecipes
{
	private static List<INasaWorkbenchRecipe> rocketBenchT4Recipes = new ArrayList<INasaWorkbenchRecipe>();
	private static List<INasaWorkbenchRecipe> rocketBenchT4NoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

	public static ItemStack findMatchingSpaceshipT4Recipe(PolongniusInventoryRocketBenchT4 inventoryRocketBench)
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

	public static void addRocketBenchT4Recipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		addT4RocketRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT4RocketRecipe(INasaWorkbenchRecipe recipe)
	{
		rocketBenchT4Recipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT3Recipes()
	{
		return rocketBenchT4Recipes;
	}

	public static ItemStack findMatchingSpaceshipT4NoFlagRecipe(PolongniusInventoryRocketBenchT4NoFlag inventoryRocketBench)
	{
		for (INasaWorkbenchRecipe recipe : getRocketT4NoFlagRecipes())
		{
			if (recipe.matches(inventoryRocketBench))
			{
				return recipe.getRecipeOutput();
			}
		}
		return null;
	}

	public static void addRocketBenchT4NoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
	{
		addT4RocketNoFlagRecipe(new GCCoreNasaWorkbenchRecipe(result, input));
	}

	public static void addT4RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
	{
		rocketBenchT4NoFlagRecipes.add(recipe);
	}

	public static List<INasaWorkbenchRecipe> getRocketT4NoFlagRecipes()
	{
		return rocketBenchT4NoFlagRecipes;
	}
}