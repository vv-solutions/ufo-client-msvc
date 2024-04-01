package dk.vv.ufo.client.msvc.rest;

import dk.vv.ufo.server.msvc.grpc.ComplexObjectRequest;
import dk.vv.ufo.server.msvc.grpc.DataGrpc;

import dk.vv.ufo.server.msvc.grpc.Empty;
import dk.vv.ufo.server.msvc.grpc.Number;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.NoCache;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@Path("/api/")
public class ServerResource {

    RestService restService;

    DataGrpc dataGrpc;

    @Inject
    public ServerResource(@RestClient RestService restService,@GrpcClient DataGrpc dataGrpc) {
        this.restService = restService;
        this.dataGrpc=dataGrpc;
    }

    @GET
    @NoCache
    @Path("rest/{count}")
    public String getRestData(@PathParam("count") int count){


        var result = restService.getNumber(count);

        return "Ok";
    }

    @GET
    @NoCache
    @Path("grpc/{count}")
    public String getGrpcData(@PathParam("count") int count) {



        var result = dataGrpc.getNumber(Number.newBuilder().setData(count).build()).await().indefinitely().getDataList();

        return "Ok";
    }



    @GET
    @NoCache
    @Path("grpc/complex/{count}")
    public String getComplexGrpc(@PathParam("count") int count) {
        var result = dataGrpc.getComplexData(ComplexObjectRequest.newBuilder().setCount(count).build()).await().indefinitely();
        return "Ok";
    }

    @GET
    @NoCache
    @Path("rest/complex/{count}")
    public String getComplexRest(@PathParam("count") int count) {
        var result = restService.getComplex(count);
        return "Ok";
    }
}
