package library;

public abstract class LibraryActions {

    // abstract methods 
    public abstract String addBook(int id, String title, String author);

    public abstract String viewAll();

    public abstract String searchBook(int id, String title);

    public abstract String removeBook(int id);

    public abstract String updateBook(int id, String newTitle, String newAuthor);

    public abstract String getBook(int id);

}
