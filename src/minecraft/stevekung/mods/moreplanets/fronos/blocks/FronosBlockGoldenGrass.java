/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockGoldenGrass extends Block implements ITerraformableBlock
{
	private Icon[] fronosGrassIcon;

	public FronosBlockGoldenGrass(int id)
	{
		super(id, Material.grass);
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
		this.blockHardness = 0.6F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.fronosGrassIcon = new Icon[8];
		this.fronosGrassIcon[0] = par1IconRegister.registerIcon("fronos:fronosDirt");
		this.fronosGrassIcon[1] = par1IconRegister.registerIcon("fronos:fronosGoldGrassTop");
		this.fronosGrassIcon[2] = par1IconRegister.registerIcon("fronos:fronosGoldGrassSide");
		this.fronosGrassIcon[3] = par1IconRegister.registerIcon("fronos:fronosGoldGrassSide");
		this.fronosGrassIcon[4] = par1IconRegister.registerIcon("fronos:fronosGoldGrassSide");
		this.fronosGrassIcon[5] = par1IconRegister.registerIcon("fronos:fronosGoldGrassSide");
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (random.nextInt(1) == 0)
		{
			MorePlanetCore.proxy.spawnParticle("goldDust", x + random.nextFloat(), y + 1.3F, z + random.nextFloat());
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return 10;
	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		if (par1 < 0 || par1 >= this.fronosGrassIcon.length)
		{
			par1 = 1;
		}
		return this.fronosGrassIcon[par1];
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}



	@Override
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 == 1)
		{
			return this.fronosGrassIcon[1];
		}
		else if (par5 == 0)
		{
			return FronosBlocks.fronosDirt.getBlockTextureFromSide(par5);
		}
		else if (par5 == 2 || par5 == 3 || par5 == 4 || par5 == 5)
		{
			return this.fronosGrassIcon[2];
		}
		return this.blockIcon;
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null)
		{
			if (par5EntityPlayer.getCurrentEquippedItem().getDisplayName().toLowerCase().contains("hoe"))
			{
				Block block = FronosBlocks.fronosFarmland;

				world.playSoundEffect(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

				if (!world.isRemote)
				{
					world.setBlock(par2, par3, par4, block.blockID);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
			{
				par1World.setBlock(par2, par3, par4, FronosBlocks.fronosDirt.blockID);
			}

			if (par5Random.nextInt(1500) == 0)
			{
				par1World.setBlock(par2, par3, par4, FronosBlocks.fronosGrass.blockID, 0, 3);
				return;
			}
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosBlocks.fronosDirt.idDropped(0, par2Random, par3);
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		return true;
	}
}