/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import net.minecraft.block.Block;
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
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.entities.ai.EntityAISpaceWolfBeg;

import com.google.common.base.Predicate;

public class EntitySpaceWolf extends EntityTameable /*implements IEntityBreathable*/
{
	private float headRotationCourse;
	private float headRotationCourseOld;
	private boolean isWet;
	private boolean isShaking;
	private float timeWolfIsShaking;
	private float prevTimeWolfIsShaking;

	public EntitySpaceWolf(World world)
	{
		super(world);
		this.setSize(0.6F, 0.8F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAISpaceWolfBeg(this, 8.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate()
		{
			public boolean func_180094_a(Entity entity)
			{
				return entity instanceof EntitySheep || entity instanceof EntityRabbit;
			}
			@Override
			public boolean apply(Object entity)
			{
				return this.func_180094_a((Entity)entity);
			}
		}));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
		this.setTamed(false);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}

		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	public void setAttackTarget(EntityLivingBase entity)
	{
		super.setAttackTarget(entity);

		if (entity == null)
		{
			this.setAngry(false);
		}
		else if (!this.isTamed())
		{
			this.setAngry(true);
		}
	}

	@Override
	protected void updateAITasks()
	{
		this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Float(this.getHealth()));
		this.dataWatcher.addObject(19, new Byte((byte)0));
		this.dataWatcher.addObject(20, new Byte((byte)EnumDyeColor.RED.getMetadata()));
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Angry", this.isAngry());
		nbt.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setAngry(nbt.getBoolean("Angry"));

