/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.blocks;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.items.MPItemRarity;
import stevekung.mods.moreplanets.diona.itemblocks.DionaItemBlock;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;

public class DionaBlocks
{
	public static Block dionaBasicBlock;
	public static Block dionaTreasureChest;
	public static Block fronisiumTnt;
	public static Block quontoniumBricksFence;
	public static Block minionCreeperEgg;
	public static Block dionaStoneStairs;
	public static Block smoothQuontoniumStairs;
	public static Block quontoniumBricksStairs;
	public static Block titaniumFence;
	public static Block dionaAncientChest;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		DionaBlocks.dionaBasicBlock = new DionaBlock(DionaConfigManager.idBlockDiona).setUnlocalizedName("dn");
		DionaBlocks.dionaTreasureChest = new DionaBlockT3TreasureChest(DionaConfigManager.idBlockDionaTreasureChest).setUnlocalizedName("dionaTreasureChest");
		DionaBlocks.fronisiumTnt = new DionaBlockFronisiumTNT(DionaConfigManager.idBlockDionaTNT).setUnlocalizedName("fronisiumTNT");
		DionaBlocks.quontoniumBricksFence = new DionaBlockQuontoniumBricksFence(DionaConfigManager.idBlockQuontoniumFence, "quontoniumBricks", Material.rock).setUnlocalizedName("quontoniumBricksFence");
		DionaBlocks.minionCreeperEgg = new DionaBlockMinionCreeperEgg(DionaConfigManager.idBlockMinionCreeperEgg).setUnlocalizedName("minionCreeperEgg");
		DionaBlocks.dionaStoneStairs = new BlockStairsMP(DionaConfigManager.idBlockDionaStairs, GCCoreBlocks.blockMoon, StairsCategory.DIONA).setHardness(2.5F).setUnlocalizedName("dionaStoneStairs");
		DionaBlocks.smoothQuontoniumStairs = new BlockStairsMP(DionaConfigManager.idBlockSmoothQuontoniumStairs, GCCoreBlocks.blockMoon, StairsCategory.SMQUON).setHardness(2.2F).setUnlocalizedName("smoothQuontoniumStairs");
		DionaBlocks.quontoniumBricksStairs = new BlockStairsMP(DionaConfigManager.idBlockBrickQuontoniumStairs, GCCoreBlocks.blockMoon, StairsCategory.BRQUON).setHardness(2.2F).setUnlocalizedName("quontoniumBricksStairs");
		DionaBlocks.titaniumFence = new DionaBlockTitaniumFence(DionaConfigManager.idBlockTitaniumFence, "titaniumFence", Material.iron).setUnlocalizedName("titaniumFence");
		DionaBlocks.dionaAncientChest = new DionaBlockAncientChest(DionaConfigManager.idBlockDionaAncientChest).setUnlocalizedName("dionaAncientChest");
	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 0, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 1, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 2, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 3, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 4, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 5, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 6, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 7, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 8, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 9, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 10, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 11, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 12, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaBasicBlock, 13, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(quontoniumBricksFence, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaStoneStairs, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(titaniumFence, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(quontoniumBricksStairs, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(smoothQuontoniumStairs, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(dionaAncientChest, "axe", 0);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(dionaBasicBlock, DionaItemBlock.class);
		MPBlocks.registerBlock(dionaTreasureChest, MPItemRarity.class);
		MPBlocks.registerBlock(fronisiumTnt, MPItemRarity.class);
		MPBlocks.registerBlock(quontoniumBricksFence, MPItemRarity.class);
		MPBlocks.registerBlock(minionCreeperEgg, MPItemRarity.class);
		MPBlocks.registerBlock(dionaStoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(titaniumFence, MPItemRarity.class);
		MPBlocks.registerBlock(smoothQuontoniumStairs, MPItemRarity.class);
		MPBlocks.registerBlock(quontoniumBricksStairs, MPItemRarity.class);
		MPBlocks.registerBlock(dionaAncientChest, MPItemRarity.class);
	}
}