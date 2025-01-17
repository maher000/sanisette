# Sanisette App

Sanisette is an Android application built for a technical interview test. It provides a convenient way to locate and view public toilets (sanisettes) in Paris. The app offers two main views: a list view and a map view, allowing users to explore available facilities either as a list of details or as markers on a map.

## Features

1. **List View**
    - Displays a detailed list of public toilets, including:
        - Address
        - Opening hours
        - Manager
        - District information
    - Users can filter the list based on specific districts.

2. **Map View**
    - Shows public toilets as markers on an interactive Google Map.
    - Users can zoom in and out to explore the facilities in various parts of Paris.

3. **Bottom Navigation Bar**
    - Provides easy navigation between the list view and the map view.

## Technologies Used

1. **Kotlin**: The primary programming language for the app.
2. **Dependency Injection**: Implemented using Koin for modular and testable code.
3. **Jetpack Compose**: Used for building the user interface in a modern and declarative style.
4. **Paging 3**: For efficient loading and pagination of large datasets in the list view.
5. **Google Maps Compose**: For rendering the map view and markers.
6. **Coroutines**: For managing asynchronous tasks, including API calls and data processing.
7. **MVVM Architecture**: Ensures a clean separation of concerns using ViewModel and Repository patterns.

## How It Works

1. **Data Source**:
    - The app fetches public toilet data from the OpenData Paris API: https://data.ratp.fr/api/records/1.0/search/?dataset=sanisettesparis2011

2. **List View**:
    - Data is loaded using the Paging 3 library and displayed in a scrollable list.
    - Each item in the list contains detailed information about a public toilet.

3. **Map View**:
    - Uses Google Maps Compose to render markers based on the fetched data.
    - Automatically adjusts the displayed markers based on the current map bounds and zoom level.

4. **Search and Filtering**:
    - Users can search for public toilets by district.
    - The search query is sent to the API, and the results update the list dynamically.

5. **Pagination**:
    - The app implements API pagination to load data incrementally for better performance and user experience.


## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/maher000/sanisette.git
   ```

2. Open the project in Android Studio.

4. Build and run the project on an emulator or a physical device.

## Testing

1. **Unit Tests**:
    - ViewModel tests ensure the correctness of business logic and API interactions.
    - MockK is used for mocking dependencies and verifying interactions.

2. **UI Tests**:
    - Basic UI tests verify the functionality of the list and map views.

3. **Paging Tests**:
    - Tests ensure proper handling of paginated data from the API.

## Usage

1. Launch the app.
2. Use the bottom navigation bar to switch between the list view and the map view.
3. Use the search bar or district filter to narrow down the results.

## Future Enhancements 

1. Add clustering for markers on the map to improve performance and readability when zoomed out.
2. Improve loading sanisettes on the map
3. Implement offline support for viewing public toilet data.
4. Add more unit tests


## Contributions

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## Contact

If you have any questions or need further assistance, feel free to contact the repository owner.

