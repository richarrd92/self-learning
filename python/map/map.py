import folium  # Library used for creating interactive maps
import pandas  # Library used for reading and analyzing data from files

# Load volcano data from the file 'volcanoes.txt'
# Assumes the file contains columns "LAT" (latitude) and "LON" (longitude)
data = pandas.read_csv("volcanoes.txt")

# Extract latitude and longitude data into separate lists
lat = list(data["LAT"])  # List of volcano latitude coordinates
lon = list(data["LON"])  # List of volcano longitude coordinates

# Prompt the user for a latitude and longitude to center the map
# Example inputs: latitude 39.29, longitude -76.60 for Baltimore
latitude = float(input("Enter latitude coordinate: "))
longitude = float(input("Enter longitude coordinate: "))

# Create a base map centered at the user-provided coordinates
map = folium.Map(location=[latitude, longitude])

# Create a FeatureGroup to organize map elements
fg = folium.FeatureGroup(name="Volcanoes Map")

# Add markers for each volcano to the FeatureGroup
for lt, ln in zip(lat, lon):
    fg.add_child(folium.Marker(location=[lt, ln]))

# Add the FeatureGroup to the map
map.add_child(fg)

# Save the generated map to an HTML file
map.save("map1.html")
