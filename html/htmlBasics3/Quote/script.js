const quote = document.getElementById("quote");
const author = document.getElementById("author");
const api_url = "https://quotes-api-self.vercel.app/quote";
async function getQuote(url) {
  const response = await fetch(url);
  var data = await response.json();
  quote.innerHTML = data.quote;
  author.innerHTML = data.author;
}

getQuote(api_url);

function tweet() {
  // new full window
  window.open(
    "https://twitter.com/intent/tweet?text=" +
      quote.innerHTML +
      "----- by " +
      author.innerHTML,
    "Tweet Window",
    "width=600, height=300"
  );
}
