/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class ItemDoorMP extends ItemMorePlanets
{
	private DoorType doorType;

	public ItemDoorMP(String name, DoorType type)
	{
		this.doorType = type;
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (side != EnumFacing.UP)
		{
			return false;
		}
		else
		{
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (this.doorType == DoorType.ANCIENT_DARK)
			{
				block = NibiruBlocks.ancient_dark_door;
			}
			else if (this.doorType == DoorType.ORANGE)
			{
				block = NibiruBlocks.orange_door;
			}
			else if (this.doorType == DoorType.COCONUT)
			{
				block = FronosBlocks.coconut_door_block;
			}
			else if (this.doorType == DoorType.MAPLE)
			{
				block = FronosBlocks.maple_door_block;
			}
			else if (this.doorType == DoorType.CRYSTAL)
			{
				block = KoentusBlocks.crystal_door;
			}

			if (!block.isReplaceable(world, pos))
			{
				pos = pos.offset(side);
			}

			if (!player.canPlayerEdit(pos, side, stack))
			{
				return false;
			}
			else if (!block.canPlaceBlockAt(world, pos))
			{
				return false;
			}
			else
			{
				placeDoor(world, pos, EnumFacing.fromAngle(player.rotationYaw), block);
				--stack.stackSize;
				return true;
			}
		}
	}

	public static void placeDoor(World worldIn, BlockPos pos, EnumFacing facing, Block door)
	{
		BlockPos blockpos1 = pos.offset(facing.rotateY());
		BlockPos blockpos2 = pos.offset(facing.rotateYCCW());
		int i = (worldIn.getBlockState(blockpos2).getBlock().isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos2.up()).getBlock().isNormalCube() ? 1 : 0);
		int j = (worldIn.getBlockState(blockpos1).getBlock().isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos1.up()).getBlock().isNormalCube() ? 1 : 0);
		boolean flag = worldIn.getBlockState(blockpos2).getBlock() == door || worldIn.getBlockState(blockpos2.up()).getBlock() == door;
		boolean flag1 = worldIn.getBlockState(blockpos1).getBlock() == door || worldIn.getBlockState(blockpos1.up()).getBlock() == door;
		boolean flag2 = false;

		if (flag && !flag1 || j > i)
		{
			flag2 = true;
		}

		BlockPos blockpos3 = pos.up();
		IBlockState iblockstate = door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, flag2 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT);
		worldIn.setBlockState(pos, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
		worldIn.setBlockState(blockpos3, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
		worldIn.notifyNeighborsOfStateChange(pos, door);
		worldIn.notifyNeighborsOfStateChange(blockpos3, door);
	}
}