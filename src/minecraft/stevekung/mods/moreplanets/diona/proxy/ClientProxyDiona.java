/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.proxy;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.core.client.GCCoreCloudRenderer;
import micdoodle8.mods.galacticraft.core.client.sounds.GCCoreSoundUpdaterSpaceship;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import stevekung.mods.moreplanets.diona.core.planets.DionaSkyProvider;
import stevekung.mods.moreplanets.diona.dimension.DionaWorldProvider;
import stevekung.mods.moreplanets.diona.entities.DionaEntityRocketT3;
import stevekung.mods.moreplanets.diona.entities.DionaEntityRocketT3NoFlag;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyDiona extends CommonProxyDiona
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	}

	public static class TickHandlerClient implements ITickHandler
	{
		@Override
		public void tickStart(EnumSet<TickType> type, Object... tickData)
		{
			final Minecraft minecraft = FMLClientHandler.instance().getClient();

			final WorldClient world = minecraft.theWorld;

			if (type.equals(EnumSet.of(TickType.CLIENT)))
			{
				if (world != null)
				{
					if (world.provider instanceof DionaWorldProvider)
					{
						if (world.provider.getSkyRenderer() == null)
						{
							world.provider.setSkyRenderer(new DionaSkyProvider());
						}
						if (world.provider.getCloudRenderer() == null)
						{
							world.provider.setCloudRenderer(new GCCoreCloudRenderer());
						}
					}

					for (int i = 0; i < world.loadedEntityList.size(); i++)
					{
						final Entity e = (Entity) world.loadedEntityList.get(i);

						if (e != null)
						{
							if (e instanceof DionaEntityRocketT3)
							{
								DionaEntityRocketT3 eship = (DionaEntityRocketT3) e;

								if (eship.rocketSoundUpdater == null)
								{
									eship.rocketSoundUpdater = new GCCoreSoundUpdaterSpaceship(FMLClientHandler.instance().getClient().sndManager, eship, FMLClientHandler.instance().getClient().thePlayer);
								}
							}
						}
						if (e != null)
						{
							if (e instanceof DionaEntityRocketT3NoFlag)
							{
								DionaEntityRocketT3NoFlag eship = (DionaEntityRocketT3NoFlag) e;

								if (eship.rocketSoundUpdater == null)
								{
									eship.rocketSoundUpdater = new GCCoreSoundUpdaterSpaceship(FMLClientHandler.instance().getClient().sndManager, eship, FMLClientHandler.instance().getClient().thePlayer);
								}
							}
						}
					}
				}
			}
		}

		@Override
		public void tickEnd(EnumSet<TickType> type, Object... tickData)
		{
		}

		@Override
		public String getLabel()
		{
			return "Diona Client";
		}

		@Override
		public EnumSet<TickType> ticks()
		{
			return EnumSet.of(TickType.CLIENT);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}