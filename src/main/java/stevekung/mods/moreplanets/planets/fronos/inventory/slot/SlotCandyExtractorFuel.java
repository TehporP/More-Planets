/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

public class SlotCandyExtractorFuel extends Slot
{
	public SlotCandyExtractorFuel(IInventory inventory, int slotIndex, int xPosition, int yPosition)
	{
		super(inventory, slotIndex, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return TileEntityCandyExtractor.isItemFuel(itemStack);
	}

	@Override
	public int getItemStackLimit(ItemStack itemStack)
	{
		return super.getItemStackLimit(itemStack);
	}
}