/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.common.entities.IImmuneMapleIvy;

public class BlockMapleIvy extends BlockBaseMP implements IShearable
{
	public static PropertyBool UP = PropertyBool.create("up");
	public static PropertyBool NORTH = PropertyBool.create("north");
	public static PropertyBool EAST = PropertyBool.create("east");
	public static PropertyBool SOUTH = PropertyBool.create("south");
	public static PropertyBool WEST = PropertyBool.create("west");
	public static PropertyBool[] ALL_FACES = new PropertyBool[] {UP, NORTH, SOUTH, WEST, EAST};
	public static int SOUTH_FLAG = getMetaFlag(EnumFacing.SOUTH);
	public static int NORTH_FLAG = getMetaFlag(EnumFacing.NORTH);
	public static int EAST_FLAG = getMetaFlag(EnumFacing.EAST);
	public static int WEST_FLAG = getMetaFlag(EnumFacing.WEST);

	public BlockMapleIvy(String name)
	{
		super(Material.vine);
		this.setStepSound(soundTypeGrass);
		this.setDefaultState(this.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		this.setTickRandomly(true);
		this.setUnlocalizedName(name);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.withProperty(UP, Boolean.valueOf(world.getBlockState(pos.up()).getBlock().isSolidFullCube()));
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
	{
		float f1 = 1.0F;
		float f2 = 1.0F;
		float f3 = 1.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean flag = false;

		if (((Boolean)world.getBlockState(pos).getValue(WEST)).booleanValue())
		{
			f4 = Math.max(f4, 0.0625F);
			f1 = 0.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}
		if (((Boolean)world.getBlockState(pos).getValue(EAST)).booleanValue())
		{
			f1 = Math.min(f1, 0.9375F);
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}
		if (((Boolean)world.getBlockState(pos).getValue(NORTH)).booleanValue())
		{
			f6 = Math.max(f6, 0.0625F);
			f3 = 0.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}
		if (((Boolean)world.getBlockState(pos).getValue(SOUTH)).booleanValue())
		{
			f3 = Math.min(f3, 0.9375F);
			f6 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}
		if (!flag && this.canPlaceOn(world.getBlockState(pos.up()).getBlock()))
		{
			f2 = Math.min(f2, 0.9375F);
			f5 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
		}
		this.setBlockBounds(f1, f2, f3, f4, f5, f6);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
	{
		switch (SwitchEnumFacing.FACING_LOOKUP[side.ordinal()])
		{
		case 1:
			return this.canPlaceOn(world.getBlockState(pos.up()).getBlock());
		case 2:
		case 3:
		case 4:
		case 5:
			return this.canPlaceOn(world.getBlockState(pos.offset(side.getOpposite())).getBlock());
		default:
			return false;
		}
	}

	private boolean canPlaceOn(Block block)
	{
		return block.isFullCube() && block.getMaterial().blocksMovement();
	}

	private boolean recheckGrownSides(World world, BlockPos pos, IBlockState state)
	{
		IBlockState iblockstate1 = state;
		Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

		while (iterator.hasNext())
		{
			EnumFacing enumfacing = (EnumFacing)iterator.next();
			PropertyBool propertybool = getPropertyFor(enumfacing);

			if (((Boolean)state.getValue(propertybool)).booleanValue() && !this.canPlaceOn(world.getBlockState(pos.offset(enumfacing)).getBlock()))
			{
				IBlockState iblockstate2 = world.getBlockState(pos.up());

				if (iblockstate2.getBlock() != this || !((Boolean)iblockstate2.getValue(propertybool)).booleanValue())
				{
					state = state.withProperty(propertybool, Boolean.valueOf(false));
				}
			}
		}

		if (getNumGrownFaces(state) == 0)
		{
			return false;
		}
		else
		{
			if (iblockstate1 != state)
			{
				world.setBlockState(pos, state, 2);
			}
			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (!world.isRemote && !this.recheckGrownSides(world, pos, state))
		{
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			if (world.rand.nextInt(4) == 0)
			{
				byte b0 = 4;
				int i = 5;
				boolean flag = false;
				label189:

					for (int j = -b0; j <= b0; ++j)
					{
						for (int k = -b0; k <= b0; ++k)
						{
							for (int l = -1; l <= 1; ++l)
							{
								if (world.getBlockState(pos.add(j, l, k)).getBlock() == this)
								{
									--i;

									if (i <= 0)
									{
										flag = true;
										break label189;
									}
								}
							}
						}
					}

				EnumFacing enumfacing1 = EnumFacing.random(rand);
				EnumFacing enumfacing2;

				if (enumfacing1 == EnumFacing.UP && pos.getY() < 255 && world.isAirBlock(pos.up()))
				{
					if (!flag)
					{
						IBlockState iblockstate2 = state;
						Iterator iterator1 = EnumFacing.Plane.HORIZONTAL.iterator();

						while (iterator1.hasNext())
						{
							enumfacing2 = (EnumFacing)iterator1.next();

							if (rand.nextBoolean() || !this.canPlaceOn(world.getBlockState(pos.offset(enumfacing2).up()).getBlock()))
							{
								iblockstate2 = iblockstate2.withProperty(getPropertyFor(enumfacing2), Boolean.valueOf(false));
							}
						}

						if (((Boolean)iblockstate2.getValue(NORTH)).booleanValue() || ((Boolean)iblockstate2.getValue(EAST)).booleanValue() || ((Boolean)iblockstate2.getValue(SOUTH)).booleanValue() || ((Boolean)iblockstate2.getValue(WEST)).booleanValue())
						{
							world.setBlockState(pos.up(), iblockstate2, 2);
						}
					}
				}
				else
				{
					BlockPos blockpos2;

					if (enumfacing1.getAxis().isHorizontal() && !((Boolean)state.getValue(getPropertyFor(enumfacing1))).booleanValue())
					{
						if (!flag)
						{
							blockpos2 = pos.offset(enumfacing1);
							Block block1 = world.getBlockState(blockpos2).getBlock();

							if (block1.getMaterial() == Material.air)
							{
								enumfacing2 = enumfacing1.rotateY();
								EnumFacing enumfacing3 = enumfacing1.rotateYCCW();
								boolean flag1 = ((Boolean)state.getValue(getPropertyFor(enumfacing2))).booleanValue();
								boolean flag2 = ((Boolean)state.getValue(getPropertyFor(enumfacing3))).booleanValue();
								BlockPos blockpos3 = blockpos2.offset(enumfacing2);
								BlockPos blockpos1 = blockpos2.offset(enumfacing3);

								if (flag1 && this.canPlaceOn(world.getBlockState(blockpos3).getBlock()))
								{
									world.setBlockState(blockpos2, this.getDefaultState().withProperty(getPropertyFor(enumfacing2), Boolean.valueOf(true)), 2);
								}
								else if (flag2 && this.canPlaceOn(world.getBlockState(blockpos1).getBlock()))
								{
									world.setBlockState(blockpos2, this.getDefaultState().withProperty(getPropertyFor(enumfacing3), Boolean.valueOf(true)), 2);
								}
								else if (flag1 && world.isAirBlock(blockpos3) && this.canPlaceOn(world.getBlockState(pos.offset(enumfacing2)).getBlock()))
								{
									world.setBlockState(blockpos3, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), Boolean.valueOf(true)), 2);
								}
								else if (flag2 && world.isAirBlock(blockpos1) && this.canPlaceOn(world.getBlockState(pos.offset(enumfacing3)).getBlock()))
								{
									world.setBlockState(blockpos1, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), Boolean.valueOf(true)), 2);
								}
								else if (this.canPlaceOn(world.getBlockState(blockpos2.up()).getBlock()))
								{
									world.setBlockState(blockpos2, this.getDefaultState(), 2);
								}
							}
							else if (block1.getMaterial().isOpaque() && block1.isFullCube())
							{
								world.setBlockState(pos, state.withProperty(getPropertyFor(enumfacing1), Boolean.valueOf(true)), 2);
							}
						}
					}
					else
					{
						if (pos.getY() > 1)
						{
							blockpos2 = pos.down();
							IBlockState iblockstate3 = world.getBlockState(blockpos2);
							Block block = iblockstate3.getBlock();
							IBlockState iblockstate1;
							Iterator iterator;
							EnumFacing enumfacing;

							if (block.getMaterial() == Material.air)
							{
								iblockstate1 = state;
								iterator = EnumFacing.Plane.HORIZONTAL.iterator();

								while (iterator.hasNext())
								{
									enumfacing = (EnumFacing)iterator.next();

									if (rand.nextBoolean())
									{
										iblockstate1 = iblockstate1.withProperty(getPropertyFor(enumfacing), Boolean.valueOf(false));
									}
								}

								if (((Boolean)iblockstate1.getValue(NORTH)).booleanValue() || ((Boolean)iblockstate1.getValue(EAST)).booleanValue() || ((Boolean)iblockstate1.getValue(SOUTH)).booleanValue() || ((Boolean)iblockstate1.getValue(WEST)).booleanValue())
								{
									world.setBlockState(blockpos2, iblockstate1, 2);
								}
							}
							else if (block == this)
							{
								iblockstate1 = iblockstate3;
								iterator = EnumFacing.Plane.HORIZONTAL.iterator();

								while (iterator.hasNext())
								{
									enumfacing = (EnumFacing)iterator.next();
									PropertyBool propertybool = getPropertyFor(enumfacing);

									if (rand.nextBoolean() || !((Boolean)state.getValue(propertybool)).booleanValue())
									{
										iblockstate1 = iblockstate1.withProperty(propertybool, Boolean.valueOf(false));
									}
								}

								if (((Boolean)iblockstate1.getValue(NORTH)).booleanValue() || ((Boolean)iblockstate1.getValue(EAST)).booleanValue() || ((Boolean)iblockstate1.getValue(SOUTH)).booleanValue() || ((Boolean)iblockstate1.getValue(WEST)).booleanValue())
								{
									world.setBlockState(blockpos2, iblockstate1, 2);
								}
							}
						}
					}
				}
			}
		}
	}

