/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJetpack extends ItemArmorMP
{
	int tick;

	public ItemJetpack(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == this)
		{
			return "venus:textures/model/jetpack.png";
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack itemStack, int slot)
	{
		ModelBiped armorModel = ClientProxyMP.jetpackModel.get(this);

		if (armorModel != null)
		{
			armorModel.bipedHead.showModel = slot == 0;

			if (living.isSneaking())
			{
				armorModel.isSneak = true;
			}
			else
			{
				armorModel.isSneak = false;
			}
		}
		return armorModel;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean selected)
	{
		if (!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
		itemStack.getTagCompound().setInteger("JetpackTick", this.tick);
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		super.onCreated(itemStack, world, player);

		if (!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
		itemStack.getTagCompound().setInteger("JetpackTick", this.tick);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		boolean flag = player.capabilities.isCreativeMode;

		if (itemStack.getItem() == this)
		{
			if (flag || player.inventory.hasItem(Items.coal))
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				{
					player.motionY = 0.4D;
					this.tick++;

					for (int i = 0; i < 5; i++)
					{
						world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -1.0D, 0);
					}

					if (this.tick >= 1000)
					{
						if (!world.isRemote && !flag)
						{
							player.inventory.consumeInventoryItem(Items.coal);
						}
						if (this.tick >= 1001)
						{
							this.tick = 0;
						}
					}
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !player.onGround)
				{
					player.motionY *= 0.7D;

					for (int i = 0; i < 5; i++)
					{
						world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -2.0D, 0);
					}
					if (this.tick >= 1000)
					{
						if (!world.isRemote && !flag)
						{
							player.inventory.consumeInventoryItem(Items.coal);
						}
						if (this.tick >= 1001)
						{
							this.tick = 0;
						}
					}
				}
			}
		}
	}

	@Override
	public String getTextureLocation()
	{
		return "venus";
	}

	@Override
	public Item getRepairItems()
	{
		return null;
	}

	@Override
	public int getRepairItemsMetadata()
	{
		return -1;
	}
}