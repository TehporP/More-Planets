/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class DionaItems
{
	public static Item dionaBasicItem;
	public static Item thailandFlag;
	public static Item rocketT3;
	public static Item dionaKeyTreasure;
	public static Item schematicT3;
	public static Item rocketPartT3;
	public static Item baronsiumBucket;
	public static Item laserGun;
	public static Item laserCharge;

	public static void initItems()
	{
		DionaItems.dionaBasicItem = new DionaItemBasic(DionaConfigManager.idItemBasicDiona).setUnlocalizedName("dionaBasicItem");
		DionaItems.thailandFlag = new DionaItemFlag(DionaConfigManager.idItemThaiFlag).setUnlocalizedName("thailandFlag");
		DionaItems.rocketT3 = new DionaItemSpaceshipTier3(DionaConfigManager.idItemSpaceshipTier3).setUnlocalizedName("spaceshipTier3");
		DionaItems.dionaKeyTreasure = new DionaItemDungeonKey(DionaConfigManager.idItemDionaKeyTreasure).setUnlocalizedName("dionaDungeonKey");
		DionaItems.schematicT3 = new DionaItemSchematic(DionaConfigManager.idItemSchematicT3).setUnlocalizedName("schematicT3");
		DionaItems.rocketPartT3 = new DionaItemRocketPartT3(DionaConfigManager.idItemRocketT3Part).setUnlocalizedName("rocketPartT3");
		DionaItems.baronsiumBucket = new DionaItemBucket(DionaConfigManager.idItemBaronsiumBucket).setUnlocalizedName("bs");
		DionaItems.laserGun = new DionaItemLaserGun(DionaConfigManager.idItemLaserGun).setUnlocalizedName("laserGun");
		DionaItems.laserCharge = new DionaItemLaserCharge(DionaConfigManager.idItemLaserCharge).setUnlocalizedName("lc");

		fluidContainerRegister();
		registerItems();
	}

	public static void fluidContainerRegister()
	{
		FluidContainerRegistry.registerFluidContainer(PolongniusBlocks.cheeseOfMilkFluid, new ItemStack(DionaItems.baronsiumBucket, 1, 1), new ItemStack(DionaItems.baronsiumBucket, 1, 0));
	}

	public static void registerItems()
	{
		registerItem(dionaBasicItem);
		registerItem(thailandFlag);
		registerItem(rocketT3);
		registerItem(dionaKeyTreasure);
		registerItem(schematicT3);
		registerItem(rocketPartT3);
		registerItem(baronsiumBucket);
		registerItem(laserGun);
		registerItem(laserCharge);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}