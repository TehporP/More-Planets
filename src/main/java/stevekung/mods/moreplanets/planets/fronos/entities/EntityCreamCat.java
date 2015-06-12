/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAICreamCatSit;

import com.google.common.base.Predicate;

public class EntityCreamCat extends EntityTameable
{
	private EntityAIAvoidEntity field_175545_bm;
	private EntityAITempt aiTempt;

	public EntityCreamCat(World world)
	{
		super(world);
		this.setSize(0.6F, 0.7F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, Items.fish, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
		this.tasks.addTask(6, new EntityAICreamCatSit(this, 0.8D));
		this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
		this.tasks.addTask(8, new EntityAIOcelotAttack(this));
		this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
		this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, false, (Predicate)null));
		this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityStrawberryChicken.class, false, (Predicate)null));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
	}

	@Override
	public void updateAITasks()
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("CatType", this.getTameSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		this.setTameSkin(tagCompund.getInteger("CatType"));
	}

	@Override
	protected String getLivingSound()
	{
		return this.isTamed() ? this.isInLove() ? "mob.cat.purr" : this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow" : null;
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
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
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
			this.aiSit.setSitting(false);
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	protected void dropFewItems(boolean drop, int par2) {}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemStack))
			{
				this.aiSit.setSitting(!this.isSitting());
			}
		}
		else if (this.aiTempt.isRunning() && itemStack != null && itemStack.getItem() == Items.fish && player.getDistanceSqToEntity(this) < 9.0D)
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
					this.setTameSkin(1 + this.worldObj.rand.nextInt(6));
					this.setOwnerId(player.getUniqueID().toString());
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
		if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1025)
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
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1025);
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		return itemStack != null && itemStack.getItem() == Items.fish;
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
		else if (!(otherAnimal instanceof EntityCreamCat))
		{
			return false;
		}
		else
		{
			EntityCreamCat entityocelot = (EntityCreamCat)otherAnimal;
			return !entityocelot.isTamed() ? false : this.isInLove() && entityocelot.isInLove();
		}
	}

	public int getTameSkin()
	{
		return this.dataWatcher.getWatchableObjectByte(18);
	}

	public void setTameSkin(int skinId)
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)skinId));
	}

	@Override
	public boolean handleLavaMovement()
	{
		if (this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()))
		{
			BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

			if (blockpos.getY() < 63)
			{
				return false;
			}

			Block block = this.worldObj.getBlockState(blockpos.down()).getBlock();

			if (block == Blocks.grass || block.isLeaves(this.worldObj, blockpos.down()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.getCustomNameTag() : this.isTamed() ? StatCollector.translateToLocal("entity.CreamCat.name") : super.getName();
	}

	@Override
	public void setTamed(boolean tamed)
	{
		super.setTamed(tamed);
	}

	@Override
	protected void setupTamedAI()
	{
		if (this.field_175545_bm == null)
		{
			this.field_175545_bm = new EntityAIAvoidEntity(this, new Predicate()
			{
				public boolean func_179874_a(Entity p_179874_1_)
				{
					return p_179874_1_ instanceof EntityPlayer;
				}
				@Override
				public boolean apply(Object p_apply_1_)
				{
					return this.func_179874_a((Entity)p_apply_1_);
				}
			}, 16.0F, 0.8D, 1.33D);
		}

		this.tasks.removeTask(this.field_175545_bm);

		if (!this.isTamed())
		{
			this.tasks.addTask(4, this.field_175545_bm);
		}
	}

	@Override
	public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
	{
		data = super.func_180482_a(diff, data);

		if (this.worldObj.rand.nextInt(7) == 0)
		{
			for (int i = 0; i < 2; ++i)
			{
				EntityCreamCat entityocelot = new EntityCreamCat(this.worldObj);
				entityocelot.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				entityocelot.setGrowingAge(-24000);
				this.worldObj.spawnEntityInWorld(entityocelot);
			}
		}
		return data;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		Block block = this.worldObj.getBlockState(this.getPosition().down()).getBlock();
		return block == FronosBlocks.fronos_grass;
	}

	@Override
	public EntityCreamCat createChild(EntityAgeable ageable)
	{
		EntityCreamCat cat = new EntityCreamCat(this.worldObj);

		if (this.isTamed())
		{
			cat.setOwnerId(this.getOwnerId());
			cat.setTamed(true);
			cat.setTameSkin(this.getTameSkin());
		}
		return cat;
	}
}