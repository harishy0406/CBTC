 import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

// User class representing both Admin and regular Users
class User implements Serializable {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

// Book class representing details of each book
class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(int id, String title, String author, String genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    public Book(String string, String string2, String string3) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Member class representing details of library members
class Member implements Serializable {
    private int memberId;
    private String name;
    private String contact;

    public Member(int memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

// Banner class to display the introductory banner
class Banner {
    public static void display() {
        System.out.println("******************************");
        System.out.println("*** Digital Library System ***");
        System.out.println("******************************");
    }
}

// FileHandler class to handle data serialization and deserialization
class FileHandler {
    private static final String USERS_FILE = "users.dat";
    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE = "members.dat";

    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No users found. Creating a new list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No books found. Creating a new list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE))) {
            members = (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No members found. Creating a new list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return members;
    }

    public static void saveMembers(List<Member> members) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// AdminDashboard class to manage administrative tasks
class AdminDashboard {
    private List<Book> books;
    private List<Member> members;

    public AdminDashboard(List<Book> books, List<Member> members) {
        this.books = books;
        this.members = members;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            Banner.display();
            System.out.println("******************************");
            System.out.println("***   Admin Dashboard   ***");
            System.out.println("******************************");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. Fine Generation");
            System.out.println("6. Advance Booking Management");
            System.out.println("7. Report Generation");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                    manageMembers();
                    break;
                case 3:
                    issueBooks();
                    break;
                case 4:
                    returnBooks();
                    break;
                case 5:
                    generateFines();
                    break;
                case 6:
                    manageAdvanceBooking();
                    break;
                case 7:
                    generateReports();
                    break;
                case 8:
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8.");
            }
        } while (choice != 8);
    }

    private void manageBooks() {
        Scanner scanner = new Scanner(System.in);
        int choice;
    
        do {
            System.out.println("******************************");
            System.out.println("Manage Books:");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    System.out.println("Returning to Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        } while (choice != 4);
    }
    
    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Is the book available? (true/false): ");
        boolean available = scanner.nextBoolean();
    
        // Create a new Book object
        Book newBook = new Book(id, title, author, genre, available);
    
        // Add the new book to the books list
        books.add(newBook);
        System.out.println("Book added successfully!");
    }
    
    private void updateBook() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Find the book by ID
        Book foundBook = null;
        for (Book book : books) {
            if (book.getId() == id) {
                foundBook = book;
                break;
            }
        }
    
        if (foundBook != null) {
            // Prompt admin to enter updated details
            System.out.print("Enter new Book Title (or press Enter to skip): ");
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                foundBook.setTitle(title);
            }
    
            System.out.print("Enter new Author Name (or press Enter to skip): ");
            String author = scanner.nextLine();
            if (!author.isEmpty()) {
                foundBook.setAuthor(author);
            }
    
            System.out.print("Enter new Genre (or press Enter to skip): ");
            String genre = scanner.nextLine();
            if (!genre.isEmpty()) {
                foundBook.setGenre(genre);
            }
    
            System.out.print("Update availability? (true/false, or press Enter to skip): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                boolean available = Boolean.parseBoolean(input);
                foundBook.setAvailable(available);
            }
    
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
    
    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Find the book by ID
        Book foundBook = null;
        for (Book book : books) {
            if (book.getId() == id) {
                foundBook = book;
                break;
            }
        }
    
        if (foundBook != null) {
            books.remove(foundBook);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
    

    private void manageMembers() {
        Scanner scanner = new Scanner(System.in);
        int choice;
    
        do {
            System.out.println("******************************");
            System.out.println("Manage Members:");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    updateMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    System.out.println("Returning to Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        } while (choice != 4);
    }
    
    private void addMember() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt admin to enter member details
        System.out.print("Enter Member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Details: ");
        String contactDetails = scanner.nextLine();
        System.out.print("Is the member active? (true/false): ");
        boolean active = scanner.nextBoolean();
    
        // Create a new Member object
        Member newMember = new Member(id, name, contactDetails, active);
    
        // Add the new member to the members list
        members.add(newMember);
        System.out.println("Member added successfully!");
    }
    
    private void updateMember() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Member ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Find the member by ID
        Member foundMember = null;
        for (Member member : members) {
            if (member.getId() == id) {
                foundMember = member;
                break;
            }
        }
    
        if (foundMember != null) {
            // Prompt admin to enter updated details
            System.out.print("Enter new Member Name (or press Enter to skip): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                foundMember.setName(name);
            }
    
            System.out.print("Enter new Contact Details (or press Enter to skip): ");
            String contactDetails = scanner.nextLine();
            if (!contactDetails.isEmpty()) {
                foundMember.setContactDetails(contactDetails);
            }
    
            System.out.print("Update membership status? (true/false, or press Enter to skip): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                boolean active = Boolean.parseBoolean(input);
                foundMember.setActive(active);
            }
    
            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }
    
    private void deleteMember() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Member ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Find the member by ID
        Member foundMember = null;
        for (Member member : members) {
            if (member.getId() == id) {
                foundMember = member;
                break;
            }
        }
    
        if (foundMember != null) {
            members.remove(foundMember);
            System.out.println("Member deleted successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }
    

    private void issueBooks() {
        Scanner scanner = new Scanner(System.in);
    
        // Display available books
        displayAvailableBooks();
    
        // Select a member
        Member selectedMember = selectMember();
    
        if (selectedMember != null) {
            // Prompt admin to enter book ID to issue
            System.out.print("Enter Book ID to issue: ");
            int bookId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            // Find the book by ID
            Book foundBook = null;
            for (Book book : books) {
                if (book.getId() == bookId && book.isAvailable()) {
                    foundBook = book;
                    break;
                }
            }
    
            if (foundBook != null) {
                // Issue the book to the selected member
                foundBook.setAvailable(false); // Mark as not available
                foundBook.setIssuedTo(selectedMember.getId()); // Record member ID who issued it
                System.out.println("Book '" + foundBook.getTitle() + "' issued to " + selectedMember.getName());
            } else {
                System.out.println("Book not available or does not exist.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }
    
    private void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("ID: " + book.getId() + " | Title: " + book.getTitle() + " | Author: " + book.getAuthor());
            }
        }
    }
    
    private Member selectMember() {
        Scanner scanner = new Scanner(System.in);
        Member selectedMember = null;
    
        // Display list of members
        displayMembers();
    
        // Prompt admin to enter member ID to issue book
        System.out.print("Enter Member ID to issue book: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        // Find the member by ID
        for (Member member : members) {
            if (member.getId() == memberId) {
                selectedMember = member;
                break;
            }
        }
    
        return selectedMember;
    }
    
    private void displayMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println("ID: " + member.getId() + " | Name: " + member.getName() + " | Contact: " + member.getContactDetails());
        }
    }
    

    private void returnBooks() {
        Scanner scanner = new Scanner(System.in);
    
        // Display issued books
        displayIssuedBooks();
    
        // Select a member
        Member selectedMember = selectMember();
    
        if (selectedMember != null) {
            // Prompt admin to enter book ID to return
            System.out.print("Enter Book ID to return: ");
            int bookId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            // Find the book by ID
            Book returnedBook = null;
            for (Book book : books) {
                if (book.getId() == bookId && book.getIssuedTo() == selectedMember.getId()) {
                    returnedBook = book;
                    break;
                }
            }
    
            if (returnedBook != null) {
                // Process return of the book
                returnedBook.setAvailable(true); // Mark as available
                returnedBook.setIssuedTo(-1); // Clear issued to member ID
                System.out.println("Book '" + returnedBook.getTitle() + "' returned by " + selectedMember.getName());
    
                // Check for fines and update member records
                processReturnFines(selectedMember, returnedBook);
            } else {
                System.out.println("Book not issued to the selected member or does not exist.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }
    
    private void displayIssuedBooks() {
        System.out.println("Issued Books:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                Member issuedTo = findMemberById(book.getIssuedTo());
                System.out.println("ID: " + book.getId() + " | Title: " + book.getTitle() + " | Issued to: " + (issuedTo != null ? issuedTo.getName() : "Unknown"));
            }
        }
    }
    
    private Member selectMember() {
        Scanner scanner = new Scanner(System.in);
        Member selectedMember = null;
    
        // Display list of members
        displayMembers();
    
        // Prompt admin to enter member ID returning book
        System.out.print("Enter Member ID returning book: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        // Find the member by ID
        selectedMember = findMemberById(memberId);
    
        return selectedMember;
    }
    
    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }
    
    private void processReturnFines(Member member, Book returnedBook) {
        // Implement fine calculation logic here if required
        // Update member's fine record or balance accordingly
        // This is a placeholder for fine calculation and adjustment
        System.out.println("Processing return fines...");
        // Example logic:
        // Check if the book is overdue and calculate fine
        // Update member's fine record or balance accordingly
    }
    
    private void displayMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println("ID: " + member.getId() + " | Name: " + member.getName() + " | Contact: " + member.getContactDetails());
        }
    }
    

    private void generateFines() {
        // Iterate through issued books
        for (Book book : books) {
            if (!book.isAvailable() && isBookOverdue(book)) {
                Member member = findMemberById(book.getIssuedTo());
                if (member != null) {
                    // Calculate fine based on library policy
                    double fineAmount = calculateFine(book);
                    // Update member's fine record
                    member.addFine(fineAmount);
    
                    // Output fine details
                    System.out.println("Fine generated for book '" + book.getTitle() + "' issued to " + member.getName() + ": $" + fineAmount);
                }
            }
        }
    }
    
    private boolean isBookOverdue(Book book) {
        // Implement logic to check if the book is overdue
        // This is a placeholder for the actual implementation
        // Example: Compare current date with due date
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = book.getDueDate(); // Assuming dueDate is stored in Book object
    
        return currentDate.isAfter(dueDate);
    }
    
    private double calculateFine(Book book) {
        // Implement fine calculation logic based on library policy
        // This is a placeholder for the actual implementation
        // Example: Calculate fine based on overdue days and fine rate per day
        double fineRatePerDay = 0.50; // Example fine rate per day
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = book.getDueDate(); // Assuming dueDate is stored in Book object
    
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, currentDate);
        double fineAmount = daysOverdue * fineRatePerDay;
    
        return fineAmount;
    }
    
    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }
    

    private void manageAdvanceBooking() {
        System.out.println("Pending Advance Booking Requests:");
        
        // Display pending advance booking requests
        for (AdvanceBookingRequest request : advanceBookingRequests) {
            System.out.println("Request ID: " + request.getRequestId());
            System.out.println("Book: " + request.getBook().getTitle());
            System.out.println("Requested By: " + request.getUser().getName());
            System.out.println("Request Date: " + request.getRequestDate());
            System.out.println("Status: " + request.getStatus());
            System.out.println("--------------------");
        }
        
        // Allow admin to review and manage requests
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Request ID to Approve/Reject (-1 to exit): ");
        int requestId = scanner.nextInt();
        
        while (requestId != -1) {
            AdvanceBookingRequest request = findRequestById(requestId);
            
            if (request != null) {
                System.out.print("Approve (Y/N): ");
                String approval = scanner.next().toUpperCase();
                
                if (approval.equals("Y")) {
                    // Approve request
                    approveRequest(request);
                    System.out.println("Request approved for Book: " + request.getBook().getTitle() +
                                       " to User: " + request.getUser().getName());
               } else if (approval.equals("N")) {
                    // Reject request
                    rejectRequest(request);
                    System.out.println("Request rejected for Book: " + request.getBook().getTitle() +
                                       " from User: " + request.getUser().getName());
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            } else {
                System.out.println("Request ID not found.");
            }
            
            System.out.print("Enter Request ID to Approve/Reject (-1 to exit): ");
            requestId = scanner.nextInt();
        }
        
        // Notify users when their requested book becomes available
        notifyUsers();
    }
    
    private AdvanceBookingRequest findRequestById(int requestId) {
        // Method to find a request by its ID
        for (AdvanceBookingRequest request : advanceBookingRequests) {
            if (request.getRequestId() == requestId) {
                return request;
            }
        }
        return null;
    }
    
    private void approveRequest(AdvanceBookingRequest request) {
        // Method to approve the request
        request.setStatus("Approved");
        // Update book availability status or queue management as per your system
        request.getBook().setAvailable(false); // Example: Set book as unavailable
    }
    
    private void rejectRequest(AdvanceBookingRequest request) {
        // Method to reject the request
        request.setStatus("Rejected");
    }
    
    private void notifyUsers() {
        // Method to notify users when their requested book becomes available
        for (AdvanceBookingRequest request : advanceBookingRequests) {
            if (request.getStatus().equals("Approved")) {
                User user = request.getUser();
                Book book = request.getBook();
                // Implement notification mechanism (e.g., email notification)
                System.out.println("Notification sent to " + user.getEmail() + ": Your requested book '" + book.getTitle() + "' is now available.");
            }
        }
    }
    

    private void generateReports() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Report to Generate:");
        System.out.println("1. Issued Books Report");
        System.out.println("2. Available Books Report");
        System.out.println("3. Overdue Books Report");
        System.out.println("4. Fines Collected Report");
        System.out.print("Enter your choice (1-4): ");
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                generateIssuedBooksReport();
                break;
            case 2:
                generateAvailableBooksReport();
                break;
            case 3:
                generateOverdueBooksReport();
                break;
            case 4:
                generateFinesCollectedReport();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }
    
    private void generateIssuedBooksReport() {
        System.out.println("Issued Books Report:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Issued To: " + book.getIssuedTo().getName());
                System.out.println("Issue Date: " + book.getIssueDate());
                System.out.println("Due Date: " + book.getDueDate());
                System.out.println("--------------------");
            }
        }
    }
    
    private void generateAvailableBooksReport() {
        System.out.println("Available Books Report:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("--------------------");
            }
        }
    }
    
    private void generateOverdueBooksReport() {
        System.out.println("Overdue Books Report:");
        for (Book book : books) {
            if (!book.isAvailable() && book.getDueDate().isBefore(LocalDate.now())) {
                long overdueDays = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Issued To: " + book.getIssuedTo().getName());
                System.out.println("Due Date: " + book.getDueDate());
                System.out.println("Overdue Days: " + overdueDays);
                System.out.println("--------------------");
            }
        }
    }
    
    private void generateFinesCollectedReport() {
        System.out.println("Fines Collected Report:");
        double totalFines = 0.0;
        for (Member member : members) {
            totalFines += member.getFinesPaid();
        }
        System.out.println("Total Fines Collected: $" + totalFines);
    }
    
}

// UserDashboard class to manage user-specific tasks
class UserDashboard {
    private List<Book> books;

    public UserDashboard(List<Book> books) {
        this.books = books;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            Banner.display();
            System.out.println("******************************");
            System.out.println("***   User Dashboard   ***");
            System.out.println("******************************");
            System.out.println("1. View Books");
            System.out.println("2. Search Books");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. Advance Booking");
            System.out.println("6. Email Option");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    issueBooks();
                    break;
                case 4:
                    returnBooks();
                    break;
                case 5:
                    advanceBooking();
                    break;
                case 6:
                    emailOption();
                    break;
                case 7:
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        } while (choice != 7);
    }

    private void viewBooks() {
        Scanner scanner = new Scanner(System.in);
        
        // Display available categories
        System.out.println("Available Book Categories:");
        System.out.println("1. Fiction");
        System.out.println("2. Non-Fiction");
        System.out.println("3. Science Fiction");
        System.out.println("4. Mystery");
        System.out.println("5. Romance");
        System.out.print("Enter category number to browse books: ");
        
        int categoryChoice = scanner.nextInt();
        List<Book> booksInCategory = getBooksInCategory(categoryChoice);
        
        if (booksInCategory.isEmpty()) {
            System.out.println("No books found in this category.");
            return;
        }
        
        // Display books in the selected category
        System.out.println("Books in Selected Category:");
        for (Book book : booksInCategory) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("--------------------");
        }
        
        // Prompt to view details of a specific book
        System.out.print("Enter the number of the book to view details (or 0 to go back): ");
        int bookChoice = scanner.nextInt();
        
        if (bookChoice > 0 && bookChoice <= booksInCategory.size()) {
            Book selectedBook = booksInCategory.get(bookChoice - 1);
            displayBookDetails(selectedBook);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }
    
    private List<Book> getBooksInCategory(int categoryChoice) {
        // Mock implementation to return books based on category choice
        // Replace with actual logic to fetch books from database or file
        List<Book> books = new ArrayList<>();
        switch (categoryChoice) {
            case 1:
                books.add(new Book(categoryChoice, "To Kill a Mockingbird", "Harper Lee", "Fiction", false));
                books.add(new Book(categoryChoice, "1984", "George Orwell", "Fiction", false));
                break;
            case 2:
                books.add(new Book(categoryChoice, "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-Fiction", false));
                books.add(new Book(categoryChoice, "Educated", "Tara Westover", "Non-Fiction", false));
                break;
            case 3:
                books.add(new Book(categoryChoice, "Dune", "Frank Herbert", "Science Fiction", false));
                books.add(new Book(categoryChoice, "The Martian", "Andy Weir", "Science Fiction", false));
                break;
            case 4:
                books.add(new Book(categoryChoice, "The Girl with the Dragon Tattoo", "Stieg Larsson", "Mystery", false));
                books.add(new Book(categoryChoice, "Gone Girl", "Gillian Flynn", "Mystery", false));
                break;
            case 5:
                books.add(new Book(categoryChoice, "Pride and Prejudice", "Jane Austen", "Romance", false));
                books.add(new Book(categoryChoice, "The Notebook", "Nicholas Sparks", "Romance", false));
                break;
            default:
                break;
        }
        return books;
    }
    
    private void displayBookDetails(Book book) {
        // Display detailed information about the selected book
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
        System.out.println("--------------------");
    }
    
    private void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Search Books:");
        System.out.println("1. By Title");
        System.out.println("2. By Author");
        System.out.println("3. By Genre");
        System.out.print("Enter search criteria number: ");
        
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        String searchString = "";
        switch (searchChoice) {
            case 1:
                System.out.print("Enter book title: ");
                searchString = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter author name: ");
                searchString = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter genre: ");
                searchString = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }
        
        List<Book> searchResults = performSearch(searchChoice, searchString);
        
        if (searchResults.isEmpty()) {
            System.out.println("No books found matching your search criteria.");
            return;
        }
        
        // Display search results
        System.out.println("Search Results:");
        int index = 1;
        for (Book book : searchResults) {
            System.out.println(index + ". Title: " + book.getTitle());
            System.out.println("   Author: " + book.getAuthor());
            System.out.println("   Genre: " + book.getGenre());
            System.out.println("--------------------");
            index++;
        }
        
        // Prompt to view details of a specific book
        System.out.print("Enter the number of the book to view details (or 0 to go back): ");
        int bookChoice = scanner.nextInt();
        
        if (bookChoice > 0 && bookChoice <= searchResults.size()) {
            Book selectedBook = searchResults.get(bookChoice - 1);
            displayBookDetails(selectedBook);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }
    
    private List<Book> performSearch(int searchChoice, String searchString) {
        // Mock implementation to perform search based on user's criteria
        // Replace with actual database logic to fetch books dynamically
        List<Book> books = new ArrayList<>();
        switch (searchChoice) {
            case 1:
                // Search by title
                books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
                books.add(new Book("1984", "George Orwell", "Fiction"));
                break;
            case 2:
                // Search by author
                books.add(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-Fiction"));
                books.add(new Book("Educated", "Tara Westover", "Non-Fiction"));
                break;
            case 3:
                // Search by genre
                books.add(new Book("Dune", "Frank Herbert", "Science Fiction"));
                books.add(new Book("The Martian", "Andy Weir", "Science Fiction"));
                break;
            default:
                break;
        }
        return books;
    }
    
    private void displayBookDetails(Book book) {
        // Display detailed information about the selected book
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
        System.out.println("--------------------");
    }
    

    private void issueBooks() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Issue Books:");
        System.out.print("Enter the book ID or title to issue: ");
        String bookIdentifier = scanner.nextLine();
        
        // Assume we have a method to retrieve book details from the database
        Book book = findBook(bookIdentifier);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Sorry, the book is currently not available for issuing.");
            return;
        }
        
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        
        System.out.print("Confirm issuing this book? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            // Update database to mark the book as issued
            book.setAvailable(false); // Mark as unavailable
            book.setIssuedTo(currentUser.getUsername()); // Set issued to current user
            
            // Update member's issued books list (assuming it's a member/user object)
            currentUser.issueBook(book);
            
            System.out.println("Book successfully issued.");
            System.out.println("Due Date: " + calculateDueDate()); // Implement due date calculation
            
            // Add log entry or update transaction history
            // logTransaction(currentUser.getUsername(), "Issued book: " + book.getTitle());
        } else {
            System.out.println("Issuance cancelled.");
        }
    }
    
