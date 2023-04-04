package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.Price;
import ma.learn.quiz.service.impl.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/price")
public class AdminPriceRest {
    @Autowired
    private PriceService priceService;

    @GetMapping("/")
    public List<Price> findAll() {
        return priceService.findAll();
    }

    @PostMapping("/")
    public Price save(@RequestBody Price entity) {
        return priceService.save(entity);
    }

}
