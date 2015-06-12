/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos.MutableBlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class BlockFarmlandMP extends BlockBaseMP
{
	public static PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 1);

	public BlockFarmlandMP()
	{
		super(Material.ground);
		this.setDefaultState(this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeGravel);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		this.setLightOpacity(255);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
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
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		int i = ((Integer)state.getValue(MOISTURE)).intValue();

		if (!this.hasWater(world, pos) && !world.canLightningStrike(pos.up()))
		{
			if (i == 1)
			{
				world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(0)), 2);
			}
			else if (!this.hasCrops(world, pos))
			{
				world.setBlockState(pos, this.getDirtBlock().getDefaultState());
			}
		}
		else if (i == 0)
		{
			world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(1)), 2);
		}
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F)
			{
				if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				{
					return;
				}
				world.setBlockState(pos, this.getDirtBlock().getDefaultState());
			}
			super.onFallenUpon(world, pos, entity, fallDistance);
		}
	}

	private boolean hasCrops(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos.up()).getBlock();
		return block instanceof IPlantMP;
	}

	private boolean hasWater(World world, BlockPos pos)
	{
		Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
		MutableBlockPos mutableblockpos;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}
			mutableblockpos = (MutableBlockPos)iterator.next();
		}
		while (world.getBlockState(mutableblockpos).getBlock().getMaterial() != Material.water);
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, pos, state, neighborBlock);

		if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid())
		{
			world.setBlockState(pos, this.getDirtBlock().getDefaultState());
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this.getDirtBlock());
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 1));
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this.getDirtBlock());
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(MOISTURE)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {MOISTURE});
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return null;
	}

	public abstract Block getDirtBlock();
}