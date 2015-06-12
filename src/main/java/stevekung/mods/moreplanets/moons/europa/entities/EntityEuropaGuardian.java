/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.entities;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;

import com.google.common.base.Predicate;

public class EntityEuropaGuardian extends EntityMob
{
	private float field_175482_b;
	private float field_175484_c;
	private float field_175483_bk;
	private float field_175485_bl;
	private float field_175486_bm;
	private EntityLivingBase field_175478_bn;
	private int field_175479_bo;
	private boolean field_175480_bp;
	private EntityAIWander wander;

	public EntityEuropaGuardian(World world)
	{
		super(world);
		this.experienceValue = 10;
		this.setSize(0.85F, 0.85F);
		this.tasks.addTask(4, new EntityEuropaGuardian.AIGuardianAttack());
		EntityAIMoveTowardsRestriction entityaimovetowardsrestriction;
		this.tasks.addTask(5, entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0D, 80));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityEuropaGuardian.class, 12.0F, 0.01F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.wander.setMutexBits(3);
		entityaimovetowardsrestriction.setMutexBits(3);
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new EntityEuropaGuardian.GuardianTargetSelector()));
		this.moveHelper = new EntityEuropaGuardian.GuardianMoveHelper();
		this.field_175484_c = this.field_175482_b = this.rand.nextFloat();
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1038);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		this.func_175467_a(tagCompund.getBoolean("Elder"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setBoolean("Elder", this.isElder());
	}

	@Override
	protected PathNavigate func_175447_b(World world)
	{
		return new PathNavigateSwimmer(this, world);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
	}

	private boolean func_175468_a(int p_175468_1_)
	{
		return (this.dataWatcher.getWatchableObjectInt(16) & p_175468_1_) != 0;
	}

	private void func_175473_a(int p_175473_1_, boolean p_175473_2_)
	{
		int j = this.dataWatcher.getWatchableObjectInt(16);

		if (p_175473_2_)
		{
			this.dataWatcher.updateObject(16, Integer.valueOf(j | p_175473_1_));
		}
		else
		{
			this.dataWatcher.updateObject(16, Integer.valueOf(j & ~p_175473_1_));
		}
	}

	public boolean func_175472_n()
	{
		return this.func_175468_a(2);
	}

	private void func_175476_l(boolean p_175476_1_)
	{
		this.func_175473_a(2, p_175476_1_);
	}

	public int func_175464_ck()
	{
		return this.isElder() ? 60 : 80;
	}

	public boolean isElder()
	{
		return this.func_175468_a(4);
	}

	public void func_175467_a(boolean p_175467_1_)
	{
		this.func_175473_a(4, p_175467_1_);

		if (p_175467_1_)
		{
			this.setSize(1.9975F, 1.9975F);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
			this.enablePersistence();
			this.wander.func_179479_b(400);
		}
	}

	@SideOnly(Side.CLIENT)
	public void func_175465_cm()
	{
		this.func_175467_a(true);
		this.field_175486_bm = this.field_175485_bl = 1.0F;
	}

	private void func_175463_b(int p_175463_1_)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(p_175463_1_));
	}

	public boolean func_175474_cn()
	{
		return this.dataWatcher.getWatchableObjectInt(17) != 0;
	}

	public EntityLivingBase getTargetedEntity()
	{
		if (!this.func_175474_cn())
		{
			return null;
		}
		else if (this.worldObj.isRemote)
		{
			if (this.field_175478_bn != null)
			{
				return this.field_175478_bn;
			}
			else
			{
				Entity entity = this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(17));

				if (entity instanceof EntityLivingBase)
				{
					this.field_175478_bn = (EntityLivingBase)entity;
					return this.field_175478_bn;
				}
				else
				{
					return null;
				}
			}
		}
		else
		{
			return this.getAttackTarget();
		}
	}

	@Override
	public void func_145781_i(int p_145781_1_)
	{
		super.func_145781_i(p_145781_1_);

		if (p_145781_1_ == 16)
		{
			if (this.isElder() && this.width < 1.0F)
			{
				this.setSize(1.9975F, 1.9975F);
			}
		}
		else if (p_145781_1_ == 17)
		{
			this.field_175479_bo = 0;
			this.field_175478_bn = null;
		}
	}

	@Override
	public int getTalkInterval()
	{
		return 160;
	}

	@Override
	protected String getLivingSound()
	{
		return !this.isInWater() ? "mob.guardian.land.idle" : this.isElder() ? "mob.guardian.elder.idle" : "mob.guardian.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return !this.isInWater() ? "mob.guardian.land.hit" : this.isElder() ? "mob.guardian.elder.hit" : "mob.guardian.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return !this.isInWater() ? "mob.guardian.land.death" : this.isElder() ? "mob.guardian.elder.death" : "mob.guardian.death";
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.5F;
	}

	@Override
	public float func_180484_a(BlockPos p_180484_1_)
	{
		return this.worldObj.getBlockState(p_180484_1_).getBlock().getMaterial() == Material.water ? 10.0F + this.worldObj.getLightBrightness(p_180484_1_) - 0.5F : super.func_180484_a(p_180484_1_);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			this.field_175484_c = this.field_175482_b;

			if (!this.isInWater())
			{
				this.field_175483_bk = 2.0F;

				if (this.motionY > 0.0D && this.field_175480_bp && !this.isSilent())
				{
					this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
				}
				this.field_175480_bp = this.motionY < 0.0D && this.worldObj.isBlockNormalCube(new BlockPos(this).down(), false);
			}
			else if (this.func_175472_n())
			{
				if (this.field_175483_bk < 0.5F)
				{
					this.field_175483_bk = 4.0F;
				}
				else
				{
					this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
				}
			}
			else
			{
				this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
			}

			this.field_175482_b += this.field_175483_bk;
			this.field_175486_bm = this.field_175485_bl;

			if (!this.isInWater())
			{
				this.field_175485_bl = this.rand.nextFloat();
			}
			else if (this.func_175472_n())
			{
				this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
			}
			else
			{
				this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
			}

			if (this.func_175472_n() && this.isInWater())
			{
				Vec3 vec3 = this.getLook(0.0F);

				for (int i = 0; i < 2; ++i)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width - vec3.xCoord * 1.5D, this.posY + this.rand.nextDouble() * this.height - vec3.yCoord * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width - vec3.zCoord * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			if (this.func_175474_cn())
			{
				if (this.field_175479_bo < this.func_175464_ck())
				{
					++this.field_175479_bo;
				}

				EntityLivingBase entitylivingbase = this.getTargetedEntity();

				if (entitylivingbase != null)
				{
					this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
					this.getLookHelper().onUpdateLook();
					double d5 = this.func_175477_p(0.0F);
					double d0 = entitylivingbase.posX - this.posX;
					double d1 = entitylivingbase.posY + entitylivingbase.height * 0.5F - (this.posY + this.getEyeHeight());
					double d2 = entitylivingbase.posZ - this.posZ;
					double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					d0 /= d3;
					d1 /= d3;
					d2 /= d3;
					double d4 = this.rand.nextDouble();

					while (d4 < d3)
					{
						d4 += 1.8D - d5 + this.rand.nextDouble() * (1.7D - d5);
						this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + d0 * d4, this.posY + d1 * d4 + this.getEyeHeight(), this.posZ + d2 * d4, 0.0D, 0.0D, 0.0D, new int[0]);
					}
				}
			}
		}

		if (this.inWater)
		{
			this.setAir(300);
		}
		else if (this.onGround)
		{
			this.motionY += 0.5D;
			this.motionX += (this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F;
			this.motionZ += (this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F;
			this.rotationYaw = this.rand.nextFloat() * 360.0F;
			this.onGround = false;
			this.isAirBorne = true;
		}

		if (this.func_175474_cn())
		{
			this.rotationYaw = this.rotationYawHead;
		}
		super.onLivingUpdate();
	}

	@SideOnly(Side.CLIENT)
	public float func_175471_a(float p_175471_1_)
	{
		return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
	}

	@SideOnly(Side.CLIENT)
	public float func_175469_o(float p_175469_1_)
	{
		return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
	}

	public float func_175477_p(float p_175477_1_)
	{
		return (this.field_175479_bo + p_175477_1_) / this.func_175464_ck();
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();

		if (this.isElder())
		{
			if ((this.ticksExisted + this.getEntityId()) % 1200 == 0)
			{
				Potion potion = Potion.digSlowdown;
				List list = this.worldObj.getPlayers(EntityPlayerMP.class, new Predicate()
				{
					public boolean func_179913_a(EntityPlayerMP p_179913_1_)
					{
						return EntityEuropaGuardian.this.getDistanceSqToEntity(p_179913_1_) < 2500.0D && p_179913_1_.theItemInWorldManager.func_180239_c();
					}
					@Override
					public boolean apply(Object p_apply_1_)
					{
						return this.func_179913_a((EntityPlayerMP)p_apply_1_);
					}
				});
				Iterator iterator = list.iterator();

				while (iterator.hasNext())
				{
					EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();

					if (!entityplayermp.isPotionActive(potion) || entityplayermp.getActivePotionEffect(potion).getAmplifier() < 2 || entityplayermp.getActivePotionEffect(potion).getDuration() < 1200)
					{
						entityplayermp.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(10, 0.0F));
						entityplayermp.addPotionEffect(new PotionEffect(potion.id, 6000, 2));
					}
				}
			}

			if (!this.hasHome())
			{
				this.func_175449_a(new BlockPos(this), 16);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean drop, int fortune)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(fortune + 1);

		if (j > 0)
		{
			this.entityDropItem(new ItemStack(EuropaItems.europa_prismarine, j, 0), 1.0F);
		}

		if (this.rand.nextInt(3 + fortune) > 1)
		{
			this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 1.0F);
		}
		else if (this.rand.nextInt(3 + fortune) > 1)
		{
			this.entityDropItem(new ItemStack(EuropaItems.europa_prismarine, 1, 1), 1.0F);
		}

		if (drop && this.isElder())
		{
			this.entityDropItem(new ItemStack(Blocks.sponge, 1, 1), 1.0F);
		}
	}

	@Override
	protected void addRandomArmor()
	{
		ItemStack itemstack = ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, EntityFishHook.func_174855_j())).getItemStack(this.rand);
		this.entityDropItem(itemstack, 1.0F);
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public boolean handleLavaMovement()
	{
		return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty();
	}

	@Override
	public boolean getCanSpawnHere()
	{
		//TODO Europa World Provider
		return (this.rand.nextInt(20) == 0 || !this.worldObj.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (!this.func_175472_n() && !source.isMagicDamage() && source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)source.getSourceOfDamage();

			if (!source.isExplosion())
			{
				entitylivingbase.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
				entitylivingbase.playSound(null, 0.5F, 1.0F);
			}
		}
		this.wander.func_179480_f();
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return 180;
	}

	@Override
	public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
	{
		if (this.isServerWorld())
		{
			if (this.isInWater())
			{
				this.moveFlying(p_70612_1_, p_70612_2_, 0.1F);
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				this.motionX *= 0.8999999761581421D;
				this.motionY *= 0.8999999761581421D;
				this.motionZ *= 0.8999999761581421D;

				if (!this.func_175472_n() && this.getAttackTarget() == null)
				{
					this.motionY -= 0.005D;
				}
			}
			else
			{
				super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
			}
		}
		else
		{
			super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
		}
	}

	class AIGuardianAttack extends EntityAIBase
	{
		private EntityEuropaGuardian field_179456_a = EntityEuropaGuardian.this;
		private int field_179455_b;

		public AIGuardianAttack()
		{
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute()
		{
			EntityLivingBase entitylivingbase = this.field_179456_a.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public boolean continueExecuting()
		{
			return super.continueExecuting() && (this.field_179456_a.isElder() || this.field_179456_a.getDistanceSqToEntity(this.field_179456_a.getAttackTarget()) > 9.0D);
		}

		@Override
		public void startExecuting()
		{
			this.field_179455_b = -10;
			this.field_179456_a.getNavigator().clearPathEntity();
			this.field_179456_a.getLookHelper().setLookPositionWithEntity(this.field_179456_a.getAttackTarget(), 90.0F, 90.0F);
			this.field_179456_a.isAirBorne = true;
		}

		@Override
		public void resetTask()
		{
			this.field_179456_a.func_175463_b(0);
			this.field_179456_a.setAttackTarget((EntityLivingBase)null);
			this.field_179456_a.wander.func_179480_f();
		}

		@Override
		public void updateTask()
		{
			EntityLivingBase entitylivingbase = this.field_179456_a.getAttackTarget();
			this.field_179456_a.getNavigator().clearPathEntity();
			this.field_179456_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);

			if (!this.field_179456_a.canEntityBeSeen(entitylivingbase))
			{
				this.field_179456_a.setAttackTarget((EntityLivingBase)null);
			}
			else
			{
				++this.field_179455_b;

				if (this.field_179455_b == 0)
				{
					this.field_179456_a.func_175463_b(this.field_179456_a.getAttackTarget().getEntityId());
					//this.field_179456_a.worldObj.setEntityState(this.field_179456_a, (byte)21); //FIXME
				}
				else if (this.field_179455_b >= this.field_179456_a.func_175464_ck())
				{
					float f = 1.0F;

					if (this.field_179456_a.worldObj.getDifficulty() == EnumDifficulty.HARD)
					{
						f += 2.0F;
					}

					if (this.field_179456_a.isElder())
					{
						f += 2.0F;
					}
					entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.field_179456_a, this.field_179456_a), f);
					entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.field_179456_a), (float)this.field_179456_a.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
					this.field_179456_a.setAttackTarget((EntityLivingBase)null);
				}
				else if (this.field_179455_b >= 60 && this.field_179455_b % 20 == 0) {}
				super.updateTask();
			}
		}
	}

	class GuardianMoveHelper extends EntityMoveHelper
	{
		private EntityEuropaGuardian field_179930_g = EntityEuropaGuardian.this;

		public GuardianMoveHelper()
		{
			super(EntityEuropaGuardian.this);
		}

		@Override
		public void onUpdateMoveHelper()
		{
			if (this.update && !this.field_179930_g.getNavigator().noPath())
			{
				double d0 = this.posX - this.field_179930_g.posX;
				double d1 = this.posY - this.field_179930_g.posY;
				double d2 = this.posZ - this.field_179930_g.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				d3 = MathHelper.sqrt_double(d3);
				d1 /= d3;
				float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
				this.field_179930_g.rotationYaw = this.limitAngle(this.field_179930_g.rotationYaw, f, 30.0F);
				this.field_179930_g.renderYawOffset = this.field_179930_g.rotationYaw;
				float f1 = (float)(this.speed * this.field_179930_g.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				this.field_179930_g.setAIMoveSpeed(this.field_179930_g.getAIMoveSpeed() + (f1 - this.field_179930_g.getAIMoveSpeed()) * 0.125F);
				double d4 = Math.sin((this.field_179930_g.ticksExisted + this.field_179930_g.getEntityId()) * 0.5D) * 0.05D;
				double d5 = Math.cos(this.field_179930_g.rotationYaw * (float)Math.PI / 180.0F);
				double d6 = Math.sin(this.field_179930_g.rotationYaw * (float)Math.PI / 180.0F);
				this.field_179930_g.motionX += d4 * d5;
				this.field_179930_g.motionZ += d4 * d6;
				d4 = Math.sin((this.field_179930_g.ticksExisted + this.field_179930_g.getEntityId()) * 0.75D) * 0.05D;
				this.field_179930_g.motionY += d4 * (d6 + d5) * 0.25D;
				this.field_179930_g.motionY += this.field_179930_g.getAIMoveSpeed() * d1 * 0.1D;
				EntityLookHelper entitylookhelper = this.field_179930_g.getLookHelper();
				double d7 = this.field_179930_g.posX + d0 / d3 * 2.0D;
				double d8 = this.field_179930_g.getEyeHeight() + this.field_179930_g.posY + d1 / d3 * 1.0D;
				double d9 = this.field_179930_g.posZ + d2 / d3 * 2.0D;
				double d10 = entitylookhelper.func_180423_e();
				double d11 = entitylookhelper.func_180422_f();
				double d12 = entitylookhelper.func_180421_g();

				if (!entitylookhelper.func_180424_b())
				{
					d10 = d7;
					d11 = d8;
					d12 = d9;
				}
				this.field_179930_g.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
				this.field_179930_g.func_175476_l(true);
			}
			else
			{
				this.field_179930_g.setAIMoveSpeed(0.0F);
				this.field_179930_g.func_175476_l(false);
			}
		}
	}

	private class GuardianTargetSelector implements Predicate
	{
		private EntityEuropaGuardian field_179916_a = EntityEuropaGuardian.this;

		public boolean func_179915_a(EntityLivingBase entity)
		{
			return (entity instanceof EntityPlayer || entity instanceof EntityEuropaSquid || entity instanceof EntitySquid) && entity.getDistanceSqToEntity(this.field_179916_a) > 9.0D;
		}

		@Override
		public boolean apply(Object entity)
		{
			return this.func_179915_a((EntityLivingBase)entity);
		}
	}
}