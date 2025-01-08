// Define the Video interface
export interface Video {
  id: string;
  title: string;
  description: string;
  publicId: string;
  originalSize: number;
  compressedSize: number;
  duration: number;
  createdAt: Date;
  updatedAt: Date;
}

// Define the Image interface
export interface Image {
  id: string;
  title: string;
  description: string;
  publicId: string;
  originalSize: number;
  compressedSize: number;
  width: number;
  height: number;
  size: number;
  createdAt: string;
  updatedAt: Date;
}
