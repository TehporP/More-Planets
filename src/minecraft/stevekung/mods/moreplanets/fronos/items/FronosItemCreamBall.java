/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityChocolateCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityOrangeCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityStrawberryCreamBall;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityVanillaCreamBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemCreamBall extends Item
{
	public static String[] names = {"creamVanillaBall",
		"creamChocolateBall",
		"creamStrawberryBall",
		"creamOrangeBall"
	};
	protected Icon[] icons = new Icon[FronosItemCreamBall.names.length];

	public FronosItemCreamBall(int par1)
	{
		super(par1);
		this.maxStackSize = 16;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		int meta = par1ItemStack.getItemDamage();
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (meta == 0)
		{
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new FronosEntityVanillaCreamBall(par2World, par3EntityPlayer));
			}
		}
		else if (meta == 1)
		{
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new FronosEntityChocolateCreamBall(par2World, par3EntityPlayer));
			}
		}
		else if (meta == 2)
		{
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new FronosEntityStrawberryCreamBall(par2World, par3EntityPlayer));
			}
		}

		else if (meta == 3)
		{
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new FronosEntityOrangeCreamBall(par2World, par3EntityPlayer));
			}
		}
		return par1ItemStack;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public Icon getIconFromDamage(int damage)
	{
		if (this.icons.length > damage)
		{
			return this.icons[damage];
		}
		return super.getIconFromDamage(damage);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < FronosItemCreamBall.names.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + FronosItemCreamBall.names[par1ItemStack.getItemDamage()];
		}
		return "unnamed";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		int i = 0;

		for (String name : FronosItemCreamBall.names)
		{
			this.icons[i++] = iconRegister.registerIcon("fronos:" + name);
		}
	}
}