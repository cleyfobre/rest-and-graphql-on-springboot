package com.demo.springgraphql2.config.graphql.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.scalar.CoercingUtil;
import graphql.schema.Coercing;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

@Component
public final class LocalDateTimeScalar {

    public static final GraphQLScalarType INSTANCE;

    private static final String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    private LocalDateTimeScalar() {
    }

    static {
        Coercing<LocalDateTime, String> coercing = new Coercing<LocalDateTime, String>() {

            public LocalDateTime parseLocalDateTime(String str, Function<String, RuntimeException> exceptionMaker) {
                try {
                    SimpleDateFormat dateFormatParser = new SimpleDateFormat(FORMAT_STRING);
                    dateFormatParser.setLenient(false);
                    dateFormatParser.parse(str);
                    LocalDateTime ldt = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(FORMAT_STRING));
                    return ldt;
                } catch (ParseException e) {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[parseLocalDateTime]")
                            .append(":").append("not format(").append(FORMAT_STRING).append(")")
                            .append(" :: ").append(e.getMessage());
                    throw exceptionMaker.apply(errorStr.toString());
                } catch (Exception e) {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[parseLocalDateTime]")
                            .append(":").append("Unknown Error")
                            .append(" :: ").append(e.getMessage());
                    throw exceptionMaker.apply(errorStr.toString());
                }
            }

            public String parseString(LocalDateTime ldt, Function<String, RuntimeException> exceptionMaker) {
                try {
                    return ldt.format(DateTimeFormatter.ofPattern(FORMAT_STRING));
                } catch (Exception e) {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[parseString]")
                            .append(":").append("Unknown Error")
                            .append(" :: ").append(e.getMessage());
                    throw exceptionMaker.apply(errorStr.toString());
                }
            }

            @Override
            public String serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale) {
                if (dataFetcherResult instanceof LocalDateTime objLdt) {
                    return parseString(objLdt, CoercingSerializeException::new);
                } else {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[serialize]")
                            .append(":").append("Expected a LocalDateTime Object.");
                    throw new CoercingSerializeException(errorStr.toString());
                }
            }

            @Override
            public LocalDateTime parseValue(Object input, GraphQLContext graphQLContext, Locale locale) {
                if (input instanceof String objStr) {
                    return parseLocalDateTime(objStr, CoercingSerializeException::new);
                } else {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[parseValue]")
                            .append(":").append("Expected a String Object.");
                    throw new CoercingSerializeException(errorStr.toString());
                }
            }

            @Override
            public LocalDateTime parseLiteral(Value<?> input, CoercedVariables variables, GraphQLContext graphQLContext,
                    Locale locale) {
                if (input instanceof StringValue strValue) {
                    String str = strValue.getValue();
                    return parseLocalDateTime(str, CoercingSerializeException::new);
                } else {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[parseLiteral]")
                            .append(":").append("Expected AST type 'StringValue'").append(" :: ")
                            .append(CoercingUtil.typeName(input));
                    throw new CoercingSerializeException(errorStr.toString());
                }
            }

            @Override
            public Value<?> valueToLiteral(Object input, GraphQLContext graphQLContext, Locale locale) {
                if (input instanceof LocalDateTime objLdt) {
                    String str = parseString(objLdt, CoercingSerializeException::new);
                    return StringValue.newStringValue(str).build();
                } else {
                    StringBuilder errorStr = new StringBuilder()
                            .append("[localDateTimeScalar]").append("[valueToLiteral]")
                            .append(":").append("Expected a LocalDateTime Object.");
                    throw new CoercingSerializeException(errorStr.toString());
                }
            }

        };
        INSTANCE = GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("A java.time.LocalDateTime type")
                .coercing(coercing)
                .build();

    }

}
