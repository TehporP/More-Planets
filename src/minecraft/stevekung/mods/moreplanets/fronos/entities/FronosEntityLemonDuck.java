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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.api.entities.IImmuneMapleIvy;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosEntityLemonDuck extends EntityAnimal implements IEntityBreathable, IImmuneMapleIvy
{
	public FronosEntityLemonDuck(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 1.0F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
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
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.150D);
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.50D;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(FronosItems.fruits, 1, 1), 0.0F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1)
	{
		super.handleHealthUpdate(par1);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
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
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
	{
		return 1 + this.worldObj.rand.nextInt(4);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.8F;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("fronos:mob.marshmallow.step", 0.15F, 1.0F);
	}

	public FronosEntityLemonDuck spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new FronosEntityLemonDuck(this.worldObj);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		int meta = par1ItemStack.getItemDamage();
		return par1ItemStack.itemID == FronosItems.fronosFood2.itemID && meta == 1;
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
			FronosEntityLemonDuck marshmallow = new FronosEntityLemonDuck(this.worldObj);
			marshmallow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			marshmallow.onSpawnWithEgg((EntityLivingData)null);
			this.worldObj.spawnEntityInWorld(marshmallow);
			marshmallow.mountEntity(this);
			marshmallow.setGrowingAge(-8000);
		}
		return par1EntityLivingData;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}