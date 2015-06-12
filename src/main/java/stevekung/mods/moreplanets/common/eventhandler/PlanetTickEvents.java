/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.eventhandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public abstract class PlanetTickEvents extends LivingEvent
{
	public WorldProvider provider;

	public PlanetTickEvents(EntityLivingBase entity)
	{
		super(entity);
		this.provider = entity.worldObj.provider;
	}

	@Cancelable
	public static class Pre extends PlanetTickEvents
	{
		public Pre(EntityLivingBase entity)
		{
			super(entity);
		}
	}

	public static class Post extends PlanetTickEvents
	{
		public Post(EntityLivingBase entity)
		{
			super(entity);
		}
	}
}