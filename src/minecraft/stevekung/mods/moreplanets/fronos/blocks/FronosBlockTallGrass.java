/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockTallGrass extends BlockFlower
{
	private static final String[] fronosGrass = new String[] {
		"fronosShortGrass",//0
		"fronosMediumGrass",//1
		"fronosTallGrass",//2
		"fronosPinkShortGrass",//3
		"fronosPinkMediumGrass",//4
		"fronosPinkTallGrass",//5
		"fronosPurpleShortGrass",//6
		"fronosPurpleMediumGrass",//7
		"fronosPurpleTallGrass",//8
		"fronosPlainsShortGrass",//9
		"fronosPlainsMediumGrass",//10
		"fronosPlainsTallGrass",//11
		"fronosGoldenTallGrass"//12
	};
	private Icon[] textures;

	public FronosBlockTallGrass(int blockID)
	{
		super(blockID);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundGrassFootstep);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[fronosGrass.length];

		for (int i = 0; i < fronosGrass.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:" + fronosGrass[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.textures.length)
		{
			meta = 0;
		}
		return this.textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			return 10;
		}
		return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		case 0:
		case 3:
		case 6:
		case 9:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.5F, 0.9F);
			break;
		default:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		switch (meta)
		{
		case 12:
			if (world.rand.nextInt(15) == 0)
			{
				ret.add(new ItemStack(FronosItems.goldenSeed, 1));
			}
			break;
		default :
			ItemStack item = ForgeHooks.getGrassSeed(world);

			if (world.rand.nextInt(8) != 0)
			{
				return ret;
			}

			if (item != null)
			{
				ret.add(item);
			}
			break;
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < fronosGrass.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int block = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == this.blockID)
		{
			if (meta >= 0 && meta <= 2)
			{
				return block == FronosBlocks.fronosGrass.blockID;
			}
			else if (meta >= 3 && meta <= 5)
			{
				return block == FronosBlocks.fronosPinkGrass.blockID;
			}
			else if (meta >= 5 && meta <= 8)
			{
				return block == FronosBlocks.fronosPurpleGrass.blockID;
			}
			else if (meta >= 8 && meta <= 11)
			{
				return block == FronosBlocks.fronosPlainsGrass.blockID;
			}
			else if (meta == 12)
			{
				return block == FronosBlocks.goldenGrass.blockID;
			}
			else
			{
				return block == FronosBlocks.fronosDirt.blockID;
			}
		}
		else
		{
			return this.canPlaceBlockOnSide(world, x, y, z, side);
		}
	}

	/*@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID)))
				{
					((EntityLivingBase)entity).attackEntityFrom(MPDamageSource.tiberium, (int) (4.0D * 0.1 + 1.0D));
				}
			}
			else
			{
				((EntityLivingBase)entity).attackEntityFrom(MPDamageSource.tiberium, (int) (4.0D * 0.1 + 1.0D));
			}
		}
	}*/

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 12)
		{
			if (par5Random.nextInt(1) == 0)
			{
				MorePlanetCore.proxy.spawnParticle("goldSmoke", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		this.checkFlowerChange(world, x, y, z);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			if (par5Random.nextInt(1500) == 0)
			{
				par1World.setBlock(par2, par3, par4, FronosBlocks.fronosTallGrass.blockID, 2, 3);
				return;
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}
}