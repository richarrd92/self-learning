# Javalin: Creating Endpoints
Javalin is a web framework for java. This allows other machines to send HTTP requests to the endpoints that we define 
with Javalin, and to send appropriate HTTP responses based on that specific Request. An endpoint is the pattern in 
the URL which directs our request to the appropriate code. For example:
    www.website.com/this/leads/to/some/code/in/our/app

## Parts of HTTP:
- **HTTP Request**
    - *An HTTP request is how a client computer sends a request to a server computer. This only works if that server 
      computer is set up to receive HTTP requests.*
    - HTTP Requests have 3 important components:
        - **Head**: stores meta-data regarding the request
        - **Body**: stores the important data that the client is trying to give the server
        - **HTTP Verb**: Describes what type of operation we want the server to do.
            - There are many HTTP verbs but let's look at the most common:
                - `GET`: retrieve records from our DB
                - `POST`: create new records in our DB
                - `PUT`: fully update records in our DB
                - `PATCH`: partially update records in our DB
                - `DELETE`: delete records from our DB
- **HTTP Response**
    - An HTTP response is what the server computer sends back to the client computer
    - HTTP Responses have 3 parts as well:
        - **Head**: stores meta-data regarding the response
        - **Body**: stores the important data that the server is trying to return to the client
        - **Status Code**: describes how successful the HTTP Request was
            - There are 5 categories of status code responses:
                - `100`: Request was informational
                - `200`: Request was successful
                - `300`: Request is being redirected
                - `400`: Request failed due to a client-side error
                - `500`: Request failed due to a server-side error

## Steps to Create Endpoints

### Step 1: Creating the Webserver
The first line to any Javalin app is as follows: 
```java
public static void main(String[] args){
    Javalin app = Javalin.create();
    app.start(9000);
}
```

Note: the number 9000 above represents the port that our web application will be running on. This is the port that we will need to send HTTP requests to.


### Step 2: Defining Endpoints
Now that we have a webserver that continuously runs, we need to define endpoints that users and other applications 
can send HTTP Requests to.

The syntax for creating an endpoint is as follows:
```java
public static void main(String[] args){
    Javalin app = Javalin.create();
    app.start(9000);

    // HTTP GET REQUEST to http://localhost:9000/get-request
    app.get("/get-request", ctx -> {
        //logic to be executed when this endpoint is hit
    });


    // HTTP POST REQUEST to http://localhost:9000/post-request
    app.post("/post-request", ctx -> {
        //logic to be executed when this endpoint is hit
    });
}
```

### Step 3: Returning Text
Notice the `ctx` variable. We can utilize this variable to send back data in HTTP response.

```java
public static void main(String[] args){
    Javalin app = Javalin.create().start(9000);

    // HTTP GET REQUEST to http://localhost:9000/get-request
    app.get("/get-request", ctx -> {
        //logic to be executed when this endpoint is hit
        ctx.result("get request endpoint hit!");
    });


    // HTTP POST REQUEST to http://localhost:9000/post-request
    app.post("/post-request", ctx -> {
        //logic to be executed when this endpoint is hit
        ctx.result("post request endpoint hit!");
    });
}
```

The `ctx.result();` method allows us to return data in the HTTP response body. 

# Javalin: Changing the Status Code in an HTTP Response

Status codes are how we tell the client machine how successful the HTTP 
Request was. HTTP Responses have 5 main categories of status codes:

There are 5 categories of status code responses:
- `100`: Request was informational
- `200`: Request was successful
- `300`: Request is being redirected
- `400`: Request failed due to a client-side error
- `500`: Request failed due to a server-side error


## Changing the status code in Javalin

```java
app.get("/request-was-accepted", ctx -> {
    ctx.status(202);
});
```

The only line we need to add to achieve this is `ctx.status(code);` Appropriate codes should be sent back. For instance:
 - 500 represents a server-side error - you should look to the server for a stack trace or other debugging info
 - 400 represents a client-side error
   - 404 This means your request did not match any endpoint.
   - 415 Could mean that the body has an unrecognized content type.


# Javalin: Retrieving Data from Path Parameters

How to retrieve data from the HTTP requests.

The most common ways to transmit data in an HTTP request are:
- Path parameters
- HTTP Request body

## Path Parameters

Path parameters are variables that are in the URL path. For example, Lets say we define the following endpoint:

```java
app.get("/lastname/{name}", ctx -> {
    String lastName = ctx.pathParam("name");
});
```

The curly braces in the url above defines a variable. A client can now send a request to the above endpoint in the 
following with any name in that spot:

- http://localhost:9000/lastname/Jones
- http://localhost:9000/lastname/Smith


The `ctx.pathParam()` method above is used to retrieve this variable in java. The variable name and the value that's 
put in the parameter of the `ctx.pathParam()` method must be identical. In this case the variable was called `name`.


# Javalin: Retrieving Data from the body of an HTTP Request

How to retrieve data from the HTTP requests.

The most common ways to retrieve data from the HTTP requests:
- Path parameters
- HTTP Request body

## HTTP Request Body

Clients can send data in the body of an HTTP request. The data is often stored in a JSON format.

Jackson utilizes a class called `ObjectMapper` and you can instantiate it as shown below:
```java
ObjectMapper om = new ObjectMapper();
```

There are 2 methods defined in the `ObjectMapper` class that we will use:

- `om.readValue()`: converts json strings to java objects

- `om.writeValueAsString()`: converts java objects to json strings

## Steps to retrieve data from a JSON String to a Java object

Lets say we have the following POST endpoint which will send us a request with a body containing a JSON of a user shown 
above. We could get the json string in a String variable by utilizing the `ctx.body()` method.

```java
app.post("/user", ctx -> {
    String jsonString = ctx.body();
});
```

This is a good start, but we can utilize jackson to convert the request body into a java object.

```java
app.post("/user", ctx -> {
    //retrieve the json string from the request body
    String jsonString = ctx.body();

    //utilize jackson to convert the json string to a user object
    ObjectMapper om = new ObjectMapper();
    User user = om.readValue(jsonString, User.class);
    
    //now we can use the 'user' response body as a Java object in whatever way we see fit.
        
    //eg: return the user as the response body, but also have Javalin convert it to JSON  
    ctx.json(user);    
});
```

NOTE: the `om.readValue()` method above takes in 2 arguments:

1. the json string that we are trying to convert

2. the class of the datatype that we are trying to convert the json string to



Now that we have the data in a java object, we can manipulate anyway we see fit. Let's say I want to change the first 
name from "Jobs" to "Jones" and then send the data back as a json string:

```java
app.post("/user", ctx ->{
        //retrieve the json string from the request body
        String jsonString=ctx.body();

        //utilize jackson to convert the json string to a user object
        ObjectMapper om=new ObjectMapper();
        User user=om.readValue(jsonString,User.class);

        //change the last name
        user.setLastname("Jones");

        //generate an HTTP response with the user object in the response body as a JSON.
        ctx.json(user);
        });
```