export interface CreateRatingRequest {
  bookId: number;
  rating: number;
}

export interface UpdateRatingRequest {
  rating: number;
}

export interface RatingResponse {
  username: string;
  title: string;
  rating: number;
}
