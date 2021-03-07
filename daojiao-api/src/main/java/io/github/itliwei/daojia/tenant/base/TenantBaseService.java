package io.github.itliwei.daojia.tenant.base;

import cn.hutool.core.util.StrUtil;
import io.github.itliwei.daojia.tenant.config.ContextUtil;
import io.github.itliwei.vboot.vorm.BaseService;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.VService;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author : liwei
 * @date : 2021/03/06 16:18
 * @description : 基础service
 */
public class TenantBaseService<T extends TenantBaseEntity> extends BaseService<T> {
    @Autowired
    private VService vService;

    @Override
    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public <T extends TenantBaseEntity> int save(T entity){
        buildTenantEntity(entity);
        return vService.save(entity);
    }

    public <T extends TenantBaseEntity> int update(T entity){
        buildTenantEntity(entity);
        return vService.update(entity);
    }

    public <T extends TenantBaseEntity> int update(T entity,boolean useNull ){
        buildTenantEntity(entity);
        return vService.update(entity,useNull);
    }

    @Override
    public <T extends IdEntity> T findById(Long id){
        String tenant = ContextUtil.getTenant();
        if (StrUtil.isBlank(tenant)){
            return super.findById(id);
        }
        return vService.find((Class<T>) getTClass(), Condition.eq(IdEntity.ID_PN, id),
                Condition.eq("tenant",tenant));
    }

    public <T extends TenantBaseEntity> T findOne(TenantBaseQueryModel queryModel){
        buildTenantQuery(queryModel);
        return vService.find((Class<T>) this.getTClass(), queryModel);
    }

    public <T extends TenantBaseEntity> List<T> findList(TenantBaseQueryModel queryModel){
        buildTenantQuery(queryModel);
        return vService.findList((Class<T>) this.getTClass(), queryModel);
    }

    public <T extends TenantBaseEntity> Page<T> findPage(TenantBaseQueryModel queryModel){
        buildTenantQuery(queryModel);
        return vService.findPage((Class<T>) this.getTClass(), queryModel);
    }

    public <T extends TenantBaseEntity> int delete(TenantBaseQueryModel queryModel){
        buildTenantQuery(queryModel);
        return vService.delete((Class<T>)getTClass(),queryModel);
    }

    public <T extends TenantBaseEntity> long count(TenantBaseQueryModel queryModel){
        buildTenantQuery(queryModel);
        return vService.count((Class<T>)getTClass(),queryModel);
    }

    @Override
    public <T extends IdEntity> int delete(Long id){
        String tenant = ContextUtil.getTenant();
        if (StrUtil.isBlank(tenant)) {
            return vService.delete((Class<T>) getTClass(), id);
        }
        return vService.delete((Class<T>) getTClass(),Condition.eq(IdEntity.ID_PN, id),Condition.eq("tenant",tenant));
    }

    @Override
    public <T extends IdEntity> int deleteBatch(List<Long> ids){
        String tenant = ContextUtil.getTenant();
        if (StrUtil.isBlank(tenant)) {
            return vService.delete((Class<T>) getTClass(), ids);
        }
        return vService.delete((Class<T>)getTClass(),Condition.in(IdEntity.ID_PN, ids),Condition.eq("tenant",tenant));
    }



    private void buildTenantQuery(TenantBaseQueryModel queryModel) {
        String tenant = ContextUtil.getTenant();
        if (!StrUtil.isBlank(tenant)) {
            queryModel.setTenantEQ(tenant);
        }
    }

    private void buildTenantEntity(TenantBaseEntity entity) {
        String tenant = ContextUtil.getTenant();
        if (!StrUtil.isBlank(tenant)) {
            entity.setTenant(tenant);
        }
    }
}
