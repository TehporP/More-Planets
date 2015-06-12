/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.nibiru.items.armor.NibiruArmorItems;
import stevekung.mods.moreplanets.nibiru.items.tools.NibiruToolsItems;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class NibiruCraftingRecipes
{
	public static void loadRecipes()
	{
		addBlockRecipes();
		addItemRecipes();
		addBlockSmelting();
		addItemSmelting();
		addOreDictRecipe();
		addRocketT5Recipes();
		addRocketT5NoFlagRecipes();
	}

	private static void addBlockRecipes()
	{
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruBlockPlanks, 4, 0), new Object[] { "W", 'W', new ItemStack(NibiruBlocks.nibiruBlockWood, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.nibiruBlockPlanks, 4, 1), new Object[] { "W", 'W', new ItemStack(NibiruBlocks.nibiruBlockWood, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.powerCrystalGenerator, 1, 0), new Object[] { "AAA", "TFT", "TWT", 'A', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4), 'F', new ItemStack(Block.furnaceIdle, 1, 0), 'W', new ItemStack(GCCoreBlocks.aluminumWire, 1, 1), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruBlocks.ichoriusTorch, 4, 0), new Object[] { "I", "S", 'I', new ItemStack(NibiruItems.powerCrystal, 1, 0), 'S', new ItemStack(Item.stick, 1, 0) });
	}

	private static void addItemRecipes()
	{
		/**@ItemRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruItems.nibiruBasicItem, 4, 4), new Object[] { "M", "M", 'M', new ItemStack(NibiruItems.nibiruBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruItems.nibiruBasicItem, 4, 5), new Object[] { "P", "P", 'P', new ItemStack(NibiruItems.nibiruBasicItem, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruItems.nibiruFood, 1, 2), new Object[] { "O", "B", 'O', new ItemStack(NibiruItems.nibiruFood, 1, 1), 'B', new ItemStack(Item.glassBottle, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruItems.rocketPartT5, 1, 1), new Object[] { "PDP", "PCP", "NON", 'D', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4), 'P', new ItemStack(PolongniusItems.rocketPartT4, 1, 2), 'C', new ItemStack(GCCoreItems.canister, 1, 0), 'N', new ItemStack(NibiruItems.rocketPartT5, 1, 2), 'O', new ItemStack(GCCoreItems.oxygenVent, 1, 0) });

		/**@ArmorRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.redGemBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.noriumHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.noriumChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.noriumLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.noriumBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.breathableRedGemHelmet, 1, 0), new Object[] { "O", "R", 'R', new ItemStack(NibiruArmorItems.redGemHelmet), 'O', new ItemStack(GCCoreItems.oxMask) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruArmorItems.breathableNoriumHelmet, 1, 0), new Object[] { "O", "N", 'N', new ItemStack(NibiruArmorItems.noriumHelmet), 'O', new ItemStack(GCCoreItems.oxMask) });

		/**@ToolsRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.redGemSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 2), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(NibiruToolsItems.noriumSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiruBasicItem, 1, 3), 'Y', new ItemStack(NibiruItems.nibiruBasicItem, 1, 5) });
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 8, new ItemStack(NibiruBlocks.nibiruBasicBlock, 1, 7), 0.5F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 0, new ItemStack(NibiruItems.powerCrystal, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 1, new ItemStack(NibiruItems.nibiruBasicItem, 1, 1), 0.5F);
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 2, new ItemStack(Item.diamond, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 3, new ItemStack(Item.coal, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(NibiruBlocks.nibiruBasicBlock.blockID, 4, new ItemStack(NibiruItems.nibiruBasicItem, 1, 0), 0.5F);
	}

	private static void addOreDictRecipe()
	{
		OreDictionary.registerOre("plankWood", new ItemStack(NibiruBlocks.nibiruBlockPlanks, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("slabWood", new ItemStack(MPBlocks.blockWoodSlabHalf, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("stairWood", new ItemStack(NibiruBlocks.whiteWoodStairs));
		OreDictionary.registerOre("treeSapling", new ItemStack(NibiruBlocks.nibiruBlockSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(NibiruBlocks.nibiruAppleLeaves, 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 1; i++)
		{
			OreDictionary.registerOre("logWood", new ItemStack(NibiruBlocks.nibiruBlockWood, 1, i));
		}
	}

	private static void addRocketT5Recipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 0));
		input.put(2, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(3, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(4, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(5, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(6, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(7, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(8, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(9, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(10, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(11, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(12, new ItemStack(NibiruItems.rocketPartT5, 1, 1));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(NibiruItems.rocketPartT5, 1, 0));
		input.put(16, new ItemStack(NibiruItems.rocketPartT5, 1, 1));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 0), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5Recipe(new ItemStack(NibiruItems.rocketT5, 1, 3), input2);
	}

	private static void addRocketT5NoFlagRecipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 4));
		input.put(2, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(3, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(4, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(5, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(6, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(7, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(8, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(9, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(10, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(11, new ItemStack(PolongniusItems.rocketPartT4, 1, 2));
		input.put(12, new ItemStack(NibiruItems.rocketPartT5, 1, 1));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(NibiruItems.rocketPartT5, 1, 0));
		input.put(16, new ItemStack(NibiruItems.rocketPartT5, 1, 1));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 10), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		NibiruRocketRecipes.addRocketBenchT5NoFlagRecipe(new ItemStack(NibiruItems.rocketT5, 1, 13), input2);
	}
}