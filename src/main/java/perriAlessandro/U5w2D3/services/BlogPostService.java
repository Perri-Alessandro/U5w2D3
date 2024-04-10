package perriAlessandro.U5w2D3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import perriAlessandro.U5w2D3.entities.BlogPost;
import perriAlessandro.U5w2D3.exceptions.BadRequestException;
import perriAlessandro.U5w2D3.exceptions.NotFoundException;
import perriAlessandro.U5w2D3.repositories.BlogPostDAO;

import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAO blogDAO;

    public Page<BlogPost> getBlogPostList(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogDAO.findAll(pageable);
    }

    public BlogPost saveBlogPost(BlogPost body) {
        this.blogDAO.findByAuthorNome(body.getAuthor().getNome()).ifPresent(
                // 2. Se lo è triggero un errore
                blog -> {
                    throw new BadRequestException("Il blog che volevi assegnare a " + blog.getAuthor().getNome() + " ha già un autore!");
                }
        );
        return blogDAO.save(body);
    }

    public BlogPost findById(UUID id) {
        return this.blogDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost findByIdAndUpdate(UUID id, BlogPost updatedBlog) {
        BlogPost found = this.findById(id);
        found.setContenuto(updatedBlog.getContenuto());
        found.setCategory(updatedBlog.getCategory());
        found.setTitolo(updatedBlog.getTitolo());
        found.setCover(updatedBlog.getCover());
        found.setMinutiLettura(updatedBlog.getMinutiLettura());
        found.setAuthor(updatedBlog.getAuthor());
        return this.blogDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        BlogPost found = this.findById(id);
        this.blogDAO.delete(found);
    }
}
