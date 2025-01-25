package java20.developia.springJava.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java20.developia.springJava.model.Book;

@Repository
public class BookRepository {

	@Autowired
	private DataSource dataSource;

	public List<Book> findAllBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection=dataSource.getConnection();
			
			PreparedStatement pr = connection.prepareStatement("SELECT * FROM java20.books");
			ResultSet rs = pr.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double prise = rs.getDouble("prise");

				// System.out.printf("id : %d, name : %s, prise : %f", id, name, prise);
				Book book = new Book(id, name, prise);
				books.add(book);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return books;
	}

	public List<Book> findWords() {
		List<Book> books = new ArrayList<Book>();
		List<String> s = new ArrayList<String>();
		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement pr = connection.prepareStatement("SELECT * FROM java20.books");
			ResultSet rs = pr.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double prise = rs.getDouble("prise");

				Book book = new Book(id, name, prise);
				books.add(book);
				s.add(name);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return books;
	}

	public void add(Book book) {

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement pr = connection.prepareStatement("insert into java20.books(name,prise) values(?,?)");
			pr.setString(1, book.getName());
			pr.setDouble(2, book.getPrise());
			pr.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
