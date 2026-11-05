package Service;

import DAO.AccountDAO;
import Model.Account;
import java.util.List;

public class AccountService {

    private AccountDAO accountDAO;

    /**
     * no-args constructor for creating a new AccountService with a new AccountDAO.
     * There is no need to change this constructor.
     */
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AccountService independently of AccountDAO.
     * There is no need to modify this constructor.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * Retrieves all Account objects from the database.
     * 
     * @return a list of all Account objects in the database.
     */
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    /**
     * Registers a new account to the database.
     * 
     * @param account The account to be registered.
     * @return The registered account.
     * @throws IllegalArgumentException If the account is null, the username is blank, the password is less than 4 characters, or the username already exists.
     */
    public Account registerAccount(Account account) {
        if (account == null) {
            System.out.println("Account cannot be null");
            return null;
        }

        // Trim username and password before persisting
        String username = account.getUsername() != null ? account.getUsername().trim() : "";
        String password = account.getPassword() != null ? account.getPassword().trim() : "";

        // Validate
        if (username.isEmpty()) {
            System.out.println("Username cannot be blank");
            return null;
        }

        if (password.length() < 4) {
            System.out.println("Password must be at least 4 characters");
            return null;
        }

        // Check if username already exists
        if (accountDAO.existsByUsername(username)) {
            System.out.println("Username already exists");
            return null;
        }

        // Set trimmed username and password
        account.setUsername(username);
        account.setPassword(password);

        // Persist and return account
        System.out.println("Registering account: " + account);
        return accountDAO.registerAccount(account);
    }

    /**
     * Attempts to log in an account with the given username and password.
     * 
     * @param username The username of the account to log in.
     * @param password The password of the account to log in.
     * @return The account if the login was successful, or null if the login failed.
     */
    public Account userLogin(String username, String password) {
        // Find the account by username
        Account account = accountDAO.findByUsername(username);

        if (account != null && account.getPassword().equals(password)) {
            return account; // successful login
        }

        return null; // failed login
    }

    
}
