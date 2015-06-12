/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
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
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityDungeonSpawner;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;

public class KoentusBlock extends Block implements IDetectableResource, ITerraformableBlock
{
	private Icon[] koentusBlockIcon;

	public KoentusBlock(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 3.0F;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 13)
		{
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 13)
		{
			return AxisAlignedBB.getAABBPool().getAABB(x + 0.0D, y + 0.0D, z + 0.0D, x + 0.0D, y + 0.0D, z + 0.0D);
		}
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 13)
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
		if (world.getBlockMetadata(x, y, z) == 13)
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
		if (world.getBlockMetadata(x, y, z) == 13)
		{
			return false;
		}
		return super.isBlockNormalCube(world, x, y, z);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.koentusBlockIcon = new Icon[14];
		this.koentusBlockIcon[0] = par1IconRegister.registerIcon("koentus:koentusTinOre");
		this.koentusBlockIcon[1] = par1IconRegister.registerIcon("koentus:meteoricIronOre");
		this.koentusBlockIcon[2] = par1IconRegister.registerIcon("koentus:whiteCrystalOre");
		this.koentusBlockIcon[3] = par1IconRegister.registerIcon("koentus:blueMineralOre");
		this.koentusBlockIcon[4] = par1IconRegister.registerIcon("koentus:becterialFossilized");
		this.koentusBlockIcon[5] = par1IconRegister.registerIcon("koentus:koentusBlueGem");
		this.koentusBlockIcon[6] = par1IconRegister.registerIcon("koentus:koentusSurfaceStone");
		this.koentusBlockIcon[7] = par1IconRegister.registerIcon("koentus:koentusSubStone");
		this.koentusBlockIcon[8] = par1IconRegister.registerIcon("koentus:koentusStone");
		this.koentusBlockIcon[9] = par1IconRegister.registerIcon("koentus:koentusCobblestone");
		this.koentusBlockIcon[10] = par1IconRegister.registerIcon("koentus:whiteCrystalBlock");
		this.koentusBlockIcon[11] = par1IconRegister.registerIcon("koentus:blueMineralBlock");
		this.koentusBlockIcon[12] = par1IconRegister.registerIcon("koentus:koentusDungeonBricks");
		this.koentusBlockIcon[13] = par1IconRegister.registerIcon("galacticraftcore:blank");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.koentusBlockIcon[0];
		case 1:
			return this.koentusBlockIcon[1];
		case 2:
			return this.koentusBlockIcon[2];
		case 3:
			return this.koentusBlockIcon[3];
		case 4:
			return this.koentusBlockIcon[4];
		case 5:
			return this.koentusBlockIcon[5];
		case 6:
			return this.koentusBlockIcon[6];
		case 7:
			return this.koentusBlockIcon[7];
		case 8:
			return this.koentusBlockIcon[8];
		case 9:
			return this.koentusBlockIcon[9];
		case 10:
			return this.koentusBlockIcon[10];
		case 11:
			return this.koentusBlockIcon[11];
		case 12:
			return this.koentusBlockIcon[12];
		case 13:
			return this.koentusBlockIcon[13];
		case 14:
			return this.koentusBlockIcon[14];
		}
		return this.koentusBlockIcon[0];
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
		for (int i = 0; i < 13; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0)
		{
			return 1.5F;
		}
		if (meta == 1)
		{
			return 1.8F;
		}
		if (meta == 2 || meta == 3 || meta == 5)
		{
			return 3.0F;
		}
		if (meta == 4)
		{
			return 3.25F;
		}
		if (meta == 6 || meta == 7 || meta == 9)
		{
			return 2.5F;
		}
		if (meta == 8)
		{
			return 2.8F;
		}
		if (meta == 10 || meta == 11)
		{
			return 3.25F;
		}
		if (meta == 12 || meta == 13)
		{
			return -1.0F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 12 || meta == 13)
		{
			return 10000.0F;
		}
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		if (meta == 12 || meta == 13)
		{
			return false;
		}
		return super.canHarvestBlock(player, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (metadata == 13)
		{
			return new DionaTileEntityDungeonSpawner();
		}
		return null;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 1)
		{
			return GCCoreItems.meteoricIronRaw.itemID;
		}
		if (meta == 2)
		{
			return KoentusItems.koentusBasicItem.itemID;
		}
		if (meta == 3)
		{
			return KoentusItems.koentusBasicItem.itemID;
		}
		if (meta == 4)
		{
			return KoentusItems.koentusBasicItem.itemID;
		}
		if (meta == 5)
		{
			return KoentusItems.koentusBasicItem.itemID;
		}
		if (meta == 8)
		{
			return KoentusBlocks.koentusBasicBlock.blockID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 1)
		{
			return 0;
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return 1;
		}
		if (meta == 4)
		{
			return 3;
		}
		if (meta == 5)
		{
			return 2;
		}
		if (meta == 8)
		{
			return 9;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 12 || meta == 13)
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
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(10) == 0)
		{
			int metadata = world.getBlockMetadata(x, y, z);

			if (metadata == 12)
			{
				MorePlanetCore.proxy.spawnParticle("koentusSludge", x + rand.nextDouble(), y, z + rand.nextDouble());

				if (rand.nextInt(100) == 0)
				{
					world.playSound(x, y, z, "galacticraftcore:ambience.singledrip", 1, 0.8F + rand.nextFloat() / 5.0F, false);
				}
			}
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
}