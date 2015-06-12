/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.schematics;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.nibiru.gui.NibiruGuiSchematicRocketT5;
import stevekung.mods.moreplanets.nibiru.inventory.NibiruContainerRocketBenchT5;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruSchematicRocketT5 implements ISchematicPage
{
	@Override
	public int getPageID()
	{
		return NibiruConfigManager.idSchematicRocketT5;
	}

	@Override
	public int getGuiID()
	{
		return NibiruConfigManager.idGuiRocketT5;
	}

	@Override
	public ItemStack getRequiredItem()
	{
		return new ItemStack(NibiruItems.schematicT5.itemID, 1, 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
	{
		return new NibiruGuiSchematicRocketT5(player.inventory, x, y, z);
	}

	@Override
	public Container getResultContainer(EntityPlayer player, int x, int y, int z)
	{
		return new NibiruContainerRocketBenchT5(player.inventory, x, y, z);
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