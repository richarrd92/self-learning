import React from "react";
import { FaEnvelope, FaMapMarkedAlt, FaPhone } from "react-icons/fa";

const Contact = () => {
  const access_key = import.meta.env.VITE_ACCESS_KEY;

const onSubmit = async (event) => {
  event.preventDefault();
  const formData = new FormData(event.target);

  // Validation
  const name = formData.get("name");
  const email = formData.get("email");
  const message = formData.get("message");

  if (!name || !email || !message) {
    console.error("All fields are required.");
    return;
  }

  formData.append("access_key", access_key);

  const object = Object.fromEntries(formData);
  const json = JSON.stringify(object);

  try {
    const res = await fetch("https://api.web3forms.com/submit", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: json,
    });
    const result = await res.json();

    if (result.success) {
        console.log("Form submitted successfully:", result);
        event.target.reset(); // Reset the form
        push("#home"); // Navigate to the home section
    } else {
      console.error("Form submission error:", result);
    }
  } catch (error) {
    console.error("An error occurred while submitting the form:", error);
  }
};


  return (
    <div className="bg-zinc-800 text-white py-20" id="contact">
      <div className="container mx-auto px-8 md:px-16 lg:px-24">
        <h2 className="text-3xl font-bold text-center mb-12">Contact Me</h2>
        <div className="flex flex-col md:flex-row md:space-x-12">
          <div className="flex-1 mb-6">
            <h3 className="text-l  mb-2">Let's Connect...</h3>
            <p className="text-md text-gray-400  break-words">
              I am open to full-time job opportunities. If you have any
              questions or would like to connect, please feel free to reach out.
            </p>
            <div className="mb-4 mt-4 text-gray-400 text-md">
              <FaEnvelope className="inline-block text-zinc-600 mr-2"></FaEnvelope>
              <a
                href="mailto:richardmaliyetu@gmail.com"
                className="hover:text-white"
              >
                richardmaliyetu@gmail.com
              </a>
            </div>
            <div className="mb-4 text-gray-400 text-md">
              <FaPhone className="inline-block text-zinc-600 mr-2"></FaPhone>
              <span className="hover:text-white">410-979-7940</span>
            </div>
            <div className="mb-4 text-gray-400 text-md">
              <FaMapMarkedAlt className="inline-block text-zinc-600 mr-2"></FaMapMarkedAlt>
              <span className="hover:text-white">Baltimore, Maryland, USA</span>
            </div>
          </div>
          <div className="flex-1 w-full text-md text-gray-400">
            <form className="space-y-4" onSubmit={onSubmit}>
              <div>
                <label htmlFor="name" className="block mb-2 text-white">
                  Name
                </label>
                <input
                  type="text"
                  name="name"
                  className="w-full p-2 rounded bg-zinc-800 border border-gray-700 hover:border-zinc-600 focus:outline-none
                    focus:border-zinc-600"
                  placeholder="Enter You Name"
                />
              </div>
              <div>
                <label htmlFor="emial" className="block mb-2 text-white">
                  Email
                </label>
                <input
                  type="email"
                  name="email"
                  className="w-full p-2 rounded bg-zinc-800 border border-gray-700 hover:border-zinc-600 focus:outline-none
                    focus:border-zinc-600"
                  placeholder="Enter You Email"
                />
              </div>
              <div>
                <label htmlFor="message" className="block mb-2 text-white">
                  Message
                </label>
                <textarea
                  type="text"
                  name="message"
                  className="w-full p-2 rounded bg-zinc-800 border border-gray-700 hover:border-zinc-600 focus:outline-none
                    focus:border-zinc-600"
                  rows="5"
                  placeholder="Enter You Message"
                />
              </div>
              <button
                className="bg-zinc-900  text-white text-lg md:inline hover:bg-zinc-800 px-3 py-2 rounded-md border border-gray-700 hover:border-zinc-600"
                type="submit"
              >
                Send
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Contact;
