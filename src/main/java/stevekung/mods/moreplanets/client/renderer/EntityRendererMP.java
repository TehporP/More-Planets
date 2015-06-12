/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import stevekung.mods.moreplanets.client.renderer.entities.RenderFlagMP;
import stevekung.mods.moreplanets.client.renderer.entities.RenderSnowballMP;
import stevekung.mods.moreplanets.common.entities.EntityFlagMP;
import stevekung.mods.moreplanets.moons.europa.client.render.entities.RenderEuropaGuardian;
import stevekung.mods.moreplanets.moons.europa.client.render.entities.RenderEuropaSquid;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaGuardian;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.projectiles.RenderLaserMP;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.projectiles.RenderProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityProjectileFronisiumTNT;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderBearry;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderBerry;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderCreamCat;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderCreamGolem;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderCreamSlime;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderFronosVillager;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderGrappy;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderJellySlime;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderKiwi;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderMarshmallow;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderMelon;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderPoisonArrow;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderStarfish;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderStrawberryChicken;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderTomato;
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
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityChocolateCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityLemonCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityOrangeCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityStrawberryCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityTeaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityVanillaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.client.render.entities.RenderUraniumBomb;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityUraniumBomb;
import stevekung.mods.moreplanets.planets.nibiru.client.render.entities.RenderGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.client.render.entities.RenderInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.polongnius.client.render.entities.RenderCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.client.render.entities.RenderCheeseSlime;
import stevekung.mods.moreplanets.planets.polongnius.client.render.entities.RenderCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.siriusb.client.render.entities.RenderSiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.client.render.entities.RenderSiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.client.render.entities.RenderSiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.client.render.entities.projectiles.RenderSiriusSmallFireball;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
import stevekung.mods.moreplanets.planets.venus.client.render.entities.RenderVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.client.render.entities.RenderVenusianSlime;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;

public class EntityRendererMP
{
	//private static IModelPart tier3RocketModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftasteroids:models/tier3rocket.obj"));

	public static void init()
	{
		EntityRendererMP.registerEntityRenderers();
		EntityRendererMP.registerNonEntityRenderers();
	}

	private static void registerEntityRenderers()
	{
		RenderManager render = Minecraft.getMinecraft().getRenderManager();

		//		RenderingRegistry.registerEntityRenderingHandler(EntityDustSludgeling.class, new RenderDustSludgeling(render));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpaceWolf.class, new RenderSpaceWolf(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedEnderman.class, new RenderEvolvedEnderman(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityDionaCreeperBoss.class, new RenderDionaCreeperBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityDionaMinionCreeper.class, new RenderDionaMinionCreeper(render));

		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCow.class, new RenderCheeseCow(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSlime.class, new RenderCheeseSlime(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCubeEyeBoss.class, new RenderCheeseCubeBoss(render));

		//		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedWorm.class, new RenderInfectedWorm(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantWorm.class, new RenderGiantWorm(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedZombie.class, new RenderInfectedZombie(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedEvolvedSpiderBoss.class, new RenderInfectedEvolvedSpiderBoss(render));

		//RenderingRegistry.registerEntityRenderingHandler(EntityKoentusianVillager.class, new RenderKoentusianVillager());
		//		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusSludgeling.class, new RenderKoentusSludgeling(render));

		RenderingRegistry.registerEntityRenderingHandler(EntityBearry.class, new RenderBearry(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityBerry.class, new RenderBerry(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarshmallow.class, new RenderMarshmallow(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityKiwi.class, new RenderKiwi(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityJellySlime.class, new RenderJellySlime(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamSlime.class, new RenderCreamSlime(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityLemonDuck.class, new RenderLemonDuck(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarfish.class, new RenderStarfish(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityMelon.class, new RenderMelon(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomato.class, new RenderTomato(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrappy.class, new RenderGrappy(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamCat.class, new RenderCreamCat(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityCreamGolem.class, new RenderCreamGolem(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryChicken.class, new RenderStrawberryChicken(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityFronosVillager.class, new RenderFronosVillager(render));

		//		RenderingRegistry.registerEntityRenderingHandler(EntityFrozenSludgeling.class, new RenderFrozenSludgeling(render));

		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusCreeper.class, new RenderSiriusCreeper(render));
		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusBlaze.class, new RenderSiriusBlaze(render));
		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusMagmaCube.class, new RenderSiriusMagmaCube(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedSiriusBlazeBoss.class, new RenderEvolvedSiriusBlazeBoss(render));

		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianBlaze.class, new RenderVenusianBlaze(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianSlime.class, new RenderVenusianSlime(render));

		RenderingRegistry.registerEntityRenderingHandler(EntityEuropaSquid.class, new RenderEuropaSquid(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityEuropaGuardian.class, new RenderEuropaGuardian(render));
	}

	private static void registerNonEntityRenderers()
	{
		RenderManager render = Minecraft.getMinecraft().getRenderManager();

		RenderingRegistry.registerEntityRenderingHandler(EntityFlagMP.class, new RenderFlagMP(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier4Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "diona", "tier4_rocket"));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier4RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "diona", "tier4_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileFronisiumTNT.class, new RenderProjectileFronisiumTNT(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityFronisiumTNT.class, new RenderFronisiumTNT(render));
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserMP.class, new RenderLaserMP(render));

		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier5Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "polongnius", "tier5_rocket"));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier5RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "polongnius", "tier5_rocket"));
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseSpore.class, new RenderCheeseSpore(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteorChunk.class, new RenderPolongniusMeteorChunk(render));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityPolongniusMeteor.class, new RenderPolongniusMeteor(render));

		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier6Rocket.class, new RenderTier1Rocket(new ModelRocketMP(), "nibiru", "tier6_rocket"));
		//		RenderingRegistry.registerEntityRenderingHandler(EntityTier6RocketNoFlag.class, new RenderTier1Rocket(new ModelRocketNoFlagMP(), "nibiru", "tier6_rocket"));

		//		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteorChunk.class, new RenderKoentusMeteorChunk());
		//		RenderingRegistry.registerEntityRenderingHandler(EntityKoentusMeteor.class, new RenderKoentusMeteor(render));

		RenderingRegistry.registerEntityRenderingHandler(EntityVanillaCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 0)));
		RenderingRegistry.registerEntityRenderingHandler(EntityChocolateCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 1)));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrawberryCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 2)));
		RenderingRegistry.registerEntityRenderingHandler(EntityOrangeCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 3)));
		RenderingRegistry.registerEntityRenderingHandler(EntityTeaCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 4)));
		RenderingRegistry.registerEntityRenderingHandler(EntityLemonCreamBall.class, new RenderSnowballMP(render, new ItemStack(FronosItems.cream_ball, 1, 5)));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonArrow.class, new RenderPoisonArrow(render));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTier7Rocket.class, new RenderTier7Rocket(EntityRendererMP.tier3RocketModel, "fronos", "tier7_rocket"));

		RenderingRegistry.registerEntityRenderingHandler(EntityUraniumBomb.class, new RenderUraniumBomb(render));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTier8Rocket.class, new RenderTier8Rocket(EntityRendererMP.tier3RocketModel, "kapteynb", "tier8_rocket"));

		RenderingRegistry.registerEntityRenderingHandler(EntitySiriusSmallFireball.class, new RenderSiriusSmallFireball(render));

	}
}