import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class libraryManager {

    static Scanner myObj = new Scanner(System.in);
    static Scanner multObj = new Scanner(System.in);
    static Connection connection = null;

    public static void main(String args[]){
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/simpledb";
            String user = "root";
            String password = "AZNdomination13";
            connection = DriverManager.getConnection(connectionURL, user, password);

            /*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_library_branch");

            while(resultSet.next()){
                System.out.println(resultSet.getString("branchName") + " Library");
            }*/
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("Welcome to the SS Library Management System. Which category of a user are you\n1)\tLibrarian\n2)\tAdministrator\n3)\tBorrower");
        int userCategory = myObj.nextInt();
        switch(userCategory){
            case 1:
                lib1();
                break;
            case 2:
                admin1();
                break;
            case 3:
                borr1();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void lib1(){
        System.out.println("1)\tEnter Branch you manage\n" + "2)\tQuit to previous");
        int lib1choice = myObj.nextInt();
        switch(lib1choice){
            case 1:
                lib2();
                break;
            case 2:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    public static void lib2(){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_library_branch");
            int counter = 1;
            while(resultSet.next()){
                System.out.println(counter++ + ") " + resultSet.getString("branchName") + " Library");
            }
            System.out.println(counter + ") Quit to previous");
            int branchSelection = myObj.nextInt();
            if(branchSelection == counter){lib1();}
            else{lib3(branchSelection);}
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void lib3(int branchId){
        System.out.println("1)\tUpdate the details of the Library \n" + "2)\tAdd copies of Book to the Branch\n" + "3)\tQuit to previous");
        int branchChoice = myObj.nextInt();
        switch (branchChoice){
            case 1:
                String branchName = null;
                String branchAddress = null;
                try{
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT branchName FROM tbl_library_branch WHERE branchId = " + branchId);
                    resultSet.next();
                    branchName = resultSet.getString("branchName");

                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("You have chosen to update the Branch with Branch Id: " + branchId + " and Branch Name: " + branchName + ". Enter ‘quit’ at any prompt to cancel operation.");
                System.out.println("Please enter new branch name or enter N/A for no change:");
                String branchNameChange = multObj.nextLine();
                if(branchNameChange.equals("quit")){break;}
                System.out.println("Please enter new branch address or enter N/A for no change:");
                String branchAddressChange = multObj.nextLine();
                if(branchAddressChange.equals("quit")){break;}

                String queryStatement = "";
                if(!branchNameChange.equals("N/A") && !branchAddressChange.equals("N/A")){
                    queryStatement = "UPDATE tbl_library_branch SET branchName = \'" + branchNameChange + "\', branchAddress = \'" + branchAddressChange + "\' WHERE branchId = " + branchId;
                }
                else if(!branchAddressChange.equals("N/A")){
                    queryStatement = "UPDATE tbl_library_branch SET branchAddress = \'" + branchAddressChange + "\' WHERE branchId = " + branchId;
                }
                else if(!branchNameChange.equals("N/A")){
                    queryStatement = "UPDATE tbl_library_branch SET branchName = \'" + branchNameChange + "\' WHERE branchId = " + branchId;
                }
                else{lib3(branchId); break;}
                try{
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(queryStatement);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                lib3(branchId);
                break;
            case 2:
                try{
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT title from tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = " + branchId + ")");
                    int counter = 1;
                    System.out.println("Pick the Book you want to add copies of, to your branch:");
                    while(resultSet.next()) {
                        System.out.println(counter++ + ") " + resultSet.getString("title"));
                    }
                    System.out.println(counter + ") Quit to cancel operation");
                    int bookChoice = myObj.nextInt();
                    if(bookChoice==counter){lib3(branchId); break;}

                    resultSet = statement.executeQuery("SELECT bookId from tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = " + branchId + ")");
                    for(int i = 0; i<bookChoice; i++){resultSet.next();}
                    int bookId = resultSet.getInt("bookId");
                    resultSet = statement.executeQuery("SELECT noOfCopies FROM tbl_book_copies WHERE branchId = " + branchId + " AND bookId = " + bookId);
                    resultSet.next();
                    System.out.println("Existing number of copies: " + resultSet.getInt("noOfCopies"));

                    System.out.print("Enter new number of copies: ");
                    int newNoOfCopies = myObj.nextInt();
                    statement.executeUpdate("UPDATE tbl_book_copies SET noOfCopies = " + newNoOfCopies + " WHERE bookId = " + bookId + " AND branchId = " + branchId);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                lib3(branchId);
                break;
            case 3:
                lib2();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void admin1(){
        System.out.print("ADMIN OPERATIONS:\n" +
                "1)\tAdd/Update/Delete/Read Book and Author\n" +
                "2)\tAdd/Update/Delete/Read Publishers\n" +
                "3)\tAdd/Update/Delete/Read Library Branches\n" +
                "4)\tAdd/Update/Delete/Read Borrowers\n" +
                "5)\tOver-ride Due Date for a Book Loan\n" +
                "6)\tQuit to previous\n");
                int adminchoice = myObj.nextInt();
                switch(adminchoice){
                    case 1:
                        adminBookAuthor();
                        break;
                    case 2:
                        adminPublisher();
                        break;
                    case 3:
                        adminBranch();
                        break;
                    case 4:
                        adminBorrower();
                        break;
                    case 5:
                        adminOverride();
                        break;
                    case 6:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
    }
    public static void adminBookAuthor(){
        System.out.println("What would you like to do with Book and Author:\n1) Add\n2) Update\n3) Read\n4) Delete");
        int bookAuthorChoice = myObj.nextInt();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            int bookId = 0;
            switch(bookAuthorChoice) {
                case 1:
                    resultSet = statement.executeQuery("SELECT MAX(bookId) FROM tbl_book");
                    resultSet.next();
                    bookId = resultSet.getInt(1) + 1;
                    System.out.println("Enter your book title:");
                    String bookTitle = multObj.nextLine();
                    System.out.println("Enter your author: ");
                    String authorName = multObj.nextLine();
                    int newAuthorId = 0;
                    resultSet = statement.executeQuery("SELECT authorId FROM tbl_author WHERE authorName = \'" + authorName + "\'");
                    if(!resultSet.next()){
                        resultSet = statement.executeQuery("SELECT MAX(authorId) FROM tbl_author");
                        resultSet.next();
                        newAuthorId = resultSet.getInt(1) + 1;
                        statement.executeUpdate("INSERT INTO tbl_author (authorId, authorName) VALUES (" + newAuthorId + ",\'" + authorName + "\')");
                    }
                    else{
                        newAuthorId = resultSet.getInt(1);
                    }
                    //System.out.println(newAuthorId);
                    System.out.println("Enter your publisher name: ");
                    String publisherName = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT publisherId FROM tbl_publisher WHERE publisherName = \'" + publisherName + "\'");
                    while(!resultSet.next()){
                        System.out.println("Invalid publisher name. Enter an existing publisher.");
                        publisherName = multObj.nextLine();
                        resultSet = statement.executeQuery("SELECT publisherId FROM tbl_publisher WHERE publisherName = \'" + publisherName + "\'");
                    }
                    int publisherId = resultSet.getInt(1);
                    statement.executeUpdate("INSERT INTO tbl_book (bookId, title, authId, pubId) VALUES(" + bookId + ",\'" + bookTitle + "\'," +
                            newAuthorId + "," + publisherId + ")");
                    break;
                case 2:
                    System.out.println("Enter Book ID:");
                    bookId = myObj.nextInt();
                    System.out.println("Enter new book title:");
                    String newBookTitle = multObj.nextLine();

                    System.out.println("Enter new book author:");
                    String newAuthorName = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT authorId FROM tbl_author WHERE authorName = \'" + newAuthorName + "\'");
                    while(!resultSet.next()){
                        System.out.println("Invalid author. Reenter an existing author.");
                        newAuthorName = multObj.nextLine();
                        resultSet = statement.executeQuery("SELECT authorId FROM tbl_author WHERE authorName = \'" + newAuthorName + "\'");
                    }
                    int authId = resultSet.getInt("authorId");

                    System.out.println("Enter new book publisher:");
                    String newPublisherName = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT publisherId FROM tbl_publisher WHERE publisherName = \'" + newPublisherName + "\'");
                    while(!resultSet.next()){
                        System.out.println("Invalid publisher. Reenter an existing publisher.");
                        newPublisherName = multObj.nextLine();
                        resultSet = statement.executeQuery("SELECT publisherId FROM tbl_publisher WHERE publisherName = \'" + newPublisherName + "\'");
                    }
                    int pubId = resultSet.getInt("publisherId");

                    statement.executeUpdate("UPDATE tbl_book SET title = \'" + newBookTitle + "\', authId = \'" + authId + "\', pubId = \'" + pubId + "\' WHERE bookId = " + bookId);
                    break;
                case 3:
                    System.out.println("Enter Book ID:");
                    bookId = myObj.nextInt();
                    resultSet = statement.executeQuery("SELECT * FROM tbl_book JOIN tbl_author ON tbl_book.authId = tbl_author.authorId WHERE bookId = " + bookId);
                    while(resultSet.next()){
                        System.out.println("Title: " + resultSet.getString("title") + "\tAuthor: " + resultSet.getString("authorName"));
                    }
                    break;
                case 4:
                    System.out.println("Enter Book ID:");
                    bookId = myObj.nextInt();
                    statement.executeUpdate("DELETE FROM tbl_book WHERE bookId = " + bookId);
                    break;
                default:
                    break;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void adminPublisher(){
        System.out.println("What would you like to do with Publishers:\n1) Add\n2) Update\n3) Read\n4) Delete");
        int publisherChoice = myObj.nextInt();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            String publisherId = null;
            switch(publisherChoice) {
                case 1:
                    System.out.println("Enter Publisher Name:");
                    String publisherName = multObj.nextLine();
                    System.out.println("Enter Publisher Address:");
                    String publisherAddress = multObj.nextLine();
                    System.out.println("Enter Publisher Phone:");
                    String publisherPhone = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT MAX(publisherId) FROM tbl_publisher;");
                    resultSet.next();
                    int newPublisherId = resultSet.getInt(1) + 1;
                    statement.executeUpdate("INSERT INTO tbl_publisher (publisherId, publisherName,publisherAddress,publisherPhone) VALUES (" +
                            newPublisherId + ",\'" + publisherName + "\',\'"+publisherAddress+"\',\'"+publisherPhone+"\')");
                    break;
                case 2:
                    System.out.println("Enter Publisher ID:");
                    publisherId = myObj.next();
                    System.out.println("Enter new Publisher Name: ");
                    String newPublisherName = multObj.nextLine();
                    System.out.println("Enter new Publisher Address: ");
                    String newPublisherAddress = multObj.nextLine();
                    System.out.println("Enter new Publisher Phone: ");
                    String newPublisherPhone = multObj.nextLine();
                    statement.executeUpdate("UPDATE tbl_publisher SET publisherName = \'" + newPublisherName + "\', publisherAddress = \'" + newPublisherAddress +
                            "\', publisherPhone = \'" + newPublisherPhone + "\' WHERE publisherId = " + publisherId);
                    break;
                case 3:
                    System.out.println("Enter Publisher ID:");
                    publisherId = myObj.next();
                    resultSet = statement.executeQuery("SELECT * FROM tbl_publisher WHERE publisherId = " + publisherId);
                    while(resultSet.next()){
                        System.out.println("Publisher Name: " + resultSet.getString("publisherName") + "\tPublisher Address: " + resultSet.getString("publisherAddress") + "\tPublisher Phone: " + resultSet.getString("publisherPhone"));
                    }
                    break;
                case 4:
                    System.out.println("Enter Publisher ID:");
                    int removePublisherId = myObj.nextInt();
                    statement.executeUpdate("DELETE FROM tbl_publisher WHERE publisherId = " + removePublisherId);
                    break;
                default:
                    break;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void adminBranch(){
        System.out.println("What would you like to do with Library Branches:\n1) Add\n2) Update\n3) Read\n4) Delete");
        int branchChoice = myObj.nextInt();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            String branchId = null;
            switch(branchChoice) {
                case 1:
                    System.out.println("Enter Branch Name: ");
                    String branchName = multObj.nextLine();
                    System.out.println("Enter Branch Address: ");
                    String branchAddress = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT MAX(branchId) FROM tbl_library_branch;");
                    resultSet.next();
                    int newBranchId = resultSet.getInt(1) + 1;
                    statement.executeUpdate("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (" + newBranchId + ",\'" + branchName +
                            "\', \'" + branchAddress + "\')");
                    break;
                case 2:
                    System.out.println("Enter Branch ID:");
                    branchId = myObj.next();
                    System.out.println("Enter new Branch Name: ");
                    String newBranchName = multObj.nextLine();
                    System.out.println("Enter new Branch Address: ");
                    String newBranchAddress = multObj.nextLine();
                    statement.executeUpdate("UPDATE tbl_library_branch SET branchName = \'" + newBranchName + "\', branchAddress = \'" + newBranchAddress +
                            "\' WHERE branchId = " + branchId);
                    break;
                case 3:
                    System.out.println("Enter Branch ID:");
                    branchId = myObj.next();
                    resultSet = statement.executeQuery("SELECT * FROM tbl_library_branch WHERE branchId = " + branchId);
                    while(resultSet.next()){
                        System.out.println("Branch Name: " + resultSet.getString("branchName") + "\tBranch Address: " + resultSet.getString("branchAddress"));
                    }
                    break;
                case 4:
                    System.out.println("Enter Branch ID:");
                    int removeBranchId = myObj.nextInt();
                    statement.executeUpdate("DELETE FROM tbl_library_branch WHERE branchId = " + removeBranchId);
                    break;
                default:
                    break;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void adminBorrower(){
        System.out.println("What would you like to do with Borrowers:\n1) Add\n2) Update\n3) Read\n4) Delete");
        int borrowerChoice = myObj.nextInt();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            int cardNo = 0;
            switch(borrowerChoice) {
                case 1:
                    System.out.println("Enter Borrower Name: ");
                    String borrowerName = multObj.nextLine();
                    System.out.println("Enter Borrower Address: ");
                    String borrowerAddress = multObj.nextLine();
                    System.out.println("Enter Borrower Phone: ");
                    String borrowerPhone = multObj.nextLine();
                    resultSet = statement.executeQuery("SELECT MAX(cardNo) FROM tbl_borrower");
                    resultSet.next();
                    int newCardNo = resultSet.getInt(1) + 1;
                    statement.executeUpdate("INSERT INTO tbl_borrower (cardNo, name, address,phone) VALUES (" + newCardNo + ",\'" + borrowerName + "\',\'" +
                            borrowerAddress + "\',\'" + borrowerPhone +"\')");
                    break;
                case 2:
                    System.out.println("Enter Card Number:");
                    cardNo = myObj.nextInt();
                    System.out.println("Enter new Name:");
                    String newName = multObj.nextLine();
                    System.out.println("Enter new Address:");
                    String newAddress = multObj.nextLine();
                    System.out.println("Enter new Phone:");
                    String newPhone = multObj.nextLine();
                    statement.executeUpdate("UPDATE tbl_borrower SET name = \'" + newName + "\', address = \'" + newAddress + "\', phone = \'" + newPhone + "\' WHERE cardNo = " + cardNo);
                    break;
                case 3:
                    System.out.println("Enter Card Number:");
                    cardNo = myObj.nextInt();
                    resultSet = statement.executeQuery("SELECT * FROM tbl_borrower WHERE cardNo = " + cardNo);
                    while(resultSet.next()){
                        System.out.println("Name: " + resultSet.getString("name") + "\tAddress: " + resultSet.getString("address") +
                                "\tPhone: " + resultSet.getString("phone"));
                    }
                    break;
                case 4:
                    System.out.println("Enter Card Number:");
                    int removedCardNo = myObj.nextInt();
                    statement.executeUpdate("DELETE FROM tbl_borrower WHERE cardNo = " + removedCardNo);
                    break;
                default:
                    break;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void adminOverride(){
        System.out.println("Enter Borrower Card Number: ");
        int cardNum = myObj.nextInt();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = " + cardNum + ")");
            int counter = 1;
            while(resultSet.next())
                System.out.println(counter++ + ") " + resultSet.getString("title"));
            int bookChoice = myObj.nextInt();
            System.out.println("Enter new due date:");
            String newDueDate = myObj.next();
            resultSet = statement.executeQuery("SELECT bookId from tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = " + cardNum + ")");
            for(int i = 0; i<bookChoice; i++){resultSet.next();}
            int bookId = resultSet.getInt("bookId");
            statement.executeUpdate("UPDATE tbl_book_loans SET dueDate = CAST(\'" + newDueDate + "\' AS DATETIME) WHERE cardNo = " + cardNum + " AND bookId = " + bookId);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void borr1(){
        System.out.println("Enter your Card Number:");
        int cardNum = myObj.nextInt();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_borrower WHERE cardNo = " + cardNum);
            if(resultSet.next()){
                System.out.println("1)\tCheck out a book\n" +
                        "2)\tReturn a Book\n" +
                        "3)\tQuit to Previous");
                int borrowerChoice = myObj.nextInt();
                switch(borrowerChoice){
                    case 1:
                        System.out. println("Pick the Branch you want to check out from: ");
                        resultSet = statement.executeQuery("SELECT branchName FROM tbl_library_branch");
                        int counter1 = 1;
                        while(resultSet.next()){
                            System.out.println(counter1++ + ") " + resultSet.getString("branchName") + " Library");
                        }
                        System.out.println(counter1 + ") Quit to previous");
                        int branchChoice = myObj.nextInt();
                        if(branchChoice == counter1){borr1(); break;}
                        resultSet = statement.executeQuery("SELECT title FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = " + branchChoice + ")");
                        int counter2 = 1;
                        System.out.println("Pick the Book you want to check out");
                        while(resultSet.next()){
                            System.out.println(counter2++ + ") " + resultSet.getString("title"));
                        }

                        int bookChoice = myObj.nextInt();
                        resultSet = statement.executeQuery("SELECT bookId from tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = " + branchChoice + ")");
                        for(int i = 0; i<bookChoice; i++){resultSet.next();}
                        statement.executeUpdate("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (" + resultSet.getInt("bookId") + "," + branchChoice + "," + cardNum + ", CURDATE(), (SELECT DATE_ADD(CURDATE(), INTERVAL 10 DAY)))");
                        //System.out.println("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (" + resultSet.getInt("bookId") + "," + branchChoice + "," + cardNum + ", CURDATE(), SELECT DATE_ADD(CURDATE(), INTERVAL 10 DAY))");
                        break;
                    case 2:
                        System.out.println("Which book do you wish to return: ");
                        Statement statement1 = connection.createStatement();
                        ResultSet resultSet1 = statement1.executeQuery("SELECT title FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = " + cardNum + ")");
                        int counter = 1;
                        while(resultSet1.next())
                            System.out.println(counter++ + ") " + resultSet1.getString("title"));
                        int bookChoice1 = myObj.nextInt();
                        resultSet = statement.executeQuery("SELECT bookId from tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = " + cardNum + ")");
                        for(int i = 0; i<bookChoice1; i++){resultSet.next();}
                        int bookId = resultSet.getInt("bookId");
                        statement1.executeUpdate("UPDATE tbl_book_loans SET dateIn = CURDATE() WHERE cardNo = " + cardNum + " AND bookId = " + bookId);
                        break;
                    case 3:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
            else{System.out.println("Card Number not found. Try again."); borr1();}
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
