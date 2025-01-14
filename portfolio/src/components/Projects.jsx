import React, { useState, useEffect } from "react";
import { FaLinkedin, FaGithub } from "react-icons/fa";

const projects = [
  {
    id: 1,
    name: "Path Finder App",
    technologies: "TypeScript, React, Next.js, Tailwind CSS",
    image: [
      "/path-finder-app/image1.png",
      "/path-finder-app/image2.png",
      "/path-finder-app/image3.png",
      "/path-finder-app/image4.png",
    ],
    github:
      "https://github.com/richarrd92/Web-Development/tree/main/search-algorithms",
  },
  {
    id: 2,
    name: "Edit App",
    technologies: "Next.js, Prisma, NeonDB, Cloudinary, Clerk, Daisy UI",
    image: [
      "/edit-app/image1.png",
      "/edit-app/image2.png",
      "/edit-app/image3.png",
      "/edit-app/image4.png",
    ],
    github: "https://github.com/richarrd92/Web-Development/tree/main/edit-app",
  },
  {
    id: 3,
    name: "Note App",
    technologies: "Appwrite, React, Tailwind CSS",
    image: [
      "/note-app/image1.png",
      "/note-app/image2.png",
      "/note-app/image3.png",
      "/note-app/image4.png",
      "/note-app/image5.png",
    ],
    github: "https://github.com/richarrd92/Web-Development/tree/main/note-app",
  },
];

const Projects = () => {
  const [carouselIndices, setCarouselIndices] = useState(
    projects.map(() => 0) // One index per project
  );

  useEffect(() => {
    const intervals = projects.map((project, idx) =>
      project.image.length > 1
        ? setInterval(() => {
            setCarouselIndices((prev) =>
              prev.map((index, i) =>
                i === idx ? (index + 1) % project.image.length : index
              )
            );
          }, 3000)
        : null
    );

    return () => intervals.forEach((interval) => clearInterval(interval));
  }, []);

  return (
    <div className="bg-zinc-800 text-white py-20" id="projects">
      <div className="container mx-auto px-8 md:px-16 lg:px-24">
        <h2 className="text-3xl font-bold text-center mb-2">Projects</h2>
        <p className="text-lg text-gray-400 px-3 text-center mb-5">
          Check out some of my recent personal projects
        </p>
        <div className="grid grid-cols-1 md:grid-cols-1 lg:grid-cols-3 gap-8">
          {projects.map((project, idx) => (
            <div
              key={project.id}
              className="bg-zinc-800 border border-gray-700 p-6 rounded-lg hover:border-zinc-600"
            >
              {project.image.length > 0 ? (
                <img
                  src={project.image[carouselIndices[idx]]}
                  alt={`${project.name} screenshot ${carouselIndices[idx] + 1}`}
                  className="rounded-lg mb-4 w-full h-60 object-cover border border-gray-700"
                />
              ) : (
                <div className="rounded-lg mb-4 w-full h-60 bg-gray-700 flex items-center justify-center">
                  <span className="text-gray-400">No image available</span>
                </div>
              )}

              <h3 className="text-2xl font-bold mb-2">{project.name}</h3>
              <p className="text-gray-400 mb-4">{project.technologies}</p>
              <div className="bg-zinc-900 px-6 py-2 inline-block rounded-md border border-gray-700 hover:border-zinc-600 hover:bg-zinc-800 text-gray-400 hover:text-white hover:cursor-pointer text-lg">
                <a
                  href={project.github}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <FaGithub />
                </a>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Projects;
