package at.lh.consumerservice.handler;


import at.lh.consumerservice.domain.Booking;
import at.lh.consumerservice.service.BookingBinder;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Log
@EnableBinding(BookingBinder.class)
public class BookingHandler {
    @StreamListener(target = BookingBinder.BOOKINGS)
    public void received(Booking booking) {
        log.info("received new booking: " + booking);
    }
}
