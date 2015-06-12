/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.client.particle.EntityBreakingFXMP;
import stevekung.mods.moreplanets.client.renderer.EntityRendererMP;
import stevekung.mods.moreplanets.client.renderer.ItemRendererMP;
import stevekung.mods.moreplanets.client.renderer.ModelRendererMP;
import stevekung.mods.moreplanets.client.renderer.TileEntityRendererHelper;
import stevekung.mods.moreplanets.client.renderer.TileEntityRendererMP;
import stevekung.mods.moreplanets.common.util.BlockVariantsUtil;
import stevekung.mods.moreplanets.common.util.ItemVariantsUtil;
import stevekung.mods.moreplanets.common.util.StateMapperUtil;
import stevekung.mods.moreplanets.moons.koentus.client.particles.EntityKoentusIceSludgeDripFX;
import stevekung.mods.moreplanets.moons.koentus.client.particles.EntityKoentusMeteorSmokeFX;
import stevekung.mods.moreplanets.moons.koentus.client.particles.EntityWhiteCrystalSmokeFX;
import stevekung.mods.moreplanets.planets.diona.client.particles.EntityBluePortalFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityBlueFlameFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityCaramelDripFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityCavernOysterFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityCoconutMilkDripFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityCoconutMilkFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityGoldenGrassFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityGoldenSmokeFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityJungleIrisFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityMineralWaterDripFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityMineralWaterFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityOrangeDandelionFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityOvantineDripFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityOvantineSmokeFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityPinkDandelionFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityPurpleDandelionFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityPurpleSpikeFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityTeaDripFX;
import stevekung.mods.moreplanets.planets.fronos.client.particles.EntityTeaFluidFX;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.client.particles.EntityFrozenWaterDripFX;
import stevekung.mods.moreplanets.planets.kapteynb.client.particles.EntityGeyserFX;
import stevekung.mods.moreplanets.planets.kapteynb.client.particles.EntityUraniumSmokeFX;
import stevekung.mods.moreplanets.planets.nibiru.client.particles.EntityGeneratorSmokeFX;
import stevekung.mods.moreplanets.planets.nibiru.client.particles.EntityInfectedSporeFX;
import stevekung.mods.moreplanets.planets.pluto.client.particles.EntityXeoniumSmokeFX;
import stevekung.mods.moreplanets.planets.polongnius.client.particles.EntityCheeseBubbleFX;
import stevekung.mods.moreplanets.planets.polongnius.client.particles.EntityCheeseOfMilkDripFX;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.client.particles.EntitySiriusFlameFX;
import stevekung.mods.moreplanets.planets.siriusb.client.particles.EntitySiriusLavaDripFX;
import stevekung.mods.moreplanets.planets.siriusb.client.particles.EntitySiriusLavaFX;
import stevekung.mods.moreplanets.planets.venus.client.model.ModelJetpack;
import stevekung.mods.moreplanets.planets.venus.client.particles.EntityVenusSmokeFX;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class ClientProxyMP extends CommonProxyMP
{
	public static Map<Item, ModelBiped> jetpackModel = new HashMap<Item, ModelBiped>();

	@Override
	public void registerRenderer()
	{
		TileEntityItemStackRenderer.instance = new TileEntityRendererHelper();

		EntityRendererMP.init();
		ItemRendererMP.registerItemRenderers();
		TileEntityRendererMP.registerTileEntityRenderers();
		ModelRendererMP.registerModelRender();
		StateMapperUtil.registerStateMapper();
		BlockVariantsUtil.registerBlockVariants();
		ItemVariantsUtil.registerItemVariants();

		ModelJetpack jetpack = new ModelJetpack(0.75F);
		jetpackModel.put(VenusItems.jetpack, jetpack);
	}

	@Override
	public void spawnMotionParticle(ParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ)
	{
		EntityFX entityfx = null;
		Minecraft mc = FMLClientHandler.instance().getClient();

		if (type == ParticleTypesMP.KOENTUS_METEOR_SMOKE)
		{
			entityfx = new EntityKoentusMeteorSmokeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		else if (type == ParticleTypesMP.CAVERN_OYSTER)
		{
			entityfx = new EntityCavernOysterFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		else if (type == ParticleTypesMP.INFECTED_SPORE)
		{
			entityfx = new EntityInfectedSporeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		else if (type == ParticleTypesMP.BLUE_PORTAL)
		{
			entityfx = new EntityBluePortalFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		else if (type == ParticleTypesMP.JUNGLE_IRIS)
		{
			entityfx = new EntityJungleIrisFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		else if (type == ParticleTypesMP.FROZEN_WATER_GEYSER)
		{
			entityfx = new EntityGeyserFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
		}
		mc.effectRenderer.addEffect(entityfx);
	}

	@Override
	public void spawnParticle(ParticleTypesMP type, double x, double y, double z)
	{
		EntityFX entityfx = null;
		Minecraft mc = FMLClientHandler.instance().getClient();

		if (type == ParticleTypesMP.KOENTUS_SLUDGE_DRIP)
		{
			entityfx = new EntityKoentusIceSludgeDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.COCONUT_MILK_DRIP)
		{
			entityfx = new EntityCoconutMilkDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.MINERAL_WATER_DRIP)
		{
			entityfx = new EntityMineralWaterDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.OVANTINE_DRIP)
		{
			entityfx = new EntityOvantineDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.CHEESE_OF_MILK_DRIP)
		{
			entityfx = new EntityCheeseOfMilkDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.FROZEN_WATER_DRIP)
		{
			entityfx = new EntityFrozenWaterDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.TEA_DRIP)
		{
			entityfx = new EntityTeaDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.CARAMEL_DRIP)
		{
			entityfx = new EntityCaramelDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.SIRIUS_LAVA_DRIP)
		{
			entityfx = new EntitySiriusLavaDripFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.CHEESE_SLIME)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, PolongniusItems.cheese_slimeball);
		}
		else if (type == ParticleTypesMP.VANILLA_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 0);
		}
		else if (type == ParticleTypesMP.CHOCOLATE_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 1);
		}
		else if (type == ParticleTypesMP.STRAWBERRY_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 2);
		}
		else if (type == ParticleTypesMP.ORANGE_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 3);
		}
		else if (type == ParticleTypesMP.TEA_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 4);
		}
		else if (type == ParticleTypesMP.LEMON_CREAM_BALL)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.cream_ball, 5);
		}
		else if (type == ParticleTypesMP.GRAPE_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 0);
		}
		else if (type == ParticleTypesMP.RASPBERRY_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 1);
		}
		else if (type == ParticleTypesMP.STRAWBERRY_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 2);
		}
		else if (type == ParticleTypesMP.BERRY_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 3);
		}
		else if (type == ParticleTypesMP.LIME_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 4);
		}
		else if (type == ParticleTypesMP.ORANGE_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 5);
		}
		else if (type == ParticleTypesMP.GREEN_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 6);
		}
		else if (type == ParticleTypesMP.LEMON_JELLY)
		{
			entityfx = new EntityBreakingFXMP(mc.theWorld, x, y, z, FronosItems.jelly, 7);
		}
		else if (type == ParticleTypesMP.ORANGE_DANDELION)
		{
			entityfx = new EntityOrangeDandelionFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.PURPLE_DANDELION)
		{
			entityfx = new EntityPinkDandelionFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.PURPLE_DANDELION)
		{
			entityfx = new EntityPurpleDandelionFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.CHEESE_OF_MILK_BUBBLE)
		{
			entityfx = new EntityCheeseBubbleFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.WHITE_CRYSTAL_SMOKE)
		{
			entityfx = new EntityWhiteCrystalSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.ICHORIUS_SMOKE)
		{
			entityfx = new EntityGeneratorSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.PURPLE_SPIKE)
		{
			entityfx = new EntityPurpleSpikeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.COCONUT_MILK)
		{
			entityfx = new EntityCoconutMilkFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.MINERAL_WATER)
		{
			entityfx = new EntityMineralWaterFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.OVANTINE)
		{
			entityfx = new EntityOvantineSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.TEA)
		{
			entityfx = new EntityTeaFluidFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.SIRIUS_LAVA)
		{
			entityfx = new EntitySiriusLavaFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.GOLDEN_DUST)
		{
			entityfx = new EntityGoldenGrassFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.GOLDEN_SMOKE)
		{
			entityfx = new EntityGoldenSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.URANIUM_SMOKE)
		{
			entityfx = new EntityUraniumSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.SIRIUS_FLAME)
		{
			entityfx = new EntitySiriusFlameFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.BLUE_FLAME)
		{
			entityfx = new EntityBlueFlameFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.VENUS_SMOKE)
		{
			entityfx = new EntityVenusSmokeFX(mc.theWorld, x, y, z);
		}
		else if (type == ParticleTypesMP.XEONIUM_SMOKE)
		{
			entityfx = new EntityXeoniumSmokeFX(mc.theWorld, x, y, z);
		}
		mc.effectRenderer.addEffect(entityfx);
	}

	public static enum ParticleTypesMP
	{
		KOENTUS_SLUDGE_DRIP,
		COCONUT_MILK_DRIP,
		MINERAL_WATER_DRIP,
		OVANTINE_DRIP,
		CHEESE_OF_MILK_DRIP,
		FROZEN_WATER_DRIP,
		TEA_DRIP,
		CARAMEL_DRIP,
		SIRIUS_LAVA_DRIP,
		CHEESE_SLIME,
		VANILLA_CREAM_BALL,
		CHOCOLATE_CREAM_BALL,
		STRAWBERRY_CREAM_BALL,
		ORANGE_CREAM_BALL,
		TEA_CREAM_BALL,
		LEMON_CREAM_BALL,
		GRAPE_JELLY,
		RASPBERRY_JELLY,
		STRAWBERRY_JELLY,
		BERRY_JELLY,
		LIME_JELLY,
		ORANGE_JELLY,
		GREEN_JELLY,
		LEMON_JELLY,
		ORANGE_DANDELION,
		PINK_DANDELION,
		PURPLE_DANDELION,
		CHEESE_OF_MILK_BUBBLE,
		WHITE_CRYSTAL_SMOKE,
		ICHORIUS_SMOKE,
		PURPLE_SPIKE,
		COCONUT_MILK,
		MINERAL_WATER,
		OVANTINE,
		TEA,
		SIRIUS_LAVA,
		GOLDEN_DUST,
		GOLDEN_SMOKE,
		URANIUM_SMOKE,
		SIRIUS_FLAME,
		BLUE_FLAME,
		KOENTUS_METEOR_SMOKE,
		CAVERN_OYSTER,
		INFECTED_SPORE,
		BLUE_PORTAL,
		JUNGLE_IRIS,
		VENUS_SMOKE,
		FROZEN_WATER_GEYSER,
		XEONIUM_SMOKE
	}
}