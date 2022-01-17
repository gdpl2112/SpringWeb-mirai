package io.github.kloping.springwebmirai.service

import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.ConsoleCommandSender
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.util.CoroutineScopeUtils.childScopeContext
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.MessageReceipt
import net.mamoe.mirai.message.data.Message
import kotlin.coroutines.CoroutineContext

/**
 * @author github-kloping
 * @version 1.0
 */

public class SysCommandSender : CommandSender {
    override val name: String
        get() = "SystemCommandSender"
    override val bot: Nothing? get() = null
    override val subject: Nothing? get() = null
    override val user: Nothing? get() = null

    companion object {
        @JvmField
        val INSTANCE = SysCommandSender()
    }

    override suspend fun sendMessage(message: String): MessageReceipt<Contact>? {
        val ls = message.split("[\r\n]".toRegex())
        for (l in ls) {
            println("\u001B[32m$l\u001B[m")
        }
        return null
    }

    override suspend fun sendMessage(message: Message): MessageReceipt<Contact>? {
        println(message.contentToString())
        return null
    }

    override val permitteeId: AbstractPermitteeId.Console = AbstractPermitteeId.Console

    override val coroutineContext: CoroutineContext by lazy { MiraiConsole.childScopeContext(ConsoleCommandSender.NAME) }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return super.toString()
    }
}