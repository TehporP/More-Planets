/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockMorePlanets;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIcyPoisonCrystal;

public class ItemBlockIcyPoisonCrystal extends ItemBlockMorePlanets
{
	public ItemBlockIcyPoisonCrystal(Block block)
	{
		super(block);
	}

	@Override
	public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState state)
	{
		boolean placed = super.placeBlockAt(itemStack, player, world, pos, side, hitX, hitY, hitZ, state);

		if (placed && state.getBlock().getMetaFromState(state) <= 6)
		{
			TileEntityIcyPoisonCrystal ts = (TileEntityIcyPoisonCrystal)world.getTileEntity(pos);
			ts.facing = (short)side.getIndex();
		}
		return placed;
	}
}