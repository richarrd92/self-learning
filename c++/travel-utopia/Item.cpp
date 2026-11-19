
#include <iostream>
#include <string>
#include "Item.h" // loads item.h file
using namespace std;

  // Item
  // Creates a new item of a specific type 
  Item::Item(string name, string type, int value){
    m_name = name; 
    m_type = type; 
    m_value = value; 
  }

  // ~Item 
  // Required because child classes exist
  Item::~Item(){
    // left empty on purpose
  }

  // GetType
  // Returns the type of item
  string Item::GetType(){
    return m_type;
  }

  // GetName
  // Returns the name of item
  string Item::GetName(){
    return m_name;
  }

  // GetValue
  // Returns value of item
  int Item::GetValue(){
    return m_value;
  }

