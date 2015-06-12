/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;

public class EntityProjectileFronisiumTNT extends EntityFireball
{
	public EntityProjectileFronisiumTNT(World world)
	{
		super(world);
		this.setSize(1.0F, 1.0F);
	}

	public EntityProjectileFronisiumTNT(World world, EntityLivingBase living, double x, double y, double z)
	{
		super(world, living, x, y, z);
		this.setSize(1.0F, 1.0F);
	}

	@SideOnly(Side.CLIENT)
	public EntityProjectileFronisiumTNT(World world, double x, double y, double z, double moX, double moY, double moZ)
	{
		super(world, x, y, z, moX, moY, moZ);
		this.setSize(0.3125F, 0.3125F);
	}

	@Override
	public boolean isBurning()
	{
		return false;
	}

	@Override
	protected void onImpact(MovingObjectPosition moving)
	{
		if (!this.worldObj.isRemote)
		{
			if (moving.entityHit != null && !(moving.entityHit instanceof EntityCreeper))
			{
				moving.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
			}
			this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			this.setDead();
		}
		if (this.rand.nextInt(8) == 0)
		{
			if (!this.worldObj.isRemote)
			{
				byte b0 = 1;

				if (this.rand.nextInt(16) == 0)
				{
					b0 = 4;
				}

				for (int i = 0; i < b0; ++i)
				{
					EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(this.worldObj);
					creeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
					creeper.setAbsorptionAmount(16.0F);

					if (this.rand.nextInt(4) == 0)
					{
						creeper.getDataWatcher().updateObject(17, Byte.valueOf((byte) 1));
					}
					this.worldObj.spawnEntityInWorld(creeper);
				}
			}
		}
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public float getBrightness(float light)
	{
		BlockPos blockpos = new BlockPos(this.posX, 0.0D, this.posZ);

		if (this.worldObj.isBlockLoaded(blockpos))
		{
			double d0 = (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * 0.66D;
			int i = MathHelper.floor_double(this.posY + d0);
			return this.worldObj.getLightBrightness(blockpos.up(i));
		}
		else
		{
			return 0.0F;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float light)
	{
		BlockPos blockpos = new BlockPos(this.posX, 0.0D, this.posZ);

		if (this.worldObj.isBlockLoaded(blockpos))
		{
			double d0 = (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * 0.66D;
			int i = MathHelper.floor_double(this.posY + d0);
			return this.worldObj.getCombinedLight(blockpos.up(i), 0);
		}
		else
		{
			return 0;
		}
	}
}