/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGravityBoots extends ItemArmorMP
{
	public ItemGravityBoots(String name, ArmorMaterial material, int render, int type)
	{
		super(material, render, type);
		this.setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == PlutoItems.gravity_boots)
		{
			return "pluto:textures/model/armor/gravity_boots.png";
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
	{
		if (player.worldObj.isRemote)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				list.add(EnumChatFormatting.RED + "Currently prevent fall damage, WIP for increase/decrease gravity factor");
			}
			else
			{
				list.add("Press LSHIFT for info");
			}
		}
	}

	@Override
	public String getTextureLocation()
	{
		return "pluto";
	}

	@Override
	public Item getRepairItems()
	{
		return null;
	}

	@Override
	public int getRepairItemsMetadata()
	{
		return -1;
	}
}