# Data Transfer Objects (DTOs)
**Note**: All the `value` properties are currently optional, to be enforced later.

### ErrorResponseDto
Represents a standardized error response returned by the API when an exception occurs.
```javascript
{
    status: 'number',       // HTTP status code, e.g. 400
    errorCode: 'string',    // Custom error code, e.g. "BAD_REQUEST"
    details: 'string'       // Detailed error description
}
```

### LogoutResponseDto
Represents a standardized logout response returned by the API.
```javascript
{
    message: 'string',
    success: 'boolean'
}
```

### UserCredentialsDto
Represents credentials when returning user data from the API. Password is omitted for security.
```javascript
{
    username: 'string',
    email: 'string',
    // password ommitted for security
}
```

### CredentialsResponseDto
Represents credentials when returning auth data from the API. Password is omitted for security.
```javascript
{
    id: 'number',
    username: 'string',
    email: 'string',
    admin: 'boolean'
    // password ommitted for security
}
```

### CredentialsRequestDto
Represents user login or registration credentials. Includes password because this is used for incoming requests.
```javascript
{
    username: 'string',
    email: 'string'
    password: 'string'      // Required for login/registration
}
```

### ProfileDto
Contains the personal information of a user.
```javascript
{
    first: 'string',
    last: 'string',
    phone: 'string'
}
```

### UserStateDto
Represents the current state of a user account, including activity and administrative flags.
```javascript
{
    active: 'boolean',
    admin: 'boolean',
    status: 'PENDING | JOINED' // enum string
}
```

### UserRequestDto
Used when creating or updating a user; contains credentials and profile information.
```javascript
{
    credentials: 'CredentialsDto',
    profile: 'ProfileDto'
    userState: 'UserStateDto'
}
```

### UserResponseDto
Represents the full user data returned from the API, including ID and state.
```javascript
{
    id: 'number',
    credentials: 'UserCredentialsDto',
    profile: 'ProfileDto'
    userState: 'UserStateDto'
}
```

### CompanyDto
Represents the full company data returned from the API
```javascript
 {
    id: 'number', 
    name: 'string', 
    description: 'string', 
    teams: [TeamDto], 		// list of teams in the company
    users: [UserRequestDto]	// list of users in the company
 }
```

### AnnouncementRequestDto
Used when creating an announcement
```javascript
 {
    authorId: 'number', 
    title: 'string', 
    message: 'string'
 }
```

### AnnouncementDto
Represents the full announcement data returned from the API
```javascript
 {
    id: 'number',
    data: 'string', 	// frontend sends a string in this format: new Date().toISOString() 
    title: 'string', 
    message: 'string', 
    author: 'UserRequestDto', 
    company: 'CompanyDto'
 }
```

### TeamRequestDto
Used when creating a team
```javascript
 {
    authorId: 'number', 
    name: 'string', 		// team name
    description: 'string', 
    userIds: [number]		// list of ids of the users on the team
 }
```

### TeamDto
Represents the full team data returned from the API
```javascript
 {
    id: 'number', 
    name: 'string', 		// team name
    description: 'string', 
    users: [UserRequestDto]	// list of users on the team
 }
```

### ProjectRequestDto
Used to create and edit a project
```javascript
 { 
    name: 'string', 
    description: 'string', 
    active: 'boolean' 		
 }
```

### ProjectDto
Represents the full project data returned from the API
```javascript
 {
    id: 'number', 
    name: 'string', 	    // project name
    description: 'string', 
    active: 'boolean', 
    team: 'TeamDto'         // team that the project belongs to
 }
```


# Endpoint Documentation
Documentation of all endpoints and their functionality.

## /auth
#### `POST auth/login`
Authenticates a user using either username or email and password.
- If successful:
  - Invalidates any previous session.
  - Creates a new secure HTTP session.
  - Stores the user’s id and isAdmin status in the session.
  - Returns basic user information (no password).

#### Request
``` Java
'CredentialsRequestDto'
```

#### Response
``` Java
'CredentialsResponseDto'
```

#### `POST auth/logout`
Invalidates the current user session. If no session exists, returns a message indicating that the user is not logged in.
#### Response
``` Java
'LogoutResponseDto'
```

## /users

#### `GET users/{id}`
Returns a user by id.

**Requires:** An active session. The logged-in user to have admin privileges.
Each UserResponseDto contains `credentials`, `profile`, and `userState`. More to be added e.g `Company`, `Team`, `Announcement`.

#### Response
``` Java
'UserResponseDto'
```

#### `GET users/companies/{companyId}`
Returns all users belonging to a specific company.

#### Response
``` Java
[UserResponseDto]
```

#### `GET users`
Returns a list of all User objects in the database.

**Requires:** An active session. The logged-in user to have admin privileges.
Each UserResponseDto contains `credentials`, `profile`, and `userState`. More to be added e.g `Company`, `Team`, `Announcement`.

#### Response
``` Java
['UserResponseDto']
```

#### `POST users`
Creates new user and Returns the created User object in the database.

**Requires:** An active session. The logged-in user to have admin privileges.
Each UserResponseDto contains `credentials`, `profile`, and `userState`. More to be added e.g `Company`, `Team`, `Announcement`.

#### Response
``` Java
'UserResponseDto'
```

## /companies

### `GET companies`
Returns all companies

### Response
``` Java
['CompanyDto']
```

## /announcements

### `GET announcements/{companyId}`
Returns all the announcements from {companyId} company

### Response
``` Java
['AnnouncementDto']
```

### `POST announcements/{companyId}`
Creates a new announcement and returns the created Announcement object in the database

### Request
``` Java
'AnnouncementRequestDto' in the @RequestBody
```

### Response
``` Java
'AnnouncementDto'
```

## /teams

### `GET teams/{companyId}`
Returns all the teams from {companyId} company

### Response
``` Java
['TeamDto']
```

### `POST teams/{companyId}`
Creates a new team and returns the created Team object in the database

### Request
``` Java
'TeamRequestDto' in the @RequestBody
```

### Response
``` Java
'TeamDto'
```

## /projects

### `GET projects/{companyId}/{teamId}`
Returns all projects in team {teamId} in company {companyId}

### Response
``` Java
['ProjectDto']
```

### `POST projects/{companyId}/{teamId}`
Creates a new project and returns the created Project object in the database

### Request
``` Java
'ProjectRequestDto' in the @RequestBody
```

### Response
``` Java
'ProjectDto'
```

### `PATCH projects/{companyId}/{teamId}/{projectId}/`
Edits project {projectId}, saves it in the database and returns it

### Request
``` Java
'ProjectRequestDto' in the @RequestBody
```

### Response
``` Java
['ProjectDto']
```