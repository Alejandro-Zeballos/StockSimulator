package model.observer.subject.model;

import utils.TypeUpdate;

public class Update <E> {

    private E obj;
    private TypeUpdate typeUpdate;

    public Update(E obj, TypeUpdate typeUpdate) {
        this.obj = obj;
        this.typeUpdate = typeUpdate;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public TypeUpdate getTypeUpdate() {
        return typeUpdate;
    }

    public void setTypeUpdate(TypeUpdate typeUpdate) {
        this.typeUpdate = typeUpdate;
    }
}
