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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.util.MPDamageSource;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockFlower extends BlockFlower
{
	private static final String[] plants = new String[] {
		"pinkButterCup",
		"orangeFlower",
		"yellowMilk",
		"littleSunFlower",
		"skyMushroom",
		"purpleSpikeFlower",
		"jungleSkyIris",
		"bluePoisonMushroom",
	"whiteMoss"};
	private Icon[] textures;

	public FronosBlockFlower(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setStepSound(Block.soundGrassFootstep);
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	public FronosBlockFlower(int blockID)
	{
		this(blockID, Material.plants);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[plants.length];

		for (int i = 0; i < plants.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:" + plants[i]);
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
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 2 || meta == 5)
		{
			return 4;
		}
		else if (meta == 7)
		{
			return 2;
		}
		return 0;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		case 2:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;
		case 3:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
			break;
		case 4:
		case 7:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.45F, 0.7F);
			break;
		case 8:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.95F, 0.7F);
			break;
		default:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 5)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID && inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID))
				{
					entity.attackEntityFrom(MPDamageSource.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
				}
			}
			else
			{
				entity.attackEntityFrom(MPDamageSource.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
			}
		}
		else if (meta == 7)
		{
			if (entity instanceof EntityLivingBase)
			{
				if (entity instanceof EntityPlayer)
				{
					InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

					if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID && inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID))
					{
						((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
					}
				}
				else
				{
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
				}
			}
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);

		ItemStack equippedItem = player.getCurrentEquippedItem();

		if (equippedItem != null)
		{
			if (equippedItem.itemID != Item.shears.itemID)
			{
				if (meta == 5)
				{
					player.attackEntityFrom(MPDamageSource.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
				}
				else if (meta == 7)
				{
					player.addPotionEffect(new PotionEffect(Potion.poison.id, 100));
				}
			}
		}
		else
		{
			if (meta == 5)
			{
				player.attackEntityFrom(MPDamageSource.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
			}
			else if (meta == 7)
			{
				player.addPotionEffect(new PotionEffect(Potion.poison.id, 100));
			}
		}
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 5)
		{
			if (par5Random.nextInt(1) == 0)
			{
				MorePlanetCore.proxy.spawnParticle("purpleSpike", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < plants.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 4)
		{
			return id == FronosBlocks.fronosStone.blockID;
		}
		if (metadata == 7)
		{
			return id == FronosBlocks.fronosStone.blockID || id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
		}
		if (metadata == 8)
		{
			return id == FronosBlocks.fronosSand.blockID || id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
		}
		return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();
		int blockmeta = world.getBlockMetadata(x, y - 1, z);

		if (itemStack.itemID == this.blockID)
		{
			switch (meta)
			{
			case 4:
				return id == FronosBlocks.fronosStone.blockID;
			case 7:
				return id == FronosBlocks.fronosStone.blockID || id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
			case 8:
				return id == FronosBlocks.fronosSand.blockID && blockmeta == 1 || id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
			default:
				return id == FronosBlocks.fronosGrass.blockID || id == FronosBlocks.fronosDirt.blockID || id == FronosBlocks.fronosFarmland.blockID;
			}
		}
		return this.canPlaceBlockOnSide(world, x, y, z, side);
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
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (world.getBlockId(x, y, z) != this.blockID)
		{
			if (meta == 4)
			{
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
			}
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			if (meta == 4)
			{
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
			}
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		switch (meta)
		{
		case 3:
			if (world.rand.nextInt(5) == 0)
			{
				ret.add(new ItemStack(FronosItems.fronosFood, 1, 10));
			}
			break;
		}
		return ret;
	}
}