import React from "react";
import { useTodo } from "../contexts/TodoContext";
import { useState } from "react";

function TodoItem({ todo }) {
  const [isTodoEditable, setIsTodoEditable] = useState(false);
  const [todoMsg, setTodoMsg] = useState(todo.todo);

  const { deleteTodo, updateTodo, toggleComplete } = useTodo();

  const editTodo = () => {
    updateTodo(todo.id, { ...todo, todo: todoMsg });
    setIsTodoEditable(false);
  };

  const toggleCompleted = () => {
    toggleComplete(todo.id);
  };

  return (
    <div
      className={`flex border border-black/10 rounded-lg px-3 py-1.5 gap-x-3 shadow-sm shadow-black duration-300 text-white ${
        todo.completed ? "bg-gray-800" : "bg-gray-700"
      }`}
    >
      <input
        type="checkbox"
        className="cursor-pointer"
        checked={todo.completed}
        onChange={toggleCompleted}
      />
      <input
        type="text"
        className={`border outline-none w-full bg-transparent rounded-lg ${
          isTodoEditable
            ? "border-black px-2 bg-gray-800"
            : "border-transparent"
        } ${todo.completed ? "line-through" : ""}`}
        value={todoMsg}
        onChange={(e) => setTodoMsg(e.target.value)}
        readOnly={!isTodoEditable}
      />

      {!todo.completed && (
        <button
          className="inline-flex w-14 h-8 rounded-lg text-sm border border-black/10 justify-center items-center bg-green-500 hover:bg-green-600 shrink-0 disabled:opacity-50 text-white"
          onClick={() => {
            if (isTodoEditable) return editTodo();
            else setIsTodoEditable((prev) => !prev);
          }}
        >
          {isTodoEditable ? "Save" : "Edit"}
        </button>
      )}
      <button
        className="inline-flex w-14 h-8 rounded-lg text-sm border border-black/10 justify-center items-center bg-red-500 hover:bg-red-600 shrink-0 text-white"
        onClick={() => deleteTodo(todo.id)}
      >
        Delete
      </button>
    </div>
  );
}

export default TodoItem;
