/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.eventhandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.gui.GuiCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

public class GuiEventHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if (tile instanceof TileEntityCandyExtractor)
		{
			return new ContainerCandyExtractor(player.inventory, (TileEntityCandyExtractor)tile);
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if (tile instanceof TileEntityCandyExtractor)
		{
			return new GuiCandyExtractor(player.inventory, (TileEntityCandyExtractor)tile);
		}
		return null;
	}

	/*TODO: Waiting for Galacticraft
	 * 
	 * 		//EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if (playerBase == null)
		{
			player.addChatMessage(new ChatComponentText("More Planets player instance null server-side. This is a bug."));
			return null;
		}
		if (tile != null)
		{
			if (tile instanceof TileEntityUltraVioletSolarPanel)
			{
				return new ContainerUltraVioletSolarPanel(player.inventory, (TileEntityUltraVioletSolarPanel) tile);
			}
			else if (tile instanceof TileEntityPowerCrystalGenerator)
			{
				return new ContainerPowerCrystalGenerator(player.inventory, (TileEntityPowerCrystalGenerator) tile);
			}
			else if (tile instanceof TileEntityCandyExtractor)
			{
				return new ContainerCandyExtractor(player.inventory, (TileEntityCandyExtractor)tile);
			}
			else if (tile instanceof TileEntityMineralWaterGenerator)
			{
				return new ContainerMineralWaterGenerator(player.inventory, (TileEntityMineralWaterGenerator) tile);
			}
		}
		return null;

						if (tile instanceof TileEntityUltraVioletSolarPanel)
				{
					return new GuiUltraVioletSolarPanel(player.inventory, (TileEntityUltraVioletSolarPanel) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
				}
				else if (tile instanceof TileEntityPowerCrystalGenerator)
				{
					return new GuiPowerCrystalGenerator(player.inventory, (TileEntityPowerCrystalGenerator) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
				}
				else if (tile instanceof TileEntityCandyExtractor)
				{
					return new GuiCandyExtractor(player.inventory, (TileEntityCandyExtractor)tile);
				}
				else if (tile instanceof TileEntityMineralWaterGenerator)
				{
					return new GuiMineralWaterGenerator(player.inventory, (TileEntityMineralWaterGenerator) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
				}
	 */
}