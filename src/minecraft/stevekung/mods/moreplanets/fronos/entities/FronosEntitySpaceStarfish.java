/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosEntitySpaceStarfish extends EntityAnimal implements IEntityBreathable
{
	public FronosEntitySpaceStarfish(World par1World)
	{
		super(par1World);
		this.setSize(0.2F, 0.15F);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.4D));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new MPEntityAITempt(this, 1.1D, new ItemStack(FronosItems.fronosFood2.itemID, 1, 0), false));
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
	public void knockBack(Entity par1Entity, float par2, double par3, double par5)
	{
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.025D);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	protected void fall(float par1) {}

	@Override
	protected String getHurtSound()
	{
		return "mob.slime.small";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.slime.small";
	}

	@Override
	protected int getDropItemId()
	{
		return 0;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		int meta = par1ItemStack.getItemDamage();
		return par1ItemStack.itemID == FronosItems.fronosFood2.itemID && meta == 1;
	}

	/*@Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

        for (int k = 0; k < j; ++k)
        {
            this.dropItem(Item.feather.itemID, 1);
        }

        if (this.isBurning())
        {
            this.dropItem(Item.chickenCooked.itemID, 1);
        }
        else
        {
            this.dropItem(Item.chickenRaw.itemID, 1);
        }
    }*/

	public FronosEntitySpaceStarfish spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new FronosEntitySpaceStarfish(this.worldObj);
	}

	/*@Override
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemFood;
    }*/

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
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean allowLeashing()
	{
		return false;
	}
}