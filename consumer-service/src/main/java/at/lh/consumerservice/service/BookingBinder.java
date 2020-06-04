package at.lh.consumerservice.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BookingBinder {
    String BOOKINGS = "bookings";

    @Input(BOOKINGS)
    SubscribableChannel bookings();
}
