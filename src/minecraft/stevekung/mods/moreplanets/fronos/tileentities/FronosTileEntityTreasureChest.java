/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.tileentities;

import java.util.Iterator;
import java.util.List;

import micdoodle8.mods.galacticraft.api.item.IKeyable;
import micdoodle8.mods.galacticraft.core.GCCoreAnnotations.NetworkedField;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.GCCorePacketHandlerServer.EnumPacketServer;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityAdvanced;
import micdoodle8.mods.galacticraft.core.util.PacketUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockT9TreasureChest;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public class FronosTileEntityTreasureChest extends GCCoreTileEntityAdvanced implements IInventory, IKeyable
{
	private ItemStack[] chestContents = new ItemStack[36];

	public float lidAngle;
	public float prevLidAngle;
	public int numUsingPlayers;
	private int ticksSinceSync;

	@NetworkedField(targetSide = Side.CLIENT)
	public boolean locked = true;

	public int tier = 3;//TODO for 9

	public FronosTileEntityTreasureChest()
	{
		this(3);//TODO for 9
	}

	public FronosTileEntityTreasureChest(int tier)
	{
		this.tier = tier;
	}

	@Override
	public int getSizeInventory()
	{
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.chestContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack itemstack;

			if (this.chestContents[par1].stackSize <= par2)
			{
				itemstack = this.chestContents[par1];
				this.chestContents[par1] = null;
				this.onInventoryChanged();
				return itemstack;
			}
			else
			{
				itemstack = this.chestContents[par1].splitStack(par2);

				if (this.chestContents[par1].stackSize == 0)
				{
					this.chestContents[par1] = null;
				}
				this.onInventoryChanged();
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
		if (this.chestContents[par1] != null)
		{
			final ItemStack itemstack = this.chestContents[par1];
			this.chestContents[par1] = null;
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
		this.chestContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.locked = nbt.getBoolean("isLocked");
		this.tier = nbt.getInteger("tier");
		final NBTTagList nbttaglist = nbt.getTagList("Items");
		this.chestContents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			final NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			final int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length)
			{
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("isLocked", this.locked);
		nbt.setInteger("tier", this.tier);
		final NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.chestContents.length; ++i)
		{
			if (this.chestContents[i] != null)
			{
				final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound1);
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

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		++this.ticksSinceSync;
		float f;

		if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
		{
			this.numUsingPlayers = 0;
			f = 5.0F;
			final List<?> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - f, this.yCoord - f, this.zCoord - f, this.xCoord + 1 + f, this.yCoord + 1 + f, this.zCoord + 1 + f));
			final Iterator<?> iterator = list.iterator();

			while (iterator.hasNext())
			{
				final EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerChest)
				{
					final IInventory iinventory = ((ContainerChest) entityplayer.openContainer).getLowerChestInventory();

					if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest) iinventory).isPartOfLargeChest(this))
					{
						++this.numUsingPlayers;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		f = 0.05F;

		if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
		{
			if (this.numUsingPlayers > 0)
			{
				this.lidAngle += f;
			}
			else
			{
				this.lidAngle -= f;
			}

			if (this.lidAngle > 1.0F)
			{
				this.lidAngle = 1.0F;
			}

			if (this.lidAngle < 0.0F)
			{
				this.lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2)
	{
		if (par1 == 1)
		{
			this.numUsingPlayers = par2;
			return true;
		}
		else
		{
			return super.receiveClientEvent(par1, par2);
		}
	}

	@Override
	public void openChest()
	{
		if (this.numUsingPlayers < 0)
		{
			this.numUsingPlayers = 0;
		}

		++this.numUsingPlayers;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
	}

	@Override
	public void closeChest()
	{
		if (this.getBlockType() != null && this.getBlockType() instanceof FronosBlockT9TreasureChest)
		{
			--this.numUsingPlayers;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
		}
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
	}

	@Override
	public String getInvName()
	{
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT ? StatCollector.translateToLocal("container.fronos.treasurechest.name") : StatCollector.translateToLocal("container.fronos.treasurechest.name");
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}

	@Override
	public int getTierOfKeyRequired()
	{
		return this.tier;
	}

	@Override
	public boolean onValidKeyActivated(EntityPlayer player, ItemStack key, int face)
	{
		if (this.locked)
		{
			this.locked = false;

			if (this.worldObj.isRemote)
			{

			}
			else
			{
				if (--player.inventory.getCurrentItem().stackSize == 0)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onActivatedWithoutKey(EntityPlayer player, int face)
	{
		if (this.locked)
		{
			if (player.worldObj.isRemote)
			{
				PacketDispatcher.sendPacketToServer(PacketUtil.createPacket(GalacticraftCore.CHANNEL, EnumPacketServer.ON_FAILED_CHEST_UNLOCK, new Object[] { this.getTierOfKeyRequired() }));
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean canBreak()
	{
		return false;
	}

	@Override
	public double getPacketRange()
	{
		return 20.0D;
	}

	@Override
	public int getPacketCooldown()
	{
		return 3;
	}

	@Override
	public boolean isNetworkedTile()
	{
		return true;
	}
}