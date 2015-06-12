/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockCandyExtractor;
import stevekung.mods.moreplanets.fronos.recipe.FronosCandyExtractorRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class FronosTileEntityCandyExtractor extends TileEntity implements ISidedInventory
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};

	private ItemStack[] extractorItemStacks = new ItemStack[3];

	public int extractorTime;
	public int currentItemExtractTime;

	public int candyCookTime;

	@Override
	public int getSizeInventory()
	{
		return this.extractorItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.extractorItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.extractorItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.extractorItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.extractorItemStacks[par1];
				this.extractorItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.extractorItemStacks[par1].splitStack(par2);

				if (this.extractorItemStacks[par1].stackSize == 0)
				{
					this.extractorItemStacks[par1] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.extractorItemStacks[par1] != null)
		{
			ItemStack itemstack = this.extractorItemStacks[par1];
			this.extractorItemStacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.extractorItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("container.candy.extractor.name");
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	public void setGuiDisplayName(String par1Str)
	{
		return;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.extractorItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.extractorItemStacks.length)
			{
				this.extractorItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.extractorTime = par1NBTTagCompound.getShort("ExtractTime");
		this.candyCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemExtractTime = getItemExtractTime(this.extractorItemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("ExtractTime", (short)this.extractorTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.candyCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.extractorItemStacks.length; ++i)
		{
			if (this.extractorItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.extractorItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	public int getCookProgressScaled(int par1)
	{
		return this.candyCookTime * par1 / 200;
	}

	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemExtractTime == 0)
		{
			this.currentItemExtractTime = 200;
		}

		return this.extractorTime * par1 / this.currentItemExtractTime;
	}

	public boolean isBurning()
	{
		return this.extractorTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.extractorTime > 0;
		boolean flag1 = false;

		if (this.extractorTime > 0)
		{
			--this.extractorTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.extractorTime == 0 && this.canExtract())
			{
				this.currentItemExtractTime = this.extractorTime = getItemExtractTime(this.extractorItemStacks[1]);

				if (this.extractorTime > 0)
				{
					flag1 = true;

					if (this.extractorItemStacks[1] != null)
					{
						--this.extractorItemStacks[1].stackSize;

						if (this.extractorItemStacks[1].stackSize == 0)
						{
							this.extractorItemStacks[1] = this.extractorItemStacks[1].getItem().getContainerItemStack(this.extractorItemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canExtract())
			{
				++this.candyCookTime;

				if (this.candyCookTime == 200)
				{
					this.candyCookTime = 0;
					this.extractItem();
					flag1 = true;
				}
			}
			else
			{
				this.candyCookTime = 0;
			}

			if (flag != this.extractorTime > 0)
			{
				flag1 = true;
				FronosBlockCandyExtractor.updateExtractorBlockState(this.extractorTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
		{
			this.onInventoryChanged();
		}
	}

	private boolean canExtract()
	{
		if (this.extractorItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = FronosCandyExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0]);
			if (itemstack == null) {
				return false;
			}
			if (this.extractorItemStacks[2] == null) {
				return true;
			}
			if (!this.extractorItemStacks[2].isItemEqual(itemstack)) {
				return false;
			}
			int result = this.extractorItemStacks[2].stackSize + itemstack.stackSize;
			return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
	}

	public void extractItem()
	{
		if (this.canExtract())
		{
			ItemStack itemstack = FronosCandyExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0]);

			if (this.extractorItemStacks[2] == null)
			{
				this.extractorItemStacks[2] = itemstack.copy();
			}
			else if (this.extractorItemStacks[2].isItemEqual(itemstack))
			{
				this.extractorItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.extractorItemStacks[0].stackSize;

			if (this.extractorItemStacks[0].stackSize <= 0)
			{
				this.extractorItemStacks[0] = null;
			}
		}
	}

	public static int getItemExtractTime(ItemStack par0ItemStack)
	{
		if (par0ItemStack == null)
		{
			return 0;
		}
		else
		{
			int i = par0ItemStack.getItem().itemID;
			Item item = par0ItemStack.getItem();

			if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
			{
				Block block = Block.blocksList[i];

				if (block == Block.woodSingleSlab)
				{
					return 150;
				}

				if (block.blockMaterial == Material.wood)
				{
					return 300;
				}

				if (block == Block.coalBlock)
				{
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) {
				return 200;
			}
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) {
				return 200;
			}
			if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) {
				return 200;
			}
			if (i == Item.stick.itemID) {
				return 100;
			}
			if (i == Item.coal.itemID) {
				return 1600;
			}
			if (i == Item.bucketLava.itemID) {
				return 20000;
			}
			if (i == Block.sapling.blockID) {
				return 100;
			}
			if (i == Item.blazeRod.itemID) {
				return 2400;
			}
			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemExtractTime(par0ItemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : par1 == 1 ? isItemFuel(par2ItemStack) : true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_bottom : par1 == 1 ? slots_top : slots_sides;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}
}