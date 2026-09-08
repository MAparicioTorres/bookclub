import { Link, useNavigate } from "react-router-dom";
import FormButton from "../components/FormButton";
import FormInput from "../components/FormInput";
import { useState } from "react";
import { useRegister } from "../hooks/useAuth";

function RegisterPage() {
  const [formData, setFormData] = useState({
    email: "",
    username: "",
    password: "",
  });

  const navigate = useNavigate();
  const registerMutation = useRegister();

  return (
    <div className="relative min-h-screen bg-bg flex items-center justify-center">
      <div className="flex-col items-center justify-center rounded min-w-md p-8">
        {/* --- Header navigation --- */}
        <header className="flex justify-start items-center">
          <div className="border-b-2 pb-1 border-yellow-300 px-3">
            <Link to="/register" className="font-bold text-yellow-50">
              Register
            </Link>
          </div>

          <div className="border-b-2 px-4 pb-1 border-gray-600">
            <Link
              to="/login"
              className="font-bold text-white opacity-40 hover:opacity-100"
            >
              Login
            </Link>
          </div>
        </header>
        {/* --- Form --- */}
        <section className="mt-4">
          <form
            className="flex flex-col gap-4 "
            onSubmit={(e) => {
              e.preventDefault();
              registerMutation.mutate(formData, {
                onSuccess: () => navigate("/books"),
                onError: (err) => {
                  console.log(err);
                },
              });
            }}
          >
            <FormInput
              label="Email"
              name="email"
              id="email"
              type="email"
              value={formData.email}
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
              isRequired
            />

            <FormInput
              label="Username"
              name="username"
              id="username"
              type="text"
              value={formData.username}
              onChange={(e) =>
                setFormData({ ...formData, username: e.target.value })
              }
              isRequired
            />

            <FormInput
              label="Password"
              name="password"
              id="password"
              type="password"
              value={formData.password}
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
              isRequired
            />

            <FormButton attributes="w-full" type="submit">
              Register
            </FormButton>
          </form>
        </section>
      </div>
    </div>
  );
}

export default RegisterPage;