    // Method to find a book by ID or title (mock implementation)
    private Book findBook(String bookIdentifier) {
        // Implement logic to search for book in database or collection
        // Replace with actual database lookup logic
        // This is a mock implementation
        List<Book> libraryBooks = new ArrayList<>();
        libraryBooks.add(new Book("101", "To Kill a Mockingbird", "Harper Lee", "Fiction", true));
        libraryBooks.add(new Book("102", "1984", "George Orwell", "Fiction", true));
        libraryBooks.add(new Book("103", "Dune", "Frank Herbert", "Science Fiction", false));
        
        // Check if bookIdentifier is numeric (assuming it's book ID) or a string (assuming it's book title)
        for (Book book : libraryBooks) {
            if (book.getBookId().equals(bookIdentifier) || book.getTitle().equalsIgnoreCase(bookIdentifier)) {
                return book;
            }
        }
        
        return null; // Book not found
    }
    
    // Method to calculate due date (mock implementation)
    private String calculateDueDate() {
        // Implement due date calculation logic
        // This is a mock implementation, replace with actual due date calculation
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(14); // Example: 14 days borrowing period
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dueDate.format(formatter);
    }
    

    private void returnBooks() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Return Books:");
        System.out.print("Enter the book ID or title to return: ");
        String bookIdentifier = scanner.nextLine();
        
