
// Represents the payload sent to the backend to log in a user
export interface LoginResponse {
  token: string;
  userId: number;
  name: string;
  username: string;
}

// Represents the response returned by the backend after successful login
export interface LoginRequest {
  username: string;
  password: string;
}
