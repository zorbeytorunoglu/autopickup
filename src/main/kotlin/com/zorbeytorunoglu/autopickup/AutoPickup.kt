package com.zorbeytorunoglu.autopickup

import com.zorbeytorunoglu.autopickup.commands.CmdAutoPickup
import com.zorbeytorunoglu.autopickup.configurations.Resource
import com.zorbeytorunoglu.autopickup.configurations.config.ConfigContainer
import com.zorbeytorunoglu.autopickup.configurations.config.ConfigHandler
import com.zorbeytorunoglu.autopickup.configurations.config.MessageContainer
import com.zorbeytorunoglu.autopickup.configurations.config.MessageHandler
import com.zorbeytorunoglu.autopickup.listeners.Break
import org.bukkit.plugin.java.JavaPlugin

class AutoPickup: JavaPlugin() {

    private lateinit var configResource: Resource
    private lateinit var dataResource: Resource

    private lateinit var configHandler: ConfigHandler
    private lateinit var messageHandler: MessageHandler

    private lateinit var autoPickupEnabled: MutableSet<String>

    override fun onEnable() {

        configResource = Resource(this,"config.yml")
        configResource.load()
        dataResource = Resource(this,"data.yml")
        dataResource.load()

        configHandler = ConfigHandler(ConfigContainer(configResource))
        messageHandler = MessageHandler(MessageContainer(configResource))

        autoPickupEnabled = loadAutoPickupActivePlayers(dataResource)

        CmdAutoPickup(this)
        Break(this)

    }

    override fun onDisable() {

        saveAutoPickupEnabled(autoPickupEnabled,dataResource)

    }

    fun getAutoPickupEnabled(): MutableSet<String> {
        return autoPickupEnabled
    }

    fun getConfigHandler(): ConfigHandler {
        return configHandler
    }

    fun getMessageHandler(): MessageHandler {
        return messageHandler
    }

    private fun loadAutoPickupActivePlayers(dataResource: Resource): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        if (!dataResource.getFile().exists()) return set

        if (!dataResource.isList("auto-pickup.enabled")) return set

        set.addAll(dataResource.getStringList("auto-pickup.enabled"))

        return set

    }

    private fun saveAutoPickupEnabled(autoPickupEnabled: MutableSet<String>, dataResource: Resource) {

        if (autoPickupEnabled.isEmpty()) return

        dataResource.set("auto-pickup.enabled", autoPickupEnabled.toList())
        dataResource.save()

    }

}