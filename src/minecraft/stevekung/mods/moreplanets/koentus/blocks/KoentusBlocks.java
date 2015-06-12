/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

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
import stevekung.mods.moreplanets.koentus.fluids.KoentusSludgeFluid;
import stevekung.mods.moreplanets.koentus.fluids.blocks.KoentusBlockSludge;
import stevekung.mods.moreplanets.koentus.itemblocks.KoentusItemBlock;
import stevekung.mods.moreplanets.koentus.itemblocks.KoentusItemBlockRock;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;

public class KoentusBlocks
{
	public static Block koentusBasicBlock;
	public static Block koentusIce;
	public static Block koentusIceGlow;
	public static Block koentusCobblestoneStairs;
	public static Block koentusSludgeBlock;
	public static Block koentusTreasureChest;
	//TODO public static Block koentusEgg;
	public static Block koentusRock;
	public static Block whiteCrystalTorch;
	public static Block fallenKoentusMeteor;

	/**@FluidStuff**/
	public static Fluid koentusSludge;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		KoentusBlocks.koentusBasicBlock = new KoentusBlock(KoentusConfigManager.idKoentusBlock).setUnlocalizedName("kt");
		KoentusBlocks.koentusIce = new KoentusBlockIce(KoentusConfigManager.idKoentusIce, Material.ice, false).setUnlocalizedName("koentusIce");
		KoentusBlocks.koentusIceGlow = new KoentusBlockIce(KoentusConfigManager.idKoentusIceGlow, Material.ice, true).setUnlocalizedName("koentusIceGlow");
		KoentusBlocks.koentusCobblestoneStairs = new BlockStairsMP(KoentusConfigManager.idKoentusCobblestoneStairs, GCCoreBlocks.blockMoon, StairsCategory.KOENTUS).setHardness(2.2F).setUnlocalizedName("koentusCobblestoneStairs");
		KoentusBlocks.fallenKoentusMeteor = new KoentusBlockFallenMeteor(KoentusConfigManager.idKoentusMeteor).setUnlocalizedName("koentusMeteor");
		//TODO KoentusBlocks.koentusEgg = new KoentusBlockEledosEgg(KoentusConfigManager.idKoentusEgg).setUnlocalizedName("koentusEgg");
		KoentusBlocks.koentusRock = new KoentusBlockRock(KoentusConfigManager.idKoentusRock).setUnlocalizedName("koentusRock");
		KoentusBlocks.whiteCrystalTorch = new KoentusBlockCrystalTorch(KoentusConfigManager.idWhiteCrystalTorch).setUnlocalizedName("crystalTorch");
		KoentusBlocks.koentusTreasureChest = new KoentusBlockT3TreasureChest(KoentusConfigManager.idKoentusTreasureChest).setUnlocalizedName("koentusTreasureChest");

		KoentusBlocks.koentusSludge = new KoentusSludgeFluid("koentusSludgeFluid").setBlockID(KoentusConfigManager.idKoentusSludge);
		FluidRegistry.registerFluid(KoentusBlocks.koentusSludge);
		KoentusBlocks.koentusSludgeBlock = new KoentusBlockSludge(KoentusConfigManager.idKoentusSludge, KoentusBlocks.koentusSludge, Material.water).setUnlocalizedName("koentusSludgeFluid");
	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(fallenKoentusMeteor, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(koentusBasicBlock, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(koentusCobblestoneStairs, "pickaxe", 6);
		//MinecraftForge.setBlockHarvestLevel(koentusEgg, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(koentusRock, "pickaxe", 6);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(koentusBasicBlock, KoentusItemBlock.class);
		MPBlocks.registerBlock(koentusIce, MPItemRarity.class);
		MPBlocks.registerBlock(koentusIceGlow, MPItemRarity.class);
		MPBlocks.registerBlock(koentusCobblestoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(koentusSludgeBlock, MPItemRarity.class);
		MPBlocks.registerBlock(koentusTreasureChest, MPItemRarity.class);
		//TODO MPBlocks.registerBlock(koentusEgg, MPItemRarity.class);
		MPBlocks.registerBlock(koentusRock, KoentusItemBlockRock.class);
		MPBlocks.registerBlock(whiteCrystalTorch, MPItemRarity.class);
		MPBlocks.registerBlock(fallenKoentusMeteor, MPItemRarity.class);
	}
}