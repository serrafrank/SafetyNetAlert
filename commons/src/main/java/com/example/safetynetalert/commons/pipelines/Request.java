package com.example.safetynetalert.commons.pipelines;

import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class Request {

    private final UUID requestId;

    private final ZonedDateTime creationDate;

    protected Request() {
        this.requestId = UUID.randomUUID();
        this.creationDate = ZonedDateTime.now();
    }

    protected Request(UUID requestId) {
        this.requestId = requestId;
        this.creationDate = ZonedDateTime.now();
    }

    public UUID getRequestId() {
        return requestId;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
}
