/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class PolongniusEntityCheeseCow extends EntityAnimal implements IEntityBreathable
{
	public PolongniusEntityCheeseCow(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 1.3F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		int block = this.worldObj.getBlockId(i, j - 1, k);
		this.worldObj.getBlockMetadata(i, j, k);
		return block == PolongniusBlocks.polongniusBasicBlock.blockID/* && meta == 7*/;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
	{
		return 1 + this.worldObj.rand.nextInt(5);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack.itemID == PolongniusItems.polongniusFood.itemID && par1ItemStack.getItemDamage() == 0;
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.cow.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.cow.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.cow.hurt";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.cow.step", 0.15F, 1.0F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (itemstack != null && itemstack.itemID == Item.bucketEmpty.itemID && !par1EntityPlayer.capabilities.isCreativeMode)
		{
			if (itemstack.stackSize-- == 1)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(PolongniusItems.polongniusFood, 1, 4));
			}
			else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(PolongniusItems.polongniusFood, 1, 4)) && !par1EntityPlayer.capabilities.isCreativeMode)
			{
				par1EntityPlayer.dropPlayerItem(new ItemStack(PolongniusItems.polongniusFood.itemID, 1, 0));
			}
			return true;
		}

		else if (itemstack != null && itemstack.itemID == Item.bowlEmpty.itemID && !par1EntityPlayer.capabilities.isCreativeMode)
		{
			if (itemstack.stackSize-- == 1)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(PolongniusItems.polongniusFood, 1, 3));
			}
			else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(PolongniusItems.polongniusFood, 1, 3)) && !par1EntityPlayer.capabilities.isCreativeMode)
			{
				par1EntityPlayer.dropPlayerItem(new ItemStack(PolongniusItems.polongniusFood.itemID, 1, 0));
			}
			return true;
		}
		else
		{
			return super.interact(par1EntityPlayer);
		}
	}

	public PolongniusEntityCheeseCow func_94900_c(EntityAgeable par1EntityAgeable)
	{
		return new PolongniusEntityCheeseCow(this.worldObj);
	}

	public PolongniusEntityCheeseCow spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return this.func_94900_c(par1EntityAgeable);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.func_94900_c(par1EntityAgeable);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.entityDropItem(new ItemStack(PolongniusItems.polongniusBasicItem, 1, 9), 0.0F);
		}

		if (this.isBurning())
		{
			this.entityDropItem(new ItemStack(PolongniusItems.polongniusFood, 1, 2), 0.0F);
		}
		else
		{
			this.entityDropItem(new ItemStack(PolongniusItems.polongniusFood, 1, 1), 0.0F);
		}
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}
}