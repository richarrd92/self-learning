import React from "react";

const Navbar = () => {
  return (
    <nav className="bg-zinc-900 text-white px-8 md:px-8 lg:px-16 border-b border-gray-700">
      <div className="container py-2 flex justify-center md:justify-between items-center">
        <div className="text-2xl font-bold hidden md:inline">
            Richard Maliyetu
        </div>
        <div className="space-x-2">
          <a href="#home" className=" hover:bg-zinc-800 py-2 px-3 rounded-md">
            Home
          </a>
          <a href="#about" className=" hover:bg-zinc-800 py-2 px-3 rounded-md">
            About
          </a>
          <a
            href="#projects"
            className=" hover:bg-zinc-800 py-2 px-3 rounded-md"
          >
            Projects
          </a>
          <a
            href="#contact"
            className=" hover:bg-zinc-800 py-2 px-3 rounded-md"
          >
            Contact
          </a>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
