import React, { useState, useCallback } from "react";
import { getCldImageUrl } from "next-cloudinary";
import { Download, FileUp } from "lucide-react";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import { filesize } from "filesize";

// Initialize dayjs with relativeTime
dayjs.extend(relativeTime);

// Define the Image interface
interface Image {
  publicId: string;
  title: string;
  description: string;
  createdAt: string;
  size: number;
}

// Define the ImageCard component
interface ImageCardProps {
  image: Image;
  onDownload: (url: string, title: string) => void;
}

// Define the ImageCard component
const ImageCard: React.FC<ImageCardProps> = ({ image, onDownload }) => {
  const [isHovered, setIsHovered] = useState(false);

  // Function to get the image URL
  const getImageUrl = useCallback((publicId: string) => {
    return getCldImageUrl({
      src: publicId,
      width: 400,
      height: 300,
      crop: "fill",
      gravity: "auto",
      format: "jpg",
      quality: "auto",
    });
  }, []);

  // Function to format the image size
  const formatSize = useCallback((size: number) => {
    return filesize(size);
  }, []);

  // Render the ImageCard
  return (
    <div
      className="card bg-base-100 shadow-xl hover:shadow-2xl transition-all duration-300"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {/* Hover overlay */}
      <figure className="aspect-video relative">
        <img
          src={getImageUrl(image.publicId)}
          alt={image.title}
          className="w-full h-full object-cover"
        />
      </figure>
      {/* Card content */}
      <div className="card-body p-4">
        <h2 className="card-title text-lg font-bold">{image.title}</h2>
        <p className="text-sm text-base-content opacity-70 mb-4">
          {image.description}
        </p>
        <p className="text-sm text-base-content opacity-70 mb-4">
          Uploaded {dayjs(image.createdAt).fromNow()}
        </p>
        <div className="flex justify-between items-center mt-4">
          <div className="flex items-center">
            <FileUp size={18} className="mr-2 text-primary" />
            <div>
              <div className="font-semibold">Size</div>
              <div>{formatSize(image.size)}</div>
            </div>
          </div>
          <button
            className="btn btn-primary btn-sm"
            onClick={() => onDownload(getImageUrl(image.publicId), image.title)}
          >
            <Download size={16} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default ImageCard;
