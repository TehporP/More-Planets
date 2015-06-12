/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities;

import java.util.ArrayList;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
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
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.core.entities.ai.MPEntityAITempt;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.ai.FronosEntityAIEatGrass;
import stevekung.mods.moreplanets.fronos.entities.template.FronosContainerGrappy;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosEntityGrappy extends EntityAnimal implements IShearable, IEntityBreathable
{
	private final InventoryCrafting field_90016_e = new InventoryCrafting(new FronosContainerGrappy(this), 2, 1);
	public static final float[][] fleeceColorTable = new float[][] {{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
	private int sheepTimer;

	private FronosEntityAIEatGrass aiEatGrass = new FronosEntityAIEatGrass(this);

	public FronosEntityGrappy(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 0.4F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new MPEntityAITempt(this, 1.1D, new ItemStack(FronosItems.fronosGem.itemID, 1, 1), false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(5, this.aiEatGrass);
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.field_90016_e.setInventorySlotContents(0, new ItemStack(Item.dyePowder, 1, 0));
		this.field_90016_e.setInventorySlotContents(1, new ItemStack(Item.dyePowder, 1, 0));
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
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack.itemID == FronosItems.fronosGem.itemID && par1ItemStack.getItemDamage() == 1;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void updateAITasks()
	{
		this.sheepTimer = this.aiEatGrass.getEatGrassTick();
		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}
		super.onLivingUpdate();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		if (!this.getSheared())
		{
			this.entityDropItem(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()), 0.0F);
		}
	}

	@Override
	protected int getDropItemId()
	{
		return Block.cloth.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1)
	{
		if (par1 == 10)
		{
			this.sheepTimer = 40;
		}
		else
		{
			super.handleHealthUpdate(par1);
		}
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return super.interact(par1EntityPlayer);
	}

	@SideOnly(Side.CLIENT)
	public float func_70894_j(float par1)
	{
		return this.sheepTimer <= 0 ? 0.0F : this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : this.sheepTimer < 4 ? (this.sheepTimer - par1) / 4.0F : -(this.sheepTimer - 40 - par1) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float func_70890_k(float par1)
	{
		if (this.sheepTimer > 4 && this.sheepTimer <= 36)
		{
			float f1 = (this.sheepTimer - 4 - par1) / 32.0F;
			return (float)Math.PI / 5F + (float)Math.PI * 7F / 100F * MathHelper.sin(f1 * 28.7F);
		}
		else
		{
			return this.sheepTimer > 0 ? (float)Math.PI / 5F : this.rotationPitch / (180F / (float)Math.PI);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Sheared", this.getSheared());
		par1NBTTagCompound.setByte("Color", (byte)this.getFleeceColor());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setSheared(par1NBTTagCompound.getBoolean("Sheared"));
		this.setFleeceColor(par1NBTTagCompound.getByte("Color"));
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

	public int getFleeceColor()
	{
		return this.dataWatcher.getWatchableObjectByte(16) & 15;
	}

	public void setFleeceColor(int par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & 240 | par1 & 15)));
	}

	public boolean getSheared()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
	}

	public void setSheared(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 16)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -17)));
		}
	}

	public static int getRandomFleeceColor(Random par0Random)
	{
		int i = par0Random.nextInt(100);
		return i < 5 ? 12 : i < 10 ? 7 : i < 15 ? 15 : i < 18 ? 6 : i < 20 ? 10 : par0Random.nextInt(30) == 0 ? 5 : 0;
	}

	public FronosEntityGrappy func_90015_b(EntityAgeable par1EntityAgeable)
	{
		FronosEntityGrappy entitysheep = (FronosEntityGrappy)par1EntityAgeable;
		FronosEntityGrappy entitysheep1 = new FronosEntityGrappy(this.worldObj);
		int i = this.func_90014_a(this, entitysheep);
		entitysheep1.setFleeceColor(15 - i);
		return entitysheep1;
	}

	@Override
	public void eatGrassBonus()
	{
		this.setSheared(false);

		if (this.isChild())
		{
			this.addGrowth(60);
		}
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
	{
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
		this.setFleeceColor(getRandomFleeceColor(this.worldObj.rand));
		return par1EntityLivingData;
	}

	private int func_90014_a(EntityAnimal par1EntityAnimal, EntityAnimal par2EntityAnimal)
	{
		int i = this.func_90013_b(par1EntityAnimal);
		int j = this.func_90013_b(par2EntityAnimal);
		this.field_90016_e.getStackInSlot(0).setItemDamage(i);
		this.field_90016_e.getStackInSlot(1).setItemDamage(j);
		ItemStack itemstack = CraftingManager.getInstance().findMatchingRecipe(this.field_90016_e, ((FronosEntityGrappy)par1EntityAnimal).worldObj);
		int k;

		if (itemstack != null && itemstack.getItem().itemID == Item.dyePowder.itemID)
		{
			k = itemstack.getItemDamage();
		}
		else
		{
			k = this.worldObj.rand.nextBoolean() ? i : j;
		}
		return k;
	}

	private int func_90013_b(EntityAnimal par1EntityAnimal)
	{
		return 15 - ((FronosEntityGrappy)par1EntityAnimal).getFleeceColor();
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.func_90015_b(par1EntityAgeable);
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int X, int Y, int Z)
	{
		return !this.getSheared() && !this.isChild();
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int X, int Y, int Z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		this.setSheared(true);
		int i = 1 + this.rand.nextInt(3);

		for (int j = 0; j < i; j++)
		{
			ret.add(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()));
		}

		this.worldObj.playSoundAtEntity(this, "mob.sheep.shear", 1.0F, 1.0F);
		return ret;
	}

	@Override
	public boolean canBreath()
	{
		return true;
	}
}