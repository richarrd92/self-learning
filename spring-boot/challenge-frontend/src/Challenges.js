import axios from "axios";

function Challenges(props) {
  const handleDelete = async () => {
    try {
        await axios.delete(`http://localhost:8080/challenges/${props.id}`);
      //   props.handleAddChallenge();
      props.setChallenges((prev) =>
        prev.filter((challenge) => challenge.id !== props.id)
      );
    } catch (error) {
      console.error("Failed to delete challenge: ", error);
    }
  };

  return (
    <a
      href="#"
      className="list-group-item list-group-item-action"
      aria-current="true"
    >
      <div className="d-flex w-100 justify-content-between">
        <h5 className="mb-1">{props.challengeMonth}</h5>
        <button className="btn btn-danger btn-md" onClick={handleDelete}>
          Delete
        </button>
      </div>
      <p className="mb-1">{props.description}</p>
    </a>
  );
}

export default Challenges;
