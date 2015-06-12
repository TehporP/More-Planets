/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityBearry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBearryEgg extends Item
{
	public double posX;
	public double posY;
	public double posZ;
	public float rotationYaw;

	public FronosItemBearryEgg(int id)
	{
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("fronos:bearryEgg");
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleFronos.fronosTab;
	}

	public static boolean spawnBearry(World world, double x, double y, double z)
	{
		FronosEntityBearry bearry = new FronosEntityBearry(world);
		bearry.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
		bearry.onSpawnWithEgg((EntityLivingData)null);
		bearry.setGrowingAge(-10000);
		world.spawnEntityInWorld(bearry);
		bearry.playLivingSound();
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float xoffset, float yoffset, float zoffset)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			final int targetBlock = world.getBlockId(x, y, z);
			x += Facing.offsetsXForSide[side];
			y += Facing.offsetsYForSide[side];
			z += Facing.offsetsZForSide[side];
			double yOffsetForFence = 0.0D;

			if (side == 1 && targetBlock == Block.fence.blockID || targetBlock == Block.netherFence.blockID || targetBlock == DionaBlocks.quontoniumBricksFence.blockID || targetBlock == DionaBlocks.titaniumFence.blockID)
			{
				yOffsetForFence = 0.5D;
			}

			if (spawnBearry(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !entityPlayer.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}

			FronosEntityBearry bearry = new FronosEntityBearry(world);

			if (itemStack.hasDisplayName())
			{
				((EntityLiving)bearry).setCustomNameTag(itemStack.getDisplayName());
			}
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}
}