package io.lvlvforever.babysite.config;

import io.lvlvforever.babysite.blog.dao.impl.BaseRepoImpl;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * Created by lvlvforever on 2020/1/7.
 */
public class CustomMongoRepositoryFactoryBean<T extends MongoRepository<S, ID>, S , ID extends Serializable>
        extends MongoRepositoryFactoryBean<T, S, ID> {
    public CustomMongoRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return new LCRRepositoryFactory(operations);
    }

    private static class LCRRepositoryFactory<S, ID extends Serializable> extends MongoRepositoryFactory {
        private final MongoOperations mongoOperations;

        public LCRRepositoryFactory(MongoOperations mongoOperations) {
            super(mongoOperations);
            this.mongoOperations = mongoOperations;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());

            return new BaseRepoImpl<S, ID>((MongoEntityInformation<S, ID>) entityInformation, this.mongoOperations);

        }


        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepoImpl.class;
        }
    }
}
