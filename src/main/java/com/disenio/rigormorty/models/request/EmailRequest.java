package com.disenio.rigormorty.models.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter @Setter
public class EmailRequest {

    String to;
    String title;
    String subject;
    String from;
    String content;
    String nombre;
    String fechaVencimiento;
    String parcela;

    private Map< String, Object > model;
}
