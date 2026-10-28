package com.example.always_on_display.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.always_on_display.di.AppContainer
import com.example.always_on_display.di.ViewModelFactory
import com.example.always_on_display.presentation.abstractclock.AbstractClockDetailScreen
import com.example.always_on_display.presentation.abstractclock.AbstractClockSelectionScreen
import com.example.always_on_display.presentation.abstractclock.AbstractClockViewModel
import com.example.always_on_display.presentation.atomic.AtomicClockDetailScreen
import com.example.always_on_display.presentation.atomic.AtomicClockSelectionScreen
import com.example.always_on_display.presentation.atomic.AtomicClockViewModel
import com.example.always_on_display.presentation.digital.DigitalClockPreviewScreen
import com.example.always_on_display.presentation.digital.DigitalClockSelectionScreen
import com.example.always_on_display.presentation.digital.DigitalClockViewModel
import com.example.always_on_display.presentation.display.AlwaysOnDisplayScreen
import com.example.always_on_display.presentation.display.AlwaysOnDisplayViewModel
import com.example.always_on_display.presentation.main.MainScreen
import com.example.always_on_display.presentation.main.MainViewModel
import com.example.always_on_display.presentation.neon.NeonClockDetailScreen
import com.example.always_on_display.presentation.neon.NeonClockSelectionScreen
import com.example.always_on_display.presentation.neon.NeonClockViewModel
import com.example.always_on_display.presentation.permission.PermissionScreen
import com.example.always_on_display.presentation.permission.PermissionViewModel
import com.example.always_on_display.presentation.splash.SplashScreen
import com.example.always_on_display.presentation.splash.SplashViewModel

@Composable
fun AppNavigation(
    appContainer: AppContainer,
    startDestination: String = "splash",
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val factory = ViewModelFactory(appContainer)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("splash") {
            val vm: SplashViewModel = viewModel(factory = factory)
            SplashScreen(
                viewModel = vm,
                onNavigateNext = { isPermissionGranted ->
                    val destination = if (isPermissionGranted) "main" else "permission"
                    navController.navigate(destination) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("permission") {
            val vm: PermissionViewModel = viewModel(factory = factory)
            PermissionScreen(
                viewModel = vm,
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("permission") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            val vm: MainViewModel = viewModel(factory = factory)
            MainScreen(
                viewModel = vm,
                onNavigateToDigital = { navController.navigate("digital_selection") },
                onNavigateToAbstract = { navController.navigate("abstract_selection") },
                onNavigateToNeon = { navController.navigate("neon_selection") },
                onNavigateToAtomic = { navController.navigate("atomic_selection") },
                onNavigateToDisplay = { navController.navigate("always_on_display") },
                onRequestOverlayPermission = { navController.navigate("permission") }
            )
        }

        composable("digital_selection") {
            val vm: DigitalClockViewModel = viewModel(factory = factory)
            DigitalClockSelectionScreen(
                viewModel = vm,
                onSelectStyle = { styleKey ->
                    navController.navigate("digital_preview/$styleKey")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "digital_preview/{styleKey}",
            arguments = listOf(navArgument("styleKey") { type = NavType.StringType })
        ) { backStackEntry ->
            val styleKey = backStackEntry.arguments?.getString("styleKey") ?: "digital1"
            val vm: DigitalClockViewModel = viewModel(factory = factory)
            DigitalClockPreviewScreen(
                styleKey = styleKey,
                viewModel = vm,
                onBack = { navController.popBackStack() },
                onApplied = { navController.navigate("main") { popUpTo("main") { inclusive = true } } },
                onNavigateToDisplay = {
                    navController.navigate("always_on_display?category=DIGITAL&styleKey=$styleKey")
                }
            )
        }

        composable("abstract_selection") {
            val vm: AbstractClockViewModel = viewModel(factory = factory)
            AbstractClockSelectionScreen(
                viewModel = vm,
                onSelectStyle = { styleKey ->
                    navController.navigate("abstract_detail/$styleKey")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "abstract_detail/{styleKey}",
            arguments = listOf(navArgument("styleKey") { type = NavType.StringType })
        ) { backStackEntry ->
            val styleKey = backStackEntry.arguments?.getString("styleKey") ?: "abstract1"
            val vm: AbstractClockViewModel = viewModel(factory = factory)
            AbstractClockDetailScreen(
                styleKey = styleKey,
                viewModel = vm,
                onBack = { navController.popBackStack() },
                onApplied = { navController.navigate("main") { popUpTo("main") { inclusive = true } } },
                onNavigateToDisplay = {
                    navController.navigate("always_on_display?category=ABSTRACT&styleKey=$styleKey")
                }
            )
        }

        composable("neon_selection") {
            val vm: NeonClockViewModel = viewModel(factory = factory)
            NeonClockSelectionScreen(
                viewModel = vm,
                onSelectStyle = { styleKey ->
                    navController.navigate("neon_detail/$styleKey")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "neon_detail/{styleKey}",
            arguments = listOf(navArgument("styleKey") { type = NavType.StringType })
        ) { backStackEntry ->
            val styleKey = backStackEntry.arguments?.getString("styleKey") ?: "neon1"
            val vm: NeonClockViewModel = viewModel(factory = factory)
            NeonClockDetailScreen(
                styleKey = styleKey,
                viewModel = vm,
                onBack = { navController.popBackStack() },
                onApplied = { navController.navigate("main") { popUpTo("main") { inclusive = true } } },
                onNavigateToDisplay = {
                    navController.navigate("always_on_display?category=NEON&styleKey=$styleKey")
                }
            )
        }

        composable("atomic_selection") {
            val vm: AtomicClockViewModel = viewModel(factory = factory)
            AtomicClockSelectionScreen(
                viewModel = vm,
                onSelectStyle = { styleKey ->
                    navController.navigate("atomic_detail/$styleKey")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "atomic_detail/{styleKey}",
            arguments = listOf(navArgument("styleKey") { type = NavType.StringType })
        ) { backStackEntry ->
            val styleKey = backStackEntry.arguments?.getString("styleKey") ?: "atomic1"
            val vm: AtomicClockViewModel = viewModel(factory = factory)
            AtomicClockDetailScreen(
                styleKey = styleKey,
                viewModel = vm,
                onBack = { navController.popBackStack() },
                onApplied = { navController.navigate("main") { popUpTo("main") { inclusive = true } } },
                onNavigateToDisplay = {
                    navController.navigate("always_on_display?category=ATOMIC&styleKey=$styleKey")
                }
            )
        }

        composable(
            route = "always_on_display?category={category}&styleKey={styleKey}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("styleKey") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            val styleKey = backStackEntry.arguments?.getString("styleKey")
            val vm: AlwaysOnDisplayViewModel = viewModel(factory = factory)
            AlwaysOnDisplayScreen(
                viewModel = vm,
                overrideCategory = category,
                overrideStyleKey = styleKey,
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}
