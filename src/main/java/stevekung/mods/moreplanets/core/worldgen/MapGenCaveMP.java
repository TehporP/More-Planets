/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MapGenCaveMP extends MapGenBaseMeta
{
	private Block block;

	public MapGenCaveMP(Block block)
	{
		this.block = block;
	}

	public static int BREAK_THROUGH_CHANCE = 25; // 1 in n chance

	protected void generateLargeCaveNode(long par1, int par3, int par4, Block[] blockIdArray, byte[] metaArray, double par6, double par8, double par10)
	{
		this.generateCaveNode(par1, par3, par4, blockIdArray, metaArray, par6, par8, par10, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	protected void generateCaveNode(long par1, int par3, int par4, Block[] blockIdArray, byte[] metaArray, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17)
	{
		double d4 = par3 * 16 + 8;
		double d5 = par4 * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;
		Random random = new Random(par1);

		if (par16 <= 0)
		{
			int j1 = this.range * 16 - 16;
			par16 = j1 - random.nextInt(j1 / 4);
		}

		boolean flag = false;

		if (par15 == -1)
		{
			par15 = par16 / 2;
			flag = true;
		}

		int k1 = random.nextInt(par16 / 2) + par16 / 4;

		for (boolean flag1 = random.nextInt(6) == 0; par15 < par16; ++par15)
		{
			double d6 = 1.5D + MathHelper.sin(par15 * (float) Math.PI / par16) * par12 * 1.0F;
			double d7 = d6 * par17;
			float f5 = MathHelper.cos(par14);
			float f6 = MathHelper.sin(par14);
			par6 += MathHelper.cos(par13) * f5;
			par8 += f6;
			par10 += MathHelper.sin(par13) * f5;

			if (flag1)
			{
				par14 *= 0.92F;
			}
			else
			{
				par14 *= 0.7F;
			}

			par14 += f4 * 0.1F;
			par13 += f3 * 0.1F;
			f4 *= 0.9F;
			f3 *= 0.75F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (!flag && par15 == k1 && par12 > 1.0F && par16 > 0)
			{
				this.generateCaveNode(random.nextLong(), par3, par4, blockIdArray, metaArray, par6, par8, par10, random.nextFloat() * 0.5F + 0.5F, par13 - (float) Math.PI / 2F, par14 / 3.0F, par15, par16, 1.0D);
				this.generateCaveNode(random.nextLong(), par3, par4, blockIdArray, metaArray, par6, par8, par10, random.nextFloat() * 0.5F + 0.5F, par13 + (float) Math.PI / 2F, par14 / 3.0F, par15, par16, 1.0D);
				return;
			}

			if (flag || random.nextInt(4) != 0)
			{
				double d8 = par6 - d4;
				double d9 = par10 - d5;
				double d10 = par16 - par15;
				double d11 = par12 + 2.0F + 16.0F;

				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
				{
					return;
				}

				if (par6 >= d4 - 16.0D - d6 * 2.0D && par10 >= d5 - 16.0D - d6 * 2.0D && par6 <= d4 + 16.0D + d6 * 2.0D && par10 <= d5 + 16.0D + d6 * 2.0D)
				{
					int l1 = MathHelper.floor_double(par6 - d6) - par3 * 16 - 1;
					int i2 = MathHelper.floor_double(par6 + d6) - par3 * 16 + 1;
					int j2 = MathHelper.floor_double(par8 - d7) - 1;
					int k2 = MathHelper.floor_double(par8 + d7) + 1;
					int l2 = MathHelper.floor_double(par10 - d6) - par4 * 16 - 1;
					int i3 = MathHelper.floor_double(par10 + d6) - par4 * 16 + 1;

					if (l1 < 0)
					{
						l1 = 0;
					}

					if (i2 > 16)
					{
						i2 = 16;
					}

					if (j2 < 1)
					{
						j2 = 1;
					}

					if (k2 > 120)
					{
						k2 = 120;
					}

					if (l2 < 0)
					{
						l2 = 0;
					}

					if (i3 > 16)
					{
						i3 = 16;
					}

					int j3;
					for (j3 = l1; j3 < i2; ++j3)
					{
						for (int l3 = l2; l3 < i3; ++l3)
						{
							for (int i4 = k2 + 1; i4 >= j2 - 1; --i4)
							{
								if (i4 >= 0 && i4 < 128)
								{
									if (i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1)
									{
										i4 = j2;
									}
								}
							}
						}
					}

					if (true)
					{

						for (int localY = j2; localY < k2; localY++)
						{
							double yfactor = (localY + 0.5D - par8) / d7;
							double yfactorSq = yfactor * yfactor;

							for (int localX = l1; localX < i2; localX++)
							{
								double zfactor = (localX + par3 * 16 + 0.5D - par6) / d6;
								double zfactorSq = zfactor * zfactor;

								for (int localZ = l2; localZ < i3; localZ++)
								{
									double xfactor = (localZ + par4 * 16 + 0.5D - par10) / d6;
									double xfactorSq = xfactor * xfactor;

									if (xfactorSq + zfactorSq < 1.0D)
									{
										int coords = (localX * 16 + localZ) * 256 + localY;

										if (yfactor > -0.7D && xfactorSq + yfactorSq + zfactorSq < 1.0D)
										{
											if (blockIdArray[coords] == this.block)
											{
												if (metaArray[coords] == 1 || metaArray[coords] == 2)
												{
													blockIdArray[coords] = Blocks.air;
												}
												else if (metaArray[coords] == 0 && random.nextInt(MapGenCaveMP.BREAK_THROUGH_CHANCE) == 0)
												{
													blockIdArray[coords] = Blocks.air;
												}
											}
										}
									}
								}
							}
						}

						if (flag)
						{
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, Block[] blockIdArray, byte[] metaArray)
	{
		int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);

		if (this.rand.nextInt(15) != 0)
		{
			var7 = 0;
		}

		for (int var8 = 0; var8 < var7; ++var8)
		{
			double var9 = par2 * 16 + this.rand.nextInt(16);
			double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			double var13 = par3 * 16 + this.rand.nextInt(16);
			int var15 = 1;

			if (this.rand.nextInt(4) == 0)
			{
				this.generateLargeCaveNode(this.rand.nextLong(), par4, par5, blockIdArray, metaArray, var9, var11, var13);
				var15 += this.rand.nextInt(4);
			}

			for (int var16 = 0; var16 < var15; ++var16)
			{
				float var17 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0)
				{
					var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}
				this.generateCaveNode(this.rand.nextLong(), par4, par5, blockIdArray, metaArray, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
			}
		}
	}
}

//package stevekung.mods.moreplanets.core.worldgen;
//
//import java.util.Random;
//
//import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
//import net.minecraft.block.Block;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public class MapGenCaveMP extends MapGenBaseMeta
//{
//	private Block block;
//	public static int BREAK_THROUGH_CHANCE = 25;
//
//	public MapGenCaveMP(Block block)
//	{
//		this.block = block;
//	}
//
//	protected void func_151544_a(long seed, int chunkX, int chunkZ, Block[] block, byte[] meta, double x, double y, double z)
//	{
//		this.func_151543_a(seed, chunkX, chunkZ, block, meta, x, y, z, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
//	}
//
//	protected void func_151543_a(long seed, int chunkX, int chunkZ, Block[] block, byte[] meta, double x, double y, double z, float par1, float par2, float par3, int par4, int par5, double par6)
//	{
//		double d4 = chunkX * 16 + 8;
//		double d5 = chunkZ * 16 + 8;
//		float f3 = 0.0F;
//		float f4 = 0.0F;
//		Random rand = new Random(seed);
//
//		if (par5 <= 0)
//		{
//			int j1 = this.range * 16 - 16;
//			par5 = j1 - rand.nextInt(j1 / 4);
//		}
//
//		boolean flag1 = false;
//
//		if (par4 == -1)
//		{
//			par4 = par5 / 2;
//			flag1 = true;
//		}
//
//		int k1 = rand.nextInt(par5 / 2) + par5 / 4;
//
//		for (boolean flag = rand.nextInt(6) == 0; par4 < par5; ++par4)
//		{
//			double d6 = 1.5D + MathHelper.sin(par4 * (float)Math.PI / par5) * par1 * 1.0F;
//			double d7 = d6 * par6;
//			float f5 = MathHelper.cos(par3);
//			float f6 = MathHelper.sin(par3);
//			x += MathHelper.cos(par2) * f5;
//			y += f6;
//			z += MathHelper.sin(par2) * f5;
//
//			if (flag)
//			{
//				par3 *= 0.92F;
//			}
//			else
//			{
//				par3 *= 0.7F;
//			}
//
//			par3 += f4 * 0.1F;
//			par2 += f3 * 0.1F;
//			f4 *= 0.9F;
//			f3 *= 0.75F;
//			f4 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
//			f3 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;
//
//			if (!flag1 && par4 == k1 && par1 > 1.0F)
//			{
//				this.func_151543_a(rand.nextLong(), chunkX, chunkZ, block, meta, x, y, z, rand.nextFloat() * 0.5F + 0.5F, par2 - (float)Math.PI / 2F, par3 / 3.0F, par4, par5, 1.0D);
//				this.func_151543_a(rand.nextLong(), chunkX, chunkZ, block, meta, x, y, z, rand.nextFloat() * 0.5F + 0.5F, par2 + (float)Math.PI / 2F, par3 / 3.0F, par4, par5, 1.0D);
//				return;
//			}
//
//			if (flag1 || rand.nextInt(4) != 0)
//			{
//				double d8 = x - d4;
//				double d9 = z - d5;
//				double d10 = par5 - par4;
//				double d11 = par1 + 2.0F + 16.0F;
//
//				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
//				{
//					return;
//				}
//
//				if (x >= d4 - 16.0D - d6 * 2.0D && z >= d5 - 16.0D - d6 * 2.0D && x <= d4 + 16.0D + d6 * 2.0D && z <= d5 + 16.0D + d6 * 2.0D)
//				{
//					int i4 = MathHelper.floor_double(x - d6) - chunkX * 16 - 1;
//					int l1 = MathHelper.floor_double(x + d6) - chunkX * 16 + 1;
//					int j4 = MathHelper.floor_double(y - d7) - 1;
//					int i2 = MathHelper.floor_double(y + d7) + 1;
//					int k4 = MathHelper.floor_double(z - d6) - chunkZ * 16 - 1;
//					int j2 = MathHelper.floor_double(z + d6) - chunkZ * 16 + 1;
//
//					if (i4 < 0)
//					{
//						i4 = 0;
//					}
//
//					if (l1 > 16)
//					{
//						l1 = 16;
//					}
//
//					if (j4 < 1)
//					{
//						j4 = 1;
//					}
//
//					if (i2 > 120)
//					{
//						i2 = 120;
//					}
//
//					if (k4 < 0)
//					{
//						k4 = 0;
//					}
//
//					if (j2 > 16)
//					{
//						j2 = 16;
//					}
//
//					boolean flag2 = false;
//					int k2;
//					int j3;
//
//					for (k2 = i4; !flag2 && k2 < l1; ++k2)
//					{
//						for (int l2 = k4; !flag2 && l2 < j2; ++l2)
//						{
//							for (int i3 = i2 + 1; !flag2 && i3 >= j4 - 1; --i3)
//							{
//								if (i3 >= 0 && i3 < 128)
//								{
//									if (i3 != j4 - 1 && k2 != i4 && k2 != l1 - 1 && l2 != k4 && l2 != j2 - 1)
//									{
//										i3 = j4;
//									}
//								}
//							}
//						}
//					}
//
//					if (!flag2)
//					{
//						for (k2 = i4; k2 < l1; ++k2)
//						{
//							double d13 = (k2 + chunkX * 16 + 0.5D - x) / d6;
//
//							for (j3 = k4; j3 < j2; ++j3)
//							{
//								double d14 = (j3 + chunkZ * 16 + 0.5D - z) / d6;
//								int k3 = (k2 * 16 + j3) * 128 + i2;
//
//								for (int l3 = i2 - 1; l3 >= j4; --l3)
//								{
//									double d12 = (l3 + 0.5D - y) / d7;
//
//									if (d12 > -0.7D && d13 * d13 + d12 * d12 + d14 * d14 < 1.0D)
//									{
//										if (block[k3] == this.block)
//										{
//											if (meta[k3] == 1 || meta[k3] == 2)
//											{
//												block[k3] = Blocks.air;
//											}
//											else if (meta[k3] == 0 && rand.nextInt(MapGenCaveMP.BREAK_THROUGH_CHANCE) == 0)
//											{
//												block[k3] = Blocks.air;
//											}
//										}
//									}
//									--k3;
//								}
//							}
//						}
//						if (flag1)
//						{
//							break;
//						}
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	protected void recursiveGenerate(World world, int x, int z, int orX, int orZ, Block[] block, byte[] meta)
//	{
//		int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(10) + 1) + 1);
//
//		if (this.rand.nextInt(5) != 0)
//		{
//			i1 = 0;
//		}
//
//		for (int j1 = 0; j1 < i1; ++j1)
//		{
//			double d0 = x * 16 + this.rand.nextInt(16);
//			double d1 = this.rand.nextInt(128);
//			double d2 = z * 16 + this.rand.nextInt(16);
//			int k1 = 1;
//
//			if (this.rand.nextInt(4) == 0)
//			{
//				this.func_151544_a(this.rand.nextLong(), orX, orZ, block, meta, d0, d1, d2);
//				k1 += this.rand.nextInt(4);
//			}
//
//			for (int l1 = 0; l1 < k1; ++l1)
//			{
//				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
//				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
//				float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
//				this.func_151543_a(this.rand.nextLong(), orX, orZ, block, meta, d0, d1, d2, f2 * 2.0F, f, f1, 0, 0, 0.5D);
//			}
//		}
//	}
//}