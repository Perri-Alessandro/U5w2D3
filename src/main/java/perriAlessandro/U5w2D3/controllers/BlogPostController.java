package perriAlessandro.U5w2D3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import perriAlessandro.U5w2D3.entities.BlogPost;
import perriAlessandro.U5w2D3.services.BlogPostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPost")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;


    // GET .../blogPost
    @GetMapping
    private List<BlogPost> getAllBlogPost() {
        return this.blogPostService.getBlogPostList();
    }

    // POST .../blogPost (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    private BlogPost saveBlogPost(@RequestBody BlogPost body) {
        return this.blogPostService.saveBlogPost(body);
    }

    // GET .../blogPost/{postId}
    @GetMapping("/{blogId}")
    private BlogPost findBlogById(@PathVariable UUID blogId) {
        return this.blogPostService.findById(blogId);
    }

    // PUT .../blogPost/{postId} (+ body)
    @PutMapping("/{blogId}")
    private BlogPost findBlogByIdAndUpdate(@PathVariable UUID blogId, @RequestBody BlogPost body) {
        return this.blogPostService.findByIdAndUpdate(blogId, body);
    }

    // DELETE .../blogPost/{postId}
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status Code 204
    private void findByBlogIdAndDelete(@PathVariable UUID blogId) {
        this.blogPostService.findByIdAndDelete(blogId);
    }

}
