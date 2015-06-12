/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import micdoodle8.mods.galacticraft.core.client.render.entities.GCCoreRenderSpaceship;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import stevekung.mods.moreplanets.core.models.MPModelSpaceship;
import stevekung.mods.moreplanets.core.models.MPModelSpaceshipNoFlag;
import stevekung.mods.moreplanets.core.render.player.MPRenderPlayer;
import stevekung.mods.moreplanets.diona.core.models.entities.DionaModelSpaceWolf;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderCreeperBoss;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderDelriumWorm;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderFlag;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderFronisiumTNT;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderMinionCreeper;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderSpaceEnderman;
import stevekung.mods.moreplanets.diona.core.render.entities.DionaRenderSpaceWolf;
import stevekung.mods.moreplanets.diona.core.render.entities.projectiles.DionaRenderLaser;
import stevekung.mods.moreplanets.diona.core.render.entities.projectiles.DionaRenderProjectileTNT;
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
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelCat;
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelGrappy1;
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelGrappy2;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderBearry;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderBerry;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderCat;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderChocolateCreamGolem;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderChocolateCreamSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderGrappy;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyBerrySlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyGrapeSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyLimeSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyOrangeSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyRaspberrySlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderJellyStrawberrySlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderKiwi;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderLemonDuck;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderMarshmallow;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderMelon;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderOrangeCreamGolem;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderOrangeCreamSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderPinkChicken;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderPoisonArrow;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderStarfish;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderStrawberryCreamGolem;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderStrawberryCreamSlime;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderTomato;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderVanillaCreamGolem;
import stevekung.mods.moreplanets.fronos.core.render.entities.FronosRenderVanillaCreamSlime;
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
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.koentus.core.render.entities.KoentusRenderEledos;
import stevekung.mods.moreplanets.koentus.core.render.entities.KoentusRenderVillager;
import stevekung.mods.moreplanets.koentus.entities.KoentusEntityEledos;
import stevekung.mods.moreplanets.koentus.entities.KoentusEntityVillager;
import stevekung.mods.moreplanets.nibiru.core.render.entities.NibiruRenderGiantSpikeBoss;
import stevekung.mods.moreplanets.nibiru.core.render.entities.NibiruRenderGiantWorm;
import stevekung.mods.moreplanets.nibiru.core.render.entities.NibiruRenderInfectedWorm;
import stevekung.mods.moreplanets.nibiru.core.render.entities.NibiruRenderInfectedZombie;
import stevekung.mods.moreplanets.nibiru.core.render.entities.NibiruRenderInfectionDart;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityGiantSpikeBoss;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityGiantWorm;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedWorm;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedZombie;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityRocketT5NoFlag;
import stevekung.mods.moreplanets.nibiru.entities.projectile.NibiruEntityInfectionDart;
import stevekung.mods.moreplanets.polongnius.core.render.entities.PolongniusRenderCheeseCow;
import stevekung.mods.moreplanets.polongnius.core.render.entities.PolongniusRenderCheeseCubeBoss;
import stevekung.mods.moreplanets.polongnius.core.render.entities.PolongniusRenderCheeseSlime;
import stevekung.mods.moreplanets.polongnius.core.render.entities.PolongniusRenderCheeseSpore;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCow;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCubeBoss;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseSlime;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4NoFlag;
import stevekung.mods.moreplanets.polongnius.entities.projectiles.PolongniusEntityCheeseSpore;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MPEntityRenderer
{
	public static void registerEntityRenderers()
	{
		/**@Diona**/
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityDelriumWorm.class, new DionaRenderDelriumWorm());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntitySpaceWolf.class, new DionaRenderSpaceWolf(new DionaModelSpaceWolf(), new DionaModelSpaceWolf(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(DionaEntitySpaceEnderman.class, new DionaRenderSpaceEnderman());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityCreeperBoss.class, new DionaRenderCreeperBoss());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityMinionCreeper.class, new DionaRenderMinionCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new MPRenderPlayer());

		/**@Kepler22b**/
		//TODO RenderingRegistry.registerEntityRenderingHandler(Kepler22bEntitySpike.class, new Kepler22bRenderSpike());

		/**@Polongnius**/
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityCheeseCow.class, new PolongniusRenderCheeseCow());
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityCheeseSlime.class, new PolongniusRenderCheeseSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityCheeseCubeBoss.class, new PolongniusRenderCheeseCubeBoss());

		/**@Nibiru**/
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityInfectedWorm.class, new NibiruRenderInfectedWorm());
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityGiantWorm.class, new NibiruRenderGiantWorm());
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityInfectedZombie.class, new NibiruRenderInfectedZombie());
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityGiantSpikeBoss.class, new NibiruRenderGiantSpikeBoss());

		/**@Koentus**/
		RenderingRegistry.registerEntityRenderingHandler(KoentusEntityEledos.class, new KoentusRenderEledos());
		RenderingRegistry.registerEntityRenderingHandler(KoentusEntityVillager.class, new KoentusRenderVillager());

		/**@Fronos**/
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityBearry.class, new FronosRenderBearry());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityBerry.class, new FronosRenderBerry());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityMarshmallow.class, new FronosRenderMarshmallow());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityKiwi.class, new FronosRenderKiwi());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyBerrySlime.class, new FronosRenderJellyBerrySlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyStrawberrySlime.class, new FronosRenderJellyStrawberrySlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyGrapeSlime.class, new FronosRenderJellyGrapeSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyRaspberrySlime.class, new FronosRenderJellyRaspberrySlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityChocolateCreamSlime.class, new FronosRenderChocolateCreamSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityVanillaCreamSlime.class, new FronosRenderVanillaCreamSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyLimeSlime.class, new FronosRenderJellyLimeSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityStrawberryCreamSlime.class, new FronosRenderStrawberryCreamSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityLemonDuck.class, new FronosRenderLemonDuck());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntitySpaceStarfish.class, new FronosRenderStarfish());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityMelon.class, new FronosRenderMelon());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityTomato.class, new FronosRenderTomato());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityGrappy.class, new FronosRenderGrappy(new FronosModelGrappy2(), new FronosModelGrappy1(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityCat.class, new FronosRenderCat(new FronosModelCat(), 0));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityVanillaGolem.class, new FronosRenderVanillaCreamGolem());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityChocolateGolem.class, new FronosRenderChocolateCreamGolem());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityStrawberryGolem.class, new FronosRenderStrawberryCreamGolem());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityOrangeGolem.class, new FronosRenderOrangeCreamGolem());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityOrangeCreamSlime.class, new FronosRenderOrangeCreamSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityJellyOrangeSlime.class, new FronosRenderJellyOrangeSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityPinkChicken.class, new FronosRenderPinkChicken());

		/**@NonMobEntities**/
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityThailandFlag.class, new DionaRenderFlag());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityRocketT3.class, new GCCoreRenderSpaceship(new MPModelSpaceship(), "diona", "rocketT3"));
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityProjectileFronisiumTNT.class, new DionaRenderProjectileTNT());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityFronisiumTNT.class, new DionaRenderFronisiumTNT());
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityLaser.class, new DionaRenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityVanillaCreamBall.class, new RenderSnowball(FronosItems.creamBall, 0));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityChocolateCreamBall.class, new RenderSnowball(FronosItems.creamBall, 1));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityStrawberryCreamBall.class, new RenderSnowball(FronosItems.creamBall, 2));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityOrangeCreamBall.class, new RenderSnowball(FronosItems.creamBall, 3));
		RenderingRegistry.registerEntityRenderingHandler(FronosEntityPoisonArrow.class, new FronosRenderPoisonArrow());
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityRocketT4.class, new GCCoreRenderSpaceship(new MPModelSpaceship(), "polongnius", "rocketT4"));
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityRocketT5.class, new GCCoreRenderSpaceship(new MPModelSpaceship(), "nibiru", "rocketT5"));
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityRocketT5NoFlag.class, new GCCoreRenderSpaceship(new MPModelSpaceshipNoFlag(), "nibiru", "rocketT5"));
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityRocketT4NoFlag.class, new GCCoreRenderSpaceship(new MPModelSpaceshipNoFlag(), "polongnius", "rocketT4"));
		RenderingRegistry.registerEntityRenderingHandler(DionaEntityRocketT3NoFlag.class, new GCCoreRenderSpaceship(new MPModelSpaceshipNoFlag(), "diona", "rocketT3"));
		RenderingRegistry.registerEntityRenderingHandler(NibiruEntityInfectionDart.class, new NibiruRenderInfectionDart());
		RenderingRegistry.registerEntityRenderingHandler(PolongniusEntityCheeseSpore.class, new PolongniusRenderCheeseSpore(2.0F));

	}
}