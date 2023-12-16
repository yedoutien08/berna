package securite.appSecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    private String email;
    private String password;
    private String oldPassword;
    private String newPassword;
}
