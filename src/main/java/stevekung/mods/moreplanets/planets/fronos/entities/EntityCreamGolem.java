/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityChocolateCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityLemonCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityOrangeCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityStrawberryCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityTeaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityVanillaCreamBall;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityCreamGolem extends EntityGolem implements IRangedAttackMob
{
	public EntityCreamGolem(World world)
	{
		super(world);
		this.setSize(0.7F, 1.9F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.mobSelector));
		this.setCreamGolemType(this.rand.nextInt(6));
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1026);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player)
	{
		return 1 + this.worldObj.rand.nextInt(6);
	}

	@Override
	protected String getLivingSound()
	{
		return null;
	}

	@Override
	protected String getHurtSound()
	{
		return null;
	}

	@Override
	protected String getDeathSound()
	{
		return null;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.worldObj.isRemote)
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY);
			int k = MathHelper.floor_double(this.posZ);

			if (this.isWet())
			{
				this.attackEntityFrom(DamageSource.drown, 1.0F);
			}

			for (int l = 0; l < 4; ++l)
			{
				i = MathHelper.floor_double(this.posX + (l % 2 * 2 - 1) * 0.25F);
				j = MathHelper.floor_double(this.posY);
				k = MathHelper.floor_double(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);

				int golemType = this.getCreamGolemType();

				if (golemType == 0)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.vanilla_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.vanilla_cream_layer.getDefaultState());
					}
				}
				else if (golemType == 1)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.chocolate_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.chocolate_cream_layer.getDefaultState());
					}
				}
				else if (golemType == 2)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.strawberry_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.strawberry_cream_layer.getDefaultState());
					}
				}
				else if (golemType == 3)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.orange_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.orange_cream_layer.getDefaultState());
					}
				}
				else if (golemType == 4)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.tea_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.tea_cream_layer.getDefaultState());
					}
				}
				else if (golemType == 5)
				{
					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && FronosBlocks.lemon_cream_layer.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), FronosBlocks.lemon_cream_layer.getDefaultState());
					}
				}
			}
		}
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(16);

		for (int i = 4; i < j; ++i)
		{
			this.entityDropItem(new ItemStack(FronosItems.cream_ball, 1, this.getCreamGolemType()), 0.0F);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("CreamGolemType", this.getCreamGolemType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setCreamGolemType(nbt.getInteger("CreamGolemType"));
	}

	public int getCreamGolemType()
	{
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	public void setCreamGolemType(int par1)
	{
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase living, float par2)
	{
		double d0 = living.posY + living.getEyeHeight() - 1.100000023841858D;
		double d1 = living.posX - this.posX;
		double d3 = living.posZ - this.posZ;
		float f1 = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));

		int golemType = this.getCreamGolemType();

		if (golemType == 0)
		{
			EntityVanillaCreamBall entitysnowball = new EntityVanillaCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
		else if (golemType == 1)
		{
			EntityChocolateCreamBall entitysnowball = new EntityChocolateCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
		else if (golemType == 2)
		{
			EntityStrawberryCreamBall entitysnowball = new EntityStrawberryCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
		else if (golemType == 3)
		{
			EntityOrangeCreamBall entitysnowball = new EntityOrangeCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
		else if (golemType == 4)
		{
			EntityTeaCreamBall entitysnowball = new EntityTeaCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
		else if (golemType == 5)
		{
			EntityLemonCreamBall entitysnowball = new EntityLemonCreamBall(this.worldObj, this);
			double d2 = d0 - entitysnowball.posY;
			entitysnowball.setThrowableHeading(d1, d2 + f1, d3, 1.6F, 12.0F);
			this.worldObj.spawnEntityInWorld(entitysnowball);
		}
	}

	@Override
	public float getEyeHeight()
	{
		return 1.7F;
	}
}