

#include <iostream>
#include <string>
#include "Item.h" // loads item.h file
#include "Weapon.h" // loads Weapon.h file
using namespace std;

  // Weapon
  // Creates a new Weapon item
  Weapon::Weapon(string name, string type, int value) : Item::Item(name, type, value){
    // intialized default contructor using inheritance 
  }

  // Display
  // Displays Weapon, name, and value of Weapon
  void Weapon::Display(){
    // const string WEAPON 
    cout << WEAPON << GetName() << "(" << GetValue() << ")" <<endl;
  }
