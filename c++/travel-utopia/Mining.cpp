
#include <iostream>
#include <string>
#include "Planet.h" // loads Planet.h file
#include "Mining.h" // loads Mining.h file
using namespace std;
 
 
  // Enemy
  // Creates a new Enemy planet
  Mining::Mining(string name, int challenge) : Planet::Planet(name, challenge){
    // intialized default contructor using inheritance 
  }


  // GetType
  // Returns the type of planet
  string Mining::GetType(){
    return "mining"; 
  }
