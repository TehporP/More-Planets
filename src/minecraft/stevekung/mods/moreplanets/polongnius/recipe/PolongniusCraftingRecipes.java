/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stevekung.mods.moreplanets.core.util.MPRecipeUtil;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.items.armor.PolongniusArmorItems;
import stevekung.mods.moreplanets.polongnius.items.tools.PolongniusToolsItems;

public class PolongniusCraftingRecipes
{
	public static void loadRecipes()
	{
		addBlockRecipes();
		addItemRecipes();
		addBlockSmelting();
		addItemSmelting();
		addRocketT4Recipes();
		addRocketT4NoFlagRecipes();
	}

	private static void addBlockRecipes()
	{
		/**@BlocksRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 11), new Object[] { "MMM", "MMM", "MMM", 'M', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 12), new Object[] { "FFF", "FFF", "FFF", 'F', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 13), new Object[] { "PPP", "PPP", "PPP", 'P', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusSlimeBlock, 1), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(PolongniusItems.cheeseSlimeball, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.floniumTorch, 4), new Object[] { "F", "S", 'F', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 0), 'S', new ItemStack(Item.stick, 1, 0) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusBlocks.polongniusSolarPanel, 1, 0), new Object[] { "TST", "TPT", "AWA", 'S', new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 3), 'A', new ItemStack(GCCoreBlocks.aluminumWire, 1, 1), 'T', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'P', new ItemStack(GCCoreItems.flagPole, 1, 0), 'W', new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 0) });
	}

	private static void addItemRecipes()
	{
		/**@ItemsRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 4, 7), new Object[] { "M", "M", 'M', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 4, 8), new Object[] { "P", "P", 'P', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.cheeseOfMilkBlockCake, 1), new Object[] { "CCC", "CMC", "CCC", 'C', new ItemStack(PolongniusItems.polongniusFood, 1, 0), 'M', new ItemStack(Item.bucketMilk, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 9, 3), new Object[] { "M", 'M', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 11) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 9, 0), new Object[] { "F", 'F', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 12) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem, 9, 4), new Object[] { "P", 'P', new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 13) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem2, 2, 2), new Object[] { "GGG", "WWW", "AAA", 'G', new ItemStack(Block.glass, 1, 0), 'W', new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 1), 'A', new ItemStack(GCCoreBlocks.aluminumWire, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 3), new Object[] { "SSS", "AAA", "SSS", 'S', new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 2), 'A', new ItemStack(GCCoreBlocks.aluminumWire, 1, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.cheeseSlimeball, 9), new Object[] { "S", 'S', new ItemStack(PolongniusBlocks.polongniusSlimeBlock, 1) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusItems.rocketPartT4, 1, 1), new Object[] { "DQD", "DCD", "POP", 'Q', new ItemStack(DionaBlocks.dionaBasicBlock, 1, 9), 'P', new ItemStack(DionaItems.dionaBasicItem, 1, 7), 'C', new ItemStack(GCCoreItems.canister, 1, 0), 'D', new ItemStack(DionaItems.dionaBasicItem, 1, 6), 'O', new ItemStack(GCCoreItems.oxygenVent, 1, 0) });

		/**@ArmorRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.polongniusMeteorHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.polongniusMeteorChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.polongniusMeteorLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.polongniusMeteorBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.palladiumHelmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.palladiumChestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.palladiumLeggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.palladiumBoots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.breathablePalladiumHelmet, 1, 0), new Object[] { "O", "P", 'O', new ItemStack(GCCoreItems.oxMask), 'P', new ItemStack(PolongniusArmorItems.palladiumHelmet) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusArmorItems.breathablePolongniusMeteorHelmet, 1, 0), new Object[] { "O", "M", 'O', new ItemStack(GCCoreItems.oxMask), 'M', new ItemStack(PolongniusArmorItems.polongniusMeteorHelmet) });

		/**@ToolsRecipes**/
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.polongniusMeteorSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 7) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
		MPRecipeUtil.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem, 1, 8) });
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 10, new ItemStack(PolongniusBlocks.polongniusBasicBlock, 1, 9), 0.5F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 0, new ItemStack(GCCoreItems.basicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 1, new ItemStack(GCCoreItems.basicItem, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 2, new ItemStack(Item.ingotIron, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 3, new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 4, new ItemStack(PolongniusItems.polongniusBasicItem, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 5, new ItemStack(PolongniusItems.polongniusFood, 1, 0), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.polongniusBasicBlock.blockID, 6, new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusBlocks.fallenPolongniusMeteor.blockID, 0, new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusItems.polongniusBasicItem.itemID, 2, new ItemStack(PolongniusItems.polongniusBasicItem, 1, 4), 0.5F);
		FurnaceRecipes.smelting().addSmelting(PolongniusItems.polongniusFood.itemID, 1, new ItemStack(PolongniusItems.polongniusFood, 1, 2), 0.5F);
	}

	private static void addRocketT4Recipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 0));
		input.put(2, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(3, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(4, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(5, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(6, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(7, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(8, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(9, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(10, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(11, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(12, new ItemStack(PolongniusItems.rocketPartT4, 1, 1));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(PolongniusItems.rocketPartT4, 1, 0));
		input.put(16, new ItemStack(PolongniusItems.rocketPartT4, 1, 1));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 0), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4Recipe(new ItemStack(PolongniusItems.rocketTier4, 1, 3), input2);
	}

	private static void addRocketT4NoFlagRecipes()
	{
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(DionaItems.rocketPartT3, 1, 4));
		input.put(2, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(3, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(4, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(5, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(6, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(7, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(8, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(9, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(10, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(11, new ItemStack(DionaItems.dionaBasicItem, 1, 7));
		input.put(12, new ItemStack(PolongniusItems.rocketPartT4, 1, 1));
		input.put(13, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(14, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(15, new ItemStack(PolongniusItems.rocketPartT4, 1, 0));
		input.put(16, new ItemStack(PolongniusItems.rocketPartT4, 1, 1));
		input.put(17, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(18, new ItemStack(DionaItems.rocketPartT3, 1, 1));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 10), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, null);
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Block.chest));
		input2.put(20, new ItemStack(Block.chest));
		input2.put(21, new ItemStack(Block.chest));
		PolongniusRocketRecipes.addRocketBenchT4NoFlagRecipe(new ItemStack(PolongniusItems.rocketTier4, 1, 13), input2);
	}
}