/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.IPlantableBlock;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityDungeonSpawner;

public class DionaBlock extends Block implements IDetectableResource, ITerraformableBlock, IPlantableBlock
{
	private Icon[] dionaTurfSideIcon;
	private Icon[] dionaBlockIcon;

	public DionaBlock(int par1)
	{
		super(par1, Material.rock);
		this.blockHardness = 2.0F;
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 15)
		{
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 15)
		{
			return AxisAlignedBB.getAABBPool().getAABB(x + 0.0D, y + 0.0D, z + 0.0D, x + 0.0D, y + 0.0D, z + 0.0D);
		}
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 15)
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
		if (world.getBlockMetadata(x, y, z) == 15)
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
		if (world.getBlockMetadata(x, y, z) == 15)
		{
			return false;
		}
		return super.isBlockNormalCube(world, x, y, z);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.dionaTurfSideIcon = new Icon[1];
		this.dionaTurfSideIcon[0] = par1IconRegister.registerIcon("diona:dionaTurfSide");

		this.dionaBlockIcon = new Icon[16];
		this.dionaBlockIcon[0] = par1IconRegister.registerIcon("diona:quontoniumOre");
		this.dionaBlockIcon[1] = par1IconRegister.registerIcon("diona:dionaSiliconOre");
		this.dionaBlockIcon[2] = par1IconRegister.registerIcon("diona:dionaAluminiumOre");
		this.dionaBlockIcon[3] = par1IconRegister.registerIcon("diona:dionaTitaniumOre");
		this.dionaBlockIcon[4] = par1IconRegister.registerIcon("diona:baronsiumOre");
		this.dionaBlockIcon[5] = par1IconRegister.registerIcon("diona:fronisiumOre");
		this.dionaBlockIcon[6] = par1IconRegister.registerIcon("diona:dionaTurf");
		this.dionaBlockIcon[7] = par1IconRegister.registerIcon("diona:dionaDirt");
		this.dionaBlockIcon[8] = par1IconRegister.registerIcon("diona:dionaStone");
		this.dionaBlockIcon[9] = par1IconRegister.registerIcon("diona:quontoniumBlock");
		this.dionaBlockIcon[10] = par1IconRegister.registerIcon("diona:baronsiumBlock");
		this.dionaBlockIcon[11] = par1IconRegister.registerIcon("diona:quontoniumSmooth");
		this.dionaBlockIcon[12] = par1IconRegister.registerIcon("diona:quontoniumBricks");
		this.dionaBlockIcon[13] = par1IconRegister.registerIcon("diona:quontoniumChiseled");
		this.dionaBlockIcon[14] = par1IconRegister.registerIcon("diona:dionaDungeonBricks");
		this.dionaBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.dionaBlockIcon[0];
		case 1:
			return this.dionaBlockIcon[1];
		case 2:
			return this.dionaBlockIcon[2];
		case 3:
			return this.dionaBlockIcon[3];
		case 4:
			return this.dionaBlockIcon[4];
		case 5:
			return this.dionaBlockIcon[5];
		case 6:
			switch (side)
			{
			case 0:
				return this.dionaBlockIcon[7]; //BOTTOM
			case 1:
				return this.dionaBlockIcon[6]; //TOP
			case 2:
				return this.dionaTurfSideIcon[0]; //Z-
			case 3:
				return this.dionaTurfSideIcon[0]; //Z+
			case 4:
				return this.dionaTurfSideIcon[0]; //X-
			case 5:
				return this.dionaTurfSideIcon[0]; //X+
			default:
				return this.dionaBlockIcon[0];
			}
		case 7:
			return this.dionaBlockIcon[7];
		case 8:
			return this.dionaBlockIcon[8];
		case 9:
			return this.dionaBlockIcon[9];
		case 10:
			return this.dionaBlockIcon[10];
		case 11:
			return this.dionaBlockIcon[11];
		case 12:
			return this.dionaBlockIcon[12];
		case 13:
			return this.dionaBlockIcon[13];
		case 14:
			return this.dionaBlockIcon[14];
		case 15:
			return this.dionaBlockIcon[15];
		}
		return this.dionaBlockIcon[0];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 15; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0 || meta == 1 || meta == 2 || meta == 3 || meta == 4 || meta == 5)
		{
			return 3.0F;
		}
		if (meta == 6 || meta == 7)
		{
			return 0.1F;
		}
		if (meta == 8)
		{
			return 2.75F;
		}
		if (meta == 9 || meta == 10)
		{
			return 3.5F;
		}
		if (meta == 11 || meta == 12 || meta == 13)
		{
			return 2.25F;
		}
		if (meta == 14 || meta == 15)
		{
			return -1.0F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 14 || meta == 15)
		{
			return 10000.0F;
		}
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		if (meta == 14 || meta == 15)
		{
			return false;
		}
		else if (meta == 6 || meta == 7)
		{
			return true;
		}
		return super.canHarvestBlock(player, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (metadata == 15)
		{
			return new DionaTileEntityDungeonSpawner();
		}
		return null;
	}

	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		super.onEntityWalking(par1World, par2, par3, par4, par5Entity);

		if (par1World.getBlockMetadata(par2, par3, par4) == 14)
		{
			for (int particleCount = 0; particleCount < 20; particleCount++)
			{
				par1World.spawnParticle("tilecrack_" + String.valueOf(DionaBlocks.dionaBasicBlock.blockID) + "_14", par2 + par1World.rand.nextFloat(), par3 + 1.0F, par4 + par1World.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 1)
		{
			return GCCoreItems.basicItem.itemID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 1)
		{
			return 2;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 14 || meta == 15)
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
		return this.quantityDropped(random);
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
		case 5:
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

		if (meta == 6 || meta == 7)
		{
			return true;
		}
		return false;
	}

	@Override
	public int requiredLiquidBlocksNearby()
	{
		return 4;
	}

	@Override
	public boolean isPlantable(int metadata)
	{
		if (metadata == 6 || metadata == 7)
		{
			return true;
		}
		return false;
	}
}