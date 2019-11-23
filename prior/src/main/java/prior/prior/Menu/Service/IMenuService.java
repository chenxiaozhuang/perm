package prior.prior.Menu.Service;

import prior.prior.Menu.Entity.Function;
import prior.prior.Menu.vo.MenuVo;

import java.util.List;

public interface IMenuService {

    /**
     * 生成菜单
     * @param userId
     * @return
     */
    public List<MenuVo> findMenu(int userId);

    /**
     * 找到所有的权限
     * @return
     */
    public List<Function> findAllFunction();
}
