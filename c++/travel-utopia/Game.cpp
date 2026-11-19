
//Includes of required classes
#include "Ship.h"
#include "Planet.h"
#include "Enemy.h"
#include "Gas.h"
#include "Mining.h"
#include "Item.h"
#include "Armor.h"
#include "Engine.h"
#include "Weapon.h"
#include "Game.h"

//Includes of required libraries
#include <iostream>
#include <ctime>
#include <fstream>
#include <string>
#include <vector>
#include <iomanip>

using namespace std;

  // Game
  // Creates a new Game by setting m_fileItems and m_filePlanets
  // Sets two file names in Game
  Game::Game(string fileItems, string filePlanets){
    m_fileItems = fileItems;
    m_filePlanets = filePlanets;
  }


  // ~Game
  // Deallocates anything dynamically allocated in Game (ship, items, and planets)
  Game::~Game(){

    // Deletes ship
    delete m_ship;
    m_ship = nullptr;

    // iterates through vector m_items
    for(unsigned int i = 0; i < m_items.size(); i++){
      delete m_items[i]; 
    }

    // iterates through vector m_planets
    for(unsigned int i = 0; i < m_planets.size(); i++){
      delete m_planets[i]; 
    }
  }


  // LoadItems
  // Dynamically allocates items and inserts them into the m_items vector
  void Game::LoadItems(){

    fstream myFile;

    // opens Items file
    myFile.open(m_fileItems); 
     
    // iterates through file line by line 
    string line; 

    if (myFile.is_open())
    {

      while (getline(myFile, line)) 
      {
          // Find the positions of commas
          int pos1 = line.find(DELIMITER);
          int pos2 = line.find(DELIMITER, pos1 + 1);
          int pos3 = line.find(DELIMITER, pos2 + 1);


          // Extract substrings based on comma positions
          string name = line.substr(0, pos1);
          string type = line.substr(pos1 + 1, pos2 - pos1 - 1);
          int value = stoi(line.substr(pos2 + 1, pos3 - pos2 - 1));

          // Dynamically allocates Armor Item
          if(type == "armor"){
            Item* newArmor = new Armor(name, type, value);
            m_items.push_back(newArmor);
          }
          
          // Dynamically allocates Engine Item
          else if (type == "engine"){
            Item* newEngine = new Engine(name, type, value);
            m_items.push_back(newEngine);
          }

          // Dynamically allocates Weapon Item
          else if (type == "weapon"){
            Item* newWeapon = new Weapon(name, type, value);
            m_items.push_back(newWeapon);
          }

      }

      cout << "85 ITEMS LOADED" <<endl; 
    }  

    // Close the file
    myFile.close(); 

  }


  // LoadPlanets
  // Dynamically allocates items and inserts them into the m_planets vector
  void Game::LoadPlanets(){

    fstream myFile;

    // opens Planet file
    myFile.open(m_filePlanets); 
     
    // iterates through file line by line 
    string line; 

    if (myFile.is_open())
    {

      while (getline(myFile, line)) 
      {
          // Find the positions of commas
          int pos1 = line.find(DELIMITER);
          int pos2 = line.find(DELIMITER, pos1 + 1);
          int pos3 = line.find(DELIMITER, pos2 + 1);


          // Extract substrings based on comma positions
          string name = line.substr(0, pos1);
          string type = line.substr(pos1 + 1, pos2 - pos1 - 1);
          int challenge = stoi(line.substr(pos2 + 1, pos3 - pos2 - 1));

          // Dynamically allocates Gas Planet
          if(type == "gas"){
            Planet* newGas = new Gas(name, challenge);
            m_planets.push_back(newGas);
          }
          
          // Dynamically allocates Mining Planet
          else if (type == "mining"){
            Planet* newMine = new Mining(name, challenge);
            m_planets.push_back(newMine);
          }

          // Dynamically allocates Enemy Planet
          else if (type == "enemy"){
            Planet* newEnemy = new Enemy(name, challenge);
            m_planets.push_back(newEnemy);
          }

      }

      cout << "112 PLANETS LOADED" <<endl; 
    }  

    // Close the file
    myFile.close(); 

  }


  // ShipCreation
  // Populates m_ship and Sets ship name
  // Also adds initial items to ship 
  void Game::ShipCreation(){

    // Ship name 
    string shipName;

    // Dynamically allocates Ship object 
    cout << "SHIP NAME: ";
    getline(cin, shipName);

    // Ship pointer for Ship (Player)
    m_ship = new Ship(shipName);
    m_ship->GetName();

    // Adds initial items
    m_ship->AddItem(m_items[START_ARMOR]);
    m_ship->AddItem(m_items[START_ENGINE]);
    m_ship->AddItem(m_items[START_WEAPON]);

  }


  // StartGame
  // Calls LoadPlanets, LoadItems, ShipCreation, sets the m_curPlanet
  // randomizes m_winEngine and calls Action
  void Game::StartGame(){

    cout << "******************* GAME HAS STARTED *******************\n\n" <<endl;

    cout << "WELCOME TO SUPER SPACE EXPLORATION!\n" <<endl;

    // Loads files
    Game::LoadPlanets();
    Game::LoadItems();

    // Ship Creation
    Game::ShipCreation();

    // sets the m_curPlanet (0)
    m_curPlanet = m_planets[START_PLANET];
    cout << "\nCURRENT PLANET: " << *m_curPlanet <<endl;

    // randNUmb btn (30 - 50)
    m_winEngine = (rand() % ((UTOPIA_MAX-1) - (UTOPIA_MIN+1) +1)) + (UTOPIA_MIN+1);
    
    // Main menu
    Game::Action();
  }


  // Action
  // Displays menu for the game
  void Game::Action(){

    int userChoice;

    do{

        cout << "\nWhat would you like to do?" <<endl;
        cout << "1. Examine Item" <<endl;
        cout << "2. Move" <<endl;
        cout << "3. Attack Planet" <<endl;
        cout << "4. Travel to Utopia" <<endl;
        cout << "5. Check Stats" <<endl;
        cout << "6. Quit" <<endl;
        cout << "Choice: ";
        cin >> userChoice;

        // validates userChoice
        while (userChoice < 1 || userChoice > 6) {
            cout << "\nERROR: OUT OF BOUNDS" <<endl;
            cout << "\nWhat would you like to do?" <<endl;
            cout << "1. Examine Item" <<endl;
            cout << "2. Move" <<endl;
            cout << "3. Attack Planet" <<endl;
            cout << "4. Travel to Utopia" <<endl;
            cout << "5. Check Stats" <<endl;
            cout << "6. Quit" <<endl;
            cout << "Choice: ";
            cin >> userChoice;
        }

        switch(userChoice){
          case 1: Game::DisplayShipItems(); break;
          case 2: 

          // Ship contain fuel
          if(m_ship->Ship::GetFuel() > 0){
            Game::Move(); break;
          } 
          
          // Ship fuel tank is empty
          else {
              cout << "\nYou ran out of food and fuel and died." <<endl;
              cout << "Please play again!" <<endl;
              cout << "Good bye!" <<endl;
              userChoice = 6;  // QUITS
              break;  
          }

          case 3: 
          
          // Planet not defeated
          if(m_curPlanet->Planet::GetIsExplored() == false){
              Game::AttackPlanet(); break;
          }

          // Planet already Defeated
          else{
            cout << "\nThis planet has already been DEFEATED." <<endl;
            cout << "You should move to a NEW planet." <<endl;
            break;
          }
          
          
          case 4: 

          // Continue Playing 
          if(Game::TravelUtopia() == 0){
            cout << "\nYou are not prepared to try to travel to Utopia yet." <<endl;
            cout << "Perhaps develop your engines more?" <<endl;
            break;
          } 
          
          else{
            // GAME LOST
            if(Game::TravelUtopia() == -1){
              cout << "\nThe engines just weren't powerful enough." <<endl;
              cout << "You eventually ran out of food and fuel and died." <<endl;
              cout << "Please play again!" <<endl;
              cout << "Good bye!" <<endl;
              userChoice = 6;  // QUITS
              break;  
            } 
            
            // GAME WON
            else if(Game::TravelUtopia() == 1){
              cout << "\nCONGRATULATIONS!! YOU MADE IT TO UTOPIA" <<endl;  
              userChoice = 6;  // QUITS
              break;     
            }
          } 
          
          case 5: Game::Stats(); break;
          case 6: userChoice = 6; break; // QUITS 
        }

    } while(userChoice != 6);

  }


  // Move
  // Moves ship to new random location
  void Game::Move(){
    cout << "\nTRAVELLING TO THE NEXT PLANET " <<endl;

    // randNumb (0 - m_planets.size())
    int randNumb = rand() % m_planets.size();

    // updates m_curPlanet
    m_curPlanet = m_planets[randNumb];
    cout << "\nPLANET: " << *m_curPlanet <<endl;

    // Modify ship fuel
    m_ship->Ship::ModifyFuel(MOVE_FUEL);

  }


  // AttackPlanet
  // Allows ship to attack a planet
  void Game::AttackPlanet(){
      
      // Calls Planet's battle with ship
      bool  battleResult = m_curPlanet->Planet::Battle(m_ship);

      if(battleResult == true){
        
          // Sets IsExplored to true
          m_curPlanet->Planet::SetIsExplored();
          
          // planet Challenge level
          Game::IdentifyItem(m_curPlanet->Planet::GetChallenge());
      } 
      
      // Lost Battle
      else {
          cout << "You attacked but didn't win the battle." <<endl;
      }
  }


  // IdentifyItem
  // Randomly identifies an item of the challenge's level
  // Calls AddItem in ship and tries to add the item
  void Game::IdentifyItem(int challenge){

    // randNumb (0 - m_items.size())
    int randNumb = rand() % m_items.size();

    // iterates until item is found
    while (challenge != m_items[randNumb]->GetValue()){
      randNumb = rand() % m_items.size();
    }

    // prints out item
    cout << "You have found: " << m_items[randNumb]->GetName() 
                               << "(" << m_items[randNumb]->GetType() 
                               << ")" << " " << m_items[randNumb]->GetValue() 
                               <<endl;

    // Adds item found to Ship
    m_ship->Ship::AddItem(m_items[randNumb]);

  }


  // Stats
  // Calls the ship's display function
  void Game::Stats(){
    m_ship->DisplayShip();
    Game::DisplayShipItems();
    cout << "\n----------------------------" <<endl;
  }


  // TravelUtopia
  // Attempts to win game by traveling to Utopia
  int Game::TravelUtopia(){

    if(m_ship->Ship::GetTotal("engine") > UTOPIA_MIN){

      // 1 = win
      if(m_ship->Ship::GetTotal("engine") > m_winEngine)
        return 1;

      // -1 = lose
      else if (m_ship->Ship::GetTotal("engine") < m_winEngine)
        return -1;    
    }

    // Continue Game
    return 0;
  }


  // DisplayShipItems
  // Displays a numbered list of all items on ship
  void Game::DisplayShipItems(){

    // m_Items vector is empty
    if(m_ship->Ship::GetItemCount() == 0){
      cout << "\nNO ITEMS FOUND" <<endl;
    }

    // ship contains items
    else{
        m_ship->Ship::DisplayItems();
    }

  }
