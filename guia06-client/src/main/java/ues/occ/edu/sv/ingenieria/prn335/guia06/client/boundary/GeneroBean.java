/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia06.client.boundary;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Genero;

/**
 *
 * @author mauricio
 */
@Named(value="generoBean")
@ViewScoped
public class GeneroBean implements Serializable{
    WebsocketClient client = new WebsocketClient();
    List<Genero> generosList = new ArrayList();
    Genero genero = new Genero();
    String[] JsonHeads = {"activo", "descripcion", "idGenero", "nombre"};

    public void btnCrearHandler() throws IOException, InterruptedException {
        if (genero != null) {
            String message = "{\"activo\":" + genero.getActivo() + ",\"descripcion\":\"" + genero.getDescripcion() + "\",\"idGenero\":" + genero.getIdGenero() + ",\"nombre\":\"" + genero.getNombre() + "\"}";
            client.sendMessage(message);
            Thread.sleep(100);
            btnCancelarHandler();
        }
    }

    public void btnCancelarHandler() {
        genero.setActivo(false);
        genero.setDescripcion(null);
        genero.setIdGenero(null);
        genero.setNombre(null);
        generosFind();
    }

    @PostConstruct
    public void generosFind() {
        try {
            System.out.println("Mensaje");
            client.sendMessage(":v");
            Thread.sleep(100);
            generosList.clear();
            JSONArray jsonArray = new JSONArray(client.message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                generosList.add(new Genero(json.getInt("idGenero"), json.getString("nombre"), json.getString("descripcion"), json.getBoolean("activo")));
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(GeneroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Genero> getGenerosList() {
        return generosList;
    }
 
}
