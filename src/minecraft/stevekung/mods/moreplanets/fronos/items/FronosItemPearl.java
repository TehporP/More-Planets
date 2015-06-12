/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemPearl extends Item
{
	public FronosItemPearl(int par1)
	{
		super(par1);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("fronos:pearl");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		int i1 = par3World.getBlockId(par4, par5, par6);
		par3World.getBlockMetadata(par4, par5, par6);

		if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && i1 == FronosBlocks.fronosGrass.blockID)
		{
			if (par3World.isRemote)
			{
				return true;
			}
			else
			{
				par3World.setBlock(par4, par5, par6, FronosBlocks.goldenGrass.blockID);

				if (par3World.rand.nextInt(4) == 0)
				{
					par3World.setBlock(par4, par5 + 1, par6, FronosBlocks.fronosTallGrass.blockID, 12, 3);
				}
				--par1ItemStack.stackSize;
				return true;
			}
		}
		else
		{
			return false;
		}
	}
}