package tk.anannoyingoose.npc

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit.getPluginManager
import org.bukkit.Bukkit.getServer
import org.bukkit.ChatColor.translateAlternateColorCodes
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import tk.anannoyingoose.npc.listeners.onInteract
import tk.anannoyingoose.npc.npcs.Chief
import java.util.*

class Npc : JavaPlugin() {
    companion object {
        // map of uuids to npc names called npcMap
        val npcMap = mutableMapOf<UUID, String>()

        /** Colorize a string.
         * @param msg The string to colorize.
         * @return The colorized string.
         */

        fun color(msg: String): Component? {
            return Component.text(translateAlternateColorCodes('&', msg))
        }

        fun clr(msg: String): String {
            return translateAlternateColorCodes('&', msg)
        }

        /** viMsg (villager message) is a function that sends a message to a player and makes it look like a villager is saying it
         *
         * @param p the player to send the message to
         * @param msg the message to send
         * @param npc the name of the npc that is sending the message
         * @param delay the delay in ticks before the message is sent (default is 0)
         */

        fun viMsg(p: Player, msg: String, npc: String, delay: Double = 0.0) {
            getPluginManager().getPlugin("Npc")?.let {
                getServer().scheduler.scheduleSyncDelayedTask(it, {
                    p.sendMessage(clr("&8[&6$npc&8] &f$msg"))
                    p.playSound(p.location, "minecraft:entity.villager.yes", 1.0f, 1.0f)
                }, (delay * 20).toLong())
            }

        }

        fun inVoice(p: Player, msg: String,delay: Double = 0.0) {
            getPluginManager().getPlugin("Npc")?.let {
                getServer().scheduler.scheduleSyncDelayedTask(it, {
                    p.sendMessage(clr("You: &a$msg"))
                }, (delay * 20).toLong())
            }

        }

        // delay() waits for a certain amount of time before continuing (not sleep)
        fun delay(task: Any ,delay: Double) {
            getPluginManager().getPlugin("Npc")?.let {
                getServer().scheduler.scheduleSyncDelayedTask(it, {
                    task
                }, (delay * 20).toLong())
            }
        }

    }

    override fun onEnable() {
        loadNpcs()
        registerListeners()

    }
    private fun loadNpcs() {
        server.pluginManager.registerEvents(Chief(), this)
    }
    private fun registerListeners() {
        server.pluginManager.registerEvents(onInteract(), this)
    }
    override fun onDisable() {}
}