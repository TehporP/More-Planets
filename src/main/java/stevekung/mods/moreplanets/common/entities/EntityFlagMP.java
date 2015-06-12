/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityFlagMP extends Entity
{
	public boolean indestructable = false;

	public EntityFlagMP(World world)
	{
		super(world);
		this.setSize(0.4F, 2.7F);
		this.ignoreFrustumCheck = true;
	}

	public EntityFlagMP(World world, double x, double y, double z, int facing)
	{
		this(world);
		this.setFacingAngle(facing);
		this.setPosition(x, y, z);
	}

	@Override
	public double getYOffset()
	{
		return 1.5D;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float par2)
	{
		if (!this.worldObj.isRemote && !this.isDead && !this.indestructable)
		{
			boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;

			if (this.isEntityInvulnerable(damageSource))
			{
				return false;
			}

			this.setBeenAttacked();
			this.setDamage(this.getDamage() + par2 * 10.0F);

			if (flag || this.getDamage() > 40.0F)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}
				if (flag)
				{
					this.setDead();
				}
				else
				{
					this.setDead();
					this.entityDropItem(new ItemStack(MPItems.flag, 1, this.getType()), 0.0F);
				}
			}
			return true;
		}
		return true;
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition moving)
	{
		return new ItemStack(MPItems.flag, 1, this.getType());
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Float(0.0F));
		this.dataWatcher.addObject(18, new Integer(-1));
		this.dataWatcher.addObject(19, new Integer(-1));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.setType(nbt.getInteger("Type"));
		this.indestructable = nbt.getBoolean("Indestructable");
		this.setFacingAngle(nbt.getInteger("Angle"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("Type", Integer.valueOf(this.getType()));
		nbt.setBoolean("Indestructable", this.indestructable);
		nbt.setInteger("Angle", this.getFacingAngle());
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		BlockPos pos = this.getPosition().down();
		Block block = this.worldObj.getBlockState(pos).getBlock();

		if (block != null)
		{
			if (block.isAir(this.worldObj, pos))
			{
				this.motionY -= 0.02F;
			}
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if (!this.worldObj.isRemote)
		{
			this.setFacingAngle(this.getFacingAngle() + 3);
		}
		return true;
	}

	public void setDamage(float damage)
	{
		this.dataWatcher.updateObject(17, Float.valueOf(damage));
	}

	public float getDamage()
	{
		return this.dataWatcher.getWatchableObjectFloat(17);
	}

	public void setType(int type)
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(type));
	}

	public int getType()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public void setFacingAngle(int facing)
	{
		this.dataWatcher.updateObject(19, Integer.valueOf(facing));
	}

	public int getFacingAngle()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}
}