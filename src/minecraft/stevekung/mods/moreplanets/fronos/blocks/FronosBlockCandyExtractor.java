/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityCandyExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockCandyExtractor extends BlockContainer
{
	private final Random extractorRand = new Random();

	private final boolean isActive;
	private static boolean keepExtractorInventory;

	private Icon extractorSide;
	private Icon extractorFace;
	private Icon extractorTop;

	public FronosBlockCandyExtractor(int par1, boolean par2)
	{
		super(par1, Material.rock);
		this.isActive = par2;
		this.setHardness(4.0F);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosBlocks.candyExtractorIdle.blockID;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		if (!this.isActive)
		{
			return ModuleFronos.fronosTab;
		}
		return null;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}
			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.extractorTop = iconRegister.registerIcon("fronos:extractorTop");
		this.extractorSide = iconRegister.registerIcon("fronos:extractorSide");
		this.extractorFace = iconRegister.registerIcon(this.isActive ? "fronos:extractorFrontOn" : "fronos:extractorFrontOff");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			FronosTileEntityCandyExtractor tileentityfurnace = (FronosTileEntityCandyExtractor)par1World.getBlockTileEntity(par2, par3, par4);

			if (tileentityfurnace != null)
			{
				par5EntityPlayer.openGui(MorePlanetCore.instance, -1, par1World, par2, par3, par4);
			}
			return true;
		}
	}

	public static void updateExtractorBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		keepExtractorInventory = true;

		if (par0)
		{
			par1World.setBlock(par2, par3, par4, FronosBlocks.candyExtractorActive.blockID);
		}
		else
		{
			par1World.setBlock(par2, par3, par4, FronosBlocks.candyExtractorIdle.blockID);
		}

		keepExtractorInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setBlockTileEntity(par2, par3, par4, tileentity);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (this.isActive)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			float f = par2 + 0.5F;
			float f1 = par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
			float f2 = par4 + 0.5F;
			float f3 = 0.52F;
			float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

			if (l == 4)
			{
				par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 5)
			{
				par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 2)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 3)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new FronosTileEntityCandyExtractor();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}

		if (par6ItemStack.hasDisplayName())
		{
			((FronosTileEntityCandyExtractor)par1World.getBlockTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (!keepExtractorInventory)
		{
			FronosTileEntityCandyExtractor tileentityfurnace = (FronosTileEntityCandyExtractor)par1World.getBlockTileEntity(par2, par3, par4);

			if (tileentityfurnace != null)
			{
				for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
				{
					ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

					if (itemstack != null)
					{
						float f = this.extractorRand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.extractorRand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.extractorRand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0)
						{
							int k1 = this.extractorRand.nextInt(21) + 10;

							if (k1 > itemstack.stackSize)
							{
								k1 = itemstack.stackSize;
							}

							itemstack.stackSize -= k1;
							EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound())
							{
								entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (float)this.extractorRand.nextGaussian() * f3;
							entityitem.motionY = (float)this.extractorRand.nextGaussian() * f3 + 0.2F;
							entityitem.motionZ = (float)this.extractorRand.nextGaussian() * f3;
							par1World.spawnEntityInWorld(entityitem);
						}
					}
				}
				par1World.func_96440_m(par2, par3, par4, par5);
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return FronosBlocks.candyExtractorIdle.blockID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.extractorTop : par1 == 0 ? this.extractorTop : par1 != par2 ? this.extractorSide : this.extractorFace;
	}
}