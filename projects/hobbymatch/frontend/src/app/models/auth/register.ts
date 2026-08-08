
// Represents the payload sent to the backend to register a new user
export interface RegisterRequest {
  name: string;
  password: string;
}

// Represents the response returned by the backend after user registration
export interface RegisterResponse {
  token: string;
  userId: number;
  name: string;
  createdAt: String;
}