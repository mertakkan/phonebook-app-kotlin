package com.example.phonebook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ContactListScreen(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    val contacts by contactViewModel.getAllContacts().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("addContact") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Contact")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(contacts) { contact ->
                ContactItem(contact = contact, navController = navController, contactViewModel = contactViewModel)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, navController: NavController, contactViewModel: ContactViewModel) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFEEEEEE))
                .padding(16.dp)
        ) {
            if (contact.imageUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(contact.imageUrl),
                    contentDescription = "Contact Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Name: ${contact.name} ${contact.surname}", maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("Phone: ${contact.phoneNumber}", maxLines = 1, overflow = TextOverflow.Ellipsis)
            contact.email?.let { email -> Text("Email: $email", maxLines = 1, overflow = TextOverflow.Ellipsis)
            contact.address?.let { address -> Text("Address: $address", maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { navController.navigate("editContact/${contact.id}") }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(
                    onClick = {
                        contactViewModel.deleteContact(contact)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}}






