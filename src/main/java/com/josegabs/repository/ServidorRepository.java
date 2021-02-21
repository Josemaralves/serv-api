package com.josegabs.repository;

import com.josegabs.dto.CargoDTO;
import com.josegabs.dto.ServidorDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServidorRepository {

    @Inject MongoCollection servidorCollection;

    public List<ServidorDTO> listServidorByName(String name){
        List<ServidorDTO> servidorDTOS = new ArrayList<>();

        MongoCursor<Document> cursor = servidorCollection.aggregate(getAggregateFilter(name)).iterator();

        while (cursor.hasNext()){
            Document document = cursor.next();
            ServidorDTO servidorDTO = extractServidor(document);

            servidorDTOS.add(servidorDTO);
        }

        return servidorDTOS;
    }

    private ServidorDTO extractServidor(Document document) {
        ServidorDTO servidor = new ServidorDTO();

        servidor.setCpf(document.getString("cpf"));
        servidor.setNome(document.getString("nome"));
        List<Document> cargoRaw = document.getList("funcao", Document.class);

        for (Document cargo: cargoRaw) {
            CargoDTO cargoDTO = new CargoDTO();

            cargoDTO.setDescCargo(cargo.getString("desc_cargo"));
            cargoDTO.setOrganizacao(cargo.getString("organizacao"));

            servidor.getCargo().add(cargoDTO);
        }

        return servidor;

    }

    private List<Bson> getAggregateFilter(String name){
        Bson lookup = new Document("$lookup",
                new Document("from", "cargo")
                        .append("localField", "_id")
                        .append("foreignField", "id_servidor")
                        .append("as", "funcao"));

        Bson match = new Document("$match",
                new Document("nome", name));

        List<Bson> filters = new ArrayList<>();
        filters.add(lookup);
        filters.add(match);

        return filters;
    }

}
