package at.lh.importservice.service.impl;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface BookingChannel {
    @Output("bookings")
    MessageChannel bookings();
}
