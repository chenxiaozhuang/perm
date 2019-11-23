package prior.prior.RoleManager.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
    private Integer role_id;
    private String role_name;
    private String note;
    private Boolean system;
    private Integer status;
    private String create_time;
    private String update_time;
}
