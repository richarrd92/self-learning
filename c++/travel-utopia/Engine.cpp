
#include <iostream>
#include <string>
#include "Item.h" // loads item.h file
#include "Engine.h" // loads Engine.h file
using namespace std;

  // Engine
  // Creates a new Engine item
  Engine::Engine(string name, string type, int value) : Item::Item(name, type, value){
    // intialized default contructor using inheritance 
  }

  // Display
  // Displays Engine, name, and value of Engine
  void Engine::Display(){
    // const string ENGINE 
    cout << ENGINE << GetName() << "(" << GetValue() << ")" <<endl;
  }
