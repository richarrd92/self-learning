import React from "react";
import { useEffect } from "react";
import { useLoaderData } from "react-router-dom";

function Github() {
    const data = useLoaderData(); // this will get preprocessed the data from the loader function

//   const [data, setData] = React.useState([]);
//   useEffect(() => {
//     fetch("https://api.github.com/users/richarrd92")
//       .then((response) => response.json())
//       .then((data) => {
//         console.log(data);
//         setData(data);
//       });
//   }, []);

  return (
    <div className="text-center m-4 bg-orange-700 text-black p-4 gap-4 text-3xl flex justify-center items-center flex-col">
      Public repos: {data.public_repos} <br />
      Bio: {data.bio}
      <img
        src={data.avatar_url}
        width={200}
        alt=""
        className="rounded-full border border-black"
      />
    </div>
  );
}


export default Github;
// loader
export const githubInfoLoader = async () => {
  const response = await fetch("https://api.github.com/users/richarrd92");
  return response.json();
};

