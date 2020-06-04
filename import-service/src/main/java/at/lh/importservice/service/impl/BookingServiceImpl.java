package at.lh.importservice.service.impl;

import at.lh.importservice.domain.Booking;
import at.lh.importservice.repository.BookingRepository;
import at.lh.importservice.service.BookingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log
@Service
@EnableBinding(BookingChannel.class)
class BookingServiceImpl implements BookingService {
    private final BookingChannel channel;
    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingChannel channel, BookingRepository repository) {
        this.channel = channel;
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(Booking booking) {
        repository.save(booking);
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void checkBookings() {
        getNextAvailableBooking().ifPresent(this::publish);
    }

    private Optional<Booking> getNextAvailableBooking() {
        Optional<Booking> opt = repository.findFirstByProcessedOrderByModifiedOn(false);

        if (opt.isPresent()) {
            Booking booking = opt.get();
            booking.incrementCheckCounter();

            if (isReadyToProcess(booking)) {
                return Optional.of(booking);
            }
        }

        return Optional.empty();
    }

    private void publish(Booking booking) {
        channel.bookings().send(MessageBuilder.withPayload(booking).build());
        booking.setProcessed(true);
        log.info("published: " + booking);
    }

    private boolean isReadyToProcess(Booking booking) {
        return booking.hasNoParent() || repository.findBookingByReference(booking.getParentReference())
                .filter(Booking::isProcessed)
                .isPresent();
    }
}
