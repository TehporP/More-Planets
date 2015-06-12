/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;

public class BlockInfectedCavernousVine extends BlockBaseMP implements IShearable
{
	public BlockInfectedCavernousVine(String name)
	{
		super(Material.vine);
		this.setLightLevel(1.0F);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeGrass);
		this.setUnlocalizedName(name);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, BlockPos pos, Vec3 vec3d, Vec3 vec3d1)
	{
		return super.collisionRayTrace(world, pos, vec3d, vec3d1);
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if (world.setBlockToAir(pos))
		{
			int y = pos.down().getY();

			while (world.getBlockState(pos.add(pos.getX(), y, pos.getZ())) == this)
			{
				world.setBlockToAir(pos.add(pos.getX(), y, pos.getZ()));
				y--;
			}
			return true;
		}
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (entity instanceof EntityPlayer && world.rand.nextInt(1000) == 0)
			{
				if (((EntityPlayer)entity).capabilities.isFlying)
				{
					return;
				}
				if (!world.isRemote)
				{
					EntityInfectedWorm worm = new EntityInfectedWorm(world);
					worm.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
					world.spawnEntityInWorld(worm);
				}
			}
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 5, 20, false, false));
			entity.motionY = 0.09F;
			entity.fallDistance = 0.0F;
			return;
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
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.DOWN && world.getBlockState(pos.up()).getBlock().getMaterial().isSolid();
	}

	@Override
	public int tickRate(World world)
	{
		return 50;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			for (int y = pos.down().getY(); y >= pos.down(2).getY(); y--)
			{
				Block block = world.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock();

				if (!block.isAir(world, pos))
				{
					return;
				}
			}
			world.setBlockState(pos.down(), this.getDefaultState(), 2);
			world.checkLight(pos);
		}
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
	{
		this.checkForDrop(world, pos, state);
	}

	private boolean checkForDrop(World world, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(world, pos))
		{
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
			return false;
		}
		else
		{
			return true;
		}
	}

	private boolean canBlockStay(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos.up()).getBlock();
		return block == this || block.getMaterial().isSolid();
	}

	@Override
	public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}
}