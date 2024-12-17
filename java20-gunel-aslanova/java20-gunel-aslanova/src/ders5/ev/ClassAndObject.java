package ders5.ev;

public class ClassAndObject {
    public static void main(String[] args) {
        Book book=new Book();
        book.id=1;
        book.name="Cinayət və Cəza";
        book.author="Fyodor Dostoyevski";
        book.pageCount=300;

        Book book2=new Book();
        book2.id=2;
        book2.name="Səfillər";
        book2.author="Viktor Huqo";
        book2.pageCount=500;

        System.out.println(book.id);
        System.out.println(book.author);
        System.out.println(book2.name);
        System.out.println(book2.pageCount);
    }
}
