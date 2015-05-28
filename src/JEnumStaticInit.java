import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum JEnumStaticInit {

    A("id");

    private static final Map<String, JEnumStaticInit> byId;

    static {
        byId = stream(values()).
                collect(toMap(ft -> ft.id, ft -> ft));
    }

    private final String id;

    JEnumStaticInit(String id) {
        this.id = id;
    }

    /**
     * Resolves feature by id. It's required to separate DB values from source code, so source code could
     * be safely refactored (e.g. constants renamed and/or reordered) without breaking mapping.
     */
    public static JEnumStaticInit byId(String id) {
        if (byId.containsKey(id)) {
            return byId.get(id);
        }

        throw new IllegalArgumentException("Feature type with id=" + id + " is not defined");
    }

    public static void main(String[] args) {
        System.out.println(JEnumStaticInit.byId("id"));
    }
}
