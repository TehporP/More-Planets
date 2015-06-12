/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import micdoodle8.mods.galacticraft.core.client.render.item.GCCoreItemRendererKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import stevekung.mods.moreplanets.core.models.MPModelSpaceshipNoFlag;
import stevekung.mods.moreplanets.diona.core.render.items.DionaItemRendererFlag;
import stevekung.mods.moreplanets.diona.core.render.items.DionaItemRendererSpaceshipT3;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.fronos.core.models.items.FronosModelEmptyCup;
import stevekung.mods.moreplanets.fronos.core.models.items.FronosModelFullCup;
import stevekung.mods.moreplanets.fronos.core.render.items.FronosItemRendererCup;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.nibiru.core.render.items.NibiruItemRendererSpaceshipT5;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.polongnius.core.render.items.PolongniusItemRendererSpaceshipT4;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class MPItemRenderer
{
	public static void registerItemRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(DionaItems.thailandFlag.itemID, new DionaItemRendererFlag());
		MinecraftForgeClient.registerItemRenderer(DionaItems.dionaKeyTreasure.itemID, new GCCoreItemRendererKey(new ResourceLocation("diona:textures/model/dionaTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(PolongniusItems.polongniusDungeonKey.itemID, new GCCoreItemRendererKey(new ResourceLocation("polongnius:textures/model/polongniusTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(NibiruItems.nibiruDungeonKey.itemID, new GCCoreItemRendererKey(new ResourceLocation("nibiru:textures/model/nibiruTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(DionaItems.rocketT3.itemID, new DionaItemRendererSpaceshipT3(new MPModelSpaceshipNoFlag()));
		MinecraftForgeClient.registerItemRenderer(KoentusItems.koentusDungeonKey.itemID, new GCCoreItemRendererKey(new ResourceLocation("koentus:textures/model/koentusTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(FronosItems.fronosDungeonKey.itemID, new GCCoreItemRendererKey(new ResourceLocation("fronos:textures/model/fronosTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(PolongniusItems.rocketTier4.itemID, new PolongniusItemRendererSpaceshipT4(new MPModelSpaceshipNoFlag()));
		MinecraftForgeClient.registerItemRenderer(KapteynBItems.kapteynBDungeonKey.itemID, new GCCoreItemRendererKey(new ResourceLocation("kapteynb:textures/model/kapteynBTreasureChest.png")));
		MinecraftForgeClient.registerItemRenderer(NibiruItems.rocketT5.itemID, new NibiruItemRendererSpaceshipT5(new MPModelSpaceshipNoFlag()));
		MinecraftForgeClient.registerItemRenderer(FronosItems.cup.itemID, new FronosItemRendererCup(new FronosModelEmptyCup(), new FronosModelFullCup()));

	}
}