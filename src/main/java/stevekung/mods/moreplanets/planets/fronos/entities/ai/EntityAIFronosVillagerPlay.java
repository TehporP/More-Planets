/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIFronosVillagerPlay extends EntityAIBase
{
	private EntityFronosVillager villagerObj;
	private EntityLivingBase targetVillager;
	private double speed;
	private int playTime;

	public EntityAIFronosVillagerPlay(EntityFronosVillager p_i1646_1_, double speedIn)
	{
		this.villagerObj = p_i1646_1_;
		this.speed = speedIn;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.villagerObj.getGrowingAge() >= 0)
		{
			return false;
		}
		else if (this.villagerObj.getRNG().nextInt(400) != 0)
		{
			return false;
		}
		else
		{
			List list = this.villagerObj.worldObj.getEntitiesWithinAABB(EntityFronosVillager.class, this.villagerObj.getEntityBoundingBox().expand(6.0D, 3.0D, 6.0D));
			double d0 = Double.MAX_VALUE;
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityFronosVillager EntityFronosVillager = (EntityFronosVillager)iterator.next();

				if (EntityFronosVillager != this.villagerObj && !EntityFronosVillager.isPlaying() && EntityFronosVillager.getGrowingAge() < 0)
				{
					double d1 = EntityFronosVillager.getDistanceSqToEntity(this.villagerObj);

					if (d1 <= d0)
					{
						d0 = d1;
						this.targetVillager = EntityFronosVillager;
					}
				}
			}

			if (this.targetVillager == null)
			{
				Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);

				if (vec3 == null)
				{
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean continueExecuting()
	{
		return this.playTime > 0;
	}

	@Override
	public void startExecuting()
	{
		if (this.targetVillager != null)
		{
			this.villagerObj.setPlaying(true);
		}

		this.playTime = 1000;
	}

	@Override
	public void resetTask()
	{
		this.villagerObj.setPlaying(false);
		this.targetVillager = null;
	}

	@Override
	public void updateTask()
	{
		--this.playTime;

		if (this.targetVillager != null)
		{
			if (this.villagerObj.getDistanceSqToEntity(this.targetVillager) > 4.0D)
			{
				this.villagerObj.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.speed);
			}
		}
		else if (this.villagerObj.getNavigator().noPath())
		{
			Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);

			if (vec3 == null)
			{
				return;
			}

			this.villagerObj.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, this.speed);
		}
	}
}