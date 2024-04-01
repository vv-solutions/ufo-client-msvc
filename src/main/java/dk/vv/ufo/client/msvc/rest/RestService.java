package dk.vv.ufo.client.msvc.rest;

import dk.vv.ufo.client.msvc.dtos.ComplexDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.NoCache;

import java.util.List;


@RegisterRestClient(configKey = "rest-service")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/")
public interface RestService {

    @GET
    @Path("number/{count}")
    @NoCache
    List<Integer> getNumber(@PathParam("count") int count);


    @GET
    @Path("complex/{count}")
    @NoCache
    List<ComplexDTO> getComplex(@PathParam("count") int count);

}
