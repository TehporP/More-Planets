/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityChocolateCreamBall;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosEntityChocolateGolem extends EntityGolem implements IRangedAttackMob, IEntityBreathable
{
	public FronosEntityChocolateGolem(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 1.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
	{
		return 1 + this.worldObj.rand.nextInt(4);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posZ);

		for (i = 0; i < 4; ++i)
		{
			j = MathHelper.floor_double(this.posX + (i % 2 * 2 - 1) * 0.25F);
			int k = MathHelper.floor_double(this.posY);
			int l = MathHelper.floor_double(this.posZ + (i / 2 % 2 * 2 - 1) * 0.25F);

			if (this.worldObj.getBlockId(j, k, l) == 0 && FronosBlocks.chocolateCreamLayer.canPlaceBlockAt(this.worldObj, j, k, l))
			{
				this.worldObj.setBlock(j, k, l, FronosBlocks.chocolateCreamLayer.blockID);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(16);

		for (int k = 4; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(FronosItems.creamBall, 1, 1), 0.0F);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
	{
		FronosEntityChocolateCreamBall entitysnowball = new FronosEntityChocolateCreamBall(this.worldObj, this);
		double d0 = par1EntityLivingBase.posX - this.posX;
		double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
		double d2 = par1EntityLivingBase.posZ - this.posZ;
		float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
		entitysnowball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(entitysnowball);
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}
}