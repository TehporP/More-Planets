/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityLaser;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityPoisonArrow;
import stevekung.mods.moreplanets.nibiru.entities.projectile.NibiruEntityGravityInfectionDart;
import stevekung.mods.moreplanets.nibiru.entities.projectile.NibiruEntityInfectionDart;

public class MPDamageSource extends DamageSource
{
	public static final MPDamageSource tiberium = new MPDamageSource("tiberium");//TODO
	public static final MPDamageSource infectionVine = new MPDamageSource("infectionVine");
	public static final MPDamageSource purpleSpike = new MPDamageSource("purpleSpike");

	public MPDamageSource(String damageType)
	{
		super(damageType);
	}

	public static DamageSource causeLaserDamage(DionaEntityLaser laser, Entity par1Entity)
	{
		return new EntityDamageSourceIndirect("laser", laser, par1Entity).setProjectile();
	}

	public static DamageSource causePoisonArrowDamage(FronosEntityPoisonArrow par0EntityArrow, Entity par1Entity)
	{
		return new EntityDamageSourceIndirect("poisonArrow", par0EntityArrow, par1Entity).setProjectile();
	}

	public static DamageSource causeGravityInfectionDartDamage(NibiruEntityGravityInfectionDart par0EntityArrow, Entity par1Entity)
	{
		return new EntityDamageSourceIndirect("infectionDart", par0EntityArrow, par1Entity).setProjectile();
	}

	public static DamageSource causeInfectionDartDamage(NibiruEntityInfectionDart par0EntityArrow, Entity par1Entity)
	{
		return new EntityDamageSourceIndirect("infectionDart", par0EntityArrow, par1Entity).setProjectile();
	}
}