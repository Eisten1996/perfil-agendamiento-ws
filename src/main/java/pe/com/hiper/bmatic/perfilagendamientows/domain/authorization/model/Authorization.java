package pe.com.hiper.bmatic.perfilagendamientows.domain.authorization.model;

public class Authorization {

    private String module;
    private boolean delete;
    private boolean update;
    private boolean insert;

    public Authorization(String module, boolean delete, boolean update, boolean insert) {
        this.module = module;
        this.delete = delete;
        this.update = update;
        this.insert = insert;
    }

    public Authorization(Authorization authorization) {
        this.module = authorization.getModule();
        this.delete = authorization.isDelete();
        this.update = authorization.isUpdate();
        this.insert = authorization.isInsert();
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}