
export interface Hobby {
  hobbyId: number; // matches HobbyResponseDto.hobbyId
  name: string;
  category?: string; // optional, if you want to display category
}


/** Represents a geographic location with optional name */
export interface EmbeddedLocation {
  latitude: number;
  longitude: number;
  locationName?: string;
}

/** Represents the response structure for a user from the backend API */
export interface UserResponse {
  userId: number;
  name: string;
  username: string;
  bio?: string;
  gender?: 'MALE' | 'FEMALE' | 'OTHER';
  isProfileComplete?: boolean;
  hobbies?: Hobby[];
  embeddedLocation?: EmbeddedLocation;
}