	private static int getMetaFlag(EnumFacing face)
	{
		return 1 << face.getHorizontalIndex();
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState iblockstate = this.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false));
		return facing.getAxis().isHorizontal() ? iblockstate.withProperty(getPropertyFor(facing.getOpposite()), Boolean.valueOf(true)) : iblockstate;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
	{
		super.harvestBlock(world, player, pos, state, te);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(NORTH, Boolean.valueOf((meta & NORTH_FLAG) > 0)).withProperty(EAST, Boolean.valueOf((meta & EAST_FLAG) > 0)).withProperty(SOUTH, Boolean.valueOf((meta & SOUTH_FLAG) > 0)).withProperty(WEST, Boolean.valueOf((meta & WEST_FLAG) > 0));
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		if (((Boolean)state.getValue(NORTH)).booleanValue())
		{
			i |= NORTH_FLAG;
		}
		if (((Boolean)state.getValue(EAST)).booleanValue())
		{
			i |= EAST_FLAG;
		}
		if (((Boolean)state.getValue(SOUTH)).booleanValue())
		{
			i |= SOUTH_FLAG;
		}
		if (((Boolean)state.getValue(WEST)).booleanValue())
		{
			i |= WEST_FLAG;
		}
		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {UP, NORTH, EAST, SOUTH, WEST});
	}

	public static PropertyBool getPropertyFor(EnumFacing side)
	{
		switch (BlockMapleIvy.SwitchEnumFacing.FACING_LOOKUP[side.ordinal()])
		{
		case 1:
			return UP;
		case 2:
			return NORTH;
		case 3:
			return SOUTH;
		case 4:
			return EAST;
		case 5:
			return WEST;
		default:
			throw new IllegalArgumentException(side + " is an invalid choice");
		}
	}

	public static int getNumGrownFaces(IBlockState state)
	{
		int i = 0;
		PropertyBool[] apropertybool = ALL_FACES;
		int j = apropertybool.length;

		for (int k = 0; k < j; ++k)
		{
			PropertyBool propertybool = apropertybool[k];

			if (((Boolean)state.getValue(propertybool)).booleanValue())
			{
				++i;
			}
		}
		return i;
	}

	@Override
	public boolean isLadder(IBlockAccess world, BlockPos pos, EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		return Arrays.asList(new ItemStack(this, 1, 0));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (!(entity instanceof IImmuneMapleIvy) || !((IImmuneMapleIvy)entity).canLivingInIvy())
			{
				if (world.rand.nextInt(2000) == 0)
				{
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120));
				}
			}
		}
	}

	static class SwitchEnumFacing
	{
		static int[] FACING_LOOKUP = new int[EnumFacing.values().length];

		static
		{
			try
			{
				FACING_LOOKUP[EnumFacing.UP.ordinal()] = 1;
			}
			catch (NoSuchFieldError var5)
			{
			}

			try
			{
				FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 2;
			}
			catch (NoSuchFieldError var4)
			{
			}

			try
			{
				FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 3;
			}
			catch (NoSuchFieldError var3)
			{
			}

			try
			{
				FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 4;
			}
			catch (NoSuchFieldError var2)
			{
			}

			try
			{
				FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 5;
			}
			catch (NoSuchFieldError var1)
			{
			}
		}
	}
}