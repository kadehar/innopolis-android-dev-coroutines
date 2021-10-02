import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        val channel: Channel<Int> = Channel(UNLIMITED)
        launch {
            var count = 1
            while (true) {
                channel.trySend(count++)
                if (count >= 40) break
            }
            channel.close()
        }
        channel.consumeEach {
            when {
                it % 5 == 0 -> println("5 sec message")
                it % 7 == 0 -> println("7 sec message")
            }
            println(it)
        }
    }
}