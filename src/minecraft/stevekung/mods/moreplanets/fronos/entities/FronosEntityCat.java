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
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.ai.FronosEntityAICatSit;

public class FronosEntityCat extends EntityTameable implements IEntityBreathable
{
	private MPEntityAITempt aiTempt;

	public FronosEntityCat(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, this.aiTempt = new MPEntityAITempt(this, 0.6D, new ItemStack(Item.fishRaw.itemID, 1, 0), true));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
		this.tasks.addTask(6, new FronosEntityAICatSit(this, 1.33D));
		this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
		this.tasks.addTask(8, new EntityAIOcelotAttack(this));
		this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
		this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, 750, false));
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
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
	}

	@Override
	public void updateAITick()
	{
		if (this.getMoveHelper().isUpdating())
		{
			double d0 = this.getMoveHelper().getSpeed();

			if (d0 == 0.6D)
			{
				this.setSneaking(true);
				this.setSprinting(false);
			}
			else if (d0 == 1.33D)
			{
				this.setSneaking(false);
				this.setSprinting(true);
			}
			else
			{
				this.setSneaking(false);
				this.setSprinting(false);
			}
		}
		else
		{
			this.setSneaking(false);
			this.setSprinting(false);
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);
	}

	@Override
	protected void fall(float par1)
	{
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("CatType", this.getTameSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setTameSkin(par1NBTTagCompound.getInteger("CatType"));
	}

	@Override
	protected String getLivingSound()
	{
		return this.isTamed() ? this.isInLove() ? "mob.cat.purr" : this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow" : "";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.cat.hitt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.cat.hitt";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			this.aiSit.setSitting(false);
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				this.aiSit.setSitting(!this.isSitting());
			}
		}
		else if (this.aiTempt.isRunning() && itemstack != null && itemstack.itemID == Item.fishRaw.itemID && par1EntityPlayer.getDistanceSqToEntity(this) < 9.0D)
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
				if (this.rand.nextInt(1) == 0)
				{
					this.setTamed(true);
					this.setTameSkin(1 + this.worldObj.rand.nextInt(5));
					this.setOwner(par1EntityPlayer.getCommandSenderName());
					this.playTameEffect(true);
					this.aiSit.setSitting(true);
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

	public FronosEntityCat spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		FronosEntityCat entityocelot = new FronosEntityCat(this.worldObj);

		if (this.isTamed())
		{
			entityocelot.setOwner(this.getOwnerName());
			entityocelot.setTamed(true);
			entityocelot.setTameSkin(this.getTameSkin());
		}
		return entityocelot;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.itemID == Item.fishRaw.itemID;
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
		else if (!(par1EntityAnimal instanceof FronosEntityCat))
		{
			return false;
		}
		else
		{
			FronosEntityCat entityocelot = (FronosEntityCat)par1EntityAnimal;
			return !entityocelot.isTamed() ? false : this.isInLove() && entityocelot.isInLove();
		}
	}

	public int getTameSkin()
	{
		return this.dataWatcher.getWatchableObjectByte(18);
	}

	public void setTameSkin(int par1)
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)par1));
	}

	@Override
	public String getEntityName()
	{
		return this.hasCustomNameTag() ? this.getCustomNameTag() : this.isTamed() ? "entity.Cat.name" : super.getEntityName();
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
	{
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

		if (this.worldObj.rand.nextInt(7) == 0)
		{
			for (int i = 0; i < 2; ++i)
			{
				FronosEntityCat entityocelot = new FronosEntityCat(this.worldObj);
				entityocelot.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				entityocelot.setGrowingAge(-24000);
				this.worldObj.spawnEntityInWorld(entityocelot);
			}
		}
		return par1EntityLivingData;
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
	public Entity getOwner()
	{
		return this.func_130012_q();
	}
}