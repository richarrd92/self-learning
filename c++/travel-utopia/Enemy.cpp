
#include <iostream>
#include <string>
#include "Planet.h" // loads Planet.h file
#include "Enemy.h" // loads Enemy.h file
using namespace std;
 
 
  // Enemy
  // Creates a new Enemy planet
  Enemy::Enemy(string name, int challenge) : Planet::Planet(name, challenge){
    // intialized default constructor using inheritance 
  }


  // GetType
  // Returns the type of planet
  string Enemy::GetType(){
    return "enemy";
  }
