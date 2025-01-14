import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faHtml5,
  faCss3Alt,
  faJs,
  faReact,
  faPython,
} from "@fortawesome/free-brands-svg-icons";
import { faDatabase } from "@fortawesome/free-solid-svg-icons";

library.add(faHtml5, faCss3Alt, faJs, faReact, faDatabase, faPython);

const About = () => {
  return (
    <div className="bg-zinc-800 text-white py-20" id="about">
      <div className="container mx-auto px-8 md:px-16 lg:px-24">
        <h2 className="text-3xl font-bold text-center mb-2">About Me</h2>
        <div className="flex flex-col items-center">
          <p className="text-md mt-4 text-gray-400 px-3 indent-8 break-words">
            I am a senior Computer Science student at UMBC with a strong
            foundation in full-stack web development and a passion for creating
            impactful applications. I am proficient in frontend technologies
            such as HTML5, CSS3, Tailwind CSS, JavaScript, and React, as well as
            backend tools like Node.js, MongoDB, and Prisma. My skills include
            API integration, state management with React Hooks, and deploying
            applications on platforms like Netlify and Vercel.
          </p>
          <p className="text-md mt-4 text-gray-400 px-3 indent-8 break-words">
            I am also actively expanding my knowledge in modern web development
            practices, including Git and GitHub, Docker, authentication
            mechanisms, responsive design, and data structures and algorithms
            using Python. Additionally, I am proficient in programming languages
            like C++ and Python.
          </p>
          <div className="mt-5">
            <h3 className="text-l font-semibold text-center  mb-4">
              Technical Skills
            </h3>
            <div className="grid grid-cols-3 sm:grid-cols-6 lg:grid-cols-6 gap-4 text-center">
              <div>
                <FontAwesomeIcon
                  icon={["fab", "html5"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">HTML5</p>
              </div>
              <div>
                <FontAwesomeIcon
                  icon={["fab", "css3-alt"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">CSS3</p>
              </div>
              <div>
                <FontAwesomeIcon
                  icon={["fab", "js"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">JavaScript</p>
              </div>
              <div>
                <FontAwesomeIcon
                  icon={["fab", "react"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">React</p>
              </div>
              <div>
                <FontAwesomeIcon
                  icon={["fas", "database"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">MySQL</p>
              </div>
              <div>
                <FontAwesomeIcon
                  icon={["fab", "python"]}
                  className="text-zinc-600 text-3xl hover:text-white"
                />
                <p className="mt-2 text-gray-400 text-md">Python</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default About;
