package com.example.phonebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.phonebook.ui.theme.PhonebookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhonebookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "contactList") {
            composable("contactList") { ContactListScreen(navController) }
            composable("addContact") { AddContact(navController) }
            composable("editContact/{contactId}", arguments = listOf(navArgument("contactId") { type = NavType.IntType })) { backStackEntry ->
                val contactId = backStackEntry.arguments?.getInt("contactId") ?: return@composable
                EditContact(navController, contactId = contactId)
            }
    }
}}
