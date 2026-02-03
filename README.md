## ShoppingListApp
- A simple shopping list app, that allows you to add Shopping List Items.
- Add Items, Delete Them, Mark them if purchased and edit them.
- Uses MVVM architecture as well as the room database

## Instructions
- To add an item press the + button on the bottom right, you may add the Item Name and quantity
- To increase quantity please press the - and + icons
- To delete hold the item and swipe to any side
- To edit an item simply click on the existing item
- To Mark the item as purchesed you can tap on the chackbox to mark it.

## Architecture
```
┌──────────────────────────────┐
│         Views / UI           │  ← Jetpack Compose Screens
│  (HomeView, AddEditView)     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          ViewModel           │  ← Manages UI state & logic
│     (shopListViewModel)      │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          Repository          │  ← Abstracts data access
│     (shopListRepository)     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Database (Room)         │  ← Local SQLite storage
│    (shopListDao → shopList)  │
└──────────────────────────────┘
```


## Features
1. Simple Utilitarian UI nothing fancy
2. Easy to perform actions
3. Timestamps: To check when the Item was added

## Added Features
1. Users can now increase or decrease quantity directly from home screen
2. Bug Fixed: Users are no longer redirected to empty home screen on adding an empty list with no name

## Future Deliverables
- Integrate Advanced sorting
- Implement Folders, to organise lists into their own category
- Delete All feature
- Undo Action Button
- Location feature: Best possible place to purchase the item at the lowest price from the closest place.

## Assumptions Made / Trade Offs
- Accidental delete may be frequent due to the swipe to delete feature
- We assume the user takes the most ideal gestures

## Problems Faced
- Understanding the fundamental concept of context and what is local context
- The different types of Flow and what it means to update in real time autonomously
- System level core libraries
- Understanding how Navigation actually works and its relation to the view model

## IMPORTANT NOTE

- Critical Update made to handle latest AndroidStudio Otter 2/3
- updated gradle files and toml files other than that a small change in AppBar.kt was made
- please git pull to the latest version on the master branch to use the app
- RAISE ISSUES IF ANY ERROR IS INCURRED

# Updates:

## Implementation of Bug Fixes:

### Problem: 
- On leaving the item name (when adding an item) blank a user faces the issue where they get redirected to the home screen with no changes
 
### Fix:
- We move the nav controller logic into the conditional that takes care of verifying that the textfield is not empty
- We add a new conditional that renders a Toast prompting the user to enter a name and that empyt text fields are not allowed
- With this the user doesn't face unexpected behaviour in regards to empty strings

### Resources used to fix the issues:
- https://stackoverflow.com/questions/63801346/error-composable-invocations-can-only-happen-from-the-context-of-a-composabl
- https://developer.android.com/guide/topics/ui/notifiers/toasts

## Implementation of additional features

### Feature:
- Adding a counter system for the Quantity, for easier increasing and decreasing of quantity value

### Implementation of logic:
- we first visualise the logic of increasing and decreasing as simple changes in state where we increment and decrement by one
- Thus we add lambda functions to our ShopListItem function (onIncrease and onDecrease)
- We then define these functions in the form of the updateShopList method where the viewModel takes care of the states (here we are concerned with quantity)
- we use the item.copy method (due to it being an immutable state) and define a function that increments or decrements by one
- We make sure that the decrease function is not abused (and causes undefined behaviour) by putting it in a conditional that checks if the quantity is greater than one

### Implementation of UI:
- We now need to utilise these functions we have created
- We visualise the UI as a simple piece of text reading "Quantity: - 1 + "
- The - and + can be Icon buttons
- We make sure that the Icon buttons have enough space and are obvious so we align them to be in the middle
- We use the Row Composable to get it right below the item name and center it
- We have 2 Text Composable (one important and one unimportant) then we have two IconButton Composables
- One Text Composable is simply for displaying "Quantity" the other is more important, it's the actual quantity value we need to modify on the fly
- We provide what the IconButtons need to do onClick with the onDecrease and onIncrease lambda functions and code blocks we defined before
- We then arbitrarily choose and Icons we want (we use any imageVector here) for decreasing and increasing, here we used '-' and '+'
- The Text composable directly gets the quantity from shopList data class and converts it to string since Text Composables don't take integer values

### Issues Faced in Development:
- Understanding Kotlins heavy type safety features, most errors are mandated at being solved at compile time
- How to manoeuvre around mutable and immutable states
- Identifying when it's an issue with your code or with a dependancy


### Resources used:
- https://kotlinlang.org/docs/data-classes.html
- https://developer.android.com/develop/ui/compose/text
- https://stackoverflow.com/questions/65665563/android-jetpack-compose-icons-doesnt-contain-some-of-the-material-icons


  

