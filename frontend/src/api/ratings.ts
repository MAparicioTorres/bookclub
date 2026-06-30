import apiClient from "./client";
import type { RatingResponse, CreateRatingRequest, UpdateRatingRequest } from "../types/rating";

export const createRating = async (data: CreateRatingRequest): Promise<RatingResponse> => {
    const response = await apiClient.post('/ratings', data);
    return response.data;
}

export const updateRating = async (bookId: number, data: UpdateRatingRequest): Promise<RatingResponse> => {
    const response = await apiClient.put(`ratings/${bookId}`, data);
    return response.data;
}
