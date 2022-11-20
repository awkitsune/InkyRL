package icu.awkitsune.inkyrl.systems

import icu.awkitsune.inkyrl.attributes.FungusSpread
import icu.awkitsune.inkyrl.builders.EntityFactory
import icu.awkitsune.inkyrl.extensions.position
import icu.awkitsune.inkyrl.extensions.tryToFindAttribute
import icu.awkitsune.inkyrl.world.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Size3D

object FungusGrowth : BaseBehavior<GameContext>(FungusSpread::class) {

    override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        val world = context.world
        val fungusSpread = entity.tryToFindAttribute(FungusSpread::class)   // 2
        val (spreadCount, maxSpread) = fungusSpread                         // 3
        return if (spreadCount < maxSpread && Math.random() < 0.015) {      // 4
            world.findEmptyLocationWithin(
                offset = entity.position
                    .withRelativeX(-1)
                    .withRelativeY(-1),
                size = Size3D.create(3, 3, 0)
            ).map { emptyLocation ->
                world.addEntity(EntityFactory.newFungus(fungusSpread), emptyLocation)   // 5
                fungusSpread.spreadCount++
            }
            true
        } else false
    }
}
