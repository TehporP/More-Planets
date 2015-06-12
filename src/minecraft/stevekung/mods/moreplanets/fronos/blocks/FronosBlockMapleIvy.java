/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.api.entities.IImmuneMapleIvy;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockMapleIvy extends Block implements IShearable
{
	public FronosBlockMapleIvy(int par1)
	{
		super(par1, Material.vine);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundGrassFootstep);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType()
	{
		return 20;
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
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float f1 = 1.0F;
		float f2 = 1.0F;
		float f3 = 1.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean flag = l > 0;

		if ((l & 2) != 0)
		{
			f4 = Math.max(f4, 0.0625F);
			f1 = 0.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}

		if ((l & 8) != 0)
		{
			f1 = Math.min(f1, 0.9375F);
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}

		if ((l & 4) != 0)
		{
			f6 = Math.max(f6, 0.0625F);
			f3 = 0.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}

		if ((l & 1) != 0)
		{
			f3 = Math.min(f3, 0.9375F);
			f6 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}

		if (!flag && this.canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4)))
		{
			f2 = Math.min(f2, 0.9375F);
			f5 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
		}
		this.setBlockBounds(f1, f2, f3, f4, f5, f6);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
	{
		switch (par5)
		{
		case 1:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4));
		case 2:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 + 1));
		case 3:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 - 1));
		case 4:
			return this.canBePlacedOn(par1World.getBlockId(par2 + 1, par3, par4));
		case 5:
			return this.canBePlacedOn(par1World.getBlockId(par2 - 1, par3, par4));
		default:
			return false;
		}
	}

	private boolean canBePlacedOn(int par1)
	{
		if (par1 == 0)
		{
			return false;
		}
		else
		{
			Block block = Block.blocksList[par1];
			return block.renderAsNormalBlock() && block.blockMaterial.blocksMovement();
		}
	}

	private boolean canVineStay(World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		int i1 = l;

		if (l > 0)
		{
			for (int j1 = 0; j1 <= 3; ++j1)
			{
				int k1 = 1 << j1;

				if ((l & k1) != 0 && !this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[j1], par3, par4 + Direction.offsetZ[j1])) && (par1World.getBlockId(par2, par3 + 1, par4) != this.blockID || (par1World.getBlockMetadata(par2, par3 + 1, par4) & k1) == 0))
				{
					i1 &= ~k1;
				}
			}
		}

		if (i1 == 0 && !this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4)))
		{
			return false;
		}
		else
		{
			if (i1 != l)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, i1, 2);
			}
			return true;
		}
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("fronos:mapleIvy");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if (!par1World.isRemote && !this.canVineStay(par1World, par2, par3, par4))
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && par1World.rand.nextInt(4) == 0)
		{
			byte b0 = 4;
			int l = 5;
			boolean flag = false;
			int i1;
			int j1;
			int k1;
			label138:

				for (i1 = par2 - b0; i1 <= par2 + b0; ++i1)
				{
					for (j1 = par4 - b0; j1 <= par4 + b0; ++j1)
					{
						for (k1 = par3 - 1; k1 <= par3 + 1; ++k1)
						{
							if (par1World.getBlockId(i1, k1, j1) == this.blockID)
							{
								--l;

								if (l <= 0)
								{
									flag = true;
									break label138;
								}
							}
						}
					}
				}

			i1 = par1World.getBlockMetadata(par2, par3, par4);
			j1 = par1World.rand.nextInt(6);
			k1 = Direction.facingToDirection[j1];
			int l1;
			int i2;

			if (j1 == 1 && par3 < 255 && par1World.isAirBlock(par2, par3 + 1, par4))
			{
				if (flag)
				{
					return;
				}

				l1 = par1World.rand.nextInt(16) & i1;

				if (l1 > 0)
				{
					for (i2 = 0; i2 <= 3; ++i2)
					{
						if (!this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3 + 1, par4 + Direction.offsetZ[i2])))
						{
							l1 &= ~(1 << i2);
						}
					}

					if (l1 > 0)
					{
						par1World.setBlock(par2, par3 + 1, par4, this.blockID, l1, 2);
					}
				}
			}
			else
			{
				int j2;

				if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0)
				{
					if (flag)
					{
						return;
					}

					l1 = par1World.getBlockId(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1]);

					if (l1 != 0 && Block.blocksList[l1] != null)
					{
						if (Block.blocksList[l1].blockMaterial.isOpaque() && Block.blocksList[l1].renderAsNormalBlock())
						{
							par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 1 << k1, 2);
						}
					}
					else
					{
						i2 = k1 + 1 & 3;
						j2 = k1 + 3 & 3;

						if ((i1 & 1 << i2) != 0 && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2])))
						{
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 1 << i2, 2);
						}
						else if ((i1 & 1 << j2) != 0 && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2])))
						{
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 1 << j2, 2);
						}
						else if ((i1 & 1 << i2) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2]) && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[i2])))
						{
							par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2], this.blockID, 1 << (k1 + 2 & 3), 2);
						}
						else if ((i1 & 1 << j2) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2]) && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[j2])))
						{
							par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2], this.blockID, 1 << (k1 + 2 & 3), 2);
						}
						else if (this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1], par3 + 1, par4 + Direction.offsetZ[k1])))
						{
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 0, 2);
						}
					}
				}
				else if (par3 > 1)
				{
					l1 = par1World.getBlockId(par2, par3 - 1, par4);

					if (l1 == 0)
					{
						i2 = par1World.rand.nextInt(16) & i1;

						if (i2 > 0)
						{
							par1World.setBlock(par2, par3 - 1, par4, this.blockID, i2, 2);
						}
					}
					else if (l1 == this.blockID)
					{
						i2 = par1World.rand.nextInt(16) & i1;
						j2 = par1World.getBlockMetadata(par2, par3 - 1, par4);

						if (j2 != (j2 | i2))
						{
							par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, j2 | i2, 2);
						}
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		byte b0 = 0;

		switch (par5)
		{
		case 2:
			b0 = 1;
			break;
		case 3:
			b0 = 4;
			break;
		case 4:
			b0 = 8;
			break;
		case 5:
			b0 = 2;
		}
		return b0 != 0 ? b0 : par9;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
	{
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			if (entity instanceof EntityPlayer)
			{
				if (!(entity instanceof IImmuneMapleIvy))
				{
					if (world.rand.nextInt(2000) == 0)
					{
						((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
					}
				}
			}
		}
	}
}