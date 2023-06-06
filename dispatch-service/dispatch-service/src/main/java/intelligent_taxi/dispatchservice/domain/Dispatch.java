package intelligent_taxi.dispatchservice.domain;

import intelligent_taxi.dispatchservice.domain.policy.PriceCalculator;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Dispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double presentLatitude;

    @Column(nullable = false)
    private Double presentLongitude;

    @Column(nullable = false)
    private Double destinationLatitude;

    @Column(nullable = false)
    private Double destinationLongitude;

    @Column(nullable = false)
    private String username;

    private long price;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private Dispatch(Double presentLatitude, Double presentLongitude, Double destinationLatitude, Double destinationLongitude, String username, long price) {
        this.presentLatitude = presentLatitude;
        this.presentLongitude = presentLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.username = username;
        this.price = price;
    }

    public static Dispatch create(DispatchRequest requestDto, String username) {
        return new Dispatch(
                requestDto.getPresentLatitude(),
                requestDto.getPresentLongitude(),
                requestDto.getDestinationLatitude(),
                requestDto.getDestinationLongitude(),
                username,
                PriceCalculator.calculatePrice(requestDto)
        );
    }
}
