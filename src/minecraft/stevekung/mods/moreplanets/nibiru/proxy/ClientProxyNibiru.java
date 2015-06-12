/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.proxy;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.core.client.GCCoreCloudRenderer;
import micdoodle8.mods.galacticraft.core.client.sounds.GCCoreSoundUpdaterSpaceship;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import stevekung.mods.moreplanets.nibiru.core.planets.NibiruSkyProvider;
import stevekung.mods.moreplanets.nibiru.dimension.NibiruWorldProvider;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5NoFlag;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyNibiru extends CommonProxyNibiru
{
	public static class TickHandlerClient implements ITickHandler
	{
		@Override
		public String getLabel()
		{
			return "More Planet's : Nibiru Client";
		}

		@Override
		public void tickEnd(EnumSet<TickType> type, Object... tickData)
		{
		}

		@Override
		public EnumSet<TickType> ticks()
		{
			return EnumSet.of(TickType.CLIENT);
		}

		@Override
		public void tickStart(EnumSet<TickType> type, Object... tickData)
		{
			final Minecraft minecraft = FMLClientHandler.instance().getClient();

			final WorldClient world = minecraft.theWorld;

			if (type.equals(EnumSet.of(TickType.CLIENT)))
			{
				if (world != null)
				{
					if (world.provider instanceof NibiruWorldProvider)
					{
						if (world.provider.getSkyRenderer() == null)
						{
							world.provider.setSkyRenderer(new NibiruSkyProvider());
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
							if (e instanceof NibiruEntityRocketT5)
							{
								NibiruEntityRocketT5 eship = (NibiruEntityRocketT5) e;

								if (eship.rocketSoundUpdater == null)
								{
									eship.rocketSoundUpdater = new GCCoreSoundUpdaterSpaceship(FMLClientHandler.instance().getClient().sndManager, eship, FMLClientHandler.instance().getClient().thePlayer);
								}
							}
						}
						if (e != null)
						{
							if (e instanceof NibiruEntityRocketT5NoFlag)
							{
								NibiruEntityRocketT5NoFlag eship = (NibiruEntityRocketT5NoFlag) e;

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
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}