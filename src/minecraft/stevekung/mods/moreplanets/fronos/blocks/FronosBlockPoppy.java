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
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockPoppy extends BlockFlower
{
	private static final String[] plants = new String[] {"whitePoppy",
		"orangePoppy",
		"purplePoppy",};
	private Icon[] textures;

	public FronosBlockPoppy(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setStepSound(Block.soundGrassFootstep);
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	public FronosBlockPoppy(int blockID)
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
		return this.canPlaceBlockOnSide(world, x, y, z, side);
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