package net.greffchandler.clients.identityClient.models;

import lombok.Data;

@Data
public class PaginatedResults<T> {
    private String previousPage;
    private String nextPage;
    private T[] results;
}
