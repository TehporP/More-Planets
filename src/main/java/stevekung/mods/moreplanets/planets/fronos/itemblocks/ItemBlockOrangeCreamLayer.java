/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockMorePlanets;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCreamLayer;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemBlockOrangeCreamLayer extends ItemBlockMorePlanets
{
	public ItemBlockOrangeCreamLayer(Block block)
	{
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (itemStack.stackSize == 0)
		{
			return false;
		}
		else if (!player.canPlayerEdit(pos, side, itemStack))
		{
			return false;
		}
		else
		{
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (block != this.block && side != EnumFacing.UP)
			{
				pos = pos.offset(side);
				state = world.getBlockState(pos);
				block = state.getBlock();
			}

			if (block == this.block)
			{
				int i = ((Integer)state.getValue(BlockCreamLayer.LAYERS)).intValue();

				if (i <= 7)
				{
					IBlockState state1 = state.withProperty(BlockCreamLayer.LAYERS, Integer.valueOf(i + 1));

					if (world.checkNoEntityCollision(this.block.getCollisionBoundingBox(world, pos, state1)) && world.setBlockState(pos, state1, 2))
					{
						world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
						--itemStack.stackSize;
						return true;
					}
				}
			}
			return super.onItemUse(itemStack, player, world, pos, side, hitX, hitY, hitZ);
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
	{
		IBlockState state = world.getBlockState(pos);
		return state.getBlock() != FronosBlocks.orange_cream_layer || (Integer)state.getValue(BlockCreamLayer.LAYERS) > 7 ? super.canPlaceBlockOnSide(world, pos, side, player, stack) : true;
	}
}