console.log("hello world");

// lambda functions
const func = (timelapse=0) => {
  console.log("hello world from func, timelapse: ", timelapse);
};

const func2 = (timelapse=0) => {
  return "hello world from func2 timelapse: " + timelapse;
};

const func3 = (timelapse=0) => "hello world from func3 timelapse: " + timelapse;

// call-back functions
setTimeout(() => func(1000), 1000);
console.log(func2());
setTimeout(() => console.log(func3(5000)), 5000);

