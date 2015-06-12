/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class BlockDoubleWoodenSlab1MP extends BlockWoodenSlab1MP
{
	public BlockDoubleWoodenSlab1MP(String name, Material material)
	{
		super(material);
		this.setStepSound(soundTypeWood);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean isDouble()
	{
		return true;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return null;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(MPBlocks.half_wooden_slab_1);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 2;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state) & 7;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(MPBlocks.half_wooden_slab_1, 1, this.getMetaFromState(world.getBlockState(pos)) & 7);
	}
}