import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Path Finder",
  description: "A simple Breadth First Search Path Finder",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="bg-zinc-900 flex justify-center items-center min-h-screen px-4 sm:px-6 md:px-8 lg:px-12 xl:px-16">
        {children}
      </body>
    </html>
  );
}
