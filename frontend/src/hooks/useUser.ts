import { useQuery } from "@tanstack/react-query";
import { getProfile } from "../api/users";

export function useUser() {
  return useQuery({
    queryKey: ["me"],
    queryFn: getProfile,
    staleTime: 5 * 60 * 1000,
  });
}
