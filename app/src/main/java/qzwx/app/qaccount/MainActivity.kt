package qzwx.app.qaccount

import android.annotation.*
import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.compose.*
import qzwx.app.qaccount.navigation.*
import qzwx.app.qaccount.theme.*

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QDemoTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute in NavRoutes.bottomNavScreens) {
                            BottomNavBar(navController = navController)
                        }
                    }) {
                    Q_NavHost(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


