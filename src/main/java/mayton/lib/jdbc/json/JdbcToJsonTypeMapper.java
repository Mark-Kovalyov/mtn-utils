package mayton.lib.jdbc.json;

import mayton.lib.jdbc.JdbcTypeMapper;

import java.util.Optional;

public class JdbcToJsonTypeMapper  implements JdbcTypeMapper {

    @Override
    public Optional<String> fromJDBC(String typeName, Optional<Integer> scala, Optional<Integer> precision) {
        switch (typeName) {
            case "text", "varchar", "varchar2", "string" : return Optional.of("string");
            default: return Optional.empty();
        }
    }
}
