package de.seven.product.adapter.primary;

import de.seven.product.application.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/host")
@AllArgsConstructor
public class HostController {
    private final ProductService productService;
    @PostMapping("/")
    public Host saveHost(@RequestBody Host host) {
        return productService.insertHost(host);
    }

}
