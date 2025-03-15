import Board from "../board/page";

// Home page
export default function Home() {
  return (
    <div className="flex flex-col justify-center items-center">
      <div className="border border-gray-600 p-3 mb-4  rounded-md text-gray-300 text-gray-300 ">
        <h1 className="text-2xl text-gray-200">
          <span className="">PATH FINDER </span>
        </h1>
      </div>
      <div className="">
        <Board />
      </div>
    </div>
  );
}
