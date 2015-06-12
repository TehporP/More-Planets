/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityAncientChest;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityDungeonSpawner;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityTreasureChest;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityCandyExtractor;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOyster;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOysterClose;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityTreasureChest;
import stevekung.mods.moreplanets.kapteynb.tileentities.KapteynBTileEntityTreasureChest;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityEledosEgg;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityTreasureChest;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityAncientChest;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityDungeonSpawner;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityPowerCrystalGenerator;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityTreasureChest;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityAncientChest;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityDungeonSpawner;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityTreasureChest;
import cpw.mods.fml.common.registry.GameRegistry;

public class MPTileEntities
{
	public static void init()
	{
		registerTileEntities();
	}

	private static void registerTileEntities()
	{
		/**@Diona**/
		GameRegistry.registerTileEntity(DionaTileEntityTreasureChest.class, "DionaTreasureChest");
		GameRegistry.registerTileEntity(DionaTileEntityDungeonSpawner.class, "DionaDungeonSpawner");
		GameRegistry.registerTileEntity(DionaTileEntityAncientChest.class, "DionaAncientChest");

		/**@Polongnius**/
		GameRegistry.registerTileEntity(PolongniusTileEntityTreasureChest.class, "PolongniusTreasureChest");
		GameRegistry.registerTileEntity(PolongniusTileEntitySolar.class, "PolongniusSolarPanel");
		GameRegistry.registerTileEntity(PolongniusTileEntityAncientChest.class, "PolongniusAncientChest");
		GameRegistry.registerTileEntity(PolongniusTileEntityDungeonSpawner.class, "PolongniusDungeonSpawner");

		/**@Nibiru**/
		GameRegistry.registerTileEntity(NibiruTileEntityTreasureChest.class, "NibiruTreasureChest");
		GameRegistry.registerTileEntity(NibiruTileEntityAncientChest.class, "NibiruAncientChest");
		GameRegistry.registerTileEntity(NibiruTileEntityPowerCrystalGenerator.class, "PowerCrystalGenerator");
		GameRegistry.registerTileEntity(NibiruTileEntityDungeonSpawner.class, "NibiruDungeonSpawner");

		/**@Koentus**/
		GameRegistry.registerTileEntity(KoentusTileEntityTreasureChest.class, "KoentusTreasureChest");
		GameRegistry.registerTileEntity(KoentusTileEntityEledosEgg.class, "KoentusEledosEgg");

		/**@Fronos**/
		GameRegistry.registerTileEntity(FronosTileEntitySpaceOyster.class, "SpaceOyster");
		GameRegistry.registerTileEntity(FronosTileEntitySpaceOysterClose.class, "SpaceOysterClose");
		GameRegistry.registerTileEntity(FronosTileEntityTreasureChest.class, "FronosTreasureChest");
		GameRegistry.registerTileEntity(FronosTileEntityCandyExtractor.class, "CandyExtractor");

		/**@KapteynB**/
		GameRegistry.registerTileEntity(KapteynBTileEntityTreasureChest.class, "KapteynBTreasureChest");
	}
}