import FormButton from "../components/FormButton";
import FormInput from "../components/FormInput";

function LoginPage() {
  return (
    <div className="min-h-screen bg-bg flex items-center justify-center">
      <div className=" flex-col items-center justify-center  rounded min-w-md p-8">
        <header className="flex justify-start items-center">
          <div className="border-b-2 px-4 pb-1 border-yellow-300">
            <a href="/login" className="font-bold text-yellow-50">
              Login
            </a>
          </div>

          <div className="border-b-2 pb-1 border-gray-600 px-3">
            <a
              href="/register"
              className="font-bold text-white opacity-40 hover:opacity-100"
            >
              Register
            </a>
          </div>
        </header>

        <section className="mt-4">
          <form className="flex flex-col gap-4 " action="">
            <FormInput label="Email" name="email" id="email" type="email" />

            <FormInput
              label="Password"
              name="password"
              id="password"
              type="password"
            />

            <FormButton attributes="w-full" type="submit">
              Login
            </FormButton>
          </form>
        </section>
      </div>
    </div>
  );
}

export default LoginPage;
