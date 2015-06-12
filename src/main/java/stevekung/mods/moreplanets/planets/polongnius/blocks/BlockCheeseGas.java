/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBreakableMP;

public class BlockCheeseGas extends BlockBreakableMP
{
	public BlockCheeseGas(String name)
	{
		super(Material.cloth);
		this.setStepSound(soundTypeCloth);
		this.setHardness(0.25F);
		this.setResistance(0.5F);
		this.setUnlocalizedName(name);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (!((EntityLivingBase)entity).isSneaking())
			{
				entity.motionX *= 1.0F;
				entity.motionY = 0.15F;
				entity.motionZ *= 1.0F;
				entity.fallDistance = 0.0F;
				return;
			}
			else
			{
				entity.motionX *= 1.0F;
				entity.motionY = -0.05F;
				entity.motionZ *= 1.0F;
				entity.fallDistance = 0.0F;
				return;
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
}