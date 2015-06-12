/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityCat;

public class FronosEntityAICatSit extends EntityAIBase
{
	private final FronosEntityCat theOcelot;
	private final double field_75404_b;
	private int currentTick;
	private int field_75402_d;
	private int maxSittingTicks;
	private int sitableBlockX;
	private int sitableBlockY;
	private int sitableBlockZ;

	public FronosEntityAICatSit(FronosEntityCat par1EntityOcelot, double par2)
	{
		this.theOcelot = par1EntityOcelot;
		this.field_75404_b = par2;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute()
	{
		return this.theOcelot.isTamed() && !this.theOcelot.isSitting() && this.theOcelot.getRNG().nextDouble() <= 0.006500000134110451D && this.getNearbySitableBlockDistance();
	}

	@Override
	public boolean continueExecuting()
	{
		return this.currentTick <= this.maxSittingTicks && this.field_75402_d <= 60 && this.isSittableBlock(this.theOcelot.worldObj, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ);
	}

	@Override
	public void startExecuting()
	{
		this.theOcelot.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
		this.currentTick = 0;
		this.field_75402_d = 0;
		this.maxSittingTicks = this.theOcelot.getRNG().nextInt(this.theOcelot.getRNG().nextInt(1200) + 1200) + 1200;
		this.theOcelot.func_70907_r().setSitting(false);
	}

	@Override
	public void resetTask()
	{
		this.theOcelot.setSitting(false);
	}

	@Override
	public void updateTask()
	{
		++this.currentTick;
		this.theOcelot.func_70907_r().setSitting(false);

		if (this.theOcelot.getDistanceSq(this.sitableBlockX, this.sitableBlockY + 1, this.sitableBlockZ) > 1.0D)
		{
			this.theOcelot.setSitting(false);
			this.theOcelot.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
			++this.field_75402_d;
		}
		else if (!this.theOcelot.isSitting())
		{
			this.theOcelot.setSitting(true);
		}
		else
		{
			--this.field_75402_d;
		}
	}

	protected boolean getNearbySitableBlockDistance()
	{
		int i = (int)this.theOcelot.posY;
		double d0 = 2.147483647E9D;

		for (int j = (int)this.theOcelot.posX - 8; j < this.theOcelot.posX + 8.0D; ++j)
		{
			for (int k = (int)this.theOcelot.posZ - 8; k < this.theOcelot.posZ + 8.0D; ++k)
			{
				if (this.isSittableBlock(this.theOcelot.worldObj, j, i, k) && this.theOcelot.worldObj.isAirBlock(j, i + 1, k))
				{
					double d1 = this.theOcelot.getDistanceSq(j, i, k);

					if (d1 < d0)
					{
						this.sitableBlockX = j;
						this.sitableBlockY = i;
						this.sitableBlockZ = k;
						d0 = d1;
					}
				}
			}
		}
		return d0 < 2.147483647E9D;
	}

	protected boolean isSittableBlock(World par1World, int par2, int par3, int par4)
	{
		int block = par1World.getBlockId(par2, par3, par4);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (block == Block.chest.blockID)
		{
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4);

			if (tileentitychest.numUsingPlayers < 1)
			{
				return true;
			}
		}
		else
		{
			if (block == Block.furnaceBurning.blockID)
			{
				return true;
			}

			if (block == FronosBlocks.pinkCakeBlock.blockID)
			{
				return true;
			}

			if (block == Block.cake.blockID)
			{
				return true;
			}

			if (block == FronosBlocks.candyExtractorActive.blockID)
			{
				return true;
			}

			if (block == FronosBlocks.frostedCakeBlock.blockID && meta == 1 || meta == 2)
			{
				return true;
			}

			if (block == FronosBlocks.strawberryCloud.blockID)
			{
				return true;
			}

			if (block == Block.bed.blockID && !BlockBed.isBlockHeadOfBed(meta))
			{
				return true;
			}
		}
		return false;
	}
}