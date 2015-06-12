/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityDungeonSpawner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBlock extends Block implements IDetectableResource, ITerraformableBlock
{
	private Icon[] polongniusBlockIcon;

	public PolongniusBlock(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 3.0F;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) == 14)
		{
			par5Entity.motionX *= 0.45D;
			par5Entity.motionZ *= 0.45D;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 7)
		{
			float f = 0.25F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
		}
		if (world.getBlockMetadata(x, y, z) == 14)
		{
			float f = 0.1F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
		}
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
		this.polongniusBlockIcon = new Icon[16];
		this.polongniusBlockIcon[0] = par1IconRegister.registerIcon("polongnius:polongniusCopperOre");
		this.polongniusBlockIcon[1] = par1IconRegister.registerIcon("polongnius:polongniusTinOre");
		this.polongniusBlockIcon[2] = par1IconRegister.registerIcon("polongnius:polongniusIronOre");
		this.polongniusBlockIcon[3] = par1IconRegister.registerIcon("polongnius:palladiumOre");
		this.polongniusBlockIcon[4] = par1IconRegister.registerIcon("polongnius:floniumOre");
		this.polongniusBlockIcon[5] = par1IconRegister.registerIcon("polongnius:cheeseOfMilkOre");
		this.polongniusBlockIcon[6] = par1IconRegister.registerIcon("polongnius:polongniusMeteorOre");
		this.polongniusBlockIcon[7] = par1IconRegister.registerIcon("polongnius:polongniusCheeseStone");
		this.polongniusBlockIcon[8] = par1IconRegister.registerIcon("polongnius:polongniusDirt");
		this.polongniusBlockIcon[9] = par1IconRegister.registerIcon("polongnius:polongniusStone");
		this.polongniusBlockIcon[10] = par1IconRegister.registerIcon("polongnius:polongniusCobblestone");
		this.polongniusBlockIcon[11] = par1IconRegister.registerIcon("polongnius:polongniusMeteorBlock");
		this.polongniusBlockIcon[12] = par1IconRegister.registerIcon("polongnius:floniumBlock");
		this.polongniusBlockIcon[13] = par1IconRegister.registerIcon("polongnius:palladiumBlock");
		this.polongniusBlockIcon[14] = par1IconRegister.registerIcon("polongnius:polongniusDungeonBricks");
		this.polongniusBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.polongniusBlockIcon[0];
		case 1:
			return this.polongniusBlockIcon[1];
		case 2:
			return this.polongniusBlockIcon[2];
		case 3:
			return this.polongniusBlockIcon[3];
		case 4:
			return this.polongniusBlockIcon[4];
		case 5:
			return this.polongniusBlockIcon[5];
		case 6:
			return this.polongniusBlockIcon[6];
		case 7:
			return this.polongniusBlockIcon[7];
		case 8:
			return this.polongniusBlockIcon[8];
		case 9:
			return this.polongniusBlockIcon[9];
		case 10:
			return this.polongniusBlockIcon[10];
		case 11:
			return this.polongniusBlockIcon[11];
		case 12:
			return this.polongniusBlockIcon[12];
		case 13:
			return this.polongniusBlockIcon[13];
		case 14:
			return this.polongniusBlockIcon[14];
		case 15:
			return this.polongniusBlockIcon[15];
		}
		return this.polongniusBlockIcon[0];
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
		for (int i = 0; i < 15; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0 || meta == 1 || meta == 2 || meta == 3 || meta == 4 || meta == 5 || meta == 6)
		{
			return 4.0F;
		}
		if (meta == 7 || meta == 8)
		{
			return 0.25F;
		}
		if (meta == 9 || meta == 10)
		{
			return 3.25F;
		}
		if (meta == 14 || meta == 15)
		{
			return -1.0F;
		}
		if (meta == 11 || meta == 12 || meta == 13)
		{
			return 3.0F;
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
		else if (meta == 7 || meta == 8)
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
			return new PolongniusTileEntityDungeonSpawner();
		}
		return null;
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		if (meta == 4)
		{
			return PolongniusItems.polongniusBasicItem.itemID;
		}
		if (meta == 5)
		{
			return PolongniusItems.polongniusFood.itemID;
		}
		if (meta == 6)
		{
			return PolongniusItems.polongniusBasicItem.itemID;
		}
		if (meta == 9)
		{
			return PolongniusBlocks.polongniusBasicBlock.blockID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 4)
		{
			return 0;
		}
		if (meta == 5)
		{
			return 0;
		}
		if (meta == 6)
		{
			return 1;
		}
		if (meta == 9)
		{
			return 10;
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
		else
		{
			return this.quantityDropped(random);
		}
	}

	@Override
	public boolean isValueable(int metadata)
	{
		if (metadata >= 0 && metadata <= 7)
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

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) == 14)
		{
			if (par1World.rand.nextInt(10) == 0)
			{
				MorePlanetCore.proxy.spawnParticle("cheeseSlime", par2 + par1World.rand.nextFloat(), par3 + 1.0F, par4 + par1World.rand.nextFloat());
			}
		}
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 7 || meta == 8)
		{
			return true;
		}
		return false;
	}
}