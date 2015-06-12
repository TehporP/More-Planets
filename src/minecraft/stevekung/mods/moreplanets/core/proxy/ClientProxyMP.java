/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.proxy;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.client.render.block.GCCoreBlockRendererMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.EnumRarity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.client.EnumHelperClient;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.renderer.MPEntityRenderer;
import stevekung.mods.moreplanets.core.renderer.MPItemRenderer;
import stevekung.mods.moreplanets.core.renderer.MPTileEntityRenderer;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.core.render.blocks.DionaBlockRendererAncientChest;
import stevekung.mods.moreplanets.diona.core.render.blocks.DionaBlockRendererKoentusMeteor;
import stevekung.mods.moreplanets.diona.core.render.blocks.DionaBlockRendererTreasureChest;
import stevekung.mods.moreplanets.diona.proxy.ClientProxyDiona;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.core.render.blocks.FronosBlockRendererExtractor;
import stevekung.mods.moreplanets.fronos.core.render.blocks.FronosBlockRendererSpaceOyster;
import stevekung.mods.moreplanets.fronos.core.render.blocks.FronosBlockRendererSpaceOysterClose;
import stevekung.mods.moreplanets.fronos.core.render.blocks.FronosBlockRendererTreasureChest;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityCoconutMilkFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityGoldenGrassFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityGoldenSmokeFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityMagicWaterFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityOrangeDandelionFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityOvantineSmokeFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityPinkDandelionFX;
import stevekung.mods.moreplanets.fronos.particles.FronosEntityPurpleFlowerFX;
import stevekung.mods.moreplanets.fronos.proxy.ClientProxyFronos;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.core.render.blocks.KapteynBBlockRendererTreasureChest;
import stevekung.mods.moreplanets.kapteynb.proxy.ClientProxyKapteynB;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.core.render.blocks.KoentusBlockRendererEgg;
import stevekung.mods.moreplanets.koentus.core.render.blocks.KoentusBlockRendererTreasureChest;
import stevekung.mods.moreplanets.koentus.particles.KoentusEntityCrystalSmokeFX;
import stevekung.mods.moreplanets.koentus.particles.KoentusEntityDripParticleFX;
import stevekung.mods.moreplanets.koentus.particles.KoentusEntityMeteorSmokeFX;
import stevekung.mods.moreplanets.koentus.particles.KoentusEntitySludgeSmokeFX;
import stevekung.mods.moreplanets.koentus.proxy.ClientProxyKoentus;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.nibiru.core.render.blocks.NibiruBlockRendererAncientChest;
import stevekung.mods.moreplanets.nibiru.core.render.blocks.NibiruBlockRendererInfectedHeliumDirt;
import stevekung.mods.moreplanets.nibiru.core.render.blocks.NibiruBlockRendererInfectedVineDirt;
import stevekung.mods.moreplanets.nibiru.core.render.blocks.NibiruBlockRendererTreasureChest;
import stevekung.mods.moreplanets.nibiru.particles.NibiruEntityIchoriusSmokeFX;
import stevekung.mods.moreplanets.nibiru.proxy.ClientProxyNibiru;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.core.render.blocks.PolongniusBlockRendererAncientChest;
import stevekung.mods.moreplanets.polongnius.core.render.blocks.PolongniusBlockRendererTreasureChest;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.particles.PolongniusEntityCheeseBubbleFX;
import stevekung.mods.moreplanets.polongnius.proxy.ClientProxyPolongnius;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyMP extends CommonProxyMP
{
	private static int koentusMeteorRenderID;
	private static int polongniusMeteorRenderID;
	private static int dionaTreasureChestRenderID;
	private static int polongniusTreasureChestRenderID;
	private static int nibiruTreasureChestRenderID;
	private static int nibiruAncientChestRenderID;
	private static int machineRenderID;
	private static int polongniusAncientChestRenderID;
	private static int koentusTreasureChestRenderID;
	private static int koentusEledosEggRenderID;
	private static int spaceOysterRenderID;
	private static int spaceOysterCloseRenderID;
	private static int fronosTreasureChestRenderID;
	private static int candyExtractorRenderID;
	private static int kapteynBTreasureChestRenderID;
	private static int dionaAncientChestRenderID;
	public static int infectedDirtVineRenderID;
	public static int infectedDirtHeliumRenderID;

	public static int infectedDirtVineRenderPass;
	public static int infectedDirtHeliumRenderPass;

	public static ClientProxyDiona diona = new ClientProxyDiona();
	public static ClientProxyPolongnius polongnius = new ClientProxyPolongnius();
	public static ClientProxyNibiru nibiru = new ClientProxyNibiru();
	public static ClientProxyFronos fronos = new ClientProxyFronos();
	public static ClientProxyKoentus koentus = new ClientProxyKoentus();
	public static ClientProxyKapteynB kapteynB = new ClientProxyKapteynB();

	public static Minecraft mc = Minecraft.getMinecraft();

	public static EnumRarity morePlanetItemRarity = EnumHelperClient.addRarity("MorePlanetRarity", 11, "MP");

	public class ClientPacketHandler implements IPacketHandler
	{
		@Override
		public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p)
		{
		}
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		ClientProxyMP.diona.init(event);
		ClientProxyMP.polongnius.init(event);
		ClientProxyMP.nibiru.init(event);
		ClientProxyMP.fronos.init(event);
		ClientProxyMP.koentus.init(event);
		ClientProxyMP.kapteynB.init(event);
		NetworkRegistry.instance().registerChannel(new ClientPacketHandler(), MorePlanetCore.MODID, Side.CLIENT);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		ClientProxyMP.diona.postInit(event);
		ClientProxyMP.polongnius.postInit(event);
		ClientProxyMP.nibiru.postInit(event);
		ClientProxyMP.koentus.postInit(event);
		ClientProxyMP.fronos.postInit(event);
		ClientProxyMP.kapteynB.postInit(event);

		MPEntityRenderer.registerEntityRenderers();
		MPTileEntityRenderer.registerTileEntityRenderers();
		MPItemRenderer.registerItemRenderers();

		registerBlockRenderers();
	}

	public static void registerBlockRenderers()
	{
		koentusMeteorRenderID = RenderingRegistry.getNextAvailableRenderId();
		polongniusMeteorRenderID = RenderingRegistry.getNextAvailableRenderId();
		dionaTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		polongniusTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		nibiruTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		nibiruAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		machineRenderID = RenderingRegistry.getNextAvailableRenderId();
		polongniusAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		koentusTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		koentusEledosEggRenderID = RenderingRegistry.getNextAvailableRenderId();
		spaceOysterRenderID = RenderingRegistry.getNextAvailableRenderId();
		spaceOysterCloseRenderID = RenderingRegistry.getNextAvailableRenderId();
		fronosTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		candyExtractorRenderID = RenderingRegistry.getNextAvailableRenderId();
		kapteynBTreasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		dionaAncientChestRenderID = RenderingRegistry.getNextAvailableRenderId();
		infectedDirtVineRenderID = RenderingRegistry.getNextAvailableRenderId();
		infectedDirtHeliumRenderID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new DionaBlockRendererKoentusMeteor(koentusMeteorRenderID));
		RenderingRegistry.registerBlockHandler(new DionaBlockRendererKoentusMeteor(polongniusMeteorRenderID));
		RenderingRegistry.registerBlockHandler(new DionaBlockRendererTreasureChest(dionaTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new PolongniusBlockRendererTreasureChest(polongniusTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new NibiruBlockRendererTreasureChest(nibiruTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new NibiruBlockRendererAncientChest(nibiruAncientChestRenderID));
		RenderingRegistry.registerBlockHandler(new GCCoreBlockRendererMachine(machineRenderID));
		RenderingRegistry.registerBlockHandler(new PolongniusBlockRendererAncientChest(polongniusAncientChestRenderID));
		RenderingRegistry.registerBlockHandler(new KoentusBlockRendererTreasureChest(koentusTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new KoentusBlockRendererEgg(koentusEledosEggRenderID));
		RenderingRegistry.registerBlockHandler(new FronosBlockRendererSpaceOyster(spaceOysterRenderID));
		RenderingRegistry.registerBlockHandler(new FronosBlockRendererSpaceOysterClose(spaceOysterCloseRenderID));
		RenderingRegistry.registerBlockHandler(new FronosBlockRendererTreasureChest(fronosTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new FronosBlockRendererExtractor(candyExtractorRenderID));
		RenderingRegistry.registerBlockHandler(new KapteynBBlockRendererTreasureChest(kapteynBTreasureChestRenderID));
		RenderingRegistry.registerBlockHandler(new DionaBlockRendererAncientChest(dionaAncientChestRenderID));
		RenderingRegistry.registerBlockHandler(new NibiruBlockRendererInfectedVineDirt(infectedDirtVineRenderID));
		RenderingRegistry.registerBlockHandler(new NibiruBlockRendererInfectedHeliumDirt(infectedDirtHeliumRenderID));
	}

	@Override
	public int getBlockRenderID(int block)
	{
		if (block == KoentusBlocks.fallenKoentusMeteor.blockID)
		{
			return koentusMeteorRenderID;
		}
		else if (block == DionaBlocks.dionaTreasureChest.blockID)
		{
			return dionaTreasureChestRenderID;
		}
		else if (block == NibiruBlocks.nibiruAncientChest.blockID)
		{
			return nibiruAncientChestRenderID;
		}
		else if (block == PolongniusBlocks.fallenPolongniusMeteor.blockID)
		{
			return polongniusMeteorRenderID;
		}
		else if (block == PolongniusBlocks.polongniusTreasureChest.blockID)
		{
			return polongniusTreasureChestRenderID;
		}
		else if (block == NibiruBlocks.nibiruTreasureChest.blockID)
		{
			return nibiruTreasureChestRenderID;
		}
		else if (block == NibiruBlocks.powerCrystalGenerator.blockID)
		{
			return machineRenderID;
		}
		else if (block == PolongniusBlocks.polongniusAncientChest.blockID)
		{
			return polongniusAncientChestRenderID;
		}
		else if (block == KoentusBlocks.koentusTreasureChest.blockID)
		{
			return koentusTreasureChestRenderID;
		}
		/*else if (block == KoentusBlocks.koentusEgg.blockID)
		{
			return koentusEggRenderID;
		}*///TODO
		else if (block == FronosBlocks.spaceOyster.blockID)
		{
			return spaceOysterRenderID;
		}
		else if (block == FronosBlocks.spaceOysterClose.blockID)
		{
			return spaceOysterCloseRenderID;
		}
		else if (block == FronosBlocks.fronosTreasureChest.blockID)
		{
			return fronosTreasureChestRenderID;
		}
		else if (block == FronosBlocks.candyExtractorIdle.blockID || block == FronosBlocks.candyExtractorActive.blockID)
		{
			return candyExtractorRenderID;
		}
		else if (block == KapteynBBlocks.kapteynBTreasureChest.blockID)
		{
			return kapteynBTreasureChestRenderID;
		}
		else if (block == DionaBlocks.dionaAncientChest.blockID)
		{
			return dionaAncientChestRenderID;
		}
		else if (block == NibiruBlocks.infectedVineDirt.blockID)
		{
			return infectedDirtVineRenderID;
		}
		else if (block == NibiruBlocks.infectedHeliumDirt.blockID)
		{
			return infectedDirtHeliumRenderID;
		}
		return -1;
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;
		new Random();

		if (string == "cheeseBubble")
		{
			entityfx = new PolongniusEntityCheeseBubbleFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "koentusSmoke")
		{
			entityfx = new KoentusEntityMeteorSmokeFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "koentusSludge")
		{
			entityfx = new KoentusEntityDripParticleFX(mc.theWorld, x, y, z, MorePlanetCore.koentusSludge);
		}
		else if (string == "cheeseSlime")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, PolongniusItems.cheeseSlimeball);
		}
		else if (string == "koentusSludgeSmoke")
		{
			entityfx = new KoentusEntitySludgeSmokeFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "crystalSmoke")
		{
			entityfx = new KoentusEntityCrystalSmokeFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "ichoriusSmoke")
		{
			entityfx = new NibiruEntityIchoriusSmokeFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "vanillaBall")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.creamBall, 0);
		}
		else if (string == "chocolateBall")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.creamBall, 1);
		}
		else if (string == "orangeDandelion")
		{
			entityfx = new FronosEntityOrangeDandelionFX(mc.theWorld, x, y, z, 2.0F);
		}
		else if (string == "pinkDandelion")
		{
			entityfx = new FronosEntityPinkDandelionFX(mc.theWorld, x, y, z, 2.0F);
		}
		else if (string == "purpleSpike")
		{
			entityfx = new FronosEntityPurpleFlowerFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "coconutMilk")
		{
			entityfx = new FronosEntityCoconutMilkFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "mineralWater")
		{
			entityfx = new FronosEntityMagicWaterFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "jellyBerry")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 3);
		}
		else if (string == "jellyStrawberry")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 2);
		}
		else if (string == "jellyRaspberry")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 1);
		}
		else if (string == "jellyGrape")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 0);
		}
		else if (string == "jellyLime")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 4);
		}
		else if (string == "jellyOrange")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.jelly, 5);
		}
		else if (string == "strawberryBall")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.creamBall, 2);
		}
		else if (string == "ovantine")
		{
			entityfx = new FronosEntityOvantineSmokeFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "orangeBall")
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, FronosItems.creamBall, 3);
		}
		else if (string == "goldDust")
		{
			entityfx = new FronosEntityGoldenGrassFX(mc.theWorld, x, y, z, 0.0F, 0.0F, 0.0F);
		}
		else if (string == "goldSmoke")
		{
			entityfx = new FronosEntityGoldenSmokeFX(mc.theWorld, x, y, z, 0.0F, 0.0F, 0.0F);
		}
		mc.effectRenderer.addEffect(entityfx);
	}
}