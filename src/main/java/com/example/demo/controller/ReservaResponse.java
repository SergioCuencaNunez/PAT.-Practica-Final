package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaResponse {
  private String result;
  private String reservaToken;

  public ReservaResponse(String ko) {
    this.result = ko;
  }
}
