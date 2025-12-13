package mayton.lib.jdbc;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface JdbcTypeMapper {

    Optional<String> fromJDBC(String typeName, Optional<Integer> scale, Optional<Integer> precision);

}
