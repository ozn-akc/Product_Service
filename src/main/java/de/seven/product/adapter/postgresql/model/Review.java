package de.seven.product.adapter.postgresql.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name="Review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String productId;
    @Min(0)
    @Max(5)
    Integer score;
    String comment;
    String userId;

    public de.seven.product.domain.model.Review toDomainReview(){
        de.seven.product.domain.model.Review review = de.seven.product.domain.model.Review.builder()
                .comment(comment)
                .score(score)
                .userId(userId)
                .build();
        return review;
    }

    public static Review fromDomainReview(de.seven.product.domain.model.Review domainReview){
        Review price = Review.builder()
                .userId(domainReview.getUserId())
                .score(domainReview.getScore())
                .comment(domainReview.getComment())
                .build();
        return price;
    }
}
