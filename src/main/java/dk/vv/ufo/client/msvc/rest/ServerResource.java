package dk.vv.ufo.client.msvc.rest;

import dk.vv.ufo.server.msvc.grpc.DataGrpc;

import dk.vv.ufo.server.msvc.grpc.empty;
import dk.vv.ufo.server.msvc.grpc.number;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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
    @Path("rest/{count}")
    public List<Integer> getRestData(@PathParam("count") int count){

        List<Integer> numbers = new ArrayList<>();

//        for (int i = 0; i < count; i++) {
            numbers.add(restService.getNumber(5).getNumber());
//        }

        return numbers;
    }

    @GET
    @Path("grpc/{count}")
    public List<Integer> getGrpcData(@PathParam("count") int count) {

        List<Integer> numbers = new ArrayList<>();

//        for (int i = 0; i < count; i++) {
            numbers.add(dataGrpc.getNumber(number.newBuilder().setData(5).build()).await().indefinitely().getData());
//        }

        return numbers;
    }

    @GET
    @Path("grpc/text")
    public String getGrpcText() {

        String result = null;
        result = dataGrpc.getText(empty.newBuilder().build()).await().indefinitely().getData();


        return "Ok";
    }

    @GET
    @Path("rest/text")
    public String getRestText() {

        String result = null;
        result = restService.getText().getData();

        return "Ok";
    }
}
