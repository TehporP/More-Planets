/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosEntityAIEatGrass extends EntityAIBase
{
	private EntityLiving theEntity;
	private World theWorld;

	int eatGrassTick;

	public FronosEntityAIEatGrass(EntityLiving par1EntityLiving)
	{
		this.theEntity = par1EntityLiving;
		this.theWorld = par1EntityLiving.worldObj;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.theEntity.getRNG().nextInt(this.theEntity.isChild() ? 50 : 1000) != 0)
		{
			return false;
		}
		else
		{
			int i = MathHelper.floor_double(this.theEntity.posX);
			int j = MathHelper.floor_double(this.theEntity.posY);
			int k = MathHelper.floor_double(this.theEntity.posZ);
			int block = this.theWorld.getBlockId(i, j - 1, k);
			return block == FronosBlocks.fronosTallGrass.blockID ? true : block == FronosBlocks.fronosGrass.blockID ? true : block == FronosBlocks.fronosPinkGrass.blockID ? true : block == FronosBlocks.fronosPurpleGrass.blockID ? true : block == FronosBlocks.fronosPlainsGrass.blockID ? true : block == FronosBlocks.goldenGrass.blockID;
		}
	}

	@Override
	public void startExecuting()
	{
		this.eatGrassTick = 40;
		this.theWorld.setEntityState(this.theEntity, (byte)10);
		this.theEntity.getNavigator().clearPathEntity();
	}

	@Override
	public void resetTask()
	{
		this.eatGrassTick = 0;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.eatGrassTick > 0;
	}

	public int getEatGrassTick()
	{
		return this.eatGrassTick;
	}

	@Override
	public void updateTask()
	{
		this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);

		if (this.eatGrassTick == 4)
		{
			int i = MathHelper.floor_double(this.theEntity.posX);
			int j = MathHelper.floor_double(this.theEntity.posY);
			int k = MathHelper.floor_double(this.theEntity.posZ);
			int block = this.theWorld.getBlockId(i, j - 1, k);

			if (block == FronosBlocks.fronosTallGrass.blockID)
			{
				this.theWorld.destroyBlock(i, j, k, false);
				this.theEntity.eatGrassBonus();
			}
			else if (block == FronosBlocks.fronosGrass.blockID || block == FronosBlocks.fronosPinkGrass.blockID || block == FronosBlocks.fronosPurpleGrass.blockID || block == FronosBlocks.fronosPlainsGrass.blockID || block == FronosBlocks.goldenGrass.blockID)
			{
				this.theWorld.playAuxSFX(2001, i, j - 1, k, FronosBlocks.fronosGrass.blockID);
				this.theWorld.setBlock(i, j - 1, k, FronosBlocks.fronosDirt.blockID, 0, 2);
				this.theEntity.eatGrassBonus();
			}
		}
	}
}