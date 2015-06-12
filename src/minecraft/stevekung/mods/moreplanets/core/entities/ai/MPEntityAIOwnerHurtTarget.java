/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import stevekung.mods.moreplanets.core.entities.MPEntityTameable;

public class MPEntityAIOwnerHurtTarget extends EntityAITarget
{
	public MPEntityTameable theEntityTameable;
	public EntityLivingBase theTarget;
	private int field_142050_e;

	public MPEntityAIOwnerHurtTarget(MPEntityTameable par1EntityTameable)
	{
		super(par1EntityTameable, false);
		this.theEntityTameable = par1EntityTameable;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (!this.theEntityTameable.isTamed())
		{
			return false;
		}
		else
		{
			EntityLivingBase entitylivingbase = this.theEntityTameable.func_130012_q();

			if (entitylivingbase == null)
			{
				return false;
			}
			else
			{
				this.theTarget = entitylivingbase.getLastAttacker();
				int i = entitylivingbase.getLastAttackerTime();
				return i != this.field_142050_e && this.isSuitableTarget(this.theTarget, false) && this.theEntityTameable.func_142018_a(this.theTarget, entitylivingbase);
			}
		}
	}

	@Override
	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.theTarget);
		EntityLivingBase entitylivingbase = this.theEntityTameable.func_130012_q();

		if (entitylivingbase != null)
		{
			this.field_142050_e = entitylivingbase.getLastAttackerTime();
		}
		super.startExecuting();
	}
}