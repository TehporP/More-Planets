/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBlockCreamOrange extends ItemBlockWithMetadata
{
	public FronosItemBlockCreamOrange(int par1, Block par2Block)
	{
		super(par1, FronosBlocks.orangeCreamLayer);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par1ItemStack.stackSize == 0)
		{
			return false;
		}
		else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else
		{
			int block = par3World.getBlockId(par4, par5, par6);

			if (block == FronosBlocks.orangeCreamLayer.blockID)
			{
				Block blocklist = Block.blocksList[this.getBlockID()];
				int j1 = par3World.getBlockMetadata(par4, par5, par6);
				int k1 = j1 & 7;

				if (k1 <= 6 && par3World.checkNoEntityCollision(blocklist.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlockMetadataWithNotify(par4, par5, par6, k1 + 1 | j1 & -8, 2))
				{
					par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F, par6 + 0.5F, blocklist.stepSound.getPlaceSound(), (blocklist.stepSound.getVolume() + 1.0F) / 2.0F, blocklist.stepSound.getPitch() * 0.8F);
					--par1ItemStack.stackSize;
					return true;
				}
			}
			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}
}