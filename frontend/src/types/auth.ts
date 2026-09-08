export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  username: string;
  role: string;
  token: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface ProfileResponse {
  username: string;
  email: string;
  role: string;
  createdAt: string;
}
