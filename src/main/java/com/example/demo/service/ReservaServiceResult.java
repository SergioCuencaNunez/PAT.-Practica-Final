package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaServiceResult {

  private boolean flag;
  private String reservaToken;


  public ReservaServiceResult(boolean flag){ this.flag = flag;}

  public boolean isFlag() {
    return flag;
  }

  public String getAccessToken() {
    return reservaToken;
  }
}
