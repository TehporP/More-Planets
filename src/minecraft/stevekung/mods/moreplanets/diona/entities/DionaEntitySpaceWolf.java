/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.entities.ai.DionaEntityAISpaceWolfBeg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaEntitySpaceWolf extends EntityTameable implements IEntityBreathable
{
	private float field_70926_e;
	private float field_70924_f;

	private boolean isShaking;
	private boolean field_70928_h;

	private float timeWolfIsShaking;
	private float prevTimeWolfIsShaking;

	public DionaEntitySpaceWolf(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new DionaEntityAISpaceWolfBeg(this, 8.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
		this.setTamed(false);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		int block = this.worldObj.getBlockId(i, j - 1, k);
		this.worldObj.getBlockMetadata(i, j, k);
		return block == DionaBlocks.dionaBasicBlock.blockID/* && meta == 6*/;
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
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
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
		this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
		par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));

		if (par1NBTTagCompound.hasKey("CollarColor"))
		{
			this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
		}
	}

	@Override
	protected String getLivingSound()
	{
		return this.isAngry() ? "mob.wolf.growl" : this.rand.nextInt(3) == 0 ? this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting" : "mob.wolf.bark";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.wolf.death";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
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

		if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround)
		{
			this.field_70928_h = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
			this.worldObj.setEntityState(this, (byte)8);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.field_70924_f = this.field_70926_e;

		if (this.func_70922_bv())
		{
			this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
		}
		else
		{
			this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
		}

		if (this.func_70922_bv())
		{
			this.numTicksToChaseTarget = 10;
		}

		if (this.isWet())
		{
			this.isShaking = true;
			this.field_70928_h = false;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		}
		else if ((this.isShaking || this.field_70928_h) && this.field_70928_h)
		{
			if (this.timeWolfIsShaking == 0.0F)
			{
				this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}

			this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
			this.timeWolfIsShaking += 0.05F;

			if (this.prevTimeWolfIsShaking >= 2.0F)
			{
				this.isShaking = false;
				this.field_70928_h = false;
				this.prevTimeWolfIsShaking = 0.0F;
				this.timeWolfIsShaking = 0.0F;
			}

			if (this.timeWolfIsShaking > 0.4F)
			{
				float f = (float)this.boundingBox.minY;
				int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

				for (int j = 0; j < i; ++j)
				{
					float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					this.worldObj.spawnParticle("splash", this.posX + f1, f + 0.8F, this.posZ + f2, this.motionX, this.motionY, this.motionZ);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean getWolfShaking()
	{
		return this.isShaking;
	}

	@SideOnly(Side.CLIENT)
	public float getShadingWhileShaking(float par1)
	{
		return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
	}

	@SideOnly(Side.CLIENT)
	public float getShakeAngle(float par1, float par2)
	{
		float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		else if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}
		return MathHelper.sin(f2 * (float)Math.PI) * MathHelper.sin(f2 * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
	}

	@SideOnly(Side.CLIENT)
	public float getInterestedAngle(float par1)
	{
		return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * (float)Math.PI;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.8F;
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
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
			this.aiSit.setSitting(false);

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
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
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
				if (Item.itemsList[itemstack.itemID] instanceof ItemFood)
				{
					ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];

					if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
					{
						if (!par1EntityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}

						this.heal(itemfood.getHealAmount());

						if (itemstack.stackSize <= 0)
						{
							par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
						}

						return true;
					}
				}
				else if (itemstack.itemID == Item.dyePowder.itemID)
				{
					int i = BlockColored.getBlockFromDye(itemstack.getItemDamage());

					if (i != this.getCollarColor())
					{
						this.setCollarColor(i);

						if (!par1EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
						{
							par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
						}

						return true;
					}
				}
			}

			if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.setPathToEntity((PathEntity)null);
				this.setTarget((Entity)null);
				this.setAttackTarget((EntityLivingBase)null);
			}
		}
		else if (itemstack != null && itemstack.itemID == Item.bone.itemID && !this.isAngry())
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
					this.aiSit.setSitting(true);
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
		if (par1 == 8)
		{
			this.field_70928_h = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		}
		else
		{
			super.handleHealthUpdate(par1);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getTailRotation()
	{
		return this.isAngry() ? 1.5393804F : this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float)Math.PI : (float)Math.PI / 5F;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack == null ? false : !(Item.itemsList[par1ItemStack.itemID] instanceof ItemFood) ? false : ((ItemFood)Item.itemsList[par1ItemStack.itemID]).isWolfsFavoriteMeat();
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 8;
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

	public int getCollarColor()
	{
		return this.dataWatcher.getWatchableObjectByte(20) & 15;
	}

	public void setCollarColor(int par1)
	{
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 15)));
	}

	public DionaEntitySpaceWolf spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		DionaEntitySpaceWolf entitywolf = new DionaEntitySpaceWolf(this.worldObj);
		String s = this.getOwnerName();

		if (s != null && s.trim().length() > 0)
		{
			entitywolf.setOwner(s);
			entitywolf.setTamed(true);
		}
		return entitywolf;
	}

	public void func_70918_i(boolean par1)
	{
		if (par1)
		{
			this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
		}
		else
		{
			this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
		}
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
		else if (!(par1EntityAnimal instanceof DionaEntitySpaceWolf))
		{
			return false;
		}
		else
		{
			DionaEntitySpaceWolf entitywolf = (DionaEntitySpaceWolf)par1EntityAnimal;
			return !entitywolf.isTamed() ? false : entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove();
		}
	}

	public boolean func_70922_bv()
	{
		return this.dataWatcher.getWatchableObjectByte(19) == 1;
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase)
	{
		if (!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast))
		{
			if (par1EntityLivingBase instanceof DionaEntitySpaceWolf)
			{
				DionaEntitySpaceWolf entitywolf = (DionaEntitySpaceWolf)par1EntityLivingBase;

				if (entitywolf.isTamed() && entitywolf.func_130012_q() == par2EntityLivingBase)
				{
					return false;
				}
			}

			return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame();
		}
		else
		{
			return false;
		}
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