/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items;

import java.util.Calendar;
import java.util.List;

import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.entities.DionaEntityThailandFlag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemFlag extends Item implements IHoldableItem
{
	public static final String[] names = { "thailand", // 0
		"laos", // 1
		"singapore", // 2
		"myanmar", // 3
		"marlaysia", // 4
		"vietnam", // 5
		"indonesia", // 6
		"philippines", // 7
		"cambodia", // 8
	"brunei"}; // 9

	public int placeProgress;

	public DionaItemFlag(int id)
	{
		super(id);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		this.setTextureName("diona:blank");

		Calendar calendar = Calendar.getInstance();

		if (calendar.get(2) + 1 == 5 && calendar.get(5) >= 20 && calendar.get(5) <= 30)
		{
			this.setHasSubtypes(true);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(2) + 1 == 5 && calendar.get(5) >= 20 && calendar.get(5) <= 30)
		{
			for (int i = 0; i < names.length; i++)
			{
				par3List.add(new ItemStack(par1, 1, i));
			}
		}
		else
		{
			for (int i = 0; i < 1; i++)
			{
				par3List.add(new ItemStack(par1, 1, i));
			}
		}
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		final int useTime = this.getMaxItemUseDuration(par1ItemStack) - par4;

		boolean placed = false;

		final MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		float var7 = useTime / 20.0F;
		var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

		if (var7 > 1.0F)
		{
			var7 = 1.0F;
		}

		if (var7 == 1.0F && var12 != null && var12.typeOfHit == EnumMovingObjectType.TILE)
		{
			final int x = var12.blockX;
			final int y = var12.blockY;
			final int z = var12.blockZ;

			if (!par2World.isRemote)
			{
				final DionaEntityThailandFlag flag = new DionaEntityThailandFlag(par2World, x + 0.5F, y + 1.0F, z + 0.5F, par3EntityPlayer.rotationYaw - 90F);

				if (par2World.getEntitiesWithinAABB(DionaEntityThailandFlag.class, AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 3, z + 1)).isEmpty())
				{
					par2World.spawnEntityInWorld(flag);
					flag.setType(par1ItemStack.getItemDamage());
					flag.setOwner(par3EntityPlayer.username);
					placed = true;
				}
				else
				{
					par3EntityPlayer.sendChatToPlayer(ChatMessageComponent.createFromText("Flag already placed here!"));
				}
			}

			if (placed)
			{
				final int var2 = this.getInventorySlotContainItem(par3EntityPlayer, par1ItemStack);

				if (var2 >= 0 && !par3EntityPlayer.capabilities.isCreativeMode)
				{
					if (--par3EntityPlayer.inventory.mainInventory[var2].stackSize <= 0)
					{
						par3EntityPlayer.inventory.mainInventory[var2] = null;
					}
				}
			}
		}
	}

	private int getInventorySlotContainItem(EntityPlayer player, ItemStack stack)
	{
		for (int var2 = 0; var2 < player.inventory.mainInventory.length; ++var2)
		{
			if (player.inventory.mainInventory[var2] != null && player.inventory.mainInventory[var2].isItemEqual(stack))
			{
				return var2;
			}
		}

		return -1;
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.none;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item.flag." + DionaItemFlag.names[itemStack.getItemDamage()];
	}

	@Override
	public Icon getIconFromDamage(int damage)
	{
		return super.getIconFromDamage(damage);
	}

	@Override
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
	}
}