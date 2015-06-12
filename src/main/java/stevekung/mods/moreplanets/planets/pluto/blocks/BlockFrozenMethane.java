/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockIceMP;

public class BlockFrozenMethane extends BlockIceMP
{
	public BlockFrozenMethane(String name)
	{
		super(Material.ice);
		this.slipperiness = 1.1F;
		this.setUnlocalizedName(name);
		this.setHardness(4.0F);
		this.setResistance(8.0F);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		ItemStack itemStack = player.getCurrentEquippedItem();
		player.addExhaustion(0.025F);

		if (itemStack == null || !(itemStack.getItem() instanceof ItemPickaxe))
		{
			if (world.rand.nextInt(10) == 0)
			{
				world.setBlockState(pos, PlutoBlocks.liquid_methane.getDefaultState());
			}
		}
		if (itemStack != null && itemStack.getItem() instanceof ItemPickaxe)
		{
			this.dropBlockAsItem(world, pos, state, 0);
		}
	}
}