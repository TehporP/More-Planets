/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.core.util.MPEntityRegisterUtil;
import stevekung.mods.moreplanets.diona.entities.DionaEntityCreeperBoss;
import stevekung.mods.moreplanets.diona.entities.DionaEntityDelriumWorm;
import stevekung.mods.moreplanets.diona.entities.DionaEntityFronisiumTNT;
import stevekung.mods.moreplanets.diona.entities.DionaEntityMinionCreeper;
import stevekung.mods.moreplanets.diona.entities.DionaEntityRocketT3;
import stevekung.mods.moreplanets.diona.entities.DionaEntityRocketT3NoFlag;
import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceEnderman;
import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceWolf;
import stevekung.mods.moreplanets.diona.entities.DionaEntityThailandFlag;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityLaser;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityProjectileFronisiumTNT;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityBearry;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityBerry;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityCat;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityChocolateCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityChocolateGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityGrappy;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyBerrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyGrapeSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyLimeSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyOrangeSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyRaspberrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyStrawberrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityKiwi;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityLemonDuck;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityMarshmallow;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityMelon;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityOrangeCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityOrangeGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityPinkChicken;
import stevekung.mods.moreplanets.fronos.entities.FronosEntitySpaceStarfish;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityStrawberryCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityStrawberryGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityTomato;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityVanillaCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityVanillaGolem;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityChocolateCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityOrangeCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityPoisonArrow;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityStrawberryCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityVanillaCreamBall;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;
import stevekung.mods.moreplanets.koentus.entities.KoentusEntityVillager;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityGiantSpikeBoss;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityGiantWorm;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedWorm;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedZombie;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5NoFlag;
import stevekung.mods.moreplanets.nibiru.entities.projectile.NibiruEntityGravityInfectionDart;
import stevekung.mods.moreplanets.nibiru.entities.projectile.NibiruEntityInfectionDart;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCow;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCubeBoss;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseSlime;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4NoFlag;
import stevekung.mods.moreplanets.polongnius.entities.projectiles.PolongniusEntityCheeseSpore;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;

public class MPEntities
{
	public static void init()
	{
		registerDionaEntities();
		registerPolongniusEntities();
		registerNibiruEntities();
		registerKoentusEntities();
		registerFronosEntities();
		registerNonMobEntities();
	}

	private static void registerDionaEntities()
	{
		MPEntityRegisterUtil.registerMorePlanetCreature(DionaEntitySpaceWolf.class, "SpaceWolf", DionaConfigManager.idEntitySpaceWolf, 14144467, 13545366);
		MPEntityRegisterUtil.registerMorePlanetCreature(DionaEntitySpaceEnderman.class, "SpaceEnderman", DionaConfigManager.idEntitySpaceEnderman, 1447446, 0);
		MPEntityRegisterUtil.registerMorePlanetCreature(DionaEntityDelriumWorm.class, "DelriumWorm", DionaConfigManager.idEntityDelriumWorm, -4144960, -8355712);
		MPEntityRegisterUtil.registerMorePlanetCreature(DionaEntityCreeperBoss.class, "DionaCreeperBoss", DionaConfigManager.idEntityCreeperBoss, -8355712, -16726874);
		MPEntityRegisterUtil.registerMorePlanetCreature(DionaEntityMinionCreeper.class, "DionaMinionCreeper", DionaConfigManager.idEntityMinionCreeper, -8355712, -9269647);
	}

	private static void registerPolongniusEntities()
	{
		MPEntityRegisterUtil.registerMorePlanetCreature(PolongniusEntityCheeseCow.class, "CheeseCow", PolongniusConfigManager.idEntityCheeseCow, -19712, -6985197);
		MPEntityRegisterUtil.registerMorePlanetCreature(PolongniusEntityCheeseSlime.class, "CheeseSlime", PolongniusConfigManager.idEntityCheeseSlime, -14848, -8323);
		MPEntityRegisterUtil.registerMorePlanetCreature(PolongniusEntityCheeseCubeBoss.class, "CheeseCubeBoss", PolongniusConfigManager.idEntityCheeseCubeBoss, -14848, -8323);
	}

