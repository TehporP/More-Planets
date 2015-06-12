/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosAncientChest;

public class EntityAICreamCatSit extends EntityAIMoveToBlock
{
	private EntityCreamCat cat;

	public EntityAICreamCatSit(EntityCreamCat cat, double par2)
	{
		super(cat, par2, 8);
		this.cat = cat;
	}

	@Override
	public boolean shouldExecute()
	{
		return this.cat.isTamed() && !this.cat.isSitting() && super.shouldExecute();
	}

	@Override
	public boolean continueExecuting()
	{
		return super.continueExecuting();
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();
		this.cat.getAISit().setSitting(false);
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
		this.cat.setSitting(false);
	}

	@Override
	public void updateTask()
	{
		super.updateTask();
		this.cat.getAISit().setSitting(false);

		if (!this.func_179487_f())
		{
			this.cat.setSitting(false);
		}
		else if (!this.cat.isSitting())
		{
			this.cat.setSitting(true);
		}
	}

	@Override
	protected boolean func_179488_a(World world, BlockPos pos)
	{
		if (!world.isAirBlock(pos.up()))
		{
			return false;
		}
		else
		{
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			TileEntity tile = world.getTileEntity(pos);
			int meta = block.getMetaFromState(state);

			if (block == Blocks.chest)
			{
				if (tile instanceof TileEntityChest && ((TileEntityChest)tile).numPlayersUsing < 1)
				{
					return true;
				}
			}
			else if (block == FronosBlocks.fronos_ancient_chest)
			{
				if (tile instanceof TileEntityFronosAncientChest && ((TileEntityFronosAncientChest)tile).numPlayersUsing < 1)
				{
					return true;
				}
			}
			else
			{
				if (block == Blocks.lit_furnace || block == FronosBlocks.candy_extractor_active || block == FronosBlocks.pink_cake || block == FronosBlocks.white_cake || block == FronosBlocks.chocolate_cake || block == Blocks.cake || block == FronosBlocks.golden_grass || block == FronosBlocks.cream_block)
				{
					return true;
				}
				else if (block == FronosBlocks.frosted_cake && meta >= 3)
				{
					return true;
				}
				else if (block == Blocks.bed && state.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD)
				{
					return true;
				}
				else if (block == FronosBlocks.cream_block)
				{
					return true;
				}
			}
			return false;
		}
	}
}