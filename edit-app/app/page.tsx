"use client";
import { SignUp } from "@clerk/nextjs";
import { dark } from "@clerk/themes";
import { useState, useEffect } from "react";

export default function Home() {
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Simulate loading the SignUp component
    const timeout = setTimeout(() => {
      setIsLoading(false);
    }, 1000); // 1 second

    return () => clearTimeout(timeout);
  }, []);

  return (
    <div className="fixed inset-0 flex justify-center items-center bg-base-200 text-white">
      {isLoading ? (
        // Loading spinner or placeholder
        <div className="flex flex-col items-center gap-4">
          <div className="loader border-t-4 border-primary rounded-full w-16 h-16 animate-spin"></div>
          <p className="text-lg text-gray-300">Loading...</p>
        </div>
      ) : (
        // Main content
        <div className="flex flex-col items-center gap-6">
          <div className="text-center">
            <h1 className="text-5xl md:text-7xl font-extrabold text-primary">
              EDIFY
            </h1>
          </div>
          <div>
            <SignUp
              appearance={{
                baseTheme: dark, // Use dark theme for consistent styling
                elements: {
                  headerTitle: "Sign In to EDIFY", // Custom header title for branding
                  headerSubtitle: "Welcome back! Please sign in to continue",
                  cardBox: `
                    rounded-[20px] 
                    w-full max-w-[550px] 
                    shadow-2xl shadow-zinc-900 
                    border border-zinc-700 
                    bg-base-200 text-white 
                    p-6 
                  `, // Responsive, shadowed, and styled card box
                  header: "text-primary text-2xl font-bold", // Header styling for emphasis
                  input: `
                    bg-zinc-800 text-white border-gray-600 
                    focus:ring-primary focus:border-primary 
                    rounded-md p-2
                  `, // Inputs with a clean look and focus effect
                  button: `
                    bg-primary hover:bg-primary-dark 
                    text-white font-medium py-2 px-4 
                    rounded-md transition-all duration-200
                  `, // Button with hover and focus effects
                  formButtonPrimary: `
                    bg-primary 
                    border border-gray-300 
                    hover:bg-zinc-800 
                    hover:outline-none
                    focus:ring-2 focus:ring-primary focus:ring-opacity-50
                    text-white font-medium py-2 px-4 
                    rounded-md transition-all duration-200 
                    active:bg-primary-dark
                  `, // Button with hover and focus effects
                  footer: "text-gray-400 mt-4 text-sm", // Footer text for additional links/info
                },
              }}
            />
          </div>
        </div>
      )}
    </div>
  );
}
