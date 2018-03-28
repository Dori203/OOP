
/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to
 * be able to check out books, if a copy of the requested book is available.
 */

public class Library{

    int maxBookCapacity;
    int maxBorrowedBooks;
    int maxPatronCapacity;
    Book[] books_array;
    Patron[] patrons_array;
    int num_of_books;
    int num_of_patrons;
    int[] num_books_borrowed;

    /**
     * This constructor creates a new library with the following values:
     * books_array - an array of book objects, initiated without any books (null).
     * patrons_array - an array of patron objects, initiated without any patrons (null).
     * num_book_borrowed - an array of integers, representing the number of books borrowed by patrons (each
     * index represents the patronID).
     * @param maxBookCapacity maximum number of books.
     * @param maxBorrowedBooks maximum number of books being borrowed simultaneously
     * @param maxPatronCapacity maximum number of patrons registered to the library.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
        books_array = new Book[maxBookCapacity];
        patrons_array = new Patron[maxPatronCapacity];
        num_books_borrowed = new int[maxPatronCapacity];
    }

    /**
     *This method adds a new book to the library, after checking weather the book is already listed in the
     *  library, and making sure there is a free space for it. It will return the book's index if the book is
     *  already in the library / was successfully added, and the int -1 if there is not space for adding it.
     * @param book book object given.
     * return int -1 if book in not in library and library is full, book index otherwise.
     */
    int addBookToLibrary(Book book) {
        if (getBookId(book) != -1) return getBookId(book); //return book index if it's in array.
        else if ((maxBookCapacity == num_of_books) && (getBookId(book) == -1)) return -1; //book has no
        // place for placement.
        else { //book is not in array, and there is space for it.
            books_array[num_of_books] = book; //add book n next free location.
            num_of_books++; //increase count of books
            return num_of_books - 1; //return added book index
            }
    }

    /**
     * This method searches for a book in the current array of books.
     * @param book Book being searched.
     * @return If book is in array - return book index. Otherwise - return -1.
     */
    int getBookId(Book book){
        for (int i=0; i<num_of_books; i++) {
            if (books_array[i] == book) return i;
            }
        return -1; }

    /**
     * This method checks weather a bookid given is valid. A bookId represents it's index in the books_array,
     * so the function simply checks if the bookId given is both non-negative, and does not exceed the current
     * number of books in the library.
     * @param bookId int of a book. only non-negative integers will possibly be valid.
     * @return True - if there is a book with the given bookId. Otherwise - False.
     */
    boolean isBookIdValid(int bookId) {
        if ((bookId + 1 <= num_of_books) && (bookId >= 0)) return true;
        else return false;
    }

    /**
     * This method will check if a bookID is valid, and if so - it will check weather the book a available
     * for borrowing.
     * @param bookId cuurent bookid being examined.
     * @return true if the book is available for borrowing, else otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if (isBookIdValid(bookId)){
            if (books_array[bookId].getCurrentBorrowerId() == -1) return true; //the book is available
            else return false;}
        else return false;
    }

    /**
     * This method registers a new patron to the library, after checking the following conditions:
     * - The library has not exceeded it's max number of patrons.
     * - Patron is not yet registered in the library.
     * finally, the method will return the added patrons ID if it's now registered, and if not - it will
     * return -1.
     * @param patron current patron being registered.
     * @return patronID, if patron is registered, -1 else.
     */
    int registerPatronToLibrary(Patron patron) {
        if (getPatronId(patron) != -1) return getPatronId(patron); //return patron index if
            // it's in array.
        else if ((maxPatronCapacity == num_of_patrons) && (getPatronId(patron) == -1)) return -1;
        //patron has no place for placement.
        else{
            patrons_array[num_of_patrons] = patron; //add patron n next free location.
            num_of_patrons++; //increase count of patrons
            return num_of_patrons - 1; //return added patron index
        }
    }

    /**
     * This method returns the patronID of the current patron, by searching for the patron's index in the
     * patron_array. If there is not
     * @param patron patron object being searched.
     * @return patronID.
     */
    int getPatronId(Patron patron){
        for (int i=0; i<num_of_patrons; i++) {
            if (patrons_array[i] == patron) return i;
        }
        return -1; }

    /**
     * This method checks weather a patron ID is valid, by checking if it's a not negative int, and if there
     * is patron with that ID (in the case - with that index in the patrons_array).
     * @param patronId PatronID being examined.
     * @return true if such patron exists, false otherwise.
     */
    boolean isPatronIdValid(int patronId){
        if ((patronId + 1 <= num_of_patrons) && (patronId >= 0)) return true;
        else return false;
    }

    /**
     * This method checks weather a book can be borrowed by a patron, and if so - it will register it with
     * the patrons ID, and increase the number of books being borrowed by the patron by 1.
     * Ths method check weather the bookID and the patronID are valid. Then it checks weather the book is
     * available for borrowing and if the patron hasn't exceeded his borrowing limit, lastly - it checks
     * weather the patron will enjoy the book. If so - the book will be borrowed by the patron. Otherwise -
     * false will be returned.
     * @param bookId ID of current book being requested.
     * @param patronId ID of patron requesting the book.
     * @return true if the borrowing process succeeded, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId){
        if ((isBookIdValid(bookId)) && (isPatronIdValid(patronId))) {
            Book cur_book = books_array[bookId];
            Patron cur_patron = patrons_array[patronId];
            if ((isBookAvailable(bookId)) && (num_books_borrowed[patronId] < maxBorrowedBooks)){
              if (cur_patron.willEnjoyBook(cur_book)){
                  cur_book.setBorrowerId(patronId); //set the new patron as borrower by his ID.
                  num_books_borrowed[patronId]++; //update the num of books borrowed by patron.
                  return true;
              }
              else return false;
            }
            else return false;
        }
        else return false;
    }

    /**
     * This function returns a bok from a borrower, by setting the book's "CurrentBorrowerID" to -1,
     * and decreasing the number of books held by the patron by 1.
     * @param bookId ID of the current book being returned.
     */
    void returnBook(int bookId){
        if (isBookIdValid(bookId)){
            Book cur_book = books_array[bookId];
            if (cur_book.getCurrentBorrowerId() != -1){
                int cur_patronID = cur_book.getCurrentBorrowerId();
                cur_book.returnBook();
                num_books_borrowed[cur_patronID]--;
                }
            }
    }

    /**
     * This method suggest the patron the best book available, that he might enjoy the most.
     * The method goes through each book and checks weather it's available, and if it will be enjoyable by
     * the patron. Finally, the book with the highest score will be recommended. If no such book exists -
     * null will be returned.
     * @param patronId patron being examined.
     * @return book object of the book with the highest enjoyment score for the patron.
     */
    Book suggestBookToPatron(int patronId){
        if (isPatronIdValid(patronId)){
            Patron cur_patron = patrons_array[patronId];
            Book best_match = null;
            int best_score = 0;

            for (int i=0; i<num_of_books; i++) {
                if ((cur_patron.willEnjoyBook (books_array[i]) && (books_array[i].getCurrentBorrowerId()==-1)
                        && (cur_patron.getBookScore(books_array[i]) > best_score))){
                    best_match = books_array[i];
                    best_score = cur_patron.getBookScore(books_array[i]);
                    }
                }
            return best_match;
            }
            return null;
    }
}


