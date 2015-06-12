/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAIFronosVillagerFollowGolem;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAIFronosVillagerHarvestFarmland;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAIFronosVillagerInteract;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAIFronosVillagerMate;
import stevekung.mods.moreplanets.planets.fronos.entities.ai.EntityAIFronosVillagerPlay;

import com.google.common.base.Predicate;

public class EntityFronosVillager extends EntityAgeable implements INpc
{
	private int randomTickDivider;
	private boolean isMating;
	private boolean isPlaying;
	Village villageObj;
	private int wealth;
	private boolean isLookingForHome;
	private boolean field_175564_by;
	private InventoryBasic villagerInventory;

	public EntityFronosVillager(World world)
	{
		super(world);
		this.villagerInventory = new InventoryBasic("Items", false, 8);
		this.setSize(0.6F, 1.8F);
		((PathNavigateGround)this.getNavigator()).func_179688_b(true);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
		{
			public boolean func_179530_a(Entity p_179530_1_)
			{
				return p_179530_1_ instanceof EntityZombie;
			}
			@Override
			public boolean apply(Object p_apply_1_)
			{
				return this.func_179530_a((Entity)p_apply_1_);
			}
		}, 8.0F, 0.6D, 0.6D));
		this.tasks.addTask(1, new EntityAIMoveIndoors(this));
		this.tasks.addTask(2, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(5, new EntityAIFronosVillagerMate(this));
		this.tasks.addTask(6, new EntityAIFronosVillagerFollowGolem(this));
		this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(7, new EntityAIFronosVillagerInteract(this));
		this.tasks.addTask(7, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.setCanPickUpLoot(true);
	}

	private void func_175552_ct()
	{
		if (!this.field_175564_by)
		{
			this.field_175564_by = true;

			if (this.isChild())
			{
				this.tasks.addTask(8, new EntityAIFronosVillagerPlay(this, 0.32D));
			}
			this.tasks.addTask(6, new EntityAIFronosVillagerHarvestFarmland(this, 0.6D));
		}
	}

	@Override
	protected void func_175500_n()
	{
		this.tasks.addTask(8, new EntityAIFronosVillagerHarvestFarmland(this, 0.6D));
		super.func_175500_n();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	}

	@Override
	protected void updateAITasks()
	{
		if (--this.randomTickDivider <= 0)
		{
			BlockPos blockpos = new BlockPos(this);
			this.worldObj.getVillageCollection().addToVillagerPositionList(blockpos);
			this.randomTickDivider = 70 + this.rand.nextInt(50);
			this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(blockpos, 32);

			if (this.villageObj == null)
			{
				this.detachHome();
			}
			else
			{
				BlockPos blockpos1 = this.villageObj.getCenter();
				this.func_175449_a(blockpos1, (int)(this.villageObj.getVillageRadius() * 1.0F));

				if (this.isLookingForHome)
				{
					this.isLookingForHome = false;
					this.villageObj.setDefaultPlayerReputation(5);
				}
			}
		}
		super.updateAITasks();
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1028)
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
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1028);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Riches", this.wealth);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

			if (itemstack != null)
			{
				nbttaglist.appendTag(itemstack.writeToNBT(new NBTTagCompound()));
			}
		}
		nbt.setTag("Inventory", nbttaglist);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.wealth = nbt.getInteger("Riches");

		NBTTagList nbttaglist = nbt.getTagList("Inventory", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));

			if (itemstack != null)
			{
				this.villagerInventory.func_174894_a(itemstack);
			}
		}
		this.setCanPickUpLoot(true);
		this.func_175552_ct();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.villager.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.villager.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.villager.death";
	}

	public boolean isMating()
	{
		return this.isMating;
	}

	public void setMating(boolean mating)
	{
		this.isMating = mating;
	}

	public void setPlaying(boolean playing)
	{
		this.isPlaying = playing;
	}

	public boolean isPlaying()
	{
		return this.isPlaying;
	}

	@Override
	public void setRevengeTarget(EntityLivingBase livingBase)
	{
		super.setRevengeTarget(livingBase);

		if (this.villageObj != null && livingBase != null)
		{
			this.villageObj.addOrRenewAgressor(livingBase);

			if (livingBase instanceof EntityPlayer)
			{
				byte b0 = -1;

				if (this.isChild())
				{
					b0 = -3;
				}

				this.villageObj.setReputationForPlayer(livingBase.getName(), b0);

				if (this.isEntityAlive())
				{
					this.worldObj.setEntityState(this, (byte)13);
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource source)
	{
		if (this.villageObj != null)
		{
			Entity entity = source.getEntity();

			if (entity != null)
			{
				if (entity instanceof EntityPlayer)
				{
					this.villageObj.setReputationForPlayer(entity.getName(), -2);
				}
				else if (entity instanceof IMob)
				{
					this.villageObj.endMatingSeason();
				}
			}
			else
			{
				EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

				if (entityplayer != null)
				{
					this.villageObj.endMatingSeason();
				}
			}
		}
		super.onDeath(source);
	}

	public boolean func_175550_n(boolean p_175550_1_)
	{
		if (p_175550_1_ && this.func_175553_cp())
		{
			boolean flag1 = false;

			for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

				if (itemstack != null)
				{
					if (itemstack.getItem() == Items.bread && itemstack.stackSize >= 3)
					{
						flag1 = true;
						this.villagerInventory.decrStackSize(i, 3);
					}
					else if ((itemstack.getItem() == Items.potato || itemstack.getItem() == Items.carrot) && itemstack.stackSize >= 12)
					{
						flag1 = true;
						this.villagerInventory.decrStackSize(i, 12);
					}
				}

				if (flag1)
				{
					this.worldObj.setEntityState(this, (byte)18);
					break;
				}
			}
		}
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		float f = 1.62F;

		if (this.isChild())
		{
			f = (float)(f - 0.81D);
		}
		return f;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte p_70103_1_)
	{
		if (p_70103_1_ == 12)
		{
			this.func_180489_a(EnumParticleTypes.HEART);
		}
		else if (p_70103_1_ == 13)
		{
			this.func_180489_a(EnumParticleTypes.VILLAGER_ANGRY);
		}
		else if (p_70103_1_ == 14)
		{
			this.func_180489_a(EnumParticleTypes.VILLAGER_HAPPY);
		}
		else
		{
			super.handleHealthUpdate(p_70103_1_);
		}
	}

	@SideOnly(Side.CLIENT)
	private void func_180489_a(EnumParticleTypes p_180489_1_)
	{
		for (int i = 0; i < 5; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(p_180489_1_, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 1.0D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
		}
	}

	public void setLookingForHome()
	{
		this.isLookingForHome = true;
	}

	public EntityFronosVillager func_180488_b(EntityAgeable p_180488_1_)
	{
		EntityFronosVillager entityvillager = new EntityFronosVillager(this.worldObj);
		entityvillager.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null);
		return entityvillager;
	}

	@Override
	public boolean allowLeashing()
	{
		return false;
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		if (!this.worldObj.isRemote)
		{
			EntityWitch entitywitch = new EntityWitch(this.worldObj);
			entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			entitywitch.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(entitywitch)), (IEntityLivingData)null);
			this.worldObj.spawnEntityInWorld(entitywitch);
			this.setDead();
		}
	}

	public InventoryBasic func_175551_co()
	{
		return this.villagerInventory;
	}

	@Override
	protected void func_175445_a(EntityItem p_175445_1_)
	{
		ItemStack itemstack = p_175445_1_.getEntityItem();
		Item item = itemstack.getItem();

		if (this.func_175558_a(item))
		{
			ItemStack itemstack1 = this.villagerInventory.func_174894_a(itemstack);

			if (itemstack1 == null)
			{
				p_175445_1_.setDead();
			}
			else
			{
				itemstack.stackSize = itemstack1.stackSize;
			}
		}
	}

	private boolean func_175558_a(Item p_175558_1_)
	{
		return p_175558_1_ == Items.bread || p_175558_1_ == Items.potato || p_175558_1_ == Items.carrot || p_175558_1_ == Items.wheat || p_175558_1_ == Items.wheat_seeds;
	}

	public boolean func_175553_cp()
	{
		return this.func_175559_s(1);
	}

	public boolean func_175555_cq()
	{
		return this.func_175559_s(2);
	}

	public boolean func_175557_cr()
	{
		return !this.func_175559_s(1);
	}

	private boolean func_175559_s(int p_175559_1_)
	{
		for (int j = 0; j < this.villagerInventory.getSizeInventory(); ++j)
		{
			ItemStack itemstack = this.villagerInventory.getStackInSlot(j);

			if (itemstack != null)
			{
				if (itemstack.getItem() == Items.bread && itemstack.stackSize >= 3 * p_175559_1_ || itemstack.getItem() == Items.potato && itemstack.stackSize >= 12 * p_175559_1_ || itemstack.getItem() == Items.carrot && itemstack.stackSize >= 12 * p_175559_1_)
				{
					return true;
				}
				if (itemstack.getItem() == Items.wheat && itemstack.stackSize >= 9 * p_175559_1_)
				{
					return true;
				}
			}
		}
		return false;
	}

	public boolean func_175556_cs()
	{
		for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

			if (itemstack != null && (itemstack.getItem() == Items.wheat_seeds || itemstack.getItem() == Items.potato || itemstack.getItem() == Items.carrot))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean replaceItemInInventory(int p_174820_1_, ItemStack p_174820_2_)
	{
		if (super.replaceItemInInventory(p_174820_1_, p_174820_2_))
		{
			return true;
		}
		else
		{
			int j = p_174820_1_ - 300;

			if (j >= 0 && j < this.villagerInventory.getSizeInventory())
			{
				this.villagerInventory.setInventorySlotContents(j, p_174820_2_);
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return this.func_180488_b(ageable);
	}
}