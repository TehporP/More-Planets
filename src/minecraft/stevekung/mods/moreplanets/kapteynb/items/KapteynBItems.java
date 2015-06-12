/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.util.KapteynBConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class KapteynBItems
{
	public static Item kapteynBBasicItem;
	public static Item kapteynBDungeonKey;
	public static Item uraniumBattery;
	public static Item frozenWaterBucket;

	public static void initItems()
	{
		KapteynBItems.kapteynBBasicItem = new KapteynBItem(KapteynBConfigManager.idItemKapteynB).setUnlocalizedName("kapteynBItems");
		KapteynBItems.kapteynBDungeonKey = new KapteynBItemDungeonKey(KapteynBConfigManager.idItemKapteynBDungeonKey).setUnlocalizedName("kapteynBDungeonKey");
		KapteynBItems.uraniumBattery = new KapteynBItemUraniumBattery(KapteynBConfigManager.idItemUraniumBattery).setUnlocalizedName("uraniumBattery");
		KapteynBItems.frozenWaterBucket = new KapteynBItemBucket(KapteynBConfigManager.idItemFrozenWaterBucket).setUnlocalizedName("bucket");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(kapteynBBasicItem);
		registerItem(kapteynBDungeonKey);
		registerItem(uraniumBattery);
		registerItem(frozenWaterBucket);

		FluidContainerRegistry.registerFluidContainer(KapteynBBlocks.frozenWaterFluid, new ItemStack(KapteynBItems.frozenWaterBucket, 1, 0), new ItemStack(Item.bucketEmpty));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}