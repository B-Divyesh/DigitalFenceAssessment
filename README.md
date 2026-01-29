## ShoppingListApp
- A simple shopping list app, that allows you to add Shopping List Items.
- Add Items, Delete Them, Mark them if purchased and edit them.
- Uses MVVM architecture as well as the room database

## Instructions
- To add an item press the + button on the bottom right, you may add the Item Name and quantity
- To delete hold the item and swipe to any side
- To edit an item simply click on the existing item
- To Mark the item as purchesed you can tap on the chackbox to mark it.

## Architecture
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


## Features
1. Simple Utilitarian UI nothing fancy
2. Easy to perform actions
3. Timestamps: To check when the Item was added

## Future Deliverables
- Integrate Advanced sorting
- Implement Folders, to organise lists into their own category
- Delete All feature
- Undo Action Button
- Location feature: Best possible place to purchase the item at the lowest price from the closest place.

## Assumptions Made / Trade Offs
- Accidental delete may be frequent due to the swipe to delete feature
- We assume the user takes the most ideal gestures

## IMPORTANT NOTE

- Critical Update made to handle latest AndroidStudio Otter 2/3
- updated gradle files and toml files other than that a small change in AppBar.kt was made
- please git pull to the latest version on the master branch to use the app
- RAISE ISSUES IF ANY ERROR IS INCURRED
