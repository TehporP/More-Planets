/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.blocks;

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
import stevekung.mods.moreplanets.kapteynb.fluids.KapteynBFrozenWaterFluid;
import stevekung.mods.moreplanets.kapteynb.fluids.blocks.KapteynBBlockFluidFrozenWater;
import stevekung.mods.moreplanets.kapteynb.itemblocks.KapteynBItemBlock;
import stevekung.mods.moreplanets.kapteynb.itemblocks.KapteynBItemBlockOre;
import stevekung.mods.moreplanets.kapteynb.util.KapteynBConfigManager;

public class KapteynBBlocks
{
	public static Block kapteynBBasicBlock;
	public static Block kapteynBIce;
	public static Block kapteynBTreasureChest;
	public static Block kapteynBOreBlock;
	public static Block kapteynBCobblestoneStairs;
	public static Block kapteynBDirtyIce;
	public static Block kapteynBFrozenWater;

	/**@FluidStuff**/
	public static Fluid frozenWaterFluid;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		KapteynBBlocks.kapteynBBasicBlock = new KapteynBBlock(KapteynBConfigManager.idBlockKapteynB).setUnlocalizedName("kb");
		KapteynBBlocks.kapteynBIce = new KapteynBBlockIce(KapteynBConfigManager.idBlockKapteynBIce, Material.ice).setUnlocalizedName("kapteynBIce");
		KapteynBBlocks.kapteynBTreasureChest = new KapteynBBlockT7TreasureChest(KapteynBConfigManager.idBlockKapteynBTreasureChest).setUnlocalizedName("kapteynBTreasureChest");
		KapteynBBlocks.kapteynBOreBlock = new KapteynBBlockOre(KapteynBConfigManager.idBlockKapteynBOre).setUnlocalizedName("kapteynBOre");
		KapteynBBlocks.kapteynBCobblestoneStairs = new BlockStairsMP(KapteynBConfigManager.idBlockKapteynBCobblestoneStairs, GCCoreBlocks.blockMoon, StairsCategory.KAPTEYNB).setUnlocalizedName("kapteynBCobblestoneStairs");
		KapteynBBlocks.kapteynBDirtyIce = new KapteynBBlockDirtyIce(KapteynBConfigManager.idBlockKapteynBDirtyIce, Material.ice).setUnlocalizedName("kapteynBDirtyIce");

		KapteynBBlocks.frozenWaterFluid = new KapteynBFrozenWaterFluid("frozenWaterFluid").setBlockID(KapteynBConfigManager.idBlockKapteynBFrozenWater);
		FluidRegistry.registerFluid(KapteynBBlocks.frozenWaterFluid);
		KapteynBBlocks.kapteynBFrozenWater = new KapteynBBlockFluidFrozenWater(KapteynBConfigManager.idBlockKapteynBFrozenWater, KapteynBBlocks.frozenWaterFluid, Material.water).setUnlocalizedName("frozenWaterFluid");

	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 0, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 1, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 2, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 3, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 5, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 6, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 7, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 8, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 9, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 10, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 11, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 12, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 13, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 14, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBBasicBlock, 15, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBOreBlock, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(kapteynBCobblestoneStairs, "pickaxe", 5);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(kapteynBBasicBlock, KapteynBItemBlock.class);
		MPBlocks.registerBlock(kapteynBIce, MPItemRarity.class);
		MPBlocks.registerBlock(kapteynBTreasureChest, MPItemRarity.class);
		MPBlocks.registerBlock(kapteynBOreBlock, KapteynBItemBlockOre.class);
		MPBlocks.registerBlock(kapteynBCobblestoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(kapteynBDirtyIce, MPItemRarity.class);
		MPBlocks.registerBlock(kapteynBFrozenWater, MPItemRarity.class);
	}
}