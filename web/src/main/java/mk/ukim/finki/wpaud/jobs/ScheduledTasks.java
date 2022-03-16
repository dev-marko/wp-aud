package mk.ukim.finki.wpaud.jobs;

import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ProductService productService;

    public ScheduledTasks(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView() {
//        this.productService.refreshMaterializedView();
    }
}
