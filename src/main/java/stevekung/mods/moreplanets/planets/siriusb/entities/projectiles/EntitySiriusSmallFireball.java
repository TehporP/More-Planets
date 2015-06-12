/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class EntitySiriusSmallFireball extends EntityFireball
{
	public EntitySiriusSmallFireball(World world)
	{
		super(world);
		this.setSize(0.3125F, 0.3125F);
		this.setCanExplode(false);
	}

	public EntitySiriusSmallFireball(World world, EntityLivingBase living, double x, double y, double z)
	{
		super(world, living, x, y, z);
		this.setSize(0.3125F, 0.3125F);
	}

	public EntitySiriusSmallFireball(World world, double x, double y, double z, double moX, double moY, double moZ)
	{
		super(world, x, y, z, moX, moY, moZ);
		this.setSize(0.3125F, 0.3125F);
	}

	@Override
	public void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 1));
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObject)
	{
		if (!this.worldObj.isRemote)
		{
			boolean flag;

			if (movingObject.entityHit != null)
			{
				flag = movingObject.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), this.getCanExplode() ? 6.0F : 5.0F);

				if (flag)
				{
					this.func_174815_a(this.shootingEntity, movingObject.entityHit);

					if (!movingObject.entityHit.isImmuneToFire())
					{
						movingObject.entityHit.setFire(5);
					}
				}
			}
			else
			{
				flag = true;

				if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving)
				{
					flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				}

				if (flag)
				{
					BlockPos blockpos = movingObject.getBlockPos().offset(movingObject.sideHit);

					if (this.worldObj.isAirBlock(blockpos))
					{
						this.worldObj.setBlockState(blockpos, SiriusBBlocks.sirius_fire.getDefaultState());
					}
					if (this.getCanExplode())
					{
						this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 4, true);
					}
				}
			}
			this.setDead();
		}
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			if (this.getCanExplode() == true)
			{
				this.setBeenAttacked();

				if (source.getEntity() != null)
				{
					Vec3 vec3 = source.getEntity().getLookVec();

					if (vec3 != null)
					{
						this.motionX = vec3.xCoord;
						this.motionY = vec3.yCoord;
						this.motionZ = vec3.zCoord;
						this.accelerationX = this.motionX * 0.1D;
						this.accelerationY = this.motionY * 0.1D;
						this.accelerationZ = this.motionZ * 0.1D;
					}
					if (source.getEntity() instanceof EntityLivingBase)
					{
						this.shootingEntity = (EntityLivingBase)source.getEntity();
					}
				}
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("CanExplode", this.getCanExplode());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		if (nbt.getBoolean("CanExplode"))
		{
			this.setCanExplode(true);
		}
	}

	public boolean getCanExplode()
	{
		return this.getDataWatcher().getWatchableObjectByte(16) == 1;
	}

	public void setCanExplode(boolean can)
	{
		this.getDataWatcher().updateObject(16, Byte.valueOf((byte) (can ? 1 : 0)));
	}
}