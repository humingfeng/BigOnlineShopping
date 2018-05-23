package cn.bos.content.service;


import cn.bos.pojo.TbContent;

import java.util.List;

/**
     * @ProjectName:    内容
     * @Package:        cn.bos.content.service
     * @ClassName:      ContentService
     * @Description:    java类作用描述
     * @Author:         胡铭锋
     * @CreateDate:     2018/5/23 17:16
     * @Version:        1.0
     */
public interface ContentService {


    List<TbContent> getContentListByCid(long cid);
}
