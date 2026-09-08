import { Link } from "react-router-dom";
import Header from "../components/Header";
import { useBooks } from "../hooks/useBooks";

function BookListPage() {
  const { data: books, isLoading } = useBooks();

  return (
    <div className="bg-bg min-h-dvh flex flex-col">
      <Header hasBorder />

      <main className="max-w-7xl mx-auto mt-6">
        {isLoading && <p>Loading books...</p>}

        {books && (
          <div className="grid xl:grid-cols-6 lg:grid-cols-5 md:grid-cols-4 sm:grid-cols-3 gap-x-8 gap-y-6">
            {books.map((book) => {
              return (
                <Link key={book.title} to={`/books/${book.id}`}>
                  <img
                    key={book.id}
                    src={book.coverUrl}
                    alt={book.title + " cover"}
                    className="aspect-2/3 max-w-50 outline-amber-300 rounded cursor-pointer hover:scale-105 hover:outline-2 hover: transition-all"
                  />
                </Link>
              );
            })}
          </div>
        )}
      </main>
    </div>
  );
}

export default BookListPage;
