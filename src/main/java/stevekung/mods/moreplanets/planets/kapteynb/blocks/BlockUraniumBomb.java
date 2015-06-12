/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityUraniumBomb;

public class BlockUraniumBomb extends BlockBaseMP
{
	public static PropertyBool EXPLODE = PropertyBool.create("explode");

	public BlockUraniumBomb(String name)
	{
		super(Material.tnt);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(0.3F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, false));
		this.setUnlocalizedName(name);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(world, pos, state);

		if (world.isBlockPowered(pos))
		{
			this.onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, true));
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (world.isBlockPowered(pos))
		{
			this.onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, true));
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)
		{
			EntityUraniumBomb entitytntprimed = new EntityUraniumBomb(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, explosion.getExplosivePlacedBy());
			entitytntprimed.fuse = world.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
			world.spawnEntityInWorld(entitytntprimed);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		this.explode(world, pos, state, (EntityLivingBase)null);
	}

	public void explode(World world, BlockPos pos, IBlockState state, EntityLivingBase igniter)
	{
		if (!world.isRemote)
		{
			if (((Boolean)state.getValue(EXPLODE)).booleanValue())
			{
				EntityUraniumBomb entitytntprimed = new EntityUraniumBomb(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, igniter);
				world.spawnEntityInWorld(entitytntprimed);
				world.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			Item item = player.getCurrentEquippedItem().getItem();

			if (item == Items.flint_and_steel || item == Items.fire_charge)
			{
				this.explode(world, pos, state.withProperty(EXPLODE, true), player);
				world.setBlockToAir(pos);

				if (item == Items.flint_and_steel)
				{
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				else if (!player.capabilities.isCreativeMode)
				{
					--player.getCurrentEquippedItem().stackSize;
				}
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityArrow)
		{
			EntityArrow entityarrow = (EntityArrow)entity;

			if (entityarrow.isBurning())
			{
				this.explode(world, pos, world.getBlockState(pos).withProperty(EXPLODE, true), entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.shootingEntity : null);
				world.setBlockToAir(pos);
			}
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion)
	{
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(EXPLODE, Boolean.valueOf((meta & 1) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Boolean)state.getValue(EXPLODE)).booleanValue() ? 1 : 0;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {EXPLODE});
	}
}