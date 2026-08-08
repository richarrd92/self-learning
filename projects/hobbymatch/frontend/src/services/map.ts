import { Injectable } from '@angular/core';
import * as L from 'leaflet';
import { UserResponse } from '../app/models/user/UserResponse';
import { environment } from '../environments/environment';

/**
 * Service for managing a Leaflet map and user markers.
 * 
 * Provides methods to:
 *  - Initialize a map (`initMap`).
 *  - Add user markers (`addUserMarkers`).
 *  - Clear markers (`clearMarkers`).
 *  - Destroy the map (`clearMap`).
 *
 * Designed to be used across components as a singleton service.
 */
@Injectable({
  providedIn: 'root',
})
export class MapService {
  private map?: L.Map; // Map instance

  /**
   * Initializes the Leaflet map inside the specified HTML container.
   * @param containerId The ID of the HTML element that will hold the map.
   * @param center The initial latitude/longitude of the map center (default = Baltimore coordinates).
   * @param zoom The initial zoom level (default = 13).
   * @returns The created Leaflet map instance or undefined if initialization fails.
   */
  initMap(
    containerId: string,
    center: [number, number] = [39.3082, -76.6338],
    zoom: number = 13
  ): L.Map | undefined {
    // Locate the DOM element that will contain the map
    const mapContainer = document.getElementById(containerId);
    if (!mapContainer) return;

    // Clear the map if it already exists
    this.clearMap();

    // Create and configure the Leaflet map
    this.map = L.map(mapContainer, {
      center,
      zoom,
      minZoom: 3,
      maxZoom: 20,
      zoomControl: false,
      attributionControl: false,
    });

    // Get the Mapbox access token
    const mapboxToken = environment.mapboxToken;

    // Add the Mapbox streets tile layer
    L.tileLayer(
      `https://api.mapbox.com/styles/v1/mapbox/streets-v12/tiles/{z}/{x}/{y}?access_token=${mapboxToken}`,
      {
        tileSize: 512,
        zoomOffset: -1,
        maxZoom: 20,
        attribution:
          '© <a href="https://www.mapbox.com/">Mapbox</a> © <a href="https://www.openstreetmap.org/">OpenStreetMap</a>',
      }
    ).addTo(this.map); // Add the tile layer to the map

    return this.map; // Return the created map
  }

  /**
   * Adds user markers to the map using their geographic coordinates.
   * @param users Array of UserResponse objects containing user location data.
   */
  addUserMarkers(users: UserResponse[]) {
    if (!this.map) return;

    // Clear existing markers
    this.clearMarkers();

    // Add new markers
    users.forEach((user) => {
      if (user.embeddedLocation?.latitude && user.embeddedLocation?.longitude) {
        L.marker([user.embeddedLocation.latitude, user.embeddedLocation.longitude])
          .addTo(this.map!) // Add marker to the map
          .bindPopup(`<b>${user.name}</b><br>${user.bio || ''}`); // Add popup
      }
    });
  }

  /**
   * Removes all markers (and layers) from the map without destroying the map itself.
   * Useful for refreshing or reloading data dynamically.
   */
  clearMarkers() {
    if (!this.map) return;
    this.map.eachLayer((layer) => {
      // Check if the layer is a marker
      if ((layer as L.Marker).getLatLng) this.map?.removeLayer(layer);
    });
  }

  /**
   * Completely destroys the current map instance.
   * Useful when navigating away from a view or reinitializing a new map.
   */
  clearMap() {
    if (this.map) {
      this.map.remove(); // destroys map and unbinds events
      this.map = undefined; // Clear the map instance
    }
  }
}
