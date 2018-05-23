package cn.bos.content.service.impl;

import cn.bos.content.service.ContentService;
import cn.bos.mapper.TbContentMapper;
import cn.bos.pojo.TbContent;
import cn.bos.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {


    @Autowired
    private TbContentMapper contentMapper;



    /**
     * 根据内容分类id查询内容列表
     * <p>Title: getContentListByCid</p>
     * <p>Description: </p>
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }
}
