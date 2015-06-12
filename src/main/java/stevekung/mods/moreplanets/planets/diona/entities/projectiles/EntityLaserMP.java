/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.projectiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.util.DamageSourceMP;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;

public class EntityLaserMP extends Entity implements IProjectile
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	public Entity shootingEntity;
	private int ticksInAir;
	private float damage;

	public EntityLaserMP(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.1F, 0.1F);
	}

	public EntityLaserMP(World world, double x, double y, double z)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.1F, 0.1F);
		this.setPosition(x, y, z);
	}

	public EntityLaserMP(World world, EntityLivingBase shooter, EntityLivingBase living, float par4, float par5)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;
		this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
		double d0 = living.posX - shooter.posX;
		double d1 = living.getEntityBoundingBox().minY + living.height / 3.0F - this.posY;
		double d2 = living.posZ - shooter.posZ;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
			float f4 = (float)(d3 * 0.20000000298023224D);
			this.setThrowableHeading(d0, d1 + f4, d2, par4, par5);
		}
	}

	public EntityLaserMP(World world, EntityLivingBase shooter, float par3)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;
		this.setSize(0.1F, 0.1F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
		this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy)
	{
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= f2;
		y /= f2;
		z /= f2;
		x += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		y += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		z += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		x *= velocity;
		y *= velocity;
		z *= velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f3) * 180.0D / Math.PI);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_180426_a(double x, double y, double z, float par4, float par5, int par6, boolean par7)
	{
		this.setPosition(x, y, z);
		this.setRotation(par4, par5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z)
	{
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(x * x + z * z);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / Math.PI);
		}

		BlockPos pos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState state = this.worldObj.getBlockState(pos);
		Block block = state.getBlock();

		if (block.getMaterial() != Material.air)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, pos);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, pos, state);

			if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ)))
			{
				this.setDead();
			}
		}

		++this.ticksInAir;
		Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
		Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition moving = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
		vec31 = new Vec3(this.posX, this.posY, this.posZ);
		vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (moving != null)
		{
			vec3 = new Vec3(moving.hitVec.xCoord, moving.hitVec.yCoord, moving.hitVec.zCoord);
		}

		Entity entity = null;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = 0.0D;
		int i;
		float f1;

		for (i = 0; i < list.size(); ++i)
		{
			Entity entity1 = (Entity)list.get(i);

			if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
			{
				f1 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f1, f1, f1);
				MovingObjectPosition moving1 = axisalignedbb1.calculateIntercept(vec31, vec3);

				if (moving1 != null)
				{
					double d1 = vec31.distanceTo(moving1.hitVec);

					if (d1 < d0 || d0 == 0.0D)
					{
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		if (entity != null)
		{
			moving = new MovingObjectPosition(entity);
		}

		if (moving != null && moving.entityHit != null && moving.entityHit instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)moving.entityHit;

			if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
			{
				moving = null;
			}
		}

		float f2;
		float f3;

		if (moving != null)
		{
			if (moving.entityHit != null)
			{
				DamageSource damagesource;
				int type = this.getLaserType();

				if (this.shootingEntity == null)
				{
					damagesource = DamageSourceMP.causeLaserDamage(this, this);
				}
				else
				{
					damagesource = DamageSourceMP.causeLaserDamage(this, this.shootingEntity);
				}

				if (type == 0)
				{
					this.damage = 6.0F;
				}
				if (type == 1)
				{
					this.damage = 10.0F;
				}
				if (type == 2)
				{
					this.damage = 7.5F;

					if (moving.entityHit instanceof EntityLivingBase)
					{
						if (!this.worldObj.isRemote)
						{
							((EntityLivingBase)moving.entityHit).addPotionEffect(new PotionEffect(MPPotions.electro_magnetic_pulse.id, 480));
						}
					}
				}
				if (type == 3)
				{
					this.damage = 9.5F;

					if (moving.entityHit instanceof EntityLivingBase)
					{
						if (!this.worldObj.isRemote)
						{
							((EntityLivingBase)moving.entityHit).addPotionEffect(new PotionEffect(MPPotions.chemical.id, 256));
						}
					}
				}
				if (type == 4)
				{
					this.damage = 10.0F;

					if (moving.entityHit instanceof EntityLivingBase)
					{
						if (!this.worldObj.isRemote)
						{
							((EntityLivingBase)moving.entityHit).addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 128));
						}
					}
				}
				if (moving.entityHit.attackEntityFrom(damagesource, this.damage))
				{
					if (moving.entityHit instanceof EntityLivingBase)
					{
						EntityLivingBase entitylivingbase = (EntityLivingBase)moving.entityHit;

						if (!this.worldObj.isRemote)
						{
							entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
						}
						if (this.shootingEntity instanceof EntityLivingBase)
						{
							EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
							EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
						}
						if (this.shootingEntity != null && moving.entityHit != this.shootingEntity && moving.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
						{
							((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
						}
					}
					if (!(moving.entityHit instanceof EntityEnderman) || !(moving.entityHit instanceof EntityEvolvedEnderman))
					{
						this.setDead();
					}
				}
				else
				{
					this.motionX *= -0.10000000149011612D;
					this.motionY += 0D;
					this.motionZ *= -0.10000000149011612D;
					this.rotationYaw += 180.0F;
					this.prevRotationYaw += 180.0F;
					this.ticksInAir = 0;
				}
			}
			else
			{
				this.motionX = (float)(moving.hitVec.xCoord - this.posX);
				this.motionY = (float)(moving.hitVec.yCoord - this.posY);
				this.motionZ = (float)(moving.hitVec.zCoord - this.posZ);
				f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				this.posX -= this.motionX / f3 * 0.05000000074505806D;
				this.posY += this.motionY / f2 * 0D;
				this.posZ -= this.motionZ / f3 * 0.05000000074505806D;
				this.setDead();
			}
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

		for (this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
		{
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		f3 = 0.99F;
		f1 = 0.05F;

		if (this.isInWater())
		{
			for (int j1 = 0; j1 < 4; ++j1)
			{
				f3 = 0.25F;
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
				this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.fizz", 0.7F, 0.9F);
			}
			if (this.worldObj.rand.nextInt(100) == 0)
			{
				this.setDead();
			}
			if (this.getLaserType() == 2)
			{
				if (this.worldObj.rand.nextInt(5) == 0)
				{
					this.setDead();
				}
			}
			f3 = 0.99F;
		}

		this.motionX *= f3;
		this.motionY += 0.0F;
		this.motionZ *= f3;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.doBlockCollisions();

		if (this.ticksExisted > 20)
		{
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setShort("xTile", (short)this.xTile);
		nbt.setShort("yTile", (short)this.yTile);
		nbt.setShort("zTile", (short)this.zTile);
		nbt.setInteger("LaserType", this.getLaserType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.xTile = nbt.getShort("xTile");
		this.yTile = nbt.getShort("yTile");
		this.zTile = nbt.getShort("zTile");
		this.setLaserType(nbt.getInteger("LaserType"));
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
	}

	public int getLaserType()
	{
		return this.dataWatcher.getWatchableObjectByte(18);
	}

	public void setLaserType(int type)
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)type));
	}
}