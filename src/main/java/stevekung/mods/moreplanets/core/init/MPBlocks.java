/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import stevekung.mods.moreplanets.common.blocks.BlockChondriteRock;
import stevekung.mods.moreplanets.common.blocks.BlockDoubleDungeonBrickSlab1;
import stevekung.mods.moreplanets.common.blocks.BlockDoubleStoneSlab1MP;
import stevekung.mods.moreplanets.common.blocks.BlockDoubleStoneSlab2MP;
import stevekung.mods.moreplanets.common.blocks.BlockDoubleWoodenSlab1MP;
import stevekung.mods.moreplanets.common.blocks.BlockDummy;
import stevekung.mods.moreplanets.common.blocks.BlockDungeonBrickSlab1;
import stevekung.mods.moreplanets.common.blocks.BlockDungeonBrickWall;
import stevekung.mods.moreplanets.common.blocks.BlockStoneSlab1MP;
import stevekung.mods.moreplanets.common.blocks.BlockStoneSlab2MP;
import stevekung.mods.moreplanets.common.blocks.BlockWallMP;
import stevekung.mods.moreplanets.common.blocks.BlockWoodenSlab1MP;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockChondrite;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockDungeonBrickSlab1;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockDungeonBrickWall;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockStoneSlab1MP;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockStoneSlab2MP;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockWallMP;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockWoodenSlab1MP;
import stevekung.mods.moreplanets.moons.deimos.blocks.DeimosBlocks;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.phobos.blocks.PhobosBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.stevecore.RegisterHelper;

public class MPBlocks
{
	public static Block half_stone_slab_1;
	public static Block double_stone_slab_1;
	public static Block half_stone_slab_2;
	public static Block double_stone_slab_2;
	public static Block half_wooden_slab_1;
	public static Block double_wooden_slab_1;
	public static Block half_dungeon_brick_slab_1;
	public static Block double_dungeon_brick_slab_1;

	public static Block stone_wall;
	public static Block dungeon_brick_wall;

	public static Block dummy_block;
	public static Block chondrite_rock;

	public static void init()
	{
		MPBlocks.initBlocks();
		MPBlocks.registerBlocks();
		MPBlocks.setHarvestLevels();
		MPBlocks.setFireBurn();

		DionaBlocks.init();
		PolongniusBlocks.init();
		NibiruBlocks.init();
		KoentusBlocks.init();
		FronosBlocks.init();
		KapteynBBlocks.init();
		SiriusBBlocks.init();

		MercuryBlocks.init();
		VenusBlocks.init();
		PlutoBlocks.init();
		PhobosBlocks.init();
		DeimosBlocks.init();
		IoBlocks.init();
		EuropaBlocks.init();
	}

	private static void initBlocks()
	{
		MPBlocks.stone_wall = new BlockWallMP("stone_wall");
		MPBlocks.dungeon_brick_wall = new BlockDungeonBrickWall("dungeon_brick_wall");

		MPBlocks.half_stone_slab_1 = new BlockStoneSlab1MP("half_stone_slab_1", Material.rock);
		MPBlocks.double_stone_slab_1 = new BlockDoubleStoneSlab1MP("double_stone_slab_1", Material.rock);
		MPBlocks.half_stone_slab_2 = new BlockStoneSlab2MP("half_stone_slab_2", Material.rock);
		MPBlocks.double_stone_slab_2 = new BlockDoubleStoneSlab2MP("double_stone_slab_2", Material.rock);
		MPBlocks.half_wooden_slab_1 = new BlockWoodenSlab1MP("half_wooden_slab_1", Material.wood);
		MPBlocks.double_wooden_slab_1 = new BlockDoubleWoodenSlab1MP("double_wooden_slab_1", Material.wood);
		MPBlocks.half_dungeon_brick_slab_1 = new BlockDungeonBrickSlab1("half_dungeon_brick_slab_1", Material.rock);
		MPBlocks.double_dungeon_brick_slab_1 = new BlockDoubleDungeonBrickSlab1("double_dungeon_brick_slab_1", Material.rock);

		MPBlocks.dummy_block = new BlockDummy("dummy_block").setBlockUnbreakable();
		MPBlocks.chondrite_rock = new BlockChondriteRock("chondrite_rock");
	}

