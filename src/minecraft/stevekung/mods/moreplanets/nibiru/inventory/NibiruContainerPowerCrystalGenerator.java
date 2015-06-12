/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.inventory;

import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.api.items.IPowerCrystal;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityPowerCrystalGenerator;

public class NibiruContainerPowerCrystalGenerator extends Container
{
	private NibiruTileEntityPowerCrystalGenerator tileEntity;

	public NibiruContainerPowerCrystalGenerator(InventoryPlayer par1InventoryPlayer, NibiruTileEntityPowerCrystalGenerator tileEntity)
	{
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new SlotSpecific(tileEntity, 0, 33, 34, IPowerCrystal.class));
		int var3;

		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}

		tileEntity.playersUsing.add(par1InventoryPlayer.player);
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);
		this.tileEntity.playersUsing.remove(entityplayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot) this.inventorySlots.get(par1);

		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (par1 != 0)
			{
				if (var4.getItem() instanceof IPowerCrystal)
				{
					final boolean isCrystalItem = false;

					if (NibiruTileEntityPowerCrystalGenerator.isCrystal(var4, isCrystalItem))
					{
						if (!this.mergeItemStack(var4, 0, 1, false))
						{
							return null;
						}
					}
				}
				else if (par1 >= 28)
				{
					if (!this.mergeItemStack(var4, 1, 28, false)) {
						return null;
					}
				}
				else if (!this.mergeItemStack(var4, 28, 37, false)) {
					return null;
				}
			}
			else if (!this.mergeItemStack(var4, 1, 37, false))
			{
				return null;
			}

			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack) null);
			}
			else
			{
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize)
			{
				return null;
			}

			var3.onPickupFromSlot(par1EntityPlayer, var4);
		}
		return var2;
	}
}