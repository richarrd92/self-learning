import { projects } from "./projects.js"; // import projects data

// GitHub API Fetch
fetch("https://api.github.com/users/richarrd92")
  .then((res) => res.json())
  .then((data) => {
    document.getElementById("profile-pic").src = data.avatar_url;
    document.getElementById("github-bio").textContent =
      data.bio || "Software developer";
  })
  .catch((err) => console.error("GitHub API error:", err));

// Render Projects
const projectsGrid = document.querySelector(".projects-grid");

projects.forEach((project) => {
  const card = document.createElement("div");
  card.classList.add("project-card", "fade-in");

  card.innerHTML = `
    <div class="project-image" style="background-image: url('${project.image}');"></div>
    <div class="project-content">
      <h3>${project.title}</h3>
      <p>${project.description}</p>
      <a href="${project.github}" target="_blank">Source Code</a>
    </div>
  `;

  projectsGrid.appendChild(card);
});

// Fade-in Scroll Animation
const faders = document.querySelectorAll(".fade-in");
const appearOptions = { threshold: 0.3 };

const appearOnScroll = new IntersectionObserver(function (entries, observer) {
  entries.forEach((entry) => {
    if (!entry.isIntersecting) return;
    entry.target.classList.add("visible");
    observer.unobserve(entry.target);
  });
}, appearOptions);

faders.forEach((fader) => appearOnScroll.observe(fader));

// Highlight Active Section
const navLinks = document.querySelectorAll("nav a");
const sections = document.querySelectorAll("section");

window.addEventListener("scroll", () => {
  let current = "";
  sections.forEach((section) => {
    const sectionTop = section.offsetTop;
    if (pageYOffset >= sectionTop - 100) current = section.getAttribute("id");
  });
  navLinks.forEach((link) => {
    link.classList.remove("active");
    if (link.getAttribute("href").includes(current))
      link.classList.add("active");
  });
});

window.addEventListener("load", () => {
  const loader = document.getElementById("loading-screen");

  // Wait for 1.5s animation to finish before hiding
  setTimeout(() => {
    loader.classList.add("hidden");
  }, 1600); // slightly longer than animation for smooth fade
});

// Initialize EmailJS
emailjs.init("service_v0v8qsv"); // Replace with your EmailJS user ID

const form = document.getElementById("contact-form");
const formMessage = document.getElementById("form-message");

form.addEventListener("submit", function (e) {
  e.preventDefault(); // Prevent default form submission

  emailjs.sendForm("YOUR_SERVICE_ID", "YOUR_TEMPLATE_ID", this).then(
    () => {
      formMessage.style.color = "#58a6ff";
      formMessage.textContent = "Message sent successfully!";
      form.reset();
    },
    (error) => {
      formMessage.style.color = "#ff6b6b";
      formMessage.textContent = "Oops! Something went wrong. Please try again.";
      console.error("EmailJS error:", error);
    }
  );
});
