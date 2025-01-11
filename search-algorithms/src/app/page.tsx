import Board from "../board/page";
import { exportedAlgorithm } from "../board/page";

// Home page
export default function Home() {
  return (
    <div className="">
      <div className="glowing-border mb-4 text-gray-300 text-gray-300">
        <h1 className="text-2xl text-gray-200">
          <span className="">Path Finder - {exportedAlgorithm} search </span>
        </h1>
      </div>
      <div className="">
        <Board />
      </div>
    </div>
  );
}
