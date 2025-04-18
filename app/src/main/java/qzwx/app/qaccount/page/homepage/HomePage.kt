package qzwx.app.qaccount.page.homepage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier : Modifier = Modifier,
    contentPadding : PaddingValues = PaddingValues(
        start = 10.dp,
        end = 10.dp
    )
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 顶部栏
        Home_Topbar()
        
        // 内容区域
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Home_statistics()
            Spacer(modifier = Modifier.height(2.dp))
            Home_FratureBtn()
            Spacer(modifier = Modifier.height(2.dp))
            Home_BillList()
        }
    }
}


