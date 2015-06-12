/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.inventory.slot;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.common.recipe.CandyExtractorRecipes;

public class SlotCandyExtractorOutput extends Slot
{
	private EntityPlayer thePlayer;
	private int field_75228_b;

	public SlotCandyExtractorOutput(EntityPlayer player, IInventory inv, int slotIndex, int xPosition, int yPosition)
	{
		super(inv, slotIndex, xPosition, yPosition);
		this.thePlayer = player;
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return false;
	}

	@Override
	public ItemStack decrStackSize(int amount)
	{
		if (this.getHasStack())
		{
			this.field_75228_b += Math.min(amount, this.getStack().stackSize);
		}
		return super.decrStackSize(amount);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
	{
		this.onCrafting(itemStack);
		super.onPickupFromSlot(player, itemStack);
	}

	@Override
	protected void onCrafting(ItemStack itemStack, int amount)
	{
		this.field_75228_b += amount;
		this.onCrafting(itemStack);
	}

	@Override
	protected void onCrafting(ItemStack itemStack)
	{
		itemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);

		if (!this.thePlayer.worldObj.isRemote)
		{
			int i = this.field_75228_b;
			float f = CandyExtractorRecipes.instance().getExtractingExperience(itemStack);
			int j;

			if (f == 0.0F)
			{
				i = 0;
			}
			else if (f < 1.0F)
			{
				j = MathHelper.floor_float(i * f);

				if (j < MathHelper.ceiling_float_int(i * f) && Math.random() < i * f - j)
				{
					++j;
				}
				i = j;
			}

			while (i > 0)
			{
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, j));
			}
		}
		this.field_75228_b = 0;
	}
}