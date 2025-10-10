import fs from "fs";

const randomIndex = (max) => Math.floor(Math.random() * max);

const chooseRandom = (randFn = randomIndex) => (array = [], numItems) => {
    if (!Array.isArray(array)) return [];
    if (array.length <= 1) return array;

    // If numItems is invalid, pick a random size
    if (typeof numItems !== "number" || numItems < 1 || numItems > array.length) {
      numItems = Math.floor(Math.random() * array.length) + 1;
    }

    const result = [];
    const usedIndexes = new Set();

    while (result.length < numItems) {
      const idx = randFn(array.length);
      if (!usedIndexes.has(idx)) {
        usedIndexes.add(idx);
        result.push(array[idx]);
      }
    }

    return result;
  };

const createPrompt = ({ numQuestions = 1, numChoices = 2 } = {}) => {
  const prompts = [];

  for (let i = 1; i <= numQuestions; i++) {
    prompts.push({
      type: "input",
      name: `question-${i}`,
      message: `Enter question ${i}`,
    });

    for (let j = 1; j <= numChoices; j++) {
      prompts.push({
        type: "input",
        name: `question-${i}-choice-${j}`,
        message: `Enter answer choice ${j} for question ${i}`,
      });
    }
  }

  return prompts;
};

const createQuestions = (obj = {}) => {
  const questions = [];

  // Find all question numbers
  const questionNumbers = Object.keys(obj)
    .filter((k) => /^question-\d+$/.test(k))
    .map((k) => k.split("-")[1])
    .sort((a, b) => a - b); // ensure order

  for (let num of questionNumbers) {
    const qText = obj[`question-${num}`] || "";
    const choices = Object.keys(obj)
      .filter((k) => k.startsWith(`question-${num}-choice-`))
      .sort((a, b) => {
        const aNum = parseInt(a.split("-").pop());
        const bNum = parseInt(b.split("-").pop());
        return aNum - bNum;
      })
      .map((k) => obj[k]);

    questions.push({
      type: "list",
      name: `question-${num}`,
      message: qText,
      choices,
    });
  }

  return questions;
};

const readFile = (path) =>
  new Promise((resolve, reject) => {
    fs.readFile(path, (err, data) => (err ? reject(err) : resolve(data)));
  });

const writeFile = (path, data) =>
  new Promise((resolve, reject) => {
    fs.writeFile(path, data, (err) =>
      err ? reject(err) : resolve("File saved successfully")
    );
  });

export { chooseRandom, createPrompt, createQuestions, readFile, writeFile };
