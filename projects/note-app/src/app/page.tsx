import NewNoteForm from "./components/NewNoteForm";
import NoteList from "./components/NoteList";
import "../styles/globals.css";
import { getNotes } from "./actions/noteActions";

export default async function Home() {
  // get all notes
  const notes: Note[] = await getNotes();

  return (
    <div className="w-2/3">
      <header className="mb-1">
        <h1>Take a note</h1>
      </header>
      {/* list of notes */}
      <NoteList initialNotes={notes} />
      <NewNoteForm />
    </div>
  );
}
