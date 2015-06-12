/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MPItemBlockSlab extends ItemSlab
{
	private static BlockHalfSlab singleSlab;
	private static BlockHalfSlab doubleSlab;

	static public void setSlabs(BlockHalfSlab singleSlab, BlockHalfSlab doubleSlab)
	{
		MPItemBlockSlab.singleSlab = singleSlab;
		MPItemBlockSlab.doubleSlab = doubleSlab;
	}

	public MPItemBlockSlab(int id)
	{
		super(id, singleSlab, doubleSlab, id == doubleSlab.blockID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 7;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockSlabMP slab = (BlockSlabMP)Block.blocksList[itemStack.itemID];
		return super.getUnlocalizedName() + "." + new StringBuilder().append(slab.getFullSlabName(itemStack.getItemDamage())).toString();
	}
}