package com.example.jess.reportes.entidades;

import android.graphics.Bitmap;

public class Reportes {
    private Integer idReporte;
    private String claveCatastral;
    private String folioCatastral;
    private String tiempo;
    private String tipovisita;
    private String urlFotos;



    public String getTipovisita() {
        return tipovisita;
    }

    public void setTipoVisita(String TipoVisita) {
        this.tipovisita = TipoVisita;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getClaveCatastral() {
        return claveCatastral;
    }

    public void setClaveCatastral(String ClaveCatastral) {
        this.claveCatastral = ClaveCatastral;
    }

    public String getFolioCatastral() {
        return folioCatastral;
    }

    public void setFolioCatastral(String FolioCatastral) {
        this.folioCatastral = FolioCatastral;
    }

    public String getTiempo() {
        return tiempo;
    }



    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getUrlFotos() {
        return urlFotos;
    }



    public void setUrlFotos(String urlFotos) {
        this.urlFotos = urlFotos;
    }




}
