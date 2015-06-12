/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockSiriusRedstoneLamp extends BlockBaseMP
{
	private boolean on;

	public BlockSiriusRedstoneLamp(String name, boolean on)
	{
		super(Material.redstoneLight);
		this.on = on;
		this.setUnlocalizedName(name);
		this.setStepSound(soundTypeGlass);

		if (on)
		{
			this.setLightLevel(1.0F);
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		if (this.on)
		{
			return null;
		}
		return MorePlanetsCore.mpBlocksTab;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			if (this.on && !world.isBlockPowered(pos))
			{
				world.setBlockState(pos, SiriusBBlocks.sirius_redstone_lamp_off.getDefaultState(), 2);
			}
			else if (!this.on && world.isBlockPowered(pos))
			{
				world.setBlockState(pos, SiriusBBlocks.sirius_redstone_lamp_on.getDefaultState(), 2);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
	{
		if (!world.isRemote)
		{
			if (this.on && !world.isBlockPowered(pos))
			{
				world.setBlockState(pos, SiriusBBlocks.sirius_redstone_lamp_off.getDefaultState(), 2);
			}
			else if (!this.on && world.isBlockPowered(pos))
			{
				world.setBlockState(pos, SiriusBBlocks.sirius_redstone_lamp_on.getDefaultState(), 2);
			}
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote && this.on && !world.isBlockPowered(pos))
		{
			world.setBlockState(pos, SiriusBBlocks.sirius_redstone_lamp_off.getDefaultState(), 2);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (this.on)
		{
			return Item.getItemFromBlock(SiriusBBlocks.sirius_redstone_lamp_off);
		}
		return Item.getItemFromBlock(SiriusBBlocks.sirius_redstone_lamp_off);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(SiriusBBlocks.sirius_redstone_lamp_off);
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(SiriusBBlocks.sirius_redstone_lamp_off);
	}
}