package us.kikin.android.gamingbacklog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import us.kikin.android.gamingbacklog.presentation.navigation.NavGraph
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.splashCondition }
        }
        setContent {
            AppTheme {
                Box(modifier = Modifier.safeDrawingPadding().background(color = MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = viewModel.startDestination)
                }
            }
        }
    }
}
