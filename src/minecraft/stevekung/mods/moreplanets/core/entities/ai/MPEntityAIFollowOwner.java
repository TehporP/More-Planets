/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import stevekung.mods.moreplanets.core.entities.MPEntityTameable;

public class MPEntityAIFollowOwner extends EntityAIBase
{
	private MPEntityTameable thePet;
	private EntityLivingBase theOwner;
	private PathNavigate petPathfinder;
	float maxDist;
	float minDist;
	private boolean field_75344_i;

	public MPEntityAIFollowOwner(MPEntityTameable par1EntityTameable, double par2, float par4, float par5)
	{
		this.thePet = par1EntityTameable;
		this.petPathfinder = par1EntityTameable.getNavigator();
		this.minDist = par4;
		this.maxDist = par5;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase entitylivingbase = this.thePet.func_130012_q();

		if (entitylivingbase == null)
		{
			return false;
		}
		else if (this.thePet.getDistanceSqToEntity(entitylivingbase) < this.minDist * this.minDist)
		{
			return false;
		}
		this.theOwner = entitylivingbase;
		return true;
	}

	@Override
	public boolean continueExecuting()
	{
		return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > this.maxDist * this.maxDist;
	}

	@Override
	public void startExecuting()
	{
		this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
		this.thePet.getNavigator().setAvoidsWater(false);
	}

	@Override
	public void resetTask()
	{
		this.theOwner = null;
		this.petPathfinder.clearPathEntity();
		this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
	}
}