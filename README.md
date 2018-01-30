# AndroidExercise
Android exercise to fetch a JSON file, parse it and present it in ListView

1. I have followed MVP design pattern for this problem.
2. MvpView - Dummy class that interacts with UI elements.
3. MvpModel - Data store for the JSON data. JSON data is converted into data model.
4. MvpPresenter - Core of the application. Handles all business logic for the application. Use retrofit library to read the JSON file.
5. Picasso library is used to load the image into the ImageView.
6. Note that the downloaded image is not resized to fit ImageView.
