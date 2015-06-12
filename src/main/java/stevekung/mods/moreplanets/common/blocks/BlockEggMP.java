/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockEggMP extends BlockFalling
{
	public BlockEggMP()
	{
		super(Material.dragonEgg);
		this.setResistance(0.0F);
		this.setHardness(-1.0F);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
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
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetsCore.mpBlocksTab;
	}

	/*@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		ItemStack stack = player.inventory.getCurrentItem();

		if (stack == null)
		{
			return player.canHarvestBlock(this);
		}
		return stack.getItem() == MarsItems.deshPickSlime;
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, BlockPos pos)
	{
		ItemStack stack = player.inventory.getCurrentItem();
		IBlockState state = world.getBlockState(pos);

		if (stack != null && stack.getItem() == MarsItems.deshPickSlime)
		{
			return 0.1F;
		}
		return ForgeHooks.blockStrength(state.getBlock().getDefaultState(), player, world, pos);
	}*/
}