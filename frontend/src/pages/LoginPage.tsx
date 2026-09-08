import { Link, useNavigate } from "react-router-dom";
import FormButton from "../components/FormButton";
import FormInput from "../components/FormInput";
import { useState } from "react";
import { useLogin } from "../hooks/useAuth";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();
  const loginMutation = useLogin();

  return (
    <div className="relative min-h-screen bg-bg flex items-center justify-center">
      <div className="flex-col items-center justify-center rounded min-w-md p-8">
        {loginMutation.isError && (
          <div className="flex items-center gap-3 bg-red-900/70 border-l-4 border-red-400 p-3 rounded-t mb-4 animate-fade-in">
            <span className="text-red-200 font-bold text-lg">!</span>
            <p className="flex-1 text-white text-sm">
              {loginMutation.error.response?.data?.message ?? "Network error"}
            </p>
            <button
              onClick={() => loginMutation.reset()}
              className="text-white/60 hover:text-white hover:bg-white/10 rounded px-2 py-1 text-sm cursor-pointer"
            >
              X
            </button>
          </div>
        )}

        <header className="flex justify-start items-center">
          <div className="border-b-2 pb-1 border-gray-600 px-3">
            <Link
              to="/register"
              className="font-bold text-white opacity-40 hover:opacity-100"
            >
              Register
            </Link>
          </div>
          <div className="border-b-2 px-4 pb-1 border-yellow-300">
            <Link to="/login" className="font-bold text-yellow-50">
              Login
            </Link>
          </div>
        </header>

        <section className="mt-4">
          <form
            onSubmit={(e) => {
              e.preventDefault();
              loginMutation.mutate(
                { username, password },
                {
                  onSuccess: () => navigate("/books"),
                  onError: (err) => console.log(err.response?.data),
                },
              );
            }}
            className="flex flex-col gap-4"
          >
            <FormInput
              label="Username"
              name="username"
              id="username"
              type="text"
              isRequired
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />

            <FormInput
              label="Password"
              name="password"
              id="password"
              type="password"
              isRequired
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />

            <FormButton
              attributes="w-full"
              disabled={loginMutation.isPending}
              type="submit"
            >
              Login
            </FormButton>
          </form>
        </section>
      </div>
    </div>
  );
}

export default LoginPage;
