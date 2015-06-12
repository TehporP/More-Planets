/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBlockPoppy extends ItemBlock
{
	private static final String[] plants = new String[] {"whitePoppy",
		"orangePoppy",
		"purplePoppy",};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public FronosItemBlockPoppy(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= plants.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + plants[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return Block.blocksList[this.itemID].getIcon(0, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int id = world.getBlockId(x, y, z);

		if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
		{
			side = 1;
		}
		else if (!Block.blocksList[id].isBlockReplaceable(world, x, y, z))
		{
			if (side == 0)
			{
				--y;
			}
			if (side == 1)
			{
				++y;
			}
			if (side == 2)
			{
				--z;
			}
			if (side == 3)
			{
				++z;
			}
			if (side == 4)
			{
				--x;
			}
			if (side == 5)
			{
				++x;
			}
		}

		if (!player.canPlayerEdit(x, y, z, side, itemStack))
		{
			return false;
		}
		else if (itemStack.stackSize == 0)
		{
			return false;
		}
		else
		{
			if (world.canPlaceEntityOnSide(this.getBlockID(), x, y, z, false, side, (Entity)null, itemStack))
			{
				Block block = Block.blocksList[this.getBlockID()];
				int j1 = block.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, 0);

				if (world.setBlock(x, y, z, this.getBlockID(), itemStack.getItemDamage(), 3))
				{
					if (world.getBlockId(x, y, z) == this.getBlockID())
					{
						Block.blocksList[this.getBlockID()].onBlockPlacedBy(world, x, y, z, player, itemStack);
						Block.blocksList[this.getBlockID()].onPostBlockPlaced(world, x, y, z, j1);
					}
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
					--itemStack.stackSize;
				}
			}
			return true;
		}
	}
}