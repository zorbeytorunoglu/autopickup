package com.zorbeytorunoglu.autopickup.listeners

import com.zorbeytorunoglu.autopickup.AutoPickup
import org.bukkit.Bukkit
import org.bukkit.Material
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

        if (event.isCancelled) return

        if (plugin.getConfigHandler().getAutoPickUpBlacklist().contains(event.block.type.toString())) return

        for (item in event.block.drops) {
            event.player.inventory.addItem(item)
        }

        event.block.type = Material.AIR

    }

}