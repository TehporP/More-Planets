/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockVenusRedstoneOre extends BlockBaseMP
{
	private boolean isOn;

	public BlockVenusRedstoneOre(String name, boolean isOn)
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setUnlocalizedName(name);
		this.isOn = isOn;

		if (isOn)
		{
			this.setTickRandomly(true);
			this.setLightLevel(0.625F);
		}
	}

	@Override
	public int tickRate(World world)
	{
		return 30;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
	{
		this.activate(world, pos);
		super.onBlockClicked(world, pos, player);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		this.activate(world, pos);
		super.onEntityCollidedWithBlock(world, pos, entity);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		this.activate(world, pos);
		return super.onBlockActivated(world, pos, state, player, side, hitX, hitY, hitZ);
	}

	private void activate(World world, BlockPos pos)
	{
		this.spawnParticles(world, pos);

		if (this == VenusBlocks.venus_redstone_ore)
		{
			world.setBlockState(pos, VenusBlocks.venus_redstone_ore_active.getDefaultState());
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (this == VenusBlocks.venus_redstone_ore_active)
		{
			world.setBlockState(pos, VenusBlocks.venus_redstone_ore.getDefaultState());
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.redstone;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand)
	{
		return this.quantityDropped(rand) + rand.nextInt(fortune + 1);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 4 + rand.nextInt(2);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
	{
		if (this.getItemDropped(world.getBlockState(pos), RANDOM, fortune) != Item.getItemFromBlock(this))
		{
			return 1 + RANDOM.nextInt(5);
		}
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (this.isOn)
		{
			this.spawnParticles(world, pos);
		}
	}

	private void spawnParticles(World world, BlockPos pos)
	{
		Random rand = world.rand;
		double d0 = 0.0625D;

		for (int i = 0; i < 6; ++i)
		{
			double d1 = pos.getX() + rand.nextFloat();
			double d2 = pos.getY() + rand.nextFloat();
			double d3 = pos.getZ() + rand.nextFloat();

			if (i == 0 && !world.getBlockState(pos.up()).getBlock().isOpaqueCube())
			{
				d2 = pos.getY() + d0 + 1.0D;
			}
			if (i == 1 && !world.getBlockState(pos.down()).getBlock().isOpaqueCube())
			{
				d2 = pos.getY() - d0;
			}
			if (i == 2 && !world.getBlockState(pos.south()).getBlock().isOpaqueCube())
			{
				d3 = pos.getZ() + d0 + 1.0D;
			}
			if (i == 3 && !world.getBlockState(pos.north()).getBlock().isOpaqueCube())
			{
				d3 = pos.getZ() - d0;
			}
			if (i == 4 && !world.getBlockState(pos.east()).getBlock().isOpaqueCube())
			{
				d1 = pos.getX() + d0 + 1.0D;
			}
			if (i == 5 && !world.getBlockState(pos.west()).getBlock().isOpaqueCube())
			{
				d1 = pos.getX() - d0;
			}

			if (d1 < pos.getX() || d1 > pos.getX() + 1 || d2 < 0.0D || d2 > pos.getY() + 1 || d3 < pos.getZ() || d3 > pos.getZ() + 1)
			{
				world.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return this.isOn ? null : MorePlanetsCore.mpBlocksTab;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(VenusBlocks.venus_redstone_ore);
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(VenusBlocks.venus_redstone_ore);
	}
}