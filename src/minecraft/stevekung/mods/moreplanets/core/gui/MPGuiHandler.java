/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.gui;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.entities.player.GCCorePlayerMP;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.inventory.FronosContainerCandyExtractor;
import stevekung.mods.moreplanets.fronos.inventory.FronosGuiCandyExtractor;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityCandyExtractor;
import stevekung.mods.moreplanets.nibiru.inventory.NibiruContainerPowerCrystalGenerator;
import stevekung.mods.moreplanets.nibiru.inventory.NibiruGuiPowerCrystalGenerator;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityPowerCrystalGenerator;
import stevekung.mods.moreplanets.polongnius.gui.PolongniusContainerSolar;
import stevekung.mods.moreplanets.polongnius.gui.PolongniusGuiSolar;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MPGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		GCCorePlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);

		if (playerBase == null)
		{
			player.sendChatToPlayer(ChatMessageComponent.createFromText("More Planets player instance null server-side. This is a bug."));
			return null;
		}

		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (tile != null)
		{
			if (tile instanceof PolongniusTileEntitySolar)
			{
				return new PolongniusContainerSolar(player.inventory, (PolongniusTileEntitySolar) tile);
			}
			else if (tile instanceof NibiruTileEntityPowerCrystalGenerator)
			{
				return new NibiruContainerPowerCrystalGenerator(player.inventory, (NibiruTileEntityPowerCrystalGenerator) tile);
			}
			else if (tile instanceof FronosTileEntityCandyExtractor)
			{
				return new FronosContainerCandyExtractor(player.inventory, (FronosTileEntityCandyExtractor) tile);
			}
		}
		return tile;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			return this.getClientGuiElement(ID, player, world, new Vector3(x, y, z));
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	private Object getClientGuiElement(int ID, EntityPlayer player, World world, Vector3 position)
	{
		TileEntity tile = world.getBlockTileEntity(position.intX(), position.intY(), position.intZ());

		if (tile != null)
		{
			if (tile instanceof PolongniusTileEntitySolar)
			{
				return new PolongniusGuiSolar(player.inventory, (PolongniusTileEntitySolar) world.getBlockTileEntity(position.intX(), position.intY(), position.intZ()));
			}
			else if (tile instanceof NibiruTileEntityPowerCrystalGenerator)
			{
				return new NibiruGuiPowerCrystalGenerator(player.inventory, (NibiruTileEntityPowerCrystalGenerator) world.getBlockTileEntity(position.intX(), position.intY(), position.intZ()));
			}
			else if (tile instanceof FronosTileEntityCandyExtractor)
			{
				return new FronosGuiCandyExtractor(player.inventory, (FronosTileEntityCandyExtractor) world.getBlockTileEntity(position.intX(), position.intY(), position.intZ()));
			}
		}
		return tile;
	}
}