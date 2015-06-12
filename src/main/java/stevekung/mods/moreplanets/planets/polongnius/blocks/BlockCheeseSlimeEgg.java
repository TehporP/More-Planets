/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockEggMP;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;

public class BlockCheeseSlimeEgg extends BlockEggMP
{
	public BlockCheeseSlimeEgg(String name)
	{
		super();
		this.setStepSound(SLIME_SOUND);
		this.setUnlocalizedName(name);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)
		{
			EntityCheeseSlime slime = new EntityCheeseSlime(world);
			slime.setPosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
			slime.setSlimeSize(world.rand.nextInt(4));
			world.spawnEntityInWorld(slime);
		}
		world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "mob.slime.big", 5.0F, 1.0F);
		world.setBlockToAir(pos);
		this.onBlockDestroyedByExplosion(world, pos, explosion);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
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