package xyz.linin.bookstore_backend.constants;

public enum Role {
    user("ROLE_USER"),
    admin("ROLE_ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
