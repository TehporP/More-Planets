/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.schematics;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.polongnius.gui.PolongniusGuiSchematicRocketT4NoFlag;
import stevekung.mods.moreplanets.polongnius.inventory.PolongniusContainerRocketBenchT4NoFlag;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusSchematicRocketT4NoFlag implements ISchematicPage
{
	@Override
	public int getPageID()
	{
		return PolongniusConfigManager.idSchematicRocketT4NoFlag;
	}

	@Override
	public int getGuiID()
	{
		return PolongniusConfigManager.idGuiRocketT4NoFlag;
	}

	@Override
	public ItemStack getRequiredItem()
	{
		return new ItemStack(PolongniusItems.schematicT4.itemID, 1, 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
	{
		return new PolongniusGuiSchematicRocketT4NoFlag(player.inventory, x, y, z);
	}

	@Override
	public Container getResultContainer(EntityPlayer player, int x, int y, int z)
	{
		return new PolongniusContainerRocketBenchT4NoFlag(player.inventory, x, y, z);
	}

	@Override
	public int compareTo(ISchematicPage o)
	{
		if (this.getPageID() > o.getPageID())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
}