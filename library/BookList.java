package library;

import book.*;
import java.io.*;

public class BookList extends LibraryActions {

    Book[] books = new Book[50];
    int count = 0;
    String fileName = "books.txt";

    // ===== ADD =====
    @Override
    public String addBook(int id, String title, String author) {
        if (count < books.length) {
            books[count] = new Book(id, title, author);
            count++;
            saveToFile();
            return "Book added successfully!";
        }
        return "Library is full!";
    }

    // ===== VIEW ALL =====
    @Override
    public String viewAll() {
        if (count == 0) {
            return "No books in the library.";
        }

        String result = "";
        for (int i = 0; i < count; i++) {
            result += "ID: " + books[i].getId() +
                      "  |  Title: " + books[i].getTitle() +
                      "  |  Author: " + books[i].getAuthor() + "\n";
        }
        return result;
    }

    // ===== SEARCH =====
    @Override
    public String searchBook(int id, String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id || books[i].getTitle().equalsIgnoreCase(title)) {
                return "Book Found!\n\n" +
                       "ID: " + books[i].getId() + "\n" +
                       "Title: " + books[i].getTitle() + "\n" +
                       "Author: " + books[i].getAuthor();
            }
        }
        return "Book not found.";
    }

    // ===== REMOVE =====
    @Override
    public String removeBook(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                count--;
                saveToFile();
                return "Book removed successfully!";
            }
        }
        return "Book not found.";
    }

    // ===== UPDATE =====
    @Override
    public String updateBook(int id, String newTitle, String newAuthor) {
        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {
                books[i].setTitle(newTitle);      // using setter
                books[i].setAuthor(newAuthor);    // using setter
                saveToFile();
                return "Book updated successfully!";
            }
        }
        return "Book not found.";
    }


    // ===== SAVE TO FILE =====
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < count; i++) {
                fw.write(books[i].getId() + "," + books[i].getTitle() + "," + books[i].getAuthor() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // ===== LOAD FROM FILE =====
    public void loadFromFile() {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            count = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    books[count] = new Book(id, title, author);
                    count++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No saved data found. Starting fresh.");
        }
    }

}
