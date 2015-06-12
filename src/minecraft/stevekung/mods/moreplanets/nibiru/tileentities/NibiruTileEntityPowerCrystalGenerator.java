/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.tileentities;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import micdoodle8.mods.galacticraft.api.transmission.ElectricityPack;
import micdoodle8.mods.galacticraft.api.transmission.compatibility.NetworkConfigHandler;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConductor;
import micdoodle8.mods.galacticraft.api.transmission.tile.IElectrical;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.GCCoreAnnotations.NetworkedField;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlockMachine;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityUniversalElectrical;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.api.items.IPowerCrystal;
import cpw.mods.fml.relauncher.Side;

public class NibiruTileEntityPowerCrystalGenerator extends GCCoreTileEntityUniversalElectrical implements IElectrical, IInventory, ISidedInventory, IPacketReceiver
{
	public static final float MAX_GENERATE_WATTS = 1.0f;
	public static final float MIN_GENERATE_WATTS = 0.5f;
	private static final float BASE_ACCELERATION = 0.015f;

	public float prevGenerateWatts = 0;

	@NetworkedField(targetSide = Side.CLIENT)
	public float generateWatts = 0;

	@NetworkedField(targetSide = Side.CLIENT)
	public int itemCookTime = 0;

	private ItemStack[] containingItems = new ItemStack[1];

	public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();

	@Override
	public void updateEntity()
	{
		this.setEnergyStored(this.generateWatts);

		super.updateEntity();

		if (!this.worldObj.isRemote)
		{
			this.prevGenerateWatts = this.generateWatts;

			if (this.itemCookTime > 0)
			{
				this.itemCookTime--;

				if (this.getEnergyStored() < this.getMaxEnergyStored())
				{
					this.generateWatts = Math.min(this.generateWatts + Math.min(this.generateWatts * 0.000005F + BASE_ACCELERATION, 0.010F), MAX_GENERATE_WATTS);
				}
			}

			final boolean isCrystalItem = false;

			if (this.containingItems[0] != null)
			{
				if (isCrystal(this.containingItems[0], isCrystalItem))
				{
					if (this.itemCookTime <= 0)
					{
						this.itemCookTime = 624;
						this.decrStackSize(0, 1);
					}
				}
			}

			this.produce();

			if (this.itemCookTime <= 0)
			{
				this.generateWatts = Math.max(this.generateWatts - 0.002F, 0);
			}
			this.generateWatts = Math.min(Math.max(this.generateWatts, 0.0F), this.getMaxEnergyStored());
		}
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
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.itemCookTime = par1NBTTagCompound.getInteger("itemCookTime");
		this.generateWatts = par1NBTTagCompound.getFloat("generateRate");
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.containingItems = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.containingItems.length)
			{
				this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("itemCookTime", this.itemCookTime);
		par1NBTTagCompound.setFloat("generateRate", this.generateWatts);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.containingItems.length; ++var3)
		{
			if (this.containingItems[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.containingItems[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Items", var2);
	}

	@Override
	public int getSizeInventory()
	{
		return this.containingItems.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.containingItems[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.containingItems[par1] != null)
		{
			ItemStack var3;

			if (this.containingItems[par1].stackSize <= par2)
			{
				var3 = this.containingItems[par1];
				this.containingItems[par1] = null;
				return var3;
			}
			else
			{
				var3 = this.containingItems[par1].splitStack(par2);

				if (this.containingItems[par1].stackSize == 0)
				{
					this.containingItems[par1] = null;
				}

				return var3;
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
		if (this.containingItems[par1] != null)
		{
			ItemStack var2 = this.containingItems[par1];
			this.containingItems[par1] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.containingItems[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return StatCollector.translateToLocal("container.powercrystal.name");
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
	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack itemstack)
	{
		return itemstack.getItem() instanceof IPowerCrystal;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(slotID, itemstack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack itemstack, int j)
	{
		return slotID == 0;
	}

	@Override
	public float receiveElectricity(ForgeDirection from, ElectricityPack electricityPack, boolean doReceive)
	{
		return 0;
	}

	@Override
	public float getRequest(ForgeDirection direction)
	{
		return 0;
	}

	@Override
	public float getProvide(ForgeDirection direction)
	{
		if (direction == ForgeDirection.UNKNOWN && NetworkConfigHandler.isIndustrialCraft2Loaded())
		{
			BlockVec3 vec = new BlockVec3(this).modifyPositionFromSide(ForgeDirection.getOrientation(this.getBlockMetadata() - GCCoreBlockMachine.STORAGE_MODULE_METADATA + 2), 1);
			TileEntity tile = vec.getTileEntity(this.worldObj);
			if (tile instanceof IConductor)
			{
				return 0.0F;
			}
		}
		return this.generateWatts < NibiruTileEntityPowerCrystalGenerator.MIN_GENERATE_WATTS ? 0F : this.generateWatts;
	}

	@Override
	public EnumSet<ForgeDirection> getElectricalInputDirections()
	{
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getElectricalOutputDirections()
	{
		return EnumSet.of(ForgeDirection.getOrientation(this.getBlockMetadata() + 2));
	}

	@Override
	public float getMaxEnergyStored()
	{
		return NibiruTileEntityPowerCrystalGenerator.MAX_GENERATE_WATTS;
	}

	public static boolean isCrystal(ItemStack stack, boolean isCrystal)
	{
		Item item = stack.getItem();

		if (item instanceof IPowerCrystal && ((IPowerCrystal) item).isPowerCrystal())
		{
			return true;
		}
		return isCrystal;
	}
}