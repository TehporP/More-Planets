/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class KoentusConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Dimensions**/
	public static int idDimensionKoentus;

	// BLOCK
	public static int idKoentusBlock;
	public static int idKoentusIce;
	public static int idKoentusIceGlow;
	public static int idKoentusCobblestoneStairs;
	public static int idKoentusSludge;
	public static int idKoentusTreasureChest;
	public static int idKoentusMeteor;
	public static int idKoentusEgg;
	public static int idKoentusRock;
	public static int idWhiteCrystalTorch;
	public static int idNitrogenFluid;

	// ITEMS
	public static int idItemKoentus;
	public static int idItemKoentusDungeonKey;
	public static int idItemNitrogenCanister;
	public static int idItemNitrogenExtractor;

	/**@Generals**/
	public static boolean generateOtherMods;
	public static boolean disableKoentusVillageGen;
	public static boolean enableTinGen;

	/**@Entities**/
	public static int idKoentusVillager;
	public static int idEledos;

	public KoentusConfigManager(File file)
	{
		if (!KoentusConfigManager.loaded)
		{
			KoentusConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			KoentusConfigManager.configuration.load();

			/**@Dimensions**/
			KoentusConfigManager.idDimensionKoentus = KoentusConfigManager.configuration.get("Dimensions", "Koentus Dimension", -11).getInt(-11);

			// BLOCK
			KoentusConfigManager.idKoentusBlock = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Block", 1140).getInt(1140);
			KoentusConfigManager.idKoentusTreasureChest = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Treasure Chest", 1141).getInt(1141);
			KoentusConfigManager.idKoentusIce = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Ice", 1142).getInt(1142);
			KoentusConfigManager.idKoentusIceGlow = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Ice Glow", 1143).getInt(1143);
			KoentusConfigManager.idKoentusMeteor = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Fallen Koentus Meteor", 1144).getInt(1144);
			KoentusConfigManager.idKoentusCobblestoneStairs = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Stairs", 1145).getInt(1145);
			KoentusConfigManager.idKoentusSludge = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Sludge", 1146).getInt(1146);
			KoentusConfigManager.idKoentusEgg = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Egg", 1147).getInt(1147);
			KoentusConfigManager.idKoentusRock = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Koentus Rock", 1148).getInt(1148);
			KoentusConfigManager.idWhiteCrystalTorch = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "White Crystal Torch", 1149).getInt(1149);
			KoentusConfigManager.idNitrogenFluid = KoentusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nitrogen Fluid", 1150).getInt(1150);

			// ITEM
			KoentusConfigManager.idItemKoentus = KoentusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Koentus Basic Item", 4948).getInt(4948);
			KoentusConfigManager.idItemKoentusDungeonKey = KoentusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Koentus Dungeon Key", 4949).getInt(4949);
			KoentusConfigManager.idItemNitrogenCanister = KoentusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Nitrogen Canister", 4950).getInt(4950);
			KoentusConfigManager.idItemNitrogenExtractor = KoentusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Nitrogen Extractor", 4951).getInt(4951);

			/**@Entities**/
			KoentusConfigManager.idKoentusVillager = KoentusConfigManager.configuration.get("Entities", "Koentus Villager", 111).getInt(111);
			KoentusConfigManager.idEledos = KoentusConfigManager.configuration.get("Entities", "Eledos", 112).getInt(112);

			/**@Generals**/
			KoentusConfigManager.generateOtherMods = KoentusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Koentus", false).getBoolean(false);
			KoentusConfigManager.disableKoentusVillageGen = KoentusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Koentus Village Gen", false).getBoolean(false);
			KoentusConfigManager.enableTinGen = KoentusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Tin Ore Gen on Koentus", true).getBoolean(true);
		}
		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's : Koentus has a problem loading it's configuration");
		}
		finally
		{
			if (KoentusConfigManager.configuration.hasChanged())
			{
				KoentusConfigManager.configuration.save();
			}

			KoentusConfigManager.loaded = true;
		}
	}
}
