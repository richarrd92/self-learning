
#include <iostream>
#include <string>
#include "Planet.h" // loads Planet.h file
#include "Gas.h" // loads Gas.h file
using namespace std;
 
 
  // Enemy
  // Creates a new Enemy planet
  Gas::Gas(string name, int challenge) : Planet::Planet(name, challenge){
    // intialized default contructor using inheritance 
  }


  // GetType
  // Returns the type of planet
  string Gas::GetType(){
    return "gas";
  }
