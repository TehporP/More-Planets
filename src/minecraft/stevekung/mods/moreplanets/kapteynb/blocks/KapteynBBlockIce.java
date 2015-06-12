/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.template.BlockBreakableMP;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KapteynBBlockIce extends BlockBreakableMP
{
	public KapteynBBlockIce(int par1, Material par2Material)
	{
		super(par1, Material.ice, false);
		this.slipperiness = 1.0F;
		this.setStepSound(soundGlassFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
	{
		par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		par2EntityPlayer.addExhaustion(0.025F);

		if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer))
		{
			ItemStack itemstack = this.createStackedBlock(par6);

			if (itemstack != null)
			{
				this.dropBlockAsItem_do(par1World, par3, par4, par5, itemstack);
			}
		}
		else
		{
			if (par1World.provider.isHellWorld)
			{
				par1World.setBlockToAir(par3, par4, par5);
				return;
			}

			int i1 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
			this.dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
			Material material = par1World.getBlockMaterial(par3, par4 - 1, par5);

			if (material.blocksMovement() || material.isLiquid())
			{
				par1World.setBlock(par3, par4, par5, KapteynBBlocks.kapteynBFrozenWater.blockID);
			}
		}
	}

	@Override
	public int getMobilityFlag()
	{
		return 0;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKapteynB.kapteynB;
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		return 0.7F;
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("kapteynb:kapteynIce");
	}
}