package com.demo.springgraphql2.config.graphql;

import com.demo.springgraphql2.config.graphql.scalar.LocalDateTimeScalar;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

@Component
public class GraphQlExtendedScalars {

    public static final GraphQLScalarType LocalDateTime = LocalDateTimeScalar.INSTANCE;

}
