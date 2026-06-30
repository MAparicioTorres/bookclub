import apiClient from "./client";
import type { BookResponse, BookDetailResponse, CreateBookRequest } from "../types/book";

export const getBook = async (id: number): Promise<BookDetailResponse> => {
    const response = await apiClient.get(`/books/${id}`);
    return response.data;
}

export const createBook = async (data: CreateBookRequest): Promise<BookResponse> => {
    const response = await apiClient.post('/books', data);
    return response.data; 
} 