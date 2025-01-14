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
          <p className="text-md mt-4 text-gray-400 px-3 break-words">
            I am a senior Computer Science student at the University of
            Maryland, Baltimore County (UMBC) graduating in May 2025, with a
            foundation in full-stack web development and a passion for building
            impactful applications. My experience spans frontend technologies,
            including HTML5, CSS3, Tailwind CSS, JavaScript, and React, as well
            as backend tools such as Node.js, MongoDB, and Prisma.
          </p>
          <p className="text-md mt-4 text-gray-400 px-3 break-words">
            I have hands-on experience in API integration, state management with
            React Hooks, and deploying applications to the cloud. My technical
            skills extend to modern web development practices, including version
            control with Git and GitHub, implementing authentication mechanisms,
            designing responsive user interfaces, and solving complex problems
            using data structures and algorithms in Python. Additionally, I am
            proficient in programming languages such as C++ and Python, further
            strengthening my ability to tackle diverse technical challenges.
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
