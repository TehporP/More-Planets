/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.core.init;
//
//import net.minecraft.util.ResourceLocation;
//import stevekung.mods.moreplanets.common.config.ConfigManagerMP;
//import stevekung.mods.moreplanets.core.MorePlanetsCore;
//
//public class MPPlanets
//{
//	public static void init()
//	{
//		MPPlanets.initSolarSystems();
//		MPPlanets.initPlanets();
//		MPPlanets.initMoons();
//		MPPlanets.registerGalaxy();
//		MPPlanets.registerTeleportType();
//		MPPlanets.registerRocketGui();
//	}
//
//	private static void initSolarSystems()
//	{
//		MorePlanetsCore.siriusSolarSystem = new SolarSystem("sirius", "milkyWay").setMapPosition(new Vector3(-32.0F, -32.0F));
//		MorePlanetsCore.sirius = new Star("sirius").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.sirius.setTierRequired(-1);
//		MorePlanetsCore.sirius.setBodyIcon(new ResourceLocation("mpcore:textures/gui/celestialbodies/sirius.png"));
//		MorePlanetsCore.siriusSolarSystem.setMainStar(MorePlanetsCore.sirius);
//
//		MorePlanetsCore.kapteynBSolarSystem = new SolarSystem("kapteyn", "milkyWay").setMapPosition(new Vector3(85F, -60F));
//		MorePlanetsCore.kapteyn = new Star("kapteyn").setParentSolarSystem(MorePlanetsCore.kapteynBSolarSystem);
//		MorePlanetsCore.kapteyn.setTierRequired(-1);
//		MorePlanetsCore.kapteyn.setBodyIcon(new ResourceLocation("kapteynb:textures/gui/celestialbodies/kapteyn_star.png"));
//		MorePlanetsCore.kapteynBSolarSystem.setMainStar(MorePlanetsCore.kapteyn);
//	}
//
//	private static void initMoons()
//	{
//		MorePlanetsCore.koentus = new Planet("koentus").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		//MorePlanetsCore.koentus.setRingColorRGB(0.53F, 2.02F, 1.42F);
//		MorePlanetsCore.koentus.setPhaseShift(2.436F);
//		MorePlanetsCore.koentus.setRelativeDistanceFromCenter(new ScalableDistance(4.75F, 4.75F));
//		MorePlanetsCore.koentus.setRelativeOrbitTime(1 / 0.01F);
//		MorePlanetsCore.koentus.setTierRequired(4);
//		MorePlanetsCore.koentus.setRelativeSize(0.3867F);
//		MorePlanetsCore.koentus.setBodyIcon(new ResourceLocation("koentus:textures/gui/celestialbodies/koentus.png"));
//		MorePlanetsCore.koentus.setDimensionInfo(ConfigManagerMP.idDimensionKoentus, WorldProviderKoentus.class);
//		MorePlanetsCore.koentus.atmosphereComponent(IAtmosphericGas.HYDROGEN).atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.HELIUM);
//
//		MorePlanetsCore.phobos = new Moon("phobos").setParentPlanet(MarsModule.planetMars);
//		MorePlanetsCore.phobos.setRelativeDistanceFromCenter(new ScalableDistance(10F, 10F));
//		MorePlanetsCore.phobos.setRelativeOrbitTime(1 / 0.01F);
//		MorePlanetsCore.phobos.setTierRequired(2);
//		MorePlanetsCore.phobos.setRelativeSize(0.3867F);
//		MorePlanetsCore.phobos.setBodyIcon(new ResourceLocation("phobos:textures/gui/celestialbodies/phobos.png"));
//		MorePlanetsCore.phobos.setDimensionInfo(ConfigManagerMP.idDimensionPhobos, WorldProviderPhobos.class);
//
//		MorePlanetsCore.deimos = new Moon("deimos").setParentPlanet(MarsModule.planetMars);
//		MorePlanetsCore.deimos.setRelativeDistanceFromCenter(new ScalableDistance(15F, 15F));
//		MorePlanetsCore.deimos.setRelativeOrbitTime(1 / 0.01F);
//		MorePlanetsCore.deimos.setTierRequired(2);
//		MorePlanetsCore.deimos.setRelativeSize(0.3867F);
//		MorePlanetsCore.deimos.setBodyIcon(new ResourceLocation("deimos:textures/gui/celestialbodies/deimos.png"));
//		MorePlanetsCore.deimos.setDimensionInfo(ConfigManagerMP.idDimensionDeimos, WorldProviderDeimos.class);
//	}
//
//	private static void initPlanets()
//	{
//		MorePlanetsCore.diona = new Planet("diona").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.diona.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.diona.setPhaseShift(8.7446F);
//		MorePlanetsCore.diona.setRelativeDistanceFromCenter(new ScalableDistance(5.0F, 5.0F));
//		MorePlanetsCore.diona.setRelativeOrbitTime(13.7685F);
//		MorePlanetsCore.diona.setTierRequired(4);
//		MorePlanetsCore.diona.setRelativeSize(0.876F);
//		MorePlanetsCore.diona.setBodyIcon(new ResourceLocation("diona:textures/gui/celestialbodies/diona.png"));
//		MorePlanetsCore.diona.setDimensionInfo(ConfigManagerMP.idDimensionDiona, WorldProviderDiona.class);
//		MorePlanetsCore.diona.atmosphereComponent(IAtmosphericGas.CO2);
//
//		MorePlanetsCore.polongnius = new Planet("polongnius").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.polongnius.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.polongnius.setPhaseShift(12.2478F);
//		MorePlanetsCore.polongnius.setRelativeDistanceFromCenter(new ScalableDistance(4.25F, 4.25F));
//		MorePlanetsCore.polongnius.setRelativeOrbitTime(76.4168F);
//		MorePlanetsCore.polongnius.setTierRequired(5);
//		MorePlanetsCore.polongnius.setRelativeSize(1.465F);
//		MorePlanetsCore.polongnius.setBodyIcon(new ResourceLocation("polongnius:textures/gui/celestialbodies/polongnius.png"));
//		MorePlanetsCore.polongnius.setDimensionInfo(ConfigManagerMP.idDimensionPolongnius, WorldProviderPolongnius.class);
//		MorePlanetsCore.polongnius.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.ARGON);
//
//		MorePlanetsCore.nibiru = new Planet("nibiru").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.nibiru.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.nibiru.setPhaseShift(52.4341F);
//		MorePlanetsCore.nibiru.setRelativeDistanceFromCenter(new ScalableDistance(3.75F, 3.75F));
//		MorePlanetsCore.nibiru.setRelativeOrbitTime(71.6582F);
//		MorePlanetsCore.nibiru.setTierRequired(6);
//		MorePlanetsCore.nibiru.setRelativeSize(4.678F);
//		MorePlanetsCore.nibiru.setBodyIcon(new ResourceLocation("nibiru:textures/gui/celestialbodies/nibiru.png"));
//		MorePlanetsCore.nibiru.setDimensionInfo(ConfigManagerMP.idDimensionNibiru, WorldProviderNibiru.class);
//		MorePlanetsCore.nibiru.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.ARGON).atmosphereComponent(IAtmosphericGas.HELIUM);
//
//		MorePlanetsCore.fronos = new Planet("fronos").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.fronos.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.fronos.setPhaseShift(1.2762F);
//		MorePlanetsCore.fronos.setRelativeDistanceFromCenter(new ScalableDistance(2.5F, 2.5F));
//		MorePlanetsCore.fronos.setRelativeOrbitTime(1 / 0.05F);
//		MorePlanetsCore.fronos.setTierRequired(7);
//		MorePlanetsCore.fronos.setRelativeSize(0.5316F);
//		MorePlanetsCore.fronos.setBodyIcon(new ResourceLocation("fronos:textures/gui/celestialbodies/fronos.png"));
//		MorePlanetsCore.fronos.setDimensionInfo(ConfigManagerMP.idDimensionFronos, WorldProviderFronos.class);
//		MorePlanetsCore.fronos.atmosphereComponent(IAtmosphericGas.OXYGEN).atmosphereComponent(IAtmosphericGas.WATER).atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.HYDROGEN);
//
//		MorePlanetsCore.kapteynB = new Planet("kapteynB").setParentSolarSystem(MorePlanetsCore.kapteynBSolarSystem);
//		MorePlanetsCore.kapteynB.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.kapteynB.setPhaseShift(1 / 2.0F);
//		MorePlanetsCore.kapteynB.setRelativeDistanceFromCenter(new ScalableDistance(1.0F, 1.0F));
//		MorePlanetsCore.kapteynB.setRelativeOrbitTime(1.9746F);
//		MorePlanetsCore.kapteynB.setTierRequired(6);
//		MorePlanetsCore.kapteynB.setRelativeSize(3.7654F);
//		MorePlanetsCore.kapteynB.setBodyIcon(new ResourceLocation("kapteynB:textures/gui/celestialbodies/kapteyn_b.png"));
//		MorePlanetsCore.kapteynB.setDimensionInfo(ConfigManagerMP.idDimensionKapteynB, WorldProviderKapteynB.class);
//		MorePlanetsCore.kapteynB.atmosphereComponent(IAtmosphericGas.NITROGEN).atmosphereComponent(IAtmosphericGas.WATER).atmosphereComponent(IAtmosphericGas.HELIUM).atmosphereComponent(IAtmosphericGas.HYDROGEN);
//
//		MorePlanetsCore.siriusB = new Planet("siriusB").setParentSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		MorePlanetsCore.siriusB.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.siriusB.setPhaseShift(100.0F);
//		MorePlanetsCore.siriusB.setRelativeDistanceFromCenter(new ScalableDistance(0.1F, 0.1F));
//		MorePlanetsCore.siriusB.setRelativeOrbitTime(46.5F);
//		MorePlanetsCore.siriusB.setTierRequired(8);
//		MorePlanetsCore.siriusB.setRelativeSize(0.125F);
//		MorePlanetsCore.siriusB.setBodyIcon(new ResourceLocation("siriusb:textures/gui/celestialbodies/sirius_b.png"));
//		MorePlanetsCore.siriusB.setDimensionInfo(ConfigManagerMP.idDimensionSiriusB, WorldProviderSiriusB.class);
//		MorePlanetsCore.siriusB.atmosphereComponent(IAtmosphericGas.CO2).atmosphereComponent(IAtmosphericGas.HELIUM);
//
//		MorePlanetsCore.mercury = new Planet("mercury").setParentSolarSystem(GalacticraftCore.solarSystemSol);
//		MorePlanetsCore.mercury.setRingColorRGB(0.1F, 0.9F, 0.6F);
//		MorePlanetsCore.mercury.setPhaseShift(1.45F);
//		MorePlanetsCore.mercury.setRelativeDistanceFromCenter(new ScalableDistance(0.5F, 0.5F));
//		MorePlanetsCore.mercury.setRelativeOrbitTime(0.24096385542168674698795180722892F);
//		MorePlanetsCore.mercury.setTierRequired(4);
//		MorePlanetsCore.mercury.setRelativeSize(0.5319F);
//		MorePlanetsCore.mercury.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/mercury.png"));
//		MorePlanetsCore.mercury.setDimensionInfo(ConfigManagerMP.idDimensionMercury, WorldProviderMercury.class);
//		MorePlanetsCore.mercury.atmosphereComponent(IAtmosphericGas.HELIUM).atmosphereComponent(IAtmosphericGas.HYDROGEN).atmosphereComponent(IAtmosphericGas.OXYGEN);
//	}
//
//	private static void registerGalaxy()
//	{
//		GalaxyRegistry.registerSolarSystem(MorePlanetsCore.siriusSolarSystem);
//		GalaxyRegistry.registerSolarSystem(MorePlanetsCore.kapteynBSolarSystem);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.diona);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.polongnius);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.nibiru);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.koentus);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.fronos);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.kapteynB);
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.siriusB);
//
//		GalaxyRegistry.registerPlanet(MorePlanetsCore.mercury);
//
//		GalaxyRegistry.registerMoon(MorePlanetsCore.deimos);
//		GalaxyRegistry.registerMoon(MorePlanetsCore.phobos);
//	}
//
//	private static void registerTeleportType()
//	{
//		TeleportTypeMP teleport = new TeleportTypeMP();
//
//		GalacticraftRegistry.registerTeleportType(WorldProviderDiona.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderPolongnius.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderNibiru.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderKoentus.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderFronos.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderKapteynB.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderSiriusB.class, teleport);
//
//		GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, teleport);
//
//		GalacticraftRegistry.registerTeleportType(WorldProviderDeimos.class, teleport);
//		GalacticraftRegistry.registerTeleportType(WorldProviderPhobos.class, teleport);
//	}
//
//	private static void registerRocketGui()
//	{
//		GalacticraftRegistry.registerRocketGui(WorldProviderDiona.class, new ResourceLocation("diona:textures/gui/diona_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderPolongnius.class, new ResourceLocation("polongnius:textures/gui/polongnius_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderNibiru.class, new ResourceLocation("nibiru:textures/gui/nibiru_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderKoentus.class, new ResourceLocation("koentus:textures/gui/koentus_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderFronos.class, new ResourceLocation("fronos:textures/gui/fronos_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderKapteynB.class, new ResourceLocation("kapteynb:textures/gui/kapteyn_b_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderSiriusB.class, new ResourceLocation("siriusb:textures/gui/sirius_b_rocket_gui.png"));
//
//		GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation("mercury:textures/gui/mercury_rocket_gui.png"));
//
//		GalacticraftRegistry.registerRocketGui(WorldProviderDeimos.class, new ResourceLocation("deimos:textures/gui/deimos_rocket_gui.png"));
//		GalacticraftRegistry.registerRocketGui(WorldProviderPhobos.class, new ResourceLocation("phobos:textures/gui/phobos_rocket_gui.png"));
//	}
//}