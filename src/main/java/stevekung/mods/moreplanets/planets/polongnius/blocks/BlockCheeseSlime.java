/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBreakableMP;

public class BlockCheeseSlime extends BlockBreakableMP
{
	public BlockCheeseSlime(String name)
	{
		super(Material.glass);
		this.setStepSound(SLIME_SOUND);
		this.setHardness(0.2F);
		this.setUnlocalizedName(name);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (entity.isSneaking())
		{
			super.onFallenUpon(world, pos, entity, fallDistance);
		}
		else
		{
			entity.fall(fallDistance, 0.0F);
		}
	}

	@Override
	public void onLanded(World world, Entity entity)
	{
		if (entity.isSneaking())
		{
			super.onLanded(world, entity);
		}
		else if (entity.motionY < 0.0D)
		{
			entity.motionY = -entity.motionY;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
		{
			double d = 0.4D + Math.abs(entity.motionY) * 0.2D;
			entity.motionX *= d;
			entity.motionZ *= d;
		}
		super.onEntityCollidedWithBlock(world, pos, entity);
	}
}