	private static void registerNibiruEntities()
	{
		MPEntityRegisterUtil.registerMorePlanetCreature(NibiruEntityInfectedWorm.class, "InfectedWorm", NibiruConfigManager.idInfectedWorm, -4653056, -6619136);
		MPEntityRegisterUtil.registerMorePlanetCreature(NibiruEntityGiantWorm.class, "GiantWorm", NibiruConfigManager.idGiantWorm, -2060769, -1413099);
		MPEntityRegisterUtil.registerMorePlanetCreature(NibiruEntityInfectedZombie.class, "InfectedZombie", NibiruConfigManager.idInfectedZombie, -2060769, -1413099);
		MPEntityRegisterUtil.registerMorePlanetCreature(NibiruEntityGiantSpikeBoss.class, "GiantSpikeBoss", NibiruConfigManager.idEntityGiantSpikeBoss, -2060769, -1413099);
	}

	private static void registerKoentusEntities()
	{
		MPEntityRegisterUtil.registerMorePlanetCreature(KoentusEntityVillager.class, "KoentusVillager", KoentusConfigManager.idKoentusVillager, -16777040, -16744320);
	}

	private static void registerFronosEntities()
	{
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityBearry.class, "Bearry", FronosConfigManager.idEntityBearry, -391882, -16744448);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityBerry.class, "Berry", FronosConfigManager.idEntityBerry, -11403100, -10157878);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityMarshmallow.class, "Marshmallow", FronosConfigManager.idEntityMarshmallow, -2631721, -1052689);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityKiwi.class, "Kiwi", FronosConfigManager.idEntityKiwi, -8608972, -8031948);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyBerrySlime.class, "JellyBerrySlime", FronosConfigManager.idEntityJellyBerrySlime, -12582784, -10485569);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyStrawberrySlime.class, "JellyStrawberrySlime", FronosConfigManager.idEntityJellyStrawberrySlime, -2021317, -563609);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyGrapeSlime.class, "JellyGrapeSlime", FronosConfigManager.idEntityJellyGrapeSlime, -4183118, -1684023);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyRaspberrySlime.class, "JellyRaspberrySlime", FronosConfigManager.idEntityJellyRaspberrySlime, -2484136, -375696);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityVanillaCreamSlime.class, "VanillaCreamSlime", FronosConfigManager.idEntityVanillaCreamSlime, -4176, -1827);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityChocolateCreamSlime.class, "ChocolateCreamSlime", FronosConfigManager.idEntityChocolateCreamSlime, -13559808, -12968704);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityStrawberryCreamSlime.class, "StrawberryCreamSlime", FronosConfigManager.idEntityStrawberryCreamSlime, -18433, -22785);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyLimeSlime.class, "JellyLimeSlime", FronosConfigManager.idEntityJellyLimeSlime, -3801464, -2621780);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityLemonDuck.class, "LemonDuck", FronosConfigManager.idEntityLemonDuck, -2558710, -3804404);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntitySpaceStarfish.class, "SpaceStarfish", FronosConfigManager.idEntitySpaceStarfish, -3433, -7883);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityMelon.class, "Melon", FronosConfigManager.idEntityMelon, -26833, -11618715);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityTomato.class, "Tomato", FronosConfigManager.idEntityTomato, -3538944, -3066352);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityGrappy.class, "Grappy", FronosConfigManager.idEntityGrappy, -4737025, -4144960);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityCat.class, "SpaceCat", FronosConfigManager.idEntitySpaceCat, -8355712, -5131855);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityVanillaGolem.class, "VanillaCreamGolem", FronosConfigManager.idEntityVanillaCreamGolem, -3655, -6004);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityChocolateGolem.class, "ChocolateCreamGolem", FronosConfigManager.idEntityChocolateCreamGolem, -6990287, -9420507);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityStrawberryGolem.class, "StrawberryCreamGolem", FronosConfigManager.idEntityStrawberryCreamGolem, -87073, -95790);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityOrangeGolem.class, "OrangeCreamGolem", FronosConfigManager.idEntityOrangeCreamGolem, -23222, -26317);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityOrangeCreamSlime.class, "OrangeCreamSlime", FronosConfigManager.idEntityOrangeCreamSlime, -23222, -26317);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityJellyOrangeSlime.class, "JellyOrangeSlime", FronosConfigManager.idEntityJellyOrangeSlime, -23222, -26317);
		MPEntityRegisterUtil.registerMorePlanetCreature(FronosEntityPinkChicken.class, "PinkChicken", FronosConfigManager.idEntityPinkChicken, -23222, -26317);
	}

	private static void registerNonMobEntities()
	{
		/**@Kepler22b**/
		//TODO MPEntityRegisterUtil.registerMorePlanetCreature(Kepler22bEntitySpike.class, "PoisonSpike", NibiruConfigManager.idSpike, -4653056, -6619136);

		/**@Koentus**/
		//TODO MPEntityRegisterUtil.registerMorePlanetCreature(KoentusEntityEledos.class, "Eledos", KoentusConfigManager.idEledos, -16777088, -16777018);

		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityThailandFlag.class, "MPFlag", DionaConfigManager.idEntityMPFlag, 150, 5, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityRocketT3.class, "SpaceshipT3", DionaConfigManager.idEntitySpaceshipTier3, 150, 1, false);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityProjectileFronisiumTNT.class, "ProjectileFronisiumTNT", DionaConfigManager.idEntityProjectileFronisiumTNT, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityFronisiumTNT.class, "FronisiumTNT", DionaConfigManager.idEntityFronisiumTNT, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityLaser.class, "Laser", DionaConfigManager.idEntityLaser, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(FronosEntityVanillaCreamBall.class, "VanillaCreamBall", FronosConfigManager.idEntityVanillaCreamBall, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(FronosEntityChocolateCreamBall.class, "ChocolateCreamBall", FronosConfigManager.idEntityChocolateCreamBall, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(FronosEntityStrawberryCreamBall.class, "StrawberryCreamBall", FronosConfigManager.idEntityStrawberryCreamBall, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(FronosEntityPoisonArrow.class, "PoisonArrow", FronosConfigManager.idEntityPoisonArrow, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(PolongniusEntityRocketT4.class, "SpaceshipT4", PolongniusConfigManager.idEntityRocketT4, 150, 1, false);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(FronosEntityOrangeCreamBall.class, "OrangeCreamBall", FronosConfigManager.idEntityOrangeCreamBall, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(NibiruEntityRocketT5.class, "SpaceshipT5", NibiruConfigManager.idRocketT5, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(NibiruEntityRocketT5NoFlag.class, "SpaceshipT5Unflag", NibiruConfigManager.idRocketT5Unflag, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(DionaEntityRocketT3NoFlag.class, "SpaceshipT3Unflag", DionaConfigManager.idEntitySpaceshipTier3Unflag, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(PolongniusEntityRocketT4NoFlag.class, "SpaceshipT4Unflag", PolongniusConfigManager.idEntityRocketT4Unflag, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(NibiruEntityGravityInfectionDart.class, "GravityInfectionDart", NibiruConfigManager.idEntityGravityInfectionDart, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(NibiruEntityInfectionDart.class, "InfectionDart", NibiruConfigManager.idEntityInfectionDart, 150, 1, true);
		MPEntityRegisterUtil.registerMorePlanetNonMobEntity(PolongniusEntityCheeseSpore.class, "CheeseSpore", PolongniusConfigManager.idEntityCheeseSpore, 150, 1, true);

	}
}