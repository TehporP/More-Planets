/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.fronos.world.gen.tree;
//
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.util.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosSapling;
//import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
//
//public class WorldGenCoconutTree extends WorldGenAbstractTree
//{
//	private int strengthRand;
//	private int bMax;
//	public WorldGenCoconutTree(int strengthRand, int bMax, double offset)
//	{
//		super(false);
//		this.strengthRand = strengthRand;
//		this.bMax = bMax;
//	}
//
//	@Override
//	public boolean generate(World world, Random rand, BlockPos pos)
//	{
//		int i = rand.nextInt(3) + 5;
//
//		boolean flag = true;
//
//		if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
//		{
//			int k;
//			int l;
//
//			for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
//			{
//				byte b0 = 1;
//
//				if (j == pos.getY())
//				{
//					b0 = 0;
//				}
//				if (j >= pos.getY() + 1 + i - 2)
//				{
//					b0 = 2;
//				}
//
//				for (k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
//				{
//					for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l)
//					{
//						if (j >= 0 && j < 256)
//						{
//							if (!this.isReplaceable(world, new BlockPos(k, j, l)))
//							{
//								flag = false;
//							}
//						}
//						else
//						{
//							flag = false;
//						}
//					}
//				}
//			}
//
//			if (!flag)
//			{
//				return false;
//			}
//			else
//			{
//				BlockPos down = pos.down();
//				Block block1 = world.getBlockState(down).getBlock();
//				boolean isSoil = block1.canSustainPlant(world, down, net.minecraft.util.EnumFacing.UP, (BlockFronosSapling)FronosBlocks.fronos_sapling);
//
//				if (isSoil && pos.getY() < 256 - i - 1)
//				{
//					block1.onPlantGrow(world, down, pos);
//					int i2;
//
//					for (i2 = pos.getY() - 3 + i; i2 <= pos.getY() + i; ++i2)
//					{
//						k = i2 - (pos.getY() + i);
//						l = 1 - k / 2;
//
//						for (int i1 = pos.getX() - l; i1 <= pos.getX() + l; ++i1)
//						{
//							int j1 = i1 - pos.getX();
//
//							for (int k1 = pos.getZ() - l; k1 <= pos.getZ() + l; ++k1)
//							{
//								int l1 = k1 - pos.getZ();
//
//								if (Math.abs(j1) != l || Math.abs(l1) != l || rand.nextInt(2) != 0 && k != 0)
//								{
//									BlockPos blockpos1 = new BlockPos(i1, i2, k1);
//									Block block = world.getBlockState(blockpos1).getBlock();
//
//									if (block.isAir(world, blockpos1) || block.isLeaves(world, blockpos1))
//									{
//										//this.func_175905_a(world, blockpos1, Blocks.leaves, BlockPlanks.EnumType.BIRCH.getMetadata());
//										this.func_175905_a(world, pos.add(pos.getX() + 2, pos.getY() - 1, pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 2, pos.getY() - 1, pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY() - 1, pos.getZ() + 2), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY() - 1, pos.getZ() - 2), FronosBlocks.fronos_colorized_leaves, 0);
//
//										this.func_175905_a(world, pos.add(pos.getX() + 1, pos.getY(), pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 1, pos.getY(), pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY(), pos.getZ() + 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY(), pos.getZ() - 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() + 2, pos.getY(), pos.getZ() + 2), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 2, pos.getY(), pos.getZ() - 2), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() + 2, pos.getY(), pos.getZ() - 2), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 2, pos.getY(), pos.getZ() + 2), FronosBlocks.fronos_colorized_leaves, 0);
//
//										this.func_175905_a(world, pos.add(pos.getX() + 1, pos.getY() + 1, pos.getZ() - 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 1, pos.getY() + 1, pos.getZ() + 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 1, pos.getY() + 1, pos.getZ() - 1), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY() + 1, pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//
//										this.func_175905_a(world, pos.add(pos.getX() + 2, pos.getY() + 2, pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX() - 2, pos.getY() + 2, pos.getZ()), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY() + 2, pos.getZ() + 2), FronosBlocks.fronos_colorized_leaves, 0);
//										this.func_175905_a(world, pos.add(pos.getX(), pos.getY() + 2, pos.getZ() - 2), FronosBlocks.fronos_colorized_leaves, 0);
//									}
//								}
//							}
//						}
//					}
//
//					double strength = rand.nextInt(this.strengthRand) / 100D;
//					double xoff = 0;
//					double yoff = 0;
//					int h = 1;
//					int r = rand.nextInt(4);
//
//					if (r == 0)
//					{
//						xoff = strength;
//					}
//					else if (r == 1)
//					{
//						xoff = -strength;
//					}
//					else if (r == 2)
//					{
//						yoff = strength;
//					}
//					else
//					{
//						yoff = -strength;
//					}
//
//					//for (i2 = 0; i2 < i; ++i2)
//					for (int b = 0; b < this.bMax; b++)
//					{
//						BlockPos upN = pos.up(i2);
//						Block block2 = world.getBlockState(upN).getBlock();
//
//						if (block2.isAir(world, upN) || block2.isLeaves(world, upN))
//						{
//							this.func_175905_a(world, pos.add(pos.getX() + (int) Math.floor(xoff), pos.getY() + h, pos.getZ() + (int) Math.floor(yoff)), FronosBlocks.fronos_log, 0);
//							//this.func_175905_a(world, pos.up(i2), Blocks.log, BlockPlanks.EnumType.BIRCH.getMetadata());
//						}
//					}
//					return true;
//				}
//				else
//				{
//					return false;
//				}
//			}
//		}
//		else
//		{
//			return false;
//		}
//	}
//}

