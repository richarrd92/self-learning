"use client";

import React, { useState, useEffect, useRef } from "react";
import { CldImage } from "next-cloudinary";

// Define dimensions and aspect ratios
const socialFormats = {
  " 1:1 ": { width: 1080, height: 1080, aspectRatio: "1:1" },
  " 4:5 ": { width: 1080, height: 1350, aspectRatio: "4:5" },
  " 16:9 ": { width: 1200, height: 675, aspectRatio: "16:9" },
  " 3:1 ": { width: 1500, height: 500, aspectRatio: "3:1" },
  " 205:78 ": { width: 820, height: 312, aspectRatio: "205:78" },
};

type SocialFormat = keyof typeof socialFormats;

// Define the SocialShare component
export default function SocialShare() {
  const [uploadedImage, setUploadedImage] = useState<string | null>(null);
  const [selectedFormat, setSelectedFormat] = useState<SocialFormat>(" 1:1 ");
  const [isUploading, setIsUploading] = useState(false);
  const [isTransforming, setIsTransforming] = useState(false);
  const imageRef = useRef<HTMLImageElement>(null);

  useEffect(() => {
    if (uploadedImage) {
      setIsTransforming(true);
    }
  }, [selectedFormat, uploadedImage]);

  // Function to handle file upload
  const handleFileUpload = async (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const file = event.target.files?.[0];
    if (!file) return;
    setIsUploading(true);
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch("/api/image-upload", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) throw new Error("Failed to upload image");

      const data = await response.json();
      setUploadedImage(data.publicId);
    } catch (error) {
      console.log(error);
      alert("Failed to upload image");
    } finally {
      setIsUploading(false);
    }
  };

  // Function to handle image transformation
  const handleDownload = () => {
    if (!imageRef.current) return;

    fetch(imageRef.current.src)
      .then((response) => response.blob())
      .then((blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.href = url;
        link.download = `${selectedFormat
          .replace(/\s+/g, "_")
          .toLowerCase()}.png`;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
        document.body.removeChild(link);
      });
  };

  return (
    <div className="container mx-auto p-4 max-w-4xl">
      <h1 className="text-3xl font-bold mb-3 text-center text-white-500">
        Choose image dimension
      </h1>

      <div className="card">
        <div className="card-body">
          {/* <h2 className="card-title mb-4">Upload an Image</h2> */}
          <div className="form-control">
            {/* <label className="label">
              <span className="label-text text-gray-200">
                Choose an image file
              </span>
            </label> */}
            <input
              type="file"
              onChange={handleFileUpload}
              className="file-input file-input-bordered file-input-primary w-full hover:bg-zinc-800"
            />
          </div>
          {isUploading && (
            <div className="mt-4">
              <progress className="progress progress-primary w-full"></progress>
            </div>
          )}

          {uploadedImage && (
            <div className="mt-6">
              <div className="flex flex-row gap-3">
                <span className="mb-4 text-gray-200">Select Dimension: </span>
                <div className="form-control">
                  <select
                    className="w-full px-4 py-2 border border-gray-700 text-center focus:outline-none rounded-md hover:border-primary translate-y-[-8px]"
                    value={selectedFormat}
                    onChange={(e) =>
                      setSelectedFormat(e.target.value as SocialFormat)
                    }
                  >
                    {Object.keys(socialFormats).map((format) => (
                      <option key={format} value={format}>
                        {format}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              <div className="mt-4">
                <h3 className="text-lg font-semibold mb-2 mb-4">
                  <span className=" bg-primary p-2 rounded-md text-zinc-800">
                    Preview:
                  </span>
                </h3>
                <div className="flex justify-center">
                  {isTransforming && (
                    <div className="absolute inset-0 flex items-center justify-center bg-base-100 bg-opacity-50 z-10">
                      <span className="loading loading-spinner loading-lg"></span>
                    </div>
                  )}
                  <CldImage
                    width={socialFormats[selectedFormat].width}
                    height={socialFormats[selectedFormat].height}
                    src={uploadedImage}
                    sizes="100vw"
                    alt="transformed image"
                    crop="fill"
                    aspectRatio={socialFormats[selectedFormat].aspectRatio}
                    gravity="auto"
                    ref={imageRef}
                    onLoad={() => setIsTransforming(false)}
                  />
                </div>
              </div>

              <div className="card-actions justify-end mt-6">
                <button className="btn btn-primary" onClick={handleDownload}>
                  Download for {selectedFormat}
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
