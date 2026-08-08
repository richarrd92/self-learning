"use client";
import { useEffect, useState } from "react";
import { deleteNote, updateNote } from "../actions/noteActions";
import { client } from "@/utils/appwrite";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";
import DeleteIcon from "@mui/icons-material/Delete";

export default function NoteList({ initialNotes }: { initialNotes: Note[] }) {
  const [notes, setNotes] = useState<Note[]>(initialNotes); // list of notes
  const [editStates, setEditStates] = useState<Record<string, boolean>>(
    notes.reduce((acc, note) => ({ ...acc, [note.$id]: false }), {})
  ); // list of edit states
  const [currentContents, setCurrentContents] = useState<
    Record<string, string>
  >(notes.reduce((acc, note) => ({ ...acc, [note.$id]: note.content }), {})); // list of current contents

  useEffect(() => {
    const channel = "databases.notesApp.collections.notes.documents";

    // subscribe to the channel
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

    // unsubscribe
    return () => {
      unsubscribe();
    };
  }, []);

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

  const handleSave = async (noteId: string) => {
    const updatedContent = currentContents[noteId];
    await updateNote(noteId, updatedContent); // Save to database
    toggleEdit(noteId); // Exit edit mode
  };

  const handleDelete = async (noteId: string) => {
    // Remove the note from the UI
    const element = document.getElementById(noteId);

    // // Add a class to the note to animate it out
    if (element) {
      element.classList.add("crossed-out");
    }

    // delete the note from the database
    await deleteNote(noteId);
  };

  return (
    <ul className="mb-1">
      {/* List of notes */}
      {notes.length === 0 && (
        <p className="mb-1">
          You currently have no notes. Add one to get started!
        </p>
      )}

      {notes.map((note) => (
        <li
          key={note.$id}
          className="mb-4 bg-gray-700 border border-gray-500 text-white"
        >
          <div>
            {/* Check if the current note is in edit mode */}
            {editStates[note.$id] ? (
              // Render an input field to allow editing the note content
              <input
                type="text"
                placeholder="Write new content..."
                value={currentContents[note.$id] || ""} // Bind the current content to the input
                onChange={
                  (e) => handleContentChange(note.$id, e.target.value) // Handle changes to the input
                }
                className="w-full p-1 border rounded text-gray-800 border-gray-500 bg-gray-200 outline-none hover:ring-1 hover:ring-black"
              />
            ) : (
              // Render the note content as a paragraph if not in edit mode
              <p id={note.$id} className="text-white text-base">
                {note.content}
              </p>
            )}

            <div className="flex gap-2 mt-2">
              {/* Button to toggle between Edit and Save modes */}
              <button
                className={`cursor-pointer text-white border border-gray-500 ${
                  editStates[note.$id]
                    ? "bg-green-800 hover:bg-green-700" // Green for Save
                    : "bg-gray-800 hover:bg-gray-700" // gray for Edit
                } focus:outline-none font-medium rounded-lg text-sm px-5 py-1 text-center w-full`}
                onClick={
                  () =>
                    editStates[note.$id]
                      ? handleSave(note.$id) // Save changes if in edit mode
                      : toggleEdit(note.$id) // Enable edit mode otherwise
                }
              >
                {/* Show "Save" or "Edit" text based on the mode */}
                {editStates[note.$id] ? "Save" : "Edit"}
                {/* Display the appropriate icon based on the mode */}
                {editStates[note.$id] ? (
                  <SaveIcon fontSize="small" className="ml-2" />
                ) : (
                  <EditIcon fontSize="small" className="ml-2" />
                )}
              </button>

              {/* Button to delete the current note */}
              <button
                className="cursor-pointer flex flex-row text-white bg-red-800 hover:bg-red-700 focus:outline-none font-medium rounded-lg text-sm text-center px-3 py-1 border-gray-500"
                onClick={() => handleDelete(note.$id)} // Call delete handler
              >
                Delete
                <DeleteIcon fontSize="small" className="ml-2" />
              </button>
            </div>
          </div>
        </li>
      ))}
    </ul>
  );
}
