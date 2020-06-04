package at.lh.importservice.repository;

import at.lh.importservice.domain.Booking;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Booking> findFirstByProcessedOrderByModifiedOn(boolean processed);

    Optional<Booking> findBookingByReference(String reference);
}
