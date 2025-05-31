package tech.cvfe.mistore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.io.File

class StorageActivity : ComponentActivity() {
    companion object {
        const val TAG = "StorageActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                Column(modifier = Modifier.background(Color.Gray)) {
                    Text(text = "文件存储")
                    Button(onClick = ::onWriteInternal) {
                        Text(text = "写入内部存储")
                    }
                    Button(onClick = ::onReadInternal) {
                        Text(text = "读取内部存储")
                    }
                    Button(onClick = ::onWriteExternalPrivate) {
                        Text(text = "写入外部存储")
                    }
                    Button(onClick = ::onReadExternalPrivate) {
                        Text(text = "读取外部存储")
                    }

                    Button(onClick = ::onDeleteExternalPrivate) {
                        Text(text = "删除外部存储")
                    }
                }
            }
        }
    }

    private fun onWriteInternal() {
        val file = File(this.filesDir, "app.txt")
        Log.d(
            TAG,
            String.format("path: %s, isDir: %b", file.path, file.isDirectory)
        )
        file.writeText("内部存储文件写入")
    }

    private fun onReadInternal() {
        val file = File(this.filesDir, "app.txt")

        if (file.exists()) {
            try {
                val result = file.readText(Charsets.UTF_8)
                Log.d(TAG, "read: $result")
            } catch (e: Exception) {
                Log.e(TAG, "读取文件失败", e)
            }
        } else {
            Log.i(TAG, "文件不存在")
        }
    }

    private fun onWriteExternalPrivate() {
        val externalPrivateDir = this.getExternalFilesDir(null)
        val file = File(externalPrivateDir, "app.txt")
        file.writeText("你好，我是外部存储")
        Log.i(TAG, "写入外部存储（应用专属）成功 path:" + file.path)
    }

    private fun onReadExternalPrivate() {
        val dir = this.getExternalFilesDir(null)
        val filename = "app.txt"
        val file = File(dir, filename)
        try {
            val text = file.readText(Charsets.UTF_8)
            Log.i(TAG, "读取外部存储（应用专属）文件成功：$text")
        } catch (e: Exception) {
            Log.e(TAG, "读取外部存储（应用专属）失败", e)
        }
    }

    private fun onDeleteExternalPrivate() {
        val dir = this.getExternalFilesDir(null)
        val filename = "app.txt"
        val file = File(dir, filename)
        val success = file.delete()
        if (success) {
            Log.i(TAG, "删除外部存储（应用专属）文件成功")
        } else {
            Log.e(TAG, "删除外部存储（应用专属）文件失败")
        }
    }

}