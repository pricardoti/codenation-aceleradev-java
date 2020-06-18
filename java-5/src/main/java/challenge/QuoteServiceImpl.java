package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository repository;

    private Integer generateRamdomId(int max) {
        return max > 0 ? new Random().nextInt(max) : 0;
    }

    @Override
    public Quote getQuote() {
        return this.repository.findById(generateRamdomId((int) repository.count())).orElse(null);
    }

    @Override
    public Quote getQuoteByActor(String actor) {
        List<Quote> listQuotes = repository.findByActor(actor);
        return listQuotes.get(generateRamdomId(listQuotes.size()));
    }
}
