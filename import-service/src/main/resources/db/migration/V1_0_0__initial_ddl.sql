CREATE TABLE booking
(
    id               BIGSERIAL    NOT NULL,
    check_counter    INTEGER      NOT NULL,
    reference        VARCHAR(255) NOT NULL,
    parent_reference VARCHAR(255),
    processed        BOOLEAN      NOT NULL,
    created_on       TIMESTAMP    NOT NULL,
    modified_on      TIMESTAMP    NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (id),
    CONSTRAINT booking_reference_unique UNIQUE (reference)
);

CREATE INDEX booking_parent_reference_index ON booking (parent_reference);
