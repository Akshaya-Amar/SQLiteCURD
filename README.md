SQLite Demo App
========

### SQLite Demo App showcases the basic functionality of CURD operations using SQLite based on MVC Architectural Pattern

### Responsibilities are well-separated:

* **Model** (**[UserDBHelper](./app/src/main/java/com/example/sqliteapp/model/UserDBHelper.java)**):
  * **Handles database operations.**
  * The **UserDBHelper class** is responsible for handling the **data-related operations**, such as **creating, updating, and querying the database.**
  * The **[UserContract.UserEntry](./app/src/main/java/com/example/sqliteapp/model/UserContract.java)** class defines the **schema of the database**.
    
* **View** (**[UserDataAdapter](./app/src/main/java/com/example/sqliteapp/adapter/UserDataAdapter.java)**):
  * **Manages the RecyclerView and binds data to the UI.**
  * The **UserDataAdapter class** represents the **View** in the MVC pattern.
  * It's responsible for displaying the data in a RecyclerView.
  * The **UserItemsBinding class** is used for view binding.
    
* **Controller** (**[HomeActivity](./app/src/main/java/com/example/sqliteapp/activity/HomeActivity.java)**):
  * **Handles user interactions, communication with the model, and view updation.**
  * The **HomeActivity class** acts as the **Controller** in the MVC pattern. It **handles user interactions (button clicks), communication with the model (UserDBHelper), and updates the view (UserDataAdapter)** accordingly.

**Code structure effectively separates concerns, making it modular and following the MVC pattern. This organization helps maintainability and readability.**
