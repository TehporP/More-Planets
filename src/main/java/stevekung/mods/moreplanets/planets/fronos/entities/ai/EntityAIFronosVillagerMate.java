/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIFronosVillagerMate extends EntityAIBase
{
	private EntityFronosVillager villagerObj;
	private EntityFronosVillager mate;
	private World worldObj;
	private int matingTimeout;
	Village villageObj;

	public EntityAIFronosVillagerMate(EntityFronosVillager p_i1634_1_)
	{
		this.villagerObj = p_i1634_1_;
		this.worldObj = p_i1634_1_.worldObj;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.villagerObj.getGrowingAge() != 0)
		{
			return false;
		}
		else if (this.villagerObj.getRNG().nextInt(500) != 0)
		{
			return false;
		}
		else
		{
			this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(new BlockPos(this.villagerObj), 0);

			if (this.villageObj == null)
			{
				return false;
			}
			else if (this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.func_175550_n(true))
			{
				Entity entity = this.worldObj.findNearestEntityWithinAABB(EntityFronosVillager.class, this.villagerObj.getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D), this.villagerObj);

				if (entity == null)
				{
					return false;
				}
				else
				{
					this.mate = (EntityFronosVillager)entity;
					return this.mate.getGrowingAge() == 0 && this.mate.func_175550_n(true);
				}
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public void startExecuting()
	{
		this.matingTimeout = 300;
		this.villagerObj.setMating(true);
	}

	@Override
	public void resetTask()
	{
		this.villageObj = null;
		this.mate = null;
		this.villagerObj.setMating(false);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0 && this.villagerObj.func_175550_n(false);
	}

	@Override
	public void updateTask()
	{
		--this.matingTimeout;
		this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);

		if (this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D)
		{
			this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25D);
		}
		else if (this.matingTimeout == 0 && this.mate.isMating())
		{
			this.giveBirth();
		}

		if (this.villagerObj.getRNG().nextInt(35) == 0)
		{
			this.worldObj.setEntityState(this.villagerObj, (byte)12);
		}
	}

	private boolean checkSufficientDoorsPresentForNewVillager()
	{
		if (!this.villageObj.isMatingSeason())
		{
			return false;
		}
		else
		{
			int i = (int)(this.villageObj.getNumVillageDoors() * 0.35D);
			return this.villageObj.getNumVillagers() < i;
		}
	}

	private void giveBirth()
	{
		EntityFronosVillager EntityFronosVillager = this.villagerObj.func_180488_b(this.mate);
		this.mate.setGrowingAge(6000);
		this.villagerObj.setGrowingAge(6000);
		EntityFronosVillager.setGrowingAge(-24000);
		EntityFronosVillager.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
		this.worldObj.spawnEntityInWorld(EntityFronosVillager);
		this.worldObj.setEntityState(EntityFronosVillager, (byte)12);
	}
}