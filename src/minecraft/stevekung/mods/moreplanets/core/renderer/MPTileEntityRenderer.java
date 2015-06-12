/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import stevekung.mods.moreplanets.diona.core.render.tileentities.DionaTileEntityAncientChestRenderer;
import stevekung.mods.moreplanets.diona.core.render.tileentities.DionaTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityAncientChest;
import stevekung.mods.moreplanets.diona.tileentities.DionaTileEntityTreasureChest;
import stevekung.mods.moreplanets.fronos.core.render.tileentities.FronosTileEntitySpaceOysterCloseRenderer;
import stevekung.mods.moreplanets.fronos.core.render.tileentities.FronosTileEntitySpaceOysterRenderer;
import stevekung.mods.moreplanets.fronos.core.render.tileentities.FronosTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOyster;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOysterClose;
import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntityTreasureChest;
import stevekung.mods.moreplanets.kapteynb.core.render.tileentities.KapteynBTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.kapteynb.tileentities.KapteynBTileEntityTreasureChest;
import stevekung.mods.moreplanets.koentus.core.render.tileentities.KoentusTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityTreasureChest;
import stevekung.mods.moreplanets.nibiru.core.render.tileentities.NibiruTileEntityAncientChestRenderer;
import stevekung.mods.moreplanets.nibiru.core.render.tileentities.NibiruTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityAncientChest;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityTreasureChest;
import stevekung.mods.moreplanets.polongnius.core.render.tileentities.PolongniusTileEntityAncientChestRenderer;
import stevekung.mods.moreplanets.polongnius.core.render.tileentities.PolongniusTileEntitySolarPanelRenderer;
import stevekung.mods.moreplanets.polongnius.core.render.tileentities.PolongniusTileEntityTreasureChestRenderer;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityAncientChest;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntityTreasureChest;
import cpw.mods.fml.client.registry.ClientRegistry;

public class MPTileEntityRenderer
{
	public static void registerTileEntityRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(DionaTileEntityTreasureChest.class, new DionaTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(PolongniusTileEntityTreasureChest.class, new PolongniusTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(NibiruTileEntityTreasureChest.class, new NibiruTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(NibiruTileEntityAncientChest.class, new NibiruTileEntityAncientChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(PolongniusTileEntitySolar.class, new PolongniusTileEntitySolarPanelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(PolongniusTileEntityAncientChest.class, new PolongniusTileEntityAncientChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(KoentusTileEntityTreasureChest.class, new KoentusTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(FronosTileEntitySpaceOyster.class, new FronosTileEntitySpaceOysterRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(FronosTileEntitySpaceOysterClose.class, new FronosTileEntitySpaceOysterCloseRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(FronosTileEntityTreasureChest.class, new FronosTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(KapteynBTileEntityTreasureChest.class, new KapteynBTileEntityTreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(DionaTileEntityAncientChest.class, new DionaTileEntityAncientChestRenderer());
	}
}