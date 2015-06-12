/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.api.entities.IImmuneMapleIvy;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.ai.FronosEntityAIBeg;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosEntityBearry extends EntityTameable implements IEntityBreathable, IImmuneMapleIvy
{
	public int timeUntilToDropStrawberry;

	public FronosEntityBearry(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(4, new MPEntityAITempt(this, 1.1D, new ItemStack(FronosItems.fronosFood2.itemID, 1, 0), false));
		this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new FronosEntityAIBeg(this, 8.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.timeUntilToDropStrawberry = this.rand.nextInt(6000) + 2000;
		this.setTamed(false);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		int block = this.worldObj.getBlockId(i, j - 1, k);
		return block == FronosBlocks.fronosGrass.blockID;
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 1.5D;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void updateAITick()
	{
		this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Float(this.getHealth()));
		this.dataWatcher.addObject(19, new Byte((byte)0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	@Override
	protected String getLivingSound()
	{
		return "fronos:mob.marshmallow.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "fronos:mob.marshmallow.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "fronos:mob.marshmallow.death";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.8F;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(FronosItems.fronosFood, 1, 0), 0.0F);
		}
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
	{
		return 1 + this.worldObj.rand.nextInt(10);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.isChild() && !this.worldObj.isRemote && --this.timeUntilToDropStrawberry <= 0)
		{
			this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(FronosItems.fronosFood.itemID, 1);
			this.timeUntilToDropStrawberry = this.rand.nextInt(6000) + 2000;
		}
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.8F;
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return super.getVerticalFaceSpeed();
	}

	@Override
	public void setTamed(boolean par1)
	{
		super.setTamed(par1);

		if (par1)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		}
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (itemstack != null)
			{
				if (itemstack.getItem() == FronosItems.pearl && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
				{
					if (!par1EntityPlayer.capabilities.isCreativeMode)
					{
						--itemstack.stackSize;
					}
					if (itemstack.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
					return true;
				}
			}
		}
		else if (itemstack != null && itemstack.itemID == FronosItems.pearl.itemID && itemstack.getItemDamage() == 0)
		{
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			}

			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.setPathToEntity((PathEntity)null);
					this.setAttackTarget((EntityLivingBase)null);
					this.setHealth(20.0F);
					this.setOwner(par1EntityPlayer.getCommandSenderName());
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
		return super.interact(par1EntityPlayer);
	}

	@Override
	public void handleHealthUpdate(byte par1)
	{
		super.handleHealthUpdate(par1);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		int meta = par1ItemStack.getItemDamage();
		return par1ItemStack.itemID == FronosItems.fronosFood2.itemID && meta == 1;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}

	public FronosEntityBearry spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		FronosEntityBearry entitywolf = new FronosEntityBearry(this.worldObj);
		String s = this.getOwnerName();

		if (s != null && s.trim().length() > 0)
		{
			entitywolf.setOwner(s);
			entitywolf.setTamed(true);
		}
		return entitywolf;
	}

	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal)
	{
		if (par1EntityAnimal == this)
		{
			return false;
		}
		else if (!this.isTamed())
		{
			return false;
		}
		else if (!(par1EntityAnimal instanceof FronosEntityBearry))
		{
			return false;
		}
		else
		{
			FronosEntityBearry entitywolf = (FronosEntityBearry)par1EntityAnimal;
			return !entitywolf.isTamed() ? false : this.isInLove() && entitywolf.isInLove();
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
	{
		if (this.worldObj.rand.nextInt(20) == 0)
		{
			FronosEntityMarshmallow marshmallow = new FronosEntityMarshmallow(this.worldObj);
			marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			marshmallow.onSpawnWithEgg((EntityLivingData)null);
			this.worldObj.spawnEntityInWorld(marshmallow);
			marshmallow.mountEntity(this);
			marshmallow.setGrowingAge(-8000);
		}
		return par1EntityLivingData;
	}

	@Override
	public Entity getOwner()
	{
		return this.func_130012_q();
	}
}