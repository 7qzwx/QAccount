package qzwx.app.qaccount.page.homepage

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*

//topbar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Home_Topbar() {
    TopAppBar(modifier = Modifier.wrapContentSize(),
        colors = TopAppBarDefaults.
                    topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Row {
                Text(
                    text = "默认账本",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Outlined.CalendarMonth,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Outlined.Search,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        })
}
// 统计标签组件
@Composable
internal fun Home_statistics(modifier : Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 4.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatisticsTab(
                title = "本月结余",
                isSelected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )
            Spacer(modifier = Modifier.width(20.dp))
            StatisticsTab(
                title = "净资产",
                isSelected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )
            Spacer(modifier = Modifier.width(20.dp))
            StatisticsTab(
                title = "预算预览",
                isSelected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
        
        // 这里可以根据selectedTab显示对应的内容
        Spacer(modifier = Modifier.height(8.dp))
        when (selectedTab) {
            0 -> Home_JieYuCard()
            1 -> Home_netAssets()
            2 -> Home_BudgetCard()
        }
    }
}
// 统计标签的标签选择组件
@Composable
private fun StatisticsTab(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column{
        Text(
            text = title,
            color = if (isSelected) 
                MaterialTheme.colorScheme.onBackground 
            else 
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = if (isSelected)
                MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            else
                MaterialTheme.typography.titleMedium,
            modifier = Modifier.clickable(onClick = onClick, indication = null, interactionSource = remember { MutableInteractionSource() })
        )
        
        // 底部指示器
        Spacer(modifier = Modifier.height(4.dp))
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(
                            topStart = 1.dp,
                            topEnd = 1.dp
                        )
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}
// 结余卡片
@Composable
private fun Home_JieYuCard() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
            .padding(16.dp)
    ) {
        val (title, data, account, income, spending, incomeNum, spendingNum) = createRefs()
        
        Text(
            "本月结余（元）",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Row(
            modifier = Modifier.constrainAs(data) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        },
             horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.CenterVertically) {
        Text(
            "2025年4月",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,

        )
            Icon(Icons.Outlined.ArrowDropDown, null, tint = Color.Gray)
        }
        
        Text(
            "-66.00",
            style = MaterialTheme.typography.titleLarge,
            color =MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(account) {
                top.linkTo(title.bottom,12.dp)
                start.linkTo(parent.start)
            }
        )
        
        Text(
            "本月收入",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(income) {
                top.linkTo(account.bottom, 12.dp)
                start.linkTo(parent.start)
                bottom.linkTo(incomeNum.top, 4.dp)
            }
        )
        
        Text(
            "0.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF4CAF50),
            modifier = Modifier.constrainAs(incomeNum) {
                top.linkTo(income.bottom, 4.dp)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
        
        Text(
            "本月支出",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(spending) {
                bottom.linkTo(spendingNum.top, 8.dp)
                end.linkTo(parent.end)
            }
        )
        
        Text(
            "￥2,500.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE91E63),
            modifier = Modifier.constrainAs(spendingNum) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}
//净资产卡片
@Composable
private fun Home_netAssets(modifier : Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
            .padding(16.dp)
    ) {
        val (title, account, assets, debt, assetsNum, debtNum) = createRefs()

        Text(
            "本月结余（元）",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            "4500.00",
            style = MaterialTheme.typography.titleLarge,
            color =MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(account) {
                top.linkTo(title.bottom,12.dp)
                start.linkTo(parent.start)
            }
        )

        Text(
            "资产",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(assets) {
                top.linkTo(account.bottom, 12.dp)
                start.linkTo(parent.start)
                bottom.linkTo(assetsNum.top, 4.dp)
            }
        )

        Text(
            "￥12000.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF4CAF50),
            modifier = Modifier.constrainAs(assetsNum) {
                top.linkTo(assets.bottom, 4.dp)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            "负债",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(debt) {
                bottom.linkTo(debtNum.top, 8.dp)
                end.linkTo(parent.end)
            }
        )

        Text(
            "￥122200.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE91E63),
            modifier = Modifier.constrainAs(debtNum) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}
//预算卡片
@Composable
fun Home_BudgetCard(modifier : Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
            .padding(16.dp)
    ) {
        val (title, data, account, allBudget, spent, allBudgetNum, spentNum) = createRefs()

        Text(
            "剩余预算（元）",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Row(
            modifier = Modifier.constrainAs(data) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                "2025年4月",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,

                )
            Icon(Icons.Outlined.ArrowDropDown, null, tint = Color.Gray)
        }

        Text(
            "1800.00",
            style = MaterialTheme.typography.titleLarge,
            color =MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(account) {
                top.linkTo(title.bottom,12.dp)
                start.linkTo(parent.start)
            }
        )

        Text(
            "总预算",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(allBudget) {
                top.linkTo(account.bottom, 12.dp)
                start.linkTo(parent.start)
                bottom.linkTo(allBudgetNum.top, 4.dp)
            }
        )

        Text(
            "￥1500.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF4CAF50),
            modifier = Modifier.constrainAs(allBudgetNum) {
                top.linkTo(allBudget.bottom, 4.dp)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            "已使用",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(spent) {
                bottom.linkTo(spentNum.top, 8.dp)
                end.linkTo(parent.end)
            }
        )

        Text(
            "￥300.00",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE91E63),
            modifier = Modifier.constrainAs(spentNum) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}
@Composable
fun Home_FratureBtn(modifier : Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FeatureButton(
            icon = Icons.Outlined.AutoAwesome,
            title = "自动记账",
            backgroundColor = Color(0xFFFFF8E1)
        )

        FeatureButton(
            icon = Icons.Outlined.Book,
            title = "账本管理",
            backgroundColor = Color(0xFFFCE4EC)
        )

        FeatureButton(
            icon = Icons.Outlined.AccountBalance,
            title = "资产管理",
            backgroundColor = Color(0xFFE3F2FD)
        )

        FeatureButton(
            icon = Icons.Outlined.Timer,
            title = "定时记账",
            backgroundColor = Color(0xFFFBE9E7)
        )

        FeatureButton(
            icon = Icons.Outlined.Calculate,
            title = "预算管理",
            backgroundColor = Color(0xFFFFFDE7)
        )
    }
}

@Composable
private fun FeatureButton(
    icon: ImageVector,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 1.dp,
                    color = backgroundColor.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = getIconColor(backgroundColor),
                modifier = Modifier.size(28.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

// 为每种背景色获取明亮的图标颜色
private fun getIconColor(backgroundColor: Color): Color {
    return when {
        // 黄色背景 - 橙色图标
        backgroundColor == Color(0xFFFFF8E1) -> Color(0xFFF57C00)
        // 粉色背景 - 深粉色图标
        backgroundColor == Color(0xFFFCE4EC) -> Color(0xFFE91E63)
        // 蓝色背景 - 蓝色图标
        backgroundColor == Color(0xFFE3F2FD) -> Color(0xFF1976D2)
        // 橙色背景 - 深橙色图标
        backgroundColor == Color(0xFFFBE9E7) -> Color(0xFFFF5722)
        // 淡黄色背景 - 黄色图标
        backgroundColor == Color(0xFFFFFDE7) -> Color(0xFFFFB300)
        // 默认颜色
        else -> Color(0xFF5E35B1)
    }
}

@Composable
fun Home_BillList(modifier : Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            "最近账单",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        BillItem(
            date = "04-13",
            category = "买菜",
            time = "20:26",
            amount = "-¥66.00",
            isExpense = true,
            income = "¥0.00",
            expense = "¥66.00",
            note = "不计入账户"
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        BillItem(
            date = "04-12",
            category = "工资",
            time = "09:30",
            amount = "+¥5000.00",
            isExpense = false,
            income = "¥5000.00",
            expense = "¥0.00",
            note = "4月工资"
        )
    }
}

@Composable
private fun BillItem(
    date: String,
    category: String,
    time: String,
    amount: String,
    isExpense: Boolean,
    income: String,
    expense: String,
    note: String = ""
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            // 日期和收支总额
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "支",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFE91E63),
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFFCE4EC),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 2.dp
                                )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = expense,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "收",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF4CAF50),
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFE8F5E9),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 2.dp
                                )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = income,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            
            // 分割线
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
            
            // 账单详情
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 图标
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (isExpense) Color(0xFFFCE4EC) else Color(0xFFE8F5E9),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isExpense) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBasket,
                            contentDescription = category,
                            tint = Color(0xFFE91E63),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Payments,
                            contentDescription = category,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                // 类别和时间
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                
                // 金额和备注
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = amount,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isExpense) Color(0xFFE91E63) else Color(0xFF4CAF50)
                    )
                    
                    if (note.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = note,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}