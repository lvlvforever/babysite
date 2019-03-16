
package io.lvlvforever.babysite.blog.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MongoGridFsService {
    @Autowired
    private GridFsOperations operations;

    public String storeFileToGridFs(InputStream inputStream, String fileName){
        try {
            ObjectId id = operations.store(inputStream,fileName);
            return id.toString();

        } catch (Exception e) {
        }

        return null;
    }

    public GridFSFile findFileInGridFs(String objectId) {

        try {
            GridFSFile file = operations.findOne(Query.query(where("_id").is(objectId)));

            return file;

        } catch (Exception e) {
        }

        return null;


    }


}
