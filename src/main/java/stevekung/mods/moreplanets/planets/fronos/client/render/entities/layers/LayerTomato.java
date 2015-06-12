package stevekung.mods.moreplanets.planets.fronos.client.render.entities.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosCoral;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderTomato;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;

@SideOnly(Side.CLIENT)
public class LayerTomato implements LayerRenderer
{
	private RenderTomato render;

	public LayerTomato(RenderTomato render)
	{
		this.render = render;
	}

	public void func_177204_a(EntityTomato entity)
	{
		if (!entity.isChild() && !entity.isInvisible())
		{
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			this.render.bindTexture(TextureMap.locationBlocksTexture);
			GlStateManager.enableCull();
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.125F, 0.0F);
			GlStateManager.rotate(180.0F, 180.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(FronosBlocks.fronos_coral.getDefaultState().withProperty(BlockFronosCoral.VARIANT, BlockFronosCoral.BlockType.colunus_coral), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.disableCull();
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		this.func_177204_a((EntityTomato)entity);
	}
}