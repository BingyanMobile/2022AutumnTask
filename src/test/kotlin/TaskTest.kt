import com.bingyan.Task
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class TaskTest {
    @Test
    fun test() {
        println("运行测试用例")
        val task = Task()
        val folder = File("case")
        folder.list()?.forEach {
            unit(task, it)
        }
    }

    fun unit(task: Task, name: String) {
        val folder = File("case")
        val folder2 = File("verify")
        val folder3 = File("offset")
        val offset = File(folder3, name).readText().toInt(10)
        val result = task.encrypt(File(folder, name), offset)
        val expect = File(folder2, name).readText()
        assertEquals(expect, result)
        val expect2 = File(folder, name).readText()
        val result2 = task.decrypt(
            File(folder2, name),
            expect2.take(20)
        )
        val bestOffset = File(folder3, name).readText().toInt(10)
        assertEquals(expect2, result2.value)
        assert((bestOffset - result2.key) % 26 == 0)
        if (result2.key < -13 || result2.key > 13) {
            System.err.println("[Unit Test]Case $name:Offset not best,expect:$bestOffset,actual:${result2.key}")
        }
    }
}