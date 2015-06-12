/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.nei.handler.MPCircuitFabricatorRecipeHandler;
import stevekung.mods.moreplanets.core.nei.handler.MPSpaceshipRecipeHandler;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIMorePlanetConfig implements IConfigureNEI
{
	private static HashMap<ArrayList<PositionedStack>, PositionedStack> spaceshipRecipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();
	private static HashMap<HashMap<Integer, PositionedStack>, PositionedStack> circuitFabricatorRecipes = new HashMap<HashMap<Integer, PositionedStack>, PositionedStack>();

	@Override
	public void loadConfig()
	{
		this.registerRecipes();
		API.registerRecipeHandler(new MPSpaceshipRecipeHandler());
		API.registerUsageHandler(new MPSpaceshipRecipeHandler());
		API.registerRecipeHandler(new MPCircuitFabricatorRecipeHandler());
		API.registerUsageHandler(new MPCircuitFabricatorRecipeHandler());
	}

	@Override
	public String getName()
	{
		return "More Planets NEI Plugin";
	}

	@Override
	public String getVersion()
	{
		return MorePlanetCore.VERSION;
	}

	public void registerCircuitFabricatorRecipe(HashMap<Integer, PositionedStack> input, PositionedStack output)
	{
		circuitFabricatorRecipes.put(input, output);
	}

	public void registerRocketBenchRecipe(ArrayList<PositionedStack> input, PositionedStack output)
	{
		spaceshipRecipes.put(input, output);
	}

	public static Set<Entry<HashMap<Integer, PositionedStack>, PositionedStack>> getCircuitFabricatorRecipes()
	{
		return circuitFabricatorRecipes.entrySet();
	}

	public static Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRocketBenchRecipes()
	{
		return spaceshipRecipes.entrySet();
	}

	public void registerRecipes()
	{
		this.addSpaceshipRecipes();
		this.addCircuitFabricatorRecipes();
	}

	private void addSpaceshipRecipes()
	{
		final int changey = 15;

		ArrayList<PositionedStack> input1 = new ArrayList<PositionedStack>();

		/**@SpaceshipTier3**/
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 0), 45, -8 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 36, -6 + 0 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 36, -6 + 1 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 36, -6 + 2 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 36, -6 + 3 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 36, -6 + 4 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 54, -6 + 0 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 54, -6 + 1 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 54, -6 + 2 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 54, -6 + 3 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 6), 54, -6 + 4 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 2), 45, 100 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 3), 18, 64 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 3), 72, 64 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 18, 82 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 18, 100 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 72, 82 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 72, 100 + changey));
		this.registerRocketBenchRecipe(input1, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 0), 139, 87 + changey));

		ArrayList<PositionedStack> input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(DionaItems.rocketT3, 1, 3), 139, 87 + changey));

		/**@SpaceshipTier4**/
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 0), 45, -8 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 36, -6 + 0 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 36, -6 + 1 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 36, -6 + 2 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 36, -6 + 3 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 36, -6 + 4 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 54, -6 + 0 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 54, -6 + 1 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 54, -6 + 2 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 54, -6 + 3 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.dionaBasicItem.itemID, 1, 7), 54, -6 + 4 * 18 + 16 + changey));
		input1.add(new PositionedStack(new ItemStack(PolongniusItems.rocketPartT4.itemID, 1, 0), 45, 100 + changey));
		input1.add(new PositionedStack(new ItemStack(PolongniusItems.rocketPartT4.itemID, 1, 1), 18, 64 + changey));
		input1.add(new PositionedStack(new ItemStack(PolongniusItems.rocketPartT4.itemID, 1, 1), 72, 64 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 18, 82 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 18, 100 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 72, 82 + changey));
		input1.add(new PositionedStack(new ItemStack(DionaItems.rocketPartT3.itemID, 1, 1), 72, 100 + changey));
		this.registerRocketBenchRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 0), 139, 87 + changey));

		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 1), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 2), 139, 87 + changey));

		input2 = new ArrayList<PositionedStack>(input1);
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
		input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
		this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.rocketTier4, 1, 3), 139, 87 + changey));
	}

	private void addCircuitFabricatorRecipes()
	{
		HashMap<Integer, PositionedStack> input1 = new HashMap<Integer, PositionedStack>();
		input1.put(0, new PositionedStack(new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3), 10, 22));
		input1.put(1, new PositionedStack(new ItemStack(GCCoreItems.basicItem, 1, 2), 69, 51));
		input1.put(2, new PositionedStack(new ItemStack(GCCoreItems.basicItem, 1, 2), 69, 69));
		input1.put(3, new PositionedStack(new ItemStack(Item.redstone), 117, 51));
		input1.put(4, new PositionedStack(new ItemStack(Item.redstoneRepeater), 140, 25));
		this.registerCircuitFabricatorRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 0), 147, 91));

		HashMap<Integer, PositionedStack> input2 = new HashMap<Integer, PositionedStack>(input1);
		input2.put(0, new PositionedStack(new ItemStack(Item.diamond), 10, 22));
		input2.put(4, new PositionedStack(new ItemStack(PolongniusItems.polongniusBasicItem, 1, 1), 140, 25));
		this.registerCircuitFabricatorRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.polongniusBasicItem2, 9, 1), 147, 91));
	}
}