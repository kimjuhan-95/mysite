package com.bit2025.mysite.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {
    void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