package stevekung.mods.moreplanets.planets.fronos.world.gen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenCoconutTree extends WorldGenAbstractTree
{
	private int strengthRand;
	private int bMax;
	private double offset;

	public WorldGenCoconutTree(int strengthRand, int bMax, double offset)
	{
		super(false);
		this.strengthRand = strengthRand;
		this.bMax = bMax;
		this.offset = offset;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int y = pos.getY();

		while (world.isAirBlock(pos) && y > 2)
		{
			--y;
		}

		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof IFronosGrass) && block != FronosBlocks.fronos_dirt)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (world.isAirBlock(pos.add(pos.getX() + var7, y - 1, pos.getZ() + var8)) && world.isAirBlock(pos.add(pos.getX() + var7, y - 2, pos.getZ() + var8)) && !world.isAirBlock(pos.add(pos.getX() + var7, y, pos.getZ() + var8)))
					{
						return false;
					}
				}
			}

			double strength = rand.nextInt(this.strengthRand) / 100D;
			double xoff = 0;
			double yoff = 0;
			int r = rand.nextInt(4);
			if(r == 0) { xoff = strength; }
			else if(r == 1) { xoff = -strength; }
			else if(r == 2) { yoff = strength; }
			else { yoff = -strength; }

			int h = 1;
			this.buildBlock(world, pos.getX(), y, pos.getZ(), FronosBlocks.fronos_dirt, 0);

			for (int b = 0; b < this.bMax; b++)
			{
				this.buildBlock(world, pos.getX() + (int) Math.floor(xoff), y + h, pos.getZ() + (int) Math.floor(yoff), FronosBlocks.fronos_log, 0);

				if (b == this.bMax - 1)
				{
					this.generateTop(world, pos.getX() + (int) Math.floor(xoff), y + h, pos.getZ() + (int) Math.floor(yoff));
				}
				else
				{
					h++;
					xoff *= this.offset;
					yoff *= this.offset;
				}
			}
			return true;
		}
	}

	public void generateTop(World world, int x, int y, int z)
	{
		this.buildBlock(world, x + 2, y - 1, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 2, y - 1, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y - 1, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y - 1, z - 2, FronosBlocks.fronos_colorized_leaves, 0);

		this.buildBlock(world, x + 1, y, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 1, y, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x + 2, y, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 2, y, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x + 2, y, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 2, y, z + 2, FronosBlocks.fronos_colorized_leaves, 0);

		this.buildBlock(world, x + 1, y + 1, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 1, y + 1, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x + 1, y + 1, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 1, y + 1, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y + 1, z, FronosBlocks.fronos_colorized_leaves, 0);

		this.buildBlock(world, x + 2, y + 2, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x - 2, y + 2, z, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y + 2, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
		this.buildBlock(world, x, y + 2, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
	}

	public void buildBlock(World world, int x, int y, int z, Block block, int meta)
	{
		BlockPos pos = new BlockPos(x, y, z);

		if (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isLeaves(world, pos))
		{
			world.setBlockState(pos, block.getStateFromMeta(meta), 2);
		}
	}
}