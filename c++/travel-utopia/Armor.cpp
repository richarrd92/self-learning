
#include <iostream>
#include <string>
#include "Item.h" // loads item.h file
#include "Armor.h" // loads Armor.h file
using namespace std;

  // Armor
  // Creates a new Armor item
  Armor::Armor(string name, string type, int value) : Item::Item(name, type, value){
    // intialized default contructor using inheritance 
  }

  // Display
  // Displays ARMOR, name, and value of armor
  void Armor::Display(){
    // const string ARMOR 
    cout << ARMOR << GetName() << "(" << GetValue() << ")" <<endl;
  }
