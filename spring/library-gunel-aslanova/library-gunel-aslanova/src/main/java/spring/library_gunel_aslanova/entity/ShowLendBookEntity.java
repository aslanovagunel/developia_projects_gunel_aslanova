package spring.library_gunel_aslanova.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "show_lend_books")
public class ShowLendBookEntity {

	@Id
	private Integer id;

	private Integer studentCode;

	private String studentName;

	private Integer librarianCode;

	private Integer bookCode;

	private String bookName;

	private Integer count;

	private LocalDate regDate;

	private LocalDate returnDate;

	private LocalDate mustReturnDate;
}
