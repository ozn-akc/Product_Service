package de.seven.product.adapter.secondary.http;

import de.seven.product.application.adapter.secondary.SearchClient;
import de.seven.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Profile("http")
@RequiredArgsConstructor
public class HTTPClient implements SearchClient {
    private final WebClient webClient;

    @Autowired
    public HTTPClient(WebClient.Builder webClientBuilder, @Value("${search.client.http}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return webClient.post()
                .uri("/product")
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public void deleteProduct(String productId) {
        webClient.delete()
                .uri("/product/{productId}", productId) // Assuming productId is part of the URL
                .retrieve();
    }
}
