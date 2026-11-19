
#include <iostream>
#include <string>
#include <vector>
#include "Item.h"
#include "Ship.h"
using namespace std;

  // Ship
  // Description: Creates A new ship
  Ship::Ship(string name){
    m_name = name;

    // sets intial fuel
    m_fuel = STARTING_FUEL;
  }


  // GetName
  // Returns ship's m_name
  string Ship::GetName(){
    return m_name;
  }


  // GetTotal
  // Returns total value for armor, weapon, or engine
  int Ship::GetTotal(string item){

      // Stores Item total
      int itemSum = 0;

      // iterates through m_shipItems
      for (unsigned int i = 0; i < m_shipItems.size(); i++){

        // Adds specific item only
        if (item == m_shipItems.at(i)->GetType()){
            itemSum += m_shipItems.at(i)->GetValue();
        }
      }

    // Returns item total
    return itemSum;
  }


  // AddItem
  // Adds a new item into m_items up to MAX_ITEMS.
  // If at max, calls ReplaceItem
  void Ship::AddItem(Item* item){

    // validates number of items in ship
    if(Ship::GetItemCount() > MAX_ITEMS-1){
       // Calls ReplaceItem
       Ship::ReplaceItem(item);
    }

    else {
        // Adds item to ship
        m_shipItems.push_back(item);       
    }
  }


  // ReplaceItem
  // Used to replace an item in inventory if m_shipItem is at capacity
  void Ship::ReplaceItem(Item* item){

      int userChoice;
      Ship::DisplayItems();  
      cout <<"\nWhich item would you like to replace?" <<endl;
      cout << "Option: ";
      cin >> userChoice;
        
      // validates userChoice
      while((userChoice < 1 || userChoice > Ship::GetItemCount())){
          cout << "\nERROR: OUT OF BOUNDS!" <<endl << "Which item would you like to replace?" <<endl;
          cout << "Option: ";
          cin >> userChoice;
      }       
      
      // Replaces item on ship
      m_shipItems[userChoice-1] = item; 
      cout << "\n******* ITEM ON SHIP SUCCESSFULLY REPLACED *******" <<endl;     
  }


  // GetFuel
  // Returns m_fuel
  int Ship::GetFuel(){
    return m_fuel;
  }


  // ModifyFuel
  // Used to update fuel
  void Ship::ModifyFuel(int fuel){
      // Updates fuel
      m_fuel += fuel;
      cout << "Your fuel has been modified by " << fuel << " fuel" <<endl;
  }


  // DisplayItems
  // Displays a numbered list of all items on ship.
  void Ship::DisplayItems(){
    cout << "\n---- Items on the ship ----\n" <<endl;

        // iterates through vector m_shipItems
        for (unsigned int i = 0; i < m_shipItems.size(); i++){
            cout << i+1 << ". "; m_shipItems.at(i)->Display();
        }
  }


  // GetItemCount
  // Returns number of items in m_shipItems
  int Ship::GetItemCount(){
    // returns the size of the items vector
    return m_shipItems.size();
  }


  // DisplayShip
  // Displays stats about the ship
  void Ship::DisplayShip(){

    cout << "\n-------- SHIP STATS --------\n" <<endl;
    cout << "Name: "  << GetName() <<endl;
    cout << "Fuel: "  << GetFuel() <<endl;
    cout << "Attack: "  << Ship::GetTotal("weapon") <<endl;
    cout << "Defense: "  << Ship::GetTotal("armor") <<endl;
    cout << "Engine: "  << Ship::GetTotal("engine") <<endl;
    
  }
