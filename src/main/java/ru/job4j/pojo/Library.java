package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book detectivesBook = new Book("Детективы Дарьи Донцовой", 10000);
        Book cookingBook = new Book("Книга рецептов", 250);
        Book cleanCodeBook = new Book("Clean code", 300);
        Book russianEnglishDictionary = new Book("Русско-английский словарь", 100);
        Book[] books = new Book[4];
        books[0] = detectivesBook;
        books[1] = cookingBook;
        books[2] = cleanCodeBook;
        books[3] = russianEnglishDictionary;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + " - " + books[i].getPagesCount());
        }
        Book tmpBook = books[0];
        books[0] = books[3];
        books[3] = tmpBook;
        System.out.println("Выводим книгу с названием Clean code");
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equals("Clean code")) {
                System.out.println(books[i].getTitle() + " - " + books[i].getPagesCount());
            }
        }
    }
}
