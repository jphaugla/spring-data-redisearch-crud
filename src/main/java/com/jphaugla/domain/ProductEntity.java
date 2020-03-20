package com.jphaugla.domain;

import com.rnbwarden.redisearch.entity.RediSearchEntity;
import com.rnbwarden.redisearch.entity.RediSearchField;
import com.rnbwarden.redisearch.entity.RediSearchFieldType;
import com.rnbwarden.redisearch.entity.RedisSearchableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RediSearchEntity(name = "product")
public class ProductEntity implements RedisSearchableEntity {

    public static final String ARTICLE_NUMBER = "articleNumber";
    public static final String BRAND = "brand";
    public static final String SKUS = "skus";

    private String id;

    @RediSearchField(name = ARTICLE_NUMBER, sortable = true)
    private String articleNumber;

    @RediSearchField(name = BRAND, type = RediSearchFieldType.TAG)
    private Brand brand;

    private List<SkuEntity> skus;

    @Override
    public String getPersistenceKey() {

        return id + "|" + brand.toString();
    }

    @RediSearchField(name = SKUS)
    public Collection<String> getSkuIds() {

        if (skus == null) {
            return Collections.emptyList();
        }
        return skus.stream().map(SkuEntity::getKey).collect(Collectors.toSet());
    }
}
