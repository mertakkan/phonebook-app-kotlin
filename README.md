# Phonebook Android App

A modern Kotlin Android application for managing personal contacts with a clean, intuitive interface built using Jetpack Compose.

## Overview

This phonebook app provides a comprehensive contact management solution that allows users to store, view, edit, and delete contact information. The application features a modern Material Design 3 interface with smooth navigation between different screens.

## Key Features

- **Contact Management**: Create, read, update, and delete contacts
- **Contact Information**: Store name, surname, phone number, email, address, and profile images
- **Automatic Avatar Generation**: Generates unique avatar images using the MultiAvatar API based on contact names
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Local Storage**: Uses Room database for persistent data storage
- **Navigation**: Smooth screen transitions using Navigation Compose
- **Image Loading**: Displays contact profile images using Coil library

## Architecture

The app follows modern Android development best practices with:

- **MVVM Architecture**: Uses ViewModel to manage UI-related data and business logic
- **Room Database**: Local SQLite database with type-safe queries
- **Jetpack Compose**: Declarative UI toolkit for building native Android interfaces
- **Navigation Compose**: Type-safe navigation between composable screens
- **Coroutines & Flow**: Asynchronous programming for database operations

## Technical Implementation

### Database Layer
- **Room Database**: Persistent storage with `Contact` entity and `ContactDao` interface
- **Database Version**: Version 2 with destructive migration for development
- **Entity Structure**: Contact model with auto-generated ID and optional fields

### UI Layer
- **MainActivity**: Entry point with navigation setup
- **ContactListScreen**: Displays all contacts in a scrollable list with edit/delete actions
- **AddContact**: Form for creating new contacts with validation
- **EditContact**: Form for modifying existing contact information

### Data Flow
- **ContactViewModel**: Manages contact data operations using coroutines
- **State Management**: Reactive UI updates using StateFlow and Compose state
- **Image Handling**: Automatic avatar generation and URL-based image loading

## Technologies Used

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Room**: Database persistence
- **Navigation Compose**: Screen navigation
- **Material Design 3**: UI design system
- **Coil**: Image loading library
- **Coroutines**: Asynchronous programming
- **MultiAvatar API**: Avatar generation service

## App Structure

The application consists of three main screens:
1. **Contact List**: Shows all saved contacts with profile images and quick actions
2. **Add Contact**: Form to create new contacts with automatic avatar generation
3. **Edit Contact**: Form to modify existing contact information

Each contact can store:
- Name and surname (required)
- Phone number (required)
- Email address (optional)
- Physical address (optional)
- Profile image URL (optional, with automatic avatar fallback)

The app provides a seamless user experience with intuitive navigation, data persistence, and modern Material Design aesthetics.
