/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public class EntityEvolvedEnderman extends EntityMob /*implements IEntityBreathable*/
{
	private static UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
	private static AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 0.15000000596046448D, 0).setSaved(false);
	private static Set carriableBlocks = Sets.newIdentityHashSet();
	private boolean isAggressive;

	public EntityEvolvedEnderman(World world)
	{
		super(world);
		this.setSize(0.6F, 2.9F);
		this.stepHeight = 1.0F;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(10, new EntityEvolvedEnderman.AIPlaceBlock());
		this.tasks.addTask(11, new EntityEvolvedEnderman.AITakeBlock());
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityEvolvedEnderman.AIFindPlayer());
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityEndermite.class, 10, true, false, new Predicate()
		{
			public boolean func_179948_a(EntityEndermite entity)
			{
				return entity.isSpawnedByPlayer();
			}
			@Override
			public boolean apply(Object entity)
			{
				return this.func_179948_a((EntityEndermite)entity);
			}
		}));
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1001);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Short((short)0));
		this.dataWatcher.addObject(17, new Byte((byte)0));
		this.dataWatcher.addObject(18, new Byte((byte)0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		IBlockState iblockstate = this.func_175489_ck();
		nbt.setShort("carried", (short)Block.getIdFromBlock(iblockstate.getBlock()));
		nbt.setShort("carriedData", (short)iblockstate.getBlock().getMetaFromState(iblockstate));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		IBlockState iblockstate;

		if (nbt.hasKey("carried", 8))
		{
			iblockstate = Block.getBlockFromName(nbt.getString("carried")).getStateFromMeta(nbt.getShort("carriedData") & 65535);
		}
		else
		{
			iblockstate = Block.getBlockById(nbt.getShort("carried")).getStateFromMeta(nbt.getShort("carriedData") & 65535);
		}
		this.func_175490_a(iblockstate);
	}

	private boolean shouldAttackPlayer(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.armorInventory[3];

		if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
		{
			return false;
		}
		else
		{
			Vec3 vec3 = player.getLook(1.0F).normalize();
			Vec3 vec31 = new Vec3(this.posX - player.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - (player.posY + player.getEyeHeight()), this.posZ - player.posZ);
			double d0 = vec31.lengthVector();
			vec31 = vec31.normalize();
			double d1 = vec3.dotProduct(vec31);
			return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
		}
	}

	@Override
	public float getEyeHeight()
	{
		return 2.55F;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; ++i)
			{
				MorePlanetsCore.proxy.spawnMotionParticle(ParticleTypesMP.BLUE_PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
			}
		}
		this.isJumping = false;
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks()
	{
		if (this.isWet())
		{
			this.attackEntityFrom(DamageSource.drown, 1.0F);
		}

		if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0)
		{
			this.setScreaming(false);
		}

		if (this.worldObj.isDaytime())
		{
			float f = this.getBrightness(1.0F);

			if (f > 0.5F && this.worldObj.canSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
			{
				this.setAttackTarget((EntityLivingBase)null);
				this.setScreaming(false);
				this.isAggressive = false;
				this.teleportRandomly();
			}
		}
		super.updateAITasks();
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.posY + (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportToEntity(Entity entity)
	{
		Vec3 vec3 = new Vec3(this.posX - entity.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - entity.posY + entity.getEyeHeight(), this.posZ - entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double x, double y, double z)
	{
		EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0);

		if (MinecraftForge.EVENT_BUS.post(event))
		{
			return false;
		}
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = event.targetX;
		this.posY = event.targetY;
		this.posZ = event.targetZ;
		boolean flag = false;
		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);

		if (this.worldObj.isBlockLoaded(blockpos))
		{
			boolean flag1 = false;

			while (!flag1 && blockpos.getY() > 0)
			{
				BlockPos blockpos1 = blockpos.down();
				Block block = this.worldObj.getBlockState(blockpos1).getBlock();

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					blockpos = blockpos1;
				}
			}

			if (flag1)
			{
				super.setPositionAndUpdate(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for (int i = 0; i < short1; ++i)
			{
				double d9 = i / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d6 = d3 + (this.posX - d3) * d9 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d7 = d4 + (this.posY - d4) * d9 + this.rand.nextDouble() * this.height;
				double d8 = d5 + (this.posZ - d5) * d9 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				MorePlanetsCore.proxy.spawnMotionParticle(ParticleTypesMP.BLUE_PORTAL, d6, d7, d8, f, f1, f2);
			}
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	@Override
	protected String getLivingSound()
	{
		return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.endermen.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.endermen.death";
	}

	@Override
	protected Item getDropItem()
	{
		return Items.ender_pearl;
	}

	@Override
	protected void dropFewItems(boolean drop, int par2)
	{
		Item item = this.getDropItem();

		if (item != null)
		{
			int j = this.rand.nextInt(2 + par2);

			for (int k = 0; k < j; ++k)
			{
				this.dropItem(item, 1);
			}
		}
	}

	public void func_175490_a(IBlockState state)
	{
		this.dataWatcher.updateObject(16, Short.valueOf((short)(Block.getStateId(state) & 65535)));
	}

	public IBlockState func_175489_ck()
	{
		return Block.getStateById(this.dataWatcher.getWatchableObjectShort(16) & 65535);
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
			if (source.getEntity() == null || !(source.getEntity() instanceof EntityEndermite))
			{
				if (!this.worldObj.isRemote)
				{
					this.setScreaming(true);
				}

				if (source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayer)
				{
					if (source.getEntity() instanceof EntityPlayerMP && ((EntityPlayerMP)source.getEntity()).theItemInWorldManager.isCreative())
					{
						this.setScreaming(false);
					}
					else
					{
						this.isAggressive = true;
					}
				}

				if (source instanceof EntityDamageSourceIndirect)
				{
					this.isAggressive = false;

					for (int i = 0; i < 64; ++i)
					{
						if (this.teleportRandomly())
						{
							return true;
						}
					}
					return false;
				}
			}

			boolean flag = super.attackEntityFrom(source, amount);

			if (source.isUnblockable() && this.rand.nextInt(10) != 0)
			{
				this.teleportRandomly();
			}
			return flag;
		}
	}

	/*@Override
	public boolean canBreath()
	{
		return true;
	}*/

	public boolean isScreaming()
	{
		return this.dataWatcher.getWatchableObjectByte(18) > 0;
	}

	public void setScreaming(boolean scream)
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte)(scream ? 1 : 0)));
	}

	static
	{
		carriableBlocks.add(Blocks.grass);
		carriableBlocks.add(Blocks.dirt);
		carriableBlocks.add(Blocks.sand);
		carriableBlocks.add(Blocks.gravel);
		carriableBlocks.add(Blocks.yellow_flower);
		carriableBlocks.add(Blocks.red_flower);
		carriableBlocks.add(Blocks.brown_mushroom);
		carriableBlocks.add(Blocks.red_mushroom);
		carriableBlocks.add(Blocks.tnt);
		carriableBlocks.add(Blocks.cactus);
		carriableBlocks.add(Blocks.clay);
		carriableBlocks.add(Blocks.pumpkin);
		carriableBlocks.add(Blocks.melon_block);
		carriableBlocks.add(Blocks.mycelium);
	}

	class AIFindPlayer extends EntityAINearestAttackableTarget
	{
		private EntityPlayer field_179448_g;
		private int field_179450_h;
		private int field_179451_i;
		private EntityEvolvedEnderman field_179449_j = EntityEvolvedEnderman.this;

		public AIFindPlayer()
		{
			super(EntityEvolvedEnderman.this, EntityPlayer.class, true);
		}

		@Override
		public boolean shouldExecute()
		{
			double d0 = this.getTargetDistance();
			List list = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.taskOwner.getEntityBoundingBox().expand(d0, 4.0D, d0), this.targetEntitySelector);
			Collections.sort(list, this.theNearestAttackableTargetSorter);

			if (list.isEmpty())
			{
				return false;
			}
			else
			{
				this.field_179448_g = (EntityPlayer)list.get(0);
				return true;
			}
		}

		@Override
		public void startExecuting()
		{
			this.field_179450_h = 5;
			this.field_179451_i = 0;
		}

		@Override
		public void resetTask()
		{
			this.field_179448_g = null;
			this.field_179449_j.setScreaming(false);
			IAttributeInstance iattributeinstance = this.field_179449_j.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			iattributeinstance.removeModifier(EntityEvolvedEnderman.attackingSpeedBoostModifier);
			super.resetTask();
		}

		@Override
		public boolean continueExecuting()
		{
			if (this.field_179448_g != null)
			{
				if (!this.field_179449_j.shouldAttackPlayer(this.field_179448_g))
				{
					return false;
				}
				else
				{
					this.field_179449_j.isAggressive = true;
					this.field_179449_j.faceEntity(this.field_179448_g, 10.0F, 10.0F);
					return true;
				}
			}
			else
			{
				return super.continueExecuting();
			}
		}

		@Override
		public void updateTask()
		{
			if (this.field_179448_g != null)
			{
				if (--this.field_179450_h <= 0)
				{
					this.targetEntity = this.field_179448_g;
					this.field_179448_g = null;
					super.startExecuting();
					this.field_179449_j.playSound("mob.endermen.stare", 1.0F, 1.0F);
					this.field_179449_j.setScreaming(true);
					IAttributeInstance iattributeinstance = this.field_179449_j.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
					iattributeinstance.applyModifier(EntityEvolvedEnderman.attackingSpeedBoostModifier);
				}
			}
			else
			{
				if (this.targetEntity != null)
				{
					if (this.targetEntity instanceof EntityPlayer && this.field_179449_j.shouldAttackPlayer((EntityPlayer)this.targetEntity))
					{
						if (this.targetEntity.getDistanceSqToEntity(this.field_179449_j) < 16.0D)
						{
							this.field_179449_j.teleportRandomly();
						}

						this.field_179451_i = 0;
					}
					else if (this.targetEntity.getDistanceSqToEntity(this.field_179449_j) > 256.0D && this.field_179451_i++ >= 30 && this.field_179449_j.teleportToEntity(this.targetEntity))
					{
						this.field_179451_i = 0;
					}
				}

				super.updateTask();
			}
		}
	}

	class AIPlaceBlock extends EntityAIBase
	{
		private EntityEvolvedEnderman field_179475_a = EntityEvolvedEnderman.this;

		@Override
		public boolean shouldExecute()
		{
			return !this.field_179475_a.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : this.field_179475_a.func_175489_ck().getBlock().getMaterial() == Material.air ? false : this.field_179475_a.getRNG().nextInt(2000) == 0;
		}

		@Override
		public void updateTask()
		{
			Random random = this.field_179475_a.getRNG();
			World world = this.field_179475_a.worldObj;
			int i = MathHelper.floor_double(this.field_179475_a.posX - 1.0D + random.nextDouble() * 2.0D);
			int j = MathHelper.floor_double(this.field_179475_a.posY + random.nextDouble() * 2.0D);
			int k = MathHelper.floor_double(this.field_179475_a.posZ - 1.0D + random.nextDouble() * 2.0D);
			BlockPos blockpos = new BlockPos(i, j, k);
			Block block = world.getBlockState(blockpos).getBlock();
			Block block1 = world.getBlockState(blockpos.down()).getBlock();

			if (this.func_179474_a(world, blockpos, this.field_179475_a.func_175489_ck().getBlock(), block, block1))
			{
				world.setBlockState(blockpos, this.field_179475_a.func_175489_ck(), 3);
				this.field_179475_a.func_175490_a(Blocks.air.getDefaultState());
			}
		}

		private boolean func_179474_a(World worldIn, BlockPos p_179474_2_, Block p_179474_3_, Block p_179474_4_, Block p_179474_5_)
		{
			return !p_179474_3_.canPlaceBlockAt(worldIn, p_179474_2_) ? false : p_179474_4_.getMaterial() != Material.air ? false : p_179474_5_.getMaterial() == Material.air ? false : p_179474_5_.isFullCube();
		}
	}

	class AITakeBlock extends EntityAIBase
	{
		private EntityEvolvedEnderman field_179473_a = EntityEvolvedEnderman.this;

		@Override
		public boolean shouldExecute()
		{
			return !this.field_179473_a.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : this.field_179473_a.func_175489_ck().getBlock().getMaterial() != Material.air ? false : this.field_179473_a.getRNG().nextInt(20) == 0;
		}

		@Override
		public void updateTask()
		{
			Random random = this.field_179473_a.getRNG();
			World world = this.field_179473_a.worldObj;
			int i = MathHelper.floor_double(this.field_179473_a.posX - 2.0D + random.nextDouble() * 4.0D);
			int j = MathHelper.floor_double(this.field_179473_a.posY + random.nextDouble() * 3.0D);
			int k = MathHelper.floor_double(this.field_179473_a.posZ - 2.0D + random.nextDouble() * 4.0D);
			BlockPos blockpos = new BlockPos(i, j, k);
			IBlockState iblockstate = world.getBlockState(blockpos);
			Block block = iblockstate.getBlock();

			if (EntityEvolvedEnderman.carriableBlocks.contains(block))
			{
				this.field_179473_a.func_175490_a(iblockstate);
				world.setBlockState(blockpos, Blocks.air.getDefaultState());
			}
		}
	}

	public static void setCarriable(Block block, boolean canCarry)
	{
		if (canCarry)
		{
			carriableBlocks.add(block);
		}
		else
		{
			carriableBlocks.remove(block);
		}
	}
	public static boolean getCarriable(Block block)
	{
		return carriableBlocks.contains(block);
	}

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
			break;
		case 6:
			//Oxygen tank half empty or less
			//this.entityDropItem(new ItemStack(GCItems.oxTankMedium, 1, 901 + this.rand.nextInt(900)), 0.0F);
			break;
		case 7:
		case 8:
			this.dropItem(Items.ender_eye, 1);
			break;
		case 9:
			//this.dropItem(GCItems.oxygenConcentrator, 1);
			break;
		case 10:
			//this.dropItem(GCItems.oxMask, 1);
			break;
		}
	}
}