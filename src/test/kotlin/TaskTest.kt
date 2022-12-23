import com.bingyan.Task
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.random.Random

class TaskTest {
//    @Test
//    fun test() {
//        println("运行测试用例")
//        val task = Task()
//        val folder = File("case")
//        folder.list()?.forEach {
//            unit(task,it)
//        }
//    }

    fun unit(task: Task, name: String) {
        val folder = File("case")
        val folder2 = File("verify")
        val folder3 = File("offset")
        val offset = File(folder3, name).readText().toInt(10)
        val result = task.encrypt(File(folder, name), offset)
        val expect = File(folder2, name).readText()
        assertEquals(expect, result)
        val result2 = task.decrypt(
            File(folder2, name),
            expect.take(20)
        )
        val expect2 = File(folder, name).readText()
        val bestOffset = File(folder3, name).readText().toInt(10)
        assertEquals(expect2, result2.value)
        assert((bestOffset - result2.key) % 26 == 0)
        if (result2.key < -13 || result2.key > 13) {
            System.err.println("[Unit Test]Case $name:Offset not best,expect:$bestOffset,actual:${result2.key}")
        }
    }

    @Test
    fun generate() {
        val folder = File("case")
        val folder2 = File("verify")
        val folder3 = File("offset")
        try {
            folder.mkdir()
            folder2.mkdir()
            folder3.mkdir()
        } catch (_: Exception) {
        }
        for (i in 0..100) {
            val file = File(folder, i.toString(10) + ".txt")
            val file2 = File(folder2, i.toString(10) + ".txt")
            val file3 = File(folder3, i.toString(10) + ".txt")
            val content = randomContent()
            val offset = Random.nextInt(-100, 100)
            file.writeText(content)
            file2.writeText(encrypt(content, offset))
            file3.writeText(offset.toString(10))
            println("${i}:${offset}")
        }
    }
}