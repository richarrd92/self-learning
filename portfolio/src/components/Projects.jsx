import React from "react";

const projects = [
  {
    id: 1,
    name: "N/A", // Replace with the actual project name
    technologies: "N/A", // Replace with the technologies used in the project
    image: "N/A", // Replace with the image of the project
    github: "https://github.com/richarrd92", // Replace with the GitHub link for the project
  },
  {
    id: 2,
    name: "N/A",
    technologies: "N/A",
    image: "N/A",
    github: "https://github.com/richarrd92",
  },
  {
    id: 3,
    name: "N/A",
    technologies: "N/A",
    image: "N/A",
    github: "https://github.com/richarrd92",
  },
];

const Projects = () => {
  return (
    <div className="bg-zinc-800 text-white py-20" id="projects">
      <div className="container mx-auto px-8 md:px-16 lg:px-24">
        <h2 className="text-3xl font-bold text-center mb-2">
          {" "}
          Projects
        </h2>
        <p className="text-lg text-gray-400 px-3 text-center mb-5">
          Check out some of my recent personal projects
        </p>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {projects.map((project) => (
            <div
              key={project.id}
              className="bg-zinc-800 border border-gray-700 p-6 rounded-lg hover:border-zinc-600
            "
            >
              <img
                src={project.image}
                alt={project.name}
                className="rounded-lg mb-4 
              w-full h-48 object-cover"
              />
              <h3 className="text-2xl font-bold mb-2">{project.name}</h3>
              <p className="text-gray-400 mb-4">{project.technologies}</p>
              <a
                href={project.github}
                className="inline-block bg-zinc-900  text-white text-lg md:inline hover:bg-zinc-800 px-3 py-2 rounded-md border border-gray-700 hover:border-zinc-600"
                target="_blank"
                rel="noopener noreferrer"
              >
                GitHub
              </a>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Projects;
