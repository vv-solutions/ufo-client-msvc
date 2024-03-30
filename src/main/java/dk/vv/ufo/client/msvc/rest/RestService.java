package dk.vv.ufo.client.msvc.rest;

import dk.vv.ufo.client.msvc.dtos.NumberDTO;
import dk.vv.ufo.client.msvc.dtos.TextDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.List;

//@RegisterRestClient(baseUri = "http://localhost:8081")

@RegisterRestClient(configKey = "rest-service")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/")
public interface RestService {

    @GET
    @Path("number/{number}")
    NumberDTO getNumber(@PathParam("number") int number);

    @GET
    @Path("text")
    TextDTO getText();

}
