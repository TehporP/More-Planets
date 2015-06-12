/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.proxy;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.planets.FronosSkyProvider;
import stevekung.mods.moreplanets.fronos.dimension.FronosWorldProvider;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyFronos extends CommonProxyFronos
{
	public class ClientPacketHandler implements IPacketHandler
	{
		@Override
		public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p)
		{
		}
	}

	public static class TickHandlerClient implements ITickHandler
	{
		@Override
		public String getLabel()
		{
			return "More Planet's : Fronos Client";
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
					if (world.provider instanceof FronosWorldProvider)
					{
						if (world.provider.getSkyRenderer() == null)
						{
							world.provider.setSkyRenderer(new FronosSkyProvider());
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
		NetworkRegistry.instance().registerChannel(new ClientPacketHandler(), MorePlanetCore.MODID, Side.CLIENT);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}