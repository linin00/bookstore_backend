package xyz.linin.bookstore_backend.constants;

public class SecurityConstants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String SECRET_KEY = "jwt_signature_secret_key";
    public static final int EXPIRATION = 86_400_400;
    public static final String ROLE_CLAIMS = "role";
    public static final String ISSUER = "bookstore";

    public static final String[] PUBLIC_ROUTES = {
            "/users/register",
            "/auth/*"
    };
}
