/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityEledosEgg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusBlockEledosEgg extends Block implements ITileEntityProvider
{
	public KoentusBlockEledosEgg(int i)
	{
		super(i, Material.rock);
		this.setHardness(2.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("koentus:koentusEledosEgg");
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		int blockID = par1World.getBlockId(par2, par3 - 1, par4);

		if (blockID > 0)
		{
			return Block.blocksList[blockID].isBlockSolidOnSide(par1World, par2, par3, par4, ForgeDirection.UP);
		}
		return false;
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		ItemStack currentStack = player.getCurrentEquippedItem();
		int l = world.getBlockMetadata(x, y, z);

		if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
		{
			return world.setBlockToAir(x, y, z);
		}
		else if (l < 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, l + 1, 2);

			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile instanceof KoentusTileEntityEledosEgg)
			{
				((KoentusTileEntityEledosEgg) tile).timeToHatch = world.rand.nextInt(500) + 100;
			}
			return false;
		}
		else if (player.capabilities.isCreativeMode)
		{
			return world.setBlockToAir(x, y, z);
		}
		else
		{
			return false;
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer par2EntityPlayer, int x, int y, int z, int par6)
	{
		ItemStack currentStack = par2EntityPlayer.getCurrentEquippedItem();

		if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
		{
			par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
			par2EntityPlayer.addExhaustion(0.025F);
			this.dropBlockAsItem(world, x, y, z, par6 % 3, 0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	/*@Override //TODO
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
	}*/

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new KoentusTileEntityEledosEgg();
	}
}