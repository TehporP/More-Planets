/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

import com.google.common.base.Predicate;

public class EntityAIGrappyEatGrass extends EntityAIBase
{
	private static Predicate field_179505_b = BlockStateHelper.forBlock(FronosBlocks.fronos_tall_grass);
	private EntityLiving grassEaterEntity;
	private World entityWorld;
	int eatingGrassTimer;

	public EntityAIGrappyEatGrass(EntityLiving living)
	{
		this.grassEaterEntity = living;
		this.entityWorld = living.worldObj;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
		{
			return false;
		}
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return field_179505_b.apply(this.entityWorld.getBlockState(blockpos)) ? true : this.entityWorld.getBlockState(blockpos.down()).getBlock() instanceof IFronosGrass;
		}
	}

	@Override
	public void startExecuting()
	{
		this.eatingGrassTimer = 40;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
		this.grassEaterEntity.getNavigator().clearPathEntity();
	}

	@Override
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos pos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (field_179505_b.apply(this.entityWorld.getBlockState(pos)))
			{
				if (this.entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				{
					this.entityWorld.destroyBlock(pos, false);
				}
				this.grassEaterEntity.eatGrassBonus();
			}
			else
			{
				BlockPos pos1 = pos.down();

				if (this.entityWorld.getBlockState(pos1).getBlock() instanceof IFronosGrass)
				{
					if (this.entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
					{
						this.entityWorld.playAuxSFX(2001, pos1, Block.getIdFromBlock(FronosBlocks.fronos_grass));
						this.entityWorld.setBlockState(pos1, FronosBlocks.fronos_dirt.getDefaultState(), 2);
					}
					this.grassEaterEntity.eatGrassBonus();
				}
				else if (this.entityWorld.getBlockState(pos).getBlock() == FronosBlocks.fronos_tall_grass)
				{
					if (this.entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
					{
						this.entityWorld.playAuxSFX(2001, pos1, Block.getIdFromBlock(FronosBlocks.fronos_tall_grass));
					}
					this.grassEaterEntity.eatGrassBonus();
				}
			}
		}
	}
}