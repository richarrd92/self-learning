package Service;

import DAO.MessageDAO;

import java.util.List;

import DAO.AccountDAO;
import Model.Message;
import Model.Account;

public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountDAO accountDAO; // needed for validation

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }


    /**
     * Creates a new message and inserts it into the database.
     * 
     * @param message The message to be created.
     * @return The created message if successful, otherwise null.
     */
    public Message createMessage(Message message) {
        // message can not be null
        if (message == null) {
            return null;
        }

        // Validate user exits using accountDAO
        Account messagePoster = accountDAO.findbyID(message.getPosted_by());
        if (messagePoster == null) {
            System.out.println("User does not exist");
            return null;
        }

        // Validate message text
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            System.out.println("Message text cannot be empty");
            return null;
        }

        message.setMessage_text(message.getMessage_text().trim());

        // Persist using MessageDAO
        return messageDAO.createMessage(message);
    }

    /**
     * Retrieves all Message objects from the database.
     * 
     * @return a list of Message objects representing all messages in the database.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * Finds a message by message ID.
     * @param message_id the message ID to search for
     * @return the Message if found, otherwise null
     */
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }


    /**
     * Deletes a message by its ID from the database.
     * @param message_id the message ID to delete
     */
    public void deleteMessageById(int message_id) {
        messageDAO.deleteMessageById(message_id);
    }

    /**
     * Updates a message by its ID in the database.
     * 
     * @param message_id The message ID to update.
     * @param message_text The new message text.
     */
    public void updateMessageById(int message_id, String message_text) {
        messageDAO.updateMessageById(message_id, message_text);
    }

    /**
     * Retrieves all messages from the database that were posted by a specific user.
     * 
     * @param posted_by the user ID to search for
     * @return a list of Message objects representing all messages posted by the user
     */
    public List<Message> getMessagesByAccountId(int accountId) {
        return messageDAO.getMessagesByAccountId(accountId);
    }
}
