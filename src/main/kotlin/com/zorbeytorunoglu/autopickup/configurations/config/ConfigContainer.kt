package com.zorbeytorunoglu.autopickup.configurations.config

import com.zorbeytorunoglu.autopickup.configurations.Resource
import com.zorbeytorunoglu.autopickup.utils.StringUtils

class ConfigContainer(configResource: Resource) {

    val autoPickupEnabled: Boolean
    val autoPickUpBlacklist: List<String>
    val autoPickupEnabledInCreative: Boolean
    val autoPickupInventoryFullWarningEnabled: Boolean
    val autoPickupInventoryFullWarningMessage: String

    init {
        this.autoPickupEnabled=configResource.getBoolean("auto-pickup.enabled")
        this.autoPickUpBlacklist=configResource.getStringList("auto-pickup.blacklist")
        this.autoPickupEnabledInCreative=configResource.getBoolean("auto-pickup.enabled-in-creative")
        this.autoPickupInventoryFullWarningEnabled=configResource.getBoolean("auto-pickup.inventory-full.warning.enabled")
        this.autoPickupInventoryFullWarningMessage=StringUtils.hex(configResource.getString("auto-pickup.inventory-full.warning.message")!!)
    }

}