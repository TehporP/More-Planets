/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Kepler22bEntitySpike extends EntityMob implements IEntityBreathable
{
	public Kepler22bEntitySpike(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.7F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.silverfish.kill";
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.silverfish.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId()
	{
		return 0;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				byte b0 = 0;

				if (this.worldObj.difficultySetting > 0)
				{
					if (this.worldObj.difficultySetting == 1)
					{
						b0 = 7;
					}
					else if (this.worldObj.difficultySetting == 2)
					{
						b0 = 13;
					}
					else if (this.worldObj.difficultySetting == 3)
					{
						b0 = 18;
					}
				}

				if (b0 > 0)
				{
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 30, 0));
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
