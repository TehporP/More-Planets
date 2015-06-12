/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLeash;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaBlockQuontoniumBricksFence extends BlockFence
{
	public final String field_94464_a;

	public DionaBlockQuontoniumBricksFence(int par1, String par2Str, Material par3Material)
	{
		super(par1, par2Str, Material.rock);
		this.field_94464_a = "quontoniumBricks";
		this.blockHardness = 2.25F;
	}

	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		boolean flag = this.canConnectFenceTo(par1World, par2, par3, par4 - 1);
		boolean flag1 = this.canConnectFenceTo(par1World, par2, par3, par4 + 1);
		boolean flag2 = this.canConnectFenceTo(par1World, par2 - 1, par3, par4);
		boolean flag3 = this.canConnectFenceTo(par1World, par2 + 1, par3, par4);
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}

		if (flag || flag1)
		{
			this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}

		f2 = 0.375F;
		f3 = 0.625F;

		if (flag2)
		{
			f = 0.0F;
		}

		if (flag3)
		{
			f1 = 1.0F;
		}

		if (flag2 || flag3 || !flag && !flag1)
		{
			this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}
		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		boolean flag = this.canConnectFenceTo(par1IBlockAccess, par2, par3, par4 - 1);
		boolean flag1 = this.canConnectFenceTo(par1IBlockAccess, par2, par3, par4 + 1);
		boolean flag2 = this.canConnectFenceTo(par1IBlockAccess, par2 - 1, par3, par4);
		boolean flag3 = this.canConnectFenceTo(par1IBlockAccess, par2 + 1, par3, par4);
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}

		if (flag2)
		{
			f = 0.0F;
		}

		if (flag3)
		{
			f1 = 1.0F;
		}
		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return false;
	}

	@Override
	public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int l = par1IBlockAccess.getBlockId(par2, par3, par4);

		if (l != this.blockID && l != Block.fenceGate.blockID)
		{
			Block block = Block.blocksList[l];
			return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("diona:" + this.field_94464_a);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		return par1World.isRemote ? true : ItemLeash.func_135066_a(par5EntityPlayer, par1World, par2, par3, par4);
	}
}