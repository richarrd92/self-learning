import React from "react";
import { useState } from "react";
import { useTodo } from "../contexts/TodoContext";

function TodoForm() {
  const [todo, setTodo] = useState("");
  const { addTodo } = useTodo();
  const add = (e) => {
    e.preventDefault();
    if (!todo) return;
    addTodo({ todo, completed: false });
    setTodo("");
  };

  return (
    <div>
      <form onSubmit={add} className="flex gap-3 ">
        <input
          type="text"
          placeholder="Write todo..."
          value={todo}
          className="w-full border border-black/10 rounded-lg px-3 outline-none duration-150 bg-gray-100 focus:bg-white-20 py-1.5 text-black border border-black"
          onChange={(e) => setTodo(e.target.value)}
        />
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1.5 shrink-0 rounded-lg w-[130px] border border-black"
        >
          Add
        </button>
      </form>
    </div>
  );
}

export default TodoForm;
