package at.lh.consumerservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class Booking {
    private String reference;

    @JsonProperty("parentReference")
    private String parent;

    @JsonProperty("createdOn")
    private Instant deliveryTime;
}
