/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;

public class BlockFallenKoentusMeteor extends BlockBaseMP
{
	public BlockFallenKoentusMeteor(String name)
	{
		super(Material.rock);
		this.setBlockBounds(0.186F, 0.186F, 0.186F, 0.814F, 0.814F, 0.814F);
		this.setHardness(50.0F);
		this.setUnlocalizedName(name);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return KoentusItems.koentus_item;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 3;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
		{
			for (int l = 0; l < 4; ++l)
			{
				double d0 = pos.getX() + rand.nextFloat();
				double d1 = pos.getY() + rand.nextFloat();
				double d2 = pos.getZ() + rand.nextFloat();
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				d3 = (rand.nextFloat() - 0.5D) * 0.5D;
				d4 = (rand.nextFloat() - 0.5D) * 0.5D;
				d5 = (rand.nextFloat() - 0.5D) * 0.5D;
				MorePlanetsCore.proxy.spawnMotionParticle(ParticleTypesMP.KOENTUS_METEOR_SMOKE, d0, d1, d2, d3, d4, d5);
			}
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, pos, state, par6, par7);

		if (this.getItemDropped(state, world.rand, par7) != Item.getItemFromBlock(this))
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
			this.dropXpOnBlockBreak(world, pos, var8);
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block par5)
	{
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random rand)
	{
		if (!par1World.isRemote)
		{
			this.tryToFall(par1World, pos);
		}
	}

	private void tryToFall(World world, BlockPos pos)
	{
		if (canFallBelow(world, pos.down()) && pos.getY() >= 0)
		{
			world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
			BlockPos blockpos1;

			for (blockpos1 = pos.down(); canFallBelow(world, blockpos1) && blockpos1.getY() > 0; blockpos1 = blockpos1.down())
			{
			}

			if (blockpos1.getY() > 0)
			{
				world.setBlockState(blockpos1.up(), this.getDefaultState(), 3);
			}
		}
	}

	public static boolean canFallBelow(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (block.getMaterial() == Material.air)
		{
			return true;
		}
		else if (block == Blocks.fire)
		{
			return true;
		}
		else
		{
			Material var5 = block.getMaterial();
			return var5 == Material.water ? true : var5 == Material.lava;
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}
}