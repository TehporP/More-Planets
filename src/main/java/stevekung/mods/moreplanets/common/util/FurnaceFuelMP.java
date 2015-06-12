/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.util;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

import org.apache.commons.lang3.tuple.Pair;

import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class FurnaceFuelMP implements IFuelHandler
{
	private static HashMap<Pair<Item, Integer>, Integer> fuelList = new HashMap<Pair<Item, Integer>, Integer>();

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		return getFuelValue(fuel);
	}

	public static void setFuelValues()
	{
		addFuel(NibiruBlocks.nibiru_sapling, 100);
		addFuel(KoentusBlocks.crystal_sapling, 100);
		addFuel(FronosBlocks.fronos_sapling, 100);
	}

	private static void addFuel(Item item, int metadata, int value)
	{
		fuelList.put(Pair.of(item, metadata), value);
	}

	private static void addFuel(Block block, int value)
	{
		addFuel(Item.getItemFromBlock(block), 0, value);
	}

	private static int getFuelValue(ItemStack stack)
	{
		if (stack == null || stack.getItem() == null)
		{
			return 0;
		}

		Pair<Item, Integer> pair = Pair.of(stack.getItem(), stack.getItemDamage());

		if (fuelList.containsKey(pair))
		{
			return fuelList.get(pair);
		}
		else
		{
			pair = Pair.of(stack.getItem(), 0);

			if (fuelList.containsKey(pair))
			{
				return fuelList.get(pair);
			}
		}
		return 0;
	}
}