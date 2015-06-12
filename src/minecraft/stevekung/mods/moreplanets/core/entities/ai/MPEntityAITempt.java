/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MPEntityAITempt extends EntityAIBase
{
	private EntityCreature temptedEntity;
	private double field_75282_b;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double field_75278_f;
	private double field_75279_g;

	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;
	private ItemStack breedingFood;
	private boolean scaredByPlayerMovement;
	private boolean field_75286_m;

	public MPEntityAITempt(EntityCreature par1EntityCreature, double par2, ItemStack par4, boolean par5)
	{
		this.temptedEntity = par1EntityCreature;
		this.field_75282_b = par2;
		this.breedingFood = par4;
		this.scaredByPlayerMovement = par5;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.delayTemptCounter > 0)
		{
			--this.delayTemptCounter;
			return false;
		}
		else
		{
			this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

			if (this.temptingPlayer == null)
			{
				return false;
			}
			else
			{
				ItemStack itemstack = this.temptingPlayer.getCurrentEquippedItem();
				return itemstack == null ? false : itemstack.getItem() == this.breedingFood.getItem();
			}
		}
	}

	@Override
	public boolean continueExecuting()
	{
		if (this.scaredByPlayerMovement)
		{
			if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D)
			{
				if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
				{
					return false;
				}

				if (Math.abs(this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0D)
				{
					return false;
				}
			}
			else
			{
				this.targetX = this.temptingPlayer.posX;
				this.targetY = this.temptingPlayer.posY;
				this.targetZ = this.temptingPlayer.posZ;
			}
			this.field_75278_f = this.temptingPlayer.rotationPitch;
			this.field_75279_g = this.temptingPlayer.rotationYaw;
		}
		return this.shouldExecute();
	}

	@Override
	public void startExecuting()
	{
		this.targetX = this.temptingPlayer.posX;
		this.targetY = this.temptingPlayer.posY;
		this.targetZ = this.temptingPlayer.posZ;
		this.isRunning = true;
		this.field_75286_m = this.temptedEntity.getNavigator().getAvoidsWater();
		this.temptedEntity.getNavigator().setAvoidsWater(false);
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.delayTemptCounter = 100;
		this.isRunning = false;
		this.temptedEntity.getNavigator().setAvoidsWater(this.field_75286_m);
	}

	@Override
	public void updateTask()
	{
		this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());

		if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
		{
			this.temptedEntity.getNavigator().clearPathEntity();
		}
		this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.field_75282_b);
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}