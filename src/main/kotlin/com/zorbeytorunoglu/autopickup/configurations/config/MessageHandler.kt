package com.zorbeytorunoglu.autopickup.configurations.config

class MessageHandler(private val messageContainer: MessageContainer) {

    fun getNoPermission(): String {
        return messageContainer.noPermission
    }

    fun getAutoPickupEnabled(): String {
        return messageContainer.autoPickupEnabled
    }

    fun getOnlyPlayers(): String {
        return messageContainer.onlyPlayers
    }

    fun getAutoPickupDisabled(): String {
        return messageContainer.autoPickupDisabled
    }

}