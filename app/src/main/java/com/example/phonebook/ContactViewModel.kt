package com.example.phonebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactDao = AppDatabase.getDatabase(application).contactDao()

    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactDao.insertContact(contact)
    }

    fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }

    fun updateContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactDao.updateContact(contact)
    }

    fun getContactById(contactId: Int): Flow<Contact> {
        return contactDao.getContactById(contactId)
    }

    fun generateAvatarUrl(name: String): String {
        val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
        return "https://api.multiavatar.com/$encodedName.png"
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactDao.deleteContact(contact)
    }
}
