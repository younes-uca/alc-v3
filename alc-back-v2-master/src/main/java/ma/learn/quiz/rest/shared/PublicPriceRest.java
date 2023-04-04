package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.Price;
import ma.learn.quiz.service.impl.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/price")
public class PublicPriceRest {
    @Autowired
    private PriceService priceService;

    @GetMapping("/min-price/{isGroup}")
    public Price findMinPrice(@PathVariable boolean isGroup) {
        return priceService.findMinPrice(isGroup);
    }

    @GetMapping("/max-price/{isGroup}")
    public Price findMaxPrice(@PathVariable boolean isGroup) {
        return priceService.findMaxPrice(isGroup);
    }
}
