/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityOwnable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MPEntityTameable extends EntityAnimal implements EntityOwnable
{
	public MPEntityTameable(World par1World)
	{
		super(par1World);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(17, "");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		if (this.getOwnerName() == null)
		{
			par1NBTTagCompound.setString("Owner", "");
		}
		par1NBTTagCompound.setString("Owner", this.getOwnerName());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		String s = par1NBTTagCompound.getString("Owner");

		if (s.length() > 0)
		{
			this.setOwner(s);
			this.setTamed(true);
		}
	}

	protected void playTameEffect(boolean par1)
	{
		String s = "heart";

		if (!par1)
		{
			s = "smoke";
		}

		for (int i = 0; i < 7; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(s, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1)
	{
		if (par1 == 7)
		{
			this.playTameEffect(true);
		}
		else if (par1 == 6)
		{
			this.playTameEffect(false);
		}
		super.handleHealthUpdate(par1);
	}

	public boolean isTamed()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 4) != 0;
	}

	public void setTamed(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 4)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -5)));
		}
	}

	@Override
	public String getOwnerName()
	{
		return this.dataWatcher.getWatchableObjectString(17);
	}

	public void setOwner(String par1Str)
	{
		this.dataWatcher.updateObject(17, par1Str);
	}

	public EntityLivingBase func_130012_q()
	{
		return this.worldObj.getPlayerEntityByName(this.getOwnerName());
	}

	public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase)
	{
		return true;
	}

	@Override
	public Team getTeam()
	{
		if (this.isTamed())
		{
			EntityLivingBase entitylivingbase = this.func_130012_q();

			if (entitylivingbase != null)
			{
				return entitylivingbase.getTeam();
			}
		}
		return super.getTeam();
	}

	@Override
	public boolean isOnSameTeam(EntityLivingBase par1EntityLivingBase)
	{
		if (this.isTamed())
		{
			EntityLivingBase entitylivingbase1 = this.func_130012_q();

			if (par1EntityLivingBase == entitylivingbase1)
			{
				return true;
			}

			if (entitylivingbase1 != null)
			{
				return entitylivingbase1.isOnSameTeam(par1EntityLivingBase);
			}
		}
		return super.isOnSameTeam(par1EntityLivingBase);
	}

	@Override
	public Entity getOwner()
	{
		return this.func_130012_q();
	}
}