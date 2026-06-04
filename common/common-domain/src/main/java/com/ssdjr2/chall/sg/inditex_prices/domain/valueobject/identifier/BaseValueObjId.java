package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseValueObjId<T> {
    private final T id;

    protected BaseValueObjId(T id) {
        this.id = id;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseValueObjId<?> baseValueObjId = (BaseValueObjId<?>) o;
        return id.equals(baseValueObjId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
