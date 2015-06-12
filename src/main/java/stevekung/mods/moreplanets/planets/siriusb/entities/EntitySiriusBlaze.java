/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.entities.ISiriusMob;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;

public class EntitySiriusBlaze extends EntityMob implements /*IEntityBreathable,*/ ISiriusMob
{
	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;

	public EntitySiriusBlaze(World world)
	{
		super(world);
		this.isImmuneToFire = true;
		this.experienceValue = 10;
		this.tasks.addTask(4, new AIFireballAttack());
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1031);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.blaze.breathe";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.blaze.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.blaze.death";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float light)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float light)
	{
		return 1.0F;
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.onGround && this.motionY < 0.0D)
		{
			this.motionY *= 0.6D;
		}

		if (this.worldObj.isRemote)
		{
			if (this.rand.nextInt(24) == 0 && !this.isSilent())
			{
				this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
			}

			for (int i = 0; i < 2; ++i)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks()
	{
		if (this.isWet())
		{
			this.attackEntityFrom(DamageSource.drown, 1.0F);
		}

		--this.heightOffsetUpdateTime;

		if (this.heightOffsetUpdateTime <= 0)
		{
			this.heightOffsetUpdateTime = 100;
			this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
		}

		EntityLivingBase entitylivingbase = this.getAttackTarget();

		if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset)
		{
			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
			this.isAirBorne = true;
		}
		super.updateAITasks();
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	protected Item getDropItem()
	{
		return Items.blaze_rod;
	}

	@Override
	public boolean isBurning()
	{
		return this.isBurn();
	}

	@Override
	protected void dropFewItems(boolean drop, int fortune)
	{
		if (drop)
		{
			int j = this.rand.nextInt(2 + fortune);

			for (int i = 0; i < j; ++i)
			{
				this.dropItem(Items.blaze_rod, 1);
			}
		}
	}

	public boolean isBurn()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void func_70844_e(boolean burn)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (burn)
		{
			b0 = (byte)(b0 | 1);
		}
		else
		{
			b0 &= -2;
		}
		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	//	@Override
	//	public boolean canBreath()
	//	{
	//		return true;
	//	}

	@Override
	public boolean canLivingInSirius()
	{
		return true;
	}

	class AIFireballAttack extends EntityAIBase
	{
		private EntitySiriusBlaze field_179469_a = EntitySiriusBlaze.this;
		private int field_179467_b;
		private int field_179468_c;

		public AIFireballAttack()
		{
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute()
		{
			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting()
		{
			this.field_179467_b = 0;
		}

		@Override
		public void resetTask()
		{
			this.field_179469_a.func_70844_e(false);
		}

		@Override
		public void updateTask()
		{
			--this.field_179468_c;
			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
			double d0 = this.field_179469_a.getDistanceSqToEntity(entitylivingbase);

			if (d0 < 4.0D)
			{
				if (this.field_179468_c <= 0)
				{
					this.field_179468_c = 20;
					this.field_179469_a.attackEntityAsMob(entitylivingbase);
				}
				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}
			else if (d0 < 256.0D)
			{
				double d1 = entitylivingbase.posX - this.field_179469_a.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.field_179469_a.posY + this.field_179469_a.height / 2.0F);
				double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;

				if (this.field_179468_c <= 0)
				{
					++this.field_179467_b;

					if (this.field_179467_b == 1)
					{
						this.field_179468_c = 60;
						this.field_179469_a.func_70844_e(true);
					}
					else if (this.field_179467_b <= 4)
					{
						this.field_179468_c = 6;
					}
					else
					{
						this.field_179468_c = 100;
						this.field_179467_b = 0;
						this.field_179469_a.func_70844_e(false);
					}

					if (this.field_179467_b > 1)
					{
						float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0)) * 0.5F;
						this.field_179469_a.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);

						for (int i = 0; i < 1; ++i)
						{
							EntitySiriusSmallFireball entitysmallfireball = new EntitySiriusSmallFireball(this.field_179469_a.worldObj, this.field_179469_a, d1 + this.field_179469_a.getRNG().nextGaussian() * f, d2, d3 + this.field_179469_a.getRNG().nextGaussian() * f);
							entitysmallfireball.setCanExplode(false);
							entitysmallfireball.posY = this.field_179469_a.posY + this.field_179469_a.height / 2.0F + 0.5D;
							this.field_179469_a.worldObj.spawnEntityInWorld(entitysmallfireball);
						}
					}
				}
				this.field_179469_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
			}
			else
			{
				this.field_179469_a.getNavigator().clearPathEntity();
				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}
			super.updateTask();
		}
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.getBoundingBox()) && this.worldObj.getCollidingBoundingBoxes(this, this.getBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getBoundingBox()) && this.worldObj.getLightBrightness(this.getPosition()) >= 0.0F)
		{
			return true;
		}
		return false;
	}
}