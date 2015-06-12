/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.MPEntityTameable;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAIFollowOwner;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAIOwnerHurtByTarget;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAIOwnerHurtTarget;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusEntityEledos extends MPEntityTameable implements IEntityBreathable
{
	public KoentusEntityEledos(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(4, new MPEntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new MPEntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new MPEntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.setTamed(false);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
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
	public void setAttackTarget(EntityLivingBase par1EntityLivingBase)
	{
		super.setAttackTarget(par1EntityLivingBase);

		if (par1EntityLivingBase == null)
		{
			this.setAngry(false);
		}
		else if (!this.isTamed())
		{
			this.setAngry(true);
		}
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
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
	}

	@Override
	protected String getLivingSound()
	{
		return null;
	}

	@Override
	protected String getHurtSound()
	{
		return null;
	}

	@Override
	protected String getDeathSound()
	{
		return null;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.6F;
	}

	@Override
	protected int getDropItemId()
	{
		return -1;
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
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.8F;
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
			Entity entity = par1DamageSource.getEntity();

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
			{
				par2 = (par2 + 1.0F) / 2.0F;
			}
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int i = this.isTamed() ? 4 : 2;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
	}

	@Override
	public void setTamed(boolean par1)
	{
		super.setTamed(par1);

		if (par1)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
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
				if (itemstack.getItem() == KoentusItems.koentusBasicItem && itemstack.getItemDamage() == 3)
				{
					if (this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
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
		}
		else if (itemstack != null && itemstack.itemID == KoentusItems.koentusBasicItem.itemID  && itemstack.getItemDamage() == 3 && !this.isAngry())
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
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1)
	{
		super.handleHealthUpdate(par1);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() == KoentusItems.koentusBasicItem && par1ItemStack.getItemDamage() == 3;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}

	public boolean isAngry()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	public void setAngry(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
		}
	}

	public KoentusEntityEledos spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		KoentusEntityEledos entitywolf = new KoentusEntityEledos(this.worldObj);
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
		else if (!(par1EntityAnimal instanceof KoentusEntityEledos))
		{
			return false;
		}
		else
		{
			KoentusEntityEledos entitywolf = (KoentusEntityEledos)par1EntityAnimal;
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
}