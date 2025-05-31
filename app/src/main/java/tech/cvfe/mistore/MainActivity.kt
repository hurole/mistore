package tech.cvfe.mistore

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import tech.cvfe.mistore.ui.theme.MistoreTheme


class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    private var isFullScreen = false
    private var statusBarHeight: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MistoreTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = statusBarHeight.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .systemBarsPadding()
                    ) {
                        MessageList(title = "消息列表")
                        OrientationButton(
                            onTogglePortrait = ::onTogglePortrait,
                            onToggleLandscape = ::onToggleLandscape,
                            onUnsetOrientation = ::onUnsetOrientation,
                            toggleFullScreen = ::toggleFullScreen
                        )
                        Button(
                            onClick = ::openFilePage
                        ) { Text(text = "文件页面") }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        statusBarHeight = SystemBarUtils.getStatusBarHeightDp(this)
        Log.d(TAG, "onResume: statusBarHeight: $statusBarHeight")
    }

    private fun toggleFullScreen() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        if (isFullScreen) {
            Log.d(TAG, "toggleFullScreen: hide")
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
            isFullScreen = false
        } else {
            Log.d(TAG, "toggleFullScreen: show")
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            isFullScreen = true
        }
        val h = SystemBarUtils.getStatusBarHeightDp(context = this)
        val n = SystemBarUtils.getNavigationBarHeightDp(context = this)
        Log.d(TAG, "getStatusBarHeightDp: ${h}-${n}")
    }

    fun onTogglePortrait() {
        Log.d(TAG, "onTogglePortrait: PORTRAIT")
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun onToggleLandscape() {
        Log.d(TAG, "onTogglePortrait: LANDSCAPE")
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun onUnsetOrientation() {
        Log.d(TAG, "onUnsetOrientation: ")
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    fun openFilePage() {
        Log.d(TAG, "openFilePage: ")
        val intent = Intent(this@MainActivity, StorageActivity::class.java)
        startActivity(intent)
    }
}

data class Message(val title: String, val content: String)

@Composable
fun MessageList(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    repeat(20) { _ ->
        MsgCard(
            message = Message(
                "Kotlin 开发",
                "是的我是第几卡号 sd 卡恢复尽快哈卡号副卡收款方好服卡上卡号放假看啥"
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

}

@Composable
fun OrientationButton(
    onToggleLandscape: () -> Unit,
    onTogglePortrait: () -> Unit,
    onUnsetOrientation: () -> Unit,
    toggleFullScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxWidth()
    ) {
        Button(onClick = onToggleLandscape) {
            Text(text = "横屏")
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = onTogglePortrait) {
            Text(text = "竖屏")
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = onUnsetOrientation) {
            Text(text = "复原")
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = toggleFullScreen) {
            Text(text = "全屏")
        }
    }
}

@Composable
fun MsgCard(message: Message) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp, 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "标题",
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column {
                Text(text = message.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(vertical = 1.dp))
                Text(text = message.content, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MistoreTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                MessageList(title = "消息列表")
            }
        }
    }
}