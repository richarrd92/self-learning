package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    /**
     * Creates a new message and inserts it into the database.
     * 
     * @param message The message to be created.
     * @return The created message if successful, otherwise null.
     */
    public Message createMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sqlStatement = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();

            ResultSet primaryKeyResultSet = preparedStatement.getGeneratedKeys();
            if (primaryKeyResultSet.next()){
                int generated_message_id = (int) primaryKeyResultSet.getInt(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(),
                        message.getTime_posted_epoch());
            }
        } catch (Exception e) {
            System.out.println("Error creating message");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves all messages from the database.
     * 
     * @return A list of Message objects representing all messages in the database.
     */
    public List<Message> getAllMessages() {
        List<Message> messagesArrList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlStatement = "SELECT * FROM message;"; // select all messages

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the results
            while (resultSet.next()) {
                Message message = new Message(
                        resultSet.getInt("message_id"),
                        resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"),
                        resultSet.getLong("time_posted_epoch")
                );
                messagesArrList.add(message);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving messages");
            e.printStackTrace();
        }

        return messagesArrList;
    }



    /**
     * Finds a message by message ID.
     * @param message_id the message ID to search for
     * @return the Message if found, otherwise null
     */
    public Message getMessageByID(int message_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            String sqlStatement = "SELECT * FROM message WHERE message_id = ?;";
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, message_id);
            resultSet = preparedStatement.executeQuery();

            // if message exists
            if (resultSet.next()) {
                return new Message(
                        resultSet.getInt("message_id"),
                        resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"),
                        resultSet.getLong("time_posted_epoch"));
            }
        } catch (Exception e) {
            System.out.println("Error finding message by message_id");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes a message by its ID from the database.
     * @param message_id the message ID of the message to be deleted
     */
    public void deleteMessageById(int message_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sqlStatement = "DELETE FROM message WHERE message_id = ?;";

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, message_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting message");
            e.printStackTrace();
        }
    }
    

    /**
     * Updates a message by its ID in the database.
     * 
     * @param message_id The message ID to update.
     * @param message_text The new message text.
     */
    public void updateMessageById(int message_id, String message_text) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sqlStatement = "UPDATE message SET message_text = ? WHERE message_id = ?;";

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating message");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all messages from the database that were posted by a specific user.
     * The messages are ordered in descending order by time posted.
     * 
     * @param accountId the user ID to search for
     * @return a list of Message objects representing all messages posted by the user
     */
    public List<Message> getMessagesByAccountId(int accountId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE posted_by = ? ORDER BY time_posted_epoch DESC;";

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

}
