
#include <iostream>
#include <string>
#include "Ship.h" // Loads Ship.h file
#include "Planet.h"  // Loads Planet.h file
using namespace std; 
  
  // Planet
  // Populates name, challenge, and isExplored (defaults to false)
  Planet::Planet(string name, int challenge){
    m_name = name;
    m_challenge = challenge;
    m_isExplored = false; // default is False
  }


  // ~Planet
  // Virtual destructor for an planet
  Planet::~Planet(){
    // left empty on purpose    
  }


  // Battle
  // initiates battle between planet and ship
  // Returns true if win, else false
  bool Planet::Battle(Ship* ship){
    cout << "\nBATTLE COMMENCING!" <<endl;

    // random difficulty
    int randDifficulty = rand() % Planet::GetChallenge() +1;

    // Ship Attack
    int shipAttack = ship->Ship::GetTotal("weapon");

    // Ship Defense
    int shipDefense = ship->Ship::GetTotal("armor");

    // Battle lost 
    if(randDifficulty > shipAttack){
      ship->Ship::ModifyFuel(-FUEL_COST_DELTA);
      return false;
    } 

    // Calculates delta
    int delta =  (shipAttack/(double)shipDefense) * FUEL_MULTI_WIN;
    cout << "\nYour planetary bombardment worked. You won!" <<endl;
    ship->Ship::ModifyFuel(delta);
    return true; 
  }


  // GetName
  // Returns planet name
  string Planet::GetName(){
    return m_name;
  }


  // SetIsExplored
  // Toggles from false to true when explored
  void Planet::SetIsExplored(){
      m_isExplored = true;
  }


  // GetIsExplored
  // Returns true if m_isExplored is true
  bool Planet::GetIsExplored(){
    return m_isExplored;
  }


  // GetChallenge
  // Returns challenge of planet
  int Planet::GetChallenge(){
    return m_challenge;
  }


  // Overloaded <<
  // returns an ostream with output of planet
  ostream& operator<<(ostream& output, Planet& planet){
    output << planet.GetName() << "(" << planet.GetType() << ") Challenge: " << planet.GetChallenge();
    return output;
  }
