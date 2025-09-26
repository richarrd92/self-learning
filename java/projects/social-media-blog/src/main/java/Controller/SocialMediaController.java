package Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;


    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }



    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/", this::healthCheckHandler);
        app.get("example-endpoint", this::exampleHandler);
        app.get("/accounts", this::getAllAccountsHandler);
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::getUserAccountHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIDHandler);
        app.delete("messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountHandler);
        
        return app;
    }

    private void healthCheckHandler(Context context) {
        context.result("Server is running!!");
    }


    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


    /**
     * This method retrieves all Account objects from the database and returns them as JSON to the caller.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllAccountsHandler(Context context) {
        context.json(accountService.getAllAccounts());
    }


    /**
     * This method registers a new account to the database.
     * 
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * 
     * The method takes the request body and parses it into an Account object. It then registers the account using the
     * AccountService and returns the registered account as JSON if the registration is successful. If the registration
     * fails, it returns a 400 status code with a message indicating that the account registration failed. If an
     * internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     */
    private void postAccountHandler(Context context) {
        try {
            // Parse the request body into an Account object
            Account account = context.bodyAsClass(Account.class);
            // Register the account
            Account registeredAccount = accountService.registerAccount(account);

            // Return the registered account
            if (registeredAccount != null) {
                context.status(200);
                context.json(registeredAccount);
            } else {
                context.status(400);
                // context.result("Account registration failed");
            }
        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }


    /**
     * This method logs in an account using the credentials provided in the request body.
     * If the login is successful, it returns the logged in account as JSON.
     * If the login fails, it returns a 401 status code with a message indicating that the username or password is invalid.
     * If an internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getUserAccountHandler(Context context) {
        try {
            // parse the request body into account object
            Account loginRequest = context.bodyAsClass(Account.class);

            // attempt login with request content
            Account loggedInAccount = accountService.userLogin(loginRequest.getUsername(), loginRequest.getPassword());

            if (loggedInAccount != null) {
                context.status(200);
                context.json(loggedInAccount);
            } else {
                // Invalid credentials
                context.status(401);
                // context.result("Invalid username or password");
            }
        } catch (Exception e) {
            // Internal server error
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }

    /**
     * This method creates a new message using the request body and inserts it into the database.
     * If the message creation is successful, it returns the created message as JSON.
     * If the message creation fails, it returns a 400 status code with a message indicating that the creation failed.
     * If an internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void postMessageHandler(Context context) {
        try {
            // Parse the request body into message object
            Message createMessageRequest = context.bodyAsClass(Message.class);
            Message createdMessage = messageService.createMessage(createMessageRequest);

            if (createdMessage != null) {
                context.status(200);
                context.json(createdMessage);
            } else {
                context.status(400);
                // context.result("Message creation failed: invalid user or message text");
            }
        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves all Message objects from the database and returns them as JSON to the caller.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    /**
     * Retrieves a message by its ID from the database and returns it as JSON to the caller.
     * If the message is found, it returns the message as JSON.
     * If the message is not found, it returns an empty body.
     * If an internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void getMessageByIDHandler(Context context) {
        try {
            String pathParam = context.pathParam("message_id");
            Integer messageId = null;

            try {
                messageId = Integer.parseInt(pathParam);
            } catch (NumberFormatException e) {
                // Treat invalid ID as no message found
                context.status(200);
                context.result(""); // empty body
                return;
            }

            Object message = messageService.getMessageByID(messageId);

            if (message == null) {
                context.status(200);
                context.result(""); // empty body
            } else {
                context.status(200); // optional, default
                context.json(message); // serialize message as JSON
            }

        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }
    

    /**
     * Deletes a message by its ID from the database.
     * If the message is found and deleted, it returns the deleted message as JSON.
     * If the message is not found, it returns an empty body.
     * If an internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void deleteMessageByIdHandler(Context context) {
        try {
            String pathParam = context.pathParam("message_id");
            Integer messageId = null;

            // Parse ID safely
            try {
                messageId = Integer.parseInt(pathParam);
            } catch (NumberFormatException e) {
                // Invalid ID → treat as "message not found"
                context.status(200);
                context.result(""); // empty body
                return;
            }

            // Fetch the message before deletion
            Message messageToDelete = messageService.getMessageByID(messageId);

            if (messageToDelete == null) {
                // Message did not exist → respond with empty body
                context.status(200);
                context.result("");
            } else {
                // Message exists → delete it
                messageService.deleteMessageById(messageId);
                context.status(200);
                context.json(messageToDelete); // return deleted message
            }

        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }


    /**
     * Updates a message by its ID in the database.
     * 
     * The message text is parsed from the request body, assuming JSON format.
     * If the message text is blank, it returns a 400 status code with a message indicating that the message text cannot be blank.
     * If the message text exceeds 255 characters, it returns a 400 status code with a message indicating that the message text cannot exceed 255 characters.
     * If an internal server error occurs, it returns a 500 status code with a message indicating an internal server error.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    public void updateMessageByIdHandler(Context context) {
        try {
            String pathParam = context.pathParam("message_id");
            Integer messageId = null;

            // Parse ID safely
            try {
                messageId = Integer.parseInt(pathParam);
            } catch (NumberFormatException e) {
                context.status(200);
                context.result(""); // treat as "not found"
                return;
            }

            // Fetch the message before update
            Message messageToUpdate = messageService.getMessageByID(messageId);

            if (messageToUpdate == null) {
                // Message does not exist → respond empty
                context.status(400).result("");
            } else {
                // Extract the new message text from request body (assuming JSON)
                @SuppressWarnings("unchecked")
                Map<String, String> body = context.bodyAsClass(Map.class);
                String newMessageText = body.get("message_text");

                if (newMessageText == null || newMessageText.isBlank()) {
                    context.status(400);
                    // context.result("Message text cannot be blank");
                    return;
                }

                if (newMessageText.length() > 255) {
                    context.status(400);
                    // context.result("Message text cannot exceed 255 characters");
                    return;
                }

                // Update message
                messageService.updateMessageById(messageId, newMessageText);

                // Fetch updated message to return in response
                Message updatedMessage = messageService.getMessageByID(messageId);
                context.status(200).json(updatedMessage);
            }

        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }

    public void getMessagesByAccountHandler(Context context) {
        try {
            // Parse and validate account ID from path
            String pathParam = context.pathParam("account_id");
            Integer accountId;
            try {
                accountId = Integer.parseInt(pathParam);
            } catch (NumberFormatException e) {
                // Invalid account ID → return empty list
                context.status(200).json(Collections.emptyList());
                return;
            }

            // Fetch messages from service
            List<Message> messages = messageService.getMessagesByAccountId(accountId);

            // Return the list (empty if no messages)
            context.status(200).json(messages);

        } catch (Exception e) {
            context.status(500);
            // context.result("Internal server error");
            e.printStackTrace();
        }
    }


}