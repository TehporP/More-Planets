/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class BlockSiriusFire extends BlockFire
{
	public static PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
	public static PropertyBool FLIP = PropertyBool.create("flip");
	public static PropertyBool ALT = PropertyBool.create("alt");
	public static PropertyBool NORTH = PropertyBool.create("north");
	public static PropertyBool EAST = PropertyBool.create("east");
	public static PropertyBool SOUTH = PropertyBool.create("south");
	public static PropertyBool WEST = PropertyBool.create("west");
	public static PropertyInteger UPPER = PropertyInteger.create("upper", 0, 2);

	public BlockSiriusFire(String name)
	{
		super();
		this.setDefaultState(this.getDefaultState().withProperty(AGE, Integer.valueOf(0)).withProperty(FLIP, Boolean.valueOf(false)).withProperty(ALT, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UPPER, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setUnlocalizedName(name);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.getGameRules().getGameRuleBooleanValue("doFireTick"))
		{
			if (!this.canPlaceBlockAt(world, pos))
			{
				world.setBlockToAir(pos);
			}

			Block block = world.getBlockState(pos.down()).getBlock();
			boolean flag = block.isFireSource(world, pos.down(), EnumFacing.UP);

			if (!flag && world.isRaining() && this.canDie(world, pos))
			{
				world.setBlockToAir(pos);
			}
			else
			{
				int i = ((Integer)state.getValue(AGE)).intValue();

				if (i < 15)
				{
					state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
					world.setBlockState(pos, state, 4);
				}

				world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(10));

				if (!flag)
				{
					if (!this.canNeighborCatchFire(world, pos))
					{
						if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) || i > 3)
						{
							world.setBlockToAir(pos);
						}
						return;
					}

					if (!this.canCatchFire(world, pos.down(), EnumFacing.UP) && i == 15 && rand.nextInt(4) == 0)
					{
						world.setBlockToAir(pos);
						return;
					}
				}

				boolean flag1 = world.isBlockinHighHumidity(pos);
				byte b0 = 0;

				if (flag1)
				{
					b0 = -50;
				}

				this.tryCatchFire(world, pos.east(), 300 + b0, rand, i, EnumFacing.WEST);
				this.tryCatchFire(world, pos.west(), 300 + b0, rand, i, EnumFacing.EAST);
				this.tryCatchFire(world, pos.down(), 250 + b0, rand, i, EnumFacing.UP);
				this.tryCatchFire(world, pos.up(), 250 + b0, rand, i, EnumFacing.DOWN);
				this.tryCatchFire(world, pos.north(), 300 + b0, rand, i, EnumFacing.SOUTH);
				this.tryCatchFire(world, pos.south(), 300 + b0, rand, i, EnumFacing.NORTH);

				for (int j = -1; j <= 1; ++j)
				{
					for (int k = -1; k <= 1; ++k)
					{
						for (int l = -1; l <= 4; ++l)
						{
							if (j != 0 || l != 0 || k != 0)
							{
								int i1 = 100;

								if (l > 1)
								{
									i1 += (l - 1) * 100;
								}

								BlockPos blockpos1 = pos.add(j, l, k);
								int j1 = this.getNeighborEncouragement(world, blockpos1);

								if (j1 > 0)
								{
									int k1 = (j1 + 40 + world.getDifficulty().getDifficultyId() * 7) / (i + 30);

									if (flag1)
									{
										k1 /= 2;
									}

									if (k1 > 0 && rand.nextInt(i1) <= k1 && (!world.isRaining() || !this.canDie(world, blockpos1)))
									{
										int l1 = i + rand.nextInt(5) / 4;

										if (l1 > 15)
										{
											l1 = 15;
										}
										world.setBlockState(blockpos1, state.withProperty(AGE, Integer.valueOf(l1)), 3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void tryCatchFire(World world, BlockPos pos, int chance, Random rand, int age, EnumFacing side)
	{
		int k = world.getBlockState(pos).getBlock().getFlammability(world, pos, side);

		if (rand.nextInt(chance) < k)
		{
			IBlockState state = world.getBlockState(pos);

			if (rand.nextInt(age + 10) < 5 && !world.canLightningStrike(pos))
			{
				int l = age + rand.nextInt(5) / 4;

				if (l > 15)
				{
					l = 15;
				}

				world.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(l)), 3);
			}
			else
			{
				world.setBlockToAir(pos);
			}

			if (state.getBlock() == Blocks.tnt)
			{
				Blocks.tnt.onBlockDestroyedByPlayer(world, pos, state.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
			}
			else if (state.getBlock() == DionaBlocks.fronisium_tnt)
			{
				DionaBlocks.fronisium_tnt.onBlockDestroyedByPlayer(world, pos, state.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
			}
		}
	}

	private boolean canNeighborCatchFire(World world, BlockPos pos)
	{
		EnumFacing[] aenumfacing = EnumFacing.values();
		int i = aenumfacing.length;

		for (int j = 0; j < i; ++j)
		{
			EnumFacing enumfacing = aenumfacing[j];

			if (this.canCatchFire(world, pos.offset(enumfacing), enumfacing.getOpposite()))
			{
				return true;
			}
		}
		return false;
	}

	private int getNeighborEncouragement(World world, BlockPos pos)
	{
		if (!world.isAirBlock(pos))
		{
			return 0;
		}
		else
		{
			int i = 0;
			EnumFacing[] aenumfacing = EnumFacing.values();
			int j = aenumfacing.length;

			for (int k = 0; k < j; ++k)
			{
				EnumFacing enumfacing = aenumfacing[k];
				i = Math.max(world.getBlockState(pos.offset(enumfacing)).getBlock().getFlammability(world, pos.offset(enumfacing), enumfacing.getOpposite()), i);
			}
			return i;
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return World.doesBlockHaveSolidTopSurface(world, pos.down()) || this.canNeighborCatchFire(world, pos);
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) && !this.canNeighborCatchFire(world, pos))
		{
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (world.provider.getDimensionId() > 0 || !Blocks.portal.func_176548_d(world, pos))
		{
			if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) && !this.canNeighborCatchFire(world, pos))
			{
				world.setBlockToAir(pos);
			}
			else
			{
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (rand.nextInt(24) == 0)
		{
			world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "fire.fire", 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
		}

		int i;
		double d0;
		double d1;
		double d2;

		if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) && !SiriusBBlocks.sirius_fire.canCatchFire(world, pos.down(), EnumFacing.UP))
		{
			if (SiriusBBlocks.sirius_fire.canCatchFire(world, pos.west(), EnumFacing.EAST))
			{
				for (i = 0; i < 2; ++i)
				{
					d0 = pos.getX() + rand.nextDouble() * 0.10000000149011612D;
					d1 = pos.getY() + rand.nextDouble();
					d2 = pos.getZ() + rand.nextDouble();
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			if (SiriusBBlocks.sirius_fire.canCatchFire(world, pos.east(), EnumFacing.WEST))
			{
				for (i = 0; i < 2; ++i)
				{
					d0 = pos.getX() + 1 - rand.nextDouble() * 0.10000000149011612D;
					d1 = pos.getY() + rand.nextDouble();
					d2 = pos.getZ() + rand.nextDouble();
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			if (SiriusBBlocks.sirius_fire.canCatchFire(world, pos.north(), EnumFacing.SOUTH))
			{
				for (i = 0; i < 2; ++i)
				{
					d0 = pos.getX() + rand.nextDouble();
					d1 = pos.getY() + rand.nextDouble();
					d2 = pos.getZ() + rand.nextDouble() * 0.10000000149011612D;
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			if (SiriusBBlocks.sirius_fire.canCatchFire(world, pos.south(), EnumFacing.NORTH))
			{
				for (i = 0; i < 2; ++i)
				{
					d0 = pos.getX() + rand.nextDouble();
					d1 = pos.getY() + rand.nextDouble();
					d2 = pos.getZ() + 1 - rand.nextDouble() * 0.10000000149011612D;
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			if (SiriusBBlocks.sirius_fire.canCatchFire(world, pos.up(), EnumFacing.DOWN))
			{
				for (i = 0; i < 2; ++i)
				{
					d0 = pos.getX() + rand.nextDouble();
					d1 = pos.getY() + 1 - rand.nextDouble() * 0.10000000149011612D;
					d2 = pos.getZ() + rand.nextDouble();
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}
		}
		else
		{
			for (i = 0; i < 3; ++i)
			{
				d0 = pos.getX() + rand.nextDouble();
				d1 = pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
				d2 = pos.getZ() + rand.nextDouble();
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
		{
			return;
		}
		entity.setFire(10);
	}

	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return MapColor.diamondColor;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(AGE)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {AGE, NORTH, EAST, SOUTH, WEST, UPPER, FLIP, ALT});
	}
}