/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.polongnius.entities;
//
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.EntityAIBase;
//import net.minecraft.entity.ai.EntityMoveHelper;
//import net.minecraft.entity.boss.IBossDisplayData;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.item.EntityXPOrb;
//import net.minecraft.entity.monster.IMob;
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
//import net.minecraft.util.Vec3;
//import net.minecraft.util.WeightedRandomChestContent;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ChestGenHooks;
//import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
//import stevekung.mods.moreplanets.common.entities.EntityFlyingBossMP;
//import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
//import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
//
//public class EntityCheeseCubeEyeBoss extends EntityFlyingBossMP implements IMob, IEntityBreathable, IBossDisplayData, IBoss
//{
//	public int courseChangeCooldown;
//	public double waypointX;
//	public double waypointY;
//	public double waypointZ;
//	private TileEntityDungeonSpawner spawner;
//	private Entity targetedEntity;
//	protected long ticks = 0;
//	public int deathTicks = 0;
//	public int attackCounter;
//	public int prevAttackCounter;
//	private int aggroCooldown;
//	public int entitiesWithin;
//	public int entitiesWithinLast;
//	private Vector3 roomCoords;
//	private Vector3 roomSize;
//
//	public EntityCheeseCubeEyeBoss(World world)
//	{
//		super(world);
//		this.setSize(1.8F, 2.0F);
//	}
//
//	@Override
//	protected void applyEntityAttributes()
//	{
//		super.applyEntityAttributes();
//		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(350.0F * ConfigManagerCore.dungeonBossHealthMod);
//		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(15.0F);
//	}
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
//    }
//
//    public void func_175454_a(boolean bool)
//    {
//        this.dataWatcher.updateObject(16, Byte.valueOf((byte)(bool ? 1 : 0)));
//    }
//
//	@Override
//	public void knockBack(Entity par1Entity, float par2, double par3, double par4) {}
//
//	@Override
//	public boolean canBePushed()
//	{
//		return false;
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
//			if (!this.worldObj.isRemote)
//			{
//				for (TileEntity tile : (List<TileEntity>) this.worldObj.loadedTileEntityList)
//				{
//					if (tile instanceof TileEntityPolongniusTreasureChest)
//					{
//						double d3 = tile.getPos().getX() + 0.5D - this.posX;
//						double d4 = tile.getPos().getY() + 0.5D - this.posY;
//						double d5 = tile.getPos().getZ() + 0.5D - this.posZ;
//						double dSq = d3 * d3 + d4 * d4 + d5 * d5;
//						TileEntityPolongniusTreasureChest chest = (TileEntityPolongniusTreasureChest) tile;
//
//						if (dSq < Math.pow(100.0D, 2))
//						{
//							if (!chest.locked)
//							{
//								chest.locked = true;
//							}
//
//							for (int k = 0; k < chest.getSizeInventory(); k++)
//							{
//								chest.setInventorySlotContents(k, null);
//							}
//
//							ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
//							WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//							WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//							WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
//							chest.setInventorySlotContents(this.rand.nextInt(chest.getSizeInventory()), this.getGuaranteedLoot(this.rand));
//							break;
//						}
//					}
//				}
//			}
//
//			this.entityDropItem(new ItemStack(PolongniusItems.polongnius_dungeon_key, 1, 0), 0.5F);
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
//	public void onLivingUpdate()
//	{
//		if (this.ticks >= Long.MAX_VALUE)
//		{
//			this.ticks = 1;
//		}
//
//		this.ticks++;
//
//		EntityPlayer player = this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 20.0);
//
//		if (player != null && !player.equals(this.targetedEntity))
//		{
//			if (this.getDistanceSqToEntity(player) < 400.0D)
//			{
//				this.getNavigator().getPathToEntityLiving(player);
//				this.targetedEntity = player;
//			}
//		}
//		else
//		{
//			this.targetedEntity = null;
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
//	public ItemStack getGuaranteedLoot(Random rand)
//	{
//		List<ItemStack> stackList = GalacticraftRegistry.getDungeonLoot(4);
//		return stackList.get(rand.nextInt(stackList.size()));
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
//	public boolean attackEntityFrom(DamageSource damageSource, float damage)
//	{
//		if (damageSource.getDamageType().equals("arrow"))
//		{
//			if (this.isEntityInvulnerable(damageSource))
//			{
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	protected String getLivingSound()
//	{
//		return null;
//	}
//
//	@Override
//	protected String getHurtSound()
//	{
//		return "fronos:mob.cheese_boss.hit";
//	}
//
//	@Override
//	protected String getDeathSound()
//	{
//		return null;
//	}
//
//	@Override
//	protected float getSoundVolume()
//	{
//		return 10.0F;
//	}
//
//	@Override
//	public boolean canBreath()
//	{
//		return true;
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
//    class AIFireballAttack extends EntityAIBase
//    {
//        private EntityCheeseCubeEyeBoss field_179470_b = EntityCheeseCubeEyeBoss.this;
//        public int field_179471_a;
//
//        public boolean shouldExecute()
//        {
//            return this.field_179470_b.getAttackTarget() != null;
//        }
//
//        public void startExecuting()
//        {
//            this.field_179471_a = 0;
//        }
//
//        public void resetTask()
//        {
//            this.field_179470_b.func_175454_a(false);
//        }
//
//        public void updateTask()
//        {
//            EntityLivingBase entitylivingbase = this.field_179470_b.getAttackTarget();
//            double d0 = 64.0D;
//
//            if (entitylivingbase.getDistanceSqToEntity(this.field_179470_b) < d0 * d0 && this.field_179470_b.canEntityBeSeen(entitylivingbase))
//            {
//                World world = this.field_179470_b.worldObj;
//                ++this.field_179471_a;
//
//                if (this.field_179471_a == 10)
//                {
//                    world.playAuxSFXAtEntity((EntityPlayer)null, 1007, new BlockPos(this.field_179470_b), 0);
//                }
//
//                if (this.field_179471_a == 20)
//                {
//                    double d1 = 4.0D;
//                    Vec3 vec3 = this.field_179470_b.getLook(1.0F);
//                    double d2 = entitylivingbase.posX - (this.field_179470_b.posX + vec3.xCoord * d1);
//                    double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.field_179470_b.posY + (double)(this.field_179470_b.height / 2.0F));
//                    double d4 = entitylivingbase.posZ - (this.field_179470_b.posZ + vec3.zCoord * d1);
//                    world.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.field_179470_b), 0);
//                    EntityCheeseSpore entitylargefireball = new EntityCheeseSpore(world, this.field_179470_b, d2, d3, d4);
//                    entitylargefireball.posX = this.field_179470_b.posX + vec3.xCoord * d1;
//                    entitylargefireball.posY = this.field_179470_b.posY + (double)(this.field_179470_b.height / 2.0F) + 0.5D;
//                    entitylargefireball.posZ = this.field_179470_b.posZ + vec3.zCoord * d1;
//                    world.spawnEntityInWorld(entitylargefireball);
//                    this.field_179471_a = -40;
//                }
//            }
//            else if (this.field_179471_a > 0)
//            {
//                --this.field_179471_a;
//            }
//            this.field_179470_b.func_175454_a(this.field_179471_a > 10);
//        }
//    }
//
//    class AILookAround extends EntityAIBase
//    {
//        private EntityCheeseCubeEyeBoss field_179472_a = EntityCheeseCubeEyeBoss.this;
//
//        public AILookAround()
//        {
//            this.setMutexBits(2);
//        }
//
//        public boolean shouldExecute()
//        {
//            return true;
//        }
//
//        public void updateTask()
//        {
//            if (this.field_179472_a.getAttackTarget() == null)
//            {
//                this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(this.field_179472_a.motionX, this.field_179472_a.motionZ)) * 180.0F / (float)Math.PI;
//            }
//            else
//            {
//                EntityLivingBase entitylivingbase = this.field_179472_a.getAttackTarget();
//                double d0 = 64.0D;
//
//                if (entitylivingbase.getDistanceSqToEntity(this.field_179472_a) < d0 * d0)
//                {
//                    double d1 = entitylivingbase.posX - this.field_179472_a.posX;
//                    double d2 = entitylivingbase.posZ - this.field_179472_a.posZ;
//                    this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(d1, d2)) * 180.0F / (float)Math.PI;
//                }
//            }
//        }
//    }
//
//    class AIRandomFly extends EntityAIBase
//    {
//        private EntityCheeseCubeEyeBoss field_179454_a = EntityCheeseCubeEyeBoss.this;
//
//        public AIRandomFly()
//        {
//            this.setMutexBits(1);
//        }
//
//        public boolean shouldExecute()
//        {
//            EntityMoveHelper entitymovehelper = this.field_179454_a.getMoveHelper();
//
//            if (!entitymovehelper.isUpdating())
//            {
//                return true;
//            }
//            else
//            {
//                double d0 = entitymovehelper.func_179917_d() - this.field_179454_a.posX;
//                double d1 = entitymovehelper.func_179919_e() - this.field_179454_a.posY;
//                double d2 = entitymovehelper.func_179918_f() - this.field_179454_a.posZ;
//                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
//                return d3 < 1.0D || d3 > 3600.0D;
//            }
//        }
//
//        public boolean continueExecuting()
//        {
//            return false;
//        }
//
//        public void startExecuting()
//        {
//            Random random = this.field_179454_a.getRNG();
//            double d0 = this.field_179454_a.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
//            double d1 = this.field_179454_a.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
//            double d2 = this.field_179454_a.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
//            this.field_179454_a.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
//        }
//    }
//
//    class GhastMoveHelper extends EntityMoveHelper
//    {
//        private EntityCheeseCubeEyeBoss field_179927_g = EntityCheeseCubeEyeBoss.this;
//        private int field_179928_h;
//
//        public GhastMoveHelper()
//        {
//            super(EntityCheeseCubeEyeBoss.this);
//        }
//
//        public void onUpdateMoveHelper()
//        {
//            if (this.update)
//            {
//                double d0 = this.posX - this.field_179927_g.posX;
//                double d1 = this.posY - this.field_179927_g.posY;
//                double d2 = this.posZ - this.field_179927_g.posZ;
//                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
//
//                if (this.field_179928_h-- <= 0)
//                {
//                    this.field_179928_h += this.field_179927_g.getRNG().nextInt(5) + 2;
//                    d3 = (double)MathHelper.sqrt_double(d3);
//
//                    if (this.func_179926_b(this.posX, this.posY, this.posZ, d3))
//                    {
//                        this.field_179927_g.motionX += d0 / d3 * 0.1D;
//                        this.field_179927_g.motionY += d1 / d3 * 0.1D;
//                        this.field_179927_g.motionZ += d2 / d3 * 0.1D;
//                    }
//                    else
//                    {
//                        this.update = false;
//                    }
//                }
//            }
//        }
//
//        private boolean func_179926_b(double p_179926_1_, double p_179926_3_, double p_179926_5_, double p_179926_7_)
//        {
//            double d4 = (p_179926_1_ - this.field_179927_g.posX) / p_179926_7_;
//            double d5 = (p_179926_3_ - this.field_179927_g.posY) / p_179926_7_;
//            double d6 = (p_179926_5_ - this.field_179927_g.posZ) / p_179926_7_;
//            AxisAlignedBB axisalignedbb = this.field_179927_g.getEntityBoundingBox();
//
//            for (int i = 1; (double)i < p_179926_7_; ++i)
//            {
//                axisalignedbb = axisalignedbb.offset(d4, d5, d6);
//
//                if (!this.field_179927_g.worldObj.getCollidingBoundingBoxes(this.field_179927_g, axisalignedbb).isEmpty())
//                {
//                    return false;
//                }
//            }
//
//            return true;
//        }
//    }
//}