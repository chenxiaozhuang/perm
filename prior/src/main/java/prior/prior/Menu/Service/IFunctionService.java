package prior.prior.Menu.Service;

import prior.prior.Menu.Entity.Function;

import java.util.List;

public interface IFunctionService {
    public List<Function> findFunctionList();

    public int insertFunction(Function function);

    public Function findByFuncName(String funcName);

    public List<Function> findbyParentId(int parentId);

    public int deleteByFuncId(int funcId);

    public Function findByFuncId(int id);

    public List<Function> findTreeByParentId(int id);

    public int updateFunction(Function function);
}
