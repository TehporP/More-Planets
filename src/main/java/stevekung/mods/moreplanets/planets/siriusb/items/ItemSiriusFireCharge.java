/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.items.ItemMorePlanets;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class ItemSiriusFireCharge extends ItemMorePlanets
{
	public ItemSiriusFireCharge(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			pos = pos.offset(side);

			if (!player.canPlayerEdit(pos, side, itemStack))
			{
				return false;
			}
			else
			{
				if (world.getBlockState(pos).getBlock().getMaterial() == Material.air)
				{
					world.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "fire.ignite", 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
					world.setBlockState(pos, SiriusBBlocks.sirius_fire.getDefaultState());
				}
				if (!player.capabilities.isCreativeMode)
				{
					--itemStack.stackSize;
				}
				return true;
			}
		}
	}
}