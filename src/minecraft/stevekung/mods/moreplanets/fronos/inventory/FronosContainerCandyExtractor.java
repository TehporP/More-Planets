/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.fronos.recipe.FronosCandyExtractorRecipes;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityCandyExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosContainerCandyExtractor extends Container
{
	private FronosTileEntityCandyExtractor candyExtractor;
	private int lastCookTime;
	private int lastExtractTime;
	private int lastItemExtractTime;

	public FronosContainerCandyExtractor(InventoryPlayer par1InventoryPlayer, FronosTileEntityCandyExtractor par2TileEntityFurnace)
	{
		this.candyExtractor = par2TileEntityFurnace;
		this.addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
		this.addSlotToContainer(new Slot(par2TileEntityFurnace, 1, 56, 53));
		this.addSlotToContainer(new FronosSlotCandyExtractor(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.candyExtractor.candyCookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.candyExtractor.extractorTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.candyExtractor.currentItemExtractTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.lastCookTime != this.candyExtractor.candyCookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.candyExtractor.candyCookTime);
			}

			if (this.lastExtractTime != this.candyExtractor.extractorTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.candyExtractor.extractorTime);
			}

			if (this.lastItemExtractTime != this.candyExtractor.currentItemExtractTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.candyExtractor.currentItemExtractTime);
			}
		}

		this.lastCookTime = this.candyExtractor.candyCookTime;
		this.lastExtractTime = this.candyExtractor.extractorTime;
		this.lastItemExtractTime = this.candyExtractor.currentItemExtractTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.candyExtractor.candyCookTime = par2;
		}

		if (par1 == 1)
		{
			this.candyExtractor.extractorTime = par2;
		}

		if (par1 == 2)
		{
			this.candyExtractor.currentItemExtractTime = par2;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.candyExtractor.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				if (FronosCandyExtractorRecipes.extracting().getExtractingResult(itemstack1) != null)
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (FronosTileEntityCandyExtractor.isItemFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return null;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}
