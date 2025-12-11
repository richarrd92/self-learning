import "./index.css";
import axios from "axios";
import Challenges from "./Challenges";
import AddChallenge from "./AddChallenge";

import { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  const [challenges, setChallenges] = useState([]);

  const getChallenges = async () => {
    try {
      const response = await axios.get("http://localhost:8080/challenges");
      setChallenges(response.data);
    } catch (error) {
      console.error("Failed to fetch challenges: ", error);
    }
  };

  useEffect(() => {
    getChallenges();
  }, []);

  const handleAddChallenge = () => {
    getChallenges();
  };

  const challengeElements = challenges.map((challenge) => {
    return (
      <Challenges
        key={challenge.id}
        id={challenge.id}
        challengeMonth={challenge.challengeMonth}
        description={challenge.description}
        // handleAddChallenge={handleAddChallenge}
        setChallenges={setChallenges}
      />
    );
  });

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Monthly challenges</h1>
      <AddChallenge handleAddChallenge={handleAddChallenge} />
      <div className="list-group">{challengeElements}</div>
    </div>
  );
}

export default App;
