package com.example.phonebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun EditContact(navController: NavController, contactViewModel: ContactViewModel = viewModel(), contactId: Int) {
    val contactState by contactViewModel.getContactById(contactId).collectAsState(initial = null)
    val contact = contactState ?: return Text("Loading...")

    var name by remember { mutableStateOf(contact.name) }
    var surname by remember { mutableStateOf(contact.surname) }
    var phoneNumber by remember { mutableStateOf(contact.phoneNumber) }
    var email by remember { mutableStateOf(contact.email ?: "") }
    var address by remember { mutableStateOf(contact.address ?: "") }
    var imageUrl by remember { mutableStateOf(contact.imageUrl ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Image URL (Optional)") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Surname") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email (Optional)") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address (Optional)") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedContact = contact.copy(
                name = name,
                surname = surname,
                phoneNumber = phoneNumber,
                email = email.ifBlank { null },
                address = address.ifBlank { null },
                imageUrl = imageUrl.ifBlank { null }
            )
            contactViewModel.updateContact(updatedContact)
            navController.popBackStack()
        }) {
            Text("Save Changes")
        }
    }
}
