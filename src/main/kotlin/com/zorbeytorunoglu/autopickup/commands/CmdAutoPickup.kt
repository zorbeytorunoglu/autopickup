package com.zorbeytorunoglu.autopickup.commands

import com.zorbeytorunoglu.autopickup.AutoPickup
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdAutoPickup(private val plugin: AutoPickup): CommandExecutor {

    init {
        plugin.getCommand("autopickup")!!.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (command.name == "autopickup") {

            if (args.isEmpty()) {

                if (sender !is Player) {
                    sender.sendMessage(plugin.getMessageHandler().getOnlyPlayers())
                    return false
                }

                if (!sender.hasPermission("autopickup.toggle")) {
                    sender.sendMessage(plugin.getMessageHandler().getNoPermission())
                    return false
                }

                if (plugin.getAutoPickupEnabled().contains(sender.name)) {
                    plugin.getAutoPickupEnabled().remove(sender.name)
                    sender.sendMessage(plugin.getMessageHandler().getAutoPickupDisabled())
                } else {
                    plugin.getAutoPickupEnabled().add(sender.name)
                    sender.sendMessage(plugin.getMessageHandler().getAutoPickupEnabled())
                }

                return true

            }

            // If you have any ideas about what can be added more, let me know

        }

        return false

    }

}