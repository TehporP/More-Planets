/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityRocketT4NoFlag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusItemSpaceshipTier4 extends Item implements IHoldableItem
{
	public PolongniusItemSpaceshipTier4(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		int amountOfCorrectBlocks = 0;

		if (par3World.isRemote)
		{
			return false;
		}
		else
		{
			float centerX = -1;
			float centerY = -1;
			float centerZ = -1;

			for (int i = -1; i < 2; i++)
			{
				for (int j = -1; j < 2; j++)
				{
					final int id = par3World.getBlockId(par4 + i, par5, par6 + j);

					if (id == GCCoreBlocks.landingPadFull.blockID)
					{
						amountOfCorrectBlocks = 9;

						centerX = par4 + i + 0.5F;
						centerY = par5 - 2.2F;
						centerZ = par6 + j + 0.5F;
					}
				}
			}

			if (amountOfCorrectBlocks == 9)
			{
				EntitySpaceshipBase rocket = null;

				if (par1ItemStack.getItemDamage() < 10)
				{
					rocket = new PolongniusEntityRocketT4(par3World, centerX, centerY + 4.2D, centerZ, EnumRocketType.values()[par1ItemStack.getItemDamage()]);
				}
				else
				{
					rocket = new PolongniusEntityRocketT4NoFlag(par3World, centerX, centerY + 4.2D, centerZ, EnumRocketType.values()[par1ItemStack.getItemDamage() - 10]);
				}

				par3World.spawnEntityInWorld(rocket);

				if (!par2EntityPlayer.capabilities.isCreativeMode)
				{
					par1ItemStack.stackSize--;

					if (par1ItemStack.stackSize <= 0)
					{
						par1ItemStack = null;
					}
				}

				if (rocket instanceof IRocketType && ((IRocketType) rocket).getType().getPreFueled())
				{
					if (rocket instanceof PolongniusEntityRocketT4)
					{
						((PolongniusEntityRocketT4) rocket).fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, rocket.getMaxFuel()), true);
					}
					else if (rocket instanceof PolongniusEntityRocketT4NoFlag)
					{
						((PolongniusEntityRocketT4NoFlag) rocket).fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, rocket.getMaxFuel()), true);
					}
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < EnumRocketType.values().length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}

		for (int i = 10; i < 10 + EnumRocketType.values().length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par2List, boolean b)
	{
		EnumRocketType type = null;

		if (par1ItemStack.getItemDamage() < 10)
		{
			type = EnumRocketType.values()[par1ItemStack.getItemDamage()];
		}
		else
		{
			par2List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.rocket.noflag.name"));
			type = EnumRocketType.values()[par1ItemStack.getItemDamage() - 10];
		}

		if (!type.getTooltip().isEmpty())
		{
			par2List.add(type.getTooltip());
		}

		if (type.getPreFueled())
		{
			par2List.add(EnumChatFormatting.RED + "\u00a7o" + StatCollector.translateToLocal("desc.rocket.creative.name"));
		}
	}

	@Override
	public boolean shouldHoldLeftHandUp(EntityPlayer player)
	{
		return true;
	}

	@Override
	public boolean shouldHoldRightHandUp(EntityPlayer player)
	{
		return true;
	}

	@Override
	public boolean shouldCrouch(EntityPlayer player)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	}
}