import apiClient from "./client";
import type { LoginRequest, LoginResponse, RegisterRequest } from "../types/auth";

export const login = async (data: LoginRequest): Promise<LoginResponse> => {
    const response = await apiClient.post<LoginResponse>('/auth/login', data);
    return response.data;
}

export const register = async (data: RegisterRequest): Promise<LoginResponse> => {
    const response = await apiClient.post('/auth/register', data);
    return response.data;
}