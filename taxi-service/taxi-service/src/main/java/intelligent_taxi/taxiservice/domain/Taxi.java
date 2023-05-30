package intelligent_taxi.taxiservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxiGrade taxiGrade;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String licenseNum;

    @Column(nullable = false, unique = true)
    private String phoneNum;

    private Taxi(Region region, TaxiGrade taxiGrade, String username, String licenseNum, String phoneNum) {
        this.region = region;
        this.taxiGrade = taxiGrade;
        this.username = username;
        this.licenseNum = licenseNum;
        this.phoneNum = phoneNum;
    }
}
