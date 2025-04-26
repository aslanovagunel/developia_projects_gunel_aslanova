package spring.library_gunel_aslanova.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "lend_books")
public class LendBookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer studentCode;

	private Integer librarianCode;

	private Integer bookCode;

	private Integer count;

	private LocalDate regDate;

	private LocalDate returnDate;

	private LocalDate mustReturnDate;
}