	private static void setHarvestLevels()
	{
		MPBlocks.half_stone_slab_1.setHarvestLevel("pickaxe", 1);
		MPBlocks.double_stone_slab_1.setHarvestLevel("pickaxe", 1);
		MPBlocks.half_stone_slab_2.setHarvestLevel("pickaxe", 1);
		MPBlocks.double_stone_slab_2.setHarvestLevel("pickaxe", 1);

		MPBlocks.half_wooden_slab_1.setHarvestLevel("axe", 0);
		MPBlocks.half_wooden_slab_1.setHarvestLevel("axe", 0);

		MPBlocks.double_wooden_slab_1.setHarvestLevel("axe", 0);
		MPBlocks.double_wooden_slab_1.setHarvestLevel("axe", 0);

		MPBlocks.half_dungeon_brick_slab_1.setHarvestLevel("pickaxe", 1);
		MPBlocks.double_dungeon_brick_slab_1.setHarvestLevel("pickaxe", 1);

		MPBlocks.stone_wall.setHarvestLevel("pickaxe", 1);
		MPBlocks.dungeon_brick_wall.setHarvestLevel("pickaxe", 1);
		MPBlocks.chondrite_rock.setHarvestLevel("pickaxe", 1);
	}

	private static void setFireBurn()
	{
		RegisterHelper.setFireBurn(MPBlocks.half_wooden_slab_1, 5, 20);
		RegisterHelper.setFireBurn(MPBlocks.double_wooden_slab_1, 5, 20);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(MPBlocks.stone_wall, ItemBlockWallMP.class);
		RegisterHelper.registerBlock(MPBlocks.dungeon_brick_wall, ItemBlockDungeonBrickWall.class);
		RegisterHelper.registerBlock(MPBlocks.half_stone_slab_1, ItemBlockStoneSlab1MP.class, MPBlocks.half_stone_slab_1, MPBlocks.double_stone_slab_1);
		RegisterHelper.registerBlock(MPBlocks.double_stone_slab_1, ItemBlockStoneSlab1MP.class, MPBlocks.half_stone_slab_1, MPBlocks.double_stone_slab_1);
		RegisterHelper.registerBlock(MPBlocks.half_stone_slab_2, ItemBlockStoneSlab2MP.class, MPBlocks.half_stone_slab_2, MPBlocks.double_stone_slab_2);
		RegisterHelper.registerBlock(MPBlocks.double_stone_slab_2, ItemBlockStoneSlab2MP.class, MPBlocks.half_stone_slab_2, MPBlocks.double_stone_slab_2);
		RegisterHelper.registerBlock(MPBlocks.half_wooden_slab_1, ItemBlockWoodenSlab1MP.class, MPBlocks.half_wooden_slab_1, MPBlocks.double_wooden_slab_1);
		RegisterHelper.registerBlock(MPBlocks.double_wooden_slab_1, ItemBlockWoodenSlab1MP.class, MPBlocks.half_wooden_slab_1, MPBlocks.double_wooden_slab_1);
		RegisterHelper.registerBlock(MPBlocks.half_dungeon_brick_slab_1, ItemBlockDungeonBrickSlab1.class, MPBlocks.half_dungeon_brick_slab_1, MPBlocks.double_dungeon_brick_slab_1);
		RegisterHelper.registerBlock(MPBlocks.double_dungeon_brick_slab_1, ItemBlockDungeonBrickSlab1.class, MPBlocks.half_dungeon_brick_slab_1, MPBlocks.double_dungeon_brick_slab_1);
		RegisterHelper.registerBlock(MPBlocks.chondrite_rock, ItemBlockChondrite.class);
		RegisterHelper.registerBlock(MPBlocks.dummy_block);
	}
}