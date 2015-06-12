/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityDungeonSpawner;

public class NibiruBlock extends Block implements IDetectableResource, ITerraformableBlock
{
	private Icon[] nibiruBlockIcon;

	public NibiruBlock(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			return AxisAlignedBB.getAABBPool().getAABB(x + 0.0D, y + 0.0D, z + 0.0D, x + 0.0D, y + 0.0D, z + 0.0D);
		}
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		}
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.setBlockBoundsBasedOnState(world, x, y, z);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List list, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
	}

	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 12)
		{
			return false;
		}
		return super.isBlockNormalCube(world, x, y, z);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.nibiruBlockIcon = new Icon[13];
		this.nibiruBlockIcon[0] = par1IconRegister.registerIcon("nibiru:ichoriusOre");
		this.nibiruBlockIcon[1] = par1IconRegister.registerIcon("nibiru:noriumOre");
		this.nibiruBlockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiruDiamondOre");
		this.nibiruBlockIcon[3] = par1IconRegister.registerIcon("nibiru:nibiruCoalOre");
		this.nibiruBlockIcon[4] = par1IconRegister.registerIcon("nibiru:nibiruRedGemOre");
		this.nibiruBlockIcon[5] = par1IconRegister.registerIcon("nibiru:nibiruGrass");
		this.nibiruBlockIcon[6] = par1IconRegister.registerIcon("nibiru:nibiruDirt");
		this.nibiruBlockIcon[7] = par1IconRegister.registerIcon("nibiru:nibiruStone");
		this.nibiruBlockIcon[8] = par1IconRegister.registerIcon("nibiru:nibiruCobblestone");
		this.nibiruBlockIcon[9] = par1IconRegister.registerIcon("nibiru:nibiruOilStone");
		this.nibiruBlockIcon[10] = par1IconRegister.registerIcon("nibiru:nibiruOilOre");
		this.nibiruBlockIcon[11] = par1IconRegister.registerIcon("nibiru:nibiruDungeonBricks");
		this.nibiruBlockIcon[12] = par1IconRegister.registerIcon("galacticraftcore:blank");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.nibiruBlockIcon[0];
		case 1:
			return this.nibiruBlockIcon[1];
		case 2:
			return this.nibiruBlockIcon[2];
		case 3:
			return this.nibiruBlockIcon[3];
		case 4:
			return this.nibiruBlockIcon[4];
		case 5:
			return this.nibiruBlockIcon[5];
		case 6:
			return this.nibiruBlockIcon[6];
		case 7:
			return this.nibiruBlockIcon[7];
		case 8:
			return this.nibiruBlockIcon[8];
		case 9:
			switch (side)
			{
			case 0:
				return this.nibiruBlockIcon[6]; //BOTTOM
			case 1:
				return this.nibiruBlockIcon[9]; //TOP
			case 2:
				return this.nibiruBlockIcon[5]; //Z-
			case 3:
				return this.nibiruBlockIcon[5]; //Z+
			case 4:
				return this.nibiruBlockIcon[5]; //X-
			case 5:
				return this.nibiruBlockIcon[5]; //X+
			default:
				return this.nibiruBlockIcon[9];
			}
		case 10:
			return this.nibiruBlockIcon[10];
		case 11:
			return this.nibiruBlockIcon[11];
		case 12:
			return this.nibiruBlockIcon[12];
		case 13:
			return this.nibiruBlockIcon[13];
		}
		return this.nibiruBlockIcon[0];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 9; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
		for (int i = 11; i < 12; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0 || meta == 1 || meta == 2 || meta == 3 || meta == 4)
		{
			return 5.0F;
		}
		if (meta == 5 || meta == 6 || meta == 7 || meta == 8)
		{
			return 4.0F;
		}
		if (meta == 11 || meta == 12)
		{
			return -1.0F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 11 || meta == 12)
		{
			return 10000.0F;
		}
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		if (meta == 11 || meta == 12)
		{
			return false;
		}
		return super.canHarvestBlock(player, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (metadata == 12)
		{
			return new NibiruTileEntityDungeonSpawner();
		}
		return null;
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		if (meta == 0)
		{
			return NibiruItems.powerCrystal.itemID;
		}
		if (meta == 2)
		{
			return Item.diamond.itemID;
		}
		if (meta == 3)
		{
			return Item.coal.itemID;
		}
		if (meta == 4)
		{
			return NibiruItems.nibiruBasicItem.itemID;
		}
		if (meta == 7)
		{
			return NibiruBlocks.nibiruBasicBlock.blockID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 0)
		{
			return 0;
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return 0;
		}
		if (meta == 4)
		{
			return 0;
		}
		if (meta == 7)
		{
			return 8;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 11 || meta == 12)
		{
			return 0;
		}

		if (fortune > 0 && this.blockID != this.idDropped(meta, random, fortune))
		{
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0)
			{
				j = 0;
			}
			return this.quantityDropped(random) * (j + 1);
		}
		else
		{
			return this.quantityDropped(random);
		}
	}

	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		super.onEntityWalking(par1World, par2, par3, par4, par5Entity);

		if (par1World.getBlockMetadata(par2, par3, par4) == 11)
		{
			for (int particleCount = 0; particleCount < 20; particleCount++)
			{
				par1World.spawnParticle("tilecrack_" + String.valueOf(NibiruBlocks.nibiruBasicBlock.blockID) + "_11", par2 + par1World.rand.nextFloat(), par3 + 1.0F, par4 + par1World.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public boolean isValueable(int metadata)
	{
		switch (metadata)
		{
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

		if (this.idDropped(par5, world.rand, par7) != this.blockID)
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
			this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
		}
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 5 || meta == 6)
		{
			return true;
		}
		return false;
	}
}