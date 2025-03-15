"use client";

import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import VideoCard from "@/components/VideoCard";
// import ImageCard from "@/components/ImageCard";
import { Video, Image } from "@/types";

function Home() {
  const [videos, setVideos] = useState<Video[]>([]); // State for videos
  const [images, setImages] = useState<Image[]>([]); // State for images
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchMedia = useCallback(async () => {
    try {
      // Fetch videos and images from separate API endpoints
      const [videoResponse, imageResponse] = await Promise.all([
        axios.get("/api/videos"),
        axios.get("/api/images"), // Fetch images from a separate API endpoint
      ]);

      // Handle videos
      if (Array.isArray(videoResponse.data)) {
        setVideos(videoResponse.data);
      } else {
        throw new Error("Unexpected video response format");
      }

      // Handle images
      if (Array.isArray(imageResponse.data)) {
        setImages(
          imageResponse.data.map((image) => ({
            ...image,
            size: image.size || 0, // Ensure size is added with a default value if missing
          }))
        );
      } else {
        throw new Error("Unexpected image response format");
      }
    } catch (error) {
      console.error(error);
      setError("Failed to fetch media");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchMedia();
  }, [fetchMedia]);

  // Function to handle image download
  const handleDownload = useCallback(
    (url: string, title: string, extension: string) => {
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", `${title}.${extension}`);
      link.setAttribute("target", "_blank");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
    []
  );

  // if (loading) {
  //   return <div>Loading...</div>;
  // }

  // Render the component
  if (loading) {
    return (
      <div className="flex flex-col items-center gap-4">
        <div className="loader border-t-4 border-primary rounded-full w-16 h-16 animate-spin"></div>
        <p className="text-lg text-gray-300">Loading...</p>
      </div>
    );
  }

  if (error) {
    return <div className="text-red-500">{error}</div>;
  }

  return (
    <div className="container mx-auto p-4">
      {/* <h1 className="text-2xl font-bold mb-4">Media</h1> */}
      {videos.length === 0 && images.length === 0 ? (
        <div className="text-center text-lg text-gray-500">
          No media available
        </div>
      ) : (
        <>
            <h2 className="text-xl font-semibold mb-2"> Videos</h2>
            <p className="text-gray-600 mb-4">Image section coming soon...</p>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
            {videos.map((video) => (
              <VideoCard
                key={video.id}
                video={video}
                onDownload={(url) => handleDownload(url, video.title, "mp4")}
              />
            ))}
          </div>
{/* 
          <h2 className="text-xl font-semibold mb-4">Images</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {images.map((image) => (
              <ImageCard
                key={image.id}
                image={image}
                onDownload={(url) => handleDownload(url, image.title, "jpg")}
              />
            ))}
          </div> */}
        </>
      )}
    </div>
  );
}

export default Home;
