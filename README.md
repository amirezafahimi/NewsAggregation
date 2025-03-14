# News Aggregator App

## Overview
This Android application aggregates top headlines from the US related to Microsoft, Apple, Google, and Tesla. It fetches news articles from the [NewsAPI](https://newsapi.org/) and displays them in a clean and user-friendly interface. The app is built using **Clean Architecture** and incorporates **Paging 3** for efficient data loading and smooth scrolling.

## Features
- Fetches news articles from **yesterday until now**, sorted by newest first.
- Queries are limited to news related to **Microsoft, Apple, Google, and Tesla**.
- Displays headlines, images, a brief summary, query name, and publication date.
- Uses **Material Design principles** for an intuitive UI.
- Implements **Paging 3** for seamless pagination.
- Follows **Clean Architecture** with MVVM pattern.
- Uses **Retrofit** for API integration and **Kotlin Coroutines & Flow** for asynchronous data handling.

## Tech Stack
- **Kotlin**
- **MVVM Architecture**
- **Jetpack Components** (LiveData, ViewModel, Paging 3, Navigation)
- **Retrofit** (for API calls)
- **Kotlin Coroutines & Flow** (for asynchronous operations)
- **Hilt** (for Dependency Injection)
- **Material Design** (for UI/UX)

## API Integration
This app uses the [NewsAPI](https://newsapi.org/) to fetch news articles. The API request follows this format:

```
https://newsapi.org/v2/everything?q={QUERY}&from={YESTERDAY_DATE}&to={TODAY_DATE}&sortBy=publishedAt&page=1&pageSize=20&apiKey=YOUR_API_KEY
```

### Example Request for Microsoft:
```
https://newsapi.org/v2/everything?q=microsoft&from=2024-03-08&to=2024-03-09&sortBy=publishedAt&page=1&pageSize=20&apiKey=YOUR_API_KEY
```

## Architecture
This project follows **Clean Architecture** with a multi-layered approach:
- **Presentation Layer**: Handles UI (Jetpack Compose or XML), ViewModels, and UI state management.
- **Domain Layer**: Contains use cases and business logic.
- **Data Layer**: Includes repositories, data sources, and models.

## Screens
### 1. News Screen
Displays a list of news headlines categorized by the company name.

### 2. Details Screen
Shows full article details when a news item is selected.

## Libraries Used
- **Retrofit** - Networking and API calls
- **Paging 3** - Efficient pagination
- **Hilt** - Dependency Injection
- **Coroutines & Flow** - Asynchronous operations
- **Material Components** - UI design

