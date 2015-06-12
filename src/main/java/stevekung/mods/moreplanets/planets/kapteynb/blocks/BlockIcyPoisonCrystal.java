/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockContainerMP;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIcyPoisonCrystal;

public class BlockIcyPoisonCrystal extends BlockContainerMP
{
	public static int[] colors = { -4663096, -6309434, -6368557, -6837559, -7748159, -5851189 };

	public BlockIcyPoisonCrystal(String name)
	{
		super(Material.glass);
		this.setLightLevel(0.8F);
		this.setResistance(1.5F);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGlass);
		this.setUnlocalizedName(name);
		this.setLightOpacity(255);
		float f = 0.0625F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F - f, 1.0F - f);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		ItemStack itemStack = player.getCurrentEquippedItem();

		if (itemStack == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe))
		{
			player.addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 60, 0, false, true));
		}
		if (itemStack != null && player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)
		{
			super.harvestBlock(world, player, pos, state, tile);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 60, 0, false, true));
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
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityIcyPoisonCrystal();
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);

		if (this.getItemDropped(state, world.rand, fortune) != Item.getItemFromBlock(this))
		{
			int xp = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
			this.dropXpOnBlockBreak(world, pos, xp);
		}
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
		{
			int i = rand.nextInt(fortune + 1) - 1;

			if (i < 0)
			{
				i = 0;
			}
			return this.quantityDropped(rand) * (i + 1);
		}
		return this.quantityDropped(rand);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return KapteynBItems.kapteyn_b_item;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 5;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
	{
		super.onNeighborBlockChange(world, pos, state, block);

		if (this.checkIfAttachedToBlock(world, pos, state))
		{
			TileEntityIcyPoisonCrystal crystal = (TileEntityIcyPoisonCrystal)world.getTileEntity(pos);
			int facing = crystal.facing;
			boolean flag = false;

			if (!world.isSideSolid(pos.west(), EnumFacing.EAST) && facing == 5)
			{
				flag = true;
			}
			if (!world.isSideSolid(pos.east(), EnumFacing.WEST) && facing == 4)
			{
				flag = true;
			}
			if (!world.isSideSolid(pos.north(), EnumFacing.SOUTH) && facing == 3)
			{
				flag = true;
			}
			if (!world.isSideSolid(pos.south(), EnumFacing.NORTH) && facing == 2)
			{
				flag = true;
			}
			if (!world.isSideSolid(pos.down(), EnumFacing.UP) && facing == 1)
			{
				flag = true;
			}
			if (!world.isSideSolid(pos.up(), EnumFacing.DOWN) && facing == 0)
			{
				flag = true;
			}
			if (flag)
			{
				world.destroyBlock(pos, false);
			}
			return;
		}
	}

	private boolean checkIfAttachedToBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!this.canPlaceBlockAt(world, pos))
		{
			world.destroyBlock(pos, false);
			return false;
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
	{
		if (side == EnumFacing.DOWN && world.isSideSolid(pos.up(), EnumFacing.DOWN))
		{
			return true;
		}
		if (side == EnumFacing.UP && world.isSideSolid(pos.down(), EnumFacing.UP))
		{
			return true;
		}
		if (side == EnumFacing.NORTH && world.isSideSolid(pos.south(), EnumFacing.NORTH))
		{
			return true;
		}
		if (side == EnumFacing.SOUTH && world.isSideSolid(pos.north(), EnumFacing.SOUTH))
		{
			return true;
		}
		if (side == EnumFacing.WEST && world.isSideSolid(pos.east(), EnumFacing.WEST))
		{
			return true;
		}
		return side == EnumFacing.EAST && world.isSideSolid(pos.west(), EnumFacing.EAST);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		if (world.isSideSolid(pos.west(), EnumFacing.EAST))
		{
			return true;
		}
		if (world.isSideSolid(pos.east(), EnumFacing.WEST))
		{
			return true;
		}
		if (world.isSideSolid(pos.north(), EnumFacing.SOUTH))
		{
			return true;
		}
		if (world.isSideSolid(pos.south(), EnumFacing.NORTH))
		{
			return true;
		}
		if (world.isSideSolid(pos.down(), EnumFacing.UP))
		{
			return true;
		}
		return world.isSideSolid(pos.up(), EnumFacing.DOWN);
	}
}