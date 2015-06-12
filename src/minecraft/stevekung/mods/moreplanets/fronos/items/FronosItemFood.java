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
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemFood extends ItemFood
{
	public static String[] names = {
		"strawberry",
		"berry",
		"marshmallow",
		"cookedMarshmallow",
		"vanillaIceCream",
		"chocolateIceCream",
		"strawberryIceCream",
		"coldStrawberryCloudIceCream",
		"orangeIceCream",
		"goldenBread",
		"littleSunFlowerSeeds"
	};
	protected Icon[] icons = new Icon[FronosItemFood.names.length];

	public FronosItemFood(int par1, int par2)
	{
		super(par1, par2, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.eat;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		--par1ItemStack.stackSize;
		switch (par1ItemStack.getItemDamage())
		{
		case 0:
		case 1:
			par3EntityPlayer.getFoodStats().addStats(4, 0.25F);
			break;
		case 2:
			par3EntityPlayer.getFoodStats().addStats(3, 0.15F);
			break;
		case 3:
			par3EntityPlayer.getFoodStats().addStats(6, 0.35F);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			par3EntityPlayer.getFoodStats().addStats(4, 0.45F);
			break;
		case 9:
			par3EntityPlayer.getFoodStats().addStats(5, 0.5F);
			break;
		case 10:
			par3EntityPlayer.getFoodStats().addStats(2, 0.15F);
			break;
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);

		switch (par1ItemStack.getItemDamage())
		{
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
			{
				par3EntityPlayer.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
			}
			break;
		}

		return par1ItemStack;
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
		for (int i = 0; i < FronosItemFood.names.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + FronosItemFood.names[par1ItemStack.getItemDamage()];
		}
		return "unnamed";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		int i = 0;

		for (String name : FronosItemFood.names)
		{
			this.icons[i++] = iconRegister.registerIcon("fronos:" + name);
		}
	}
}