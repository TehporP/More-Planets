/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;

public class ItemCreamGolem extends ItemBaseMP
{
	public ItemCreamGolem(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xoffset, float yoffset, float zoffset)
	{
		int meta = itemStack.getItemDamage();
		IBlockState state = world.getBlockState(pos);

		if (world.isRemote)
		{
			return true;
		}
		if (player.capabilities.isCreativeMode)
		{
			if (state.getBlock() == Blocks.mob_spawner)
			{
				TileEntity localTileEntity = world.getTileEntity(pos);

				if (localTileEntity instanceof TileEntityMobSpawner)
				{
					MobSpawnerBaseLogic mobSpawner = ((TileEntityMobSpawner)localTileEntity).getSpawnerBaseLogic();
					mobSpawner.setEntityName("CreamGolem");
					localTileEntity.markDirty();
					world.markBlockForUpdate(pos);

					if (!player.capabilities.isCreativeMode)
					{
						itemStack.stackSize -= 1;
					}
					return true;
				}
			}
		}
		if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
		{
			return false;
		}
		else
		{
			pos = pos.offset(side);
			double d = 0.0D;
			EntityCreamGolem cream = new EntityCreamGolem(world);

			if (side == EnumFacing.UP && state instanceof BlockFence)
			{
				d = 0.5D;
			}

			if (this.spawnCreamGolem(world, meta, pos.getX() + 0.5D, pos.getY() + d, pos.getZ() + 0.5D) && !player.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}
			if (itemStack.hasDisplayName())
			{
				((EntityLiving)cream).setCustomNameTag(itemStack.getDisplayName());
			}
			return true;
		}
	}

	boolean spawnCreamGolem(World world, int type, double x, double y, double z)
	{
		EntityCreamGolem cream = new EntityCreamGolem(world);
		cream.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
		cream.setCreamGolemType(type);
		world.spawnEntityInWorld(cream);
		cream.playLivingSound();
		return true;
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "vanilla_cream_golem", "chocolate_cream_golem", "strawberry_cream_golem", "orange_cream_golem", "tea_cream_golem", "lemon_cream_golem" };
	}
}