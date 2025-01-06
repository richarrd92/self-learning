import type { Metadata } from "next"

export const metadata: Metadata = {
  title: "Note App",
  description: "Add, edit and delete notes",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="en">
      <head>
      <link
        href="https://fonts.googleapis.com/icon?family=Material+Icons"
        rel="stylesheet"
      />
      </head>
      <body className="w-screen h-auto">{children}</body>
    </html>
  );
}
