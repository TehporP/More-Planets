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

public class FronosBlockCoral extends BlockFlower
{
	private static final String[] coral = new String[] {"kelpBottom", "kelpMiddle", "kelpTop", "kelpTopFull", "kelpSingle", "glowingPinkCoral", "colunusCoral"};
	private Icon[] textures;

	public FronosBlockCoral(int blockID)
	{
		super(blockID, Material.water);
		this.setTickRandomly(true);
		float f = 0.4F;
		this.setStepSound(Block.soundGrassFootstep);
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[coral.length];

		for (int i = 0; i < coral.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:" + coral[i]);
		}
		this.textures[3] = iconRegister.registerIcon("fronos:kelpTop");
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 4)//Single Block
		{
			if (par5Random.nextInt(500) == 0)
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 0, 3);//Bottom Stage 1
				par1World.setBlock(par2, par3 + 1, par4, this.blockID, 2, 3);//Top
				return;
			}
		}
		else if (meta == 2)//Top Block
		{
			if (par5Random.nextInt(500) == 0)
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 1, 3);//Bottom Stage 2
				par1World.setBlock(par2, par3 + 1, par4, this.blockID, 3, 3);//Top
				return;
			}
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
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < coral.length; ++i)
		{
			if (i > 3)
			{
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.dirt.blockID || id == Block.sand.blockID || id == Block.sponge.blockID || id == Block.stone.blockID || id == Block.blockClay.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosStone.blockID || id == this.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 1) {
			return id == this.blockID;
		}
		if (metadata == 2) {
			return id == this.blockID;
		}
		if (metadata == 3) {
			return id == this.blockID;
		} else {
			return id == Block.dirt.blockID || id == Block.sand.blockID || id == Block.sponge.blockID || id == Block.stone.blockID || id == Block.blockClay.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosStone.blockID;
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int block = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == this.blockID)
		{
			switch (meta)
			{
			case 1: // Kelp Middle
				return block == this.blockID;

			case 2: // Kelp Top
				return block == this.blockID;

			case 3: // Kelp Top 2
				return block == this.blockID;

			default:
				return block == Block.dirt.blockID || block == Block.sand.blockID || block == Block.sponge.blockID || block == Block.stone.blockID || block == Block.blockClay.blockID || block == FronosBlocks.fronosDirt.blockID || block == FronosBlocks.fronosStone.blockID;
			}
		} else {
			return this.canPlaceBlockOnSide(world, x, y, z, side);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborID);

		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0 && world.getBlockId(x, y + 1, z) != this.blockID)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (meta == 1 && world.getBlockId(x, y + 1, z) != this.blockID)
		{
			if (world.getBlockId(x, y - 1, z) == this.blockID)
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			}
		}

		if (meta == 0 || meta == 1 || meta == 2 || meta == 7)
		{
			this.checkBlockCoordValid(world, x, y, z);
		}

		if (world.getBlockId(x, y, z) != this.blockID)
		{
			world.setBlock(x, y, z, Block.waterMoving.blockID, 0, 2);
		}
	}

	private final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		for (int i = 1; world.getBlockId(x, y + i, z) == this.blockID; i++)
		{
			if (!this.canBlockStay(world, x, y + i, z))
			{
				this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
				world.setBlock(x, y + i, z, Block.waterMoving.blockID, 0, 2);
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta < 4)
		{
			return 4;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 5)
		{
			return 10;
		}
		return 0;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta < 4)
		{
			meta = 4;
		}
		return meta;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlockId(x, y, z) != this.blockID)
		{
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}
}