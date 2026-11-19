CXX = g++
CXXFLAGS = -Wall -g

proj4: Item.o Engine.o Weapon.o Armor.o Planet.o Enemy.o Mining.o Gas.o Ship.o Game.o proj4.cpp 
	$(CXX) $(CXXFLAGS) Item.o Engine.o Weapon.o Armor.o Planet.o Enemy.o Mining.o Gas.o Ship.o Game.o proj4.cpp -o proj4

Game.o: Game.h Game.cpp
	$(CXX) $(CXXFLAGS) -c Game.cpp

Ship.o: Ship.h Ship.cpp
	$(CXX) $(CXXFLAGS) -c Ship.cpp

Gas.o: Gas.h Gas.cpp
	$(CXX) $(CXXFLAGS) -c Gas.cpp

Mining.o: Mining.h Mining.cpp
	$(CXX) $(CXXFLAGS) -c Mining.cpp	

Enemy.o: Enemy.h Enemy.cpp
	$(CXX) $(CXXFLAGS) -c Enemy.cpp

Planet.o: Planet.h Planet.cpp
	$(CXX) $(CXXFLAGS) -c Planet.cpp

Armor.o: Armor.h Armor.cpp
	$(CXX) $(CXXFLAGS) -c Armor.cpp

Weapon.o: Weapon.h Weapon.cpp
	$(CXX) $(CXXFLAGS) -c Weapon.cpp

Engine.o: Engine.h Engine.cpp
	$(CXX) $(CXXFLAGS) -c Engine.cpp

Item.o: Item.h Item.cpp
	$(CXX) $(CXXFLAGS) -c Item.cpp

clean:
	rm *.o*
	rm *~ 

run:
	./proj4 proj4_items.txt proj4_planets.txt

val1:
	valgrind ./proj4 proj4_items.txt proj4_planets.txt
