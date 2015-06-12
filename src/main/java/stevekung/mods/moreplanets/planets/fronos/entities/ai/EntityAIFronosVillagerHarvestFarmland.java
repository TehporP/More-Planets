/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIFronosVillagerHarvestFarmland extends EntityAIMoveToBlock
{
	private EntityFronosVillager theVillager;
	private boolean field_179502_d;
	private boolean field_179503_e;
	private int field_179501_f;

	public EntityAIFronosVillagerHarvestFarmland(EntityFronosVillager theVillager, double p_i45889_2_)
	{
		super(theVillager, p_i45889_2_, 16);
		this.theVillager = theVillager;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.field_179496_a <= 0)
		{
			if (!this.theVillager.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
			{
				return false;
			}
			this.field_179501_f = -1;
			this.field_179502_d = this.theVillager.func_175556_cs();
			this.field_179503_e = this.theVillager.func_175557_cr();
		}
		return super.shouldExecute();
	}

	@Override
	public boolean continueExecuting()
	{
		return this.field_179501_f >= 0 && super.continueExecuting();
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();
		this.theVillager.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.theVillager.getVerticalFaceSpeed());

		if (this.func_179487_f())
		{
			World world = this.theVillager.worldObj;
			BlockPos blockpos = this.destinationBlock.up();
			IBlockState iblockstate = world.getBlockState(blockpos);
			Block block = iblockstate.getBlock();

			if (this.field_179501_f == 0 && block instanceof BlockCrops && ((Integer)iblockstate.getValue(BlockCrops.AGE)).intValue() == 7)
			{
				world.destroyBlock(blockpos, true);
			}
			else if (this.field_179501_f == 1 && block == Blocks.air)
			{
				InventoryBasic inventorybasic = this.theVillager.func_175551_co();

				for (int i = 0; i < inventorybasic.getSizeInventory(); ++i)
				{
					ItemStack itemstack = inventorybasic.getStackInSlot(i);
					boolean flag = false;

					if (itemstack != null)
					{
						if (itemstack.getItem() == Items.wheat_seeds)
						{
							world.setBlockState(blockpos, Blocks.wheat.getDefaultState(), 3);
							flag = true;
						}
						else if (itemstack.getItem() == Items.potato)
						{
							world.setBlockState(blockpos, Blocks.potatoes.getDefaultState(), 3);
							flag = true;
						}
						else if (itemstack.getItem() == Items.carrot)
						{
							world.setBlockState(blockpos, Blocks.carrots.getDefaultState(), 3);
							flag = true;
						}
					}

					if (flag)
					{
						--itemstack.stackSize;

						if (itemstack.stackSize <= 0)
						{
							inventorybasic.setInventorySlotContents(i, (ItemStack)null);
						}
						break;
					}
				}
			}
			this.field_179501_f = -1;
			this.field_179496_a = 10;
		}
	}

	@Override
	protected boolean func_179488_a(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (block == Blocks.farmland || block == FronosBlocks.fronos_farmland)
		{
			pos = pos.up();
			IBlockState iblockstate = world.getBlockState(pos);
			block = iblockstate.getBlock();

			if (block instanceof BlockCrops && ((Integer)iblockstate.getValue(BlockCrops.AGE)).intValue() == 7 && this.field_179503_e && (this.field_179501_f == 0 || this.field_179501_f < 0))
			{
				this.field_179501_f = 0;
				return true;
			}
			if (block == Blocks.air && this.field_179502_d && (this.field_179501_f == 1 || this.field_179501_f < 0))
			{
				this.field_179501_f = 1;
				return true;
			}
		}
		return false;
	}
}