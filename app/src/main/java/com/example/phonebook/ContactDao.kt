package com.example.phonebook

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>

    @Insert
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContactById(contactId: Int): Flow<Contact>

    @Delete
    suspend fun deleteContact(contact: Contact)
}
