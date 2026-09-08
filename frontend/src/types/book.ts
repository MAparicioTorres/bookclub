export interface BookResponse {
  id: number;
  title: string;
  authors: string[];
  genres: string[];
  coverUrl: string;
}

export interface BookDetailResponse {
  id: number;
  title: string;
  authors: string[];
  genres: string[];
  coverUrl: string;
  startDate: string | null;
  finishDate: string | null;
  publishedDate: string;
  avgRating: number | null;
}

export interface CreateBookRequest {
    title: string;
    publishedDate?: string;
    startDate?: string;
    endDate?: string;
    authors: string[];
    genres?: string[];
    coverUrl?: string;
}
