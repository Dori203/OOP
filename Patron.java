/**
 * This class represents a patron, which has a first name, last name, tendencies (comic, dramatic, aducational
 * and an enjoyment threshold.
 */

public class Patron {
    final String FirstName;
    final String LastName;
    int comicTendency;
    int dramaticTendency;
    int educationalTendency;
    int EnjoymentThreshold;

    /**
     * Creates a new Patron with the following parameters:
     * @param patronFirstName The patron's first name.
     * @param patronLastName The patron's last name.
     * @param comicTendency The patron's comic tendency.
     * @param dramaticTendency The patron's dramatic tendency.
     * @param educationalTendency The patron's educational tendency.
     * @param patronEnjoymentThreshold The patron's enjoyment threshold.
     */
    Patron (String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold){
        FirstName = patronFirstName;
        LastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        EnjoymentThreshold = patronEnjoymentThreshold;
    }


    /**
     * Returns the book's value in accordence with the patron's tendencies.
     * @param book The book object, who's value is being requested.
     * @return The combined value of the books values, multiplied with the patrons tendencies.
     */
    int getBookScore(Book book){
        int book_score = comicTendency * book.comicValue + dramaticTendency * book.dramaticValue +
                educationalTendency * book.educationalValue;
        return book_score;
    }

    /**
     * This method returns the string representation of the patron - his complete name.
     * @return a string of the patron's first name and last name, separated by a blank space.
     */
    String stringRepresentation(){
        return FirstName + " " + LastName;
    }

    /**
     * Returns a boolean answer, true if the patron will enjoy the given book, false otherwise. The function
     * uses the "getBookscore" function for calculations.
     * @param book A book object, the current book being examined.
     * @return true if the patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book){
        if (getBookScore(book) > EnjoymentThreshold)
            return true;
        else return false;
        }
}