package com.lzy.platform.generator.utils;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

/**
 * <p>
 * 生成代码工具类
 * </p>
 *
 * @author lzy
 * @since 2023/5/8 9:42
 */
public class GeneratorUtil {
    private static final Integer GENERATOR_UI_PORT = 8068;


    /**
     * 得到生成代码配置类
     *
     * @param jdbcUrl     jdbc url
     * @param userName    用户名
     * @param passWord    密码
     * @param driverClass 驱动名称
     * @param scheName    数据库名称
     * @param packageName 包名
     * @return {@link GeneratorConfig}
     */
    public static GeneratorConfig getGeneratorConfig(String jdbcUrl, String driverClass, String userName, String passWord, String scheName, String packageName, NameConverter nameConverter) {
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
                .jdbcUrl(jdbcUrl)
                .userName(userName)
                .password(passWord)
                .driverClassName(driverClass)
                .schemaName(scheName)
                .basePackage(packageName)
                .port(GENERATOR_UI_PORT)
                .nameConverter(new NameConverter() {
                    @Override
                    public String mapperNameConvert(String entityName) {
                        return this.entityNameConvert(entityName) + "Mapper";
                    }

                    @Override
                    public String mapperXmlNameConvert(String entityName) {
                        return this.entityNameConvert(entityName) + "Mapper";
                    }

                    @Override
                    public String serviceNameConvert(String entityName) {
                        return this.entityNameConvert(entityName) + "Service";
                    }

                    @Override
                    public String serviceImplNameConvert(String entityName) {
                        return this.entityNameConvert(entityName) + "ServiceImpl";
                    }

                    @Override
                    public String controllerNameConvert(String entityName) {
                        return this.entityNameConvert(entityName) + "Controller";
                    }
                })
                .build();

        if (nameConverter != null) {
            generatorConfig.setNameConverter(nameConverter);
        }
        return generatorConfig;
    }
}
