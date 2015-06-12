/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemBlockDandelion extends ItemBlockBaseMP
{
	public ItemBlockDandelion(Block block)
	{
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();

		if (meta >= 3 && meta <= 5)
		{
			return EnumAction.BLOCK;
		}
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return 20;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		int meta = itemStack.getItemDamage();

		if (meta >= 3 && meta <= 5)
		{
			if (!player.isPotionActive(Potion.regeneration.id))
			{
				player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
			}
		}
		return itemStack;
	}

	@Override
	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int tick)
	{
		Vec3 vec = player.getLook(0.5F);
		Random rand = player.getRNG();
		float pos = (rand.nextFloat() - 0.5F) / 8;
		int meta = itemStack.getItemDamage();

		for (int i = 0; i < 4; ++i)
		{
			if (meta == 3)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.ORANGE_DANDELION, player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
			}
			else if (meta == 4)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PINK_DANDELION, player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
			}
			else if (meta == 5)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PURPLE_DANDELION, player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
			}
		}

		if (!player.capabilities.isCreativeMode)
		{
			if (!player.isPotionActive(Potion.regeneration.id))
			{
				if (tick < 10)
				{
					player.stopUsingItem();

					if (meta == 3)
					{
						player.inventory.addItemStackToInventory(new ItemStack(FronosBlocks.fronos_dandelion, 1, 0));
					}
					else if (meta == 4)
					{
						player.inventory.addItemStackToInventory(new ItemStack(FronosBlocks.fronos_dandelion, 1, 1));
					}
					else if (meta == 5)
					{
						player.inventory.addItemStackToInventory(new ItemStack(FronosBlocks.fronos_dandelion, 1, 2));
					}
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
					--itemStack.stackSize;
				}
			}
		}
		else
		{
			if (tick < 10)
			{
				player.stopUsingItem();

				if (meta >= 3 && meta <= 5)
				{
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
				}
			}
		}
	}

	@Override
	public String[] getBlockVariantsName()
	{
		return new String[] { "orange", "pink", "purple", "orange", "pink", "purple" };
	}

	@Override
	public boolean reverseName()
	{
		return true;
	}
}