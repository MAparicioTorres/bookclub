import { useMutation } from "@tanstack/react-query";
import { login, register } from "../api/auth";
import type { LoginRequest, RegisterRequest } from "../types/auth";

export function useLogin(){
    return useMutation({
        mutationFn: (data: LoginRequest) => login(data),
        onSuccess: (res) => {
            localStorage.setItem('jwt', res.token);
        }
    });
}

export function useRegister(){
    return useMutation({
        mutationFn: (data: RegisterRequest) => register(data),
        onSuccess: (res) => {
            localStorage.setItem('jwt', res.token);
        }
    })
}