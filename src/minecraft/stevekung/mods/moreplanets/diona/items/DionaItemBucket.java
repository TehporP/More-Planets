/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemBucket extends Item
{
	private int isFull;

	private static final String[] bucketTypes = new String[] {"baronsiumBucket",
		"cheeseOfMilkBucket",
		"koentusSludgeBucket",
	};

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public DionaItemBucket(int i)
	{
		super(i);
		this.setHasSubtypes(true);
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		float f = 1.0F;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		this.isFull = getLiquidIDFromMeta(itemstack.getItemDamage());
		boolean flag = this.isFull == 0;

		if (itemstack.getItemDamage() == 0)
		{
			MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);

			if (pos == null)
			{
				return itemstack;
			}
			else
			{
				int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
				int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

				if (blockID == PolongniusBlocks.cheeseOfMilkFluidBlock.blockID && meta == 0)
				{
					if (player.capabilities.isCreativeMode)
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						return itemstack;
					}

					if (--itemstack.stackSize <= 0)
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						return new ItemStack(DionaItems.baronsiumBucket, 1, 1);
					}

					if (!player.inventory.addItemStackToInventory(new ItemStack(DionaItems.baronsiumBucket, 1, 1)))
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						player.dropPlayerItem(new ItemStack(DionaItems.baronsiumBucket, 1, 1));
					}
					return itemstack;
				}
				else if (blockID == KoentusBlocks.koentusSludgeBlock.blockID && meta == 0)
				{
					if (player.capabilities.isCreativeMode)
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						return itemstack;
					}

					if (--itemstack.stackSize <= 0)
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						return new ItemStack(DionaItems.baronsiumBucket, 1, 2);
					}

					if (!player.inventory.addItemStackToInventory(new ItemStack(DionaItems.baronsiumBucket, 1, 2)))
					{
						world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

						player.dropPlayerItem(new ItemStack(DionaItems.baronsiumBucket, 1, 2));
					}
					return itemstack;
				}
				else
				{
					return itemstack;
				}
			}
		}
		else
		{
			MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, flag);

			if (pos == null)
			{
				return itemstack;
			}
			else if (pos.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = pos.blockX;
				int j = pos.blockY;
				int k = pos.blockZ;

				if (!world.canMineBlock(player, i, j, k))
				{
					return itemstack;
				}
				if (this.isFull < 0)
				{
					return new ItemStack(DionaItems.baronsiumBucket, 1, 0);
				}
				if (pos.sideHit == 0)
				{
					--j;
				}
				if (pos.sideHit == 1)
				{
					++j;
				}
				if (pos.sideHit == 2)
				{
					--k;
				}
				if (pos.sideHit == 3)
				{
					++k;
				}
				if (pos.sideHit == 4)
				{
					--i;
				}
				if (pos.sideHit == 5)
				{
					++i;
				}
				if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemstack))
				{
					return itemstack;
				}
				if (this.tryPlaceContainedLiquid(world, d0, d1, d2, i, j, k) && !player.capabilities.isCreativeMode)
				{
					return new ItemStack(DionaItems.baronsiumBucket, 1, 0);
				}
			}
		}
		return itemstack;
	}

	public boolean tryPlaceContainedLiquid(World par1World, double par2, double par4, double par6, int par8, int par9, int par10)
	{
		if (this.isFull <= 0)
		{
			return false;
		}
		else if (!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid())
		{
			return false;
		}
		else
		{
			if (this.isFull == PolongniusBlocks.cheeseOfMilkFluidBlock.blockID)
			{
				par1World.setBlock(par8, par9, par10, this.isFull, 0, 3);
			}
			else if (this.isFull == KoentusBlocks.koentusSludgeBlock.blockID)
			{
				par1World.setBlock(par8, par9, par10, this.isFull, 0, 3);
			}
			else
			{
				par1World.setBlock(par8, par9, par10, this.isFull, 0, 3);
			}

			return true;
		}
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack itemstack)
	{
		return itemstack.getItemDamage() == 2 ? new ItemStack(DionaItems.baronsiumBucket, 1, 0) : new ItemStack(DionaItems.baronsiumBucket, 1, 0);
	}

	private static int getLiquidIDFromMeta(int meta)
	{
		switch (meta)
		{
		case 1:
			return PolongniusBlocks.cheeseOfMilkFluidBlock.blockID;
		case 2:
			return KoentusBlocks.koentusSludgeBlock.blockID;
		default:
			return PolongniusBlocks.cheeseOfMilkFluidBlock.blockID;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < DionaItemBucket.bucketTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= DionaItemBucket.bucketTypes.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + DionaItemBucket.bucketTypes[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[3];
		this.textures[0] = iconRegister.registerIcon("diona:baronsiumBucket");
		this.textures[1] = iconRegister.registerIcon("polongnius:cheeseOfMilkBucket");
		this.textures[2] = iconRegister.registerIcon("koentus:koentusSludgeBucket");
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return this.textures[meta];
	}
}