package com.zorbeytorunoglu.autopickup.listeners

import com.zorbeytorunoglu.autopickup.AutoPickup
import com.zorbeytorunoglu.autopickup.utils.StringUtils
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class Break(private val plugin: AutoPickup): Listener {

    init {
        Bukkit.getServer().pluginManager.registerEvents(this,plugin)
    }

    @EventHandler (priority = EventPriority.MONITOR)
    fun blockBreakEvent(event: BlockBreakEvent) {

        if (!plugin.getConfigHandler().getAutoPickUpEnabled()) return

        if (!plugin.getAutoPickupEnabled().contains(event.player.name)) return

        if (!plugin.getConfigHandler().getAutoPickUpEnabledInCreative() &&
                event.player.gameMode == GameMode.CREATIVE) return

        if (event.isCancelled) return

        if (plugin.getConfigHandler().getAutoPickUpBlacklist().contains(event.block.type.toString())) return

        for (item in event.block.drops) {
            if (event.player.inventory.addItem(item).isNotEmpty()) {
                event.block.world.dropItemNaturally(event.block.location,item)
                if (plugin.getConfigHandler().getAutoPickupInventoryFullWarningEnabled()) {
                    event.player.sendMessage(plugin.getConfigHandler().getAutoPickupInventoryFullWarningMessage())
                    if (plugin.getConfigHandler().getAutoPickupInventoryFullWarningSound() != "none") {
                        event.player.playSound(event.player.location,
                            Sound.valueOf(plugin.getConfigHandler().getAutoPickupInventoryFullWarningSound()),
                        1.0F, 1.0F)
                    }
                    continue
                }
            }
        }

        event.player.giveExp(event.expToDrop)

        event.block.type = Material.AIR

    }

}