        // Assume we have a method to retrieve book details from the database
        Book book = findBook(bookIdentifier);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        // Check if the book is issued to the current user
        if (!book.isIssued() || !book.getIssuedTo().equals(currentUser.getUsername())) {
            System.out.println("You have not borrowed this book.");
            return;
        }
        
        // Calculate fine for overdue books (mock implementation)
        double fine = calculateFine(book);
        
        // Confirm return and display fine (if any)
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        
        System.out.println("Confirm return this book? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            // Update database to mark the book as returned
            book.setAvailable(true); // Mark as available
            book.setIssuedTo(null); // Clear issued to
            
            // Update member's issued books list (assuming it's a member/user object)
            currentUser.returnBook(book);
            
            System.out.println("Book successfully returned.");
            if (fine > 0) {
                System.out.println("Fine incurred: $" + fine);
            }
            
            // Add log entry or update transaction history
            // logTransaction(currentUser.getUsername(), "Returned book: " + book.getTitle());
        } else {
            System.out.println("Return cancelled.");
        }
    }
    
    // Method to find a book by ID or title (mock implementation)
    private Book findBook(String bookIdentifier) {
        // Implement logic to search for book in database or collection
        // Replace with actual database lookup logic
        // This is a mock implementation
        List<Book> libraryBooks = new ArrayList<>();
        libraryBooks.add(new Book("101", "To Kill a Mockingbird", "Harper Lee", "Fiction", true, "user1"));
        libraryBooks.add(new Book("102", "1984", "George Orwell", "Fiction", true, "user2"));
        libraryBooks.add(new Book("103", "Dune", "Frank Herbert", "Science Fiction", false, null));
        
        // Check if bookIdentifier is numeric (assuming it's book ID) or a string (assuming it's book title)
        for (Book book : libraryBooks) {
            if (book.getBookId().equals(bookIdentifier) || book.getTitle().equalsIgnoreCase(bookIdentifier)) {
                return book;
            }
        }
        
        return null; // Book not found
    }
    
    // Method to calculate fine for overdue books (mock implementation)
    private double calculateFine(Book book) {
        // Implement fine calculation logic based on due date and current date
        // This is a mock implementation, replace with actual fine calculation
        if (book.isOverdue()) {
            return 5.0; // Example: $5 fine per overdue book
        } else {
            return 0.0; // No fine
        }
    }
    
    private void advanceBooking() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Advance Booking:");
        System.out.print("Enter the book ID or title to book in advance: ");
        String bookIdentifier = scanner.nextLine();
        
        // Assume we have a method to retrieve book details from the database
        Book book = findBook(bookIdentifier);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        // Check if the book is currently issued to someone else
        if (!book.isIssued()) {
            System.out.println("This book is currently available. No advance booking needed.");
            return;
        }
        
        // Check if the book is issued to the current user
        if (book.getIssuedTo().equals(currentUser.getUsername())) {
            System.out.println("You have already borrowed this book.");
            return;
        }
        
        // Check if the book is already in advance booking queue
        if (book.getAdvanceBooking() != null && book.getAdvanceBooking().contains(currentUser.getUsername())) {
            System.out.println("You have already booked this book in advance.");
            return;
        }
        
        // Confirm advance booking
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        
        System.out.println("Confirm advance booking this book? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            // Update database to add user to advance booking queue
            book.addAdvanceBooking(currentUser.getUsername());
            
            System.out.println("Book successfully booked in advance.");
            // Notify user or update dashboard with booking status
        } else {
            System.out.println("Advance booking cancelled.");
        }
    }
    
    // Method to find a book by ID or title (mock implementation)
    private Book findBook(String bookIdentifier) {
        // Implement logic to search for book in database or collection
        // Replace with actual database lookup logic
        // This is a mock implementation
        List<Book> libraryBooks = new ArrayList<>();
        libraryBooks.add(new Book("101", "To Kill a Mockingbird", "Harper Lee"));
        libraryBooks.add(new Book("102", "1984", "George Orwell"));
        libraryBooks.add(new Book("103", "Dune", "Frank Herbert"));
        
        // Check if bookIdentifier is numeric (assuming it's book ID) or a string (assuming it's book title)
        for (Book book : libraryBooks) {
            if (book.getBookId().equals(bookIdentifier) || book.getTitle().equalsIgnoreCase(bookIdentifier)) {
                return book;
            }
        }
        
        return null; // Book not found
    }
    

    private void emailOption() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Email Option:");
        System.out.println("Enter your message to the admin:");
        String message = scanner.nextLine();
        
        // Assume sending email functionality
        // This is a mock implementation
        System.out.println("Email sent successfully to admin.");
        System.out.println("Message: " + message);
    }
    
}

