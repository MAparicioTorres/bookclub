import { useParams } from "react-router-dom";
import Header from "../components/Header";
import { useBook } from "../hooks/useBooks";

function BookPage() {
  const { id } = useParams();
  const bookId = Number(id);
  const { data: book, isLoading } = useBook(bookId);

  if (!isLoading) console.log(book);

  return (
    <div className="bg-bg min-h-dvh flex flex-col">
      <Header />

      <main className="max-w-7xl mx-auto w-full py-6 px-4">
        {/* Cover */}
        <div className="flex flex-row gap-6 w-full">
          <div className="flex-col justify-center items-center flex">
            <img
              className="object-cover rounded outline-1 outline-gray-500 h-100
            "
              src={book?.coverUrl}
              alt={`${book?.title} cover`}
              loading="lazy"
              decoding="async"
            />
          </div>

          {/*Book Metadata */}
          <section className="flex flex-col flex-1">
            <h1 className="font-serif text-5xl leading-tight tracking-wide text-balance text-white">
              {book?.title}
            </h1>
            <p className="text-white/70 text-lg">{`by ${book?.authors.join(", ")}`}</p>
            <p className="h-full flex justify-start items-end text-white/70">Publish Date: {book?.publishedDate}</p>
          </section>

          {/*Average Rating */}
          <section className="flex items-center justify-center bg-gray-500 ml-auto p-2 size-28 rounded">
            <span className="text-3xl text-bold">9.5</span>
          </section>
        </div>
        <div>
          <p className="text-white mt-2 italic">
            Start Date: {book?.startDate}
          </p>
          <p className="text-white italic">Finish Date: {book?.finishDate} </p>
        </div>
      </main>
    </div>
  );
}

export default BookPage;
