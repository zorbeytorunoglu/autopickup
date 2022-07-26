package com.zorbeytorunoglu.autopickup.configurations.config

class ConfigHandler(private val configContainer: ConfigContainer) {

    fun getAutoPickUpEnabled(): Boolean {
        return configContainer.autoPickupEnabled
    }

    fun getAutoPickUpBlacklist(): List<String> {
        return configContainer.autoPickUpBlacklist
    }

}