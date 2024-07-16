package Library_Management_Sys;

import java.util.*;

public class Main {

    Scanner input = new Scanner(System.in);
    static Scanner inputStatic = new Scanner(System.in);

    static int choice = 0;
    static int back_option = 1;

    static int bookID;
    static int readerID;
    static int d;
    static int m;
    static int y;

    static Librarian l = new Librarian(111, "1234", "Harish", "g", "Teacher", 123456789, "blr", "A@gmail.com", true);
    static Reader r = new Reader(222, "5678", "Yukti", "hy", "Student", 987654321, "bek", "B@gmail.com", false);

    public static void main(String[] args) {
        l.preRegistered();

        do {
            back_option = 1;
            cls();

            System.out.print("\n\t\t"
                    + "\t\t   **********************************\n\t\t"
                    + "\t\t **************************************\n\t\t"
                    + "\t\t******---------> WELCOME<----------*******\n\t\t"
                    + "\t\t#####-> Library Management System <-#####\n\t\t"
                    + "\t\t******---------> By Harish<----------*****\n\t\t"
                    + "\t\t *************************************\n\t\t"
                    + "\t\t  **********************************\n\n");

            System.out.print("\n"
                    + "\t° Enter >-[1]-> to:-> Register User\t"
                    + "\t   ° Enter >-[2]-> to:-> Search Book\n\n"
                    + "\t° Enter >-[3]-> to:-> Add Reader to Book"
                    + "\t   ° Enter >-[4]-> to:-> Remove Reader from Book\n\n"
                    + "\t° Enter >-[5]-> to:-> Add new Books\t"
                    + "\t   ° Enter >-[6]-> to:-> Remove Books\n\n"
                    + "\t° Enter >-[7]-> to:-> Rent/Buy Book\t"
                    + "\t   ° Enter >-[8]-> to:-> Block Reader\n\n"
                    + "\t° Enter >-[9]-> to:-> Search User\t"
                    + "\t   ° Enter >-[10]-> to:-> Check Return Date\n\n"
                    + "\t° Enter >-[11]-> to:-> Display Books\t"
                    + "\t   ° Enter >-[12]-> to:-> Display Readers\n\n"
                    + "\t° Enter >-[13]-> to:-> Number of Books\t"
                    + "\t   ° Enter >-[14]-> to:-> Number of Readers\n\n"
                    + "\t° Enter >-[15]-> to:-> Pay a Late Fine\t"
                    + "\t   ° Enter >-[0]-> to:-> EXIT\n"
                    + "\n\n****************************************************************************************************\n\n"
                    + "\t° -> Your Choice is:-> ");
            choice = inputStatic.nextInt();

            switch (choice) {
                case 1:
                    cls();
                    if (Login() == 1) {
                        l.addReader();
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 2:
                    cls();

                    if (Login() != 0) {
                        System.out.print("\n\t-> Enter Book ID: ");
                        bookID = inputStatic.nextInt();
                        System.out.println("\tBook's Name is: " + l.Search_For_Book(bookID));
                    }

                    Back_Option(back_option);
                    continue;

                case 3:
                    cls();

                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter Reader ID: ");
                        readerID = inputStatic.nextInt();

                        System.out.print("\n\t-> Enter Book ID: ");
                        bookID = inputStatic.nextInt();

                        System.out.print("\n\t-> Enter Reader Name: ");
                        String readerName = inputStatic.next();

                        l.Add_ReaderToBook(readerID, bookID, readerName);
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 4:
                    cls();
                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter Book ID: ");
                        bookID = inputStatic.nextInt();

                        l.Remove_ReaderFromBook(bookID);
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 5:
                    cls();
                    if (Login() == 1) {
                        l.addBook();
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 6:
                    cls();
                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter Book ID: ");
                        bookID = inputStatic.nextInt();

                        l.removeBook(bookID);
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 7:
                    cls();
                    if (Login() != 0) {
                        l.rentBook();
                    }

                    Back_Option(back_option);
                    continue;

                case 8:
                    cls();
                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter Reader ID: ");
                        readerID = inputStatic.nextInt();

                        l.blockUser(readerID);
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 9:
                    cls();
                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter User's ID: ");
                        readerID = inputStatic.nextInt();
                        System.out.println("\tUser's Name is:" + l.searchUser(readerID));
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 10:
                    cls();
                    if (Login() == 1) {
                        System.out.print("\n\t-> Enter Rental Date:-");

                        System.out.print("\n\t\t\t-> Day: ");
                        d = inputStatic.nextInt();

                        System.out.print("\n\t\t\t-> Month: ");
                        m = inputStatic.nextInt();

                        System.out.print("\n\t\t\t-> Year: ");
                        y = inputStatic.nextInt();

                        if (l.checkReturnDate(d, m, y)) {
                            System.out.println("\n\n\t-> Valid Date, Haven't Exceeded a Week");
                        } else {
                            System.out.println("\n\n\t-> Invalid Date, Exceeded a Week");
                        }
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 11:
                    cls();
                    if (Login() != 0) {
                        l.displayBooks();
                    }

                    Back_Option(back_option);
                    continue;

                case 12:
                    cls();
                    if (Login() == 1) {
                        l.displayReaders();
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 13:
                    cls();
                    if (Login() == 1) {
                        Librarian.booksNum();
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 14:
                    cls();
                    if (Login() == 1) {
                        Librarian.readerNum();
                    } else {
                        System.out.println("\n\n\tSorry you aren't Allowed to do This Operation\n\n");
                    }

                    Back_Option(back_option);
                    continue;

                case 15:
                    cls();
                    if (Login() != 0) {
                        System.out.print("\n\t-> Enter Reader ID: ");
                        readerID = inputStatic.nextInt();

                        System.out.print("\n\t-> Enter Book ID: ");
                        bookID = inputStatic.nextInt();

                        System.out.print("\n\t-> Enter Rental Date:-");

                        System.out.print("\n\t\t\t-> Day: ");
                        d = inputStatic.nextInt();

                        System.out.print("\n\t\t\t-> Month: ");
                        m = inputStatic.nextInt();

                        System.out.print("\n\t\t\t-> Year: ");
                        y = inputStatic.nextInt();

                        if (l.checkReturnDate(d, m, y)) {
                            System.out.println("\n\n\t-> No Fine, You will Pay Book's Price Only:-"
                                    + "\t\t\tPay: " + l.payFine(readerID, bookID, d, m, y));
                        } else {
                            System.out.println("\n\n\t-> Fine 10%, You will Pay Book's Price + Fine:-"
                                    + "\t\t\tPay: " + l.payFine(readerID, bookID, d, m, y));
                        }
                    }

                    Back_Option(back_option);
                    continue;

                case 0:
                    cls();
                    System.out.println("\t\t*-> Program  Ended <-*\n\n"
                            + "\t\t    *-> Thanks<-*\n\n\n\n\n\n\n\n\n");
                    back_option = 0;
            }

        } while (back_option != 0);
    }

    public static int Login() {
        int Id;
        String Password;

        System.out.println("\t\t-> Please Login First <-\n\t\t*************************\n\n");

        System.out.print("\n\n\tEnter User ID: ");
        Id = inputStatic.nextInt();

        System.out.print("\n\n\tEnter Password: ");
        Password = inputStatic.next();

        if (Id == l.getId() && Password.equals(l.getPassword())) {
            cls();
            return 1;
        } else if (Id == r.getId() && Password.equals(r.getPassword())) {
            cls();
            return 2;
        } else {
            cls();
            System.out.println("\n\n\tInvalid Login\n\n");
        }
        return 0;
    }

    public static boolean Back_Option(int back_option) {
        System.out.print("\n\n\n****************************************************************************************************\n"
                + "\n\t° --> To Return Menu,, Enter [0] --> ");
        back_option = inputStatic.nextInt();

        if (back_option == 0) {
            return true;
        }

        while (back_option != 0) {
            System.out.print("\n\t\tInvalid "
                    + "\n\t° --> To Return Menu,, Enter [0] --> ");

            back_option = inputStatic.nextInt();

            if (back_option == 0) {
                return true;
            }
        }
        cls();
        return false;
    }

    public static void cls() {
        for (int i = 1; i <= 50; i++) {
            System.out.print("\n");
        }
    }
}
