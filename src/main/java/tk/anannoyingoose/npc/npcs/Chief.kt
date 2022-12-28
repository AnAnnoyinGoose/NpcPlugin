@file:Suppress("DEPRECATION")

package tk.anannoyingoose.npc.npcs


import org.bukkit.Bukkit.dispatchCommand
import org.bukkit.Bukkit.getConsoleSender
import org.bukkit.World
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent
import org.bukkit.scheduler.BukkitRunnable
import tk.anannoyingoose.npc.Npc
import tk.anannoyingoose.npc.Npc.Companion.color
import tk.anannoyingoose.npc.Npc.Companion.npcMap
import org.bukkit.plugin.java.JavaPlugin.getPlugin as getPlugin1

class Chief : Listener {
    @EventHandler
    fun onLoad(event: ServerLoadEvent) {
        dispatchCommand(getConsoleSender(), "kill @e[type=!player,type=!armor_stand,type=!end_crystal,type=!pig]")
        val x: Double = -124.5
        val y = 145.0
        val z = 225.5
        val yaw = 110.0F
        val pitch = 0.0F
        val name = "Herald"
        val type: EntityType = EntityType.VILLAGER
        val world: World = getPlugin1(Npc::class.java).server.getWorld("world")!!
        val location = org.bukkit.Location(world, x, y, z, yaw, pitch)
        val entity = world.spawnEntity(location, type) as LivingEntity
        entity.customName(color("&0[&l&6???&r&0]&r"))
        entity.isCustomNameVisible = true
        entity.isInvulnerable = true
        entity.isSilent = true
        entity.isPersistent = true
        entity.setAI(false)
        val uId = entity.uniqueId
        npcMap[uId] = name
        object : BukkitRunnable() {
            override fun run() {
                if (entity.isDead) {
                    npcMap.remove(uId)
                    cancel()
                }
            }
        }.runTaskTimer(getPlugin1(Npc::class.java), 0, 10)
        object : BukkitRunnable() {
            override fun run() {
                // if the player is in a 10 block radius of the npc, make it look at the player and glow
                val nearbyPlayers = entity.getNearbyEntities(5.0, 5.0, 5.0)
                for (player in nearbyPlayers) {
                    entity.isGlowing = player is Player
                }
            }
        }.runTaskTimer(getPlugin1(Npc::class.java), 0, 10)
    }
}
