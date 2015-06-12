/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.planets.pluto.fluids.BlockFluidMethane;
import stevekung.mods.moreplanets.planets.pluto.fluids.BlockFluidNitrogen;
import stevekung.mods.moreplanets.planets.pluto.itemblocks.ItemBlockPluto;
import stevekung.mods.stevecore.RegisterHelper;

public class PlutoBlocks
{
	public static Block pluto_block;
	public static Block xeonium_glowstone;
	public static Block xeonium_torch;
	public static Block frozen_methane_block;
	public static Block frozen_nitrogen_block;
	public static Block pluto_ancient_chest;
	//public static Block pluto_treasure_chest;
	public static Block space_potato_block;
	public static Block liquid_methane;
	public static Block liquid_nitrogen;

	public static Fluid liquid_methane_fluid;
	public static Fluid liquid_nitrogen_fluid;

	public static void init()
	{
		PlutoBlocks.initBlocks();
		PlutoBlocks.setHarvestLevels();
		PlutoBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		PlutoBlocks.pluto_block = new BlockPluto("pluto_block");
		PlutoBlocks.xeonium_glowstone = new BlockXeoniumGlowstone("xeonium_glowstone");
		PlutoBlocks.xeonium_torch = new BlockXeoniumTorch("xeonium_torch");
		PlutoBlocks.frozen_methane_block = new BlockFrozenMethane("frozen_methane_block");
		PlutoBlocks.frozen_nitrogen_block = new BlockFrozenNitrogen("frozen_nitrogen_block");
		PlutoBlocks.pluto_ancient_chest = new BlockPlutoAncientChest("pluto_ancient_chest");
		//PlutoBlocks.pluto_treasure_chest = new BlockPlutoTreasureChest("pluto_treasure_chest");
		PlutoBlocks.space_potato_block = new BlockSpacePotato("space_potato_block");

		PlutoBlocks.liquid_methane_fluid = new Fluid("liquid_methane_fluid").setBlock(PlutoBlocks.liquid_methane).setViscosity(3000);
		PlutoBlocks.liquid_nitrogen_fluid = new Fluid("liquid_nitrogen_fluid").setBlock(PlutoBlocks.liquid_nitrogen).setViscosity(3000);
		FluidRegistry.registerFluid(PlutoBlocks.liquid_methane_fluid);
		FluidRegistry.registerFluid(PlutoBlocks.liquid_nitrogen_fluid);
		PlutoBlocks.liquid_methane = new BlockFluidMethane("liquid_methane_fluid");
		PlutoBlocks.liquid_nitrogen = new BlockFluidNitrogen("liquid_nitrogen_fluid");
	}

	private static void setHarvestLevels()
	{
		PlutoBlocks.pluto_block.setHarvestLevel("pickaxe", 3);
		PlutoBlocks.frozen_methane_block.setHarvestLevel("pickaxe", 2);
		PlutoBlocks.frozen_nitrogen_block.setHarvestLevel("pickaxe", 2);
		PlutoBlocks.pluto_ancient_chest.setHarvestLevel("axe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(PlutoBlocks.pluto_block, ItemBlockPluto.class);
		RegisterHelper.registerBlock(PlutoBlocks.xeonium_glowstone);
		RegisterHelper.registerBlock(PlutoBlocks.frozen_methane_block);
		RegisterHelper.registerBlock(PlutoBlocks.frozen_nitrogen_block);
		RegisterHelper.registerBlock(PlutoBlocks.pluto_ancient_chest);
		//RegisterHelper.registerBlock(PlutoBlocks.pluto_treasure_chest);
		RegisterHelper.registerBlock(PlutoBlocks.xeonium_torch);
		RegisterHelper.registerBlock(PlutoBlocks.liquid_methane);
		RegisterHelper.registerBlock(PlutoBlocks.liquid_nitrogen);
		RegisterHelper.registerBlock(PlutoBlocks.space_potato_block);
	}
}