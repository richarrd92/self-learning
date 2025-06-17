import { useState } from "react";

export default function Player({ initialName, symbol, isActive }) {
  const [isEditing, setIsEditing] = useState(false);
  const [playerName, setPlayerName] = useState(initialName);

  // handle player name edit
  function handleEdit() {
    setIsEditing((editing) => !editing);
  }

  function handleSave(event) {
    setPlayerName(event.target.value);
  }

  let player;
  let editButton;

  if (isEditing) {
    player = (
      <input type="text" required value={playerName} onChange={handleSave} />
    );
    editButton = "Save";
  } else {
    player = <span className="player-name">{playerName} </span>;
    editButton = "Edit";
  }

  return (
    <li className={isActive ? "active" : undefined}>
      <span className="player">
        {player}
        <span className="player-symbol">{symbol}</span>
      </span>
      <button onClick={handleEdit}>{editButton}</button>
    </li>
  );
}
