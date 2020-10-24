package mayton.lib.logparser

class KtLogParser {

    fun nextMessage() : Int {
        for (x in 555..600) {
            return x
        }
        return -1
    }

    fun main(args: Array<String>) {
        var res = 0
        do {
            res = nextMessage()
            if (res == -1 ) break
            println(res)
        } while(true)
    }
}