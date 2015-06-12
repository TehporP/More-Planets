/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.entities.EntityFlagMP;

public class ItemFlagMP extends ItemBaseMP /*implements IHoldableItem*/
{
	public ItemFlagMP(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.setHasSubtypes(true);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		//if (!ConfigManagerMP.enableThaiFlagAndCanvas)
		{
			return null;
		}
		/*else
		{
			return MorePlanetsCore.mpItemsTab;
		}*/
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
	{
		int useTime = this.getMaxItemUseDuration(itemStack) - tick;
		boolean placed = false;
		MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, true);
		float usingTime = useTime / 20.0F;

		usingTime = (usingTime * usingTime + usingTime * 2.0F) / 3.0F;

		if (usingTime > 1.0F)
		{
			usingTime = 1.0F;
		}

		if (usingTime == 1.0F && moving != null && moving.typeOfHit == MovingObjectType.BLOCK)
		{
			int x = moving.getBlockPos().getX();
			int y = moving.getBlockPos().getY();
			int z = moving.getBlockPos().getZ();

			if (!world.isRemote)
			{
				EntityFlagMP flag = new EntityFlagMP(world, x + 0.5F, y + 1.0F, z + 0.5F, (int) (player.rotationYaw - 90));

				if (world.getEntitiesWithinAABB(EntityFlagMP.class, AxisAlignedBB.fromBounds(x, y, z, x + 1, y + 3, z + 1)).isEmpty())
				{
					world.spawnEntityInWorld(flag);
					flag.setType(itemStack.getItemDamage());
					placed = true;
				}
				else
				{
					player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.flag.alreadyPlaced")));
				}
			}

			if (placed)
			{
				int i = this.getInventorySlotContainItem(player, itemStack);

				if (i >= 0 && !player.capabilities.isCreativeMode)
				{
					if (--player.inventory.mainInventory[i].stackSize <= 0)
					{
						player.inventory.mainInventory[i] = null;
					}
				}
			}
		}
	}

	private int getInventorySlotContainItem(EntityPlayer player, ItemStack itemStack)
	{
		for (int i = 0; i < player.inventory.mainInventory.length; ++i)
		{
			if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].isItemEqual(itemStack))
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.NONE;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		return itemStack;
	}

	/*@Override
	public boolean shouldHoldLeftHandUp(EntityPlayer player)
	{
		return false;
	}

	@Override
	public boolean shouldHoldRightHandUp(EntityPlayer player)
	{
		return true;
	}

	@Override
	public boolean shouldCrouch(EntityPlayer player)
	{
		return false;
	}*/

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "thai", "laos", "singapore", "myanmar", "marlaysia", "vietnam", "indonesia", "philippines", "cambodia", "brunei" };
	}
}