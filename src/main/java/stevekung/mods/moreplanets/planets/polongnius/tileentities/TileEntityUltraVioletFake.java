/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.tileentities;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;

public class TileEntityUltraVioletFake extends TileEntityAdvanced implements IPacketReceiver
{
	@NetworkedField(targetSide = Side.CLIENT)
	public BlockVec3 mainBlockPosition;

	public void setMainBlock(BlockVec3 mainBlock)
	{
		this.mainBlockPosition = mainBlock;

		if (!this.worldObj.isRemote)
		{
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
	}

	public void onBlockRemoval()
	{
		if (this.mainBlockPosition != null)
		{
			final TileEntity tileEntity = this.worldObj.getTileEntity(this.mainBlockPosition.x, this.mainBlockPosition.y, this.mainBlockPosition.z);

			if (tileEntity instanceof IMultiBlock)
			{
				final IMultiBlock mainBlock = (IMultiBlock) tileEntity;
				mainBlock.onDestroy(this);
			}
		}
	}

	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		if (this.mainBlockPosition != null)
		{
			final TileEntity tileEntity = this.worldObj.getTileEntity(this.mainBlockPosition.x, this.mainBlockPosition.y, this.mainBlockPosition.z);

			if (tileEntity instanceof IMultiBlock)
			{
				return ((IMultiBlock) tileEntity).onActivated(par5EntityPlayer);
			}
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.mainBlockPosition = new BlockVec3(nbt.getCompoundTag("mainBlockPosition"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		if (this.mainBlockPosition != null)
		{
			nbt.setTag("mainBlockPosition", this.mainBlockPosition.writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public double getPacketRange()
	{
		return 30.0D;
	}

	@Override
	public int getPacketCooldown()
	{
		return 50;
	}

	@Override
	public boolean isNetworkedTile()
	{
		return this.mainBlockPosition != null;
	}
}