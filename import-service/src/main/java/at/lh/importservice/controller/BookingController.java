package at.lh.importservice.controller;

import at.lh.importservice.domain.Booking;
import at.lh.importservice.service.BookingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log
@RestController
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public void booking(@RequestBody @Valid Booking booking) {
        service.save(booking);
    }
}
