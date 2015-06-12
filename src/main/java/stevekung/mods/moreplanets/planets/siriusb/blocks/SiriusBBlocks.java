/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.common.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.planets.siriusb.fluids.BlockFluidSiriusLava;
import stevekung.mods.moreplanets.planets.siriusb.itemblocks.ItemBlockSiriusB;
import stevekung.mods.stevecore.RegisterHelper;

public class SiriusBBlocks
{
	public static BlockSiriusFire sirius_fire;
	public static Block sirius_b_block;
	public static Block sirius_blaze_egg;
	//public static Block sirius_b_treasure_chest;
	public static Block sirius_b_ancient_chest;
	public static Block sirius_obsidian;
	public static Block sirius_b_carbon_cobblestone_stairs;
	public static Block sirius_b_dungeon_brick_stairs;
	public static Block sirius_glowstone;
	public static Block sirius_redstone_lamp_off;
	public static Block sirius_redstone_lamp_on;
	public static Block sirius_lava;

	public static Fluid sirius_lava_fluid;

	public static void init()
	{
		SiriusBBlocks.initBlocks();
		SiriusBBlocks.setHarvestLevels();
		SiriusBBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		SiriusBBlocks.sirius_b_block = new BlockSiriusB("sirius_b_block");
		SiriusBBlocks.sirius_blaze_egg = new BlockSiriusBlazeEgg("sirius_blaze_egg");
		//SiriusBBlocks.sirius_b_treasure_chest = new BlockSiriusBTreasureChest("sirius_b_treasure_chest");
		SiriusBBlocks.sirius_fire = new BlockSiriusFire("sirius_fire");
		SiriusBBlocks.sirius_b_ancient_chest = new BlockSiriusBAncientChest("sirius_b_ancient_chest");
		SiriusBBlocks.sirius_obsidian = new BlockBaseMP("sirius_obsidian", Material.rock).setHardness(70.0F).setResistance(5000.0F).setLightLevel(1.0F);
		SiriusBBlocks.sirius_b_carbon_cobblestone_stairs = new BlockStairsMP("sirius_b_carbon_cobblestone_stairs", 4.0F, StairsCategory.sirius_carbon_cobblestone, Blocks.stone.getDefaultState());
		SiriusBBlocks.sirius_b_dungeon_brick_stairs = new BlockStairsMP("sirius_b_dungeon_brick_stairs", 4.0F, StairsCategory.sirius_dungeon_brick, Blocks.stone.getDefaultState());
		SiriusBBlocks.sirius_glowstone = new BlockSiriusGlowstone("sirius_glowstone");
		SiriusBBlocks.sirius_redstone_lamp_off = new BlockSiriusRedstoneLamp("sirius_redstone_lamp", false);
		SiriusBBlocks.sirius_redstone_lamp_on = new BlockSiriusRedstoneLamp("sirius_redstone_lamp_on", true);

		SiriusBBlocks.sirius_lava_fluid = new Fluid("sirius_lava").setBlock(SiriusBBlocks.sirius_lava).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
		FluidRegistry.registerFluid(SiriusBBlocks.sirius_lava_fluid);
		SiriusBBlocks.sirius_lava = new BlockFluidSiriusLava("sirius_lava");
	}

	private static void setHarvestLevels()
	{
		SiriusBBlocks.sirius_obsidian.setHarvestLevel("pickaxe", 1);
		SiriusBBlocks.sirius_b_block.setHarvestLevel("pickaxe", 1);
		SiriusBBlocks.sirius_b_carbon_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
		SiriusBBlocks.sirius_b_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
		SiriusBBlocks.sirius_b_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_b_block, ItemBlockSiriusB.class);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_obsidian);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_glowstone);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_redstone_lamp_off);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_redstone_lamp_on);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_b_ancient_chest);
		//RegisterHelper.registerBlock(SiriusBBlocks.sirius_b_treasure_chest);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_blaze_egg);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_b_carbon_cobblestone_stairs);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_b_dungeon_brick_stairs);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_fire);
		RegisterHelper.registerBlock(SiriusBBlocks.sirius_lava);
	}
}