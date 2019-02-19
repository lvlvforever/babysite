
package io.lvlvforever.babysite.common.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;


import java.io.InputStream;

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


}
