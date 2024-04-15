package de.seven.product.adapter.primary;

import de.seven.product.adapter.secondary.postgresql.model.HostDTO;
import de.seven.product.application.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String saveHost(@RequestBody String host) {
        return productService.insertHost(host);
    }

    @DeleteMapping("/{hostId}")
    public void deleteHost(@PathVariable String hostId) {
        productService.deleteHost(hostId);
    }

}
