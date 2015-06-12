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

public class MPEntityAIOwnerHurtByTarget extends EntityAITarget
{
	public MPEntityTameable theDefendingTameable;
	public EntityLivingBase theOwnerAttacker;
	private int field_142051_e;

	public MPEntityAIOwnerHurtByTarget(MPEntityTameable par1EntityTameable)
	{
		super(par1EntityTameable, false);
		this.theDefendingTameable = par1EntityTameable;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (!this.theDefendingTameable.isTamed())
		{
			return false;
		}
		else
		{
			EntityLivingBase entitylivingbase = this.theDefendingTameable.func_130012_q();

			if (entitylivingbase == null)
			{
				return false;
			}
			else
			{
				this.theOwnerAttacker = entitylivingbase.getAITarget();
				int i = entitylivingbase.func_142015_aE();
				return i != this.field_142051_e && this.isSuitableTarget(this.theOwnerAttacker, false) && this.theDefendingTameable.func_142018_a(this.theOwnerAttacker, entitylivingbase);
			}
		}
	}

	@Override
	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.theOwnerAttacker);
		EntityLivingBase entitylivingbase = this.theDefendingTameable.func_130012_q();

		if (entitylivingbase != null)
		{
			this.field_142051_e = entitylivingbase.func_142015_aE();
		}
		super.startExecuting();
	}
}