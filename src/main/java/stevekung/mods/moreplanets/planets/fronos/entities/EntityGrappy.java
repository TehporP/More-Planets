/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

import com.google.common.collect.Maps;

public class EntityGrappy extends EntityAnimal implements IShearable
{
	private InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container()
	{
		@Override
		public boolean canInteractWith(EntityPlayer player)
		{
			return false;
		}
	}, 2, 1);
	private static Map field_175514_bm = Maps.newEnumMap(EnumDyeColor.class);
	private int sheepTimer;
	private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);

	public static float[] func_175513_a(EnumDyeColor dye)
	{
		return (float[])field_175514_bm.get(dye);
	}

	public EntityGrappy(World world)
	{
		super(world);
		this.setSize(0.9F, 0.7F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Items.wheat, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(5, this.entityAIEatGrass);
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.dye, 1, 0));
		this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.dye, 1, 0));
	}

	@Override
	public float getEyeHeight()
	{
		return this.height - 0.4F;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		Block block = this.worldObj.getBlockState(this.getPosition().down()).getBlock();
		return block == FronosBlocks.fronos_grass;
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		return itemStack != null && itemStack.getItem() == FronosItems.fronos_item && itemStack.getItemDamage() == 6;
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1024);
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1024)
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
	protected void updateAITasks()
	{
		this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	@Override
	protected void dropFewItems(boolean drop, int par2)
	{
		if (!this.getSheared())
		{
			this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()), 0.0F);
		}

		int j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
		{
			if (this.isBurning())
			{
				this.dropItem(Items.cooked_mutton, 1);
			}
			else
			{
				this.dropItem(Items.mutton, 1);
			}
		}
	}

	@Override
	protected Item getDropItem()
	{
		return Item.getItemFromBlock(Blocks.wool);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte health)
	{
		if (health == 10)
		{
			this.sheepTimer = 40;
		}
		else
		{
			super.handleHealthUpdate(health);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float par1)
	{
		return this.sheepTimer <= 0 ? 0.0F : this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : this.sheepTimer < 4 ? (this.sheepTimer - par1) / 4.0F : -(this.sheepTimer - 40 - par1) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float par1)
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
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Sheared", this.getSheared());
		nbt.setByte("Color", (byte)this.getFleeceColor().getMetadata());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setSheared(nbt.getBoolean("Sheared"));
		this.setFleeceColor(EnumDyeColor.byMetadata(nbt.getByte("Color")));
	}

	@Override
	protected String getLivingSound()
	{
		return "moreplanets:mob.fronos.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "moreplanets:mob.fronos.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "moreplanets:mob.fronos.death";
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("mob.sheep.step", 0.15F, 1.0F);
	}

	public EnumDyeColor getFleeceColor()
	{
		return EnumDyeColor.byMetadata(this.dataWatcher.getWatchableObjectByte(16) & 15);
	}

	public void setFleeceColor(EnumDyeColor color)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & 240 | color.getMetadata() & 15)));
	}

	public boolean getSheared()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
	}

	public void setSheared(boolean sheared)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (sheared)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 16)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -17)));
		}
	}

	public static EnumDyeColor func_175510_a(Random rand)
	{
		int i = rand.nextInt(100);
		return i < 5 ? EnumDyeColor.BLACK : i < 10 ? EnumDyeColor.GRAY : i < 15 ? EnumDyeColor.SILVER : i < 18 ? EnumDyeColor.BROWN : rand.nextInt(30) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE;
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
	public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
	{
		p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
		this.setFleeceColor(func_175510_a(this.worldObj.rand));
		return p_180482_2_;
	}

	private EnumDyeColor func_175511_a(EntityAnimal p_175511_1_, EntityAnimal p_175511_2_)
	{
		int i = ((EntityGrappy)p_175511_1_).getFleeceColor().getDyeDamage();
		int j = ((EntityGrappy)p_175511_2_).getFleeceColor().getDyeDamage();
		this.inventoryCrafting.getStackInSlot(0).setItemDamage(i);
		this.inventoryCrafting.getStackInSlot(1).setItemDamage(j);
		ItemStack itemstack = CraftingManager.getInstance().findMatchingRecipe(this.inventoryCrafting, ((EntityGrappy)p_175511_1_).worldObj);
		int k;

		if (itemstack != null && itemstack.getItem() == Items.dye)
		{
			k = itemstack.getMetadata();
		}
		else
		{
			k = this.worldObj.rand.nextBoolean() ? i : j;
		}

		return EnumDyeColor.byDyeDamage(k);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		EntityGrappy entitysheep = (EntityGrappy)ageable;
		EntityGrappy entitysheep1 = new EntityGrappy(this.worldObj);
		entitysheep1.setFleeceColor(this.func_175511_a(this, entitysheep));
		return entitysheep1;
	}

	static
	{
		field_175514_bm.put(EnumDyeColor.WHITE, new float[] {1.0F, 1.0F, 1.0F});
		field_175514_bm.put(EnumDyeColor.ORANGE, new float[] {0.85F, 0.5F, 0.2F});
		field_175514_bm.put(EnumDyeColor.MAGENTA, new float[] {0.7F, 0.3F, 0.85F});
		field_175514_bm.put(EnumDyeColor.LIGHT_BLUE, new float[] {0.4F, 0.6F, 0.85F});
		field_175514_bm.put(EnumDyeColor.YELLOW, new float[] {0.9F, 0.9F, 0.2F});
		field_175514_bm.put(EnumDyeColor.LIME, new float[] {0.5F, 0.8F, 0.1F});
		field_175514_bm.put(EnumDyeColor.PINK, new float[] {0.95F, 0.5F, 0.65F});
		field_175514_bm.put(EnumDyeColor.GRAY, new float[] {0.3F, 0.3F, 0.3F});
		field_175514_bm.put(EnumDyeColor.SILVER, new float[] {0.6F, 0.6F, 0.6F});
		field_175514_bm.put(EnumDyeColor.CYAN, new float[] {0.3F, 0.5F, 0.6F});
		field_175514_bm.put(EnumDyeColor.PURPLE, new float[] {0.5F, 0.25F, 0.7F});
		field_175514_bm.put(EnumDyeColor.BLUE, new float[] {0.2F, 0.3F, 0.7F});
		field_175514_bm.put(EnumDyeColor.BROWN, new float[] {0.4F, 0.3F, 0.2F});
		field_175514_bm.put(EnumDyeColor.GREEN, new float[] {0.4F, 0.5F, 0.2F});
		field_175514_bm.put(EnumDyeColor.RED, new float[] {0.6F, 0.2F, 0.2F});
		field_175514_bm.put(EnumDyeColor.BLACK, new float[] {0.1F, 0.1F, 0.1F});
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return !this.getSheared() && !this.isChild();
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		this.setSheared(true);
		int i = 1 + this.rand.nextInt(3);

		List<ItemStack> ret = new ArrayList<ItemStack>();
		for (int j = 0; j < i; ++j) {
			ret.add(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()));
		}

		this.playSound("mob.sheep.shear", 1.0F, 1.0F);
		return ret;
	}
}