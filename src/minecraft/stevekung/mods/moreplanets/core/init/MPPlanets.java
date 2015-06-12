/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.planets.MPPlanetSirius;
import stevekung.mods.moreplanets.core.planets.MPPlanetSiriusB;
import stevekung.mods.moreplanets.diona.core.planets.DionaPlanet;
import stevekung.mods.moreplanets.diona.dimension.DionaTeleportType;
import stevekung.mods.moreplanets.diona.dimension.DionaWorldProvider;
import stevekung.mods.moreplanets.fronos.core.planets.FronosPlanet;
import stevekung.mods.moreplanets.fronos.dimension.FronosTeleportType;
import stevekung.mods.moreplanets.fronos.dimension.FronosWorldProvider;
import stevekung.mods.moreplanets.kapteynb.core.planets.KapteynBPlanet;
import stevekung.mods.moreplanets.kapteynb.dimension.KapteynBTeleportType;
import stevekung.mods.moreplanets.kapteynb.dimension.KapteynBWorldProvider;
import stevekung.mods.moreplanets.koentus.core.planets.KoentusPlanet;
import stevekung.mods.moreplanets.koentus.dimension.KoentusTeleportType;
import stevekung.mods.moreplanets.koentus.dimension.KoentusWorldProvider;
import stevekung.mods.moreplanets.nibiru.core.planets.NibiruPlanet;
import stevekung.mods.moreplanets.nibiru.dimension.NibiruTeleportType;
import stevekung.mods.moreplanets.nibiru.dimension.NibiruWorldProvider;
import stevekung.mods.moreplanets.polongnius.core.planets.PolongniusPlanet;
import stevekung.mods.moreplanets.polongnius.dimension.PolongniusTeleportType;
import stevekung.mods.moreplanets.polongnius.dimension.PolongniusWorldProvider;

public class MPPlanets
{
	public static void init()
	{
		registerPlanets();
	}

	private static void registerPlanets()
	{
		MorePlanetCore.sirius = new MPPlanetSirius();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.sirius);
		GalacticraftRegistry.registerGalaxy(MorePlanetCore.galaxySirius);

		MorePlanetCore.siriusB = new MPPlanetSiriusB();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.siriusB);
		GalacticraftRegistry.registerGalaxy(MorePlanetCore.galaxySirius);

		MorePlanetCore.diona = new DionaPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.diona);
		GalacticraftRegistry.registerTeleportType(DionaWorldProvider.class, new DionaTeleportType());
		GalacticraftRegistry.registerRocketGui(DionaWorldProvider.class, new ResourceLocation("diona:textures/gui/dionaRocketGui.png"));

		MorePlanetCore.polongnius = new PolongniusPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.polongnius);
		GalacticraftRegistry.registerTeleportType(PolongniusWorldProvider.class, new PolongniusTeleportType());
		GalacticraftRegistry.registerRocketGui(PolongniusWorldProvider.class, new ResourceLocation("polongnius:textures/gui/polongniusRocketGui.png"));

		MorePlanetCore.nibiru = new NibiruPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.nibiru);
		GalacticraftRegistry.registerTeleportType(NibiruWorldProvider.class, new NibiruTeleportType());
		GalacticraftRegistry.registerRocketGui(NibiruWorldProvider.class, new ResourceLocation("nibiru:textures/gui/nibiruRocketGui.png"));

		MorePlanetCore.koentus = new KoentusPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.koentus);
		GalacticraftRegistry.registerTeleportType(KoentusWorldProvider.class, new KoentusTeleportType());
		GalacticraftRegistry.registerRocketGui(KoentusWorldProvider.class, new ResourceLocation("koentus:textures/gui/koentusRocketGui.png"));

		MorePlanetCore.fronos = new FronosPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.fronos);
		GalacticraftRegistry.registerTeleportType(FronosWorldProvider.class, new FronosTeleportType());
		GalacticraftRegistry.registerRocketGui(FronosWorldProvider.class, new ResourceLocation("fronos:textures/gui/fronosRocketGui.png"));

		MorePlanetCore.kapteynB = new KapteynBPlanet();
		GalacticraftRegistry.registerCelestialBody(MorePlanetCore.kapteynB);
		GalacticraftRegistry.registerTeleportType(KapteynBWorldProvider.class, new KapteynBTeleportType());
		GalacticraftRegistry.registerRocketGui(KapteynBWorldProvider.class, new ResourceLocation("kapteynb:textures/gui/kapteynBRocketGui.png"));
	}
}