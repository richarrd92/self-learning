# Note App

This project is a React-based application for managing a list of notes. The application allows users to view, edit, and delete notes. It features real-time updates via a subscription model, ensuring that any changes to the notes are reflected dynamically.

## Features

### 1. **Real-Time Updates**
- Utilizes a subscription model to listen for changes (create, update, delete) in the notes database.
- Automatically updates the UI to reflect these changes.

### 2. **Edit Notes**
- Users can toggle between viewing and editing a note.
- When editing, a text input field is displayed, allowing users to modify the note's content.
- Changes can be saved to the database.

### 3. **Delete Notes**
- Users can delete notes, which are then removed from both the UI and the database.
- Includes an animation ("crossed-out") for a smooth transition when a note is deleted.

### 4. **Dynamic State Management**
- Manages states for edit mode and note contents using React hooks.
- Efficiently updates only the necessary components on state changes.

## Project Structure

### File Overview
- **`NoteList.tsx`**: Main component for rendering the list of notes and handling user interactions.
- **`noteActions.ts`**: Contains functions for performing database operations (update and delete).
- **`appwrite.js`**: Configures and initializes the Appwrite client for interacting with the database.

### State Management
1. **Notes (`notes`)**: An array of notes managed with `useState`.
2. **Edit States (`editStates`)**: Tracks whether each note is in edit mode.
3. **Current Contents (`currentContents`)**: Stores the current content of each note during editing.

## Core Functionalities

### 1. Subscription to Database Changes
The application subscribes to a specific Appwrite channel:
```javascript
const channel = "databases.notesApp.collections.notes.documents";
const unsubscribe = client.subscribe(channel, (response) => {
  const eventType = response.events[0];
  const changedNote = response.payload as Note;

  if (eventType.includes("create")) {
    setNotes((prevNotes) => [changedNote, ...prevNotes]);
  } else if (eventType.includes("delete")) {
    setNotes((prevNotes) =>
      prevNotes.filter((note) => note.$id !== changedNote.$id)
    );
  } else if (eventType.includes("update")) {
    setNotes((prevNotes) =>
      prevNotes.map((note) =>
        note.$id === changedNote.$id ? changedNote : note
      )
    );
  }
});
```

### 2. Edit Functionality
Toggling between edit and save modes:
```javascript
const toggleEdit = (noteId: string) => {
  setEditStates((prev) => ({
    ...prev,
    [noteId]: !prev[noteId],
  }));
};

const handleContentChange = (noteId: string, newContent: string) => {
  setCurrentContents((prev) => ({
    ...prev,
    [noteId]: newContent,
  }));
};
```

### 3. Delete Functionality
Animation and deletion:
```javascript
const handleDelete = async (noteId: string) => {
  const element = document.getElementById(noteId);
  if (element) {
    element.classList.add("crossed-out");
  }
  await deleteNote(noteId);
};
```

## Styling
- Styled using Tailwind CSS for responsive and modern UI design.
- Includes hover effects and clear visual indicators for edit and delete actions.

## Technologies Used

### Frontend
- React
- TypeScript
- Tailwind CSS

### Backend
- Appwrite for database and real-time subscriptions.

## Possible Future Enhancements
- Add user authentication for personalized note management.
- Enable sorting and searching of notes.
- Improve animations for a smoother user experience.

---

## Screenshots

- **Image 1:**
![plot](./public/image1.png)

- **Image 2:**  
![plot](./public/image2.png)

- **Image 3:**  
![plot](./public/image3.png)

- **Image 4:**  
![plot](./public/image4.png)

- **Image 5:**  
![plot](./public/image5.png)


