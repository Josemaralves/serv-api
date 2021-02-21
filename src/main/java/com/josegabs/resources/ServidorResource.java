package com.josegabs.resources;

import com.josegabs.dto.ServidorDTO;
import com.josegabs.repository.ServidorRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/servidor")
public class ServidorResource {

    @Inject ServidorRepository servidorRepository;

    @GET
    @Path("/{nome}")
    public List<ServidorDTO> findServidorByName(@PathParam("nome") String name){
        return servidorRepository.listServidorByName(name);
    }
}
