/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.todo;


public class Something
{
	//	@Override
	//	public void explode () {
	//		super.explode ();
	//
	//		// spawn particles
	//		for (int i = 0; i < PARTICLE_COUNT; i++) {
	//			// calculate a point
	//			int x = (this.worldObj.rand.nextInt ((RANGE * 2)) - RANGE);
	//			int y = (this.worldObj.rand.nextInt ((RANGE * 2)) - RANGE);
	//			int z = (this.worldObj.rand.nextInt ((RANGE * 2)) - RANGE);
	//
	//			// spawn particle
	//			this.worldObj.spawnParticle ("spell", (this.posX + x), (this.posY + y), (this.posZ + z), 0, 0.5f, 0);
	//		}
	//
	//		// get all entities in range
	//		List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB (EntityLivingBase.class, this.getExplosionRange ());
	//
	//		// apply potion effect
	//		for (EntityLivingBase entity : entities) {
	//			entity.addPotionEffect (new PotionEffect (Potion.poison.getId (), 600));
	//		}
	//	}

	//	public void explode () {
	//		// remove block
	//		this.worldObj.setBlock (this.xCoord, this.yCoord, this.zCoord, Blocks.air);
	//
	//		// spawn explosion
	//		this.worldObj.newExplosion (null, this.xCoord, this.yCoord, this.zCoord, 2.5f, true, true);
	//
	//		// damage all entities in radius
	//		List<Entity> damageEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity (null, this.getDamageBounds ());
	//
	//		for (Entity entity : damageEntities) {
	//			entity.attackEntityFrom (ExplosivesDamageSource.LANDMINE, 24.0f);
	//		}
	//	}
	//
	//	/**
	//	 * Returns the damage bounds.
	//	 * @return The bounds.
	//	 */
	//	public AxisAlignedBB getDamageBounds () {
	//		return AxisAlignedBB.getBoundingBox ((this.xCoord - 2), (this.yCoord - 2), (this.zCoord - 2), (this.xCoord + 2), (this.yCoord + 2), (this.zCoord + 2));
	//	}
	//
	//	/**
	//	 * Returns the detection bounds.
	//	 * @return The bounds.
	//	 */
	//	public AxisAlignedBB getDetectionBounds () {
	//		return AxisAlignedBB.getBoundingBox (this.xCoord, this.yCoord, this.zCoord, (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1));
	//	}
}