import { databases } from "@/utils/appwrite";
import { ID } from "appwrite";

// create a new note
export async function addNote(content: string): Promise<Note> {
  // create a new note
  const newNote = {
    content: content,
  };

  // create a new note
  const response = await databases.createDocument(
    "notesApp",
    "notes",
    ID.unique(),
    newNote
  );

  // return the new note
  const note = {
    $id: response.$id,
    $createdAt: response.$createdAt,
    content: response.content,
  };

  return note;
}

// update a note
export async function updateNote(
  noteId: string,
  content: string
): Promise<Note> {
  // Validate inputs
  if (!noteId) throw new Error("Invalid note ID.");
  if (typeof content !== "string" || content.trim() === "") {
    throw new Error("Content must be a non-empty string.");
  }

  // Prepare updated note content
  const updatedNote = {
    content: content,
  };

  try {
    // Update the note in the database
    const response = await databases.updateDocument(
      "notesApp",
      "notes",
      noteId,
      updatedNote
    );

    // Return the updated note
    const note: Note = {
      $id: response.$id,
      $createdAt: response.$createdAt,
      content: response.content,
    };

    return note;
  } catch (error) {
    console.error("Error updating note:", error);
    throw new Error("Failed to update the note.");
  }
}

// get all notes
export async function getNotes(): Promise<Note[]> {
  const response = await databases.listDocuments("notesApp", "notes");
  console.log(response.documents);

  // map the response to an array of notes
  const notes: Note[] = response.documents.map((doc) => ({
    $id: doc.$id,
    $createdAt: doc.$createdAt,
    content: doc.content,
  }));

  return notes;
}

// delete a note
export async function deleteNote(noteId: string) {
  await databases.deleteDocument("notesApp", "notes", noteId);
}

// // update a note
// export async function updateNote(noteId: string, content: string) {
//   await databases.updateDocument("notesApp", "notes", noteId, { content });
// }
