/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class BlockSlabMP extends BlockHalfSlab
{
	public static enum SlabCategory
	{
		WOOD1, WOOD2, STONE;
	}
	private static String[] woodTypes = new String[] {"tin", "tin2", "moonStone", "marsCobblestone", "polongniusCobblestone", "nibiruCobblestone", "dionaStone", "smoothQuontonium", "brickQuontonium", "koentusCobblestone", "fronosCobblestone", "kapteynBCobblestone"};
	private static String[] rockTypes = new String[] {"whiteWood", "orangeWood", "coconutWood"};
	private Icon[] textures;

	protected boolean isDoubleSlab;
	private SlabCategory category;

	public BlockSlabMP(int par1, boolean par2, Material material, SlabCategory cat)
	{
		super(par1, par2, material);
		this.isDoubleSlab = par2;
		this.category = cat;

		if (material == Material.wood)
		{
			Block.setBurnProperties(this.blockID, 5, 20);
			this.setHardness(2.0F);
			this.setResistance(5.0F);
			this.setStepSound(soundWoodFootstep);
		}
		else if (material == Material.rock)
		{
			this.setStepSound(soundStoneFootstep);
			this.setHardness(3.0F);
			this.setResistance(5.0F);
		}
		Block.useNeighborBrightness[this.blockID] = true;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		if (this.category == SlabCategory.STONE) /**Wood Type**/
		{
			this.textures = new Icon[3];
			this.textures[0] = par1IconRegister.registerIcon("nibiru:whiteWoodPlanks");
			this.textures[1] = par1IconRegister.registerIcon("nibiru:orangeWoodPlanks");
			this.textures[2] = par1IconRegister.registerIcon("fronos:coconutWoodPlanks");
		}
		else if (this.category == SlabCategory.WOOD2) /**Stone Type 2**/
		{
			this.textures = new Icon[4];
			this.textures[0] = par1IconRegister.registerIcon("diona:quontoniumBricks");
			this.textures[1] = par1IconRegister.registerIcon("koentus:koentusCobblestone");
			this.textures[2] = par1IconRegister.registerIcon("fronos:fronosCobblestone");
			this.textures[3] = par1IconRegister.registerIcon("kapteynb:kapteynBCobblestone");
		}
		else /**Stone Type**/
		{
			this.textures = new Icon[8];
			this.textures[0] = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_4");
			this.textures[1] = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_2");
			this.textures[2] = par1IconRegister.registerIcon("galacticraftmoon:bottom");
			this.textures[3] = par1IconRegister.registerIcon("galacticraftmars:cobblestone");
			this.textures[4] = par1IconRegister.registerIcon("polongnius:polongniusCobblestone");
			this.textures[5] = par1IconRegister.registerIcon("nibiru:nibiruCobblestone");
			this.textures[6] = par1IconRegister.registerIcon("diona:dionaStone");
			this.textures[7] = par1IconRegister.registerIcon("diona:quontoniumSmooth");
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (this.category == SlabCategory.STONE)
		{
			return this.textures[getTypeFromMeta(meta)];
		}
		else if (this.category == SlabCategory.WOOD1)
		{
			return this.textures[getTypeFromMeta(meta)];
		}
		else if (this.category == SlabCategory.WOOD2)
		{
			return this.textures[getTypeFromMeta(meta)];
		}
		return this.blockIcon;
	}

	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		int max = 0;

		if (this.category == SlabCategory.WOOD1)
		{
			max = 8;
		}
		else if (this.category == SlabCategory.WOOD2)
		{
			max = 4;
		}
		else if (this.category == SlabCategory.STONE)
		{
			max = 3;
		}
		for (int i = 0; i < max; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public String getFullSlabName(int meta)
	{
		if (this.category == SlabCategory.STONE)
		{
			return new StringBuilder().append(rockTypes[getTypeFromMeta(meta)]).append("Slab").toString();
		}
		return new StringBuilder().append(woodTypes[this.getWoodType(meta)]).append("Slab").toString();
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 7;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (this.isDoubleSlab)
		{
			if (this.blockID == MPBlocks.blockStoneSlabFull.blockID)
			{
				return MPBlocks.blockStoneSlabHalf.blockID;
			}
			if (this.blockID == MPBlocks.blockWoodSlabFull.blockID)
			{
				return MPBlocks.blockWoodSlabHalf.blockID;
			}
			else
			{
				return MPBlocks.blockStoneSlabHalf2.blockID;
			}
		}
		return this.blockID;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		if (!this.isDoubleSlab)
		{
			return MorePlanetCore.morePlanetTab;
		}
		return null;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		float hardness = this.blockHardness;

		if (this.category == SlabCategory.STONE)
		{
			hardness = 2.0F;
		}
		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		world.getBlockMetadata(x, y, z);
		float resistance = this.blockHardness;

		if (this.category == SlabCategory.STONE)
		{
			resistance = 1.0F;
		}
		return resistance / 5.0F;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return !this.isDoubleSlab ? this.blockID : this.blockID == MPBlocks.blockWoodSlabFull.blockID ? MPBlocks.blockWoodSlabHalf.blockID : this.blockID == MPBlocks.blockStoneSlabFull.blockID ? MPBlocks.blockStoneSlabHalf.blockID : MPBlocks.blockStoneSlabHalf2.blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(this.blockID, 2, par1);
	}

	private int getWoodType(int meta)
	{
		meta = getTypeFromMeta(meta) + this.category.ordinal() * 8;

		if (meta < woodTypes.length)
		{
			return meta;
		}
		return 0;
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 7;
	}
}