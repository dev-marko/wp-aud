package mk.ukim.finki.wpaud.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DiscountDto {

    private LocalDateTime validUntil;

    public DiscountDto(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
