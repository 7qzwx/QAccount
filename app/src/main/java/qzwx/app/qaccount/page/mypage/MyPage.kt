package qzwx.app.qaccount.page.mypage

import android.content.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.navigation.*

import java.util.concurrent.*
import qzwx.app.qaccount.R
private const val FIRST_LAUNCH_PREF = "first_launch_pref"
private const val FIRST_LAUNCH_DATE = "first_launch_date"

@Composable
fun MyPage(
    navController : NavController,
//    viewModel : TransactionViewModel = hiltViewModel()
) {
    // 获取数据库中的交易记录数量
//    val transactions by viewModel.allTransactions.collectAsState(initial = emptyList())
//    val transactionCount = transactions.size
    // 获取用户首次启动日期
    val context = LocalContext.current
    val firstLaunchDate = getFirstLaunchDate(context)
    val daysCount = calculateDaysSinceFirstLaunch(firstLaunchDate)

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                UserInfoCard(
                    daysCount = daysCount,
                    transactionCount = 520
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "功能集合",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                FunctionGrid(navController)
                Spacer(modifier = Modifier.height(16.dp))
                // 数据管理部分
                Text(
                    text = "数据管理",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                SettingsCard {
                    SettingItem(
                        icon = Icons.Outlined.CloudSync,
                        title = "云同步",
                        trailing = "未开启，数据仅存本地",
                        trailingColor = Color.Red,
                        onClick = { /* 云同步点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.FileDownload,
                        title = "数据备份",
                        onClick = { /* 数据备份点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.FileDownload,
                        title = "导入导出",
                        onClick = { /* 导入导出点击 */ }
                    )
                }
                // 其他设置部分
                Text(
                    text = "其他",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                SettingsCard {
                    SettingItem(
                        icon = Icons.Outlined.Wallpaper,
                        title = "主题外观",
                        onClick = { /* 主题外观点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.Widgets,
                        title = "桌面小部件",
                        onClick = { /* 桌面小部件点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.Help,
                        title = "使用手册",
                        onClick = { /* 使用手册点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.Person,
                        title = "邀请好友",
                        onClick = { /* 邀请好友点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.ChatBubbleOutline,
                        title = "建议与反馈",
                        onClick = { /* 建议与反馈点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.Star,
                        title = "给应用评分",
                        onClick = { /* 给应用评分点击 */ }
                    )
                    Divider()
                    SettingItem(
                        icon = Icons.Outlined.Info,
                        title = "关于我们",
                        onClick = { /* 关于我们点击 */ }
                    )
                }
                // 底部ICP备案信息
                Text(
                    text = "©2025 版权归七种文学所有",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }
}

// 获取首次启动日期，如果不存在则保存当前日期
private fun getFirstLaunchDate(context : Context) : Long {
    val prefs = context.getSharedPreferences(FIRST_LAUNCH_PREF, Context.MODE_PRIVATE)
    val firstLaunchDate = prefs.getLong(FIRST_LAUNCH_DATE, 0)

    if (firstLaunchDate == 0L) {
        // 首次启动，保存当前时间
        val currentTime = System.currentTimeMillis()
        prefs.edit().putLong(FIRST_LAUNCH_DATE, currentTime).apply()
        return currentTime
    }

    return firstLaunchDate
}

// 计算自首次启动以来的天数
private fun calculateDaysSinceFirstLaunch(firstLaunchDate : Long) : Int {
    val currentTime = System.currentTimeMillis()
    val diffMillis = currentTime - firstLaunchDate
    return (TimeUnit.MILLISECONDS.toDays(diffMillis) + 1).toInt() // +1 包括首日
}

@Composable
fun UserInfoCard(
    daysCount : Int,
    transactionCount : Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE0F2F1)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 背景装饰
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                // 左侧装饰植物
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(60.dp)
                ) {
                    // 简化的植物装饰
                    Icon(
                        imageVector = Icons.Outlined.EmojiNature,
                        contentDescription = null,
                        tint = Color(0x334CAF50),
                        modifier = Modifier.size(60.dp)
                    )
                }
                // 右侧装饰植物
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.EmojiNature,
                        contentDescription = null,
                        tint = Color(0x334CAF50),
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            // 用户信息内容
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 用户头像
                Image(
                    painter = painterResource(id = R.drawable.qzwxlogo),
                    contentDescription = "用户头像",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        .border(
                            2.dp,
                            Color.White,
                            CircleShape
                        )
                )

                Spacer(modifier = Modifier.height(8.dp))
                // 用户名
                Text(
                    text = "七种文学",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF4CAF50)
                )

                Spacer(modifier = Modifier.height(8.dp))
                // 记账信息 - 使用动态计算的数据
                Text(
                    text = "坚持记账的第${daysCount}天",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )

                Text(
                    text = "已记录${transactionCount}条账单",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun FunctionGrid(navController : NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        // 第一行功能按钮
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FunctionButton(
                icon = Icons.Outlined.Edit,
                iconTint = Color(0xFF4CAF50),
                text = "自动记账",
                onClick = { navController.navigate("AutoAddCount") }
            )

            FunctionButton(
                icon = Icons.Outlined.DateRange,
                iconTint = Color(0xFF4CAF50),
                text = "周期记账",
                onClick = { /* 处理点击 */ }
            )

            FunctionButton(
                icon = Icons.Outlined.Savings,
                iconTint = Color(0xFF4CAF50),
                text = "愿望清单",
                onClick = { /* 处理点击 */ }
            )

            FunctionButton(
                icon = Icons.Outlined.FavoriteBorder,
                iconTint = Color(0xFF4CAF50),
                text = "分类关键词",
                onClick = { /* 处理点击 */ }
            )
        }

        Divider(color = Color.LightGray.copy(alpha = 0.5f))
        // 第二行功能按钮
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FunctionButton(
                icon = Icons.Outlined.Book,
                iconTint = Color(0xFF4CAF50),
                text = "账本管理",
                onClick = { navController.navigate("AllLedgerPage") }
            )

            FunctionButton(
                icon = Icons.Outlined.LocationOn,
                iconTint = Color(0xFF4CAF50),
                text = "地点管理",
                onClick = { navController.navigate("AllLocationPage") }
            )

            FunctionButton(
                icon = Icons.Outlined.Category,
                iconTint = Color(0xFF4CAF50),
                text = "分类管理",
                onClick = { navController.navigate("EditMenuPage") }
            )

            FunctionButton(
                icon = Icons.Outlined.Settings,
                iconTint = Color(0xFF4CAF50),
                text = "更多设置",
                onClick = { navController.navigate("MoreSettingPage") }
            )
        }
    }
}

@Composable
fun FunctionButton(
    icon : ImageVector,
    iconTint : Color,
    text : String,
    onClick : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,  // 去除点击波纹效果
                onClick = onClick
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = iconTint.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SettingsCard(content : @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            content()
        }
    }
}

@Composable
fun SettingItem(
    icon : ImageVector,
    title : String,
    trailing : String? = null,
    trailingColor : Color = Color.Gray,
    onClick : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,  // 去除点击波纹效果
                onClick = onClick
            )
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧图标
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4CAF50),
            modifier = Modifier.size(24.dp)
        )
        // 标题
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )
        // 可选的尾部文本
        if (trailing != null) {
            Text(
                text = trailing,
                style = MaterialTheme.typography.bodyMedium,
                color = trailingColor,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        // 右箭头
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "下一步",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}



