package qzwx.app.qaccount.page.homepage

import android.annotation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier : Modifier = Modifier,contentPadding : PaddingValues = PaddingValues(
        start = 10.dp,
        end = 10.dp
    )
) {
    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            Home_Topbar()
        }) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Home_statistics()
            Spacer(modifier = Modifier.height(16.dp))
            Home_FratureBtn()
            Spacer(modifier = Modifier.height(16.dp))
            Home_BillList()
        }
    }
}

