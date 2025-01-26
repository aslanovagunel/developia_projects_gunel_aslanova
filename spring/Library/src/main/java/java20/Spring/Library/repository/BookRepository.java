package java20.Spring.Library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java20.Spring.Library.model.Book;

@Repository
public class BookRepository {

	@Autowired
	private DataSource dataSource;

	public List<Book> allBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement pr = connection.prepareStatement("SELECT * FROM books");
			ResultSet rs = pr.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");

				Book book = new Book(id, title, author);
				books.add(book);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return books;
	}

	public List<Book> findBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement pr = connection.prepareStatement("SELECT * FROM books");
			ResultSet rs = pr.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");

				Book book = new Book(id, title, author);
				books.add(book);
			}

		} catch (Exception e) {
		}

		return books;

	}

	public void add(Book book) {
		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement pr = connection.prepareStatement("insert into books(title,author) values (?,?)");
			pr.setString(1, book.getTitle());
			pr.setString(2, book.getAuthor());
			pr.executeUpdate();

		} catch (Exception e) {

		}

	}

}
