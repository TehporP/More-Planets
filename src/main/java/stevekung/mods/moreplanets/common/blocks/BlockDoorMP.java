/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class BlockDoorMP extends BlockDoor
{
	private DoorType doorType;

	public static enum DoorType
	{
		ANCIENT_DARK, ORANGE, COCONUT, MAPLE, CRYSTAL;
	}

	public BlockDoorMP(String name, DoorType type)
	{
		super(Material.wood);
		this.doorType = type;
		this.setHardness(3.0F);
		this.setStepSound(soundTypeWood);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoorMP.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoorMP.EnumDoorHalf.LOWER));
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition mov, World world, BlockPos pos)
	{
		if (this.doorType == DoorType.ANCIENT_DARK)
		{
			return new ItemStack(NibiruItems.ancient_dark_door);
		}
		else if (this.doorType == DoorType.ORANGE)
		{
			return new ItemStack(NibiruItems.orange_door);
		}
		else if (this.doorType == DoorType.COCONUT)
		{
			return new ItemStack(FronosItems.coconut_door);
		}
		else if (this.doorType == DoorType.MAPLE)
		{
			return new ItemStack(FronosItems.maple_door);
		}
		else if (this.doorType == DoorType.CRYSTAL)
		{
			return new ItemStack(KoentusItems.crystal_door);
		}
		return null;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (!(state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER))
		{
			if (this.doorType == DoorType.ANCIENT_DARK)
			{
				return NibiruItems.ancient_dark_door;
			}
			else if (this.doorType == DoorType.ORANGE)
			{
				return NibiruItems.orange_door;
			}
			else if (this.doorType == DoorType.COCONUT)
			{
				return FronosItems.coconut_door;
			}
			else if (this.doorType == DoorType.MAPLE)
			{
				return FronosItems.maple_door;
			}
			else if (this.doorType == DoorType.CRYSTAL)
			{
				return KoentusItems.crystal_door;
			}
		}
		return null;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
		{
			if (world.getBlockState(pos.down()).getBlock() != this)
			{
				world.setBlockToAir(pos);
			}
			else if (neighborBlock != this)
			{
				this.onNeighborBlockChange(world, pos.down(), world.getBlockState(pos.down()), neighborBlock);
			}
		}
		else
		{
			boolean flag1 = false;

			if (world.getBlockState(pos.up()).getBlock() != this)
			{
				world.setBlockToAir(pos);
				flag1 = true;
			}

			if (!World.doesBlockHaveSolidTopSurface(world, pos.down()))
			{
				world.setBlockToAir(pos);
				flag1 = true;

				if (world.getBlockState(pos.up()).getBlock() == this)
				{
					world.setBlockToAir(pos.up());
				}
			}

			if (flag1)
			{
				if (!world.isRemote)
				{
					this.dropBlockAsItem(world, pos, state, 0);
				}
			}
			else
			{
				boolean flag = world.isBlockPowered(pos) || world.isBlockPowered(pos.up());

				if ((flag || neighborBlock.canProvidePower()) && neighborBlock != this && flag != ((Boolean)world.getBlockState(pos.up()).getValue(POWERED)).booleanValue())
				{
					world.setBlockState(pos.up(), world.getBlockState(pos.up()).withProperty(POWERED, Boolean.valueOf(flag)), 2);

					if (flag != ((Boolean)state.getValue(OPEN)).booleanValue())
					{
						world.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
						world.markBlockRangeForRenderUpdate(pos, pos);
						world.playAuxSFXAtEntity((EntityPlayer)null, flag ? 1003 : 1006, pos, 0);
					}
				}
			}
		}
	}
}