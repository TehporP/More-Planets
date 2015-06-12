/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityTreasureChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusBlockT3TreasureChest extends BlockContainer implements ITileEntityProvider
{
	private final Random random = new Random();

	public KoentusBlockT3TreasureChest(int id)
	{
		super(id, Material.rock);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("koentus:koentusTreasureChest");
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		return -1.0F;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
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
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack stack)
	{
		par1World.getBlockId(par2, par3, par4 - 1);
		par1World.getBlockId(par2, par3, par4 + 1);
		par1World.getBlockId(par2 - 1, par3, par4);
		par1World.getBlockId(par2 + 1, par3, par4);
		byte var10 = 0;
		final int var11 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (var11 == 0)
		{
			var10 = 2;
		}

		if (var11 == 1)
		{
			var10 = 5;
		}

		if (var11 == 2)
		{
			var10 = 3;
		}

		if (var11 == 3)
		{
			var10 = 4;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var10, 3);
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		final KoentusTileEntityTreasureChest var7 = (KoentusTileEntityTreasureChest) par1World.getBlockTileEntity(par2, par3, par4);

		if (var7 != null)
		{
			for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
			{
				final ItemStack var9 = var7.getStackInSlot(var8);

				if (var9 != null)
				{
					final float var10 = this.random.nextFloat() * 0.8F + 0.1F;
					final float var11 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem var14;

					for (final float var12 = this.random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; par1World.spawnEntityInWorld(var14))
					{
						int var13 = this.random.nextInt(21) + 10;

						if (var13 > var9.stackSize)
						{
							var13 = var9.stackSize;
						}

						var9.stackSize -= var13;
						var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.itemID, var13, var9.getItemDamage()));
						final float var15 = 0.05F;
						var14.motionX = (float) this.random.nextGaussian() * var15;
						var14.motionY = (float) this.random.nextGaussian() * var15 + 0.2F;
						var14.motionZ = (float) this.random.nextGaussian() * var15;

						if (var9.hasTagCompound())
						{
							var14.getEntityItem().setTagCompound((NBTTagCompound) var9.getTagCompound().copy());
						}
					}
				}
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		Object var10 = par1World.getBlockTileEntity(par2, par3, par4);

		if (var10 == null)
		{
			return true;
		}
		else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, ForgeDirection.DOWN))
		{
			return true;
		}
		else if (KoentusBlockT3TreasureChest.isOcelotBlockingChest(par1World, par2, par3, par4))
		{
			return true;
		}
		else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, ForgeDirection.DOWN) || KoentusBlockT3TreasureChest.isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
		{
			return true;
		}
		else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, ForgeDirection.DOWN) || KoentusBlockT3TreasureChest.isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
		{
			return true;
		}
		else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, ForgeDirection.DOWN) || KoentusBlockT3TreasureChest.isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
		{
			return true;
		}
		else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, ForgeDirection.DOWN) || KoentusBlockT3TreasureChest.isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
		{
			return true;
		}
		else
		{
			if (par1World.isRemote)
			{
				return true;
			}
			else
			{
				par5EntityPlayer.displayGUIChest((IInventory) var10);
				return true;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new KoentusTileEntityTreasureChest(3);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3)
	{
		final Iterator var4 = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(par1, par2 + 1, par3, par1 + 1, par2 + 2, par3 + 1)).iterator();
		EntityOcelot var6;

		do
		{
			if (!var4.hasNext())
			{
				return false;
			}

			final EntityOcelot var5 = (EntityOcelot) var4.next();
			var6 = var5;
		}
		while (!var6.isSitting());

		return true;
	}
}