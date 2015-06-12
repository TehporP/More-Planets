/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityEledosEgg;

public class BlockEledosEgg extends BlockBaseMP implements ITileEntityProvider
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockEledosEgg(String name)
	{
		super(Material.rock);
		this.setHardness(0.75F);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.eledos_egg));
		this.setBlockBounds(0.125F, 0.0F, 0.1F, 0.875F, 0.70F, 0.9F);
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean canHarvest)
	{
		ItemStack currentStack = player.getCurrentEquippedItem();

		if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
		{
			return world.setBlockToAir(pos);
		}
		else if (player.capabilities.isCreativeMode)
		{
			return world.setBlockToAir(pos);
		}
		else if (this.getMetaFromState(world.getBlockState(pos)) == 0)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.eledos_egg_crack), 2);

			/*TileEntity tile = world.getTileEntity(pos);

			if (tile instanceof TileEntityEledosEgg)
			{
				((TileEntityEledosEgg) tile).timeToHatch = world.rand.nextInt(1000);
			}*/
			return false;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		ItemStack currentStack = player.getCurrentEquippedItem();

		if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
		{
			player.addExhaustion(0.025F);
			this.dropBlockAsItem(world, pos, state, 0);
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityEledosEgg();
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		if (this.getMetaFromState(world.getBlockState(pos)) == 1)
		{
			return new ItemStack(this, 1, 0);
		}
		return super.getPickBlock(target, world, pos);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		eledos_egg,
		eledos_egg_crack;

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}
	}
}