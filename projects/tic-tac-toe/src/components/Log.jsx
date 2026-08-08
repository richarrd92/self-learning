export default function Log({ turns }) {
  return (
    <ol id="log">
      {turns.map((turn, index) => (
        <li key={index}>
          {turn.player} played {turn.square.row + 1}, {turn.square.column + 1}
        </li>
      ))}
    </ol>
  );
}