		if (nbt.hasKey("CollarColor", 99))
		{
			this.setCollarColor(EnumDyeColor.byDyeDamage(nbt.getByte("CollarColor")));
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
	protected Item getDropItem()
	{
		return Item.getItemById(-1);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround)
		{
			this.isShaking = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
			this.worldObj.setEntityState(this, (byte)8);
		}

		if (!this.worldObj.isRemote && this.getAttackTarget() == null && this.isAngry())
		{
			this.setAngry(false);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.headRotationCourseOld = this.headRotationCourse;

		if (this.func_70922_bv())
		{
			this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
		}
		else
		{
			this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
		}

		if (this.isWet())
		{
			this.isWet = true;
			this.isShaking = false;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		}
		else if ((this.isWet || this.isShaking) && this.isShaking)
		{
			if (this.timeWolfIsShaking == 0.0F)
			{
				this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}

			this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
			this.timeWolfIsShaking += 0.05F;

			if (this.prevTimeWolfIsShaking >= 2.0F)
			{
				this.isWet = false;
				this.isShaking = false;
				this.prevTimeWolfIsShaking = 0.0F;
				this.timeWolfIsShaking = 0.0F;
			}

			if (this.timeWolfIsShaking > 0.4F)
			{
				float f = (float)this.getEntityBoundingBox().minY;
				int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

				for (int j = 0; j < i; ++j)
				{
					float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + f1, f + 0.8F, this.posZ + f2, this.motionX, this.motionY, this.motionZ, new int[0]);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean isWolfWet()
	{
		return this.isWet;
	}

	@SideOnly(Side.CLIENT)
	public float getShadingWhileWet(float par1)
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
		return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * par1) * 0.15F * (float)Math.PI;
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
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			Entity entity = source.getEntity();
			this.aiSit.setSitting(false);

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
			{
				amount = (amount + 1.0F) / 2.0F;
			}
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());

		if (flag)
		{
			this.func_174815_a(this, entity);
		}
		return flag;
	}

	@Override
	public void setTamed(boolean tamed)
	{
		super.setTamed(tamed);

		if (tamed)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (itemStack != null)
			{
				if (itemStack.getItem() instanceof ItemFood)
				{
					ItemFood itemfood = (ItemFood)itemStack.getItem();

					if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
					{
						if (!player.capabilities.isCreativeMode)
						{
							--itemStack.stackSize;
						}

						this.heal(itemfood.getHealAmount(itemStack));

						if (itemStack.stackSize <= 0)
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
						}
						return true;
					}
				}
				else if (itemStack.getItem() == Items.dye)
				{
					EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemStack.getMetadata());

					if (enumdyecolor != this.getCollarColor())
					{
						this.setCollarColor(enumdyecolor);

						if (!player.capabilities.isCreativeMode && --itemStack.stackSize <= 0)
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
						}
						return true;
					}
				}
			}

			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
			{
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.navigator.clearPathEntity();
				this.setAttackTarget((EntityLivingBase)null);
			}
		}
		else if (itemStack != null && itemStack.getItem() == Items.bone && !this.isAngry())
		{
			if (!player.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}
			if (itemStack.stackSize <= 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
			}

			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.navigator.clearPathEntity();
					this.setAttackTarget((EntityLivingBase)null);
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
		if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1000)
		{
			if (!this.worldObj.isRemote)
			{
				EntityAgeable entityageable = this.createChild(this);

				if (entityageable != null)
				{
					entityageable.setGrowingAge(-24000);
					entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityageable);

					if (itemStack.hasDisplayName())
					{
						entityageable.setCustomNameTag(itemStack.getDisplayName());
					}
					if (!player.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;

						if (itemStack.stackSize <= 0)
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
						}
					}
				}
			}
			return true;
		}
		return super.interact(player);
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1000);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte health)
	{
		if (health == 8)
		{
			this.isShaking = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		}
		else
		{
			super.handleHealthUpdate(health);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getTailRotation()
	{
		return this.isAngry() ? 1.5393804F : this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float)Math.PI : (float)Math.PI / 5F;
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		return itemStack == null ? false : !(itemStack.getItem() instanceof ItemFood) ? false : ((ItemFood)itemStack.getItem()).isWolfsFavoriteMeat();
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

	public void setAngry(boolean angry)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (angry)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
		}
	}

	public EnumDyeColor getCollarColor()
	{
		return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(20) & 15);
	}

	public void setCollarColor(EnumDyeColor collarcolor)
	{
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)(collarcolor.getDyeDamage() & 15)));
	}

	@Override
	public EntitySpaceWolf createChild(EntityAgeable ageable)
	{
		EntitySpaceWolf entitywolf = new EntitySpaceWolf(this.worldObj);
		String s = this.getOwnerId();

		if (s != null && s.trim().length() > 0)
		{
			entitywolf.setOwnerId(s);
			entitywolf.setTamed(true);
		}
		return entitywolf;
	}

	public void func_70918_i(boolean p_70918_1_)
	{
		if (p_70918_1_)
		{
			this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
		}
		else
		{
			this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
		}
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal)
	{
		if (otherAnimal == this)
		{
			return false;
		}
		else if (!this.isTamed())
		{
			return false;
		}
		else if (!(otherAnimal instanceof EntitySpaceWolf))
		{
			return false;
		}
		else
		{
			EntitySpaceWolf entitywolf = (EntitySpaceWolf)otherAnimal;
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
	public boolean func_142018_a(EntityLivingBase living, EntityLivingBase living2)
	{
		if (!(living instanceof EntityCreeper) && !(living instanceof EntityGhast))
		{
			if (living instanceof EntitySpaceWolf)
			{
				EntitySpaceWolf entitywolf = (EntitySpaceWolf)living;

				if (entitywolf.isTamed() && entitywolf.getOwnerEntity() == living2)
				{
					return false;
				}
			}
			return living instanceof EntityPlayer && living2 instanceof EntityPlayer && !((EntityPlayer)living2).canAttackPlayer((EntityPlayer)living) ? false : !(living instanceof EntityHorse) || !((EntityHorse)living).isTame();
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean allowLeashing()
	{
		return !this.isAngry() && super.allowLeashing();
	}

	/*@Override
	public boolean canBreath()
	{
		return true;
	}*/

	@Override
	protected void addRandomArmor()
	{
		switch (this.rand.nextInt(10))
		{
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			break;
		case 7:
			//Oxygen tank half empty or less
			//this.entityDropItem(new ItemStack(GCItems.oxTankMedium, 1, 901 + this.rand.nextInt(900)), 0.0F);
			break;
		case 8:
			this.dropItem(Item.getItemFromBlock(Blocks.glass), 1);
			break;
		case 9:
			//this.dropItem(GCItems.oxygenGear, 1);
			break;
		case 10:
			//this.dropItem(GCItems.oxMask, 1);
			break;
		}
	}
}