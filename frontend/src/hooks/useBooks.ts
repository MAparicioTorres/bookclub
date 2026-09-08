import { useQuery } from "@tanstack/react-query";
import { getBook, getBooks } from "../api/books";

export function useBooks() {
  return useQuery({
    queryKey: ["books"],
    queryFn: getBooks,
  });
}

export function useBook(id: number) {
  return useQuery({
    queryKey: ["books", id],
    queryFn: () => getBook(id),
    enabled: !!id,
  });
}
