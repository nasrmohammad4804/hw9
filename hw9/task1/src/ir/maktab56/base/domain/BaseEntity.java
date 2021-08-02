package ir.maktab56.base.domain;

public abstract class BaseEntity<ID> {

    private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }
    public BaseEntity() {}

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
