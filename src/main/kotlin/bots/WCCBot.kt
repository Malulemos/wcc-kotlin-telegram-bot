package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.*
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage
//import org.telegram.telegrambots.meta.api.objects.File

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.media.InputMedia
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAnimation
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto
import org.telegram.telegrambots.meta.exceptions.TelegramApiException



class WCCBot : TelegramLongPollingBot() {

    val welcome = """
        \/menu \- Veja as opções
        """.trimMargin()

    val myMenu = """
        \/start \- onde tudo começa
        \/info \- sobre o projeto
        \/gif \- baby Yoda gif
        """.trimMargin()


    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "YodaBot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "2105413181:AAFExjNJ1Gn98OC7crF2q5v-afqGbPaW3Cw"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName //nome do usuário que mandou a mensagem
        val chatId = update?.message?.chatId.toString() //id do chat que irá receber a mensagem de volta
        val messageCommand = update?.message?.text //comando que o usuário está mandando

        try {

            when(messageCommand) {
                "/start" -> getStart("/start",nameSender, chatId)
                "/info" -> getInfo("/info", chatId)
                "/menu" -> getMenu("/menu", nameSender, chatId)
                "/gif" -> getGif("/gif", nameSender, chatId)
                else -> EmojiParser.parseToUnicode("Não encontrei :confused:")
            }

        } catch (e: TelegramApiException) {
                e.printStackTrace()
        }
    }

    private fun getStart(command: String?, nameSender: String?, chatId: String) {
        if (command == "/start") {
            val sendAudio = SendAudio().apply {
                this.chatId = chatId
                this.audio = InputFile().setMedia(java.io.File("src/main/resources/Star Wars Music Theme.mp3"))
                this.caption =  welcome

                this.parseMode = "MarkdownV2"
            }
            execute(sendAudio)

            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.document = InputFile().setMedia("https://c.tenor.com/3avr97IhcVoAAAAC/the-mandalorian-baby-yoda.gif")
                this.caption = EmojiParser.parseToUnicode("*\n$nameSender, que a força esteja com você* :sparkles:\n")
                this.parseMode = "MarkdownV2"//permite usar o negrito, pular linha
            }
            execute(sendDocument) //executa a variável
        }
    }

    private fun getInfo(command: String?, chatId: String) {
        if (command == "/info"){
            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.caption = EmojiParser.parseToUnicode("Esse projeto foi criado por uma garota bastante curiosa, que adora se desafiar\nNessa aventura intergaláctica ela resolveu aprender Kotlin :rocket:")
                this.document = InputFile().setMedia("https://gifman.net/wp-content/uploads/2019/12/baby-yoda-15.gif")
                this.parseMode = "MarkdownV2"
            }
            execute(sendDocument)//executa a variável
        }
    }

    private fun getMenu(command: String?, nameSender: String?, chatId: String) {
        if (command == "/menu") {
            val sendAudio = SendAudio().apply {
                this.chatId = chatId
                this.audio = InputFile().setMedia(java.io.File("src/main/resources/The Imperial March (Darth Vader's Theme).mp3"))
                this.caption = EmojiParser.parseToUnicode("*\n$nameSender, este é o menu* :sparkles:\n" + myMenu)
                this.parseMode = "MarkdownV2"
            }
            execute(sendAudio)

            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.document = InputFile().setMedia("https://c.tenor.com/zCJeYYjW-E4AAAAC/baby-yoda.gif")
                this.parseMode = "MarkdownV2"
            }
            execute(sendDocument) //executa a variável
        }
    }

    private fun getGif(command: String?, nameSender: String?, chatId: String) { //gostaria de criar uma lista com links de Gifs
        if (command == "/gif") {
            val sendMedia = SendMediaGroup().apply {
                this.chatId = chatId
                this.medias = enviarArquivos() as List<InputMedia>
                //this.caption = EmojiParser.parseToUnicode("$nameSender, gifs para você:green_heart:")
            }
            execute(sendMedia)//executa a variável
        }
    }


        /*val listGif = listOf<InputFile>(
            InputFile("https://gifman.net/wp-content/uploads/2019/12/baby-yoda-15.gif"),
            InputFile("https://c.tenor.com/3avr97IhcVoAAAAC/the-mandalorian-baby-yoda.gif"),
            InputFile("https://c.tenor.com/zCJeYYjW-E4AAAAC/baby-yoda.gif")
        )*/


    private fun enviarArquivos() : List<InputMediaDocument> {
        return listOf(
            InputMediaDocument("https://media4.giphy.com/media/iYVneIXJQ3jdJLkZmM/giphy.gif"),
            InputMediaDocument("https://i.pinimg.com/originals/1b/58/77/1b58772e23bb5b761ded9aa1d1e1d7e4.gif"),
            InputMediaDocument("https://camisetas.com.br/wp-content/uploads/2021/01/baby-yoda.gif"),
            InputMediaDocument("https://i.pinimg.com/originals/f5/23/07/f52307537e9b123e7a96901dc62f4e6d.gif"),
            InputMediaDocument("https://c.tenor.com/65zou-niYIUAAAAd/mandalorian-baby-yoda.gif")
        )
    }

}

/*private fun getMessage(command: String?) = when(command) {
        "/info" -> EmojiParser.parseToUnicode("Esse projeto foi criado por uma garota bastante curiosa :laughing:")
        "/menu" -> myMenu
        "/start" ->""
        else -> EmojiParser.parseToUnicode("Não tem ainda :confused:")
    }*/

/*}*/



