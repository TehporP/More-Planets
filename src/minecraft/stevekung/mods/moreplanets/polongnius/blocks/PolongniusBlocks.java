/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.items.MPItemRarity;
import stevekung.mods.moreplanets.polongnius.fluids.PolongniusCheeseOfMilkFluid;
import stevekung.mods.moreplanets.polongnius.fluids.blocks.PolongniusBlockFluidCheeseOfMilk;
import stevekung.mods.moreplanets.polongnius.itemblocks.PolongniusItemBasicBlock;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;

public class PolongniusBlocks
{
	public static Block polongniusBasicBlock;
	public static Block polongniusTreasureChest;
	public static Block fallenPolongniusMeteor;
	public static Block floniumTorch;
	public static Block cheeseOfMilkCake;
	public static Block polongniusSolarPanel;
	public static Block polongniusCobblestoneStairs;
	public static Block cheeseOfMilkFluidBlock;
	public static Block polongniusAncientChest;
	public static Block polongniusSlimeBlock;
	public static Block cheeseSlimeEgg;

	/**@Fluid**/
	public static Fluid cheeseOfMilkFluid;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		PolongniusBlocks.polongniusBasicBlock = new PolongniusBlock(PolongniusConfigManager.idBlockPolongnius).setUnlocalizedName("pn");
		PolongniusBlocks.polongniusTreasureChest = new PolongniusBlockT4TreasureChest(PolongniusConfigManager.idBlockPolongniusTreasureChest).setUnlocalizedName("polongniusTreasureChest");
		PolongniusBlocks.fallenPolongniusMeteor = new PolongniusBlockFallenMeteor(PolongniusConfigManager.idBlockPolongniusFallenMeteor).setUnlocalizedName("polongniusFallenMeteor");
		PolongniusBlocks.cheeseOfMilkCake = new PolongniusBlockCheeseOfMilkCake(PolongniusConfigManager.idBlockCheeseOfMilkCake, Material.cake).setUnlocalizedName("cheeseOfMilkCake");
		PolongniusBlocks.floniumTorch = new PolongniusBlockFloniumTorch(PolongniusConfigManager.idBlockFloniumTorch).setUnlocalizedName("floniumTorch");
		PolongniusBlocks.polongniusSolarPanel = new PolongniusBlockSolar(PolongniusConfigManager.idBlockSolarPanel).setUnlocalizedName("polongniusSolarPanel");
		PolongniusBlocks.polongniusCobblestoneStairs = new BlockStairsMP(PolongniusConfigManager.idBlockPolongniusCobblestoneStairs, GCCoreBlocks.blockMoon, StairsCategory.POLONGNIUS).setHardness(2.0F).setUnlocalizedName("polongniusCobblestoneStairs");
		PolongniusBlocks.polongniusAncientChest = new PolongniusBlockAncientChest(PolongniusConfigManager.idBlockPolongniusEpicChest).setUnlocalizedName("polongniusAncientChest");
		PolongniusBlocks.polongniusSlimeBlock = new PolongniusBlockSlime(PolongniusConfigManager.idBlockPolongniusSlime).setUnlocalizedName("polongniusSlimeBlock");
		PolongniusBlocks.cheeseSlimeEgg = new PolongniusBlockCheeseSlimeEgg(PolongniusConfigManager.idBlockCheeseSlimeEgg, Material.glass).setUnlocalizedName("cheeseSlimeEgg");

		PolongniusBlocks.cheeseOfMilkFluid = new PolongniusCheeseOfMilkFluid("cheeseOfMilkFluid").setBlockID(PolongniusConfigManager.idBlockCheeseOfMilkFluid);
		FluidRegistry.registerFluid(PolongniusBlocks.cheeseOfMilkFluid);
		PolongniusBlocks.cheeseOfMilkFluidBlock = new PolongniusBlockFluidCheeseOfMilk(PolongniusConfigManager.idBlockCheeseOfMilkFluid, PolongniusBlocks.cheeseOfMilkFluid, Material.water).setUnlocalizedName("cheeseOfMilkFluid");
	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 0, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 1, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 2, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 3, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 4, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 5, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 6, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 7, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 8, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 9, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 10, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 11, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 12, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 13, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(polongniusBasicBlock, 14, "pickaxe", 4);

		MinecraftForge.setBlockHarvestLevel(fallenPolongniusMeteor, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(polongniusSolarPanel, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(polongniusCobblestoneStairs, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(polongniusAncientChest, "axe", 0);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(polongniusBasicBlock, PolongniusItemBasicBlock.class);
		MPBlocks.registerBlock(polongniusTreasureChest, MPItemRarity.class);
		MPBlocks.registerBlock(fallenPolongniusMeteor, MPItemRarity.class);
		MPBlocks.registerBlock(cheeseOfMilkCake, MPItemRarity.class);
		MPBlocks.registerBlock(floniumTorch, MPItemRarity.class);
		MPBlocks.registerBlock(polongniusSolarPanel, MPItemRarity.class);
		MPBlocks.registerBlock(polongniusAncientChest, MPItemRarity.class);
		MPBlocks.registerBlock(polongniusCobblestoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(cheeseOfMilkFluidBlock, MPItemRarity.class);
		MPBlocks.registerBlock(polongniusSlimeBlock, MPItemRarity.class);
		MPBlocks.registerBlock(cheeseSlimeEgg, MPItemRarity.class);
	}
}