/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBlockCoral extends ItemBlock
{
	private static final String[] coral = new String[] {"kelpBottom", "kelpMiddle", "kelpTop", "kelpTopFull", "kelpSingle", "glowingPinkCoral", "colunusCoral"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public FronosItemBlockCoral(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
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
		this.textures = new Icon[1];
		this.textures[0] = iconRegister.registerIcon("fronos:fronosKelp");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();

		if (meta < 0 || meta >= coral.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + coral[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta == 4)
		{
			return this.textures[0];
		}
		return Block.blocksList[this.itemID].getIcon(0, meta);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int id = world.getBlockId(x, y, z);

		if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
		{
			side = 1;
		}
		else if (id != Block.vine.blockID && id != Block.tallGrass.blockID && id != Block.deadBush.blockID && (Block.blocksList[id] == null || !Block.blocksList[id].isBlockReplaceable(world, x, y, z)))
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

		if (itemstack.stackSize == 0)
		{
			return false;
		}
		else if (!player.canPlayerEdit(x, y, z, side, itemstack))
		{
			return false;
		}
		else if (y == 255 && Block.blocksList[this.itemID].blockMaterial.isSolid())
		{
			return false;
		}
		else if (world.canPlaceEntityOnSide(this.itemID, x, y, z, false, side, player, itemstack))
		{
			int block = world.getBlockId(x, y + 1, z);
			if (block == Block.waterStill.blockID || block == Block.waterMoving.blockID || block == FronosBlocks.mineralWaterFluidBlock.blockID)
			{
				this.onItemUsePlaceBlock(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
				return true;
			}
		}
		return false;
	}

	private void onItemUsePlaceBlock(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block block = Block.blocksList[this.itemID];
		int j1 = this.getMetadata(itemstack.getItemDamage());
		int k1 = Block.blocksList[this.itemID].onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, j1);

		if (this.placeBlockAt(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ, k1))
		{
			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			--itemstack.stackSize;
		}
	}
}