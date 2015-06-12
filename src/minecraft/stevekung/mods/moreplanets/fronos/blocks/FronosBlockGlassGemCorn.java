/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockGlassGemCorn extends BlockFlower
{
	private static final String[] corn = new String[] {
		"glassGemCorn_1",//0 Corn Bottom State 1
		"glassGemCorn_1",//1 Corn Middle
		"glassGemCorn_0",//2 Corn Top
		"glassGemCorn_0",//3 Corn State 0
		"glassGemCorn_1"//4 Corn Middle State 1
	};
	private Icon[] textures;

	private static final int CornMiddleState1 = 4;

	public FronosBlockGlassGemCorn(int blockID)
	{
		super(blockID, Material.plants);
		this.setTickRandomly(true);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[corn.length];

		for (int i = 0; i < corn.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:" + corn[i]);
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 3)//Corn State 0
		{
			if (par5Random.nextInt(1) == 0)
			{
				par1World.setBlock(par2, par3 + 1, par4, this.blockID, 1, 3);//Top
				par1World.setBlock(par2, par3, par4, this.blockID, 0, 3);//Corn Bottom State 1
				return;
			}
		}
		else if (meta == 1)//Corn Middle
		{
			if (par5Random.nextInt(1) == 0)
			{
				par1World.setBlock(par2, par3 + 1, par4, this.blockID, CornMiddleState1, 3);//Corn Middle State 1
				return;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return FronosItems.glassGemCorn.itemID;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return null;
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
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == FronosBlocks.fronosFarmland.blockID || id == this.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 1) {
			return id == this.blockID;
		}
		if (metadata == 2) {
			return id == this.blockID;
		}
		if (metadata == CornMiddleState1) {
			return id == this.blockID;
		} else {
			return id == FronosBlocks.fronosFarmland.blockID;
		}
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return FronosItems.glassGemCorn.itemID;
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
			case 1: // Corn Middle
				return id == this.blockID;

			case 2: // Corn Top
				return id == this.blockID;

			case CornMiddleState1:
				return id == this.blockID;

			default:
				return id == FronosBlocks.fronosFarmland.blockID;
			}
		} else {
			return this.canPlaceBlockOnSide(world, x, y, z, side);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborID);

		if (world.getBlockMetadata(x, y, z) == 0 && world.getBlockId(x, y + 1, z) != this.blockID)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (world.getBlockMetadata(x, y, z) == 1 && world.getBlockId(x, y + 1, z) != this.blockID)
		{
			if (world.getBlockId(x, y - 1, z) == this.blockID)
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			}
		}

		if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 2)
		{
			this.checkBlockCoordValid(world, x, y, z);
		}
	}

	protected final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		for (int i = 1; world.getBlockId(x, y + i, z) == this.blockID; i++)
		{
			if (!this.canBlockStay(world, x, y + i, z))
			{
				this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta < 4)
		{
			return 0;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta < 4)
		{
			return 0;
		}
		return meta;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlockId(x, y, z) != this.blockID) {
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		} else {
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}
}