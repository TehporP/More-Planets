/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.entities.IBreathableInfectedGas;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.init.MPPotions;

public class EntityInfectedZombie extends EntityZombie implements /*IEntityBreathable,*/ IBreathableInfectedGas
{
	public EntityInfectedZombie(World world)
	{
		super(world);
	}

	/*@Override
	public boolean canBreath()
	{
		return true;
	}*/

	@Override
	public void setDead()
	{
		if (!this.worldObj.isRemote && !this.isChild())
		{
			if (this.rand.nextInt(4) == 0)
			{
				EntityGiantWorm worm = new EntityGiantWorm(this.worldObj);
				worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(worm);
			}
			else
			{
				EntityInfectedWorm worm = new EntityInfectedWorm(this.worldObj);
				worm.setLocationAndAngles(this.posX, this.posY + this.rand.nextInt(2), this.posZ, 360.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(worm);
			}
		}
		super.setDead();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (super.attackEntityAsMob(entity))
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 20, 0));
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1010);
	}

	public IAttribute getReinforcementsAttribute()
	{
		return EntityZombie.reinforcementChance;
	}

	@Override
	public boolean canBreathInGas()
	{
		return true;
	}
}