package spring.library_gunel_aslanova.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.LendBookEntity;
import spring.library_gunel_aslanova.repository.LendBookRepository;
import spring.library_gunel_aslanova.request.ReturnBookRequest;
import spring.library_gunel_aslanova.response.LendBookSingleResponse;

@Service
@Transactional
public class LendBookService {

	@Autowired
	private LendBookRepository repository;

	@Autowired
	private ModelMapper mapper;

	public Integer lendBook(LendBookSingleResponse re) {
		LendBookEntity en = new LendBookEntity();
		mapper.map(re, en);
		repository.save(en);

		return en.getId();
	}

	public List<LendBookEntity> showLendBook(Integer userId) {
		List<LendBookEntity> list = repository.showLendBook(userId);
		return list;
	}

	public List<LendBookEntity> changeReturnDate(ReturnBookRequest req) {
		List<LendBookEntity> list = repository.changeReturnDate(req.getStudentCode(), req.getBookCode());
		
		for (LendBookEntity s : list) {
			s.setReturnDate(req.getReturnDate());
			repository.save(s);
		}
		return list;
	}

	public List<LendBookEntity> showReturnBook(Integer userId) {
		List<LendBookEntity> list = repository.showReturnBook(userId);
		return list;
	}

	public List<LendBookEntity> getLateReturnedBooks(Integer userId) {
		List<LendBookEntity> list = repository.getLateReturnedBooks(userId);
		return list;
	}

}
