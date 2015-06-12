/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.schematics;
//package stevekung.mods.moreplanets.planets.fronos.schematics;
//
//import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
//import net.minecraft.client.gui.GuiScreen;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
//import stevekung.mods.moreplanets.planets.fronos.gui.GuiSchematicTier7Rocket;
//import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerSchematicTier7Rocket;
//import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
//
//public class SchematicTier7Rocket implements ISchematicPage
//{
//	@Override
//	public int getPageID()
//	{
//		return ConfigManagerMP.idSchematicTier7Rocket;
//	}
//
//	@Override
//	public int getGuiID()
//	{
//		return ConfigManagerMP.idGuiSchematicTier7Rocket;
//	}
//
//	@Override
//	public ItemStack getRequiredItem()
//	{
//		return new ItemStack(FronosItems.tier7_rocket_schematic, 1, 0);
//	}
//
//	@SideOnly(Side.CLIENT)
//	@Override
//	public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
//	{
//		return new GuiSchematicTier7Rocket(player.inventory, x, y, z);
//	}
//
//	@Override
//	public Container getResultContainer(EntityPlayer player, int x, int y, int z)
//	{
//		return new ContainerSchematicTier7Rocket(player.inventory, x, y, z);
//	}
//
//	@Override
//	public int compareTo(ISchematicPage o)
//	{
//		if (this.getPageID() > o.getPageID())
//		{
//			return 1;
//		}
//		else
//		{
//			return -1;
//		}
//	}
//}