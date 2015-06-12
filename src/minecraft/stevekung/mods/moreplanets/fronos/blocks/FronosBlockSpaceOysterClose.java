/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOysterClose;

public class FronosBlockSpaceOysterClose extends Block implements ITileEntityProvider
{
	public FronosBlockSpaceOysterClose(int id)
	{
		super(id, Material.plants);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 0.6F;
		this.setBlockBounds(0.225F, 0.0F, 0.225F, 0.775F, 0.275F, 0.775F);
		this.setTickRandomly(true);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("fronos:oyster");
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
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
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return 10;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if (par5Random.nextInt(1500) == 0)
		{
			par1World.setBlock(par2, par3, par4, FronosBlocks.spaceOyster.blockID, meta, 3);
			return;
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		this.canBlockStay(par1World, par2, par3, par4);
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int block = par1World.getBlockId(par2, par3 - 1, par4);
		Block block2 = Block.blocksList[block];

		if (block2 == null)
		{
			return false;
		}
		if (!block2.isLeaves(par1World, par2, par3 - 1, par4) && !Block.blocksList[block].isOpaqueCube())
		{
			return false;
		}
		return par1World.getBlockMaterial(par2, par3 - 1, par4).blocksMovement();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		final int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		byte change = 0;

		switch (angle)
		{
		case 0:
			change = 0;
			break;
		case 1:
			change = 3;
			break;
		case 2:
			change = 1;
			break;
		case 3:
			change = 2;
			break;
		}
		world.setBlockMetadataWithNotify(x, y, z, change, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new FronosTileEntitySpaceOysterClose();
	}
}