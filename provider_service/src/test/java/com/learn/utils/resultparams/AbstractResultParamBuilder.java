package com.learn.utils.resultparams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WBZ
 */
public abstract class AbstractResultParamBuilder implements Builder {
    private Builder root;
    private final Builder pre;
    private final Map<String, Builder> pAnoMap;

    public AbstractResultParamBuilder(Builder builder) {
        this.pre = builder;
        this.pAnoMap = new HashMap<>();
        if (this.pre == null) {
            this.root = this;
        }
    }

    protected AbstractResultParamBuilder() {
        this(null);
    }

    @Override
    public Builder root() {
        if (this.root == null) {
            return this.pre.root();
        }
        return root;
    }

    @Override
    public Builder rePreLevel() {
        return this.pre;
    }

    @Override
    public Builder field(String name) {
        this.pAnoMap.put(name, null);
        return this;
    }

    @Override
    public Builder map(String name) {
        Builder builder = new MapResultParamBuilder(this);
        this.pAnoMap.put(name, builder);

        return builder;
    }

    @Override
    public Builder list(String name) {
        Builder builder = new ListResultParamBuilder(this);
        this.pAnoMap.put(name, builder);

        return builder;
    }

    @Override
    public Map<String, Builder> getInfos() {
        return this.pAnoMap;
    }
}
