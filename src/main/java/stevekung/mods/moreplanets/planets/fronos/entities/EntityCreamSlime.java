/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityCreamSlime extends EntityLiving implements IMob
{
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;

	public EntityCreamSlime(World world)
	{
		super(world);
		this.moveHelper = new EntityCreamSlime.SlimeMoveHelper();
		this.tasks.addTask(1, new EntityCreamSlime.AISlimeFloat());
		this.tasks.addTask(2, new EntityCreamSlime.AISlimeFaceRandom());
		this.tasks.addTask(3, new EntityCreamSlime.AISlimeHop());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		this.targetTasks.addTask(2, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
		this.setCreamSlimeType(this.rand.nextInt(6));
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1019);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)1));
		this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
	}

	protected void setSlimeSize(int size)
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
		nbt.setBoolean("WasOnGround", this.wasOnGround);
		nbt.setInteger("SlimeType", this.getCreamSlimeType());
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
		this.setCreamSlimeType(nbt.getInteger("SlimeType"));
		this.wasOnGround = nbt.getBoolean("WasOnGround");
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

		if (this.onGround && !this.wasOnGround)
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
				int particles = this.getCreamSlimeType();

				if (particles == 0)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.VANILLA_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
				else if (particles == 1)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.CHOCOLATE_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
				else if (particles == 2)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.STRAWBERRY_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
				else if (particles == 3)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.ORANGE_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
				else if (particles == 4)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.TEA_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
				else if (particles == 5)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.LEMON_CREAM_BALL, d0, this.getEntityBoundingBox().minY, d1);
				}
			}

			if (this.makesSoundOnLand())
			{
				this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			}
			this.squishAmount = -0.5F;
		}
		else if (!this.onGround && this.wasOnGround)
		{
			this.squishAmount = 1.0F;
		}
		this.wasOnGround = this.onGround;
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

	protected EntityCreamSlime createInstance()
	{
		return new EntityCreamSlime(this.worldObj);
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
				EntityCreamSlime entityslime = this.createInstance();

				if (this.hasCustomName())
				{
					entityslime.setCustomNameTag(this.getCustomNameTag());
				}

				if (this.isNoDespawnRequired())
				{
					entityslime.enablePersistence();
				}

				entityslime.setCreamSlimeType(this.getCreamSlimeType());
				entityslime.setSlimeSize(i / 2);
				entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entityslime);
			}
		}
		super.setDead();
	}

	public int getCreamSlimeType()
	{
		return this.dataWatcher.getWatchableObjectByte(17);
	}

	public void setCreamSlimeType(int par1)
	{
		this.dataWatcher.updateObject(17, Byte.valueOf((byte)par1));
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		if (this.getSlimeSize() == 1)
		{
			int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

			for (int i = 0; i < j; ++i)
			{
				this.entityDropItem(new ItemStack(FronosItems.cream_ball, 1, this.getCreamSlimeType()), 0.0F);
			}
		}
	}

	@Override
	public float getEyeHeight()
	{
		return 0.625F * this.height;
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
	public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
	{
		int i = this.rand.nextInt(2);

		if (i < 2 && this.rand.nextFloat() < 0.5F * diff.getClampedAdditionalDifficulty())
		{
			++i;
		}
		int j = 1 << i;
		this.setSlimeSize(j);
		return super.func_180482_a(diff, data);
	}

	class AISlimeFaceRandom extends EntityAIBase
	{
		private EntityCreamSlime field_179461_a = EntityCreamSlime.this;
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
			((EntityCreamSlime.SlimeMoveHelper)this.field_179461_a.getMoveHelper()).func_179920_a(this.field_179459_b, false);
		}
	}

	class AISlimeFloat extends EntityAIBase
	{
		private EntityCreamSlime field_179457_a = EntityCreamSlime.this;

		public AISlimeFloat()
		{
			this.setMutexBits(5);
			((PathNavigateGround)EntityCreamSlime.this.getNavigator()).func_179693_d(true);
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
			((EntityCreamSlime.SlimeMoveHelper)this.field_179457_a.getMoveHelper()).func_179921_a(1.2D);
		}
	}

	class AISlimeHop extends EntityAIBase
	{
		private EntityCreamSlime field_179458_a = EntityCreamSlime.this;

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
			((EntityCreamSlime.SlimeMoveHelper)this.field_179458_a.getMoveHelper()).func_179921_a(1.0D);
		}
	}

	class SlimeMoveHelper extends EntityMoveHelper
	{
		private float field_179922_g;
		private int field_179924_h;
		private EntityCreamSlime field_179925_i = EntityCreamSlime.this;
		private boolean field_179923_j;

		public SlimeMoveHelper()
		{
			super(EntityCreamSlime.this);
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