// Main class controlling the flow of the program
public class main{
    private static final Object DEFAULT_PASSWORD = null;
    private static final Object DEFAULT_USER_ID = null;
    private static List<User> users = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        loadDefaultData(); // Load default data on startup

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            Banner.display();
            System.out.println("******************************");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Signup");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    signup();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        } while (choice != 4);

        // Save data before exiting
        saveData();
    }

    private static void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("123")) {
            AdminDashboard adminDashboard = new AdminDashboard(books, members);
            adminDashboard.displayMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void userLogin() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("### User Login ###");
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Check credentials (mock implementation)
        if (userId.equals(DEFAULT_USER_ID) && password.equals(DEFAULT_PASSWORD)) {
            System.out.println("Login successful. Redirecting to User Dashboard...");
            // Implement redirect to user dashboard
            userDashboard();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            userLogin();
        }
        
        scanner.close();
    }

    private static void userDashboard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userDashboard'");
    }

    private static void signup() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("### Signup ###");
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        
        // Check if username already exists
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
            signup(); // Recursive call to retry signup process
            return;
        }
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Store new user in the map
        users.put(username, password);
        
        System.out.println("Signup successful. You can now login with your credentials.");
        
        // Redirect to login menu
        start();
        
        scanner.close();
    }

    private static void loadDefaultData() {
        // Load default data for testing
        users.add(new User("admin", "123", true)); // Admin user
        books.add(new Book(1, "Book Title", "Author Name", "Genre", true)); // Sample book
        members.add(new Member(1, "Member Name", "Contact Info")); // Sample member
    }

    private static void saveData() {
        // Save all data before exiting
        FileHandler.saveUsers(users);
        FileHandler.saveBooks(books);
        FileHandler.saveMembers(members);
    }
}
