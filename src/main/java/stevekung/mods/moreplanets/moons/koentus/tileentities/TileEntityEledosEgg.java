/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEledosEgg extends TileEntity implements IUpdatePlayerListBox
{
	public int timeToHatch = -1;

	@Override
	public void update()
	{
		super.validate();

		if (!this.worldObj.isRemote)
		{
			if (this.timeToHatch > 0)
			{
				this.timeToHatch--;
			}
			/*else if (this.timeToHatch == 0) TODO
			{
				EntityEledos eledos = new EntityEledos(this.worldObj);
				eledos.setPosition(this.pos.getX() + 0.5, this.pos.getY() + 1.0, this.pos.getZ() + 0.5);

				if (!this.worldObj.isRemote)
				{
					this.worldObj.spawnEntityInWorld(eledos);
				}
				eledos.setPathToEntity((PathEntity) null);
				eledos.setAttackTarget((EntityLivingBase) null);
				this.worldObj.setBlockToAir(this.pos);
			}*/
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.timeToHatch = nbt.getInteger("TimeToHatch");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("TimeToHatch", this.timeToHatch);
	}
}