package tk.anannoyingoose.npc.listeners

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin.getPlugin
import tk.anannoyingoose.npc.Npc
import tk.anannoyingoose.npc.Npc.Companion.color
import tk.anannoyingoose.npc.Npc.Companion.delay
import tk.anannoyingoose.npc.Npc.Companion.inVoice
import tk.anannoyingoose.npc.Npc.Companion.npcMap
import tk.anannoyingoose.npc.Npc.Companion.viMsg

class onInteract : Listener {
    @EventHandler
    fun onInteract(event: org.bukkit.event.player.PlayerInteractEntityEvent) {
        val player = event.player
        val entity = event.rightClicked
        val npcName = npcMap[entity.uniqueId]
        // add a cooldown to the npc, so it doesn't spam the chat (10 seconds) scoreboard tags

        if (npcName != null && !player.scoreboardTags.contains("npcCooldown")) {
            when (npcName) {
                "Herald" -> {
                    inVoice(player, "H-Hey there, who are you? What is this place?",0.0)
                    delay(entity.customName(color("&0[&l&6Herald&r&0]&r")),2.0)

                    viMsg(player, "Hello, my name is &6Herald&f I am the highest counsel of &0[PLACEHOLDER]&f.", npcName, 1.0)
                    viMsg(player, "You seem disoriented; are you ok? From where are you?", npcName, 2.0)
                    inVoice(player, "I-I don't know, I don't have any recollection of this place nor of myself.",3.2)
                    viMsg(player, "I see, well, I think I can help you with that. In my city there's a healer, a &6[WISHER]&f.", npcName, 4.0)
                    viMsg(player, "I believe he might be able you regain your memories, but his services aren't cheap and it doesn't seem that you have some on yourself..", npcName, 5.0)
                    viMsg(player, "But if you help me I'll pay it for you myself", npcName, 7.0)
                    inVoice(player, "Seems fair... What is it?",8.0)
                    viMsg(player, "I need you to go to the top of the mountain to my dear friend &0[&l&6Frederick&r&0]&r.", npcName, 9.0)
                    viMsg(player, "He's a great warrior and a great friend, but he's been acting strange lately.", npcName, 10.0)
                    viMsg(player, "The terrain is rough and the path is deteriorating also I have heard that some Undeads have made a nest near his home.", npcName, 11.0)
                    viMsg(player, "Could you please check-up on him. Or better yet persuade him to move out?", npcName, 13.0)
                    inVoice(player, "I'll do it, but I need some time to prepare.",14.0)
                    viMsg(player, "Of course, take your time.", npcName, 15.0)
                    viMsg(player, "Oh! And also, here, have this. I believe it will help! Good Luck.. uhh what is your name?", npcName, 17.0)
                    inVoice(player, "Ummm... &6${player.name}&f.",19.0)
                    viMsg(player, "Well then Good Luck ${player.name}.", npcName, 21.0)
                }
            }
            player.addScoreboardTag("npcCooldown")
            object : org.bukkit.scheduler.BukkitRunnable() {
                override fun run() {
                    player.removeScoreboardTag("npcCooldown")
                    println("removed npcCooldown tag")
                }
            }.runTaskLater(getPlugin(Npc::class.java), 200)
        }
    }
}