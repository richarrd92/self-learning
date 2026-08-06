

class incrementer {
    static value: number = 10;

    constructor(){}

    increment(): void {
        incrementer.value++;
    }

    getValue(): number {
        return incrementer.value;
    }
}

const inc1 = new incrementer();
console.log(inc1.getValue()); // Output: 10
inc1.increment();
console.log(inc1.getValue()); // Output: 11

const inc2 = new incrementer();
console.log(inc2.getValue()); // Output: 10
inc2.increment();
console.log(inc2.getValue()); // Output: 11


// let person : { name: string; age: number; greet(): void }; 

type Person = {
    name: string;
    age: number;
    greet(): void;
};

let person2 : Person = {
    name: "Alice",
    age: 30,
    greet() {
        console.log(`Hello, my name is ${this.name} and I am ${this.age} years old.`);
    }
};

person2.greet();