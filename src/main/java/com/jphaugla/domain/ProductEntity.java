package com.jphaugla.domain;

import com.rnbwarden.redisearch.entity.RediSearchField;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import lombok.Data;
import com.rnbwarden.redisearch.entity.RediSearchEntity;
import com.rnbwarden.redisearch.entity.RedisSearchableEntity;


@DependsOn("RediSearchAutoConfiguration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RediSearchEntity(name = "product")

public class ProductEntity implements RedisSearchableEntity {
    @Autowired(required = true)
   public static final String COLUMN1 = "column1";
   public static final String COLUMN2 = "column2";

   private String key;

   @RediSearchField(name = COLUMN1, sortable = true)
       String column1;
   @RediSearchField(name = COLUMN2)
       String column2;

   @Override
       public String getPersistenceKey() {
          return key;
   }

}
