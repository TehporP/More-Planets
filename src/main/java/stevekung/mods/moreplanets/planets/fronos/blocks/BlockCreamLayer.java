/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public abstract class BlockCreamLayer extends BlockBaseMP
{
	public static PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

	protected BlockCreamLayer()
	{
		super(Material.snow);
		this.setDefaultState(this.getDefaultState().withProperty(LAYERS, Integer.valueOf(1)));
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeSnow);
		this.setHardness(0.1F);
		this.setBlockBoundsForItemRender();
	}

	@Override
	public boolean isPassable(IBlockAccess world, BlockPos pos)
	{
		return ((Integer)world.getBlockState(pos).getValue(LAYERS)).intValue() < 5;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		int i = ((Integer)state.getValue(LAYERS)).intValue() - 1;
		float f = 0.125F;
		return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, pos.getY() + i * f, pos.getZ() + this.maxZ);
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
	public void setBlockBoundsForItemRender()
	{
		this.getBoundsForLayers(0);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
	{
		IBlockState iblockstate = world.getBlockState(pos);
		this.getBoundsForLayers(((Integer)iblockstate.getValue(LAYERS)).intValue());
	}

	protected void getBoundsForLayers(int meta)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, meta / 8.0F, 1.0F);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos.down());
		Block block = state.getBlock();
		return block != Blocks.ice && block != Blocks.packed_ice ? block.isLeaves(world, pos.down()) ? true : block == this && ((Integer)state.getValue(LAYERS)).intValue() == 7 ? true : block.isOpaqueCube() && block.getMaterial().blocksMovement() : false;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		this.checkAndDropBlock(world, pos);
	}

	private boolean checkAndDropBlock(World world, BlockPos pos)
	{
		if (!this.canPlaceBlockAt(world, pos))
		{
			world.setBlockToAir(pos);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
	{
		super.harvestBlock(world, player, pos, state, te);
		world.setBlockToAir(pos);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getCreamBallDropped();
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getCreamBallMetaDropped();
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11)
		{
			world.setBlockToAir(pos);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(world, pos, side);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(LAYERS, Integer.valueOf((meta & 7) + 1));
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return ((Integer)world.getBlockState(pos).getValue(LAYERS)).intValue() == 1;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(LAYERS)).intValue() - 1;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {LAYERS});
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return (Integer)state.getValue(LAYERS) + 1;
	}

	public abstract Item getCreamBallDropped();
	public abstract int getCreamBallMetaDropped();
}