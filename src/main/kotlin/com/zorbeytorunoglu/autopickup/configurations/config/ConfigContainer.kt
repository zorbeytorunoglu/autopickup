package com.zorbeytorunoglu.autopickup.configurations.config

import com.zorbeytorunoglu.autopickup.configurations.Resource

class ConfigContainer(configResource: Resource) {

    val autoPickupEnabled: Boolean
    val autoPickUpBlacklist: List<String>

    init {
        this.autoPickupEnabled=configResource.getBoolean("auto-pickup.enabled")
        this.autoPickUpBlacklist=configResource.getStringList("auto-pickup.blacklist")
    }

}