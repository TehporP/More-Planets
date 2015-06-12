/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlockTile;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBlockSolar extends GCCoreBlockTile
{
	private Icon[] icons = new Icon[6];

	public PolongniusBlockSolar(int block)
	{
		super(block, Material.iron);
		this.setStepSound(Block.soundMetalFootstep);
		this.blockHardness = 2.0F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.icons[0] = par1IconRegister.registerIcon("polongnius:solarAdvanced_0");
		this.icons[1] = par1IconRegister.registerIcon("polongnius:solarAdvanced_1");
		this.icons[2] = par1IconRegister.registerIcon("galacticraftcore:machine_blank");
		this.icons[3] = par1IconRegister.registerIcon("galacticraftcore:machine_output");
		this.blockIcon = this.icons[0];
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		if (side == ForgeDirection.getOrientation(2).getOpposite().ordinal())
		{
			return this.icons[3];
		}
		else if (side == ForgeDirection.UP.ordinal())
		{
			return this.icons[0];
		}
		else if (side == ForgeDirection.DOWN.ordinal())
		{
			return this.icons[2];
		}
		return this.icons[1];
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x1, int y1, int z1, int side)
	{
		for (int y = 1; y <= 2; y++)
		{
			for (int x = -1; x <= 1; x++)
			{
				for (int z = -1; z <= 1; z++)
				{
					int blockID = world.getBlockId(x1 + (y == 2 ? x : 0), y1 + 2, z1 + (y == 2 ? z : 0));

					if (blockID > 0 && !Block.blocksList[blockID].isBlockReplaceable(world, x1 + x, y1 + 2, z1 + z))
					{
						return false;
					}
				}
			}
		}

		return new Vector3(x1, y1, z1).clone().modifyPositionFromSide(ForgeDirection.getOrientation(side).getOpposite()).getBlockID(world) != GCCoreBlocks.fakeBlock.blockID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		world.getBlockMetadata(x, y, z);

		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int change = 0;

		switch (angle)
		{
		case 0:
			change = 1;
			break;
		case 1:
			change = 2;
			break;
		case 2:
			change = 0;
			break;
		case 3:
			change = 3;
			break;
		}

		world.setBlockMetadataWithNotify(x, y, z, this.blockID + change, 3);

		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (tile instanceof PolongniusTileEntitySolar)
		{
			((PolongniusTileEntitySolar) tile).onCreate(new Vector3(x, y, z));
		}
	}

	@Override
	public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
	{
		final TileEntity var9 = var1.getBlockTileEntity(var2, var3, var4);

		if (var9 instanceof PolongniusTileEntitySolar)
		{
			((PolongniusTileEntitySolar) var9).onDestroy(var9);
		}

		super.breakBlock(var1, var2, var3, var4, var5, var6);
	}

	@Override
	public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int metadata = par1World.getBlockMetadata(x, y, z);
		int original = metadata;

		int change = 0;

		if (metadata >= this.blockID)
		{
			original -= this.blockID;
		}

		switch (original)
		{
		case 0:
			change = 3;
			break;
		case 3:
			change = 1;
			break;
		case 1:
			change = 2;
			break;
		case 2:
			change = 0;
			break;
		}

		if (metadata >= this.blockID)
		{
			change += this.blockID;
		}

		par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
		return true;
	}

	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		entityPlayer.openGui(MorePlanetCore.instance, -1, world, x, y, z);
		return true;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		Item item = Item.itemsList[this.blockID];

		if (item == null)
		{
			return null;
		}

		int metadata = this.getDamageValue(world, x, y, z);

		return new ItemStack(this.blockID, 1, metadata);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new PolongniusTileEntitySolar(200);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}