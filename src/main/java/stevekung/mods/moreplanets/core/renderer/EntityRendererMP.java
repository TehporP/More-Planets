/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import micdoodle8.mods.galacticraft.core.client.render.entities.RenderTier1Rocket;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import stevekung.mods.moreplanets.core.entities.models.ModelRocketMP;
import stevekung.mods.moreplanets.core.entities.models.ModelRocketNoFlagMP;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;
import stevekung.mods.moreplanets.moons.europa.render.entities.RenderEuropaSquid;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteorChunk;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusSludgeling;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusianVillager;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusMeteorChunk;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusSludgeling;
import stevekung.mods.moreplanets.moons.koentus.render.entities.RenderKoentusianVillager;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaCreeperBoss;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDustSludgeling;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFlagMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;
import stevekung.mods.moreplanets.planets.diona.entities.EntityTier4Rocket;
import stevekung.mods.moreplanets.planets.diona.entities.EntityTier4RocketNoFlag;
import stevekung.mods.moreplanets.planets.diona.entities.models.ModelSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderDionaCreeperBoss;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderDustSludgeling;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderFlagMP;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.render.entities.projectiles.RenderLaser;
import stevekung.mods.moreplanets.planets.diona.render.entities.projectiles.RenderProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBerry;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamSlime;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityKiwi;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityMarshmallow;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityMelon;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStarfish;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStrawberryChicken;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTier7Rocket;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityChocolateCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityLemonCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityOrangeCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityStrawberryCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityTeaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityVanillaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelCreamCat;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelGrappy1;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelGrappy2;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderBearry;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderBerry;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderCreamCat;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderCreamGolem;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderCreamSlime;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderFronosVillager;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderGrappy;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderJellySlime;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderKiwi;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderMarshmallow;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderMelon;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderPoisonArrow;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderStarfish;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderStrawberryChicken;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderTier7Rocket;
import stevekung.mods.moreplanets.planets.fronos.render.entities.RenderTomato;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityFrozenSludgeling;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityIceCrystalMeteor;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityTier8Rocket;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityUraniumBomb;
import stevekung.mods.moreplanets.planets.kapteynb.render.entities.RenderFrozenSludgeling;
import stevekung.mods.moreplanets.planets.kapteynb.render.entities.RenderIceCrystalMeteor;
import stevekung.mods.moreplanets.planets.kapteynb.render.entities.RenderTier8Rocket;
import stevekung.mods.moreplanets.planets.kapteynb.render.entities.RenderUraniumBomb;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityEvolvedInfectedSpiderBoss;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityTier6Rocket;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityTier6RocketNoFlag;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedEvolvedSpiderBoss;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedWorm;
import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedZombie;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityPolongniusMeteor;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityPolongniusMeteorChunk;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityTier5Rocket;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityTier5RocketNoFlag;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderCheeseCubeBoss;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderCheeseSlime;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderPolongniusMeteor;
import stevekung.mods.moreplanets.planets.polongnius.render.entities.RenderPolongniusMeteorChunk;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntityEvolvedSiriusBlazeBoss;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderEvolvedSiriusBlazeBoss;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.RenderSiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.render.entities.projectiles.RenderSiriusSmallFireball;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianVillager;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.render.entities.RenderVenusianVillager;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRendererMP
{
	private static IModelCustom tier3RocketModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftasteroids:models/tier3rocket.obj"));

	public static void init()
	{
		EntityRendererMP.registerEntityRenderers();
		EntityRendererMP.registerNonEntityRenderers();
	}

	private static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDustSludgeling.class, new RenderDustSludgeling());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpaceWolf.class, new RenderSpaceWolf(new ModelSpaceWolf(), new ModelSpaceWolf()));
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedEnderman.class, new RenderEvolvedEnderman());
		RenderingRegistry.registerEntityRenderingHandler(EntityDionaCreeperBoss.class, new RenderDionaCreeperBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityDionaMinionCreeper.class, new RenderDionaMinionCreeper());

		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCow.class, new RenderCheeseCow());
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSlime.class, new RenderCheeseSlime(new ModelSlime(16), new ModelSlime(0)));
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCubeEyeBoss.class, new RenderCheeseCubeBoss());

		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedWorm.class, new RenderInfectedWorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantWorm.class, new RenderGiantWorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedZombie.class, new RenderInfectedZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedInfectedSpiderBoss.class, new RenderInfectedEvolvedSpiderBoss());

		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusianVillager.class, new RenderKoentusianVillager());
		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusSludgeling.class, new RenderKoentusSludgeling());

		RenderingRegistry.registerEntityRenderingHandler(EntityBearry.class, new RenderBearry());
		RenderingRegistry.registerEntityRenderingHandler(EntityBerry.class, new RenderBerry());
		RenderingRegistry.registerEntityRenderingHandler(EntityMarshmallow.class, new RenderMarshmallow());
		RenderingRegistry.registerEntityRenderingHandler(EntityKiwi.class, new RenderKiwi());
		RenderingRegistry.registerEntityRenderingHandler(EntityJellySlime.class, new RenderJellySlime(new ModelSlime(16), new ModelSlime(0)));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamSlime.class, new RenderCreamSlime(new ModelSlime(16), new ModelSlime(0)));
		RenderingRegistry.registerEntityRenderingHandler(EntityLemonDuck.class, new RenderLemonDuck());
		RenderingRegistry.registerEntityRenderingHandler(EntityStarfish.class, new RenderStarfish());
		RenderingRegistry.registerEntityRenderingHandler(EntityMelon.class, new RenderMelon());
		RenderingRegistry.registerEntityRenderingHandler(EntityTomato.class, new RenderTomato());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrappy.class, new RenderGrappy(new ModelGrappy2(), new ModelGrappy1()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamCat.class, new RenderCreamCat(new ModelCreamCat()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamGolem.class, new RenderCreamGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryChicken.class, new RenderStrawberryChicken());
		RenderingRegistry.registerEntityRenderingHandler(EntityFronosVillager.class, new RenderFronosVillager());

		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenSludgeling.class, new RenderFrozenSludgeling());

		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusCreeper.class, new RenderSiriusCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusBlaze.class, new RenderSiriusBlaze());
		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusMagmaCube.class, new RenderSiriusMagmaCube());
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedSiriusBlazeBoss.class, new RenderEvolvedSiriusBlazeBoss());

		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianBlaze.class, new RenderVenusianBlaze());
		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianSlime.class, new RenderVenusianSlime(new ModelSlime(16), new ModelSlime(0)));
		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianVillager.class, new RenderVenusianVillager());

		RenderingRegistry.registerEntityRenderingHandler(EntityEuropaSquid.class, new RenderEuropaSquid());
	}

	private static void registerNonEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFlagMP.class, new RenderFlagMP());
		RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "diona", "tier4_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier4RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "diona", "tier4_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileFronisiumTNT.class, new RenderProjectileFronisiumTNT());
		RenderingRegistry.registerEntityRenderingHandler(EntityFronisiumTNT.class, new RenderFronisiumTNT());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserMP.class, new RenderLaser());

		RenderingRegistry.registerEntityRenderingHandler(EntityTier5Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "polongnius", "tier5_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier5RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "polongnius", "tier5_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSpore.class, new RenderCheeseSpore(2.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteorChunk.class, new RenderPolongniusMeteorChunk());
		RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteor.class, new RenderPolongniusMeteor());

		RenderingRegistry.registerEntityRenderingHandler(EntityTier6Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "nibiru", "tier6_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTier6RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "nibiru", "tier6_rocket"));

		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteorChunk.class, new RenderKoentusMeteorChunk());
		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteor.class, new RenderKoentusMeteor());

		RenderingRegistry.registerEntityRenderingHandler(EntityVanillaCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityChocolateCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityOrangeCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 3));
		RenderingRegistry.registerEntityRenderingHandler(EntityTeaCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 4));
		RenderingRegistry.registerEntityRenderingHandler(EntityLemonCreamBall.class, new RenderSnowball(FronosItems.cream_ball, 5));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonArrow.class, new RenderPoisonArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityTier7Rocket.class, new RenderTier7Rocket(EntityRendererMP.tier3RocketModel, "fronos", "tier7_rocket"));

		RenderingRegistry.registerEntityRenderingHandler(EntityUraniumBomb.class, new RenderUraniumBomb());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceCrystalMeteor.class, new RenderIceCrystalMeteor());
		RenderingRegistry.registerEntityRenderingHandler(EntityTier8Rocket.class, new RenderTier8Rocket(EntityRendererMP.tier3RocketModel, "kapteynb", "tier8_rocket"));

		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusSmallFireball.class, new RenderSiriusSmallFireball());


	}
}