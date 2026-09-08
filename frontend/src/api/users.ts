import type { ProfileResponse } from "../types/auth";
import apiClient from "./client";

export const getProfile = async (): Promise<ProfileResponse> => {
    const response = await apiClient.get("users/me");
    return response.data;
}