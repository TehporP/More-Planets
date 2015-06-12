/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;

public class DamageSourceMP extends DamageSource
{
	public static DamageSourceMP chemical = (DamageSourceMP) new DamageSourceMP("chemical").setDamageBypassesArmor();
	public static DamageSourceMP infected_vine = (DamageSourceMP) new DamageSourceMP("infected_vine").setDamageBypassesArmor().setDifficultyScaled();
	public static DamageSourceMP purple_spike = (DamageSourceMP)new DamageSourceMP("purple_spike").setDamageBypassesArmor();
	public static DamageSourceMP infected_gas = (DamageSourceMP)new DamageSourceMP("infected_gas").setDamageBypassesArmor().setDifficultyScaled();
	public static DamageSourceMP icy_poison = (DamageSourceMP)new DamageSourceMP("icy_poison").setDamageBypassesArmor().setDifficultyScaled();

	public DamageSourceMP(String damageType)
	{
		super(damageType);
	}

	public static DamageSource causeLaserDamage(EntityLaserMP laser, Entity entity)
	{
		return new EntityDamageSourceIndirect("laser", laser, entity).setProjectile();
	}

	public static DamageSource causePoisonArrowDamage(EntityPoisonArrow arrow, Entity entity)
	{
		return new EntityDamageSourceIndirect("poison_arrow", arrow, entity).setProjectile();
	}
}