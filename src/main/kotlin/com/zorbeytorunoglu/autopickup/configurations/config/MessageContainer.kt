package com.zorbeytorunoglu.autopickup.configurations.config

import com.zorbeytorunoglu.autopickup.configurations.Resource
import com.zorbeytorunoglu.autopickup.utils.StringUtils

class MessageContainer(private val configResource: Resource) {

    val noPermission: String = StringUtils.hex(configResource.getString("messages.no-permission")!!)
    val autoPickupEnabled: String = StringUtils.hex(configResource.getString("messages.auto-pickup-enabled")!!)
    val onlyPlayers: String = StringUtils.hex(configResource.getString("messages.only-players")!!)
    val autoPickupDisabled: String = StringUtils.hex(configResource.getString("messages.auto-pickup-disabled")!!)

}