/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class CraftingRecipesVenus
{
	public static void loadRecipes()
	{
		CraftingRecipesVenus.addBlockRecipes();
		CraftingRecipesVenus.addItemRecipes();
		CraftingRecipesVenus.addBlockSmelting();
		CraftingRecipesVenus.addItemSmelting();
		CraftingRecipesVenus.registerOre();
	}

	private static void addBlockRecipes()
	{
		// Blocks
		GameRegistry.addRecipe(new ItemStack(MPBlocks.chondrite_rock, 4, 1), new Object[] { "CC", "CC", 'C', new ItemStack(MPBlocks.chondrite_rock, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(VenusBlocks.sulfur_torch, 4), new Object[] { "D", "S", 'D', new ItemStack(SiriusBItems.sirius_b_item, 1, 2), 'S', new ItemStack(Items.stick) });
		GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_block, 4, 12), new Object[] { "SS", "SS", 'S', new ItemStack(VenusBlocks.venus_block, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_block, 1, 11), new Object[] { "AAA", "AAA", "AAA" ,'A', new ItemStack(VenusItems.venus_item, 1, 0) });
		// Koentus Cobblestone Stairs
		//		GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });

		// Slabs
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 5), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 6), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 7), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 4), new Object[] { "CCC", 'C', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 3), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });

		// Walls
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 5), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 6), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 7), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });
		//		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 3), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });
	}

	private static void addItemRecipes()
	{
		// Items
		GameRegistry.addShapelessRecipe(new ItemStack(VenusItems.venus_item, 9, 0), new ItemStack(VenusBlocks.venus_block, 1, 11) );
		//		GameRegistry.addRecipe(new ItemStack(VenusItems.sulfur_battery, 1, 99), new Object[] { " I ", "IUI", "IGI", 'I', new ItemStack(GCItems.basicItem, 1, 7), 'U', new ItemStack(Items.redstone), 'G', new ItemStack(SiriusBItems.sirius_b_item, 1, 2) });
		//		GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 9, 1), new ItemStack(KoentusBlocks.koentus_block, 1, 10) );
		//		GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 9, 4), new ItemStack(KoentusBlocks.koentus_block, 1, 15) );
		//		GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_meteor_chunk, 3), new ItemStack(KoentusItems.koentus_item, 1, 3) );
		//		GameRegistry.addRecipe(new ItemStack(KoentusItems.crystal_door, 3), new Object[] { "CC ", "CC ", "CC ", 'C', new ItemStack(KoentusBlocks.crystal_wooden_planks) });

		// Armor
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableKoentusMeteorHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6), 'O', new ItemStack(GCItems.oxMask) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableWhiteCrystalHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5), 'O', new ItemStack(GCItems.oxMask) });

		// Tools
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		//		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
	}

	private static void addBlockSmelting()
	{
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 3), new ItemStack(VenusBlocks.venus_block, 1, 2), 0.4F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 12), new ItemStack(VenusBlocks.venus_block, 1, 13), 0.4F);
	}

	private static void addItemSmelting()
	{
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(MPBlocks.chondrite_rock, 1, 0), new ItemStack(MPBlocks.chondrite_rock, 1, 2), 0.4F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 4), new ItemStack(SiriusBItems.sirius_b_item, 1, 3), 0.8F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 5), new ItemStack(VenusItems.venus_item, 1, 0), 0.8F);
		//FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
		//FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 7), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 8), new ItemStack(Items.coal), 0.8F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 9), new ItemStack(Items.iron_ingot), 0.8F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_block, 1, 10), new ItemStack(Items.gold_ingot), 0.8F);
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(VenusBlocks.venus_redstone_ore), new ItemStack(Items.redstone), 0.7F);
	}

	private static void registerOre()
	{
		OreDictionary.registerOre("sand", new ItemStack(VenusBlocks.venus_sand, 1, 0));
	}
}