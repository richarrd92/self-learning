import React from "react";
import { FaLinkedin, FaGithub } from "react-icons/fa";

const Hero = () => {
  return (
    <div className="bg-zinc-800 text-white py-20 text-center">
      <img
        src="/profile.png"
        alt="Hero image"
        className="mx-auto mb-5 w-48  h-48 rounded-full object-cover border border-gray-700 hover:border-zinc-600"
      />
      <h1 className="text-3xl font-bold">
        <span>Richard Maliyetu</span>
      </h1>
      <p className="text-md mt-2 text-gray-400 px-4 mb-2">
        Aspiring software | web developer
      </p>
      <div className="flex justify-center space-x-4 my-2 md:my-0 text-center mt-3 text-xl">
        <a
          href="https://www.linkedin.com/in/richardmaliyetu"
          className="text-gray-400 hover:text-white"
        >
          <FaLinkedin />
        </a>
        <a
          href="https://github.com/richarrd92"
          className="text-gray-400 hover:text-white"
        >
          <FaGithub />
        </a>
      </div>
      <div className="mt-4 space-x-3">
        <a
          href="/RICHARD_MALIYETU_RESUME.pdf"
          download="RICHARD_MALIYETU_RESUME" 
          className="bg-zinc-900 text-white text-md md:inline px-3 py-2 rounded-md border border-gray-700 hover:border-zinc-600 hover:bg-zinc-800"
        >
          Download CV
        </a>
      </div>
    </div>
  );
};

export default Hero;
