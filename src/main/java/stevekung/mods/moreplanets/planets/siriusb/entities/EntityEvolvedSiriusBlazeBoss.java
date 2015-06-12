/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.siriusb.entities;
//
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.EntityAIBase;
//import net.minecraft.entity.ai.EntityAIHurtByTarget;
//import net.minecraft.entity.ai.EntityAILookIdle;
//import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
//import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
//import net.minecraft.entity.ai.EntityAIWander;
//import net.minecraft.entity.ai.EntityAIWatchClosest;
//import net.minecraft.entity.boss.IBossDisplayData;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.item.EntityXPOrb;
//import net.minecraft.entity.monster.EntityMob;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.BlockPos;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.WeightedRandomChestContent;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ChestGenHooks;
//import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.common.entities.ISiriusMob;
//import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
//import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
//
//public class EntityEvolvedSiriusBlazeBoss extends EntityMob implements IEntityBreathable, IBossDisplayData, IBoss, ISiriusMob //IRangedAttackMob
//{
//	protected long ticks = 0;
//	protected TileEntityDungeonSpawner spawner;
//
//	protected Entity targetEntity;
//	protected int deathTicks = 0;
//
//	protected int entitiesWithin;
//	protected int entitiesWithinLast;
//
//	protected Vector3 roomCoords;
//	protected Vector3 roomSize;
//
//	private float heightOffset = 0.5F;
//	private int heightOffsetUpdateTime;
//	private int field_70846_g;
//
//	public EntityEvolvedSiriusBlazeBoss(World par1World)
//	{
//		super(par1World);
//		this.setSize(2.5F, 7.5F);
//		this.isImmuneToFire = true;
//		this.tasks.addTask(4, new AIFireballAttack());
//		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
//		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
//		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
//		this.tasks.addTask(8, new EntityAILookIdle(this));
//		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
//		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
//	}
//
//	public EntityEvolvedSiriusBlazeBoss(World world, Vector3 vec)
//	{
//		this(world);
//		this.setPosition(vec.x, vec.y, vec.z);
//	}
//
//	/*@Override TODO
//	public boolean attackEntityFrom(DamageSource damageSource, float damage)
//	{
//		if (damageSource.getDamageType().equals("water"))
//		{
//			if (this.isEntityInvulnerable())
//			{
//				return false;
//			}
//			else if (super.attackEntityFrom(damageSource, damage))
//			{
//				Entity entity = damageSource.getEntity();
//
//				if (this.riddenByEntity != entity && this.ridingEntity != entity)
//				{
//					if (entity != this)
//					{
//						this.entityToAttack = entity;
//					}
//					return true;
//				}
//				else
//				{
//					return true;
//				}
//			}
//			else
//			{
//				return false;
//			}
//		}
//		return false;
//	}*/
//
//	@Override
//	public void knockBack(Entity entity, float par2, double par3, double par4) {}
//
//	@Override
//	public boolean canBePushed()
//	{
//		return false;
//	}
//
//	@Override
//	protected String getLivingSound()
//	{
//		return "mob.blaze.breathe";
//	}
//
//	@Override
//	protected String getHurtSound()
//	{
//		return "mob.blaze.hit";
//	}
//
//	@Override
//	protected String getDeathSound()
//	{
//		return "mob.blaze.death";
//	}
//
//	@Override
//	protected boolean isValidLightLevel()
//	{
//		return true;
//	}
//
//	@Override
//	public boolean canLivingInSirius()
//	{
//		return true;
//	}
//
//	@Override
//	public void setDead()
//	{
//		if (this.spawner != null)
//		{
//			this.spawner.isBossDefeated = false;
//			this.spawner.boss = null;
//			this.spawner.spawned = false;
//		}
//		super.setDead();
//	}
//
//	@Override
//	protected void applyEntityAttributes()
//	{
//		super.applyEntityAttributes();
//		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0F * ConfigManagerCore.dungeonBossHealthMod);
//		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected void onDeathUpdate()
//	{
//		++this.deathTicks;
//
//		if (this.deathTicks >= 180 && this.deathTicks <= 200)
//		{
//			float f = (this.rand.nextFloat() - 0.5F) * 1.5F;
//			float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
//			float f2 = (this.rand.nextFloat() - 0.5F) * 1.5F;
//			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + f, this.posY + 2.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
//		}
//
//		int i;
//		int j;
//
//		if (!this.worldObj.isRemote)
//		{
//			if (this.deathTicks >= 180 && this.deathTicks % 5 == 0)
//			{
//				GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_EXPLODE, new Object[] { }), new TargetPoint(this.worldObj.provider.getDimensionId(), this.posX, this.posY, this.posZ, 40.0D));
//			}
//
//			if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
//			{
//				i = 30;
//
//				while (i > 0)
//				{
//					j = EntityXPOrb.getXPSplit(i);
//					i -= j;
//					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
//				}
//			}
//
//			if (this.deathTicks == 1)
//			{
//				GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_BOSS_DEATH, new Object[] {}), new TargetPoint(this.worldObj.provider.getDimensionId(), this.posX, this.posY, this.posZ, 40.0D));
//			}
//		}
//
//		this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
//		this.renderYawOffset = this.rotationYaw += 20.0F;
//
//		if (this.deathTicks == 200 && !this.worldObj.isRemote)
//		{
//			i = 20;
//
//			while (i > 0)
//			{
//				j = EntityXPOrb.getXPSplit(i);
//				i -= j;
//				this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
//			}
//
//			for (TileEntity tile : (List<TileEntity>) this.worldObj.loadedTileEntityList)
//			{
//				if (tile instanceof TileEntityDionaTreasureChest)
//				{
//					double d3 = tile.getPos().getX() + 0.5D - this.posX;
//					double d4 = tile.getPos().getY() + 0.5D - this.posY;
//					double d5 = tile.getPos().getZ() + 0.5D - this.posZ;
//					double dSq = d3 * d3 + d4 * d4 + d5 * d5;
//					TileEntityDionaTreasureChest chest = (TileEntityDionaTreasureChest) tile;
//
//					if (dSq < 10000)
//					{
//						if (!chest.locked)
//						{
//							chest.locked = true;
//						}
//
//						for (int k = 0; k < chest.getSizeInventory(); k++)
//						{
//							chest.setInventorySlotContents(k, null);
//						}
//						ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
//						WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//						WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//						WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//						chest.setInventorySlotContents(this.rand.nextInt(chest.getSizeInventory()), this.getGuaranteedLoot(this.rand));
//						break;
//					}
//				}
//			}
//
//			this.entityDropItem(new ItemStack(SiriusBItems.sirius_b_dungeon_key, 1, 0), 0.5F);
//			super.setDead();
//
//			if (this.spawner != null)
//			{
//				this.spawner.isBossDefeated = true;
//				this.spawner.boss = null;
//				this.spawner.spawned = false;
//			}
//		}
//	}
//
//	@Override
//	public void onLivingUpdate()
//	{
//		if (this.ticks >= Long.MAX_VALUE)
//		{
//			this.ticks = 1;
//		}
//		this.ticks++;
//
//		EntityPlayer player = this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 20.0);
//
//		if (player != null && !player.equals(this.targetEntity))
//		{
//			if (this.getDistanceSqToEntity(player) < 400.0D)
//			{
//				this.getNavigator().getPathToEntityLiving(player);
//				this.targetEntity = player;
//			}
//		}
//		else
//		{
//			this.targetEntity = null;
//		}
//
//		if (this.roomCoords != null && this.roomSize != null)
//		{
//			@SuppressWarnings("unchecked")
//			List<Entity> entitiesWithin = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.roomCoords.intX() - 1, this.roomCoords.intY() - 1, this.roomCoords.intZ() - 1, this.roomCoords.intX() + this.roomSize.intX(), this.roomCoords.intY() + this.roomSize.intY(), this.roomCoords.intZ() + this.roomSize.intZ()));
//
//			this.entitiesWithin = entitiesWithin.size();
//
//			if (this.entitiesWithin == 0 && this.entitiesWithinLast != 0)
//			{
//				@SuppressWarnings("unchecked")
//				List<EntityPlayer> entitiesWithin2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.roomCoords.intX() - 11, this.roomCoords.intY() - 11, this.roomCoords.intZ() - 11, this.roomCoords.intX() + this.roomSize.intX() + 10, this.roomCoords.intY() + this.roomSize.intY() + 10, this.roomCoords.intZ() + this.roomSize.intZ() + 10));
//
//				for (EntityPlayer p : entitiesWithin2)
//				{
//					p.addChatMessage(new ChatComponentText(GCCoreUtil.translate("gui.skeletonBoss.message")));
//				}
//
//				this.setDead();
//
//				if (this.spawner != null)
//				{
//					this.spawner.playerCheated = true;
//				}
//				return;
//			}
//			this.entitiesWithinLast = this.entitiesWithin;
//		}
//
//		if (!this.onGround && this.motionY < 0.0D)
//		{
//			this.motionY *= 0.6D;
//		}
//
//		if (this.worldObj.isRemote)
//		{
//			if (this.rand.nextInt(24) == 0 && !this.isSilent())
//			{
//				this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
//			}
//
//			for (int i = 0; i < 2; ++i)
//			{
//				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
//			}
//		}
//		super.onLivingUpdate();
//	}
//
//	@Override
//	public EntityItem entityDropItem(ItemStack par1ItemStack, float par2)
//	{
//		EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY + par2, this.posZ, par1ItemStack);
//		entityitem.motionY = -2.0D;
//		entityitem.setPickupDelay(10);
//
//		if (this.captureDrops)
//		{
//			this.capturedDrops.add(entityitem);
//		}
//		else
//		{
//			this.worldObj.spawnEntityInWorld(entityitem);
//		}
//		return entityitem;
//	}
//
//	@Override
//	public boolean canBreath()
//	{
//		return true;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public int getBrightnessForRender(float light)
//	{
//		return 15728880;
//	}
//
//	@Override
//	public float getBrightness(float light)
//	{
//		return 1.0F;
//	}
//
//	@Override
//	public void fall(float fall, float damage) {}
//
//	/*@Override
//	protected void dropRareDrop(int par1)
//	{
//		this.dropItem(Items.blaze_rod, 1);
//	}*/
//
//	public ItemStack getGuaranteedLoot(Random rand)
//	{
//		List<ItemStack> stackList = GalacticraftRegistry.getDungeonLoot(8);
//		return stackList.get(rand.nextInt(stackList.size())).copy();
//	}
//
//	@Override
//	public void writeEntityToNBT(NBTTagCompound nbt)
//	{
//		super.writeEntityToNBT(nbt);
//
//		if (this.roomCoords != null)
//		{
//			nbt.setDouble("roomCoordsX", this.roomCoords.x);
//			nbt.setDouble("roomCoordsY", this.roomCoords.y);
//			nbt.setDouble("roomCoordsZ", this.roomCoords.z);
//			nbt.setDouble("roomSizeX", this.roomSize.x);
//			nbt.setDouble("roomSizeY", this.roomSize.y);
//			nbt.setDouble("roomSizeZ", this.roomSize.z);
//		}
//	}
//
//	@Override
//	public void readEntityFromNBT(NBTTagCompound nbt)
//	{
//		super.readEntityFromNBT(nbt);
//		this.roomCoords = new Vector3();
//		this.roomCoords.x = nbt.getDouble("roomCoordsX");
//		this.roomCoords.y = nbt.getDouble("roomCoordsY");
//		this.roomCoords.z = nbt.getDouble("roomCoordsZ");
//		this.roomSize = new Vector3();
//		this.roomSize.x = nbt.getDouble("roomSizeX");
//		this.roomSize.y = nbt.getDouble("roomSizeY");
//		this.roomSize.z = nbt.getDouble("roomSizeZ");
//	}
//
//	@Override
//	public void setRoom(Vector3 roomCoords, Vector3 roomSize)
//	{
//		this.roomCoords = roomCoords;
//		this.roomSize = roomSize;
//	}
//
//	@Override
//	public void onBossSpawned(TileEntityDungeonSpawner spawner)
//	{
//		this.spawner = spawner;
//	}
//
//	@Override
//	protected void updateAITasks()
//	{
//		if (this.isWet())
//		{
//			this.attackEntityFrom(DamageSource.drown, 1.0F);
//		}
//
//		--this.heightOffsetUpdateTime;
//
//		if (this.heightOffsetUpdateTime <= 0)
//		{
//			this.heightOffsetUpdateTime = 100;
//			this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
//		}
//
//		EntityLivingBase entitylivingbase = this.getAttackTarget();
//
//		if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset)
//		{
//			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
//			this.isAirBorne = true;
//		}
//		super.updateAITasks();
//	}
//
//	@Override
//	protected void entityInit()
//	{
//		super.entityInit();
//		this.dataWatcher.addObject(16, new Byte((byte)0));
//	}
//
//	@Override
//	public boolean isBurning()
//	{
//		return this.isBurn();
//	}
//
//	public boolean isBurn()
//	{
//		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
//	}
//
//	public void isBurn(boolean par1)
//	{
//		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//
//		if (par1)
//		{
//			b0 = (byte)(b0 | 1);
//		}
//		else
//		{
//			b0 &= -2;
//		}
//		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
//	}
//
//	class AIFireballAttack extends EntityAIBase
//	{
//		private EntityEvolvedSiriusBlazeBoss field_179469_a = EntityEvolvedSiriusBlazeBoss.this;
//		private int field_179467_b;
//		private int field_179468_c;
//
//		public AIFireballAttack()
//		{
//			this.setMutexBits(3);
//		}
//
//		@Override
//		public boolean shouldExecute()
//		{
//			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
//			return entitylivingbase != null && entitylivingbase.isEntityAlive();
//		}
//
//		@Override
//		public void startExecuting()
//		{
//			this.field_179467_b = 0;
//		}
//
//		@Override
//		public void resetTask()
//		{
//			this.field_179469_a.isBurn(false);
//		}
//
//		@Override
//		public void updateTask()
//		{
//			--this.field_179468_c;
//			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
//			double d0 = this.field_179469_a.getDistanceSqToEntity(entitylivingbase);
//
//			if (d0 < 4.0D)
//			{
//				if (this.field_179468_c <= 0)
//				{
//					this.field_179468_c = 20;
//					this.field_179469_a.attackEntityAsMob(entitylivingbase);
//				}
//				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
//			}
//			else if (d0 < 256.0D)
//			{
//				double d1 = entitylivingbase.posX - this.field_179469_a.posX;
//				double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.field_179469_a.posY + this.field_179469_a.height / 2.0F);
//				double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;
//
//				if (this.field_179468_c <= 0)
//				{
//					++this.field_179467_b;
//
//					if (this.field_179467_b == 1)
//					{
//						this.field_179468_c = 60;
//						this.field_179469_a.isBurn(true);
//					}
//					else if (this.field_179467_b <= 4)
//					{
//						this.field_179468_c = 6;
//					}
//					else
//					{
//						this.field_179468_c = 100;
//						this.field_179467_b = 0;
//						this.field_179469_a.isBurn(false);
//					}
//
//					if (this.field_179467_b > 1)
//					{
//						float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0)) * 0.5F;
//						this.field_179469_a.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);
//
//						for (int i = 0; i < 1; ++i)
//						{
//							EntitySiriusSmallFireball entitysmallfireball = new EntitySiriusSmallFireball(this.field_179469_a.worldObj, this.field_179469_a, d1 + this.field_179469_a.getRNG().nextGaussian() * f, d2, d3 + this.field_179469_a.getRNG().nextGaussian() * f);
//							entitysmallfireball.posY = this.field_179469_a.posY + this.field_179469_a.height / 2.0F + 0.5D;
//							entitysmallfireball.setCanExplode(true);
//							this.field_179469_a.worldObj.spawnEntityInWorld(entitysmallfireball);
//						}
//					}
//				}
//				this.field_179469_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
//			}
//			else
//			{
//				this.field_179469_a.getNavigator().clearPathEntity();
//				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
//			}
//			super.updateTask();
//		}
//	}
//}