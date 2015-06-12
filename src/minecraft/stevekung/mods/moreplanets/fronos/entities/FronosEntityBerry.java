/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.api.entities.IImmuneMapleIvy;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosEntityBerry extends EntityAnimal implements IEntityBreathable, IImmuneMapleIvy
{
	public int timeUntilToDropBerry;

	public FronosEntityBerry(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.7F);
		this.timeUntilToDropBerry = this.rand.nextInt(2000) + 2000;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new MPEntityAITempt(this, 1.1D, new ItemStack(FronosItems.fronosFood2.itemID, 1, 0), false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		int block = this.worldObj.getBlockId(i, j - 1, k);
		return block == FronosBlocks.fronosGrass.blockID;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 1.25D;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	protected String getLivingSound()
	{
		return "fronos:mob.marshmallow.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "fronos:mob.marshmallow.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "fronos:mob.marshmallow.death";
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.5F;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.isChild() && !this.worldObj.isRemote && --this.timeUntilToDropBerry <= 0)
		{
			this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.entityDropItem(new ItemStack(FronosItems.fronosFood, 1, 1), 0.0F);
			this.timeUntilToDropBerry = this.rand.nextInt(2000) + 2000;
		}
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
	{
		return 1 + this.worldObj.rand.nextInt(6);
	}

	public FronosEntityBerry spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new FronosEntityBerry(this.worldObj);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(FronosItems.fronosFood, 1, 1), 0.0F);
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		int meta = par1ItemStack.getItemDamage();
		return par1ItemStack.itemID == FronosItems.fronosFood2.itemID && meta == 1;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
	{
		if (this.worldObj.rand.nextInt(20) == 0)
		{
			FronosEntityMarshmallow marshmallow = new FronosEntityMarshmallow(this.worldObj);
			marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			marshmallow.onSpawnWithEgg((EntityLivingData)null);
			this.worldObj.spawnEntityInWorld(marshmallow);
			marshmallow.mountEntity(this);
			marshmallow.setGrowingAge(-8000);
		}
		return par1EntityLivingData;
	}
}