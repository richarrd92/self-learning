
/**
 * Represents a single sidebar button.
 * @property label - The text displayed on the button.
 * @property route - The route path to navigate when clicked.
 */
export interface SidebarButton {
  label: string;
  route: string;
}

/**
 * Sidebar configuration mapping each section to its buttons.
 * 
 * Keys represent different sections/pages of the app.
 * Values are arrays of SidebarButton objects to render in that section.
 * 
 * Example:
 *  - dashboard → Home, Friends, Hobbies
 *  - profile → Profile Overview, Edit Profile
 */
export const SIDEBAR_CONFIG: Record<string, SidebarButton[]> = {
  dashboard: [
    { label: 'Homes', route: '/dashboard' },
    { label: 'Profile', route: '/profile' },
    { label: 'Hobbies', route: '/hobbies' },
  ],
  profile: [
    { label: 'Homes', route: '/dashboard' },
    { label: 'Profile', route: '/profile' },
    { label: 'Hobbies', route: '/hobbies' },
  ],
  friends: [
    { label: 'All Friends', route: '/friends' },
    { label: 'Friend Requests', route: '/friends/requests' },
  ],
  hobbies: [
    { label: 'All Hobbies', route: '/hobbies' },
    { label: 'Add Hobby', route: '/hobbies/add' },
  ],
};
