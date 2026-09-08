import { useMutation, useQueryClient } from "@tanstack/react-query";
import { login, register } from "../api/auth";
import type {
  LoginRequest,
  LoginResponse,
  RegisterRequest,
} from "../types/auth";
import type { AxiosError } from "axios";

export function useLogin() {
  return useMutation<
    LoginResponse,
    AxiosError<{ message: string }>,
    LoginRequest
  >({
    mutationFn: (data) => login(data),
    onSuccess: (res) => {
      localStorage.setItem("jwt", res.token);
    },
  });
}

export function useRegister() {
  return useMutation<
    LoginResponse,
    AxiosError<{ message: string }>,
    RegisterRequest
  >({
    mutationFn: (data) => register(data),
    onSuccess: (res) => {
      localStorage.setItem("jwt", res.token);
    },
  });
}

export function useLogout() {
  const queryClient = useQueryClient();

  return () => {
    localStorage.removeItem("jwt");
    queryClient.clear();
    window.location.href = "/login";
  };
}
