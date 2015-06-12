/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class EntityPolongniusMeteorChunk extends Entity implements IProjectile
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private int inData;
	public int canBePickedUp;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private boolean inGround;
	private int knockbackStrength;

	public EntityPolongniusMeteorChunk(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityPolongniusMeteorChunk(World world, double x, double y, double z)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(x, y, z);
	}

	public EntityPolongniusMeteorChunk(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float speed, float randMod)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shootingEntity;

		if (shootingEntity instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.posY = shootingEntity.posY + shootingEntity.getEyeHeight() - 0.10000000149011612D;
		double d0 = target.posX - shootingEntity.posX;
		double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - this.posY;
		double d2 = target.posZ - shootingEntity.posZ;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) -(Math.atan2(d1, d3) * 180.0D / Math.PI);
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(shootingEntity.posX + d4, this.posY, shootingEntity.posZ + d5, f2, f3);
			float f4 = (float) d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + f4, d2, speed, randMod);
		}
	}

	public EntityPolongniusMeteorChunk(World world, EntityLivingBase shootingEntity, float speed)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shootingEntity;

		if (shootingEntity instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shootingEntity.posX, shootingEntity.posY + shootingEntity.getEyeHeight(), shootingEntity.posZ, shootingEntity.rotationYaw, shootingEntity.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F, 1.0F);
	}

	@Override
	public void setThrowableHeading(double headingX, double headingY, double headingZ, float speed, float randMod)
	{
		float f2 = MathHelper.sqrt_double(headingX * headingX + headingY * headingY + headingZ * headingZ);
		headingX /= f2;
		headingY /= f2;
		headingZ /= f2;
		headingX += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
		headingY += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
		headingZ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * randMod;
		headingX *= speed;
		headingY *= speed;
		headingZ *= speed;
		this.motionX = headingX;
		this.motionY = headingY;
		this.motionZ = headingZ;
		float f3 = MathHelper.sqrt_double(headingX * headingX + headingZ * headingZ);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(headingX, headingZ) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(headingY, f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
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
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
		}

		BlockPos pos = new BlockPos(this.xTile, this.yTile, this.zTile);
		Block block = this.worldObj.getBlockState(pos).getBlock();
		int meta = block.getMetaFromState(block.getDefaultState());

		if (!block.isAir(this.worldObj, pos))
		{
			block.setBlockBoundsBasedOnState(this.worldObj, pos);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, pos, this.worldObj.getBlockState(pos));

			if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.inGround)
		{
			if (block == this.inTile && meta == this.inData)
			{
				++this.ticksInGround;

				if (this.ticksInGround == 1200)
				{
					this.setDead();
				}
			}
			else
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		}
		else
		{
			++this.ticksInAir;
			Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
			Vec3 vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition moving = this.worldObj.rayTraceBlocks(vec3, vec31, false, true, false);
			vec3 = new Vec3(this.posX, this.posY, this.posZ);
			vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (moving != null)
			{
				vec31 = new Vec3(moving.hitVec.xCoord, moving.hitVec.yCoord, moving.hitVec.zCoord);
			}

			this.rotationPitch += 1F;

			Entity entity = null;
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int l;
			float f1;

			for (l = 0; l < list.size(); ++l)
			{
				Entity entity1 = list.get(l);

				if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
				{
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f1, f1, f1);
					MovingObjectPosition moving1 = axisalignedbb1.calculateIntercept(vec3, vec31);

					if (moving1 != null)
					{
						double d1 = vec3.distanceTo(moving1.hitVec);

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
				EntityPlayer player = (EntityPlayer)moving.entityHit;

				if (player.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(player))
				{
					moving = null;
				}
			}

			float f2;
			float f3;
			double damage = 1.6D;//ConfigManagerCore.hardMode ? 3.2D : 1.6D; TODO

			if (moving != null)
			{
				if (moving.entityHit != null)
				{
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int i1 = MathHelper.ceiling_double_int(f2 * damage);

					DamageSource damagesource = null;

					if (this.shootingEntity == null)
					{
						damagesource = new EntityDamageSourceIndirect("meteorChunk", this, this).setProjectile();
					}
					else
					{
						damagesource = new EntityDamageSourceIndirect("meteorChunk", this, this.shootingEntity).setProjectile();
					}

					if (this.isBurning() && !(moving.entityHit instanceof EntityEnderman))
					{
						moving.entityHit.setFire(2);
					}

					if (moving.entityHit.attackEntityFrom(damagesource, i1))
					{
						if (moving.entityHit instanceof EntityLivingBase)
						{
							EntityLivingBase living = (EntityLivingBase)moving.entityHit;

							if (!this.worldObj.isRemote)
							{
								living.setArrowCountInEntity(living.getArrowCountInEntity() + 1);
							}

							if (this.knockbackStrength > 0)
							{
								f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

								if (f3 > 0.0F)
								{
									moving.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / f3, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / f3);
								}
							}

							if (this.shootingEntity != null)
							{
								EnchantmentHelper.func_151384_a(living, this.shootingEntity);
								EnchantmentHelper.func_151385_b((EntityLivingBase) this.shootingEntity, living);
							}

							if (this.shootingEntity != null && moving.entityHit != this.shootingEntity && moving.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
							{
								((EntityPlayerMP) this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
							}
						}

						if (!(moving.entityHit instanceof EntityEnderman))
						{
							this.setDead();
						}
					}
					else
					{
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else
				{
					this.inTile = block;
					this.inData = meta;
					this.motionX = (float) (moving.hitVec.xCoord - this.posX);
					this.motionY = (float) (moving.hitVec.yCoord - this.posY);
					this.motionZ = (float) (moving.hitVec.zCoord - this.posZ);
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / f2 * 0.05000000074505806D;
					this.posY -= this.motionY / f2 * 0.05000000074505806D;
					this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
					this.inGround = true;

					if (!this.inTile.isAir(this.worldObj, pos))
					{
						this.inTile.onEntityCollidedWithBlock(this.worldObj, pos, this);
					}
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (!this.onGround)
			{
				this.rotationPitch += 10;
				this.rotationYaw += 2;
			}

			float f4 = 0.99F;
			f1 = 0.05F;

			if (this.isInWater())
			{
				for (int j1 = 0; j1 < 4; ++j1)
				{
					f3 = 0.25F;
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
				}
				f4 = 0.8F;
			}

			this.motionX *= f4;
			this.motionY *= f4;
			this.motionZ *= f4;
			//this.motionY -= WorldUtil.getGravityForEntity(this); TODO
			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {}

	@Override
	protected void entityInit() {}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!this.worldObj.isRemote && this.inGround)
		{
			boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;

			if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(PolongniusItems.polongnius_meteor_chunk, 1, 0)))
			{
				flag = false;
			}
			if (flag)
			{
				this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				player.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
}