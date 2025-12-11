import { useState } from "react";
import axios from "axios";

function Challenges({ handleAddChallenge }) {
  const [challengeMonth, setChallengeMonth] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/challenges", {
        challengeMonth,
        description,
      });
      setChallengeMonth("");
      setDescription("");
      handleAddChallenge();
    } catch (error) {
      console.error("Failed to add challenge: ", error);
    }
  };

  return (
    <div className="card my-5">
      <div className="card-header"> Add New challenge</div>
      <div className="card-body">
        <form className="submit-form" onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="month" className="form-label">
              Month
            </label>
            <input
              type="text"
              id="month"
              required
              value={challengeMonth}
              onChange={(e) => setChallengeMonth(e.target.value)}
              className="form-control"
              placeholder="e.g., January"
            ></input>
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Description
            </label>
            <textarea
              type="text"
              id="description"
              required
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="form-control"
              placeholder="Describe the challenge"
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary">
            Add challenge
          </button>
        </form>
      </div>
    </div>
  );
}

export default Challenges;
