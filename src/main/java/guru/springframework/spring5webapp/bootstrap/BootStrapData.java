package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting in Bootstrap");

        Publisher pub = new Publisher();

        pub.setAddressLine1("123 NewYork State");
        pub.setCity("NYC");
        pub.setState("NY");
        pub.setName("ABC Publisher");

        publisherRepository.save(pub);

        System.out.println("Publisher Count: " +publisherRepository.count());

        Author kaku = new Author("Michio", "Kaku");
        Book pof = new Book("Physics", "123456");

        kaku.getBooks().add(pof);
        pof.getAuthors().add(kaku);
        pof.setPublisher(pub);
        pub.getBook().add(pof);

        authorRepository.save(kaku);
        bookRepository.save(pof);
        publisherRepository.save(pub);

        Author hawking = new Author("Stephen", "Hawking");
        Book bht = new Book ("A brief history of Time", "987654");

        hawking.getBooks().add(bht);
        bht.getAuthors().add(hawking);
        bht.setPublisher(pub);
        pub.getBook().add(bht);

        authorRepository.save(hawking);
        bookRepository.save(bht);
        publisherRepository.save(pub);


        System.out.println("Number of Books: " +bookRepository.count());
        System.out.println("Nummber of books publisher has:" +pub.getBook().size());

    }
}
