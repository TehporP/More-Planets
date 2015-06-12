/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

public class EntityAIFronosVillagerInteract extends EntityAIWatchClosest2
{
	private int field_179478_e;
	private EntityFronosVillager villagerObj;

	public EntityAIFronosVillagerInteract(EntityFronosVillager p_i45886_1_)
	{
		super(p_i45886_1_, EntityFronosVillager.class, 3.0F, 0.02F);
		this.villagerObj = p_i45886_1_;
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();

		if (this.villagerObj.func_175555_cq() && this.closestEntity instanceof EntityFronosVillager && ((EntityFronosVillager)this.closestEntity).func_175557_cr())
		{
			this.field_179478_e = 10;
		}
		else
		{
			this.field_179478_e = 0;
		}
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.field_179478_e > 0)
		{
			--this.field_179478_e;

			if (this.field_179478_e == 0)
			{
				InventoryBasic inventorybasic = this.villagerObj.func_175551_co();

				for (int i = 0; i < inventorybasic.getSizeInventory(); ++i)
				{
					ItemStack itemstack = inventorybasic.getStackInSlot(i);
					ItemStack itemstack1 = null;

					if (itemstack != null)
					{
						Item item = itemstack.getItem();
						int j;

						if ((item == Items.bread || item == Items.potato || item == Items.carrot) && itemstack.stackSize > 3)
						{
							j = itemstack.stackSize / 2;
							itemstack.stackSize -= j;
							itemstack1 = new ItemStack(item, j, itemstack.getMetadata());
						}
						else if (item == Items.wheat && itemstack.stackSize > 5)
						{
							j = itemstack.stackSize / 2 / 3 * 3;
							int k = j / 3;
							itemstack.stackSize -= j;
							itemstack1 = new ItemStack(Items.bread, k, 0);
						}

						if (itemstack.stackSize <= 0)
						{
							inventorybasic.setInventorySlotContents(i, (ItemStack)null);
						}
					}

					if (itemstack1 != null)
					{
						double d0 = this.villagerObj.posY - 0.30000001192092896D + this.villagerObj.getEyeHeight();
						EntityItem entityitem = new EntityItem(this.villagerObj.worldObj, this.villagerObj.posX, d0, this.villagerObj.posZ, itemstack1);
						float f = 0.3F;
						float f1 = this.villagerObj.rotationYawHead;
						float f2 = this.villagerObj.rotationPitch;
						entityitem.motionX = -MathHelper.sin(f1 / 180.0F * (float)Math.PI) * MathHelper.cos(f2 / 180.0F * (float)Math.PI) * f;
						entityitem.motionZ = MathHelper.cos(f1 / 180.0F * (float)Math.PI) * MathHelper.cos(f2 / 180.0F * (float)Math.PI) * f;
						entityitem.motionY = -MathHelper.sin(f2 / 180.0F * (float)Math.PI) * f + 0.1F;
						entityitem.setDefaultPickupDelay();
						this.villagerObj.worldObj.spawnEntityInWorld(entityitem);
						break;
					}
				}
			}
		}
	}
}