package io.mangue.search;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

/**
 * Created by misael on 23/10/2015.
 */
@Service
public class ElasticSearchService {

    @Autowired
    protected Client client;

    public IndexResponse index(String doc, String id, String index, String type){
        return client == null ? null:  client.prepareIndex(index, type, id)
                .setSource(doc)
                .execute()
                .actionGet();
    }

    public UpdateResponse update(String doc, String id, String index, String type){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.type(type);
        updateRequest.id(id);
        updateRequest.doc(doc);
        try {
            return client.update(updateRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(String doc, String id, String index, String type){
        index(doc, id, index, type);
    }

    public void delete(String id, String index, String type){
        if(client !=null)
            client.prepareDeleteByQuery(index)
                    .setQuery(QueryBuilders.idsQuery(type).addIds(id))
                    .execute();
    }
}
