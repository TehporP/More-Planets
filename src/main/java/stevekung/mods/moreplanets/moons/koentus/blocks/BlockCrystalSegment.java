/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBreakableMP;

public class BlockCrystalSegment extends BlockBreakableMP
{
	public static PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public BlockCrystalSegment(String name)
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
		this.setUnlocalizedName(name);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
	{
		return super.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, metadata, entity).withProperty(AXIS, side.getAxis());
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
	{
		this.setBlockBoundsBasedOnState(world, pos);
		return super.getSelectedBoundingBox(world, pos);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		this.setBlockBoundsBasedOnState(world, pos);
		return super.getCollisionBoundingBox(world, pos, state);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
		{
			return;
		}

		float width = 0.25F;
		float min = (1.0F - width) / 2F;
		float max = 1.0F - min;

		switch ((Axis) state.getValue(AXIS))
		{
		case X:
			this.setBlockBounds(0F, min, min, 1.0F, max, max);
			break;
		case Y:
			this.setBlockBounds(min, 0F, min, max, 1.0F, max);
			break;
		case Z:
			this.setBlockBounds(min, min, 0F, max, max, 1.0F);
			break;
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
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AXIS });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		int axis = meta % 3;
		return this.getDefaultState().withProperty(AXIS, Axis.values()[axis]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Axis)state.getValue(AXIS)).ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return true;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
}