/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.common.eventhandler;
//
//import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
//import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
//import micdoodle8.mods.galacticraft.planets.mars.client.SkyProviderMars;
//import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.WorldClient;
//import net.minecraftforge.fml.client.FMLClientHandler;
//import net.minecraftforge.fml.common.Loader;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.moons.deimos.dimension.WorldProviderDeimos;
//import stevekung.mods.moreplanets.moons.deimos.dimension.sky.SkyProviderDeimos;
//import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
//import stevekung.mods.moreplanets.moons.koentus.dimension.sky.SkyProviderKoentus;
//import stevekung.mods.moreplanets.moons.phobos.dimension.WorldProviderPhobos;
//import stevekung.mods.moreplanets.moons.phobos.dimension.sky.SkyProviderPhobos;
//import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
//import stevekung.mods.moreplanets.planets.diona.dimension.sky.SkyProviderDiona;
//import stevekung.mods.moreplanets.planets.fronos.dimension.WorldProviderFronos;
//import stevekung.mods.moreplanets.planets.fronos.dimension.sky.SkyProviderFronos;
//import stevekung.mods.moreplanets.planets.kapteynb.dimension.WorldProviderKapteynB;
//import stevekung.mods.moreplanets.planets.kapteynb.dimension.sky.SkyProviderKapteynB;
//import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;
//import stevekung.mods.moreplanets.planets.mercury.dimension.sky.SkyProviderMercury;
//import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
//import stevekung.mods.moreplanets.planets.nibiru.dimension.sky.SkyProviderNibiru;
//import stevekung.mods.moreplanets.planets.polongnius.dimension.WorldProviderPolongnius;
//import stevekung.mods.moreplanets.planets.polongnius.dimension.sky.SkyProviderPolongnius;
//import stevekung.mods.moreplanets.planets.siriusb.dimension.WorldProviderSiriusB;
//import stevekung.mods.moreplanets.planets.siriusb.dimension.sky.SkyProviderSiriusB;
//
//public class SkyProviderHandlerMP
//{
//	@SubscribeEvent
//	@SideOnly(Side.CLIENT)
//	public void onSkyRendererTick(ClientTickEvent event)
//	{
//		Minecraft minecraft = FMLClientHandler.instance().getClient();
//		WorldClient world = minecraft.theWorld;
//
//		if (world != null)
//		{
//			if (Loader.isModLoaded("GalacticraftMars"))
//			{
//				if (world.provider instanceof WorldProviderMars)
//				{
//					if (world.provider.getSkyRenderer() instanceof SkyProviderMars)
//					{
//						world.provider.setSkyRenderer(new SkyProviderMarsMP((IGalacticraftWorldProvider) world.provider));
//					}
//				}
//			}
//
//			if (world.provider instanceof WorldProviderDiona)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderDiona((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderPolongnius)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderPolongnius((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderNibiru)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderNibiru((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderFronos)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderFronos((IGalacticraftWorldProvider) world.provider));
//				}
//			}
//			else if (world.provider instanceof WorldProviderKoentus)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderKoentus((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderKapteynB)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderKapteynB((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderSiriusB)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderSiriusB((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderMercury)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderMercury((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderPhobos)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderPhobos((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//			else if (world.provider instanceof WorldProviderDeimos)
//			{
//				if (world.provider.getSkyRenderer() == null)
//				{
//					world.provider.setSkyRenderer(new SkyProviderDeimos((IGalacticraftWorldProvider) world.provider));
//				}
//				if (world.provider.getCloudRenderer() == null)
//				{
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
//			}
//		}
//	}
//}