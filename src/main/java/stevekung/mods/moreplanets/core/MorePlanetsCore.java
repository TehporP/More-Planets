/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core;

import java.io.File;

import net.minecraft.block.Block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Level;

import stevekung.mods.moreplanets.common.config.ConfigManagerMP;
import stevekung.mods.moreplanets.common.eventhandler.GuiEventHandler;
import stevekung.mods.moreplanets.common.eventhandler.LightningStormHandler;
import stevekung.mods.moreplanets.common.eventhandler.MainMenuEventHandlerMP;
import stevekung.mods.moreplanets.common.eventhandler.MorePlanetsEvents;
import stevekung.mods.moreplanets.common.integration.DispenserRegistryMP;
import stevekung.mods.moreplanets.common.integration.TreeCapitatorIntegrationMP;
import stevekung.mods.moreplanets.common.network.MessageHandlerOnClient;
import stevekung.mods.moreplanets.common.network.MessageHandlerOnServer;
import stevekung.mods.moreplanets.common.network.MeteorMessageToServer;
import stevekung.mods.moreplanets.common.network.NetworkMessageToClient;
import stevekung.mods.moreplanets.common.util.FurnaceFuelMP;
import stevekung.mods.moreplanets.core.init.MPArmors;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.init.MPEntities;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.core.init.MPTileEntities;
import stevekung.mods.moreplanets.core.init.MPTools;
import stevekung.mods.moreplanets.core.proxy.CommonProxyMP;
import stevekung.mods.moreplanets.moons.deimos.recipe.CraftingRecipesDeimos;
import stevekung.mods.moreplanets.moons.io.recipe.CraftingRecipesIo;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.client.KoentusMeteorTickHandler;
import stevekung.mods.moreplanets.moons.koentus.recipe.CraftingRecipesKoentus;
import stevekung.mods.moreplanets.moons.phobos.recipe.CraftingRecipesPhobos;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.recipe.CraftingRecipesDiona;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.armor.FronosArmorItems;
import stevekung.mods.moreplanets.planets.fronos.recipe.CraftingRecipesFronos;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.client.IceCrystalMeteorTickHandler;
import stevekung.mods.moreplanets.planets.kapteynb.items.tools.KapteynBToolsItems;
import stevekung.mods.moreplanets.planets.kapteynb.recipe.CraftingRecipesKapteynB;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.mercury.recipe.CraftingRecipesMercury;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.recipe.CraftingRecipesNibiru;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.recipe.CraftingRecipesPluto;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.client.PolongniusMeteorTickHandler;
import stevekung.mods.moreplanets.planets.polongnius.recipe.CraftingRecipesPolongnius;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.recipe.CraftingRecipesSiriusB;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.recipe.CraftingRecipesVenus;
import stevekung.mods.stevecore.CreativeTabsHelper;
import stevekung.mods.stevecore.RegisterHelper;

@Mod(modid = MorePlanetsCore.MOD_ID, name = MorePlanetsCore.NAME, version = MorePlanetsCore.VERSION/*, dependencies = "required-after:GalacticraftCore; required-after:GalacticraftMars; required-after:Micdoodlecore; after:SteveCore@[1.0.3,); after:Forge@[10.13.2.1272,);"*/)
public class MorePlanetsCore
{
	public static final String NAME = "More Planets";
	public static final String MOD_ID = "MorePlanets";
	public static final String VERSION = MorePlanetsCore.major_version + "." + MorePlanetsCore.minor_version + "." + MorePlanetsCore.build_version;

	public static final int major_version = 2;
	public static final int minor_version = 0;
	public static final int build_version = 0;

	@SidedProxy(clientSide = "stevekung.mods.moreplanets.core.proxy.ClientProxyMP", serverSide = "stevekung.mods.moreplanets.core.proxy.CommonProxyMP")
	public static CommonProxyMP proxy;

	@Instance(MorePlanetsCore.MOD_ID)
	public static MorePlanetsCore instance;

