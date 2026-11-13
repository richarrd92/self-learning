const canvas = document.getElementById("canvas1");
const ctx = canvas.getContext("2d");
const CANVAS_WIDTH = (canvas.width = 1000);
const CANVAS_HEIGHT = (canvas.height = 800);

// default image
const defaultImage = new Image();
defaultImage.src = "proj2_sprite_skel.png";
const defaultImageX = 0;
const defaultImageY = 0;

// Foward Facing Sprite Characters
const characterSet1 = new Image();
characterSet1.src = "proj2_sprite_skel.png";

// Back Facing Sprite Characters
const characterSet2 = new Image();
characterSet2.src = "proj2_sprite_skel.png";
let characterSet2X = 1015;
// const characterSet2Y = 0;

// Right Facing Sprite Characters
const characterSet3 = new Image();
characterSet3.src = "proj2_sprite_skel.png";
let characterSet3Y = 310;
let characterSet3W = 180;

// Left Facing Sprite Characters
const characterSet4 = new Image();
characterSet4.src = "proj2_sprite_skel.png";
let characterSet4Y = 625;
let characterSet4W = 180;

// Image dimensions
let imageWidth = 50;
let imageHeight = 100;

// Forward animation frames dimensions
const forwardW = 240;
const forwardH = 300;

// Starting position (center of canvas)
let startingPositionX = 450;
let startingPositionY = 320;

// Movement speed
const speed = 10;

// Source image position
let frameX = 0;
let frameY = 0;

let gameFrame = 0;
let motionFlag = 0;

// Default animation image
function noMotion() {
  ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
  ctx.drawImage(
    defaultImage,
    defaultImageX,
    defaultImageY,
    forwardW,
    forwardH,
    startingPositionX,
    startingPositionY,
    imageWidth,
    imageHeight
  );
}

// Foward facing animation
function fowardMotion() {
  ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

  ctx.drawImage(
    characterSet1,
    frameX,
    frameY,
    forwardW,
    forwardH,
    startingPositionX,
    startingPositionY,
    imageWidth,
    imageHeight
  );

  if (gameFrame % motionFlag == 0) {
    if (frameX < 720) {
      frameX += forwardW;
    } else {
      frameX = 0;
    }
  }
  gameFrame++;
}

// Back facing animation
function backwardMotion() {
  ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

  ctx.drawImage(
    characterSet2,
    characterSet2X,
    frameY,
    forwardW,
    forwardH,
    startingPositionX,
    startingPositionY,
    imageWidth,
    imageHeight
  );

  if (gameFrame % motionFlag == 0) {
    if (characterSet2X < 1735) {
      characterSet2X += forwardW;
    } else {
      characterSet2X = 1015;
    }
  }
  gameFrame++;
}

// Right facing animation
function rightMotion() {
  ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

  ctx.drawImage(
    characterSet3,
    frameX,
    characterSet3Y,
    characterSet3W,
    forwardH,
    startingPositionX,
    startingPositionY,
    imageWidth,
    imageHeight
  );

  if (gameFrame % motionFlag == 0) {
    if (frameX < 480) {
      frameX += characterSet3W;
    } else {
      frameX = 0;
    }
  }
  gameFrame++;
}

// Left facing animation
function leftMotion() {
  ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

  ctx.drawImage(
    characterSet3,
    frameX,
    characterSet4Y,
    characterSet4W,
    forwardH,
    startingPositionX,
    startingPositionY,
    imageWidth,
    imageHeight
  );

  if (gameFrame % motionFlag == 0) {
    if (frameX < 480) {
      frameX += characterSet4W;
    } else {
      frameX = 0;
    }
  }
  gameFrame++;
}

function moveUp() {
  if (startingPositionY > 0) {
    startingPositionY -= speed;
  }
  motionFlag = 1;
  requestAnimationFrame(backwardMotion);
}

function moveDown() {
  if (startingPositionY + imageHeight < CANVAS_HEIGHT) {
    startingPositionY += speed;
  }
  motionFlag = 1;
  requestAnimationFrame(fowardMotion);
}

function moveRight() {
  if (startingPositionX + imageWidth < CANVAS_WIDTH) {
    startingPositionX += speed;
  }
  motionFlag = 1;
  requestAnimationFrame(rightMotion);
}

function moveLeft() {
  if (startingPositionX > 0) {
    startingPositionX -= speed;
  }
  motionFlag = 1;
  requestAnimationFrame(leftMotion);
}

// detect user input
function keyDown(e) {
  if (
    e.key === "ArrowRight" ||
    e.key === "Right" ||
    e.key === "d" ||
    e.key === "D"
  ) {
    moveRight();
  }

  if (
    e.key === "ArrowLeft" ||
    e.key === "Left" ||
    e.key === "a" ||
    e.key === "A"
  ) {
    moveLeft();
  }

  if (e.key === "ArrowUp" || e.key === "Up" || e.key === "w" || e.key === "W") {
    moveUp();
  }

  if (
    e.key === "ArrowDown" ||
    e.key === "Down" ||
    e.key === "s" ||
    e.key === "S"
  ) {
    moveDown();
  }
}

// detect no user Input
function keyUp(e) {
  if (
    e.key === "ArrowRight" ||
    e.key === "Right" ||
    e.key === "d" ||
    e.key === "D" ||
    e.key === "ArrowLeft" ||
    e.key === "Left" ||
    e.key === "a" ||
    e.key === "A" ||
    e.key === "ArrowUp" ||
    e.key === "Up" ||
    e.key === "w" ||
    e.key === "W" ||
    e.key === "ArrowDown" ||
    e.key === "Down" ||
    e.key === "s" ||
    e.key === "S"
  ) {
    motionFlag = 0;
    requestAnimationFrame(noMotion);
  }
}

fowardMotion();
leftMotion();
backwardMotion();
rightMotion();
noMotion();
document.addEventListener("keydown", keyDown);
document.addEventListener("keyup", keyUp);
