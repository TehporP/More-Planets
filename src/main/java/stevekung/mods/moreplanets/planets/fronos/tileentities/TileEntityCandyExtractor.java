/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.recipe.CandyExtractorRecipes;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class TileEntityCandyExtractor extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	private static int[] slotsTop = new int[] {0};
	private static int[] slotsBottom = new int[] {2, 1};
	private static int[] slotsSides = new int[] {1};
	private ItemStack[] itemStacks = new ItemStack[3];
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;

	@Override
	public int getSizeInventory()
	{
		return this.itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.itemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.itemStacks[index] != null)
		{
			ItemStack itemstack;

			if (this.itemStacks[index].stackSize <= count)
			{
				itemstack = this.itemStacks[index];
				this.itemStacks[index] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.itemStacks[index].splitStack(count);

				if (this.itemStacks[index].stackSize == 0)
				{
					this.itemStacks[index] = null;
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
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (this.itemStacks[index] != null)
		{
			ItemStack itemstack = this.itemStacks[index];
			this.itemStacks[index] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		boolean flag = stack != null && stack.isItemEqual(this.itemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.itemStacks[index]);
		this.itemStacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
		if (index == 0 && !flag)
		{
			this.totalCookTime = this.func_174904_a(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public String getName()
	{
		return EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("container.candy.extractor.name");
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.itemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.itemStacks.length)
			{
				this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		this.burnTime = nbt.getShort("BurnTime");
		this.cookTime = nbt.getShort("CookTime");
		this.totalCookTime = nbt.getShort("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime(this.itemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short)this.burnTime);
		nbt.setShort("CookTime", (short)this.cookTime);
		nbt.setShort("CookTimeTotal", (short)this.totalCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i)
		{
			if (this.itemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.itemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isBurning()
	{
		return this.burnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inv)
	{
		return inv.getField(0) > 0;
	}

	@Override
	public void update()
	{
		boolean flag = this.isBurning();
		boolean flag1 = false;

		if (this.isBurning())
		{
			--this.burnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (!this.isBurning() && (this.itemStacks[1] == null || this.itemStacks[0] == null))
			{
				if (!this.isBurning() && this.cookTime > 0)
				{
					this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
				}
			}
			else
			{
				if (!this.isBurning() && this.canSmelt())
				{
					this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.itemStacks[1]);

					if (this.isBurning())
					{
						flag1 = true;

						if (this.itemStacks[1] != null)
						{
							--this.itemStacks[1].stackSize;

							if (this.itemStacks[1].stackSize == 0)
							{
								this.itemStacks[1] = this.itemStacks[1].getItem().getContainerItem(this.itemStacks[1]);
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt())
				{
					++this.cookTime;

					if (this.cookTime == this.totalCookTime)
					{
						this.cookTime = 0;
						this.totalCookTime = this.func_174904_a(this.itemStacks[0]);
						this.smeltItem();
						flag1 = true;
					}
				}
				else
				{
					this.cookTime = 0;
				}
			}

			if (flag != this.isBurning())
			{
				flag1 = true;
				BlockCandyExtractor.setState(this.isBurning(), this.worldObj, this.pos);
			}
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	public int func_174904_a(ItemStack itemStack)
	{
		return 200;
	}

	private boolean canSmelt()
	{
		if (this.itemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = CandyExtractorRecipes.instance().getExtractingResult(this.itemStacks[0]);

			if (itemstack == null)
			{
				return false;
			}
			if (this.itemStacks[2] == null)
			{
				return true;
			}
			if (!this.itemStacks[2].isItemEqual(itemstack))
			{
				return false;
			}
			int result = this.itemStacks[2].stackSize + itemstack.stackSize;
			return result <= this.getInventoryStackLimit() && result <= this.itemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = CandyExtractorRecipes.instance().getExtractingResult(this.itemStacks[0]);

			if (this.itemStacks[2] == null)
			{
				this.itemStacks[2] = itemstack.copy();
			}
			else if (this.itemStacks[2].getItem() == itemstack.getItem())
			{
				this.itemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.itemStacks[0].stackSize;

			if (this.itemStacks[0].stackSize <= 0)
			{
				this.itemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack itemStack)
	{
		if (itemStack == null)
		{
			return 0;
		}
		else
		{
			if (itemStack.getItem() == FronosItems.fronos_item && itemStack.getItemDamage() == 1)
			{
				return 500;
			}
		}
		return 0;
	}

	public static boolean isItemFuel(ItemStack itemStack)
	{
		return getItemBurnTime(itemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return slot == 2 ? false : slot == 1 ? TileEntityCandyExtractor.isItemFuel(itemStack) : true;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? slotsBottom : side == EnumFacing.UP ? slotsTop : slotsSides;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack itemStack, EnumFacing direction)
	{
		return true;
	}

	@Override
	public String getGuiID()
	{
		return "moreplanets:candy_extractor";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
	{
		return new ContainerCandyExtractor(playerInventory, this);
	}

	@Override
	public int getField(int id)
	{
		switch (id)
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value)
	{
		switch (id)
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount()
	{
		return 4;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.itemStacks.length; ++i)
		{
			this.itemStacks[i] = null;
		}
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.getNbtCompound());
	}
}