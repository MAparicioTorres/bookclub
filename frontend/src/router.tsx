import { createBrowserRouter } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import BookListPage from "./pages/BookListPage";
import BookPage from "./pages/BookPage";
import RequireAuth from "./components/RequireAuth";

export const router = createBrowserRouter([
  {
    element: <RequireAuth />,
    children: [
      { path: "/", element: <BookListPage /> },
      { path: "/books", element: <BookListPage /> },
      { path: "/books/:id", element: <BookPage /> },
    ],
  },

  { path: "/login", element: <LoginPage /> },
  { path: "/register", element: <RegisterPage /> },
]);
