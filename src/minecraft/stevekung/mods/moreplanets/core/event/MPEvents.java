/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.event;

import java.util.logging.Level;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.sound.PlayStreamingEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import stevekung.mods.moreplanets.core.worldgen.MPWorldGenMeteor;
import stevekung.mods.moreplanets.diona.dimension.DionaWorldProvider;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockSapling;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityGrappy;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlockSapling;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedZombie;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.dimension.PolongniusWorldProvider;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MPEvents
{
	private WorldGenerator koentusMeteorGenerator;
	private WorldGenerator polongniusMeteorGenerator;

	private static String[] recordSoundFiles = { "fronos:ladadee.ogg" };

	@ForgeSubscribe
	public void onDionaPlanetDecorated(GCCoreEventPopulate.Post event)
	{
		if (this.koentusMeteorGenerator == null)
		{
			this.koentusMeteorGenerator = new MPWorldGenMeteor(KoentusBlocks.fallenKoentusMeteor.blockID);
		}

		if (event.worldObj.provider instanceof DionaWorldProvider)
		{
			int meteorPerChunk = 1;
			int x;
			int y;
			int z;

			for (int meteorCount = 0; meteorCount < meteorPerChunk; ++meteorCount)
			{
				if (event.worldObj.rand.nextInt(50) == 0)
				{
					x = event.chunkX + event.rand.nextInt(16) + 8;
					y = event.rand.nextInt(256);
					z = event.chunkZ + event.rand.nextInt(16) + 8;
					this.koentusMeteorGenerator.generate(event.worldObj, event.rand, x, y, z);
				}
			}
		}
	}

	@ForgeSubscribe
	public void onPolongniusPlanetDecorated(GCCoreEventPopulate.Post event)
	{
		if (this.polongniusMeteorGenerator == null)
		{
			this.polongniusMeteorGenerator = new MPWorldGenMeteor(PolongniusBlocks.fallenPolongniusMeteor.blockID);
		}

		if (event.worldObj.provider instanceof PolongniusWorldProvider)
		{
			int x;
			int y;
			int z;

			if (event.worldObj.rand.nextInt(50) == 0)
			{
				x = event.chunkX + event.rand.nextInt(16) + 8;
				y = event.rand.nextInt(256);
				z = event.chunkZ + event.rand.nextInt(16) + 8;
				this.polongniusMeteorGenerator.generate(event.worldObj, event.rand, x, y, z);
			}
		}
	}

	@ForgeSubscribe
	public void onZombieSummonAid(SummonAidEvent event)
	{
		if (event.entity instanceof NibiruEntityInfectedZombie)
		{
			event.customSummonedAid = new NibiruEntityInfectedZombie(event.world);

			if (((EntityLivingBase) event.entity).getRNG().nextFloat() < ((NibiruEntityInfectedZombie) event.entity).getEntityAttribute(((NibiruEntityInfectedZombie) event.entity).getReinforcementsAttribute()).getAttributeValue())
			{
				event.setResult(Result.ALLOW);
			}
			event.setResult(Result.DENY);
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event)
	{
		event.manager.addSound("diona:player/laser.ogg");
		event.manager.addSound("fronos:mob/marshmallow/say.ogg");
		event.manager.addSound("fronos:mob/marshmallow/step.ogg");
		event.manager.addSound("fronos:mob/marshmallow/hurt1.ogg");
		event.manager.addSound("fronos:mob/marshmallow/hurt2.ogg");
		event.manager.addSound("fronos:mob/marshmallow/death.ogg");

		for (String recordSoundFile : recordSoundFiles)
		{
			try
			{
				event.manager.soundPoolStreaming.addSound(recordSoundFile);
			}
			catch (Exception e)
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[MorePlanet] Failed loading sound file: " + recordSoundFile);
			}
		}
	}

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		int meta = event.world.getBlockMetadata(event.X, event.Y, event.Z);

		if (event.ID == NibiruBlocks.nibiruBlockSapling.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				switch (meta)
				{
				default:
					if (event.world.rand.nextFloat() < 0.45D)
					{
						((NibiruBlockSapling)NibiruBlocks.nibiruBlockSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
					}
					break;
				}
			}
		}
		else if (event.ID == FronosBlocks.fronosBlockSapling.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				switch (meta)
				{
				default:
					if (event.world.rand.nextFloat() < 0.45D)
					{
						((FronosBlockSapling)FronosBlocks.fronosBlockSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
					}
					break;
				}
			}
		}
	}

	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event)
	{
		World world = event.world;
		MovingObjectPosition pos = event.target;

		int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if (blockID == KapteynBBlocks.kapteynBFrozenWater.blockID && meta == 0)
		{
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(new FluidStack(KapteynBBlocks.frozenWaterFluid, FluidContainerRegistry.BUCKET_VOLUME), event.current);

			if (filledContainer != null)
			{
				world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

				event.result = filledContainer;
				event.setResult(Result.ALLOW);
			}
			else
			{
				event.setResult(Result.DENY);
			}
		}
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	{
		int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if (blockID == KapteynBBlocks.kapteynBFrozenWater.blockID && meta == 0)
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

			return new ItemStack(KapteynBItems.frozenWaterBucket, 1, 0);
		}
		else
		{
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onPlayStreaming(PlayStreamingEvent event)
	{
		if (event.name == "fronosDisc")
		{
			FMLClientHandler.instance().getClient().sndManager.playStreaming("fronos:ladadee", event.x + 0.5F, event.y + 0.5F, event.z + 0.5F);
		}
	}

	@ForgeSubscribe
	public void interactEntityWithDyeColor(EntityInteractEvent event)
	{
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
		Entity entity = event.target;
		if (itemstack != null)
		{
			int meta = itemstack.getItemDamage();

			if (itemstack.itemID == Item.dyePowder.itemID && (meta == 0 || meta == 1 || meta == 2 || meta == 3 || meta == 4 || meta == 5 || meta == 6 || meta == 7 || meta == 8 || meta == 9 || meta == 10 || meta == 11 || meta == 12 || meta == 13 || meta == 14 || meta == 15))
			{
				int dyeMeta = this.convertToDyeMeta(meta);
				int i = BlockColored.getBlockFromDye(dyeMeta);

				if (entity instanceof FronosEntityGrappy)
				{
					FronosEntityGrappy grappy = (FronosEntityGrappy)entity;

					if (!grappy.getSheared() && grappy.getFleeceColor() != i)
					{
						grappy.setFleeceColor(i);

						if (!event.entityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}
					}
					event.setResult(Result.ALLOW);
				}
			}
		}
	}

	private int convertToDyeMeta(int meta)
	{
		switch (meta)
		{
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		case 5:
			return 5;
		case 6:
			return 6;
		case 7:
			return 7;
		case 8:
			return 8;
		case 9:
			return 9;
		case 10:
			return 10;
		case 11:
			return 11;
		case 12:
			return 12;
		case 13:
			return 13;
		case 14:
			return 14;
		case 15:
			return 15;
		}
		return 0;
	}
}