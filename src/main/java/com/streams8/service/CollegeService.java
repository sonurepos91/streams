package com.streams8.service;

import org.springframework.http.ResponseEntity;

public interface CollegeService<T> {
    ResponseEntity<T> playWithStreams ();
}
