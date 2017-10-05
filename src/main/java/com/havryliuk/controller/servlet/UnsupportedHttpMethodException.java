package com.havryliuk.controller.servlet;

class UnsupportedHttpMethodException extends Exception {
    UnsupportedHttpMethodException(String message) {
        super(message);
    }
}
