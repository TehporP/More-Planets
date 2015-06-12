/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.eventhandler;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.common.util.DamageSourceMP;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;
import stevekung.mods.moreplanets.moons.io.items.IoItems;
import stevekung.mods.moreplanets.moons.koentus.blocks.BlockCrystalSapling;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockDandelion;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosSapling;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockGlassGemCorn;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.items.ItemCandyBow;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockNibiruSapling;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class MorePlanetsEvents
{
	@SubscribeEvent
	public void onZombieSummonAid(SummonAidEvent event)
	{
		if (event.entity instanceof EntityInfectedZombie)
		{
			event.customSummonedAid = new EntityInfectedZombie(event.world);

			if (((EntityLivingBase) event.entity).getRNG().nextFloat() < ((EntityInfectedZombie) event.entity).getEntityAttribute(((EntityInfectedZombie) event.entity).getReinforcementsAttribute()).getAttributeValue())
			{
				event.setResult(Result.ALLOW);
			}
			event.setResult(Result.DENY);
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.entityLiving.isPotionActive(MPPotions.icy_poison))
		{
			if (event.entityLiving.worldObj.isAirBlock(event.entityLiving.getPosition()))
			{
				if (KapteynBBlocks.icy_poison_crystal.canPlaceBlockAt(event.entityLiving.worldObj, event.entityLiving.getPosition()))
				{
					if (event.entityLiving.worldObj.rand.nextInt(100) == 0)
					{
						event.entityLiving.worldObj.setBlockState(event.entityLiving.getPosition(), KapteynBBlocks.icy_poison_crystal.getDefaultState());
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event)
	{
		World world = event.entityLiving.worldObj;

		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;

			if (player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == VenusItems.jetpack)
			{
				event.distance = 0.0F;
				event.setCanceled(true);
				return;
			}
			if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == PlutoItems.gravity_boots)
			{
				/*if (world.provider instanceof WorldProviderKoentus || world.provider instanceof WorldProviderPluto)
				{
					event.distance = 0.1F;
				}
				else if (world.provider instanceof WorldProviderSiriusB)
				{
					event.distance = 0.76F;
				}*/
				event.distance *= 0.4F;
			}
		}
	}

	@SubscribeEvent
	public void fovUpdate(FOVUpdateEvent event)
	{
		if (event.entity.isUsingItem() && event.entity.getItemInUse().getItem() instanceof ItemCandyBow)
		{
			int i = event.entity.getItemInUseDuration();
			float f = i / 20.0F;

			if (f > 1.0F)
			{
				f = 1.0F;
			}
			else
			{
				f *= f;
			}
			event.newfov *= 1.0F - f * 0.15F;
		}
	}

	/*@SubscribeEvent
	public void onRenderPlanet(CelestialBodyRenderEvent.Post event)
	{
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.currentScreen instanceof GuiCelestialSelection)
		{
			if (event.celestialBody == MorePlanetsCore.polongnius)
			{
				mc.renderEngine.bindTexture(new ResourceLocation("polongnius:textures/gui/celestialbodies/polongnius_ring.png"));
				float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
				((GuiCelestialSelection)mc.currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 30, 7);
			}
		}
	}*/

	/*@SubscribeEvent
	public void onPickupItem(EntityItemPickupEvent event)
	{
		ItemStack itemStack = event.item.getEntityItem();
		Item item = itemStack.getItem();
		int meta = itemStack.getItemDamage();

		if (event.entityPlayer.inventory.addItemStackToInventory(itemStack))
		{
			if (item == Item.getItemFromBlock(GCBlocks.basicBlock) && (meta >= 5 && meta <= 7) || item == GCItems.basicItem && meta == 2)
			{
				event.setResult(Result.ALLOW);
				event.entityPlayer.onItemPickup(event.item, itemStack.stackSize);
				event.entityPlayer.triggerAchievement(MorePlanetCore.findOre);
			}
		}
	}*/

	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		Random rand = event.world.rand;
		BlockPos pos = event.pos;
		int x = pos.getX();
		int z = pos.getZ();

		if (event.block.getBlock() == NibiruBlocks.nibiru_sapling)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					((BlockNibiruSapling)NibiruBlocks.nibiru_sapling).grow(event.world, event.world.rand, event.pos, event.block);
				}
			}
		}
		else if (event.block.getBlock() == KoentusBlocks.crystal_sapling)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					((BlockCrystalSapling)KoentusBlocks.crystal_sapling).grow(event.world, event.world.rand, event.pos, event.block);
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.fronos_sapling)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					((BlockFronosSapling)FronosBlocks.fronos_sapling).grow(event.world, event.world.rand, event.pos, event.block);
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.fronos_grass)
		{
			int var14 = pos.getY() + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{
				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				BlockPos pos1 = new BlockPos(x, var14, z);

				if (event.world.getBlockState(pos1).getBlock().isAir(event.world, pos1))
				{
					if (FronosBlocks.fronos_tall_grass.canReplace(event.world, pos1, EnumFacing.UP, new ItemStack(FronosBlocks.fronos_tall_grass, 1, rand.nextInt(3))))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlockState(pos1, FronosBlocks.fronos_tall_grass.getStateFromMeta(rand.nextInt(3)), 2);
						}
					}
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.pink_grass)
		{
			int var14 = pos.getY() + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{
				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				BlockPos pos1 = new BlockPos(x, var14, z);

				if (event.world.getBlockState(pos1).getBlock().isAir(event.world, pos1))
				{
					if (FronosBlocks.fronos_tall_grass.canReplace(event.world, pos1, EnumFacing.UP, new ItemStack(FronosBlocks.fronos_tall_grass, 1, rand.nextInt(3) + 3)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlockState(pos1, FronosBlocks.fronos_tall_grass.getStateFromMeta(rand.nextInt(3) + 3), 2);
						}
					}
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.purple_grass)
		{
			int var14 = pos.getY() + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{
				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				BlockPos pos1 = new BlockPos(x, var14, z);

				if (event.world.getBlockState(pos1).getBlock().isAir(event.world, pos1))
				{
					if (FronosBlocks.fronos_tall_grass.canReplace(event.world, pos1, EnumFacing.UP, new ItemStack(FronosBlocks.fronos_tall_grass, 1, rand.nextInt(3) + 6)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlockState(pos1, FronosBlocks.fronos_tall_grass.getStateFromMeta(rand.nextInt(3) + 6), 2);
						}
					}
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.plains_grass)
		{
			int var14 = pos.getY() + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{
				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				BlockPos pos1 = new BlockPos(x, var14, z);

				if (event.world.getBlockState(pos1).getBlock().isAir(event.world, pos1))
				{
					if (FronosBlocks.fronos_tall_grass.canReplace(event.world, pos1, EnumFacing.UP, new ItemStack(FronosBlocks.fronos_tall_grass, 1, rand.nextInt(3) + 9)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlockState(pos1, FronosBlocks.fronos_tall_grass.getStateFromMeta(rand.nextInt(3) + 9), 2);
						}
					}
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.golden_grass)
		{
			int var14 = pos.getY() + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{
				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				BlockPos pos1 = new BlockPos(x, var14, z);

				if (event.world.getBlockState(pos1).getBlock().isAir(event.world, pos1))
				{
					if (FronosBlocks.fronos_tall_grass.canReplace(event.world, pos1, EnumFacing.UP, new ItemStack(FronosBlocks.fronos_tall_grass, 1, rand.nextInt(3) + 12)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlockState(pos1, FronosBlocks.fronos_tall_grass.getStateFromMeta(rand.nextInt(3) + 12), 2);
						}
					}
				}
			}
		}
		else if (event.block == FronosBlocks.glass_gem_corn.getDefaultState())
		{
			if (event.world.getBlockState(pos.up()).getBlock().isAir(event.world, pos.up()) && event.world.getBlockState(pos.up(2)).getBlock().isAir(event.world, pos.up(2)))
			{
				event.setResult(Result.ALLOW);

				if (!event.world.isRemote)
				{
					if (rand.nextInt(3) == 0)
					{
						event.world.setBlockState(pos, FronosBlocks.glass_gem_corn.getDefaultState().withProperty(BlockGlassGemCorn.STAGE, BlockGlassGemCorn.BlockType.state_bottom2), 2);
						event.world.setBlockState(pos.up(), FronosBlocks.glass_gem_corn.getDefaultState().withProperty(BlockGlassGemCorn.STAGE, BlockGlassGemCorn.BlockType.state_middle1), 2);
						event.world.setBlockState(pos.up(2), FronosBlocks.glass_gem_corn.getDefaultState().withProperty(BlockGlassGemCorn.STAGE, BlockGlassGemCorn.BlockType.state_top1), 2);
					}
				}
			}
		}
		else if (event.block == FronosBlocks.glass_gem_corn.getDefaultState().withProperty(BlockGlassGemCorn.STAGE, BlockGlassGemCorn.BlockType.state_middle1))
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (rand.nextInt(2) == 0)
				{
					event.world.setBlockState(pos, FronosBlocks.glass_gem_corn.getStateFromMeta(rand.nextInt(2) + 4), 2);
				}
			}
		}
		else if (event.block == FronosBlocks.glass_gem_corn.getDefaultState().withProperty(BlockGlassGemCorn.STAGE, BlockGlassGemCorn.BlockType.state_middle2))
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (rand.nextInt(1) == 0)
				{
					event.world.setBlockState(pos, FronosBlocks.glass_gem_corn.getStateFromMeta(5), 2);
				}
			}
		}
		else if (event.block.getBlock() == FronosBlocks.fronos_dandelion)
		{
			if (event.block == FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.young_orange_dandelion))
			{
				event.setResult(Result.ALLOW);

				if (!event.world.isRemote)
				{
					if (rand.nextInt(2) == 0)
					{
						event.world.setBlockState(pos, FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.orange_dandelion), 2);
					}
				}
			}
			if (event.block == FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.young_pink_dandelion))
			{
				event.setResult(Result.ALLOW);

				if (!event.world.isRemote)
				{
					if (rand.nextInt(2) == 0)
					{
						event.world.setBlockState(pos, FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.pink_dandelion), 2);
					}
				}
			}
			if (event.block == FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.young_purple_dandelion))
			{
				event.setResult(Result.ALLOW);

				if (!event.world.isRemote)
				{
					if (rand.nextInt(2) == 0)
					{
						event.world.setBlockState(pos, FronosBlocks.fronos_dandelion.getDefaultState().withProperty(BlockDandelion.VARIANT, BlockDandelion.BlockType.purple_dandelion), 2);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;

		// Potion tick
		if (entity instanceof EntityLivingBase)
		{
			if (entity.isPotionActive(MPPotions.infected_gas))
			{
				if (entity.ticksExisted % 35 == 0)
				{
					entity.attackEntityFrom(DamageSourceMP.infected_gas, 0.5F);
				}
				if (entity.getActivePotionEffect(MPPotions.infected_gas).getDuration() == 0)
				{
					entity.removePotionEffect(MPPotions.infected_gas.id);
					return;
				}
			}
			else if (entity.isPotionActive(MPPotions.chemical))
			{
				if (entity.ticksExisted % 8 == 0)
				{
					entity.attackEntityFrom(DamageSourceMP.chemical, 1.0F);
				}
				if (entity.getActivePotionEffect(MPPotions.chemical).getDuration() == 0)
				{
					entity.removePotionEffect(MPPotions.chemical.id);
					return;
				}
			}
			else if (entity.isPotionActive(MPPotions.electro_magnetic_pulse))
			{
				if (!event.entityLiving.worldObj.isRemote)
				{
					entity.motionX = 0.0F;
					entity.motionY = -1.0F;
					entity.motionZ = 0.0F;
				}
				if (entity.getActivePotionEffect(MPPotions.electro_magnetic_pulse).getDuration() == 0)
				{
					entity.removePotionEffect(MPPotions.electro_magnetic_pulse.id);
					return;
				}
			}
			else if (entity.isPotionActive(MPPotions.icy_poison))
			{
				entity.attackEntityFrom(DamageSourceMP.icy_poison, 1.0F);

				if (entity.getActivePotionEffect(MPPotions.icy_poison).getDuration() == 0)
				{
					entity.removePotionEffect(MPPotions.icy_poison.id);
					return;
				}
			}
		}

		//		// Nibiru planet
		//		if (entity.worldObj.provider instanceof WorldProviderNibiru)
		//		{
		//			if (!(entity instanceof EntityPlayer))
		//			{
		//				if (entity.ticksExisted % 100 == 0 && (!(entity instanceof IBreathableInfectedGas) || !((IBreathableInfectedGas) entity).canBreathInGas()) && !(entity.getClass() == EntityEvolvedZombie.class || entity.getClass() == EntityEvolvedSpider.class || entity.getClass() == EntityEvolvedSkeleton.class || entity.getClass() == EntityEvolvedCreeper.class || entity.getClass() == EntityEvolvedEnderman.class))
		//				{
		//					PlanetTickEvents livingEvent = new PlanetTickEvents.Pre(entity);
		//					MinecraftForge.EVENT_BUS.post(livingEvent);
		//
		//					if (livingEvent.isCanceled())
		//					{
		//						return;
		//					}
		//					entity.addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 60));
		//					PlanetTickEvents livingEventPost = new PlanetTickEvents.Post(entity);
		//					MinecraftForge.EVENT_BUS.post(livingEventPost);
		//				}
		//			}
		//			else if (entity instanceof EntityPlayer)
		//			{
		//				EntityPlayer player = (EntityPlayer)entity;
		//				InventoryPlayer inventory = player.inventory;
		//
		//				if (entity.ticksExisted % 100 == 0)
		//				{
		//					if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == PolongniusArmorItems.purple_crystal_boots) || !(inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == PolongniusArmorItems.purple_crystal_leggings) || !(inventory.armorInventory[2] != null && inventory.armorInventory[2].getItem() == PolongniusArmorItems.purple_crystal_chestplate) || !(inventory.armorInventory[3] != null && inventory.armorInventory[3].getItem() == PolongniusArmorItems.purple_crystal_helmet))
		//					{
		//						if (!player.capabilities.isCreativeMode)
		//						{
		//							if (entity.ticksExisted % 2000 == 0 && !player.capabilities.isCreativeMode)
		//							{
		//								player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("message.warning.infected.gas")));
		//							}
		//							entity.addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 80));
		//						}
		//					}
		//				}
		//			}
		//		}
		//		// Sirius planet
		//		else if (entity.worldObj.provider instanceof WorldProviderSiriusB)
		//		{
		//			if (!(entity instanceof EntityPlayer))
		//			{
		//				if (entity.ticksExisted % 100 == 0 && (!(entity instanceof ISiriusMob) || !((ISiriusMob) entity).canLivingInSirius()))
		//				{
		//					PlanetTickEvents livingEvent = new PlanetTickEvents.Pre(entity);
		//					MinecraftForge.EVENT_BUS.post(livingEvent);
		//
		//					if (livingEvent.isCanceled())
		//					{
		//						return;
		//					}
		//					entity.setFire(50);
		//					PlanetTickEvents livingEventPost = new PlanetTickEvents.Post(entity);
		//					MinecraftForge.EVENT_BUS.post(livingEventPost);
		//				}
		//			}
		//		}
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		World world = event.world;
		MovingObjectPosition moving = event.target;
		BlockPos pos = moving.getBlockPos();
		Block block = world.getBlockState(pos).getBlock();

		if (block == KapteynBBlocks.frozen_water)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(KapteynBItems.frozen_water_bucket);
			event.setResult(Result.ALLOW);
		}
		else if (block == PolongniusBlocks.cheese_of_milk)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(PolongniusItems.cheese_of_milk_bucket);
			event.setResult(Result.ALLOW);
		}
		else if (block == FronosBlocks.coconut_milk)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(FronosItems.fronos_bucket, 1, 0);
			event.setResult(Result.ALLOW);
		}
		else if (block == FronosBlocks.mineral_water)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(FronosItems.fronos_bucket, 1, 1);
			event.setResult(Result.ALLOW);
		}
		else if (block == FronosBlocks.ovantine)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(FronosItems.fronos_bucket, 1, 2);
			event.setResult(Result.ALLOW);
		}
		else if (block == FronosBlocks.tea)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(FronosItems.fronos_bucket, 1, 3);
			event.setResult(Result.ALLOW);
		}
		else if (block == FronosBlocks.caramel)
		{
			world.setBlockToAir(pos);
			event.result = new ItemStack(FronosItems.fronos_bucket, 1, 4);
			event.setResult(Result.ALLOW);
		}
		else if (block == SiriusBBlocks.sirius_lava)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(SiriusBItems.sirius_lava_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.io_lava)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.io_lava_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.liquid_red_sulfur)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.liquid_red_sulfur_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.liquid_yellow_sulfur)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.liquid_yellow_sulfur_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.liquid_orange_sulfur)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.liquid_orange_sulfur_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.liquid_brown_sulfur)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == IoBlocks.io_black_lava)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				world.setBlockToAir(pos);
				event.result = new ItemStack(IoItems.io_black_lava_bucket);
				event.setResult(Result.ALLOW);
			}
		}
		else if (block == PlutoBlocks.liquid_methane)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				//world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
				//event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
				//event.setResult(Result.ALLOW);
			}
		}
		else if (block == PlutoBlocks.liquid_nitrogen)
		{
			if (event.current.getItem() == Items.bucket)
			{
				event.setCanceled(true);
			}
			else
			{
				//world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
				//event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
				//event.setResult(Result.ALLOW);
			}
		}
		else if (block == MercuryBlocks.dirty_water)
		{
			//world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			//event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
			//event.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public void interactEntityWithDye(EntityInteractEvent event)
	{
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
		Entity entity = event.target;

		if (itemstack != null)
		{
			int meta = itemstack.getItemDamage() & 15;

			if (itemstack.getItem() == Items.dye && meta >= 0)
			{
				EnumDyeColor color = EnumDyeColor.byDyeDamage(meta);

				if (entity instanceof EntityGrappy)
				{
					EntityGrappy grappy = (EntityGrappy)entity;

					if (!grappy.getSheared() && grappy.getFleeceColor() != color)
					{
						grappy.setFleeceColor(color);

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
}