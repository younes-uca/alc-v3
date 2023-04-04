package ma.learn.quiz.rest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ma.learn.quiz.filter.JwtConstant.FORWARD_SLASH;
import static ma.learn.quiz.filter.JwtConstant.RECLAMATION_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
@RestController
@RequestMapping("/reclamation")
public class ReclamationRest {

    @GetMapping(path = "/image/{id}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getReclamationImg(@PathVariable("id") String id, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(RECLAMATION_FOLDER + id + FORWARD_SLASH + fileName));
    }
}
