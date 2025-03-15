"use client";
import { useState } from "react";
import { addNote } from "../actions/noteActions";

const NewNoteForm = () => {
  const [content, setContent] = useState("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (content.trim() !== "") {
      await addNote(content);
      setContent("");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="mt-none">
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="Write your note here..."
        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm placeholder:text-gray-400 hover:ring-2 hover:ring-inset hover:ring-green-800 focus:ring-2 focus:ring-inset focus:ring-green-800 text-sm sm:leading-6 min-h-[33px] h-[33px] mb-4 mt-3"
      />

      <button
        type="submit"
        className="cursor-pointer text-white bg-green-800 hover:bg-green-700  focus:outline-none font-medium rounded-lg text-sm px-5 py-2.5 text-center"
      >
        Add Note
      </button>
    </form>
  );
};

export default NewNoteForm;
