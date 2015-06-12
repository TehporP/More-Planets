/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;

public class BlockFrozenWaterGeyser extends BlockBaseMP
{
	public BlockFrozenWaterGeyser(String name)
	{
		super(Material.rock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setUnlocalizedName(name);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			if (((EntityPlayer)entity).capabilities.isFlying)
			{
				return;
			}
			entity.motionY = 3.0F;
			entity.fallDistance = 0.0F;
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
			return;
		}
		if (entity instanceof EntityLivingBase)
		{
			entity.motionY = 3.0F;
			entity.fallDistance = 0.0F;
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
			return;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		float f = 0.100F;
		return AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - f, pos.getZ() + 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		for (int i = 0; i < 5; i++)
		{
			if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
			{
				MorePlanetsCore.proxy.spawnMotionParticle(ParticleTypesMP.FROZEN_WATER_GEYSER, pos.getX() + 0.5F, pos.getY() + 0.5F + rand.nextFloat(), pos.getZ() + 0.5F, 0.0D, 0.0D + rand.nextFloat(), 0.0D);
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(KapteynBBlocks.kapteyn_b_block);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return true;
	}
}