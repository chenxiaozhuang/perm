package prior.prior.Menu.Entity;

/**
 * 权限表
 */
public class Function {
    private Integer func_id;
    private String func_name;
    private String func_url;
    private String func_code;
    private Integer parent_id;
    private Integer func_type;
    private Integer status;
    private Integer sort_num;
    private String create_time;
    private String update_time;

    public Integer getFunc_id() {
        return func_id;
    }

    public void setFunc_id(Integer func_id) {
        this.func_id = func_id;
    }

    public String getFunc_name() {
        return func_name;
    }

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }

    public String getFunc_url() {
        return func_url;
    }

    public void setFunc_url(String func_url) {
        this.func_url = func_url;
    }

    public String getFunc_code() {
        return func_code;
    }

    public void setFunc_code(String func_code) {
        this.func_code = func_code;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getFunc_type() {
        return func_type;
    }

    public void setFunc_type(Integer func_type) {
        this.func_type = func_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort_num() {
        return sort_num;
    }

    public void setSort_num(Integer sort_num) {
        this.sort_num = sort_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
