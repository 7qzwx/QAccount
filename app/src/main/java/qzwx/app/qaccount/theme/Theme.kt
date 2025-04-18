package qzwx.app.qaccount.theme


import android.os.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import com.google.accompanist.systemuicontroller.*

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xff00574A),
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF141C25),
    surface = Color(0xFF222A33),
    onSurface = Color(0xFFF2F2F2),
//    onSurfaceVariant = Color(0xFFF2F2F2),
)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xff48AB93),
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFF2F2F2),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun QDemoTheme(
    darkTheme : Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor : Boolean = false,
    content : @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme                                                      -> DarkColorScheme
        else                                                           -> LightColorScheme
    }
    // 设置系统 状态栏颜色跟背景一致
    val systemUiController = rememberSystemUiController()
    val syscolor = if (isSystemInDarkTheme()) {
        Color(0xFF141C25)
    } else {
        Color(0xFFF2F2F2)
    }
    val darkiconscolor = if (isSystemInDarkTheme()) {
        false
    } else true
    SideEffect {
        systemUiController.setStatusBarColor(
            color = syscolor, // 状态栏
            darkIcons = darkiconscolor, // 根据主题设置图标颜色
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}