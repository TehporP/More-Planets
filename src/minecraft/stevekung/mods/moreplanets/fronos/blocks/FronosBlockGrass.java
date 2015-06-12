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
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosBlockGrass extends Block implements ITerraformableBlock
{
	private Icon[] fronosGrassIcon;

	public FronosBlockGrass(int id)
	{
		super(id, Material.grass);
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
		this.blockHardness = 0.6F;
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		par1World.getBlockMetadata(par2, par3, par4);

		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == FronosItems.pearl.itemID)
		{
			par1World.setBlock(par2, par3, par4, FronosBlocks.goldenGrass.blockID, 1, 3);
			par5EntityPlayer.inventory.consumeInventoryItem(FronosItems.pearl.itemID);
		}
		super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.fronosGrassIcon = new Icon[7];
		this.fronosGrassIcon[0] = par1IconRegister.registerIcon("fronos:fronosDirt");
		this.fronosGrassIcon[1] = par1IconRegister.registerIcon("fronos:fronosGrassTop");
		this.fronosGrassIcon[2] = par1IconRegister.registerIcon("fronos:fronosGrassSide");
		this.fronosGrassIcon[3] = par1IconRegister.registerIcon("fronos:fronosGrassSide");
		this.fronosGrassIcon[4] = par1IconRegister.registerIcon("fronos:fronosGrassSide");
		this.fronosGrassIcon[5] = par1IconRegister.registerIcon("fronos:fronosGrassSide");
		this.fronosGrassIcon[6] = par1IconRegister.registerIcon("fronos:fronosGrassSideCream");
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
		int block = par1IBlockAccess.getBlockId(par2, par3 + 1, par4);

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
			return block != FronosBlocks.vanillaCreamLayer.blockID && block != FronosBlocks.vanillaCream.blockID ? this.fronosGrassIcon[2] : this.fronosGrassIcon[6];
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
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == FronosItems.pearl.itemID)
		{
			world.setBlock(par2, par3, par4, FronosBlocks.goldenGrass.blockID, 1, 3);
			par5EntityPlayer.inventory.consumeInventoryItem(FronosItems.pearl.itemID);
			return true;
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
			else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
			{
				for (int var6 = 0; var6 < 4; ++var6)
				{
					int var7 = par2 + par5Random.nextInt(3) - 1;
					int var8 = par3 + par5Random.nextInt(5) - 3;
					int var9 = par4 + par5Random.nextInt(3) - 1;
					int var10 = par1World.getBlockId(var7, var8 + 1, var9);

					if (par1World.getBlockId(var7, var8, var9) == FronosBlocks.fronosDirt.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
					{
						par1World.setBlock(var7, var8, var9, FronosBlocks.fronosGrass.blockID);
					}
				}
			}
		}
	}

	/*@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        float f = 0.005F;
        return AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - f), (double)(par4 + 1));
    }*/

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