/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import cpw.mods.fml.common.IFuelHandler;

public class MPFurnaceFuel implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		return addFuel(fuel.itemID, fuel.getItemDamage());
	}

	private static int addFuel(int par1, int par2)
	{
		if (par1 == NibiruBlocks.nibiruBlockSapling.blockID || par1 == FronosBlocks.fronosBlockSapling.blockID)
		{
			return 100;
		}
		return 0;
	}
}