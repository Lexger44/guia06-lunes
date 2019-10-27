/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.kk.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.Decoder;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Genero;

/**
 *
 * @author mauricio
 */
@LocalBean
public class GeneroDecoder implements Decoder.Text<Genero> {
    
    Jsonb jsonb = JsonbBuilder.create();

    @Override
    public Genero decode(String arg0) throws DecodeException {
        if (arg0 != null && !arg0.isEmpty() && !arg0.equals("")) {
            try {
                return jsonb.fromJson(arg0, Genero.class);
            } catch (JsonbException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public boolean willDecode(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(EndpointConfig ec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


