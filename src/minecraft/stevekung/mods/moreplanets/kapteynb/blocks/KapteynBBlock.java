/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.IPlantableBlock;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
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
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityDungeonSpawner;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KapteynBBlock extends Block implements IDetectableResource, IPlantableBlock, ITerraformableBlock
{
	private Icon[] kapteynBlockIcons;

	public KapteynBBlock(int block)
	{
		super(block, Material.rock);
		this.blockHardness = 4.0F;
		this.setStepSound(soundStoneFootstep);
		this.setTickRandomly(true);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 5 || meta == 15 || meta == 16)
		{
			return false;
		}
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 16)
		{
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 16)
		{
			return AxisAlignedBB.getAABBPool().getAABB(x + 0.0D, y + 0.0D, z + 0.0D, x + 0.0D, y + 0.0D, z + 0.0D);
		}
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 16)
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
		if (world.getBlockMetadata(x, y, z) == 16)
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
		if (world.getBlockMetadata(x, y, z) == 16)
		{
			return false;
		}
		return super.isBlockNormalCube(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.kapteynBlockIcons = new Icon[17];
		this.kapteynBlockIcons[0] = par1IconRegister.registerIcon("kapteynb:kapteynSurfaceStone");
		this.kapteynBlockIcons[1] = par1IconRegister.registerIcon("kapteynb:kapteynHardenedIceStone");
		this.kapteynBlockIcons[2] = par1IconRegister.registerIcon("kapteynb:kapteynBStone");
		this.kapteynBlockIcons[3] = par1IconRegister.registerIcon("kapteynb:kapteynBCobblestone");
		this.kapteynBlockIcons[4] = par1IconRegister.registerIcon("kapteynb:rockySolidWater");
		this.kapteynBlockIcons[5] = par1IconRegister.registerIcon("kapteynb:kapteynBBricks");
		this.kapteynBlockIcons[6] = par1IconRegister.registerIcon("kapteynb:kapteynBIridiumOre");
		this.kapteynBlockIcons[7] = par1IconRegister.registerIcon("kapteynb:kapteynBElementiumOre");
		this.kapteynBlockIcons[8] = par1IconRegister.registerIcon("kapteynb:kapteynBFrozenIronOre");
		this.kapteynBlockIcons[9] = par1IconRegister.registerIcon("kapteynb:kapteynBUraniumOre");
		this.kapteynBlockIcons[10] = par1IconRegister.registerIcon("kapteynb:kapteynBTinOre");
		this.kapteynBlockIcons[11] = par1IconRegister.registerIcon("kapteynb:kapteynBCopperOre");
		this.kapteynBlockIcons[12] = par1IconRegister.registerIcon("kapteynb:kapteynBDiamondOre");
		this.kapteynBlockIcons[13] = par1IconRegister.registerIcon("kapteynb:kapteynBGoldOre");
		this.kapteynBlockIcons[14] = par1IconRegister.registerIcon("kapteynb:kapteynBRedstoneOre");
		this.kapteynBlockIcons[15] = par1IconRegister.registerIcon("kapteynb:kapteynBRedstoneOre");
		this.kapteynBlockIcons[16] = par1IconRegister.registerIcon("galacticraftcore:blank");
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 15)
		{
			return 9;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);

		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 14)
		{
			par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteynBBasicBlock.blockID, 15, 3);
		}
	}

	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		super.onEntityWalking(par1World, par2, par3, par4, par5Entity);

		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 14)
		{
			par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteynBBasicBlock.blockID, 15, 3);
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 14)
		{
			par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteynBBasicBlock.blockID, 15, 3);
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 15)
		{
			par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteynBBasicBlock.blockID, 14, 3);
		}
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 15)
		{
			this.sparkle(par1World, par2, par3, par4);
		}
	}

	private void sparkle(World par1World, int par2, int par3, int par4)
	{
		Random random = par1World.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l)
		{
			double d1 = par2 + random.nextFloat();
			double d2 = par3 + random.nextFloat();
			double d3 = par4 + random.nextFloat();

			if (l == 0 && !par1World.isBlockOpaqueCube(par2, par3 + 1, par4))
			{
				d2 = par3 + 1 + d0;
			}

			if (l == 1 && !par1World.isBlockOpaqueCube(par2, par3 - 1, par4))
			{
				d2 = par3 + 0 - d0;
			}

			if (l == 2 && !par1World.isBlockOpaqueCube(par2, par3, par4 + 1))
			{
				d3 = par4 + 1 + d0;
			}

			if (l == 3 && !par1World.isBlockOpaqueCube(par2, par3, par4 - 1))
			{
				d3 = par4 + 0 - d0;
			}

			if (l == 4 && !par1World.isBlockOpaqueCube(par2 + 1, par3, par4))
			{
				d1 = par2 + 1 + d0;
			}

			if (l == 5 && !par1World.isBlockOpaqueCube(par2 - 1, par3, par4))
			{
				d1 = par2 + 0 - d0;
			}

			if (d1 < par2 || d1 > par2 + 1 || d2 < 0.0D || d2 > par3 + 1 || d3 < par4 || d3 > par4 + 1)
			{
				par1World.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public int tickRate(World par1World)
	{
		return 30;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKapteynB.kapteynB;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 5 || meta == 16)
		{
			return 10000.0F;
		}
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0 || meta == 1)
		{
			return 4.0F;
		}
		if (meta == 2 || meta == 3)
		{
			return 3.5F;
		}
		if (meta == 4)
		{
			return 0.6F;
		}
		if (meta == 5 || meta == 16)
		{
			return -1.0F;
		}
		if (meta >= 6 && meta <= 16)
		{
			return 3.75F;
		}
		return this.blockHardness;
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		if (meta == 4)
		{
			return true;
		}
		if (meta == 5)
		{
			return false;
		}
		return super.canHarvestBlock(player, meta);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.kapteynBlockIcons[0];
		case 1:
			return this.kapteynBlockIcons[1];
		case 2:
			return this.kapteynBlockIcons[2];
		case 3:
			return this.kapteynBlockIcons[3];
		case 4:
			return this.kapteynBlockIcons[4];
		case 5:
			return this.kapteynBlockIcons[5];
		case 6:
			return this.kapteynBlockIcons[6];
		case 7:
			return this.kapteynBlockIcons[7];
		case 8:
			return this.kapteynBlockIcons[8];
		case 9:
			return this.kapteynBlockIcons[9];
		case 10:
			return this.kapteynBlockIcons[10];
		case 11:
			return this.kapteynBlockIcons[11];
		case 12:
			return this.kapteynBlockIcons[12];
		case 13:
			return this.kapteynBlockIcons[13];
		case 14:
			return this.kapteynBlockIcons[14];
		case 15:
			return this.kapteynBlockIcons[15];
		case 16:
			return this.kapteynBlockIcons[16];
		default:
			return this.kapteynBlockIcons[0];
		}
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		switch (meta)
		{
		case 2:
			return KapteynBBlocks.kapteynBBasicBlock.blockID;
		case 9:
			return KapteynBItems.kapteynBBasicItem.itemID;
		case 12:
			return Item.diamond.itemID;
		case 14:
		case 15:
			return Item.redstone.itemID;
		default:
			return this.blockID;
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 2)
		{
			return 3;
		}
		if (meta == 9)
		{
			return 1;
		}
		if (meta == 12 || meta == 14 || meta == 15)
		{
			return 0;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random par1Random)
	{
		if (meta == 14 || meta == 15)
		{
			return 4 + par1Random.nextInt(2);
		}

		if (fortune > 0 && this.blockID != this.idDropped(meta, par1Random, fortune))
		{
			int j = par1Random.nextInt(fortune + 2) - 1;

			if (j < 0)
			{
				j = 0;
			}
			return this.quantityDropped(par1Random) * (j + 1);
		}
		return this.quantityDropped(par1Random);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 16; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (metadata == 16)
		{
			return new DionaTileEntityDungeonSpawner();
		}
		return null;
	}

	@Override
	public boolean isValueable(int metadata)
	{
		if (metadata >= 6 && metadata <= 16)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0 || meta == 1)
		{
			return true;
		}

		plant.getPlantID(world, x, y + 1, z);

		if (plant instanceof BlockFlower)
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
	public boolean isPlantable(int meta)
	{
		if (meta == 0 || meta == 1)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0 || meta == 1 || meta == 4)
		{
			return true;
		}
		return false;
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

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.blockID;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		return meta;
	}
}