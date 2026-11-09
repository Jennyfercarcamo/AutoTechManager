/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;


import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class Diagnostico {
    private int idDiagnostico;
    private int idOrden;
    private String evaluacionTecnica;
    private String evidencias;
    private Timestamp fechaDiagnostico;

    public Diagnostico() {
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getEvaluacionTecnica() {
        return evaluacionTecnica;
    }

    public void setEvaluacionTecnica(String evaluacionTecnica) {
        this.evaluacionTecnica = evaluacionTecnica;
    }

    public String getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(String evidencias) {
        this.evidencias = evidencias;
    }

    public Timestamp getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Timestamp fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }
}
    

