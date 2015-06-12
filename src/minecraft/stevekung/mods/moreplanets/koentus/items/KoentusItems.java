/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class KoentusItems
{
	public static Item koentusBasicItem;
	public static Item koentusDungeonKey;

	public static void initItems()
	{
		KoentusItems.koentusBasicItem = new KoentusItemBasic(KoentusConfigManager.idItemKoentus).setUnlocalizedName("koentusItems");
		KoentusItems.koentusDungeonKey = new KoentusItemDungeonKey(KoentusConfigManager.idItemKoentusDungeonKey).setUnlocalizedName("koentusDungeonKey");

		fluidContainerRegister();
		registerItems();
	}

	public static void fluidContainerRegister()
	{
		FluidContainerRegistry.registerFluidContainer(KoentusBlocks.koentusSludge, new ItemStack(DionaItems.baronsiumBucket, 1, 2), new ItemStack(DionaItems.baronsiumBucket, 1, 0));
	}

	public static void registerItems()
	{
		registerItem(koentusBasicItem);
		registerItem(koentusDungeonKey);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}