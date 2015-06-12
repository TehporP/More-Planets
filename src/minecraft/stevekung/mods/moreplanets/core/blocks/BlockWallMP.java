/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWallMP extends BlockWall
{
	@SideOnly(Side.CLIENT)
	private Icon[] wallBlockIcon;

	public BlockWallMP(int par1, Block par2Block)
	{
		super(par1, par2Block);
		this.setHardness(par2Block.blockHardness);
		this.setResistance(par2Block.blockResistance / 3.0F);
		this.setStepSound(par2Block.stepSound);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.wallBlockIcon[0];
		case 1:
			return this.wallBlockIcon[1];
		case 2:
			return this.wallBlockIcon[2];
		case 3:
			return this.wallBlockIcon[3];
		case 4:
			return this.wallBlockIcon[4];
		case 5:
			return this.wallBlockIcon[5];
		case 6:
			return this.wallBlockIcon[6];
		case 7:
			return this.wallBlockIcon[7];
		case 8:
			return this.wallBlockIcon[8];
		}
		return this.blockIcon;
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
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		boolean flag = this.canConnectWallTo(par1IBlockAccess, par2, par3, par4 - 1);
		boolean flag1 = this.canConnectWallTo(par1IBlockAccess, par2, par3, par4 + 1);
		boolean flag2 = this.canConnectWallTo(par1IBlockAccess, par2 - 1, par3, par4);
		boolean flag3 = this.canConnectWallTo(par1IBlockAccess, par2 + 1, par3, par4);
		float f = 0.25F;
		float f1 = 0.75F;
		float f2 = 0.25F;
		float f3 = 0.75F;
		float f4 = 1.0F;

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

		if (flag && flag1 && !flag2 && !flag3)
		{
			f4 = 0.8125F;
			f = 0.3125F;
			f1 = 0.6875F;
		}
		else if (!flag && !flag1 && flag2 && flag3)
		{
			f4 = 0.8125F;
			f2 = 0.3125F;
			f3 = 0.6875F;
		}
		this.setBlockBounds(f, 0.0F, f2, f1, f4, f3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		this.maxY = 1.5D;
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
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
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 9; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetCore.morePlanetTab;
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return par5 == 0 ? super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) : true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.wallBlockIcon = new Icon[9];
		this.wallBlockIcon[0] = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_4");
		this.wallBlockIcon[1] = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_2");
		this.wallBlockIcon[2] = par1IconRegister.registerIcon("galacticraftmars:cobblestone");
		this.wallBlockIcon[3] = par1IconRegister.registerIcon("polongnius:polongniusCobblestone");
		this.wallBlockIcon[4] = par1IconRegister.registerIcon("nibiru:nibiruCobblestone");
		this.wallBlockIcon[5] = par1IconRegister.registerIcon("diona:quontoniumBricks");
		this.wallBlockIcon[6] = par1IconRegister.registerIcon("koentus:koentusCobblestone");
		this.wallBlockIcon[7] = par1IconRegister.registerIcon("fronos:fronosCobblestone");
		this.wallBlockIcon[8] = par1IconRegister.registerIcon("kapteynb:kapteynBCobblestone");
	}
}