package com.gitee.gen.gen.oracle;

import com.gitee.gen.gen.GeneratorConfig;
import com.gitee.gen.gen.SQLService;
import com.gitee.gen.gen.TableSelector;

public class OracleService implements SQLService {

    @Override
    public TableSelector getTableSelector(GeneratorConfig generatorConfig, String delFieldPrefix) {
        return new OracleTableSelector(new OracleColumnSelector(generatorConfig), generatorConfig, delFieldPrefix);
    }

}
