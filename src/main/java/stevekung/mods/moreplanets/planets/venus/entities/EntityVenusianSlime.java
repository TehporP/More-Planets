/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityVenusianSlime extends EntityLiving implements IMob/*, IEntityBreathable*/
{
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean field_175452_bi;

	public EntityVenusianSlime(World world)
	{
		super(world);
		this.isImmuneToFire = true;
		this.moveHelper = new SlimeMoveHelper();
		this.tasks.addTask(1, new AISlimeFloat());
		this.tasks.addTask(2, new AISlimeAttack());
		this.tasks.addTask(3, new AISlimeFaceRandom());
		this.tasks.addTask(4, new AISlimeHop());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		this.targetTasks.addTask(2, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1035);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)1));
	}

	@Override
	public boolean handleLavaMovement()
	{
		return false;
	}

	public void setSlimeSize(int size)
	{
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)size));
		this.setSize(0.51000005F * size, 0.51000005F * size);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(size * size);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F + 0.1F * size);
		this.setHealth(this.getMaxHealth());
		this.experienceValue = size;
	}

	public int getSlimeSize()
	{
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Size", this.getSlimeSize() - 1);
		nbt.setBoolean("WasOnGround", this.field_175452_bi);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		int i = nbt.getInteger("Size");

		if (i < 0)
		{
			i = 0;
		}
		this.setSlimeSize(i + 1);
		this.field_175452_bi = nbt.getBoolean("WasOnGround");
	}

	protected String getJumpSound()
	{
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	@Override
	public void onUpdate()
	{
		if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
		{
			this.isDead = true;
		}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.onUpdate();

		if (this.onGround && !this.field_175452_bi)
		{
			int i = this.getSlimeSize();

			for (int j = 0; j < i * 8; ++j)
			{
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * i * 0.5F * f1;
				float f3 = MathHelper.cos(f) * i * 0.5F * f1;
				double d0 = this.posX + f2;
				double d1 = this.posZ + f3;
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
			}

			if (this.makesSoundOnLand())
			{
				this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			}
			this.squishAmount = -0.5F;
		}
		else if (!this.onGround && this.field_175452_bi)
		{
			this.squishAmount = 1.0F;
		}
		this.field_175452_bi = this.onGround;
		this.alterSquishAmount();
	}

	protected void alterSquishAmount()
	{
		this.squishAmount *= 0.6F;
	}

	protected int getJumpDelay()
	{
		return this.rand.nextInt(20) + 10;
	}

	protected EntityVenusianSlime createInstance()
	{
		return new EntityVenusianSlime(this.worldObj);
	}

	@Override
	public void func_145781_i(int par1)
	{
		if (par1 == 16)
		{
			int j = this.getSlimeSize();
			this.setSize(0.51000005F * j, 0.51000005F * j);
			this.rotationYaw = this.rotationYawHead;
			this.renderYawOffset = this.rotationYawHead;

			if (this.isInWater() && this.rand.nextInt(20) == 0)
			{
				this.resetHeight();
			}
		}
		super.func_145781_i(par1);
	}

	@Override
	public void setDead()
	{
		int i = this.getSlimeSize();

		if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
		{
			int j = 2 + this.rand.nextInt(3);

			for (int k = 0; k < j; ++k)
			{
				float f = (k % 2 - 0.5F) * i / 4.0F;
				float f1 = (k / 2 - 0.5F) * i / 4.0F;
				EntityVenusianSlime entityslime = this.createInstance();

				if (this.hasCustomName())
				{
					entityslime.setCustomNameTag(this.getCustomNameTag());
				}
				if (this.isNoDespawnRequired())
				{
					entityslime.enablePersistence();
				}
				entityslime.setSlimeSize(i / 2);
				entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entityslime);
			}
		}
		super.setDead();
	}

	@Override
	public void applyEntityCollision(Entity entity)
	{
		super.applyEntityCollision(entity);

		if (entity instanceof EntityIronGolem && this.canDamagePlayer())
		{
			this.func_175451_e((EntityLivingBase)entity);
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (this.canDamagePlayer())
		{
			this.func_175451_e(player);
			player.setFire(5);
		}
	}

	protected void func_175451_e(EntityLivingBase living)
	{
		int i = this.getSlimeSize();

		if (this.canEntityBeSeen(living) && this.getDistanceSqToEntity(living) < 0.6D * i * 0.6D * i && living.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
		{
			this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.func_174815_a(this, living);
		}
	}

	@Override
	public float getEyeHeight()
	{
		return 0.625F * this.height;
	}

	protected boolean canDamagePlayer()
	{
		return this.getSlimeSize() > 1;
	}

	protected int getAttackStrength()
	{
		return this.getSlimeSize();
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	@Override
	protected void dropFewItems(boolean drop, int fortune)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + fortune);

		if (this.getSlimeSize() == 1)
		{
			for (int i = 0; i < j; ++i)
			{
				this.entityDropItem(new ItemStack(Items.magma_cream, 1), 0.0F);
			}
		}
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F * this.getSlimeSize();
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return 0;
	}

	protected boolean makesSoundOnJump()
	{
		return this.getSlimeSize() > 0;
	}

	protected boolean makesSoundOnLand()
	{
		return this.getSlimeSize() > 2;
	}

	@Override
	protected void jump()
	{
		this.motionY = 0.41999998688697815D;
		this.isAirBorne = true;
	}

	@Override
	public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
	{
		int i = this.rand.nextInt(3);

		if (i < 2 && this.rand.nextFloat() < 0.5F * p_180482_1_.getClampedAdditionalDifficulty())
		{
			++i;
		}
		int j = 1 << i;
		this.setSlimeSize(j);
		return super.func_180482_a(p_180482_1_, p_180482_2_);
	}

	/*@Override
	public boolean canBreath()
	{
		return true;
	}*/

	class AISlimeAttack extends EntityAIBase
	{
		private EntityVenusianSlime field_179466_a = EntityVenusianSlime.this;
		private int field_179465_b;

		public AISlimeAttack()
		{
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute()
		{
			EntityLivingBase entitylivingbase = this.field_179466_a.getAttackTarget();
			return entitylivingbase == null ? false : entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting()
		{
			this.field_179465_b = 300;
			super.startExecuting();
		}

		@Override
		public boolean continueExecuting()
		{
			EntityLivingBase entitylivingbase = this.field_179466_a.getAttackTarget();
			return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : --this.field_179465_b > 0;
		}

		@Override
		public void updateTask()
		{
			this.field_179466_a.faceEntity(this.field_179466_a.getAttackTarget(), 10.0F, 10.0F);
			((EntityVenusianSlime.SlimeMoveHelper)this.field_179466_a.getMoveHelper()).func_179920_a(this.field_179466_a.rotationYaw, this.field_179466_a.canDamagePlayer());
		}
	}

	class AISlimeFaceRandom extends EntityAIBase
	{
		private EntityVenusianSlime field_179461_a = EntityVenusianSlime.this;
		private float field_179459_b;
		private int field_179460_c;

		public AISlimeFaceRandom()
		{
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute()
		{
			return this.field_179461_a.getAttackTarget() == null && (this.field_179461_a.onGround || this.field_179461_a.isInWater() || this.field_179461_a.isInLava());
		}

		@Override
		public void updateTask()
		{
			if (--this.field_179460_c <= 0)
			{
				this.field_179460_c = 40 + this.field_179461_a.getRNG().nextInt(60);
				this.field_179459_b = this.field_179461_a.getRNG().nextInt(360);
			}
			((EntityVenusianSlime.SlimeMoveHelper)this.field_179461_a.getMoveHelper()).func_179920_a(this.field_179459_b, false);
		}
	}

	class AISlimeFloat extends EntityAIBase
	{
		private EntityVenusianSlime field_179457_a = EntityVenusianSlime.this;

		public AISlimeFloat()
		{
			this.setMutexBits(5);
			((PathNavigateGround)EntityVenusianSlime.this.getNavigator()).func_179693_d(true);
		}

		@Override
		public boolean shouldExecute()
		{
			return this.field_179457_a.isInWater() || this.field_179457_a.isInLava();
		}

		@Override
		public void updateTask()
		{
			if (this.field_179457_a.getRNG().nextFloat() < 0.8F)
			{
				this.field_179457_a.getJumpHelper().setJumping();
			}
			((EntityVenusianSlime.SlimeMoveHelper)this.field_179457_a.getMoveHelper()).func_179921_a(1.2D);
		}
	}

	class AISlimeHop extends EntityAIBase
	{
		private EntityVenusianSlime field_179458_a = EntityVenusianSlime.this;

		public AISlimeHop()
		{
			this.setMutexBits(5);
		}

		@Override
		public boolean shouldExecute()
		{
			return true;
		}

		@Override
		public void updateTask()
		{
			((SlimeMoveHelper)this.field_179458_a.getMoveHelper()).func_179921_a(1.0D);
		}
	}

	class SlimeMoveHelper extends EntityMoveHelper
	{
		private float field_179922_g;
		private int field_179924_h;
		private EntityVenusianSlime field_179925_i = EntityVenusianSlime.this;
		private boolean field_179923_j;

		public SlimeMoveHelper()
		{
			super(EntityVenusianSlime.this);
		}

		public void func_179920_a(float p_179920_1_, boolean p_179920_2_)
		{
			this.field_179922_g = p_179920_1_;
			this.field_179923_j = p_179920_2_;
		}

		public void func_179921_a(double p_179921_1_)
		{
			this.speed = p_179921_1_;
			this.update = true;
		}

		@Override
		public void onUpdateMoveHelper()
		{
			this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.field_179922_g, 30.0F);
			this.entity.rotationYawHead = this.entity.rotationYaw;
			this.entity.renderYawOffset = this.entity.rotationYaw;

			if (!this.update)
			{
				this.entity.setMoveForward(0.0F);
			}
			else
			{
				this.update = false;

				if (this.entity.onGround)
				{
					this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));

					if (this.field_179924_h-- <= 0)
					{
						this.field_179924_h = this.field_179925_i.getJumpDelay();

						if (this.field_179923_j)
						{
							this.field_179924_h /= 3;
						}

						this.field_179925_i.getJumpHelper().setJumping();

						if (this.field_179925_i.makesSoundOnJump())
						{
							this.field_179925_i.playSound(this.field_179925_i.getJumpSound(), this.field_179925_i.getSoundVolume(), ((this.field_179925_i.getRNG().nextFloat() - this.field_179925_i.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
						}
					}
					else
					{
						this.field_179925_i.moveStrafing = this.field_179925_i.moveForward = 0.0F;
						this.entity.setAIMoveSpeed(0.0F);
					}
				}
				else
				{
					this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
				}
			}
		}
	}
}