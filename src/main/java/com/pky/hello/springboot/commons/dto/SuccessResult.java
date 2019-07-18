package com.pky.hello.springboot.commons.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 通用成功响应结果
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResult<T extends AbstractBaseDomain> extends AbstractBaseResult {
    private Links links;

    private List<DataBean> data;

    /**
     * 构建删除响应结果
     * @param self
     */
    public SuccessResult(String self){
        links = new AbstractBaseResult.Links();
        links.setSelf(self);
    }

    /**
     * 构建单笔数据响应结果
     */
    public SuccessResult(String self, T attributes) {
        links = new Links();
        links.setSelf(self);

        data = Lists.newArrayList();
        createDataBean(null, attributes);
    }

    /**
     * 构建多笔数据响应结果
     * @param self
     * @param next
     * @param last
     */
    public SuccessResult(String self, int next, int last, List<T> attributes) {
        links = new Links();
        links.setSelf(self);
        links.setNext(self + "?page=" + next);
        links.setLast(self + "?page=" + last);

        attributes.forEach(attribute -> createDataBean(self, attribute));
    }

    private void createDataBean(String self, T attributes) {
        if(data == null) {
            data = Lists.newArrayList();
        }

        DataBean dataBean = new DataBean();
        dataBean.setId(attributes.getId());
        // 设置类型，其中类型为实体类的类型
        dataBean.setType(attributes.getClass().getSimpleName());
        dataBean.setAttributes(attributes);

        // 判断是否多条数据
        if(StringUtils.isNotBlank(self)) {
            Links links = new Links();
            links.setSelf(self + "/" + attributes.getId());
            dataBean.setLinks(links);
        }

        data.add(dataBean);
    }
}