	public static CreativeTabs mpBlocksTab;
	public static CreativeTabs mpItemsTab;
	public static CreativeTabs mpToolsTab;
	public static CreativeTabs mpArmorTab;

	public static SimpleNetworkWrapper simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("MorePlanets");

	//	public static Planet siriusB;
	//	public static Planet diona;
	//	public static Planet polongnius;
	//	public static Planet nibiru;
	//	public static Planet fronos;
	//	public static Planet kapteynB;
	//
	//	public static Planet mercury;
	//
	//	public static Moon koentus;
	//	public static Moon phobos;
	//	public static Moon deimos;
	//	public static Moon io;
	//
	//	public static Star sirius;
	//	public static Star kapteyn;

	//	public static SolarSystem siriusSolarSystem;
	//	public static SolarSystem kapteynBSolarSystem;

	//public static Achievement findOre;

	public static SoundType soundTypeSmallSlime = new SoundType("slime", 1.0F, 1.0F)
	{
		@Override
		public String getBreakSound()
		{
			return "mob.slime.small";
		}

		@Override
		public String getStepSound()
		{
			return "mob.slime.small";
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MPBlocks.init();
		MPItems.init();
		MPArmors.init();
		MPTools.init();
		MPPotions.init();

		//		FMLCommonHandler.instance().bus().register(new SkyProviderHandlerMP());
		FMLCommonHandler.instance().bus().register(new PolongniusMeteorTickHandler());
		FMLCommonHandler.instance().bus().register(new KoentusMeteorTickHandler());
		FMLCommonHandler.instance().bus().register(new IceCrystalMeteorTickHandler());
		FMLCommonHandler.instance().bus().register(new LightningStormHandler());
		FMLCommonHandler.instance().bus().register(new MorePlanetsEvents());

		MinecraftForge.EVENT_BUS.register(new MorePlanetsEvents());
		MinecraftForge.EVENT_BUS.register(new PolongniusMeteorTickHandler());
		MinecraftForge.EVENT_BUS.register(new KoentusMeteorTickHandler());
		MinecraftForge.EVENT_BUS.register(new IceCrystalMeteorTickHandler());
		MinecraftForge.EVENT_BUS.register(new LightningStormHandler());

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			MinecraftForge.EVENT_BUS.register(new MainMenuEventHandlerMP());
		}
		new ConfigManagerMP(new File(event.getModConfigurationDirectory(), "MorePlanets.cfg"));
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//MPPlanets.init();
		MPEntities.init();
		TreeCapitatorIntegrationMP.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiEventHandler());

		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			RegisterHelper.registerBuiltInBlocks(DionaBlocks.diona_ancient_chest, PolongniusBlocks.polongnius_ancient_chest, NibiruBlocks.nibiru_ancient_chest, KoentusBlocks.koentus_ancient_chest, FronosBlocks.fronos_ancient_chest, FronosBlocks.space_oyster_open, FronosBlocks.space_oyster_close, FronosBlocks.cavern_oyster_open, FronosBlocks.cavern_oyster_close,
					KapteynBBlocks.kapteyn_b_ancient_chest, SiriusBBlocks.sirius_b_ancient_chest, MercuryBlocks.mercury_ancient_chest, VenusBlocks.venus_ancient_chest, PlutoBlocks.pluto_ancient_chest);
		}

		MorePlanetsCore.mpBlocksTab = new CreativeTabsHelper("MorePlanetsBlocks", new ItemStack(MercuryBlocks.mercury_block, 1, 11));
		MorePlanetsCore.mpItemsTab = new CreativeTabsHelper("MorePlanetsItems", new ItemStack(DionaItems.laser_gun));
		MorePlanetsCore.mpToolsTab = new CreativeTabsHelper("MorePlanetsTools", new ItemStack(KapteynBToolsItems.uranium_pickaxe));
		MorePlanetsCore.mpArmorTab = new CreativeTabsHelper("MorePlanetsArmor", new ItemStack(FronosArmorItems.iridium_helmet));

		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier4Rocket());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier4RocketNoFlag());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier5Rocket());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier5RocketNoFlag());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier6Rocket());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier6RocketNoFlag());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier7Rocket());
		//		SchematicRegistry.registerSchematicRecipe(new SchematicTier8Rocket());

		if (ConfigManagerMP.enableRocketWithThaiFlag)
		{
			//			GalacticraftRegistry.addDungeonLoot(1, new ItemStack(DionaItems.tier4_rocket_schematic, 1, 0));
			//			GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.tier5_rocket_schematic, 1, 0));
			//			GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.tier6_rocket_schematic, 1, 0));
		}

		//		GalacticraftRegistry.addDungeonLoot(1, new ItemStack(DionaItems.tier4_rocket_schematic, 1, 1));
		//		GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.tier5_rocket_schematic, 1, 1));
		//		GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.tier6_rocket_schematic, 1, 1));
		//		GalacticraftRegistry.addDungeonLoot(5, new ItemStack(FronosItems.tier7_rocket_schematic, 1, 0));

		/*findOre = new Achievement("achievement.gc.mineOre", "gc.mineOre", 0, 0, new ItemStack(GCBlocks.basicBlock, 1, 6), null).registerStat();
		AchievementPage.registerAchievementPage(new AchievementPage("Galacticraft", new Achievement[] { findOre }));*/
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		MorePlanetsCore.proxy.registerRenderer();
		GameRegistry.registerFuelHandler(new FurnaceFuelMP());

		MPTileEntities.init();

		CraftingRecipesDiona.loadRecipes();
		CraftingRecipesPolongnius.loadRecipes();
		CraftingRecipesNibiru.loadRecipes();
		CraftingRecipesKoentus.loadRecipes();
		CraftingRecipesFronos.loadRecipes();
		CraftingRecipesKapteynB.loadRecipes();
		CraftingRecipesSiriusB.loadRecipes();

		CraftingRecipesMercury.loadRecipes();
		CraftingRecipesVenus.loadRecipes();
		CraftingRecipesPluto.loadRecipes();
		CraftingRecipesPhobos.loadRecipes();
		CraftingRecipesDeimos.loadRecipes();
		CraftingRecipesIo.loadRecipes();

		//		CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, ConfigManagerCore.quickMode ? 2 : 1, 0), new ItemStack[] { new ItemStack(PolongniusItems.polongnius_item, 1, 1), new ItemStack(GCItems.basicItem, 1, 2), new ItemStack(GCItems.basicItem, 1, 2), new ItemStack(Items.redstone), new ItemStack(Items.repeater) });
		//		CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, 9, 1), new ItemStack[] { new ItemStack(Items.diamond), new ItemStack(GCItems.basicItem, 1, 2), new ItemStack(GCItems.basicItem, 1, 2), new ItemStack(Items.redstone), new ItemStack(PolongniusItems.polongnius_item, 1, 1) });

		//		CompressorRecipesMP.registerCompressorRecipes();

		DispenserRegistryMP.initRegistry();

		MorePlanetsCore.simpleNetworkWrapper.registerMessage(MessageHandlerOnServer.class, MeteorMessageToServer.class, 35, Side.SERVER);
		MorePlanetsCore.simpleNetworkWrapper.registerMessage(MessageHandlerOnClient.class, NetworkMessageToClient.class, 63, Side.CLIENT);
	}

	@EventHandler
	public void serverInit(FMLServerStartedEvent event)
	{
		ThreadVersionCheckMP.startCheck();
	}

	public static void info(String message)
	{
		FMLRelaunchLog.log("More Planets", Level.INFO, message);
	}

	public static void severe(String message)
	{
		FMLRelaunchLog.log("More Planets", Level.ERROR, message);
	}
}