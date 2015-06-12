/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityLaser;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityLaser.LaserType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemLaserGun extends Item
{
	public DionaItemLaserGun(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(860);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("diona:laserGun");
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

		if (flag || par3EntityPlayer.inventory.hasItem(DionaItems.laserCharge.itemID))
		{
			DionaEntityLaser entityDart = new DionaEntityLaser(par2World, par3EntityPlayer, 1.0F);

			itemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "diona:player.laser", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);

			int slot = -1;
			if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(DionaItems.laserCharge.itemID, 1, 1)))
			{
				entityDart.setLaserType(LaserType.HYPER);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
				{
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].itemID == DionaItems.laserCharge.itemID && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 1)
					{
						slot = k;
						break;
					}
				}
			}
			else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(DionaItems.laserCharge.itemID, 1, 0)))
			{
				entityDart.setLaserType(LaserType.NORMAL);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
				{
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].itemID == DionaItems.laserCharge.itemID && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 0)
					{
						slot = k;
						break;
					}
				}
			}
			else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(DionaItems.laserCharge.itemID, 1, 2)))
			{
				entityDart.setLaserType(LaserType.EMP);

				for (int k = 0; k < par3EntityPlayer.inventory.mainInventory.length; ++k)
				{
					if (par3EntityPlayer.inventory.mainInventory[k] != null && par3EntityPlayer.inventory.mainInventory[k].itemID == DionaItems.laserCharge.itemID && par3EntityPlayer.inventory.mainInventory[k].getItemDamage() == 2)
					{
						slot = k;
						break;
					}
				}
			}
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(entityDart);
			}
			if (!flag && slot >= 0)
			{
				par3EntityPlayer.inventory.decrStackSize(slot, 1);
			}
		}
		return itemStack;
	}
}