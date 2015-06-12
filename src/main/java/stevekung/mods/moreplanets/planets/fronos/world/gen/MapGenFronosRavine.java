/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class MapGenFronosRavine extends MapGenBase
{
	private float[] field_75046_d = new float[1024];

	protected void func_180707_a(long seed, int x, int z, ChunkPrimer chunk, double p_180707_6_, double p_180707_8_, double p_180707_10_, float p_180707_12_, float p_180707_13_, float p_180707_14_, int p_180707_15_, int p_180707_16_, double p_180707_17_)
	{
		Random random = new Random(seed);
		double d4 = x * 16 + 8;
		double d5 = z * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;

		if (p_180707_16_ <= 0)
		{
			int j1 = this.range * 16 - 16;
			p_180707_16_ = j1 - random.nextInt(j1 / 4);
		}

		boolean flag1 = false;

		if (p_180707_15_ == -1)
		{
			p_180707_15_ = p_180707_16_ / 2;
			flag1 = true;
		}

		float f5 = 1.0F;

		for (int k1 = 0; k1 < 256; ++k1)
		{
			if (k1 == 0 || random.nextInt(3) == 0)
			{
				f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
			}

			this.field_75046_d[k1] = f5 * f5;
		}

		for (; p_180707_15_ < p_180707_16_; ++p_180707_15_)
		{
			double d13 = 1.5D + MathHelper.sin(p_180707_15_ * (float)Math.PI / p_180707_16_) * p_180707_12_ * 1.0F;
			double d6 = d13 * p_180707_17_;
			d13 *= random.nextFloat() * 0.25D + 0.75D;
			d6 *= random.nextFloat() * 0.25D + 0.75D;
			float f6 = MathHelper.cos(p_180707_14_);
			float f7 = MathHelper.sin(p_180707_14_);
			p_180707_6_ += MathHelper.cos(p_180707_13_) * f6;
			p_180707_8_ += f7;
			p_180707_10_ += MathHelper.sin(p_180707_13_) * f6;
			p_180707_14_ *= 0.7F;
			p_180707_14_ += f4 * 0.05F;
			p_180707_13_ += f3 * 0.05F;
			f4 *= 0.8F;
			f3 *= 0.5F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (flag1 || random.nextInt(4) != 0)
			{
				double d7 = p_180707_6_ - d4;
				double d8 = p_180707_10_ - d5;
				double d9 = p_180707_16_ - p_180707_15_;
				double d10 = p_180707_12_ + 2.0F + 16.0F;

				if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10)
				{
					return;
				}

				if (p_180707_6_ >= d4 - 16.0D - d13 * 2.0D && p_180707_10_ >= d5 - 16.0D - d13 * 2.0D && p_180707_6_ <= d4 + 16.0D + d13 * 2.0D && p_180707_10_ <= d5 + 16.0D + d13 * 2.0D)
				{
					int k3 = MathHelper.floor_double(p_180707_6_ - d13) - x * 16 - 1;
					int l1 = MathHelper.floor_double(p_180707_6_ + d13) - x * 16 + 1;
					int l3 = MathHelper.floor_double(p_180707_8_ - d6) - 1;
					int i2 = MathHelper.floor_double(p_180707_8_ + d6) + 1;
					int i4 = MathHelper.floor_double(p_180707_10_ - d13) - z * 16 - 1;
					int j2 = MathHelper.floor_double(p_180707_10_ + d13) - z * 16 + 1;

					if (k3 < 0)
					{
						k3 = 0;
					}

					if (l1 > 16)
					{
						l1 = 16;
					}

					if (l3 < 1)
					{
						l3 = 1;
					}

					if (i2 > 248)
					{
						i2 = 248;
					}

					if (i4 < 0)
					{
						i4 = 0;
					}

					if (j2 > 16)
					{
						j2 = 16;
					}

					boolean flag2 = false;
					int k2;

					for (k2 = k3; !flag2 && k2 < l1; ++k2)
					{
						for (int l2 = i4; !flag2 && l2 < j2; ++l2)
						{
							for (int i3 = i2 + 1; !flag2 && i3 >= l3 - 1; --i3)
							{
								if (i3 >= 0 && i3 < 256)
								{
									chunk.getBlockState(k2, i3, l2);

									if (this.isOceanBlock(chunk, k2, i2, l2, x, z))
									{
										flag2 = true;
									}
									if (i3 != l3 - 1 && k2 != k3 && k2 != l1 - 1 && l2 != i4 && l2 != j2 - 1)
									{
										i3 = l3;
									}
								}
							}
						}
					}

					if (!flag2)
					{
						for (k2 = k3; k2 < l1; ++k2)
						{
							double d14 = (k2 + x * 16 + 0.5D - p_180707_6_) / d13;

							for (int j4 = i4; j4 < j2; ++j4)
							{
								double d11 = (j4 + z * 16 + 0.5D - p_180707_10_) / d13;
								boolean flag = false;

								if (d14 * d14 + d11 * d11 < 1.0D)
								{
									for (int j3 = i2; j3 > l3; --j3)
									{
										double d12 = (j3 - 1 + 0.5D - p_180707_8_) / d6;

										if ((d14 * d14 + d11 * d11) * this.field_75046_d[j3 - 1] + d12 * d12 / 6.0D < 1.0D)
										{
											chunk.getBlockState(k2, j3, j4);

											if (this.isTopBlock(chunk, k2, j3, j4, x, z))
											{
												flag = true;
											}

											this.digBlock(chunk, k2, j3, j4, x, z, flag);
										}
									}
								}
							}
						}
						if (flag1)
						{
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void func_180701_a(World world, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_)
	{
		if (this.rand.nextInt(50) == 0)
		{
			double d0 = p_180701_2_ * 16 + this.rand.nextInt(16);
			double d1 = this.rand.nextInt(this.rand.nextInt(40) + 8) + 20;
			double d2 = p_180701_3_ * 16 + this.rand.nextInt(16);
			byte b0 = 1;

			for (int i1 = 0; i1 < b0; ++i1)
			{
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
				this.func_180707_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
			}
		}
	}

	protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
	{
		Block block = data.getBlockState(x, y, z).getBlock();
		return block == Blocks.flowing_water || block == Blocks.water;
	}

	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);
		return state.getBlock() == biome.topBlock;
	}

	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;

		if (state.getBlock() == FronosBlocks.fronos_block || state.getBlock() == FronosBlocks.jelly_ore || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
		{
			if (y < 10)
			{
				data.setBlockState(x, y, z, Blocks.lava.getDefaultState());
			}
			else
			{
				data.setBlockState(x, y, z, Blocks.air.getDefaultState());

				if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
				{
					data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
				}
			}
		}
	}
}