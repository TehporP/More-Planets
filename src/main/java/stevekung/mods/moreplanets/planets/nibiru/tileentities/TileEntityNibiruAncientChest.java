/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.tileentities;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import stevekung.mods.moreplanets.common.tileentities.TileEntityAncientChestMP;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class TileEntityNibiruAncientChest extends TileEntityAncientChestMP
{
	@Override
	public Block getAncientChestBlock()
	{
		return NibiruBlocks.nibiru_ancient_chest;
	}

	@Override
	public String getChestName()
	{
		return "Nibiru";
	}

	@Override
	public void checkForAdjacentChests()
	{
		if (!this.adjacentChestChecked)
		{
			this.adjacentChestChecked = true;
			this.adjacentChestXNeg = this.func_174911_a(EnumFacing.WEST);
			this.adjacentChestXPos = this.func_174911_a(EnumFacing.EAST);
			this.adjacentChestZNeg = this.func_174911_a(EnumFacing.NORTH);
			this.adjacentChestZPos = this.func_174911_a(EnumFacing.SOUTH);
		}
	}

	protected TileEntityNibiruAncientChest func_174911_a(EnumFacing side)
	{
		BlockPos blockpos = this.pos.offset(side);

		if (this.func_174912_b(blockpos))
		{
			TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

			if (tileentity instanceof TileEntityNibiruAncientChest)
			{
				TileEntityNibiruAncientChest tileentitychest = (TileEntityNibiruAncientChest)tileentity;
				tileentitychest.func_174910_a(this, side.getOpposite());
				return tileentitychest;
			}
		}
		return null;
	}

	private void func_174910_a(TileEntityNibiruAncientChest chest, EnumFacing side)
	{
		if (chest.isInvalid())
		{
			this.adjacentChestChecked = false;
		}
		else if (this.adjacentChestChecked)
		{
			switch (SwitchEnumFacing.field_177366_a[side.ordinal()])
			{
			case 1:
				if (this.adjacentChestZNeg != chest)
				{
					this.adjacentChestChecked = false;
				}
				break;
			case 2:
				if (this.adjacentChestZPos != chest)
				{
					this.adjacentChestChecked = false;
				}
				break;
			case 3:
				if (this.adjacentChestXPos != chest)
				{
					this.adjacentChestChecked = false;
				}
				break;
			case 4:
				if (this.adjacentChestXNeg != chest)
				{
					this.adjacentChestChecked = false;
				}
			}
		}
	}
}