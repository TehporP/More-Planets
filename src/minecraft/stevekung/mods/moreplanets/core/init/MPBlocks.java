/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.core.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.core.blocks.BlockSlabMP.SlabCategory;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.blocks.BlockWallMP;
import stevekung.mods.moreplanets.core.itemblocks.MPItemBlockSlab;
import stevekung.mods.moreplanets.core.itemblocks.MPItemBlockWall;
import stevekung.mods.moreplanets.core.items.MPItemRarity;
import stevekung.mods.moreplanets.core.util.MPConfigManager;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class MPBlocks
{
	/**@CoreBlock**/
	public static BlockHalfSlab blockStoneSlabFull;
	public static BlockHalfSlab blockStoneSlabHalf;
	public static BlockHalfSlab blockStoneSlabFull2;
	public static BlockHalfSlab blockStoneSlabHalf2;
	public static BlockHalfSlab blockWoodSlabFull;
	public static BlockHalfSlab blockWoodSlabHalf;

	public static Block tinStairs;
	public static Block tin2Stairs;
	public static Block moonStoneStairs;
	public static Block marsCobblestoneStairs;
	public static Block blockWall;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();

		DionaBlocks.init();
		PolongniusBlocks.init();
		NibiruBlocks.init();
		KoentusBlocks.init();
		FronosBlocks.init();
		KapteynBBlocks.init();
	}

	private static void initBlocks()
	{
		/**@CoreBlock**/
		MPBlocks.blockStoneSlabFull = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockStoneDoubleSlab, true, Material.rock, SlabCategory.WOOD1).setUnlocalizedName("stoneDoubleSlab1");
		MPBlocks.blockStoneSlabHalf = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockStoneSingleSlab, false, Material.rock, SlabCategory.WOOD1).setUnlocalizedName("stoneHalfSlab1");
		MPBlocks.blockStoneSlabFull2 = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockStoneDoubleSlab2, true, Material.rock, SlabCategory.WOOD2).setUnlocalizedName("stoneDoubleSlab2");
		MPBlocks.blockStoneSlabHalf2 = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockStoneSingleSlab2, false, Material.rock, SlabCategory.WOOD2).setUnlocalizedName("stoneHalfSlab2");
		MPBlocks.blockWoodSlabFull = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockWoodDoubleSlab, true, Material.wood, SlabCategory.STONE).setUnlocalizedName("woodenDoubleSlab");
		MPBlocks.blockWoodSlabHalf = (BlockHalfSlab) new BlockSlabMP(MPConfigManager.idBlockWoodSingleSlab, false, Material.wood, SlabCategory.STONE).setUnlocalizedName("woodenHalfSlab");
		MPBlocks.tinStairs = new BlockStairsMP(MPConfigManager.idBlockTinStairs, GCCoreBlocks.blockMoon, StairsCategory.TIN).setHardness(1.0F).setUnlocalizedName("tinStairs");
		MPBlocks.tin2Stairs = new BlockStairsMP(MPConfigManager.idBlockTin2Stairs, GCCoreBlocks.blockMoon, StairsCategory.TIN2).setHardness(1.0F).setUnlocalizedName("tin2Stairs");
		MPBlocks.moonStoneStairs = new BlockStairsMP(MPConfigManager.idBlockMoonStairs, GCCoreBlocks.blockMoon, StairsCategory.MOON).setHardness(2.0F).setUnlocalizedName("moonStoneStairs");
		MPBlocks.marsCobblestoneStairs = new BlockStairsMP(MPConfigManager.idBlockMarsStairs, GCCoreBlocks.blockMoon, StairsCategory.MARS).setHardness(2.2F).setUnlocalizedName("marsCobblestoneStairs");
		MPBlocks.blockWall = new BlockWallMP(MPConfigManager.idBlockWall, GCCoreBlocks.blockMoon).setHardness(2.0F).setUnlocalizedName("wall");
	}

	private static void setHarvestLevels()
	{
		/**@CoreBlock**/
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 2, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 3, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 4, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 5, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 6, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull, 7, "pickaxe", 3);

		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 2, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 3, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 4, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 5, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 6, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf, 7, "pickaxe", 3);

		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull2, 0, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull2, 1, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabFull2, 2, "pickaxe", 6);

		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf2, 0, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf2, 1, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(blockStoneSlabHalf2, 2, "pickaxe", 6);

		MinecraftForge.setBlockHarvestLevel(blockWoodSlabFull, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(blockWoodSlabHalf, "axe", 0);

		MinecraftForge.setBlockHarvestLevel(tinStairs, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(tin2Stairs, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(moonStoneStairs, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(marsCobblestoneStairs, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockWall, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockWall, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(blockWall, 2, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockWall, 3, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(blockWall, 4, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(blockWall, 5, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockWall, 6, "pickaxe", 6);
	}

	private static void registerBlocks()
	{
		/**@CoreBlock**/
		MPItemBlockSlab.setSlabs(blockStoneSlabHalf, blockStoneSlabFull);
		registerBlock(blockStoneSlabFull, MPItemBlockSlab.class);
		registerBlock(blockStoneSlabHalf, MPItemBlockSlab.class);

		MPItemBlockSlab.setSlabs(blockStoneSlabHalf2, blockStoneSlabFull2);
		registerBlock(blockStoneSlabFull2, MPItemBlockSlab.class);
		registerBlock(blockStoneSlabHalf2, MPItemBlockSlab.class);

		MPItemBlockSlab.setSlabs(blockWoodSlabHalf, blockWoodSlabFull);
		registerBlock(blockWoodSlabFull, MPItemBlockSlab.class);
		registerBlock(blockWoodSlabHalf, MPItemBlockSlab.class);

		registerBlock(tinStairs, MPItemRarity.class);
		registerBlock(tin2Stairs, MPItemRarity.class);
		registerBlock(moonStoneStairs, MPItemRarity.class);
		registerBlock(marsCobblestoneStairs, MPItemRarity.class);
		registerBlock(blockWall, MPItemBlockWall.class);
	}

	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}
}