import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import bots.WCCBot

fun main() {
    val log: Logger = LoggerFactory.getLogger("main")

    // Initialize Api Context
    val botSession = DefaultBotSession()

    // Instantiate Telegram Bots API
    val botsApi = TelegramBotsApi(botSession::class.java)

    // Register our bot
    try {
        botsApi.registerBot(WCCBot())

        log.info("Yoda Bot is up \\o/")
    } catch (e: TelegramApiException) {
        e.printStackTrace()

        log.error("Telegram API failure", e)
    }
}