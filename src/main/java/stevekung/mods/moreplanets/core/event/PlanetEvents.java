/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.Cancelable;

public abstract class PlanetEvents extends LivingEvent
{
	public WorldProvider provider;

	public PlanetEvents(EntityLivingBase entity)
	{
		super(entity);
		this.provider = entity.worldObj.provider;
	}

	@Cancelable
	public static class Pre extends PlanetEvents
	{
		public Pre(EntityLivingBase entity)
		{
			super(entity);
		}
	}

	public static class Post extends PlanetEvents
	{
		public Post(EntityLivingBase entity)
		{
			super(entity);
		}
	}
}