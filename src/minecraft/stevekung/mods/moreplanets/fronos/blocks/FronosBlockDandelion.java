/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockDandelion extends BlockFlower
{
	private static final String[] plants = new String[] {"orangeDandelion_0", "pinkDandelion_0", "orangeDandelion_1", "pinkDandelion_1"};
	private Icon[] textures;

	public FronosBlockDandelion(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setStepSound(Block.soundGrassFootstep);
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	public FronosBlockDandelion(int blockID)
	{
		this(blockID, Material.plants);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[plants.length];

		for (int i = 0; i < plants.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:" + plants[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.textures.length)
		{
			meta = 0;
		}

		return this.textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		default:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0)
		{
			if (par5Random.nextInt(20) == 0 || par1World.isDaytime())
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 2, 3);
				return;
			}
		}
		if (meta == 1)
		{
			if (par5Random.nextInt(20) == 0 || par1World.isDaytime())
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 3, 3);
				return;
			}
		}
		if (meta == 2)
		{
			if (par5Random.nextInt(50) == 0 || par1World.isRaining() || par1World.isThundering())
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 0, 3);
				return;
			}
		}
		if (meta == 3)
		{
			if (par5Random.nextInt(50) == 0 || par1World.isRaining() || par1World.isThundering())
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 1, 3);
				return;
			}
		}
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 2)
		{
			if (par5Random.nextInt(50) == 0)
			{
				MorePlanetCore.proxy.spawnParticle("orangeDandelion", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
			else if (par5Random.nextInt(10) == 0 || par1World.isRaining() || par1World.isThundering())
			{
				MorePlanetCore.proxy.spawnParticle("orangeDandelion", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
		}
		else if (meta == 3)
		{
			if (par5Random.nextInt(50) == 0)
			{
				MorePlanetCore.proxy.spawnParticle("pinkDandelion", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
			else if (par5Random.nextInt(10) == 0 || par1World.isRaining() || par1World.isThundering())
			{
				MorePlanetCore.proxy.spawnParticle("pinkDandelion", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < plants.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 2)
		{
			world.setBlock(x, y, z, FronosBlocks.fronosDandelion.blockID, 0, 3);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
		}
		else if (meta == 3)
		{
			world.setBlock(x, y, z, FronosBlocks.fronosDandelion.blockID, 1, 3);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == this.blockID)
		{
			switch (meta)
			{
			default:
				return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
			}
		}
		else
		{
			return this.canPlaceBlockOnSide(world, x, y, z, side);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		this.checkFlowerChange(world, x, y, z);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}
}