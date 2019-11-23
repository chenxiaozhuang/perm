package prior.prior.Menu.Service.impl;

import org.springframework.stereotype.Service;
import prior.prior.Menu.Dao.IFunctionMapper;
import prior.prior.Menu.Entity.Function;
import prior.prior.Menu.Service.IMenuService;
import prior.prior.Menu.vo.MenuVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private IFunctionMapper functionMapper;

    public List<MenuVo> findMenu(int userId) {
        List<MenuVo> menus = new ArrayList<MenuVo>();
        List<Function> functions = functionMapper.findMenu(userId);
        if(functions != null && functions.size() > 0){
            for(Function f : functions){
                MenuVo mv = new MenuVo();
                mv.setId(f.getFunc_id().toString());
                mv.setName(f.getFunc_name());
                mv.setUrl(f.getFunc_url());
                if(f.getParent_id() != null)
                {
                    mv.setpId(f.getParent_id().toString());
                    mv.setOpen(false);
                }else{
                    mv.setOpen(true);
                }
                mv.setChecked(false);
                menus.add(mv);
            }
        }
        return menus;
    }
    /**
     * 查询所有的菜单
     */
    public List<Function> findAllFunction() {
        return functionMapper.selectAll();
    }
}
