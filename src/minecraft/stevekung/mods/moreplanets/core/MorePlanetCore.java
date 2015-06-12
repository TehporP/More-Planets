/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core;

import java.io.File;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.CircuitFabricatorRecipes;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMoon;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.core.event.MPEvents;
import stevekung.mods.moreplanets.core.gui.MPGuiHandler;
import stevekung.mods.moreplanets.core.init.MPArmor;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.init.MPEntities;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.init.MPPlanets;
import stevekung.mods.moreplanets.core.init.MPTileEntities;
import stevekung.mods.moreplanets.core.init.MPTools;
import stevekung.mods.moreplanets.core.planets.MPGalaxySirius;
import stevekung.mods.moreplanets.core.proxy.CommonProxyMP;
import stevekung.mods.moreplanets.core.recipe.MPCompressorRecipes;
import stevekung.mods.moreplanets.core.recipe.MPCraftingRecipes;
import stevekung.mods.moreplanets.core.util.MPConfigManager;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.core.util.MPFurnaceFuel;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.diona.recipe.DionaCraftingRecipes;
import stevekung.mods.moreplanets.diona.schematic.DionaSchematicRocketT3;
import stevekung.mods.moreplanets.diona.schematic.DionaSchematicRocketT3NoFlag;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import stevekung.mods.moreplanets.fronos.biomes.FronosBiomes;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.recipe.FronosCraftingRecipes;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import stevekung.mods.moreplanets.kapteynb.recipe.KapteynBCraftingRecipes;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import stevekung.mods.moreplanets.koentus.recipe.KoentusCraftingRecipes;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.nibiru.recipe.NibiruCraftingRecipes;
import stevekung.mods.moreplanets.nibiru.schematics.NibiruSchematicRocketT5;
import stevekung.mods.moreplanets.nibiru.schematics.NibiruSchematicRocketT5NoFlag;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.recipe.PolongniusCraftingRecipes;
import stevekung.mods.moreplanets.polongnius.schematics.PolongniusSchematicRocketT4;
import stevekung.mods.moreplanets.polongnius.schematics.PolongniusSchematicRocketT4NoFlag;

import com.google.common.base.Preconditions;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = MorePlanetCore.NAME, version = MorePlanetCore.VERSION, useMetadata = true, modid = MorePlanetCore.MODID, dependencies = "required-after:GalacticraftCore; required-after:GalacticraftMars; required-after:Micdoodlecore;")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MorePlanetCore
{
	public static final String NAME = "More Planets";
	public static final String MODID = "MorePlanet";
	public static final String VERSION = "0.0.5";

	@SidedProxy(clientSide = "stevekung.mods.moreplanets.core.proxy.ClientProxyMP", serverSide = "stevekung.mods.moreplanets.core.proxy.CommonProxyMP")
	public static CommonProxyMP proxy;

	@Instance(MorePlanetCore.MODID)
	public static MorePlanetCore instance;

	public static CreativeTabs morePlanetTab;

	public static IGalaxy galaxySirius = new MPGalaxySirius();

	public static IPlanet sirius;
	public static IPlanet diona;
	public static IPlanet polongnius;
	public static IPlanet nibiru;
	public static IPlanet fronos;
	public static IPlanet kapteynB;

	public static IMoon koentus;
	public static IMoon siriusB;

	public static Material koentusSludge = new MaterialLiquid(MapColor.foliageColor);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModuleDiona.preLoad(event);
		ModulePolongnius.preLoad(event);
		ModuleNibiru.preLoad(event);
		ModuleKoentus.preLoad(event);
		ModuleFronos.preLoad(event);
		ModuleKapteynB.preLoad(event);

		MinecraftForge.EVENT_BUS.register(new MPEvents());

		new MPConfigManager(new File(event.getModConfigurationDirectory(), "SteveMCCommander/MorePlanet/core.cfg"));
		new DionaConfigManager(new File(event.getModConfigurationDirectory(), "SteveMCCommander/MorePlanet/diona.cfg"));

		MPBlocks.init();
		MPItems.init();
		MPArmor.init();
		MPTools.init();
		FronosBiomes.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MorePlanetCore.morePlanetTab = new MPCreativeTab(CreativeTabs.getNextID(), MorePlanetCore.MODID, DionaItems.thailandFlag.itemID, 0);

		ModuleDiona.load(event);
		ModulePolongnius.load(event);
		ModuleNibiru.load(event);
		ModuleKoentus.load(event);
		ModuleFronos.load(event);
		ModuleKapteynB.load(event);
		MorePlanetCore.proxy.init(event);

		MPPlanets.init();
		MPEntities.init();

		SchematicRegistry.registerSchematicRecipe(new DionaSchematicRocketT3());
		SchematicRegistry.registerSchematicRecipe(new DionaSchematicRocketT3NoFlag());
		SchematicRegistry.registerSchematicRecipe(new PolongniusSchematicRocketT4());
		SchematicRegistry.registerSchematicRecipe(new PolongniusSchematicRocketT4NoFlag());
		SchematicRegistry.registerSchematicRecipe(new NibiruSchematicRocketT5());
		SchematicRegistry.registerSchematicRecipe(new NibiruSchematicRocketT5NoFlag());

		GalacticraftRegistry.addDungeonLoot(2, new ItemStack(DionaItems.schematicT3.itemID, 1, 0));
		GalacticraftRegistry.addDungeonLoot(2, new ItemStack(DionaItems.schematicT3.itemID, 1, 1));
		GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.schematicT4.itemID, 1, 0));
		GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.schematicT4.itemID, 1, 1));
		GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.schematicT5.itemID, 1, 0));
		GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.schematicT5.itemID, 1, 1));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		MPTileEntities.init();
		MPCompressorRecipes.registerCompressorRecipes();

		CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem2, 1, 0), new ItemStack[] { new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3), new ItemStack(GCCoreItems.basicItem, 1, 2), new ItemStack(GCCoreItems.basicItem, 1, 2), new ItemStack(Item.redstone), new ItemStack(Item.redstoneRepeater) });
		CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.polongniusBasicItem2, 9, 1), new ItemStack[] { new ItemStack(Item.diamond), new ItemStack(GCCoreItems.basicItem, 1, 2), new ItemStack(GCCoreItems.basicItem, 1, 2), new ItemStack(Item.redstone), new ItemStack(PolongniusItems.polongniusBasicItem, 1, 3) });

		MorePlanetCore.proxy.postInit(event);
		NetworkRegistry.instance().registerGuiHandler(this, new MPGuiHandler());

		MPCraftingRecipes.loadRecipes();
		DionaCraftingRecipes.loadRecipes();
		PolongniusCraftingRecipes.loadRecipes();
		NibiruCraftingRecipes.loadRecipes();
		KoentusCraftingRecipes.loadRecipes();
		FronosCraftingRecipes.loadRecipes();
		KapteynBCraftingRecipes.loadRecipes();

		GameRegistry.registerFuelHandler(Preconditions.checkNotNull(new MPFurnaceFuel()));
	}
}