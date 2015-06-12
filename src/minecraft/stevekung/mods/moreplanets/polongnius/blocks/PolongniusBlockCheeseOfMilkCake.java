/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBlockCheeseOfMilkCake extends Block
{
	Icon[] cheeseOfMilkIcons;

	public PolongniusBlockCheeseOfMilkCake(int par1, Material material)
	{
		super(par1, Material.cake);
		this.setTickRandomly(true);
		this.disableStats();
		this.setHardness(0.5F);
		this.setStepSound(Block.soundClothFootstep);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.cheeseOfMilkIcons = new Icon[3];
		this.cheeseOfMilkIcons[0] = par1IconRegister.registerIcon("polongnius:cheeseOfMilk_1");
		this.cheeseOfMilkIcons[1] = par1IconRegister.registerIcon("polongnius:cheeseOfMilk_2");
		this.cheeseOfMilkIcons[2] = par1IconRegister.registerIcon("polongnius:cheeseOfMilk_3");
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		final int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		final float var6 = 0.0625F;
		final float var7 = (1 + var5 * 2) / 16.0F;
		final float var8 = 0.5F;
		this.setBlockBounds(var7, 0.0F, var6, 1.0F - var6, var8, 1.0F - var6);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		final float var1 = 0.0625F;
		final float var2 = 0.5F;
		this.setBlockBounds(var1, 0.0F, var1, 1.0F - var1, var2, 1.0F - var1);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		final int var5 = par1World.getBlockMetadata(par2, par3, par4);
		final float var6 = 0.0625F;
		final float var7 = (1 + var5 * 2) / 16.0F;
		final float var8 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB(par2 + var7, par3, par4 + var6, par2 + 1 - var6, par3 + var8 - var6, par4 + 1 - var6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		final int var5 = par1World.getBlockMetadata(par2, par3, par4);
		final float var6 = 0.0625F;
		final float var7 = (1 + var5 * 2) / 16.0F;
		final float var8 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB(par2 + var7, par3, par4 + var6, par2 + 1 - var6, par3 + var8, par4 + 1 - var6);
	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.cheeseOfMilkIcons[0] : par1 == 0 ? this.cheeseOfMilkIcons[0] : par2 > 0 && par1 == 4 ? this.cheeseOfMilkIcons[2] : this.cheeseOfMilkIcons[1];
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		this.eatCakeSlice(par1World, par2, par3, par4, par5EntityPlayer);
		return true;
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		this.eatCakeSlice(par1World, par2, par3, par4, par5EntityPlayer);
	}

	private void eatCakeSlice(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		if (par5EntityPlayer.canEat(false))
		{
			par5EntityPlayer.getFoodStats().addStats(4, 0.4F);
			final int l = par1World.getBlockMetadata(par2, par3, par4) + 1;

			if (l >= 6)
			{
				par1World.setBlockToAir(par2, par3, par4);
			}
			else
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return !super.canPlaceBlockAt(par1World, par2, par3, par4) ? false : this.canBlockStay(par1World, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if (!this.canBlockStay(par1World, par2, par3, par4))
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid();
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return PolongniusItems.cheeseOfMilkBlockCake.itemID;
	}
}