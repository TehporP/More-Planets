/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;

public class EntityOrangeCreamBall extends EntityThrowable
{
	public EntityOrangeCreamBall(World world)
	{
		super(world);
	}

	public EntityOrangeCreamBall(World world, EntityLivingBase living)
	{
		super(world, living);
	}

	@Override
	protected void onImpact(MovingObjectPosition moving)
	{
		if (moving.entityHit != null)
		{
			moving.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);

			if (moving.entityHit instanceof EntityLivingBase)
			{
				((EntityLivingBase)moving.entityHit).addPotionEffect(new PotionEffect(Potion.invisibility.id, 120, 0));
				((EntityLivingBase)moving.entityHit).addPotionEffect(new PotionEffect(Potion.regeneration.id, 250, 2));
			}
		}

		for (int i = 0; i < 8; ++i)
		{
			MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.ORANGE_CREAM_BALL, this.posX, this.posY, this.posZ);
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}