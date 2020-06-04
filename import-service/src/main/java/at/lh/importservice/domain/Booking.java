package at.lh.importservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@Table(schema = "import_service")
@EntityListeners(AuditingEntityListener.class)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private Integer checkCounter;

    @NotEmpty
    private String reference;

    @EqualsAndHashCode.Exclude
    private String parentReference;

    private boolean processed = false;

    @CreatedDate
    @Column(updatable = false)
    @EqualsAndHashCode.Exclude
    private Instant createdOn;

    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private Instant modifiedOn;

    public Booking() {
        checkCounter = 0;
    }

    public boolean hasNoParent() {
        return StringUtils.isEmpty(parentReference);
    }

    public void incrementCheckCounter() {
        this.checkCounter++;
    }
}
