package sadiva.mpi.platformbackend.controller;

public interface HttpFilterConsts {
    String NO_SQLI_REGEXP = "^(?!.*\\b(select|insert|delete|update|drop|truncate|union|--|exec|grant)\\b).*";
}
