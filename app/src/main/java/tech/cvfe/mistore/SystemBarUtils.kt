package tech.cvfe.mistore

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager

object SystemBarUtils {

    // 获取状态栏高度（返回 dp）
    fun getStatusBarHeightDp(context: Context): Float {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        val px = if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
        return pxToDp(px, context)
    }

    // 获取导航栏高度（返回 dp，兼容 Android 7+）
    fun getNavigationBarHeightDp(context: Context): Float {
        if (!hasNavigationBar(context)) return 0f

        val resourceId =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        val px = if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
        return pxToDp(px, context)
    }

    // 判断是否有导航栏
    fun hasNavigationBar(context: Context): Boolean {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics = windowManager.currentWindowMetrics
            val insets = metrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars())
            insets.bottom > 0
        } else {
            val display = windowManager.defaultDisplay
            val realSize = Point()
            val usableSize = Point()

            display.getRealSize(realSize)
            display.getSize(usableSize)

            realSize.y != usableSize.y
        }
    }

    // 像素转换为 dp
    private fun pxToDp(px: Int, context: Context): Float {
        val density = context.resources.displayMetrics.density
        return px / density
    }
}
