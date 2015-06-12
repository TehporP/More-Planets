/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.entities.IImmuneMapleIvy;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public abstract class IFronosPet extends EntityTameable implements IImmuneMapleIvy
{
	public IFronosPet(World world)
	{
		super(world);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("moreplanets:mob.fronos.step", 0.15F, 1.0F);
	}

	@Override
	protected String getLivingSound()
	{
		return "moreplanets:mob.fronos.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "moreplanets:mob.fronos.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "moreplanets:mob.fronos.death";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.8F;
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player)
	{
		return 1 + this.worldObj.rand.nextInt(6);
	}

	@Override
	public void setTamed(boolean tame)
	{
		super.setTamed(tame);

		if (tame)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		Block block = this.worldObj.getBlockState(this.getPosition().down()).getBlock();
		return block == FronosBlocks.fronos_grass;
	}

	@Override
	public boolean canMateWith(EntityAnimal entity)
	{
		if (entity == this)
		{
			return false;
		}
		if (!this.isTamed())
		{
			return false;
		}
		if (!(entity instanceof IFronosPet))
		{
			return false;
		}

		IFronosPet pet = (IFronosPet)entity;

		if (!pet.isTamed())
		{
			return false;
		}
		if (pet.isSitting())
		{
			return false;
		}
		return this.isInLove() && pet.isInLove();
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		if (itemStack == null)
		{
			return false;
		}
		return itemStack != null && itemStack.getItem() == FronosItems.candy_food && itemStack.getItemDamage() == 1;
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (itemStack != null)
			{
				if (itemStack.getItem() == FronosItems.pearl)
				{
					this.heal(12.0F);

					if (itemStack.stackSize <= 0)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
					return true;
				}
			}
			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
			{
				this.aiSit.setSitting(!this.isSitting());
			}
		}
		else if (itemStack != null && itemStack.getItem() == FronosItems.pearl && itemStack.getItemDamage() == 0)
		{
			if (!player.capabilities.isCreativeMode)
			{
				itemStack.stackSize -= 1;
			}
			if (itemStack.stackSize <= 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.aiSit.setSitting(true);
					this.setHealth(20.0F);
					this.setOwnerId(player.getUniqueID().toString());
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}
				else
				{
					this.playTameEffect(false);
					this.worldObj.setEntityState(this, (byte)6);
				}
			}
			return true;
		}
		return super.interact(player);
	}

	@Override
	public boolean canLivingInIvy()
	{
		return true;
	}
}