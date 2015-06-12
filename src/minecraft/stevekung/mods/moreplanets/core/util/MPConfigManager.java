/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class MPConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Blocks**/
	public static int idBlockStoneDoubleSlab;
	public static int idBlockStoneSingleSlab;
	public static int idBlockStoneDoubleSlab2;
	public static int idBlockStoneSingleSlab2;
	public static int idBlockWoodDoubleSlab;
	public static int idBlockWoodSingleSlab;
	public static int idBlockTinStairs;
	public static int idBlockTin2Stairs;
	public static int idBlockMoonStairs;
	public static int idBlockMarsStairs;
	public static int idBlockWall;

	public MPConfigManager(File file)
	{
		if (!MPConfigManager.loaded)
		{
			MPConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			MPConfigManager.configuration.load();

			MPConfigManager.idBlockStoneDoubleSlab = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Stone Double Slab", 980).getInt(980);
			MPConfigManager.idBlockStoneSingleSlab = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Stone Half Slab", 981).getInt(981);
			MPConfigManager.idBlockStoneDoubleSlab2 = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Stone Double Slab 2", 982).getInt(982);
			MPConfigManager.idBlockStoneSingleSlab2 = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Stone Half Slab 2", 983).getInt(983);
			MPConfigManager.idBlockWoodDoubleSlab = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Wood Double Slab", 984).getInt(984);
			MPConfigManager.idBlockWoodSingleSlab = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Wood Half Slab", 985).getInt(985);
			MPConfigManager.idBlockTinStairs = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Tin Stairs", 986).getInt(986);
			MPConfigManager.idBlockTin2Stairs = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Tin Stairs 2", 987).getInt(987);
			MPConfigManager.idBlockMoonStairs = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Moon Stone Stairs", 988).getInt(988);
			MPConfigManager.idBlockMarsStairs = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Mars Stone Stairs", 989).getInt(989);
			MPConfigManager.idBlockWall = MPConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Block Wall", 990).getInt(990);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's has a problem loading it's configuration");
		}

		finally
		{
			if (MPConfigManager.configuration.hasChanged())
			{
				MPConfigManager.configuration.save();
			}
			MPConfigManager.loaded = true;
		}
	}
}